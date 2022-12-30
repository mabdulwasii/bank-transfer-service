package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
@ToString
public class FlutterGetBankApiResponse extends FlutterApiResponse{
    @JsonProperty("data")
    private List<FlutterGetBankData> data = null;
    @JsonProperty("data")
    public List<FlutterGetBankData> getData() {
        return data;
    }
    @JsonProperty("data")
    public void setData(List<FlutterGetBankData> data) {
        this.data = data;
    }
}
