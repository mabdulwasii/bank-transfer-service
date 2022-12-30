package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
public class PaystackTransactionStatusResponse extends PaystackApiResponse {
    @JsonProperty("data")
    private PaystackTransactionStatusData data = null;
    @JsonProperty("data")
    public PaystackTransactionStatusData getData() {
        return data;
    }
    @JsonProperty("data")
    public void setData(PaystackTransactionStatusData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PaystackTransactionStatusResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((getStatus() == null)?"<null>":getStatus()));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((getMessage() == null)?"<null>":getMessage()));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}