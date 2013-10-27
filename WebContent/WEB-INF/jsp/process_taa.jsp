<%@page import="com.asu.ss.secure_banking_system.model.TransactionEntity"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.asu.ss.secure_banking_system.service.ProcessTAAService"%>
<%@page import="com.sbs.model.user.User"%>
<%@page import="com.asu.ss.secure_banking_system.model.TAARequestEntity"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Technical account acccess processing page for employee</title>
</head>
<body>

	<% 
		ProcessTAAService taaService = new  ProcessTAAService();
		User user = (User)session.getAttribute("userToProcess");
		user = taaService.getUserDetails(user);
		List<TransactionEntity> transactions = taaService.getTransactionDetails(user);
	%>
	<table>
		<tr>
			<td style="width: 214px;"><h4>
					Profile information details
					<h4></td>
		</tr>
		<tr>
			<td>First name :<%= user.getFirstname()%>></td>
		</tr>
		<tr>
			<td>Last name:<%= user.getLastname()%></td>
		</tr>
		<tr>
			<td>Address:<%= user.getAddress() %></td>
		</tr>
		<tr>
			<td>Contact:<%= user.getContact()%></td>
		</tr>
		<tr>
			<td>Email id:<%= user.getEmailid()%></td>
		</tr>
		<tr>
			<td>Date of birth:<%= user.getDOB()%></td>
		</tr>
		<tr>
			<td>ID type:<%= user.getIdtype()%></td>
		</tr>
		<tr>
			<td>ID number:<%= user.getIdNo()%></td>
		</tr>
	</table>
	<table>
		<tr>
			<td><h4>Transaction information details</h4></td>
		</tr>

	</table>
	<table>
		<tr>
			<td>Transaction type</td>
			<td>Transaction date</td>
			<td>From account</td>
			<td>To account</td>
			<td>From user</td>
			<td>To user</td>
			<td>Amount</td>
			<td>Balance</td>
			<td>Details</td>
		</tr>
		<% for(int i=0;i<transactions.size();i++)
			{
			%>
		
		<tr>
			<td><%= transactions.get(i).getTransactionKey()%></td>
			<td><%= transactions.get(i).getTranDate()%></td>
			<td><%= transactions.get(i).getAccountId()%></td>
			<td><%= transactions.get(i).getAccountId()%></td>
			<td><%= transactions.get(i).getTranCreatedByUser()%></td>
			<td><%= transactions.get(i).getTranCreatedByUser()%></td>
			<td><%= transactions.get(i).getTranAmount()%></td>
			<td><%= transactions.get(i).getBalance()%></td>
			<td><%= transactions.get(i).getTransactionKey()%></td>
		</tr>
		<%} %>
	</table>
	<br>
</body>
</html>