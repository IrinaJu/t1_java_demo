package ru.t1.java.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
@Entity
@Table(name = "data_source_error_log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class DataSourceErrorLog  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String stackTrace;

    @Column(name = "message")
    private String message;

    @Column(name = "method_signature")
    private String methodSignature;

    @Column(name = "error_time")
    private LocalDateTime errorTime;

}
