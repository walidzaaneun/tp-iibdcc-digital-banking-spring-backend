package com.walidzaaneun.digitalbankingbackend.webControllers;

import com.walidzaaneun.digitalbankingbackend.dtos.*;
import com.walidzaaneun.digitalbankingbackend.exceptions.BalanceNotSufficientException;
import com.walidzaaneun.digitalbankingbackend.exceptions.BankAccountNotFoundException;
import com.walidzaaneun.digitalbankingbackend.services.AccountOperationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class AccountOperationRestController {
    private AccountOperationService accountOperationService;

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return accountOperationService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "5")int size) throws BankAccountNotFoundException {
        return accountOperationService.getAccountHistory(accountId,page,size);
    }
    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.accountOperationService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }
    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        this.accountOperationService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.accountOperationService.transfer(
                transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }
}
