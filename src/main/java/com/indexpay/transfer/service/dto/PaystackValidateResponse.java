package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
public class PaystackValidateResponse extends PaystackApiResponse {
    @JsonProperty("data")
    private PaystackValidateData data = null;
    @JsonProperty("data")
    public PaystackValidateData getData() {
        return data;
    }
    @JsonProperty("data")
    public void setData(PaystackValidateData data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "PaystackValidateResponse{" +
                "status='" + getStatus() + '\'' +
                "message='" + getMessage() + '\'' +
                "data=" + data +
                '}';
    }
}