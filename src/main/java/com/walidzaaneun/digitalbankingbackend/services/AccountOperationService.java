package com.walidzaaneun.digitalbankingbackend.services;

import com.walidzaaneun.digitalbankingbackend.dtos.AccountHistoryDTO;
import com.walidzaaneun.digitalbankingbackend.dtos.AccountOperationDTO;
import com.walidzaaneun.digitalbankingbackend.exceptions.BalanceNotSufficientException;
import com.walidzaaneun.digitalbankingbackend.exceptions.BankAccountNotFoundException;

import java.util.List;

public interface AccountOperationService {
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
    List<AccountOperationDTO> accountHistory(String accountId);
    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
