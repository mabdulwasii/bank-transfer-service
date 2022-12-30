package com.indexpay.transfer.service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class BankTransferResponse {
    private BigDecimal amount;
   private String beneficiaryAccountNumber;
    private String beneficiaryAccountName;
    private String beneficiaryBankCode;
    private String transactionDateTime;
    private String transactionReference;
    private String currencyCode = "NGN";
    private String responseMessage;
    private String responseCode;
    private String sessionId;
    private String status;
}
