package com.indexpay.transfer.client.flutterwave;

import com.indexpay.transfer.entity.TransactionLog;
import com.indexpay.transfer.entity.enumeration.Status;
import com.indexpay.transfer.exception.GenericException;
import com.indexpay.transfer.service.TransactionLogService;
import com.indexpay.transfer.service.constant.ResponseCode;
import com.indexpay.transfer.service.dto.*;
import com.indexpay.transfer.utils.AppConstants;
import com.indexpay.transfer.utils.DtoTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.indexpay.transfer.utils.AppConstants.AUTHORIZATION_HEADER;
import static com.indexpay.transfer.utils.annotation.Utilities.ensureSuccessResponse;

@Component
@AllArgsConstructor
@Slf4j
public class FlutterWaveClient {

    private final FlutterWaveConfigProperties properties;
    private RestTemplate template;
    private TransactionLogService transactionLogService;

    public List<BankDto> getBanks() {
        HttpEntity<Object> requestEntity = new HttpEntity<>(getHeaders());
        log.info("Request entity {}", requestEntity);
        try {
            ResponseEntity<FlutterGetBankApiResponse> response =
                    template.exchange(buildUrl(properties.getGetBanks()), HttpMethod.GET,
                            requestEntity, FlutterGetBankApiResponse.class);
            log.info("flutter get bank list response {} ", response);
            ensureSuccessResponse(response.getStatusCode(), "Failed to retrieve list of banks");
            FlutterGetBankApiResponse responseBody = response.getBody();
            if (responseBody == null) {
                throw new GenericException("Failed to retrieve list of banks");
            } else if ("success".equalsIgnoreCase(responseBody.getStatus())) {
                return DtoTransformer.transformToFlutterBankDto(responseBody.getData());
            } else {
                throw new GenericException(responseBody.getMessage());
            }
        }catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Transactional
    public void fundTransfer(TransactionLog transactionLog) {
        log.info("Inside fund transfer method {} ", transactionLog);
        FlutterTransferData transferRequest = FlutterTransferData.builder()
                .accountBank(transactionLog.getBeneficiaryBankCode())
                .amount(transactionLog.getAmount())
                .callbackUrl(transactionLog.getCallBackUrl())
                .accountNumber(transactionLog.getBeneficiaryAccountNumber())
                .debitCurrency(transactionLog.getCurrencyCode())
                .narration(transactionLog.getNarration())
                .reference(transactionLog.getTransactionReference())
                .currency(transactionLog.getCurrencyCode())
                .build();

        HttpEntity<FlutterTransferData> requestEntity = new HttpEntity<>(transferRequest, getHeaders());
        log.info("initiateTransfer entity {}", requestEntity);
        try {
            ResponseEntity<FlutterTransferResponse> response = template.exchange(buildUrl(properties.getTransfer()),
                    HttpMethod.POST, requestEntity, FlutterTransferResponse.class);

            log.info("Flutter transfer response {} ", response);
            ensureSuccessResponse(response.getStatusCode(), "Failed to initiate transfer");

            FlutterTransferResponse responseBody = response.getBody();
            if (responseBody == null) {
                throw new GenericException("Failed to retrieve list of banks");
            } else if (ResponseCode.SUCCESS.getDescription().equalsIgnoreCase(responseBody.getStatus())) {
                FlutterTransferResponseData data = responseBody.getData();
                if (data != null) {
                    finalTransactionLogUpdate(transactionLog, data);
                    BankTransferResponse transferResponse = DtoTransformer.transformToFlutterBankTransferResponse(transactionLog, data);
                    if (StringUtils.hasText(transactionLog.getCallBackUrl())) {
                        postToCallBackUrl(transactionLog.getCallBackUrl(), transferResponse);
                    }
                }
            }
        }catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Transactional
    public GetTransactionStatusResponse getTransactionStatus(TransactionLog transactionLog) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(getHeaders());
        log.info("Request entity {}", requestEntity);
        try {
            ResponseEntity<FlutterGetStatusResponse> response =
                    template.exchange(buildUrl(properties.getVerifyTransfer()), HttpMethod.GET,
                            requestEntity, FlutterGetStatusResponse.class, transactionLog.getTransactionReference());
            log.info("flutter get transaction status response {} ", response);
            ensureSuccessResponse(response.getStatusCode(), "Failed to get transaction status");
            FlutterGetStatusResponse responseBody = response.getBody();
            if (responseBody == null) {
                throw new GenericException("Failed to retrieve list of banks");
            } else if ("success".equalsIgnoreCase(responseBody.getStatus())) {
                FlutterwGetStatusData data = responseBody.getData();
                if (data != null) {
                    transactionLog.setStatus(data.getStatus());
                    transactionLog = transactionLogService.save(transactionLog);
                    return DtoTransformer.transformToStatusResponse(transactionLog);
                }
            }
        }catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
        throw new GenericException("Failed to process request");

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

    @Transactional
    public void finalTransactionLogUpdate(TransactionLog transactionLog, FlutterTransferResponseData data) {
        transactionLog.setSessionId(UUID.randomUUID().toString());
        String status = data.getStatus();
        if (status.equalsIgnoreCase("NEW")){
            status = Status.PENDING.name();
        }
        transactionLog.setStatus(status);
        transactionLogService.save(transactionLog);
        log.info("Updated transaction log {} ", transactionLog);
    }

    @Async("taskExecutor")
    public void postToCallBackUrl(String callBackUrl, BankTransferResponse bankTransferResponse) {
        HttpEntity<BankTransferResponse> requestEntity = new HttpEntity<>(bankTransferResponse);
        log.info("postToCallBackUrl entity flutter {} {}", requestEntity, callBackUrl);
        ResponseEntity<String> apiResponse = template.exchange(callBackUrl,
                HttpMethod.POST, requestEntity, String.class);
        log.info("Flutter Call back response for {} == {}", requestEntity, apiResponse);
    }
}
