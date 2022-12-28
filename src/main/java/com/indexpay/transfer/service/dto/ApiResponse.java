package com.indexpay.transfer.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponse {
    private String code;
    private String description;
    private Object data;
}