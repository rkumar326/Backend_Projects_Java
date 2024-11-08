package com.infy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.BankAccountDTO;
import com.infy.entity.BankAccountEntity;
import com.infy.exception.InfyMeMobileException;
import com.infy.repository.BankAccountRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Override
	public Long createAccount(BankAccountDTO bankAccountDTO) throws InfyMeMobileException {
		BankAccountEntity bankAccountEntity = new BankAccountEntity();
		bankAccountEntity.setAccountNumber(bankAccountDTO.getAccountNumber());
		bankAccountEntity.setAccountType(bankAccountDTO.getAccountType());
		bankAccountEntity.setBalance(bankAccountDTO.getBalance());
		bankAccountEntity.setBankName(bankAccountDTO.getBankName());
		bankAccountEntity.setIfscCode(bankAccountDTO.getIfscCode());
		bankAccountEntity.setMobileNumber(bankAccountDTO.getMobileNumber());
		bankAccountEntity.setOpeningDate(bankAccountDTO.getOpeningDate());
		bankAccountRepository.save(bankAccountEntity);
		return bankAccountEntity.getAccountNumber();
	}

	@Override
	public List<BankAccountDTO> listAccount(Long mobileNo) throws InfyMeMobileException {
		List<BankAccountEntity> bankAccountEntities = bankAccountRepository.findAll();
		
		if(bankAccountEntities.isEmpty()) {
			throw new InfyMeMobileException("NO_ACCOUNTS_FOUND.");
		}
		
		List<BankAccountDTO> bankAccountDTOs = new ArrayList<BankAccountDTO>();
		bankAccountEntities.forEach(bankAccount -> {
			if(bankAccount.getMobileNumber().equals(mobileNo)) {
				BankAccountDTO bankAccountDTO = new BankAccountDTO();
				bankAccountDTO.setAccountNumber(bankAccount.getAccountNumber());
				bankAccountDTO.setAccountType(bankAccount.getAccountType());
				bankAccountDTO.setBalance(bankAccount.getBalance());
				bankAccountDTO.setBankName(bankAccount.getBankName());
				bankAccountDTO.setIfscCode(bankAccount.getIfscCode());
				bankAccountDTO.setOpeningDate(bankAccount.getOpeningDate());
				bankAccountDTO.setMobileNumber(bankAccount.getMobileNumber());
				bankAccountDTOs.add(bankAccountDTO);
			}
		});
		return bankAccountDTOs;
	}

	
	
}
