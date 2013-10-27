package com.asu.ss.secure_banking_system.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.asu.ss.secure_banking_system.model.SessionFactoryUtil;
import com.asu.ss.secure_banking_system.model.TAARequestEntity;
import com.sbs.model.user.User;

public class TAAAssignmentService {

	public List<User> getAllInternalUsers()
	{
		RequestRoleService svc = new RequestRoleService();
		return svc.getAllInternalUsers();
	}
	
	public void assignTAAtoUser(TAARequestEntity taa, User user)
	{
		try{
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		taa.setAssigned(true);
		Query query = session.createQuery("update TAARequestEntity set assignedTo = :assignedTo");
		query.setParameter("assignedTo", user);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
}
