package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "message"
})
public class PaystackApiResponse {
    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    public Boolean getStatus() {
        return status;
    }
    @JsonProperty("status")
    public void setStatus(Boolean status) {
        this.status = status;
    }
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }
}
