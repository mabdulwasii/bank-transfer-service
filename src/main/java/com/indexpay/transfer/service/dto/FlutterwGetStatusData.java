package com.indexpay.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "tx_ref",
        "flw_ref",
        "device_fingerprint",
        "amount",
        "currency",
        "charged_amount",
        "app_fee",
        "merchant_fee",
        "processor_response",
        "auth_model",
        "ip",
        "narration",
        "status",
        "payment_type",
        "created_at",
        "account_id",
        "card",
        "meta",
        "amount_settled",
        "customer"
})
@ToString
@Data
public class FlutterwGetStatusData {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("tx_ref")
    private String txRef;
    @JsonProperty("flw_ref")
    private String flwRef;
    @JsonProperty("device_fingerprint")
    private String deviceFingerprint;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("charged_amount")
    private Integer chargedAmount;
    @JsonProperty("app_fee")
    private Double appFee;
    @JsonProperty("merchant_fee")
    private Integer merchantFee;
    @JsonProperty("processor_response")
    private String processorResponse;
    @JsonProperty("auth_model")
    private String authModel;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("narration")
    private String narration;
    @JsonProperty("status")
    private String status;
    @JsonProperty("payment_type")
    private String paymentType;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("account_id")
    private Integer accountId;
    @JsonProperty("card")
    private Object card;
    @JsonProperty("meta")
    private Object meta;
    @JsonProperty("amount_settled")
    private Double amountSettled;
    @JsonProperty("customer")
    private Object customer;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("tx_ref")
    public String getTxRef() {
        return txRef;
    }

    @JsonProperty("tx_ref")
    public void setTxRef(String txRef) {
        this.txRef = txRef;
    }

    @JsonProperty("flw_ref")
    public String getFlwRef() {
        return flwRef;
    }

    @JsonProperty("flw_ref")
    public void setFlwRef(String flwRef) {
        this.flwRef = flwRef;
    }

    @JsonProperty("device_fingerprint")
    public String getDeviceFingerprint() {
        return deviceFingerprint;
    }

    @JsonProperty("device_fingerprint")
    public void setDeviceFingerprint(String deviceFingerprint) {
        this.deviceFingerprint = deviceFingerprint;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("charged_amount")
    public Integer getChargedAmount() {
        return chargedAmount;
    }

    @JsonProperty("charged_amount")
    public void setChargedAmount(Integer chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    @JsonProperty("app_fee")
    public Double getAppFee() {
        return appFee;
    }

    @JsonProperty("app_fee")
    public void setAppFee(Double appFee) {
        this.appFee = appFee;
    }

    @JsonProperty("merchant_fee")
    public Integer getMerchantFee() {
        return merchantFee;
    }

    @JsonProperty("merchant_fee")
    public void setMerchantFee(Integer merchantFee) {
        this.merchantFee = merchantFee;
    }

    @JsonProperty("processor_response")
    public String getProcessorResponse() {
        return processorResponse;
    }

    @JsonProperty("processor_response")
    public void setProcessorResponse(String processorResponse) {
        this.processorResponse = processorResponse;
    }

    @JsonProperty("auth_model")
    public String getAuthModel() {
        return authModel;
    }

    @JsonProperty("auth_model")
    public void setAuthModel(String authModel) {
        this.authModel = authModel;
    }

    @JsonProperty("ip")
    public String getIp() {
        return ip;
    }

    @JsonProperty("ip")
    public void setIp(String ip) {
        this.ip = ip;
    }

    @JsonProperty("narration")
    public String getNarration() {
        return narration;
    }

    @JsonProperty("narration")
    public void setNarration(String narration) {
        this.narration = narration;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("payment_type")
    public String getPaymentType() {
        return paymentType;
    }

    @JsonProperty("payment_type")
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("account_id")
    public Integer getAccountId() {
        return accountId;
    }

    @JsonProperty("account_id")
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("card")
    public Object getCard() {
        return card;
    }

    @JsonProperty("card")
    public void setCard(Object card) {
        this.card = card;
    }

    @JsonProperty("meta")
    public Object getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Object meta) {
        this.meta = meta;
    }

    @JsonProperty("amount_settled")
    public Double getAmountSettled() {
        return amountSettled;
    }

    @JsonProperty("amount_settled")
    public void setAmountSettled(Double amountSettled) {
        this.amountSettled = amountSettled;
    }

    @JsonProperty("customer")
    public Object getCustomer() {
        return customer;
    }

    @JsonProperty("customer")
    public void setCustomer(Object customer) {
        this.customer = customer;
    }

}