package com.infy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

}
