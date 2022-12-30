package com.indexpay.transfer.entity.enumeration;

public enum Status {
    CREATED("Transaction_Created"),
    PENDING("Transaction_Pending"),
    SUCCESS("Transaction_Successful"),
    FAILURE("Transaction_Not_Successful"),
    RETRY("Retry_Transaction");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}