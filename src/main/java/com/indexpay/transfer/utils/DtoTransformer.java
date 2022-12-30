package com.indexpay.transfer.utils;

import com.indexpay.transfer.entity.TransactionLog;
import com.indexpay.transfer.entity.enumeration.Status;
import com.indexpay.transfer.service.constant.ResponseCode;
import com.indexpay.transfer.service.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public class DtoTransformer {

    private DtoTransformer(){}

    public static List<BankDto> transformToBankDto(List<PaystackBank> payStackBanks) {
        return payStackBanks.stream().map(paystackBank -> new BankDto(paystackBank.getCode(), paystackBank.getName(), paystackBank.getLongcode())).toList();
    }
    public static List<BankDto> transformToFlutterBankDto(List<FlutterGetBankData> flutterBanks) {
        return flutterBanks.stream().map(flutterBank -> new BankDto(flutterBank.getCode(), flutterBank.getName(), String.valueOf(flutterBank.getId()))).toList();
    }
    public static ValidateAccountResponse transformToValidateResponse(PaystackValidateData data, ValidateAccountRequest request) {
        return new ValidateAccountResponse(data.getAccountNumber(), data.getAccountName(), request.getBankCode(), String.valueOf(data.getBankId()));
    }

    public static GetTransactionStatusResponse transformToStatusResponse(TransactionLog transactionLog) {
        String statusString = transactionLog.getStatus();
        Status status = Status.valueOf(statusString.toUpperCase());
        return GetTransactionStatusResponse.builder().status(statusString)
                .amount(transactionLog.getAmount())
                .responseMessage(status.getDescription())
                .beneficiaryBankCode(transactionLog.getBeneficiaryBankCode())
                .sessionId(transactionLog.getSessionId())
                .beneficiaryAccountNumber(transactionLog.getBeneficiaryAccountNumber())
                .currencyCode(transactionLog.getCurrencyCode())
                .beneficiaryAccountName(transactionLog.getBeneficiaryAccountName())
                .transactionDateTime(transactionLog.getTransactionDateTime())
                .transactionReference(transactionLog.getTransactionReference())
                .responseCode(ResponseCode.SUCCESS.getCode())
                .build();
    }
    public static TransactionLog transformToTransactionLog(BankTransferRequest request) {
        return TransactionLog.builder().amount(request.getAmount()).beneficiaryBankCode(request.getBeneficiaryBankCode())
                .beneficiaryAccountName(request.getBeneficiaryAccountName())
                .callBackUrl(request.getCallBackUrl())
                .narration(request.getNarration())
                .maxRetryAttempt(request.getMaxRetryAttempt())
                .beneficiaryAccountNumber(request.getBeneficiaryAccountNumber())
                .status(Status.PENDING.name())
                .provider(request.getProvider())
                .transactionDateTime(LocalDateTime.now().toString())
                .transactionReference(request.getTransactionReference())
                .build();
    }

    public static BankTransferResponse transformToBankTransferResponse(TransactionLog transactionLog,
                                                                       InitiateTransferData data) {
        return BankTransferResponse.builder()
                .amount(transactionLog.getAmount())
                .beneficiaryAccountNumber(transactionLog.getBeneficiaryAccountNumber())
                .beneficiaryBankCode(transactionLog.getBeneficiaryBankCode())
                .currencyCode(data.getCurrency())
                .responseCode(ResponseCode.SUCCESS.getCode())
                .responseMessage(AppConstants.SUCCESS_TRANSFER)
                .sessionId(data.getTransferCode())
                .beneficiaryAccountName(transactionLog.getBeneficiaryAccountName())
                .transactionDateTime(data.getCreatedAt())
                .transactionReference(data.getReference())
                .status(transactionLog.getStatus())
                .build();
    }
    public static BankTransferResponse transformToBankTransferResponse(BankTransferRequest request) {
        return BankTransferResponse.builder()
                .amount(request.getAmount())
                .beneficiaryAccountNumber(request.getBeneficiaryAccountNumber())
                .beneficiaryBankCode(request.getBeneficiaryBankCode())
                .currencyCode(request.getCurrencyCode())
                .responseCode(ResponseCode.SUCCESS.getCode())
                .responseMessage(AppConstants.SUCCESS_TRANSFER)
                .beneficiaryAccountName(request.getBeneficiaryAccountName())
                .transactionDateTime(LocalDateTime.now().toString())
                .transactionReference(request.getTransactionReference())
                .status(Status.PENDING.name())
                .build();
    }

    public static BankTransferResponse transformToFlutterBankTransferResponse(TransactionLog transactionLog, FlutterTransferResponseData data) {
        return BankTransferResponse.builder()
                .amount(transactionLog.getAmount())
                .beneficiaryAccountNumber(transactionLog.getBeneficiaryAccountNumber())
                .beneficiaryBankCode(transactionLog.getBeneficiaryBankCode())
                .currencyCode(data.getCurrency())
                .responseCode(ResponseCode.SUCCESS.getCode())
                .responseMessage(AppConstants.SUCCESS_TRANSFER)
                .sessionId(transactionLog.getSessionId())
                .beneficiaryAccountName(transactionLog.getBeneficiaryAccountName())
                .transactionDateTime(data.getCreatedAt())
                .transactionReference(data.getReference())
                .status(transactionLog.getStatus())
                .build();
    }
}
