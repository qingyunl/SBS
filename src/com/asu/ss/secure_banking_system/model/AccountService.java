package com.asu.ss.secure_banking_system.model;

import org.apache.catalina.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;



public class AccountService {

	private String accountID;
	private AccountEntity accEntity;
	private String userID;

	/**
	 * @return the accountID
	 */
	
	
	public String getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
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


	public AccountEntity getAccountDetails(String accountID) throws Exception
	{
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			System.out.println("account = "+accountID);
			this.accEntity = (AccountEntity)session.get(AccountEntity.class, accountID);
			if(accEntity == null)
				throw new Exception("Invalid Account passed ["+accountID+"]");
		}
		catch(Exception e)
		{
			System.out.println("error satya in Account Service : "+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally {
			session.close();
		}
		
		return this.accEntity;
	}
	/* updates the balance  */
	public AccountEntity updateBalance(String accountID,double tranAmount,char typeOfUpdate) throws Exception
	{
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			this.accEntity = (AccountEntity)session.get(AccountEntity.class, accountID);
			if(accEntity == null)
				throw new Exception("Invalid Account passed ["+accountID+"]");

		}
		catch(Exception  e)
		{
			System.out.println("error satya in Account Service1 : "+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally {
			session.close();
		}
		double availableBalance = 0.0;

		if(typeOfUpdate == 'C')
		{
			availableBalance = accEntity.getAcctBalance() + tranAmount;
		}
		else if(typeOfUpdate == 'D')
		{
			availableBalance = accEntity.getAcctBalance() - tranAmount;
			if(availableBalance < 0.00)
			{
				throw new Exception("Balance is Insufficient");
			}

		}
		accEntity.setAcctBalance(availableBalance);

		return accEntity;

	}
}
