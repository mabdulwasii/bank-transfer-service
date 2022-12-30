package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
@Data
@ToString
public class FlutterTransferResponse extends FlutterApiResponse{
    @JsonProperty("data")
    private FlutterTransferResponseData data;
    @JsonProperty("data")
    public FlutterTransferResponseData getData() {
        return data;
    }
    @JsonProperty("data")
    public void setData(FlutterTransferResponseData data) {
        this.data = data;
    }
}
