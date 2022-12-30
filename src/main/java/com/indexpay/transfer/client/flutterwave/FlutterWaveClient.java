package com.indexpay.transfer.client.flutterwave;

import com.indexpay.transfer.exception.GenericException;
import com.indexpay.transfer.service.dto.BankDto;
import com.indexpay.transfer.service.dto.FlutterGetBankApiResponse;
import com.indexpay.transfer.utils.AppConstants;
import com.indexpay.transfer.utils.DtoTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static com.indexpay.transfer.utils.AppConstants.AUTHORIZATION_HEADER;
import static com.indexpay.transfer.utils.annotation.Utilities.ensureSuccessResponse;

@Component
@AllArgsConstructor
@Slf4j
public class FlutterWaveClient {

    private final FlutterWaveConfigProperties properties;
    private RestTemplate template;

    public List<BankDto> getBanks() {
        HttpEntity<Object> requestEntity = new HttpEntity<>(getHeaders());
        log.info("Request entity {}", requestEntity);
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
