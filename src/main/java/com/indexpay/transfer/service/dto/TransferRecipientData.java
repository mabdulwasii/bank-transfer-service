package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "active",
        "createdAt",
        "currency",
        "domain",
        "id",
        "integration",
        "name",
        "recipient_code",
        "type",
        "updatedAt",
        "is_deleted",
        "details"
})
public class TransferRecipientData {

    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("integration")
    private Integer integration;
    @JsonProperty("name")
    private String name;
    @JsonProperty("recipient_code")
    private String recipientCode;
    @JsonProperty("type")
    private String type;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
    @JsonProperty("details")
    private Details details;

    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
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

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("integration")
    public Integer getIntegration() {
        return integration;
    }

    @JsonProperty("integration")
    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("recipient_code")
    public String getRecipientCode() {
        return recipientCode;
    }

    @JsonProperty("recipient_code")
    public void setRecipientCode(String recipientCode) {
        this.recipientCode = recipientCode;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("is_deleted")
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    @JsonProperty("is_deleted")
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @JsonProperty("details")
    public Details getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(Details details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "TransferRecipientResponse{" +
                "active=" + active +
                ", createdAt='" + createdAt + '\'' +
                ", currency='" + currency + '\'' +
                ", domain='" + domain + '\'' +
                ", id=" + id +
                ", integration=" + integration +
                ", name='" + name + '\'' +
                ", recipientCode='" + recipientCode + '\'' +
                ", type='" + type + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", isDeleted=" + isDeleted +
                ", details=" + details +
                '}';
    }
}
