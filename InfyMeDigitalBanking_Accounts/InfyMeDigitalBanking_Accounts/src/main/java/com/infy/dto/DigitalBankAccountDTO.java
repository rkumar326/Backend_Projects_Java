package com.infy.dto;

import jakarta.validation.constraints.NotNull;

public class DigitalBankAccountDTO {
	private String digitalBankingId;
	@NotNull // Foreign key
	private Long mobileNumber;
	@NotNull // Foreign key
	private Long accountNumber;
	@NotNull
	private String accountType;

	public DigitalBankAccountDTO() {
		super();
	}

	public DigitalBankAccountDTO(String digitalBankingId, Long mobileNumber, Long accountNumber, String accountType) {
		super();
		this.digitalBankingId = digitalBankingId;
		this.mobileNumber = mobileNumber;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
	}

	public String getDigitalBankingId() {
		return digitalBankingId;
	}

	public void setDigitalBankingId(String digitalBankingId) {
		this.digitalBankingId = digitalBankingId;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "DigitalBankAccountDTO [digitalBankingId=" + digitalBankingId + ", mobileNumber=" + mobileNumber
				+ ", accountNumber=" + accountNumber + ", accountType=" + accountType + "]";
	}

}
