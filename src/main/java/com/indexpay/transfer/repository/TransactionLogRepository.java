package com.indexpay.transfer.repository;

import com.indexpay.transfer.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {
    Optional<TransactionLog> findByTransactionReference(String reference);
}