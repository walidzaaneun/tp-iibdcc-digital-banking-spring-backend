package com.walidzaaneun.digitalbankingbackend.services.impl;

import com.walidzaaneun.digitalbankingbackend.dtos.BankAccountDTO;
import com.walidzaaneun.digitalbankingbackend.dtos.CurrentBankAccountDTO;
import com.walidzaaneun.digitalbankingbackend.dtos.SavingBankAccountDTO;
import com.walidzaaneun.digitalbankingbackend.entities.BankAccount;
import com.walidzaaneun.digitalbankingbackend.entities.CurrentAccount;
import com.walidzaaneun.digitalbankingbackend.entities.Customer;
import com.walidzaaneun.digitalbankingbackend.entities.SavingAccount;
import com.walidzaaneun.digitalbankingbackend.exceptions.BankAccountNotFoundException;
import com.walidzaaneun.digitalbankingbackend.exceptions.CustomerNotFoundException;
import com.walidzaaneun.digitalbankingbackend.mappers.BankAccountMapperImpl;
import com.walidzaaneun.digitalbankingbackend.repositories.BankAccountRepository;
import com.walidzaaneun.digitalbankingbackend.repositories.CustomerRepository;
import com.walidzaaneun.digitalbankingbackend.services.BankAccountService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @Transactional @AllArgsConstructor @Slf4j
public class BankAccountServiceImpl implements BankAccountService {
    private BankAccountMapperImpl dtoMapper;
    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;

    @Override
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
        if(bankAccount instanceof SavingAccount){
            SavingAccount savingAccount= (SavingAccount) bankAccount;
            return dtoMapper.fromSavingBankAccount(savingAccount);
        } else {
            CurrentAccount currentAccount= (CurrentAccount) bankAccount;
            return dtoMapper.fromCurrentBankAccount(currentAccount);
        }
    }

    @Override
    public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if(customer==null)
            throw new CustomerNotFoundException("Customer not found");
        CurrentAccount currentAccount=new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        CurrentAccount savedBankAccount = bankAccountRepository.save(currentAccount);
        return dtoMapper.fromCurrentBankAccount(savedBankAccount);
    }

    @Override
    public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if(customer==null)
            throw new CustomerNotFoundException("Customer not found");
        SavingAccount savingAccount=new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);
        return dtoMapper.fromSavingBankAccount(savedBankAccount);
    }

    @Override
    public List<BankAccountDTO> bankAccountList(){
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        List<BankAccountDTO> bankAccountDTOS = bankAccounts.stream().map(bankAccount -> {
            if (bankAccount instanceof SavingAccount) {
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return dtoMapper.fromSavingBankAccount(savingAccount);
            } else {
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return dtoMapper.fromCurrentBankAccount(currentAccount);
            }
        }).collect(Collectors.toList());
        return bankAccountDTOS;
    }
}
