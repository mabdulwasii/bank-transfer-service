package com.indexpay.transfer.utils;

import com.indexpay.transfer.service.dto.BankDto;
import com.indexpay.transfer.service.dto.PaystackBank;

import java.util.List;
import java.util.stream.Collectors;

public class DtoTransformer {

    public static List<BankDto> transformToBankDto(List<PaystackBank> payStackBanks) {
        return payStackBanks.stream().map(paystackBank -> {
            return BankDto.builder().bankName(paystackBank.getName()).code(paystackBank.getCode())
                    .longCode(paystackBank.getLongcode()).build();
        }).collect(Collectors.toList());
    }
}
