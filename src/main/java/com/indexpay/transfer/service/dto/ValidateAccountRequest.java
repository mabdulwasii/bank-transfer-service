package com.indexpay.transfer.service.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ValidateAccountRequest {
    @NotEmpty(message = "Account number is mandatory")
    @Size(min = 10, max = 10, message = "Account number must be of length 2")
    private String accountNumber;
    @NotEmpty(message = "bank code is mandatory")
    private String bankCode;

    private String provider;
}
