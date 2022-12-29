package com.indexpay.transfer.service.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BankTransferRequest implements Serializable {
    @Min(value = 1, message = "Amount must be greater than 0")
    private BigDecimal amount;
    @NotEmpty(message = "Beneficiary account number is mandatory")
    @Size(min = 10, max = 10, message = "Invalid beneficiary account number")
    private String beneficiaryAccountNumber;
    @NotEmpty(message = "Beneficiary account name is mandatory")
    private String beneficiaryAccountName;
    @NotEmpty(message = "Beneficiary bank code is mandatory")
    private String beneficiaryBankCode;
    @NotEmpty(message = "Transaction reference is mandatory")
    private String transactionReference;
    private String currencyCode = "NGN";
    @NotEmpty(message = "Narration is mandatory")
    private String narration;
    private int maxRetryAttempt;
    private String callBackUrl;
    private String provider;
}
