package com.indexpay.transfer.service;

import com.indexpay.transfer.client.flutterwave.FlutterWaveClient;
import com.indexpay.transfer.client.paystack.PaystackApiClient;
import com.indexpay.transfer.entity.TransactionLog;
import com.indexpay.transfer.entity.enumeration.Provider;
import com.indexpay.transfer.service.dto.BankTransferRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TransferConsumerService {

    private final PaystackApiClient paystackClient;
    private final FlutterWaveClient flutterWaveClient;

    @KafkaListener(topics = "${spring.kafka.transferTopic}", groupId = "${spring.kafka.groupId}",
            containerFactory = "transferListenerContainerFactory")
    public void receive(BankTransferRequest request) {
        log.info("Transfer consumer receives {} ", request);
        TransactionLog transactionLog = paystackClient.persistTransactionLog(request);
        log.info("Saved transactionLog from request {} ", transactionLog);
        if (Provider.PAYSTACK.equals(Provider.valueOf(request.getProvider()))) {
            paystackClient.fundTransfer(transactionLog);
        }else {
            flutterWaveClient.fundTransfer(transactionLog);
        }
    }
}
