package com.indexpay.transfer.client;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaystackClientConfig {
    private static final String AUTHORIZATION_HEADER="Authorization";
    private static final String TOKEN_TYPE = "Bearer";
    public static final String ACCEPT_HEADER = "Accept";
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";

    @Value("${api.paystack.secretKey}")
    String key;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, key));
            requestTemplate.header(ACCEPT_HEADER, APPLICATION_JSON);
            requestTemplate.header(CONTENT_TYPE_HEADER, APPLICATION_JSON);
        };
    }
}
