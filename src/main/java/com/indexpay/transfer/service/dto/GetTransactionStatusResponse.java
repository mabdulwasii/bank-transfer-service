package com.indexpay.transfer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionStatusResponse {
    @NotEmpty(message = "Amount is mandatory")
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
    @NotNull(message = "Transaction date cannot be empty")
    private String transactionDateTime;
    private String currencyCode = "NGN";
    @NotEmpty(message = "Response message is mandatory")
    private String responseMessage;
    @NotEmpty(message = "Response code is mandatory")
    private String responseCode;
    @NotEmpty(message = "Session Id is mandatory")
    private String sessionId;
    @NotEmpty(message = "Status is mandatory")
    private String status;
}