package com.walidzaaneun.digitalbankingbackend.webControllers;

import com.walidzaaneun.digitalbankingbackend.dtos.*;
import com.walidzaaneun.digitalbankingbackend.exceptions.BankAccountNotFoundException;
import com.walidzaaneun.digitalbankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }
}
