package com.infy.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "digital_bank_account")
public class DigitalBankAccountEntity {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String digitalBankingId;

//	@Column(unique = true)
	private Long mobileNumber;

//	@Column(unique = true)
	private Long accountNumber;
	private String accountType;

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
	public int hashCode() {
		return Objects.hash(accountNumber, accountType, digitalBankingId, mobileNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DigitalBankAccountEntity other = (DigitalBankAccountEntity) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(accountType, other.accountType)
				&& Objects.equals(digitalBankingId, other.digitalBankingId)
				&& Objects.equals(mobileNumber, other.mobileNumber);
	}

	@Override
	public String toString() {
		return "DigitalBankAccount [digitalBankingId=" + digitalBankingId + ", mobileNumber=" + mobileNumber
				+ ", accountNumber=" + accountNumber + ", accountType=" + accountType + "]";
	}

}
