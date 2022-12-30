package com.indexpay.transfer.entity;

import com.indexpay.transfer.entity.enumeration.Provider;
import com.indexpay.transfer.entity.enumeration.Status;
import com.indexpay.transfer.utils.annotation.ValidEnumValue;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

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
    @Min(value = 1, message = "Amount must be greater than 0")
    private BigDecimal amount;
    @Column(name = "currency_code", nullable = false)
    private final String currencyCode = "NGN";
    @Column(name = "narration", nullable = false)
    @NotEmpty(message = "Narration is mandatory")
    private String narration;
    @Column(name = "beneficiary_account_no", nullable = false)
    @NotEmpty(message = "Beneficiary account number is mandatory")
    @Size(min = 10, max = 10, message = "Invalid beneficiary account number")
    private String beneficiaryAccountNumber;
    @Column(name = "beneficiary_account_name", nullable = false)
    @NotEmpty(message = "Beneficiary account name is mandatory")
    private String beneficiaryAccountName;
    @Column(name = "beneficiary_bank_code", nullable = false)
    @NotEmpty(message = "Beneficiary bank code is mandatory")
    private String beneficiaryBankCode;
    @Column(name = "transaction_reference", nullable = false, unique = true)
    @NotEmpty(message = "Transaction reference is mandatory")
    private String transactionReference;
    @Column(name = "external_reference")
    private String externalReference;
    @Column(name = "max_retry", nullable = false)
    private int maxRetryAttempt;
    @Column(name = "callback_url")
    private String callBackUrl;
    @Column(name = "provider", nullable = false)
    @ValidEnumValue(enumClass = Provider.class)
    private String provider;
    @Column(name = "status", nullable = false)
    @ValidEnumValue(enumClass = Status.class)
    private String status;
    @Column(name = "sessionId")
    private String sessionId;
    @Column(name = "transaction_time", nullable = false)
    @NotNull(message = "Transaction date cannot be empty")
    private String transactionDateTime;
    @Column(name = "recipient_code")
    private String recipientCode;
}