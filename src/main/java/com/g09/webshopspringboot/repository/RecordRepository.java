package com.g09.webshopspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.g09.webshopspringboot.domain.Record;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
