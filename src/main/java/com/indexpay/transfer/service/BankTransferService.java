package com.indexpay.transfer.service;

import com.indexpay.transfer.entity.Bank;
import com.indexpay.transfer.entity.enumeration.Provider;
import com.indexpay.transfer.repository.BankRepository;
import com.indexpay.transfer.service.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankTransferService {
    private final BankRepository bankRepository;
    public ApiResponse getBanks(String provider) {
        provider = Provider.ensureProviderIsValid(provider);
        List<Bank> bankByProvider = bankRepository.findBankByProvider(provider);
        return ApiResponse.builder().code("00").description("bank retrieved successfully")
                .data(bankByProvider).build();
    }

    public ApiResponse validateBankAccount(ValidateAccountRequest request) {
        String provider = Provider.ensureProviderIsValid(request.getProvider());
       request.setProvider(provider);

        ValidateAccountResponse response = new ValidateAccountResponse("1111111111", "Adewale Johnson", "025", "Ecobank");

        return ApiResponse.successResponse(response);
    }

    public ApiResponse transFerFund(BankTransferRequest request) {
        String provider = Provider.ensureProviderIsValid(request.getProvider());
        request.setProvider(provider);

        BankTransferResponse response = new BankTransferResponse();

        return ApiResponse.successResponse(null);
    }
}
