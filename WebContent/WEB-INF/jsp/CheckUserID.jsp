<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Check User ID</title>
<!-- Bootstrap core CSS -->
<!--Online link to include bootstrap need not include any libraries in eclipse -->
<link href="//netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
<h1><a href="#">User ID</a></h1>
<div class="row">
         <div class="span3">
             <ul class="nav nav-list">
             	<li class="nav-header">Other Links</li>
             	<li>Add Links Here</li>
             </ul>
         </div>
      	<div class="span9">
      	<div id ="mainFrame">
		<div id="content">
		<form:form action = "checkuserid.html" method ="post" commandName="user" >
		<table>
		<tr>
			<td><p> Please Enter Your UserID </p></td>
  			<td><form:input path="userID" type="text"  /></td>
  			
		</tr>
		<tr>
			
			<td><input  class="btn btn-success" type="submit" value="Enter" /></td>
		</tr>
		</table>
		</form:form> 
		</div>
		</div>
		</div>
</div>
</div>
</body>
</html>


