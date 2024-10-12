package ru.t1.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSourceErrorLogRepository extends JpaRepository<ru.t1.java.demo.model.DataSourceErrorLog, Long> {

}
