package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "account_number",
        "bank_code",
        "full_name",
        "created_at",
        "currency",
        "debit_currency",
        "amount",
        "fee",
        "status",
        "reference",
        "meta",
        "narration",
        "complete_message",
        "requires_approval",
        "is_approved",
        "bank_name"
})
@ToString
public class FlutterTransferResponseData {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("bank_code")
    private String bankCode;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("debit_currency")
    private String debitCurrency;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("fee")
    private Integer fee;
    @JsonProperty("status")
    private String status;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("meta")
    private Object meta;
    @JsonProperty("narration")
    private String narration;
    @JsonProperty("complete_message")
    private String completeMessage;
    @JsonProperty("requires_approval")
    private Integer requiresApproval;
    @JsonProperty("is_approved")
    private Integer isApproved;
    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("account_number")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("bank_code")
    public String getBankCode() {
        return bankCode;
    }

    @JsonProperty("bank_code")
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @JsonProperty("full_name")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("full_name")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("debit_currency")
    public String getDebitCurrency() {
        return debitCurrency;
    }

    @JsonProperty("debit_currency")
    public void setDebitCurrency(String debitCurrency) {
        this.debitCurrency = debitCurrency;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("fee")
    public Integer getFee() {
        return fee;
    }

    @JsonProperty("fee")
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    @JsonProperty("meta")
    public Object getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Object meta) {
        this.meta = meta;
    }

    @JsonProperty("narration")
    public String getNarration() {
        return narration;
    }

    @JsonProperty("narration")
    public void setNarration(String narration) {
        this.narration = narration;
    }@JsonProperty("complete_message")
    public String getCompleteMessage() {
        return completeMessage;
    }

    @JsonProperty("complete_message")
    public void setCompleteMessage(String completeMessage) {
        this.completeMessage = completeMessage;
    }

    @JsonProperty("requires_approval")
    public Integer getRequiresApproval() {
        return requiresApproval;
    }

    @JsonProperty("requires_approval")
    public void setRequiresApproval(Integer requiresApproval) {
        this.requiresApproval = requiresApproval;
    }

    @JsonProperty("is_approved")
    public Integer getIsApproved() {
        return isApproved;
    }

    @JsonProperty("is_approved")
    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    @JsonProperty("bank_name")
    public String getBankName() {
        return bankName;
    }

    @JsonProperty("bank_name")
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}