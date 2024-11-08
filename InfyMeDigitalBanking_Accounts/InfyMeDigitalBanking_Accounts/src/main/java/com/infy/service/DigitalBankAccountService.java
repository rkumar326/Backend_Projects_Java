package com.infy.service;

import com.infy.exception.InfyMeMobileException;

public interface DigitalBankAccountService {

	String linkAccount(Long mobileNo, Long accountNo) throws InfyMeMobileException;

	Double getBalance(Long mobileNo, Long accountNo) throws InfyMeMobileException;

}
