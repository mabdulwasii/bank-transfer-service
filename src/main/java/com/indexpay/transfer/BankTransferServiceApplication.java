package com.indexpay.transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class BankTransferServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankTransferServiceApplication.class, args);
    }

}
