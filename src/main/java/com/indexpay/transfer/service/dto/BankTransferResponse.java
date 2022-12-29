package com.indexpay.transfer.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class BankTransferResponse {
    private BigDecimal amount;
   private String beneficiaryAccountNumber;
    private String beneficiaryAccountName;
    private String beneficiaryBankCode;
    private LocalDateTime transactionDateTime;
    private String transactionReference;
    private String currencyCode = "NGN";
    private String responseMessage;
    private String responseCode;
    private String sessionId;
    private String status;
}
