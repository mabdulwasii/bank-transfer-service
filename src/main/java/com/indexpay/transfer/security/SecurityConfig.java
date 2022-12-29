package com.indexpay.transfer.security;

import com.indexpay.transfer.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Objects;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig {
    @Value("${spring.application.api-key}")
    private String apiKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthFilter filter = new AuthFilter();
        filter.setAuthenticationManager(authentication -> {
            String suppliedApiKey = (String) authentication.getPrincipal();
            if (!Objects.equals(EncryptionUtil.encrypt(apiKey), suppliedApiKey)) {
                throw new AccessDeniedException("Invalid api key.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        http.antMatcher("/api/**").
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic().disable();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> {
            throw new AuthenticationServiceException("");
        };
    }
}
