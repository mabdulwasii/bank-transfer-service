package com.indexpay.transfer.service.constant;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS("00", "Success"),
    FAIL("01", "Fail"),
    ERROR("02", "An error occurred!");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }



}
