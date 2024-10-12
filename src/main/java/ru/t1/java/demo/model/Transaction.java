package ru.t1.java.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;


@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction extends AbstractPersistable<Long> {

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "client_id")
    private Long clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;


    public void setAccount(Account account) {
        this.account = account;
        account.addTransaction(this);
    }
}
