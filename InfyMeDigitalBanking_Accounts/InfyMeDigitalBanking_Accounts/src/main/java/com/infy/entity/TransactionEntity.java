package com.infy.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;
	private String modeOfTransaction; // Always 'Fund Transfer'
	private Long paidTo;
	private Long receiverAccountNumber;
	private Double amount;
	private LocalDateTime transactionDateTime = LocalDateTime.now();
	private String remarks;
	private Long paidFrom;
	private Long senderAccountNumber;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getModeOfTransaction() {
		return modeOfTransaction;
	}

	public void setModeOfTransaction(String modeOfTransaction) {
		this.modeOfTransaction = modeOfTransaction;
	}

	public Long getPaidTo() {
		return paidTo;
	}

	public void setPaidTo(Long paidTo) {
		this.paidTo = paidTo;
	}

	public Long getReceiverAccountNumber() {
		return receiverAccountNumber;
	}

	public void setReceiverAccountNumber(Long receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(LocalDateTime transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getPaidFrom() {
		return paidFrom;
	}

	public void setPaidFrom(Long paidFrom) {
		this.paidFrom = paidFrom;
	}

	public Long getSenderAccountNumber() {
		return senderAccountNumber;
	}

	public void setSenderAccountNumber(Long senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, modeOfTransaction, paidFrom, paidTo, receiverAccountNumber, remarks,
				senderAccountNumber, transactionDateTime, transactionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionEntity other = (TransactionEntity) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(modeOfTransaction, other.modeOfTransaction)
				&& Objects.equals(paidFrom, other.paidFrom) && Objects.equals(paidTo, other.paidTo)
				&& Objects.equals(receiverAccountNumber, other.receiverAccountNumber)
				&& Objects.equals(remarks, other.remarks)
				&& Objects.equals(senderAccountNumber, other.senderAccountNumber)
				&& Objects.equals(transactionDateTime, other.transactionDateTime)
				&& Objects.equals(transactionId, other.transactionId);
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", modeOfTransaction=" + modeOfTransaction + ", paidTo="
				+ paidTo + ", receiverAccountNumber=" + receiverAccountNumber + ", amount=" + amount
				+ ", transactionDateTime=" + transactionDateTime + ", remarks=" + remarks + ", paidFrom=" + paidFrom
				+ ", senderAccountNumber=" + senderAccountNumber + "]";
	}

}
