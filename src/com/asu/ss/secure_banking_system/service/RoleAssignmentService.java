package com.asu.ss.secure_banking_system.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.asu.ss.secure_banking_system.model.RequestEntity;
import com.asu.ss.secure_banking_system.model.RoleRequestEntity;
import com.asu.ss.secure_banking_system.model.RoleType;
import com.asu.ss.secure_banking_system.model.SessionFactoryUtil;
import com.sbs.model.user.User;

public class RoleAssignmentService {

	
	public boolean checkUserHierarchy(User assigned, User assignee, int roleID)
	{
		
		if(roleID == RoleType.CORPORATE_LEVEL_OFFICER.getValue())
		{
			return false;
		}
		else if(assignee.getRoleID() == RoleType.CORPORATE_LEVEL_OFFICER.getValue()
				&& ( assignee.getRoleID() == RoleType.SYSTEM_ADMIN.getValue()
					|| assignee.getRoleID() == RoleType.REGULAR_EMPLOYEE.getValue()
					|| assignee.getRoleID() == RoleType.MANAGER.getValue()
					|| assignee.getRoleID() == RoleType.EXTERNAL_MERCHANT_USER.getValue()
					|| assignee.getRoleID() == RoleType.EXTERNAL_REGULAR_USER.getValue()
					))
		{
			return true;
		}
		else if(assignee.getRoleID()==RoleType.MANAGER.getValue()
				&& (   assigned.getRoleID() == RoleType.SYSTEM_ADMIN.getValue()
					|| assigned.getRoleID() == RoleType.REGULAR_EMPLOYEE.getValue()
					|| assigned.getRoleID() == RoleType.EXTERNAL_MERCHANT_USER.getValue()
					|| assigned.getRoleID() == RoleType.EXTERNAL_REGULAR_USER.getValue()))
		{
			return true;
		}
		else if(assignee.getRoleID() == RoleType.REGULAR_EMPLOYEE.getValue()
				&& (  assigned.getRoleID() == RoleType.EXTERNAL_MERCHANT_USER.getValue()
					|| assigned.getRoleID() == RoleType.EXTERNAL_REGULAR_USER.getValue()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean checkIfMultipleAuthorizationSatisfied(User assignee, List<RequestEntity> coAssignee)
	{
		int similarLevelCoAssignee = 0;
		
		for(int i=0; i<coAssignee.size();i++)
		{
			if(assignee.getRoleID()==coAssignee.get(i).getRequestedBy().getRoleID())
			{
				similarLevelCoAssignee++;
			}
		}
		if(similarLevelCoAssignee>=3)
			return true;
		else
			return false;
		
	}
	
	private void updateRoleRequestEntity(RoleRequestEntity ree)
	{
		try{
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(ree);
		session.getTransaction().commit();
		session.close();
				}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
	}
	
	public void assignRoleToUser(RoleRequestEntity re)
	{
		try{
			if(re.isValidated()==true)
			{
				SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				re.getRequestForUser().setRoleID(re.getRole());
				session.save(re.getRequestForUser());
				session.getTransaction().commit();
				session.close();
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public boolean isValidRequest(RoleRequestEntity re)
	{
		try{
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM RoleRequestEntity RE WHERE RE.isValidated = false AND RE.requestForUser = '"
				+re.getRequestForUser().getUserID()+"' AND RE.roleID = "+re.getRole();
		Query query = session.createQuery(hql);
		List<RequestEntity> requestList = query.list();
		
		hql = "FROM User U WHERE U.userID = '"+re.getRequestedBy().getUserID()+"'";
		
		User requestingUser = (User)(session.createQuery(hql).list()).get(0);
				
		hql = "FROM User U WHERE U.userID = '"+re.getRequestForUser().getUserID()+"'";
		
		User requestforUser = (User)(session.createQuery(hql).list()).get(0);
		
		//check if the request came from higher level user than the user to be assigned
		if(checkUserHierarchy(requestforUser, requestingUser, re.getRole()))
		{
			re.setValidated(true);
			updateRoleRequestEntity(re);
			
		}
		else
		{
			
			if(checkIfMultipleAuthorizationSatisfied(re.getRequestedBy(), requestList)==true)
			{
				re.setValidated(true);
				
				for(int i=0; i<requestList.size();i++)
				{
					((RoleRequestEntity)requestList.get(i)).setValidated(true);
					updateRoleRequestEntity(((RoleRequestEntity)requestList.get(i)));
				}
				return true;
			}
		}
		//check if atleast two other similar request came from same level user
		
		session.getTransaction().commit();
		session.close();
		
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return false;
	}
}
