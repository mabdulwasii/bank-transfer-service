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
@Table(name = "transaction", indexes = {
        @Index(name = "idx_transaction_reference", columnList = "transaction-reference")
})
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "currency-code", nullable = false)
    private String currencyCode = "NGN";
    @Column(name = "narration", nullable = false)
    private String narration;
    @Column(name = "beneficiary-account-no", nullable = false)
    private String beneficiaryAccountNumber;
    @Column(name = "beneficiary-account-no", nullable = false)
    private String beneficiaryAccountName;
    @Column(name = "beneficiary-bank-code", nullable = false)
    private String beneficiaryBankCode;
    @Column(name = "transaction-reference", nullable = false)
    private String transactionReference;
    @Column(name = "max-retry", nullable = false)
    private int maxRetryAttempt;
    @Column(name = "callback-url", nullable = false)
    private String callBackUrl;
    @Column(name = "provider", nullable = false)
    private String provider;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "sessionId")
    private String sessionId;
    @Column(name = "transaction-time", nullable = false)
    private LocalDateTime transactionDateTime;
}
