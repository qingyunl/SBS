package com.asu.ss.secure_banking_system.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.asu.ss.secure_banking_system.model.SessionFactoryUtil;
import com.asu.ss.secure_banking_system.model.TAARequestEntity;
import com.asu.ss.secure_banking_system.model.UserEntity;
import com.sbs.model.user.User;

public class TAARequestService {

	public User getUserVerfied(String username, String dob, String emailID)
	{
		//yet to be implemented
		
		User ue = new User();
		return ue;
	}
	
	public void createTAARequest(User user, String description)
	{
		try{
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//
		TAARequestEntity taa = new TAARequestEntity();
		taa.setAssigned(false);
		taa.setDescription(description);
		taa.setRequestedBy(user);
		taa.setValidated(false);
		taa.setRequestTime(new Date());
		session.save(taa);
		session.getTransaction().commit();
		session.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}

	}
	
	public boolean checkIfValidTAARequest(TAARequestEntity taa)
	{
		return true;
	}
	
}
