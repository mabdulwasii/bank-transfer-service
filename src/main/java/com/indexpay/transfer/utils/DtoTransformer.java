package com.indexpay.transfer.utils;

import com.indexpay.transfer.service.dto.*;

import java.util.List;

public class DtoTransformer {

    private DtoTransformer(){}

    public static List<BankDto> transformToBankDto(List<PaystackBank> payStackBanks) {
        return payStackBanks.stream().map(paystackBank -> new BankDto(paystackBank.getCode(), paystackBank.getName(), paystackBank.getLongcode())).toList();
    }
    public static ValidateAccountResponse transformToValidateResponse(PaystackValidateData data, ValidateAccountRequest request) {
        return new ValidateAccountResponse(data.getAccountNumber(), data.getAccountName(), request.getBankCode(), String.valueOf(data.getBankId()));
    }
}
