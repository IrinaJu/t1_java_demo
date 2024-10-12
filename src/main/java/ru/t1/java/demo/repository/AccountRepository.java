package ru.t1.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.model.AccountType;



import java.util.Optional;
@Repository
public interface AccountRepository  extends JpaRepository<Account, Long> {
        Optional<Account> findByName(AccountType name);
    }

