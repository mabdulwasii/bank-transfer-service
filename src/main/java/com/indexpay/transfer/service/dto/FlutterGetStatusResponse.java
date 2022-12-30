package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
@ToString
public class FlutterGetStatusResponse extends FlutterApiResponse{
    @JsonProperty("data")
    private FlutterwGetStatusData data = null;
    @JsonProperty("data")
    public FlutterwGetStatusData getData() {
        return data;
    }
    @JsonProperty("data")
    public void setData(FlutterwGetStatusData data) {
        this.data = data;
    }
}
