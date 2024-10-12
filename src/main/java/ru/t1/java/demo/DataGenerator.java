package ru.t1.java.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.model.*;
import ru.t1.java.demo.repository.ClientRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Component
public class DataGenerator {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;




    public Account generateAccount(String owner, BigDecimal balance) {
        Account account = new Account();
        account.setOwner(owner);
        account.setBalance(balance);
        return account;
    }

    public Client generateClient(String name) {
        Client client = new Client();
        client.setName(name);
        return clientRepository.save(client);
    }

    public Transaction generateTransaction(Account account, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setAccount(account);
        return transaction;
    }

    public DataSourceErrorLog generateDataSourceErrorLog() {
        DataSourceErrorLog errorLog = new DataSourceErrorLog();
        errorLog.setMethodSignature("com.example.SomeService.methodWithError");
        errorLog.setMessage("Database connection error");
        errorLog.setStackTrace("Stack trace of the error");
        errorLog.setErrorTime(LocalDateTime.now());
        return errorLog;
    }

    public TimeLiimitExceedLog generateTimeLimitExceedLog(String methodSignature, BigDecimal executionTime) {
        TimeLiimitExceedLog exceedLog = new TimeLiimitExceedLog();
        exceedLog.setMethodSignature(methodSignature);
        exceedLog.setExecutionTime(executionTime);
        exceedLog.setLogTime(LocalDateTime.now());
        return exceedLog;
    }

    public void generateData(int countAccounts, int countTransactions, int countErrorLogs, int countExceedLogs) {
        IntStream.range(0, countAccounts)
                .forEach(i -> {
                    Client client = generateClient("Client " + i);
                    Account account = generateAccount("Owner " + i, new BigDecimal("1000.00"));
                    account.setClient(client);
                    entityManager.persist(client);
                    entityManager.persist(account);
                });

        IntStream.range(0, countTransactions)
                .forEach(i -> {
                    Account account = entityManager.find(Account.class, i + 1);
                    Transaction transaction = generateTransaction(account, new BigDecimal("150.00"));
                    account.getTransactions().add(transaction);
                    entityManager.persist(transaction);
                });

        IntStream.range(0, countErrorLogs)
                .forEach(i -> entityManager.persist(generateDataSourceErrorLog()));

        IntStream.range(0, countExceedLogs)
                .forEach(i -> entityManager.persist(generateTimeLimitExceedLog("com.example.LongRunningMethod", new BigDecimal("0.32"))));
    }
}




