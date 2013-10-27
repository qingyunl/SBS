package com.asu.ss.secure_banking_system.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Embeddable
class TransactionKey implements Serializable{

	@Column(name = "tran_id")
	@TableGenerator(name = "Tran_Gen" , table = "ID_GEN" , initialValue = 3)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tran_Gen")
	private long transactionID;
	
	@Column(name = "tran_type")
	private char tranType;

	/**
	 * @return the transactionID
	 */
	public long getTransactionID() {
		return transactionID;
	}

	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}

	/**
	 * @return the tranType
	 */
	public char getTranType() {
		return tranType;
	}

	/**
	 * @param tranType the tranType to set
	 */
	public void setTranType(char tranType) {
		this.tranType = tranType;
	}
	
}
@Entity
@Table(name="TRAN_DTLS")
public class TransactionEntity {

@EmbeddedId
private TransactionKey transactionKey;
@Column(name = "tran_date")
private Date tranDate;

@Column(name = "account_id", length = 10)
private String accountId;

@Column(name = "tran_amt")
private double tranAmount;

@Column(name = "user_id",length = 15)
private String userID;

@Column(name = "tran_created_by_user",length = 15)
private String tranCreatedByUser;

@Column(name = "balance_after_tran")
private double balance;
/**
 * @return the transactionKey
 */
public TransactionKey getTransactionKey() {
	return transactionKey;
}

/**
 * @param transactionKey the transactionKey to set
 */
public void setTransactionKey(TransactionKey transactionKey) {
	this.transactionKey = transactionKey;
}

/**
 * @return the tranDate
 */
public Date getTranDate() {
	return tranDate;
}

/**
 * @param tranDate the tranDate to set
 */
public void setTranDate(Date tranDate) {
	this.tranDate = tranDate;
}

/**
 * @return the accountId
 */
public String getAccountId() {
	return accountId;
}

/**
 * @param accountId the accountId to set
 */
public void setAccountId(String accountId) {
	this.accountId = accountId;
}

/**
 * @return the tranAmount
 */
public double getTranAmount() {
	return tranAmount;
}

/**
 * @param tranAmount the tranAmount to set
 */
public void setTranAmount(double tranAmount) {
	this.tranAmount = tranAmount;
}

/**
 * @return the userID
 */
public String getUserID() {
	return userID;
}

/**
 * @param userID the userID to set
 */
public void setUserID(String userID) {
	this.userID = userID;
}

/**
 * @return the tranCreatedByUser
 */
public String getTranCreatedByUser() {
	return tranCreatedByUser;
}

/**
 * @param tranCreatedByUser the tranCreatedByUser to set
 */
public void setTranCreatedByUser(String tranCreatedByUser) {
	this.tranCreatedByUser = tranCreatedByUser;
}

/**
 * @return the balance
 */
public double getBalance() {
	return balance;
}

/**
 * @param balance the balance to set
 */
public void setBalance(double balance) {
	this.balance = balance;
}




	
}
