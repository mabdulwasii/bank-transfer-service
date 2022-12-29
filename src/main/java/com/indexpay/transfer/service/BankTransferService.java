package com.indexpay.transfer.service;

import com.indexpay.transfer.entity.Bank;
import com.indexpay.transfer.entity.TransactionLog;
import com.indexpay.transfer.entity.enumeration.Provider;
import com.indexpay.transfer.exception.InvalidTransactionReference;
import com.indexpay.transfer.repository.BankRepository;
import com.indexpay.transfer.repository.TransactionLogRepository;
import com.indexpay.transfer.service.dto.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BankTransferService {
    private final BankRepository bankRepository;
    private final TransactionLogRepository transactionLogRepository;

    private final TransferProducerService producer;
    public List<Bank> getBanks(String provider) {
        provider = Provider.ensureProviderIsValid(provider);
        return bankRepository.findBankByProvider(provider);
    }
    public ValidateAccountResponse validateBankAccount(ValidateAccountRequest request) {
        String provider = Provider.ensureProviderIsValid(request.getProvider());
       request.setProvider(provider);
       log.info("Inside validate bank service");
        //Todo make a call to provider to validate account
        return new ValidateAccountResponse("1234567891", "Adewale Johnson", "025", "Ecobank");
    }
    public BankTransferResponse transFerFund(BankTransferRequest request) {
        String provider = Provider.ensureProviderIsValid(request.getProvider());
        request.setProvider(provider);
        //Todo make a call to provider for fund transfer
        producer.send(request);
        return new BankTransferResponse();
    }
    public GetTransactionStatusResponse getTransactionStatus(String reference) {
        String provider = getTransactionProvider(reference);
        //Todo make a call to provider get transaction status
        GetTransactionStatusResponse response = new GetTransactionStatusResponse();
        return response;
    }
    private String getTransactionProvider(String reference) {
        TransactionLog transactionLog = transactionLogRepository.findByTransactionReference(reference);
        if (transactionLog == null) {
            throw new InvalidTransactionReference("Invalid transaction reference");
        }
        return transactionLog.getProvider();

    }
}