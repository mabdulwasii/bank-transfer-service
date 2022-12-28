package com.indexpay.transfer.service.constant;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS("00", "Success"),
    TRANSFER_SUCCESS("00", "Transfer_Successful"),
    TRANSFER_FAILURE("99", "Transfer_Not_Successful"),
    FAIL("01", "Fail"),
    ERROR("02", "An error occurred!");

    private final String code;
    private final String description;

    ResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
