package com.indexpay.transfer.service;

import com.indexpay.transfer.entity.TransactionLog;
import com.indexpay.transfer.repository.TransactionLogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionLogService {
    private final TransactionLogRepository repository;

    public TransactionLog save(TransactionLog transactionLog){
        log.info("request to save transactionLog {}", transactionLog);
        TransactionLog savedTransactionLog = repository.save(transactionLog);
        log.info("Saved transaction log {}", savedTransactionLog);
        return savedTransactionLog;
    }

    public Optional<TransactionLog> findByTransactionReference(String reference) {
        log.info("request to find by reference {}", reference);
        Optional<TransactionLog> savedTransactionLog = repository.findByTransactionReference(reference);
        log.info("Retrieved transaction log optional {}", savedTransactionLog);
        return savedTransactionLog;
    }
}
