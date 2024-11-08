package com.infy.service;

import java.util.List;

import com.infy.dto.TransactionDTO;
import com.infy.exception.InfyMeMobileException;

public interface TransactionService {

	List<TransactionDTO> accountStatement(Long mobileNo) throws InfyMeMobileException;

	String fundTransfer(TransactionDTO transactionDTO) throws InfyMeMobileException;

}
