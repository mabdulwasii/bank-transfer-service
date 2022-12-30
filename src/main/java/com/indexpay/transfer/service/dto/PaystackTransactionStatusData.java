package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "recipient",
        "domain",
        "amount",
        "currency",
        "source",
        "source_details",
        "reason",
        "status",
        "failures",
        "transfer_code",
        "id",
        "createdAt",
        "updatedAt"
})
@Generated("jsonschema2pojo")
public class PaystackTransactionStatusData {

    @JsonProperty("recipient")
    private Object recipient;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("source")
    private String source;
    @JsonProperty("source_details")
    private Object sourceDetails;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("status")
    private String status;
    @JsonProperty("failures")
    private Object failures;
    @JsonProperty("transfer_code")
    private String transferCode;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;

    @JsonProperty("recipient")
    public Object getRecipient() {
        return recipient;
    }

    @JsonProperty("recipient")
    public void setRecipient(Object recipient) {
        this.recipient = recipient;
    }

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("source_details")
    public Object getSourceDetails() {
        return sourceDetails;
    }

    @JsonProperty("source_details")
    public void setSourceDetails(Object sourceDetails) {
        this.sourceDetails = sourceDetails;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("failures")
    public Object getFailures() {
        return failures;
    }

    @JsonProperty("failures")
    public void setFailures(Object failures) {
        this.failures = failures;
    }

    @JsonProperty("transfer_code")
    public String getTransferCode() {
        return transferCode;
    }

    @JsonProperty("transfer_code")
    public void setTransferCode(String transferCode) {
        this.transferCode = transferCode;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PaystackTransactionStatusData{" +
                "recipient=" + recipient +
                ", domain='" + domain + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", source='" + source + '\'' +
                ", sourceDetails=" + sourceDetails +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", failures=" + failures +
                ", transferCode='" + transferCode + '\'' +
                ", id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
