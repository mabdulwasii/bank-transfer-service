package com.indexpay.transfer.service;

import com.indexpay.transfer.client.paystack.PaystackApiClient;
import com.indexpay.transfer.entity.TransactionLog;
import com.indexpay.transfer.entity.enumeration.Provider;
import com.indexpay.transfer.exception.GenericException;
import com.indexpay.transfer.exception.NonUniqueReferenceException;
import com.indexpay.transfer.repository.TransactionLogRepository;
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
    private final TransactionLogRepository transactionLogRepository;

    private final PaystackApiClient paystackClient;

    private final TransferProducerService producer;
    public List<BankDto> getBanks(String provider) {
        Provider providerEnum = Provider.ensureProviderIsValid(provider);
        if (Provider.PAYSTACK.equals(providerEnum)) {
            return paystackClient.getBanks();
        }
        return null;
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
        Optional<TransactionLog> logOptional = transactionLogRepository.findByTransactionReference(request.getTransactionReference());
        if (logOptional.isPresent()) {
            throw new NonUniqueReferenceException("Transaction reference not unique");
        }
        Provider provider = Provider.ensureProviderIsValid(request.getProvider());
        TransactionLog savedTransactionLog = persistTransactionLog(request);
        if (Provider.PAYSTACK.equals(provider)) {
            return paystackClient.transFerFund(request, savedTransactionLog);
        }
        producer.send(request);
        return BankTransferResponse.builder().build();
    }

    public GetTransactionStatusResponse getTransactionStatus(String reference) {
        TransactionLog transactionLog = getTransactionLog(reference);
        String providerString = transactionLog.getProvider();
        Provider provider  = Provider.valueOf(providerString);
        if (Provider.PAYSTACK.equals(provider)) {
            return paystackClient.getTransactionStatus(transactionLog.getExternalReference(), transactionLog);
        }
        return new GetTransactionStatusResponse();
    }
    private TransactionLog getTransactionLog(String reference) {
        Optional<TransactionLog> transactionLogOptional = transactionLogRepository.findByTransactionReference(reference);
        return transactionLogOptional.orElseThrow(() -> {
            throw new GenericException("Invalid reference number : " + reference);
        });
    }
    private TransactionLog persistTransactionLog(BankTransferRequest request) {
        TransactionLog transactionLog = DtoTransformer.transformToTransactionLog(request);
        return transactionLogRepository.save(transactionLog);
    }
}