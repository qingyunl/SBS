<%@page import="com.asu.ss.secure_banking_system.service.TAANotificationService"%>
<%@page import="com.sbs.model.user.User"%>
<%@page import="com.asu.ss.secure_banking_system.model.TAARequestEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee notifcation page for technical account access</title>
</head>
<body>

	<form action="ProcessTAARequest" method="post">
		<table>
			<tr>

				<td><h4>Technical account access requests assigned to you</h4></td><td></td>
			</tr>

			<%
				TAANotificationService svc =  new  TAANotificationService();
				User user = new User();
				user.setUserID(session.getAttribute("UserID").toString());
				List<TAARequestEntity> requests = svc.getAllTAARequestsForUser(user);
				for(int i=0;i<requests.size();i++)
				{
			%> 
			<tr>			
				<td><%= requests.get(i).getDescription()%></td>
				<td><input type="submit" align="left" value="view details"
				onclick="<% session.setAttribute("requestToProcess", requests.get(i)); %>>"></td>
			</tr>				
			<% 
				}
			%>	

		</table>
	</form>
</body>
</html>