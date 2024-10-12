package ru.t1.java.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "time_limit_exceed_log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TimeLiimitExceedLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method_signature")
    private String methodSignature;

    @Column(name = "execution_time", precision = 10, scale = 6)
    private BigDecimal executionTime;

    @Column(name = "log_time")
    private LocalDateTime logTime;

}
