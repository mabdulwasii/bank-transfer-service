package com.indexpay.transfer.service.dto;

import lombok.Builder;

@Builder
public class BankDto {
    private String code;
    private String bankName;
    private String longCode;
}
