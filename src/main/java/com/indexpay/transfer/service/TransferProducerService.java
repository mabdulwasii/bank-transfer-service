package com.indexpay.transfer.service;

import com.indexpay.transfer.service.dto.BankTransferRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransferProducerService {
    @Value("${spring.kafka.transfer-topic}")
    String transferTopic;

    private final KafkaTemplate<String, BankTransferRequest> transferTemplate;

    public TransferProducerService(KafkaTemplate<String, BankTransferRequest> transferTemplate) {
        this.transferTemplate = transferTemplate;
    }

    public void send(BankTransferRequest request) {
        log.info("Bank transfer request {} ", request);
        this.transferTemplate.send(transferTopic, request);
    }
}
