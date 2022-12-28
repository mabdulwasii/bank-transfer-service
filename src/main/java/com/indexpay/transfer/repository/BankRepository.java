package com.indexpay.transfer.repository;

import com.indexpay.transfer.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {}
