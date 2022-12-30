package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
public class FlutterTransferResponse extends FlutterApiResponse{
    @JsonProperty("data")
    private FlutterTransferResponseData data = null;
    @JsonProperty("data")
    public FlutterTransferResponseData getData() {
        return data;
    }
    @JsonProperty("data")
    public void setData(FlutterTransferResponseData data) {
        this.data = data;
    }
}
