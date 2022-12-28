package com.indexpay.transfer.service.dto;

import com.indexpay.transfer.service.constant.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ApiResponse {
    private String code;
    private String description;
    private Object data;

    public static ApiResponse successResponse(Object data) {
        return ApiResponse.builder().code(ResponseCode.SUCCESS.getCode())
                .description(ResponseCode.SUCCESS.getDescription())
                .data(data).build();
    }

    public static ApiResponse defaultFailedResponse(Object data) {
        return ApiResponse.builder().code(ResponseCode.FAIL.getCode())
                .description(ResponseCode.FAIL.getDescription())
                .data(data).build();
    }
}