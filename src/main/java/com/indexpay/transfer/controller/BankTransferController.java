package com.indexpay.transfer.controller;

import com.indexpay.transfer.service.BankTransferService;
import com.indexpay.transfer.service.dto.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/core-banking")
@Slf4j
public class BankTransferController {
    private final BankTransferService service;
    @GetMapping("/banks")
    public ResponseEntity<List<BankDto>> getBanks(@RequestParam(required = false) String provider){
        log.info("REST request to get banks via provider {}", provider);
        List<BankDto> response = service.getBanks(provider);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/validateBankAccount")
    public ResponseEntity<ValidateAccountResponse> validateBankAccount(@RequestBody @Valid ValidateAccountRequest request){
        log.info("REST request to validate bank number {}", request);
        ValidateAccountResponse response = service.validateBankAccount(request);
        log.info("REST response to validate bank number {} {}", request, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/bankTransfer")
    public ResponseEntity<BankTransferResponse> transFerFund(@RequestBody @Valid BankTransferRequest request){
        log.info("REST request to transfer fund to an account {}", request);
        BankTransferResponse response = service.transFerFund(request);
        log.info("REST response to transfer fund to an account {} {}", request, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/transaction/{transactionReference}")
    public ResponseEntity<GetTransactionStatusResponse> getTransactionStatus(@PathVariable String transactionReference){
        log.info("REST request to get transaction status {}", transactionReference);
        GetTransactionStatusResponse response = service.getTransactionStatus(transactionReference);
        log.info("REST response to get transaction status {} {}", transactionReference, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}