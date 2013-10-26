<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Delete User Account</title>
<!-- Bootstrap core CSS -->
<!--Online link to include bootstrap need not include any libraries in eclipse -->
<link href="//netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<h1><a href="#">Delete User</a></h1>
	<div class="navbar">
  		<div class="navbar-inner">
      	<ul class="nav nav-tabs">
      	<li><a href="addUser.html">Add User</a></li>
      	<li class="active"><a href="#">Delete User</a></li>
      	<li><a href="transferUserPage.html">Transfer User</a></li>
    	</ul>
  		</div>
	</div>
	<div class="row">
         <div class="span4">
             <ul class="nav nav-list">
             	<li class="nav-header">Other Links</li>
             	<li>Add Links Here</li>
             </ul>
         </div>
         <div class="span8">
          <div class="container">
			<form:form action = "deleteUser.html" method ="post" commandName="user" >
               <table>
    			<tr>
    				<td align="right">User ID:</td>
    				<td align="left"><form:input path="userID" type="text"/></td>
    			</tr>
    			<tr>
    				<td align="justify"><input  class="btn btn-success" type="submit" value="delete" /></td>
    				<td align="justify"><input  class="btn" type="submit" value="Cancel"/></td>
    			</tr>
    		</table>	
    		</form:form> 
		</div>
     </div>
     </div>
	
	
	
	</div>
</body>
</html>