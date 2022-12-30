package com.indexpay.transfer.client.paystack;


import com.indexpay.transfer.exception.GenericException;
import com.indexpay.transfer.service.dto.BankDto;
import com.indexpay.transfer.service.dto.PaystackGetBanksResponse;
import com.indexpay.transfer.utils.AppConstants;
import com.indexpay.transfer.utils.DtoTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

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
        ResponseEntity<PaystackGetBanksResponse> response =
                template.exchange(buildUrl(properties.getGetBanks()), HttpMethod.GET,
                new HttpEntity<>(getHeaders()), PaystackGetBanksResponse.class);
        log.info("Get bank list response {} ", response );
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new GenericException("Failed to retrieve list of banks");
        }
        PaystackGetBanksResponse responseBody = response.getBody();
        if (responseBody == null) {
            throw new GenericException("Failed to retrieve list of banks");
        } else if(responseBody.getStatus()) {
            return DtoTransformer.transformToBankDto(responseBody.getData());
        }else {
            throw new GenericException(responseBody.getMessage());
        }
    }

    private String buildUrl(String path) {
        return properties.getUrl() + path;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("AUTHORIZATION_HEADER", AppConstants.TOKEN_TYPE + properties.getSecretKey());
        return headers;
    }
}