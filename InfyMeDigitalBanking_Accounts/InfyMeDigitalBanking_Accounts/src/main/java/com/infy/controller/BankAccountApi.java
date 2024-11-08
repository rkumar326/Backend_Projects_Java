package com.infy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.BankAccountDTO;
import com.infy.dto.TransactionDTO;
import com.infy.exception.InfyMeMobileException;
import com.infy.service.BankAccountService;
import com.infy.service.DigitalBankAccountService;
import com.infy.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@Validated
//@CrossOrigin
public class BankAccountApi {
//	private static final Log LOGGER = LogFactory.getLog(BankAccountApi.class);

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private DigitalBankAccountService digitalBankAccountService;

	// Working
	@PostMapping(value = "/accounts")
	public ResponseEntity<String> createAccount(@Valid @RequestBody BankAccountDTO bankAccountDTO)
			throws InfyMeMobileException {
		Long bankAccountNumber = bankAccountService.createAccount(bankAccountDTO);
		String msg = "Account created successfully with Account no : " + bankAccountNumber;
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	// Working
	@GetMapping(value = "/accounts/{mobileNo}")
	public ResponseEntity<List<BankAccountDTO>> listAccounts(@PathVariable Long mobileNo) throws InfyMeMobileException {
		List<BankAccountDTO> bankAccountDTOs = bankAccountService.listAccount(mobileNo);
		return new ResponseEntity<List<BankAccountDTO>>(bankAccountDTOs, HttpStatus.OK);
	}

	// NOT WORKING
//	This method is used to link a mobile number and respective bank account to a digital_banking__id without any OTP verification
	@PostMapping(value = "/accounts/{mobileNo}/{accountNo}")
	public ResponseEntity<String> linkAccount(@PathVariable Long mobileNo, @PathVariable Long accountNo)
			throws InfyMeMobileException {
		String digitalBankId = digitalBankAccountService.linkAccount(mobileNo, accountNo);
		String msg = "Your mobile number is successfully linked with Id : " + digitalBankId;
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

//	@PostMapping(value = "/accounts/{mobileNo}")
//	public ResponseEntity<String> linkAccount(@PathVariable Long mobileNo, Long accountNo, Integer otp) {
//		return null;
//
//	}

	// Working but not satisfied
	@GetMapping(value = "/accounts/balance/{mobileNo}/{accountNo}")
	public ResponseEntity<Double> checkBalance(@PathVariable Long mobileNo, @PathVariable Long accountNo)
			throws InfyMeMobileException {
		Double balance = digitalBankAccountService.getBalance(mobileNo, accountNo);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}

	// Working but transaction id is given by user
//	PUT/PATCH
	@PutMapping(value = "/accounts/fundtransfer")
	public ResponseEntity<String> fundTransfer(@RequestBody TransactionDTO transactionDTO)
			throws InfyMeMobileException {
		String msg = transactionService.fundTransfer(transactionDTO);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	// Working
	@GetMapping(value = "/accounts/statement/{mobileNo}")
	public ResponseEntity<List<TransactionDTO>> accountStatement(@PathVariable Long mobileNo)
			throws InfyMeMobileException {
		List<TransactionDTO> transactionDTOs = transactionService.accountStatement(mobileNo);
		return new ResponseEntity<List<TransactionDTO>>(transactionDTOs, HttpStatus.OK);
	}
}
