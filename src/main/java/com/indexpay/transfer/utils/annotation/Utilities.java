package com.indexpay.transfer.utils.annotation;

import com.indexpay.transfer.exception.GenericException;
import org.springframework.http.HttpStatus;

public class Utilities {
    public static void ensureSuccessResponse(HttpStatus statusCode, String message) throws IllegalArgumentException{
        ensureSuccessResponse(HttpStatus.OK, statusCode, message);
    }
    public static void ensureSuccessResponse(HttpStatus expectedStatus,HttpStatus statusCode, String message) {
        if (!expectedStatus.equals(statusCode)) {
            throw new GenericException(message);
        }
    }
}
