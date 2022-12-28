package com.indexpay.transfer.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "transaction_log", indexes = {
        @Index(name = "idx_transaction_reference", columnList = "transaction_reference")
})
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "currency_code", nullable = false)
    private String currencyCode = "NGN";
    @Column(name = "narration", nullable = false)
    private String narration;
    @Column(name = "beneficiary_account_no", nullable = false)
    private String beneficiaryAccountNumber;
    @Column(name = "beneficiary_account_name", nullable = false)
    private String beneficiaryAccountName;
    @Column(name = "beneficiary_bank_code", nullable = false)
    private String beneficiaryBankCode;
    @Column(name = "transaction_reference", nullable = false)
    private String transactionReference;
    @Column(name = "max_retry", nullable = false)
    private int maxRetryAttempt;
    @Column(name = "callback_url", nullable = false)
    private String callBackUrl;
    @Column(name = "provider", nullable = false)
    private String provider;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "sessionId")
    private String sessionId;
    @Column(name = "transaction_time", nullable = false)
    private LocalDateTime transactionDateTime;
}
