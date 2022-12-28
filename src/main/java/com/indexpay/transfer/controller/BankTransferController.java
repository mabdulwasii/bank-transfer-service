package com.indexpay.transfer.controller;

import com.indexpay.transfer.service.BankTransferService;
import com.indexpay.transfer.service.dto.ApiResponse;
import com.indexpay.transfer.service.dto.BankTransferRequest;
import com.indexpay.transfer.service.dto.ValidateAccountRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/core-banking")
@Slf4j
public class BankTransferController {
    private final BankTransferService service;
    @GetMapping("/banks")
    public ResponseEntity<ApiResponse> getBanks(@RequestParam String provider){
        log.info("REST request to get banks via provider {}", provider);
        ApiResponse response = service.getBanks(provider);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/validateBankAccount")
    public ResponseEntity<ApiResponse> validateBankAccount(@RequestBody @Valid ValidateAccountRequest request){
        log.info("REST request to validate bank number {}", request);
        ApiResponse response = service.validateBankAccount(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/bankTransfer")
    public ResponseEntity<ApiResponse> transFerFund(@RequestBody @Valid BankTransferRequest request){
        log.info("REST request to transfer fund to an account {}", request);
        ApiResponse response = service.transFerFund(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/transaction/{transactionReference}")
    public ResponseEntity<ApiResponse> getTransactionStatus(@PathVariable String transactionReference){
        log.info("REST request to get transaction status {}", transactionReference);
        ApiResponse response = service.getTransactionStatus(transactionReference);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}