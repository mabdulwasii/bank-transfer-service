package com.indexpay.transfer.service;

import com.indexpay.transfer.client.flutterwave.FlutterWaveClient;
import com.indexpay.transfer.client.paystack.PaystackApiClient;
import com.indexpay.transfer.config.KafkaConfigProperties;
import com.indexpay.transfer.entity.TransactionLog;
import com.indexpay.transfer.entity.enumeration.Provider;
import com.indexpay.transfer.exception.GenericException;
import com.indexpay.transfer.exception.NonUniqueReferenceException;
import com.indexpay.transfer.service.dto.*;
import com.indexpay.transfer.utils.DtoTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BankTransferService {
    private final TransactionLogService transactionLogService;

    private final PaystackApiClient paystackClient;
    private final FlutterWaveClient flutterWaveClient;
    private final KafkaConfigProperties properties;

    private final TransferProducerService producer;

    public List<BankDto> getBanks(String provider) {
        Provider providerEnum = Provider.ensureProviderIsValid(provider);
        if (Provider.PAYSTACK.equals(providerEnum)) {
            return paystackClient.getBanks();
        }else {
            return flutterWaveClient.getBanks();
        }
    }

    public ValidateAccountResponse validateBankAccount(ValidateAccountRequest request) {
        Provider provider = Provider.ensureProviderIsValid(request.getProvider());
        if (Provider.PAYSTACK.equals(provider)) {
            return paystackClient.validateBankAccount(request);
        }
        return new ValidateAccountResponse("1234567891", "Adewale Johnson", "025", "Ecobank");
    }

    @Transactional
    public BankTransferResponse transFerFund(BankTransferRequest request) {
        Optional<TransactionLog> logOptional = transactionLogService.findByTransactionReference(request.getTransactionReference());
        if (logOptional.isPresent()) {
            throw new NonUniqueReferenceException("Transaction reference not unique");
        }
        Provider provider = Provider.ensureProviderIsValid(request.getProvider());
        request.setProvider(provider.name());
        if (request.getMaxRetryAttempt() > 0) {
            properties.setMaxAttempts(request.getMaxRetryAttempt());
        }
        producer.send(request);
        return DtoTransformer.transformToBankTransferResponse(request);
    }

    public GetTransactionStatusResponse getTransactionStatus(String reference) {
        TransactionLog transactionLog = getTransactionLog(reference);
        String providerString = transactionLog.getProvider();
        Provider provider = Provider.valueOf(providerString);
        if (Provider.PAYSTACK.equals(provider)) {
            return paystackClient.getTransactionStatus(transactionLog.getExternalReference(), transactionLog);
        }
        return new GetTransactionStatusResponse();
    }

    private TransactionLog getTransactionLog(String reference) {
        Optional<TransactionLog> transactionLogOptional = transactionLogService.findByTransactionReference(reference);
        return transactionLogOptional.orElseThrow(() -> {
            throw new GenericException("Invalid reference number : " + reference);
        });
    }
}