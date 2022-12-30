package com.indexpay.transfer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BankDto implements Serializable {
    private String code;
    private String bankName;
    private String longCode;
}
