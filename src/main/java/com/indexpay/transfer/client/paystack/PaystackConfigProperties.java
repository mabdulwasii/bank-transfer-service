package com.indexpay.transfer.client.paystack;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.paystack")
public class PaystackConfigProperties {
    private String url;
    private String getBanks;
    private String validateAccount;
    private String transferRecipient;
    private String transfer;
    private String verifyTransfer;
    private String secretKey;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGetBanks() {
        return getBanks;
    }

    public void setGetBanks(String getBanks) {
        this.getBanks = getBanks;
    }

    public String getValidateAccount() {
        return validateAccount;
    }

    public void setValidateAccount(String validateAccount) {
        this.validateAccount = validateAccount;
    }

    public String getTransferRecipient() {
        return transferRecipient;
    }

    public void setTransferRecipient(String transferRecipient) {
        this.transferRecipient = transferRecipient;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getVerifyTransfer() {
        return verifyTransfer;
    }

    public void setVerifyTransfer(String verifyTransfer) {
        this.verifyTransfer = verifyTransfer;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}