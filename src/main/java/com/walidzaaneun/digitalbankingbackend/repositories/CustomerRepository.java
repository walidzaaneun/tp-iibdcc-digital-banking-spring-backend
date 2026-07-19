package com.walidzaaneun.digitalbankingbackend.repositories;

import com.walidzaaneun.digitalbankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
