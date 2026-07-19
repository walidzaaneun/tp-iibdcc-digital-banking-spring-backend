package com.walidzaaneun.digitalbankingbackend.repositories;

import com.walidzaaneun.digitalbankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
