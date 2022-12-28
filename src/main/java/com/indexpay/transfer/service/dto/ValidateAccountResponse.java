package com.indexpay.transfer.service.dto;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class ValidateAccountResponse {
    private String accountNumber;
    private String accountName;
    private String bankCode;
    private String bankName;
}
