package com.indexpay.transfer.client.paystack;


import com.indexpay.transfer.entity.TransactionLog;
import com.indexpay.transfer.exception.GenericException;
import com.indexpay.transfer.repository.TransactionLogRepository;
import com.indexpay.transfer.service.dto.*;
import com.indexpay.transfer.utils.AppConstants;
import com.indexpay.transfer.utils.DtoTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.indexpay.transfer.utils.AppConstants.AUTHORIZATION_HEADER;

@Component
@Slf4j
public class PaystackApiClient {
    private final PaystackConfigProperties properties;
    private final RestTemplate template;
    private final TransactionLogRepository transactionLogRepository;

    public PaystackApiClient(PaystackConfigProperties properties, RestTemplate template, TransactionLogRepository transactionLogRepository) {
        this.properties = properties;
        this.template = template;
        this.transactionLogRepository = transactionLogRepository;
    }

    @Cacheable(value = "banks")
    public List<BankDto> getBanks() {
        HttpEntity<Object> requestEntity = new HttpEntity<>(getHeaders());
        log.info("Request entity {}", requestEntity);
        ResponseEntity<PaystackGetBanksResponse> response =
                template.exchange(buildUrl(properties.getGetBanks()), HttpMethod.GET,
                        requestEntity, PaystackGetBanksResponse.class);
        log.info("Paystack get bank list response {} ", response);
        ensureSuccessResponse(response.getStatusCode(), "Failed to retrieve list of banks");
        PaystackGetBanksResponse responseBody = response.getBody();
        if (responseBody == null) {
            throw new GenericException("Failed to retrieve list of banks");
        } else if (Boolean.TRUE.equals(responseBody.getStatus())) {
            return DtoTransformer.transformToBankDto(responseBody.getData());
        } else {
            throw new GenericException(responseBody.getMessage());
        }
    }

    public ValidateAccountResponse validateBankAccount(ValidateAccountRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("account_number", request.getAccountNumber());
        params.put("bank_code", request.getBankCode());

        HttpEntity<Object> requestEntity = new HttpEntity<>(getHeaders());
        log.info("Request entity {}", requestEntity);

        ResponseEntity<PaystackValidateResponse> response =
                template.exchange(buildUrl(properties.getValidateAccount()), HttpMethod.GET,
                        requestEntity, PaystackValidateResponse.class, params);
        log.info("Paystack validate Account response {} ", response);
        ensureSuccessResponse(response.getStatusCode(), "Failed to validate bank account");

        PaystackValidateResponse responseBody = response.getBody();
        if (responseBody == null) {
            throw new GenericException("Failed to retrieve list of banks");
        } else if (Boolean.TRUE.equals(responseBody.getStatus())) {
            return DtoTransformer.transformToValidateResponse(responseBody.getData(), request);
        } else {
            throw new GenericException(responseBody.getMessage());
        }
    }

    public void transFerFund(BankTransferRequest request) {
        String recipientCode = createTransferRecipient(request);
        Optional<TransactionLog> transactionLogOptional =
                transactionLogRepository.findByTransactionReference(request.getTransactionReference());
        TransactionLog transactionLog = transactionLogOptional.orElseThrow(PaystackApiClient::throwException);
        transactionLog.setRecipientCode(recipientCode);
        transactionLog = transactionLogRepository.save(transactionLog);
        initiateTransfer(request, recipientCode, transactionLog);
    }

    public GetTransactionStatusResponse getTransactionStatus(String reference, TransactionLog transactionLog) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(getHeaders());
        log.info("Request entity {}", requestEntity);

        ResponseEntity<PaystackTransactionStatusResponse> response =
                template.exchange(buildUrl(properties.getValidateAccount()), HttpMethod.GET,
                        requestEntity, PaystackTransactionStatusResponse.class, reference);
        log.info("Paystack get transaction status response {} ", response);
        ensureSuccessResponse(response.getStatusCode(), "Failed to get transaction status");

