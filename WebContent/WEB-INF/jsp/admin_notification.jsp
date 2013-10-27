<%@page import="com.asu.ss.secure_banking_system.model.RoleRequestEntity"%>
<%@page import="com.asu.ss.secure_banking_system.model.TAARequestEntity"%>
<%@page import="com.asu.ss.secure_banking_system.service.AdminNotificationService"%>
<%@page import="com.asu.ss.secure_banking_system.model.RequestEntity"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin notification page</title>
<!-- Bootstrap core CSS -->
<!--Online link to include bootstrap need not include any libraries in eclipse -->
<link href="//netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">
<div class="hero-unit">
  		<div class="container">
    		<h1>System administrator notification page</h1>
    		<p>Notification page for system administrator</p>
    		<p><a class="btn btn-primary btn-lg">Learn more</a></p>
  		</div>
</div>
<div class="row">
	<div class="span3">
		<ul class="nav nav-list">
			<li class="nav-header">Links</li>
			<li>Add Links Here</li>
			
		</ul>
	</div>
	<div class="span9">
	<div class="container">
		<table>

			<tr >
			<td style="padding-left: 20px; padding-right: 20px"><b>Requester</b></td>
					<td style="padding-left: 20px; padding-right: 20px"><b>Request type</b></td>
					<td style="padding-left: 20px; padding-right: 20px"><b>Request details</b></td>
					<td style="padding-left: 20px; padding-right: 20px"><b>Action</b></td>
				</tr>
				
			<%
				AdminNotificationService ansvc = new AdminNotificationService();
			    ArrayList<RequestEntity> requestList = ansvc.getAllRequestes();
			    for(RequestEntity re: requestList)
			    {
			    	if(re.getClass()==RoleRequestEntity.class){
			 %>
		<form action="RoleRequestValidation" method="POST"> 
			<tr style="padding-left: 20px; padding-right: 20px">
			
				<td style="padding-left: 20px; padding-right: 20px"><%= re.getRequestedBy().getUserID()%></td>
				<td style="padding-left: 20px; padding-right: 20px">Role assignment</td>
				<td style="padding-left: 20px; padding-right: 20px">
				<%= ((RoleRequestEntity)re).getRole()%> to be assigned to <%= ((RoleRequestEntity)re).getRequestForUser().getUserID()%>
				</td>
				<td style="padding-left: 20px; padding-right: 20px"><button class="btn btn-primary" 
				onclick="<% session.setAttribute("RoleRequestToValidate", re);%>>" type="submit">Validate</button></td>
			</tr>
			</form>
			<%    	
			    }
			    	else if(re.getClass()==TAARequestEntity.class)
			    	{
			%>
			<form action="TAARequestValidation" method="POST"> 
			   <tr style="padding-left: 20px; padding-right: 20px">
				<td style="padding-left: 20px; padding-right: 20px"><%= re.getRequestedBy().getUserID()%></td>
				<td style="padding-left: 20px; padding-right: 20px">Technical account access</td>
				<td style="padding-left: 20px; padding-right: 20px"><%= ((TAARequestEntity)re).getDescription()%></td>
				<td style="padding-left: 20px; padding-right: 20px"><button class="btn btn-primary" type="submit"
				onclick="<% session.setAttribute("TAARequestToValidate", re);%>">Validate</button></td>
			</tr>
			</form>
			<%     	}
			    }
			%>

		</table>
	</div>
	</div>
</div>
</div>
</body>
</html>