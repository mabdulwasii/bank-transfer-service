package com.indexpay.transfer.exception;

public class InvalidTransactionReference extends RuntimeException {
    public InvalidTransactionReference(String message) {
        super(message);
    }
}