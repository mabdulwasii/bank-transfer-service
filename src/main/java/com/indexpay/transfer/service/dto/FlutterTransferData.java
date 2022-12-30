package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "account_bank",
        "account_number",
        "amount",
        "narration",
        "currency",
        "reference",
        "callback_url",
        "debit_currency"
})
@ToString
@Builder
public class FlutterTransferData {

    @JsonProperty("account_bank")
    private String accountBank;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("narration")
    private String narration;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("callback_url")
    private String callbackUrl;
    @JsonProperty("debit_currency")
    private String debitCurrency;

    @JsonProperty("account_bank")
    public String getAccountBank() {
        return accountBank;
    }

    @JsonProperty("account_bank")
    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    @JsonProperty("account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("account_number")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("amount")
    public BigDecimal getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @JsonProperty("narration")
    public String getNarration() {
        return narration;
    }

    @JsonProperty("narration")
    public void setNarration(String narration) {
        this.narration = narration;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    @JsonProperty("callback_url")
    public String getCallbackUrl() {
        return callbackUrl;
    }

    @JsonProperty("callback_url")
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    @JsonProperty("debit_currency")
    public String getDebitCurrency() {
        return debitCurrency;
    }

    @JsonProperty("debit_currency")
    public void setDebitCurrency(String debitCurrency) {
        this.debitCurrency = debitCurrency;
    }
}
