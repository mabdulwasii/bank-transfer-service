package com.indexpay.transfer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ApiErrorResponse {
    private String code;
    private String description;
}