        PaystackTransactionStatusResponse responseBody = response.getBody();
        if (responseBody == null) {
            throw new GenericException("Failed to retrieve list of banks");
        } else if (Boolean.TRUE.equals(responseBody.getStatus())) {
            PaystackTransactionStatusData data = responseBody.getData();
            if (data != null) {
                transactionLog.setStatus(data.getStatus());
                transactionLog = transactionLogRepository.save(transactionLog);
                return DtoTransformer.transformToStatusResponse(transactionLog);
            }
        }
        throw new GenericException(responseBody.getMessage());
    }

    private BankTransferResponse initiateTransfer(BankTransferRequest request, String recipientCode, TransactionLog transactionLog) {
        InitiateTransferRequest transferRequest = new InitiateTransferRequest("balance",
                request.getAmount().toString(), request.getTransactionReference(), recipientCode,
                request.getNarration());
        HttpEntity<InitiateTransferRequest> requestEntity = new HttpEntity<>(transferRequest, getHeaders());
        log.info("initiateTransfer entity {}", requestEntity);
        ResponseEntity<InitiateTransferResponse> response = template.exchange(buildUrl(properties.getTransfer()),
                HttpMethod.POST, requestEntity, InitiateTransferResponse.class);

        log.info("Paystack initiate transfer response {} ", response);
        ensureSuccessResponse(response.getStatusCode(), "Failed to initiate transfer");

        InitiateTransferResponse responseBody = response.getBody();
        if (responseBody == null) {
            throw new GenericException("Failed to retrieve list of banks");
        } else if (Boolean.TRUE.equals(responseBody.getStatus())) {
            InitiateTransferData data = responseBody.getData();
            if (data != null) {
                finalTransactionLogUpdate(transactionLog, data);
                BankTransferResponse transferResponse = DtoTransformer.transformToBankTransferResponse(request, data);
                if (StringUtils.hasText(request.getCallBackUrl())) {
                    postToCallBackUrl(request.getCallBackUrl(), transferResponse );
                }
                return transferResponse;
            }
        }
        throw new GenericException(responseBody.getMessage());
    }

    @Async("taskExecutor")
    public void postToCallBackUrl(String callBackUrl, BankTransferResponse bankTransferResponse) {
        HttpEntity<BankTransferResponse> requestEntity = new HttpEntity<>(bankTransferResponse);
        log.info("postToCallBackUrl entity {} {}", requestEntity, callBackUrl);
        ResponseEntity<String> apiResponse = template.exchange(callBackUrl,
                HttpMethod.POST, requestEntity, String.class);
        log.info("Call back response for {} == {}", requestEntity, apiResponse);
    }

    private void finalTransactionLogUpdate(TransactionLog transactionLog, InitiateTransferData data) {
        transactionLog.setSessionId(data.getTransferCode());
        transactionLog.setStatus(data.getStatus());
        transactionLogRepository.save(transactionLog);
        log.info("Updated transaction log {} ", transactionLog);
    }

    private String createTransferRecipient(BankTransferRequest request) {
        TransferRecipientRequest recipientRequest = new TransferRecipientRequest("nuban",
                request.getBeneficiaryAccountName(), request.getBeneficiaryAccountNumber(),
                request.getBeneficiaryBankCode(), request.getCurrencyCode());
        HttpEntity<TransferRecipientRequest> requestEntity = new HttpEntity<>(recipientRequest, getHeaders());
        log.info("getTransferRecipient entity {}", requestEntity);
        ResponseEntity<TransferRecipientResponse> response = template.exchange(buildUrl(properties.getTransferRecipient()),
                HttpMethod.POST, requestEntity, TransferRecipientResponse.class);

        log.info("Paystack get transfer recipient response {} ", response);
        ensureSuccessResponse(HttpStatus.CREATED, response.getStatusCode(), "Failed to initiate recipient transfer");

        TransferRecipientResponse responseBody = response.getBody();
        if (responseBody == null) {
            throw new GenericException("Failed to retrieve list of banks");
        } else if (Boolean.TRUE.equals(responseBody.getStatus())) {
            if (responseBody.getData() != null) {
                return responseBody.getData().getRecipientCode();
            }
        }
        throw new GenericException(responseBody.getMessage());
    }
    private static void ensureSuccessResponse(HttpStatus statusCode, String message) {
        ensureSuccessResponse(HttpStatus.OK, statusCode, message);
    }
    private static void ensureSuccessResponse(HttpStatus expectedStatus,HttpStatus statusCode, String message) {
        if (!expectedStatus.equals(statusCode)) {
            throw new GenericException(message);
        }
    }

    private String buildUrl(String path) {
        return properties.getUrl() + path;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add(AUTHORIZATION_HEADER, AppConstants.TOKEN_TYPE + properties.getSecretKey());
        return headers;
    }
    private static RuntimeException throwException() {
        throw new GenericException("Invalid transaction details");
    }
}