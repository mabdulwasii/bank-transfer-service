package com.indexpay.transfer.repository;

import com.indexpay.transfer.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Long> {
    List<Bank> findBankByProvider(String provider);
}
