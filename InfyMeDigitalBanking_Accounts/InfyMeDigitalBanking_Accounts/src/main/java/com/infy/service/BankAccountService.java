package com.infy.service;

import java.util.List;

import com.infy.dto.BankAccountDTO;
import com.infy.exception.InfyMeMobileException;

public interface BankAccountService {

	Long createAccount(BankAccountDTO bankAccountDTO) throws InfyMeMobileException;

	List<BankAccountDTO> listAccount(Long mobileNo) throws InfyMeMobileException;

}
