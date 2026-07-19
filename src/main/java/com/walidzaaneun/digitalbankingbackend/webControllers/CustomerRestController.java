package com.walidzaaneun.digitalbankingbackend.webControllers;

import com.walidzaaneun.digitalbankingbackend.dtos.CustomerDTO;
import com.walidzaaneun.digitalbankingbackend.exceptions.CustomerNotFoundException;
import com.walidzaaneun.digitalbankingbackend.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return customerService.listCustomers();
    }
    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword){
        return customerService.searchCustomers("%"+keyword+"%");
    }
    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return customerService.getCustomer(customerId);
    }
    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomer(customerDTO);
    }
    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return customerService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }
}