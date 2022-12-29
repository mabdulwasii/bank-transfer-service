package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
public class PaystackGetBanksResponse extends PaystackApiResponse {

    @JsonProperty("data")
    private List<PaystackBank> data = null;



    @JsonProperty("data")
    public List<PaystackBank> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<PaystackBank> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PaystackGetBanksResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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