package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "reference",
        "integration",
        "domain",
        "amount",
        "currency",
        "source",
        "reason",
        "recipient",
        "status",
        "transfer_code",
        "id",
        "createdAt",
        "updatedAt"
})
public class InitiateTransferData {

    @JsonProperty("reference")
    private String reference;
    @JsonProperty("integration")
    private Integer integration;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("source")
    private String source;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("recipient")
    private Integer recipient;
    @JsonProperty("status")
    private String status;
    @JsonProperty("transfer_code")
    private String transferCode;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    @JsonProperty("integration")
    public Integer getIntegration() {
        return integration;
    }

    @JsonProperty("integration")
    public void setIntegration(Integer integration) {
        this.integration = integration;
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

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("recipient")
    public Integer getRecipient() {
        return recipient;
    }

    @JsonProperty("recipient")
    public void setRecipient(Integer recipient) {
        this.recipient = recipient;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
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
        return "InitiateTransferResponse{" +
                "reference='" + reference + '\'' +
                ", integration=" + integration +
                ", domain='" + domain + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", source='" + source + '\'' +
                ", reason='" + reason + '\'' +
                ", recipient=" + recipient +
                ", status='" + status + '\'' +
                ", transferCode='" + transferCode + '\'' +
                ", id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
