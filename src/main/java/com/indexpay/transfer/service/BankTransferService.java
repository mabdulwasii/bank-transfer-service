package com.indexpay.transfer.service;

import com.indexpay.transfer.entity.Bank;
import com.indexpay.transfer.entity.TransactionLog;
import com.indexpay.transfer.entity.enumeration.Provider;
import com.indexpay.transfer.exception.InvalidTransactionReference;
import com.indexpay.transfer.repository.BankRepository;
import com.indexpay.transfer.repository.TransactionLogRepository;
import com.indexpay.transfer.service.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankTransferService {
    private final BankRepository bankRepository;
    private final TransactionLogRepository transactionLogRepository;
    public ApiResponse getBanks(String provider) {
        provider = Provider.ensureProviderIsValid(provider);
        List<Bank> bankByProvider = bankRepository.findBankByProvider(provider);
        return ApiResponse.builder().code("00").description("bank retrieved successfully")
                .data(bankByProvider).build();
    }
    public ApiResponse validateBankAccount(ValidateAccountRequest request) {
        String provider = Provider.ensureProviderIsValid(request.getProvider());
       request.setProvider(provider);
        //Todo make a call to provider to validate account
        ValidateAccountResponse response = new ValidateAccountResponse("1111111111", "Adewale Johnson", "025", "Ecobank");

        return ApiResponse.successResponse(response);
    }
    public ApiResponse transFerFund(BankTransferRequest request) {
        String provider = Provider.ensureProviderIsValid(request.getProvider());
        request.setProvider(provider);
        //Todo make a call to provider for fund transfer
        BankTransferResponse response = new BankTransferResponse();
        return ApiResponse.successResponse(response);
    }
    public ApiResponse getTransactionStatus(String reference) {
        String provider = getTransactionProvider(reference);
        //Todo make a call to provider get transaction status
        GetTransactionStatusResponse response = new GetTransactionStatusResponse();
        return ApiResponse.successResponse(response);
    }
    private String getTransactionProvider(String reference) {
        TransactionLog transactionLog = transactionLogRepository.findByTransactionReference(reference);
        if (transactionLog == null) {
            throw new InvalidTransactionReference("Invalid transaction reference");
        }
        return transactionLog.getProvider();

    }
}