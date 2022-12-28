package com.indexpay.transfer.service.constant;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS("00", "Success"),
    FAIL("01", "Fail"),
    ERROR("02", "An error occurred!");

    private final String code;
    private final String description;

    ResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }



}
