package com.walidzaaneun.digitalbankingbackend.services;

import com.walidzaaneun.digitalbankingbackend.dtos.BankAccountDTO;
import com.walidzaaneun.digitalbankingbackend.dtos.CurrentBankAccountDTO;
import com.walidzaaneun.digitalbankingbackend.dtos.SavingBankAccountDTO;
import com.walidzaaneun.digitalbankingbackend.exceptions.BankAccountNotFoundException;
import com.walidzaaneun.digitalbankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<BankAccountDTO> bankAccountList();
}
