package com.walidzaaneun.digitalbankingbackend.repositories;

import com.walidzaaneun.digitalbankingbackend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
