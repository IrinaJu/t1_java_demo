package ru.t1.java.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractPersistable<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", length = 10)
    private AccountType accountType;

    @Column(name = "balance", precision = 19, scale = 2)
    private BigDecimal balance;

    public void setClient(Client client) {
        this.client = client;
        client.getAccounts().add(this);

    }

    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions = new HashSet<>();


    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
    }

    public BigDecimal getBalance() {
        BigDecimal balance = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            balance = balance.add(transaction.getAmount());
        }
        return balance;
    }


    public void setOwner(String owner) {
    }
}

