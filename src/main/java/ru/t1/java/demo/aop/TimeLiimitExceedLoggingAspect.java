package ru.t1.java.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.model.TimeLiimitExceedLog;
import ru.t1.java.demo.service.TimeLiimitExceedLogService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Aspect
@Component

public class TimeLiimitExceedLoggingAspect {
    private static final String TIME_LIMIT_CONFIG = "myapp.time-limit.ms";
    private static final long TIME_LIMIT_MILLI = Long.parseLong(System.getProperty(TIME_LIMIT_CONFIG, "1000"));

    @Autowired
    private TimeLiimitExceedLogService timeLiimitExceedLogService;

    @Around("execution(* *(..))")
    public Object checkExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();
        long endTime = System.nanoTime();
        long executionTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        if (executionTime > TIME_LIMIT_MILLI) {
            TimeLiimitExceedLog exceedLog = new TimeLiimitExceedLog();
            exceedLog.setMethodSignature(joinPoint.getSignature().toShortString());
            exceedLog.setExecutionTime(new BigDecimal(executionTime));
            exceedLog.setLogTime(LocalDateTime.parse(LocalDateTime.now(TimeZone.getDefault().toZoneId()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
            timeLiimitExceedLogService.save(exceedLog);
        }

        return result;
    }
}
