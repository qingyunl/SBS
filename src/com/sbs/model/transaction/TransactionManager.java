package com.sbs.model.transaction;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sbs.model.notification.Notification;
import com.sbs.model.user.SessionFactoryUtil;

public class TransactionManager {

	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static void createTransaction(Transaction t) {
		createSession();
		session.save(t);
		session.getTransaction().commit();
		session.close();
	}
	
	public static void askAuthorization() {
		/** Send Request **/
		
	}
	
	public static List<Transaction> getAllTransactions() {
		createSession();
		String hql = "from Transaction as t where 1=1";
		Query query = session.createQuery(hql);
		// query.setString("strID", strID);
		List<Transaction> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public static List<Transaction> getExtTransactions() {
		createSession();
		
		String hql = "select t.txnId, t.txnType, t.txnDate, t.fromUserId, t.toUserId, t.amount, t.balance from Transaction t where "
				+ "(t.txnType = 'CREDIT' and t.fromUserId = :userId) "
				+ "or (t.txnType = 'DEBIT' and t.toUserId = :userId) "
				+ "or (t.txnType = 'TRANSFER' and (t.fromUserId = :userId or t.toUserId = :userId))";
		Query query = session.createQuery(hql);
		query.setString("userId", "kdvyas");
		List<Transaction> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public static List<Transaction> getIntTransactions() {
		createSession();
		
		String hql = "from Transaction t where 1 = 1";
		Query query = session.createQuery(hql);
		//query.setString("userId", "kdvyas");
		List<Transaction> tlist = query.list();
		//hql = "from Notification n where n.requesterId = :requesterId and ( n.approvedDate = null or (day(current_date()) - day(n.approvedDate)) < n.timeoutDays)";
		hql = "from Notification n where n.requesterId = :requesterId";
		query = session.createQuery(hql);
		query.setString("requesterId", "requester");
		List<Notification> nlist = query.list();
		session.getTransaction().commit();
		session.close();
		for (Transaction t : tlist) {
			t.setAmount(0);
			t.setBalance(0);
			t.setFromAccountId(null);
			t.setToAccountId(null);
			String fromUserStatus = ""; // no notification 
			String toUserStatus = "";  // no notification
			if (t.getFromUserId() != null && !t.getFromUserId().equals("")) {
				for (Notification n : nlist) {
					if (n.getKey1() == t.getTxnId() && n.getUserId().equals(t.getFromUserId())) {
						//t.setStatus(n.getStatus());
						fromUserStatus = n.getStatus();
					}
				}
			} else {
				fromUserStatus = "X"; // fromUser not exists
			}
			if (t.getToUserId() != null && !t.getToUserId().equals("")) {
				for (Notification n : nlist) {
					if (n.getKey1() == t.getTxnId() && n.getUserId().equals(t.getToUserId())) {
						//t.setStatus(n.getStatus());
						toUserStatus = n.getStatus();
					}
				}
			} else {
				toUserStatus = "X"; // toUser not exists
			}
			
			if ((fromUserStatus.equals("A") && toUserStatus.equals("A")) 
					|| (fromUserStatus.equals("A") && toUserStatus.equals("X"))
					|| (fromUserStatus.equals("X") && toUserStatus.equals("A"))) {
				t.setStatus("A");
			} else if (fromUserStatus.equals("D") || toUserStatus.equals("D")) {
				t.setStatus("D");
			} else if (fromUserStatus.equals("R") || toUserStatus.equals("R")){
				t.setStatus("R");
			} 
			
			if (!t.getStatus().equals("A")) {
				t.setFromUserId("");
				t.setToUserId("");
			}
		}
		return tlist;
	}
	
	public static List<Transaction> getSystemLog() {
		createSession();
		
		String hql = "select t.txnId, t.txnType, t.txnDate, t.details from Transaction t where 1 = 1";
		Query query = session.createQuery(hql);
		//query.setString("userId", "kdvyas");
		List<Transaction> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public static Transaction getTransaction(int txnId) {
		createSession();
		String hql = "from Transaction t where txnId = :txnId";
		Query query = session.createQuery(hql);
		query.setInteger("txnId", txnId);
		List<Transaction> tList = query.list();
		session.getTransaction().commit();
		session.close();
		if (tList.size() > 0) {
			return tList.get(0);
		} else return null;
	}
}
