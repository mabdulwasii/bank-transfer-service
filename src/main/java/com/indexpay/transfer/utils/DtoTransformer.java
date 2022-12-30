package com.indexpay.transfer.utils;

import com.indexpay.transfer.service.dto.BankDto;
import com.indexpay.transfer.service.dto.PaystackBank;

import java.util.List;

public class DtoTransformer {

    private DtoTransformer(){}

    public static List<BankDto> transformToBankDto(List<PaystackBank> payStackBanks) {
        return payStackBanks.stream().map(paystackBank -> new BankDto(paystackBank.getCode(), paystackBank.getName(), paystackBank.getLongcode())).toList();
    }
}
