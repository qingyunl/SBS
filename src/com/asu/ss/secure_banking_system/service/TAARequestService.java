package com.asu.ss.secure_banking_system.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.asu.ss.secure_banking_system.model.SessionFactoryUtil;
import com.asu.ss.secure_banking_system.model.TAARequestEntity;
import com.asu.ss.secure_banking_system.model.UserEntity;

public class TAARequestService {

	public UserEntity getUserVerfied(String username, String dob, String emailID)
	{
		//yet to be implemented
		
		UserEntity ue = new UserEntity();
		ue.setUserID("abcdeew");
		return ue;
	}
	
	public void createTAARequest(UserEntity user, String description)
	{
		try{
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//
		TAARequestEntity taa = new TAARequestEntity();
		taa.setAssigned(false);
		taa.setDescription(description);
		UserEntity ue = new UserEntity();
		ue.setUserID("abcdeew");
		taa.setRequestedBy(ue);
		taa.setValidated(false);
		taa.setRequestID(999);
		taa.setRequestTime(new Date());
		
		
		session.save(taa);
		session.getTransaction().commit();
		session.close();
		}
		catch(Exception exception)
		{
			
		}

	}
	
}
