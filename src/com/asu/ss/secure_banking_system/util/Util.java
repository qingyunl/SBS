package com.asu.ss.secure_banking_system.util;

import com.asu.ss.secure_banking_system.model.RoleType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.asu.ss.secure_banking_system.model.PageAuthorization;
import com.asu.ss.secure_banking_system.model.RoleType;
import com.asu.ss.secure_banking_system.model.SessionFactoryUtil;
import com.sbs.model.user.User;

public class Util {

	public static User getUserAuthorizationLoaded(User user)
	{
		if(user.getPageAuthorizations()==null)
			user.setPageAuthorizations(new PageAuthorization());
		
		user.getPageAuthorizations().isAssignRoleAccessible();
		
	}
	
	public static User getUser(String userID)
	{
		User user = null;
		try{
			SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			user = (User)session.get(User.class,"userID");
			session.getTransaction().commit();
			session.close();
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
			finally
			{
				
			}
		return user;
	}
	
	public static String getStringRoleType(RoleType type)
	{		
		
		switch(type)
		{
			case EXTERNAL_MERCHANT_USER: 
				return "Merchant user";
				
			case EXTERNAL_REGULAR_USER: 
				return "Regular user";
				
			case SYSTEM_ADMIN: 
				return "System administrator";
				
			case REGULAR_EMPLOYEE: 
				return "Regular employee";
				
			case MANAGER: 
				return "Manager";
				
			case CORPORATE_LEVEL_OFFICER: 
				return "Corporate level officer";
				
			default:
				return "";
				
		}
	}
}
