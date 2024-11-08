package com.infy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.DigitalBankAccountEntity;

public interface DigitalBankAccountRepository extends JpaRepository<DigitalBankAccountEntity, String> {

}
