package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "authorization_code",
        "account_number",
        "account_name",
        "bank_code",
        "bank_name"
})
public class Details {

    @JsonProperty("authorization_code")
    private Object authorizationCode;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("account_name")
    private Object accountName;
    @JsonProperty("bank_code")
    private String bankCode;
    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("authorization_code")
    public Object getAuthorizationCode() {
        return authorizationCode;
    }

    @JsonProperty("authorization_code")
    public void setAuthorizationCode(Object authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    @JsonProperty("account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("account_number")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("account_name")
    public Object getAccountName() {
        return accountName;
    }

    @JsonProperty("account_name")
    public void setAccountName(Object accountName) {
        this.accountName = accountName;
    }

    @JsonProperty("bank_code")
    public String getBankCode() {
        return bankCode;
    }

    @JsonProperty("bank_code")
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @JsonProperty("bank_name")
    public String getBankName() {
        return bankName;
    }

    @JsonProperty("bank_name")
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "Details{" +
                "authorizationCode=" + authorizationCode +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountName=" + accountName +
                ", bankCode='" + bankCode + '\'' +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
