package com.sbs.model.transaction;

public enum TransactionType {
	ADD_USER_ACCT("ADD_USR"),
	MOD_USER_ACCT("MOD_USR"),
	DEL_USER_ACCT("DEL_USR"),
	TFR_USER_ACCT("TFR_USR"),
	VIEW_USER_ACCT("VIEW_USR"),
	AUTH_REQUESTED("AUTH_REQUESTED"),
	AUTH_GIVEN("AUTH_GIVEN"),
	VIEW_INT_TXN("VIEW_INT_TXN"),
	VIEW_EXT_TXN("VIEW_EXT_TXN"),
	VIEW_SYS_LOG("VIEW_SYS_LOG"),
	PAYMENT_REQUESTED("PAYMENT_REQUESTED"),
	PAYMENT_GIVEN("PAYMENT_GIVEN"),
	PAYMENT_SUBMITTED("PAYMENT_SUBMITTED"),
	ADD_EXT_TXN("ADD_EXT_TXN"),
	MOD_EXT_TXN("MOD_EXT_TXN"),
	DEL_EXT_TXN("DEL_EXT_TXN"),
	USR_ROLE_CHANGED("USR_ROLE_CHANGED"),
	CREDIT("CREDIT"),
	DEBIT("DEBIT"),
	TRANSFER("TRANSFER");
	 
	private String txnTypeCode;
 
	private TransactionType(String s) {
		txnTypeCode = s;
	}
 
	public String getTxnTypeCode() {
		return txnTypeCode;
	}
}
