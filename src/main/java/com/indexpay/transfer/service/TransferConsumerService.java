package com.indexpay.transfer.service;

import com.indexpay.transfer.service.dto.BankTransferRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransferConsumerService {
@KafkaListener(topics = "${spring.kafka.transfer-topic}",
        containerFactory = "transferListenerContainerFactory")
    public void receive(BankTransferRequest request) {
        log.info("Transfer consumer receives {} ", request);
       //todo process consumer tasks
    }
}
