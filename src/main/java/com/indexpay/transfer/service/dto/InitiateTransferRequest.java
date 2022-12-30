package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "source",
        "amount",
        "reference",
        "recipient",
        "reason"
})
@AllArgsConstructor
public class InitiateTransferRequest {

    @JsonProperty("source")
    private String source;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("recipient")
    private String recipient;
    @JsonProperty("reason")
    private String reason;

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    @JsonProperty("recipient")
    public String getRecipient() {
        return recipient;
    }

    @JsonProperty("recipient")
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "InitiateTransferRequest{" +
                "source='" + source + '\'' +
                ", amount='" + amount + '\'' +
                ", reference='" + reference + '\'' +
                ", recipient='" + recipient + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}