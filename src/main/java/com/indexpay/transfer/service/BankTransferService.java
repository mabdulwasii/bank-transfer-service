package com.indexpay.transfer.service;

import com.indexpay.transfer.client.paystack.PaystackApiClient;
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
    public BankTransferResponse transFerFund(BankTransferRequest request) {
        Provider provider = Provider.ensureProviderIsValid(request.getProvider());
        request.setProvider(provider.name().toLowerCase());
        //Todo make a call to provider for fund transfer
        producer.send(request);
        return new BankTransferResponse();
    }
    public GetTransactionStatusResponse getTransactionStatus(String reference) {
        String providerString = getTransactionProvider(reference);
        Provider provider = Provider.valueOf(providerString);
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