package ru.t1.java.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.model.DataSourceErrorLog;
import ru.t1.java.demo.service.DataSourceErrorLogService;

import java.time.LocalDateTime;


@Aspect
@Component
public class ExceptionLoggingAspect {

    @Autowired
    private DataSourceErrorLogService errorLogService;

    @Around("within(ru.t1.java.demo.*)")
    public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            return result;
        } catch (Exception ex) {
            DataSourceErrorLog errorLog = new DataSourceErrorLog();
            errorLog.setStackTrace(ex.getStackTrace().toString());
            errorLog.setMessage(ex.getMessage());
            errorLog.setMethodSignature(joinPoint.getSignature().toString());
            errorLog.setErrorTime(LocalDateTime.now());
            errorLogService.save(errorLog);
            throw ex;
        }
    }
}

