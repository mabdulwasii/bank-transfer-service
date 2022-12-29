package com.indexpay.transfer.client;

import com.indexpay.transfer.service.dto.PaystackGetBanksResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "paystack", url = "${api.paystack.url}", configuration = PaystackClientConfig.class)
public interface PaystackApiClient {
    @GetMapping("${api.paystack.getBanks}")
    PaystackGetBanksResponse getBanks();

    @GetMapping("${api.paystack.validateAccount")
    List<PaystackGetBanksResponse> validateAccountNumber(
            @RequestParam(name = "account-number") String accountNumber,
            @RequestParam(name = "bank_code") String bankCode);


}
