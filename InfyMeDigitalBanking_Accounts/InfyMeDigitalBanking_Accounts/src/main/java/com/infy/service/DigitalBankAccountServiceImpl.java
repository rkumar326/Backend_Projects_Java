package com.infy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.entity.BankAccountEntity;
import com.infy.entity.DigitalBankAccountEntity;
import com.infy.exception.InfyMeMobileException;
import com.infy.repository.BankAccountRepository;
import com.infy.repository.DigitalBankAccountRepository;

@Service
public class DigitalBankAccountServiceImpl implements DigitalBankAccountService {

	@Autowired
	private DigitalBankAccountRepository digitalBankAccountRepository;

	@Autowired
	private BankAccountRepository bankAccountRepository;

	BankAccountEntity bankEntity = new BankAccountEntity();

	@Override
	public String linkAccount(Long mobileNo, Long accountNo) throws InfyMeMobileException {

		List<BankAccountEntity> bankAccountEntities = bankAccountRepository.findByMobileNumber(mobileNo);
		if (bankAccountEntities.isEmpty()) {
			throw new InfyMeMobileException("NO_ACCOUNTS_FOUND");
		}

		bankAccountEntities.forEach(bankAccountEntity -> {
			if (bankAccountEntity.equals(accountNo)) {
				bankEntity.setAccountNumber(bankAccountEntity.getAccountNumber());
				bankEntity.setAccountType(bankAccountEntity.getAccountType());
				bankEntity.setBalance(bankAccountEntity.getBalance());
				bankEntity.setBankName(bankAccountEntity.getBankName());
				bankEntity.setIfscCode(bankAccountEntity.getIfscCode());
				bankEntity.setMobileNumber(bankAccountEntity.getMobileNumber());
				bankEntity.setOpeningDate(bankAccountEntity.getOpeningDate());
			}
		});

		System.out.println(bankEntity);

		DigitalBankAccountEntity digitalBankAccountEntity = new DigitalBankAccountEntity();
		digitalBankAccountEntity.setAccountNumber(bankEntity.getAccountNumber());
		digitalBankAccountEntity.setAccountType(bankEntity.getAccountType());
		digitalBankAccountEntity.setMobileNumber(bankEntity.getMobileNumber());
		digitalBankAccountEntity.setDigitalBankingId("w_1005");

		List<DigitalBankAccountEntity> digitalBankAccountEntities = digitalBankAccountRepository.findAll();
		for (var i : digitalBankAccountEntities) {
			if (i.equals(digitalBankAccountEntity)) {
				throw new InfyMeMobileException("Account already linked");
			}
		}

		digitalBankAccountRepository.save(digitalBankAccountEntity);
		return digitalBankAccountEntity.getDigitalBankingId();

	}


	@Override
	public Double getBalance(Long mobileNo, Long accountNo) throws InfyMeMobileException {
		List<DigitalBankAccountEntity> digitalBankAccountEntities = digitalBankAccountRepository.findAll();
		Boolean flag = false;
		for (var digitalBankAccountEntity : digitalBankAccountEntities) {
			if (digitalBankAccountEntity.getMobileNumber().equals(mobileNo)
					&& digitalBankAccountEntity.getAccountNumber().equals(accountNo)) {
				flag = true;
				break;
			}
		}

		if (flag) {
			Optional<BankAccountEntity> optional = bankAccountRepository.findById(accountNo);
			BankAccountEntity bankAccountEntity = optional
					.orElseThrow(() -> new InfyMeMobileException("Account not found"));

			return bankAccountEntity.getBalance();
//			List<BankAccountEntity> bankAccountEntities = bankAccountRepository.findAll();
//			for (var bankAccountEntity : bankAccountEntities) {
//				if (bankAccountEntity.equals(accountNo)) {
//					return bankAccountEntity.getBalance();
//				}
//			}
		} else {
			throw new InfyMeMobileException("NO_ACCOUNT_IS_LINKED.");
		}
	}

}
