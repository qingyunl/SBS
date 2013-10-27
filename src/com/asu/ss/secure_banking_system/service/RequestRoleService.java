package com.asu.ss.secure_banking_system.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.asu.ss.secure_banking_system.model.RequestEntity;
import com.asu.ss.secure_banking_system.model.RoleEntity;
import com.asu.ss.secure_banking_system.model.RoleRequestEntity;
import com.asu.ss.secure_banking_system.model.RoleType;
import com.asu.ss.secure_banking_system.model.SessionFactoryUtil;
import com.asu.ss.secure_banking_system.model.UserEntity;
import com.sbs.model.user.User;


public class RequestRoleService {

	public List<User> getAllInternalUsers()
	{
		try{
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM User UE";
		Query query = session.createQuery(hql);
		List<User> requestList = query.list();
		session.getTransaction().commit();
		session.close();
		return requestList;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return null;
		
	}
	public void createRoleRequest(RoleEntity role, UserEntity user)
	{
		try{
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();		
		RoleRequestEntity re = new RoleRequestEntity();
		UserEntity ue = new UserEntity();
		ue.setUserID("abcdeew");
		re.setRequestedBy(ue);
		re.setRequestID(111);
		re.setRequestTime(new Date());
		re.setRole(role);
		re.setUser(user);
		session.save(re);
		session.getTransaction().commit();
		session.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}	
	}
	
	public RoleType[] getAllRoles()
	{
		try{
		
		return RoleType.values();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return null;
		
	}	
}
