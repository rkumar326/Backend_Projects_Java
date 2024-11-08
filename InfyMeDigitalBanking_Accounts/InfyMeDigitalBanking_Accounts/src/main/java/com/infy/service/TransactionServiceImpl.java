package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.TransactionDTO;
import com.infy.entity.BankAccountEntity;
import com.infy.entity.TransactionEntity;
import com.infy.exception.InfyMeMobileException;
import com.infy.repository.BankAccountRepository;
import com.infy.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Override
	public List<TransactionDTO> accountStatement(Long mobileNo) throws InfyMeMobileException {
		List<TransactionEntity> transactionEntities = transactionRepository.findAll();

		if (transactionEntities.isEmpty()) {
			throw new InfyMeMobileException("NO_ACTIVE_TRANSACTIONS");
		}

		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		transactionEntities.forEach(t -> {
			if (t.getPaidFrom().equals(mobileNo)) {
				TransactionDTO transactionDTO = new TransactionDTO();
				transactionDTO.setAmount(t.getAmount());
				transactionDTO.setModeOfTransaction(t.getModeOfTransaction());
				transactionDTO.setPaidFrom(t.getPaidFrom());
				transactionDTO.setPaidTo(t.getPaidTo());
				transactionDTO.setReceiverAccountNumber(t.getReceiverAccountNumber());
				transactionDTO.setRemarks(t.getRemarks());
				transactionDTO.setSenderAccountNumber(t.getSenderAccountNumber());
				transactionDTO.setTransactionDateTime(t.getTransactionDateTime());
				transactionDTO.setTransactionId(t.getTransactionId());
				transactionDTOs.add(transactionDTO);
			}
		});
		return transactionDTOs;
	}

	@Override
	public String fundTransfer(TransactionDTO transactionDTO) throws InfyMeMobileException {
		Optional<BankAccountEntity> optional = bankAccountRepository.findById(transactionDTO.getSenderAccountNumber());
		BankAccountEntity bankAccountEntity = optional.orElseThrow(() -> new InfyMeMobileException("NO_ACCOUNT_FOUND"));

		if (transactionDTO.getAmount() > bankAccountEntity.getBalance()) {
			throw new InfyMeMobileException("INSUFFICIENT_FUNDS");
		} else {
			Optional<BankAccountEntity> optional2 = bankAccountRepository
					.findById(transactionDTO.getReceiverAccountNumber());
			BankAccountEntity recevier = optional2
					.orElseThrow(() -> new InfyMeMobileException("RECEIVER_ACCOUNT_NOT_FOUND"));

			Optional<BankAccountEntity> optional3 = bankAccountRepository
					.findById(transactionDTO.getSenderAccountNumber());
			BankAccountEntity sender = optional3
					.orElseThrow(() -> new InfyMeMobileException("SENDER_ACCOUNT_NOT_FOUND"));

			recevier.setBalance(recevier.getBalance() + transactionDTO.getAmount());
			sender.setBalance(sender.getBalance() - transactionDTO.getAmount());
		}

		TransactionEntity transactionEntity = new TransactionEntity();
		transactionEntity.setAmount(transactionDTO.getAmount());
		transactionEntity.setModeOfTransaction(transactionDTO.getModeOfTransaction());
		transactionEntity.setPaidFrom(transactionDTO.getPaidFrom());
		transactionEntity.setPaidTo(transactionDTO.getPaidTo());
		transactionEntity.setReceiverAccountNumber(transactionDTO.getReceiverAccountNumber());
		transactionEntity.setRemarks(transactionDTO.getRemarks());
		transactionEntity.setSenderAccountNumber(transactionDTO.getSenderAccountNumber());
		transactionEntity.setTransactionDateTime(transactionDTO.getTransactionDateTime());
		transactionEntity.setTransactionId(transactionDTO.getTransactionId());
		transactionRepository.save(transactionEntity);
		return "Fund transfer successfull, with Transaction id : " + transactionEntity.getTransactionId();
	}
}
