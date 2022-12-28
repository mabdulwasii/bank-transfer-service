package com.indexpay.transfer.repository;

import com.indexpay.transfer.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {}