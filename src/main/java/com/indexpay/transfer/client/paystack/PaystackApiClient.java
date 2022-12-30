package com.indexpay.transfer.client.paystack;


import com.indexpay.transfer.exception.GenericException;
import com.indexpay.transfer.service.dto.*;
import com.indexpay.transfer.utils.AppConstants;
import com.indexpay.transfer.utils.DtoTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.indexpay.transfer.utils.AppConstants.AUTHORIZATION_HEADER;

@Component
@Slf4j
public class PaystackApiClient {
    private final PaystackConfigProperties properties;
    private final RestTemplate template;
    public PaystackApiClient(PaystackConfigProperties properties, RestTemplate template) {
        this.properties = properties;
        this.template = template;
    }
    @CachePut(value = "banks")
    public List<BankDto> getBanks() {
        HttpEntity<Object> requestEntity = new HttpEntity<>(getHeaders());
        log.info("Request entity {}" ,requestEntity);
        ResponseEntity<PaystackGetBanksResponse> response =
                template.exchange(buildUrl(properties.getGetBanks()), HttpMethod.GET,
                        requestEntity, PaystackGetBanksResponse.class);
        log.info("Paystack get bank list response {} ", response );
        ensureSuccessResponse(response.getStatusCode());
        PaystackGetBanksResponse responseBody = response.getBody();
        if (responseBody == null) {
            throw new GenericException("Failed to retrieve list of banks");
        } else if(Boolean.TRUE.equals(responseBody.getStatus())) {
            return DtoTransformer.transformToBankDto(responseBody.getData());
        }else {
            throw new GenericException(responseBody.getMessage());
        }
    }
    public ValidateAccountResponse validateBankAccount(ValidateAccountRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("account_number", request.getAccountNumber());
        params.put("bank_code", request.getBankCode());

        HttpEntity<Object> requestEntity = new HttpEntity<>(getHeaders());
        log.info("Request entity {}" ,requestEntity);

        ResponseEntity<PaystackValidateResponse> response =
                template.exchange(buildUrl(properties.getValidateAccount()), HttpMethod.GET,
                      requestEntity, PaystackValidateResponse.class, params);
        log.info("Paystack validate Account response {} ", response );
        ensureSuccessResponse(response.getStatusCode());

        PaystackValidateResponse responseBody = response.getBody();
        if (responseBody == null) {
            throw new GenericException("Failed to retrieve list of banks");
        } else if(Boolean.TRUE.equals(responseBody.getStatus())) {
            return DtoTransformer.transformToValidateResponse(responseBody.getData(), request);
        }else {
            throw new GenericException(responseBody.getMessage());
        }
    }

    private static void ensureSuccessResponse(HttpStatus statusCode) {
        if (!HttpStatus.OK.equals(statusCode)) {
            throw new GenericException("Failed to retrieve list of banks");
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
}