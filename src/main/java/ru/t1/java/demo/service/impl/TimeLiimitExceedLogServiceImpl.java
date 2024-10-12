package ru.t1.java.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.t1.java.demo.model.TimeLiimitExceedLog;
import ru.t1.java.demo.repository.TimeLiimitExceedLogRepository;
import ru.t1.java.demo.service.TimeLiimitExceedLogService;
@Service
@RequiredArgsConstructor
public class TimeLiimitExceedLogServiceImpl implements TimeLiimitExceedLogService {

    @Autowired
    private TimeLiimitExceedLogRepository repository;
    @Override
    public void save(TimeLiimitExceedLog exceedLog) {
        repository.save(exceedLog);
    }
}