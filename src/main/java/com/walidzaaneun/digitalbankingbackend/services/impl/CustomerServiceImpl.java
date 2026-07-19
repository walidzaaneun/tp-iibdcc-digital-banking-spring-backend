package com.walidzaaneun.digitalbankingbackend.services.impl;

import com.walidzaaneun.digitalbankingbackend.dtos.CustomerDTO;
import com.walidzaaneun.digitalbankingbackend.entities.Customer;
import com.walidzaaneun.digitalbankingbackend.exceptions.CustomerNotFoundException;
import com.walidzaaneun.digitalbankingbackend.mappers.BankAccountMapperImpl;
import com.walidzaaneun.digitalbankingbackend.repositories.CustomerRepository;
import com.walidzaaneun.digitalbankingbackend.services.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @Transactional @AllArgsConstructor @Slf4j
public class CustomerServiceImpl implements CustomerService {
    private BankAccountMapperImpl dtoMapper;
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving new Customer");
        Customer customer=dtoMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return dtoMapper.fromCustomer(savedCustomer);
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> dtoMapper.fromCustomer(customer))
                .collect(Collectors.toList());
        return customerDTOS;
    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not found"));
        return dtoMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers=customerRepository.searchCustomer(keyword);
        List<CustomerDTO> customerDTOS = customers.stream().map(cust -> dtoMapper.fromCustomer(cust)).collect(Collectors.toList());
        return customerDTOS;
    }
}
