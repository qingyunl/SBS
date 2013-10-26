<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Transfer User Account</title>
<!-- Bootstrap core CSS -->
<!--Online link to include bootstrap need not include any libraries in eclipse -->
<link href="//netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<h1><a href="#">Transfer User</a></h1>
	<div class="navbar">
  		<div class="navbar-inner">
      	<ul class="nav nav-tabs">
      	<li><a href="addUser.html">Add User</a></li>
      	<li><a href="deleteUserPage.html">Delete User</a></li>
      	<li class="active"><a href="#">Transfer User</a></li>
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
			<form:form action = "transferuser.html" method ="post" commandName="user" >
               <table>
               <tr>
               <td>
               <%= session.getAttribute("UserID")%> 
               </td>
               </tr>
    			<tr>
    				<td align="right">User ID:</td>
    				<td align="left"><form:input path="userID" type="text"/></td>
    			</tr>
    			<tr>
    				<td align="right">Destination Department:</td>
    				<td align="left"><form:input path="deptID" type="text"/></td>
    			</tr>
    			<tr>
    			
    				<td align="justify"><input  class="btn btn-success" type="submit" value="transfer"></td>
    				<td align="justify"><input  class="btn" type="reset" value="Cancel"></td>
    			</tr>
    		</table>	
    		</form:form>  
		</div>
     </div>
     </div>
	
	
	
	</div>
</body>
</html>