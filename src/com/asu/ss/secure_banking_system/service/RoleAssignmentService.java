package com.asu.ss.secure_banking_system.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.asu.ss.secure_banking_system.model.RequestEntity;
import com.asu.ss.secure_banking_system.model.RoleRequestEntity;
import com.asu.ss.secure_banking_system.model.RoleType;
import com.asu.ss.secure_banking_system.model.SessionFactoryUtil;
import com.asu.ss.secure_banking_system.model.UserEntity;

public class RoleAssignmentService {

	
	public boolean checkUserHierarchy(UserEntity assigned, UserEntity assignee)
	{
		if(assigned.getRole().getRoleName().equals(RoleType.CORPORATE_LEVEL_OFFICER))
		{
			return false;
		}
		else if(assignee.getRole().getRoleName().equals(RoleType.CORPORATE_LEVEL_OFFICER)
				&& (   assignee.getRole().getRoleName().equals(RoleType.SYSTEM_ADMIN)
					|| assignee.getRole().getRoleName().equals(RoleType.REGULAR_EMPLOYEE)
					|| assignee.getRole().getRoleName().equals(RoleType.MANAGER)
					|| assignee.getRole().getRoleName().equals(RoleType.EXTERNAL_MERCHANT_USER)
					|| assignee.getRole().getRoleName().equals(RoleType.EXTERNAL_REGULAR_USER))
					)
		{
			return true;
		}
		else if(assignee.getRole().getRoleName().equals(RoleType.MANAGER)
				&& (   assigned.getRole().getRoleName().equals(RoleType.SYSTEM_ADMIN)
					|| assigned.getRole().getRoleName().equals(RoleType.REGULAR_EMPLOYEE)
					|| assigned.getRole().getRoleName().equals(RoleType.EXTERNAL_MERCHANT_USER)
					|| assigned.getRole().getRoleName().equals(RoleType.EXTERNAL_REGULAR_USER)))
		{
			return true;
		}
		else if(assignee.getRole().getRoleName().equals(RoleType.REGULAR_EMPLOYEE)
				&& (  assigned.getRole().getRoleName().equals(RoleType.EXTERNAL_MERCHANT_USER)
					|| assigned.getRole().getRoleName().equals(RoleType.EXTERNAL_REGULAR_USER)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean checkIfMultipleAuthorizationSatisfied(UserEntity assignee, List<RequestEntity> coAssignee)
	{
		int similarLevelCoAssignee = 0;
		
		for(int i=0; i<coAssignee.size();i++)
		{
			if(assignee.getRole().getRoleID()==coAssignee.get(i).getRequestedBy().getRole().getRoleID())
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
				//
				re.getRequestForUser().setRole(re.getRole());
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
				+re.getRequestForUser().getUserID()+"' AND RE.role = "+re.getRole().getRoleID();
		Query query = session.createQuery(hql);
		List<RequestEntity> requestList = query.list();
		
		//check if the request came from higher level user than the user to be assigned
		if(checkUserHierarchy(re.getRequestForUser(), re.getRequestedBy()))
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
