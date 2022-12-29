package com.indexpay.transfer.client.paystack;


import com.indexpay.transfer.exception.GenericException;
import com.indexpay.transfer.service.dto.PaystackGetBanksResponse;
import com.indexpay.transfer.utils.AppConstants;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class PaystackApiClient {
    private final PaystackConfigProperties properties;
    private final RestTemplate template;
    public PaystackApiClient(PaystackConfigProperties properties, RestTemplate template) {
        this.properties = properties;
        this.template = template;
    }
    public PaystackGetBanksResponse getBanks() {
        ResponseEntity<PaystackGetBanksResponse> response =
                template.exchange(properties.getUrl(), HttpMethod.GET,
                new HttpEntity<>(getHeaders()), PaystackGetBanksResponse.class);
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new GenericException("Failed to retrieve list of banks");
        }
        return response.getBody();
    }
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("AUTHORIZATION_HEADER", AppConstants.TOKEN_TYPE + properties.getSecretKey());
        return headers;
    }
}