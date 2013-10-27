<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign</title>
<!-- Bootstrap core CSS -->
<!--Online link to include bootstrap need not include any libraries in eclipse -->
<link href="//netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
	<div class="row">
	<div class="span3">
		<ul class="nav nav-list">
			<li class="nav-header">Links</li>
			<li>Add Links Here</li>
			
		</ul>
	</div>
	<div class="span9">
		<form>
		<table style="height: 121px; width: 605px;" align="center">
		<tr>

			<td>Validation status :</td>
			<td>Valid request</td>
			<td></td>
		</tr>
		<tr>
			<td>Assigned employee :</td>
			<td><select style="width: 238px; ">
			<option value="emailid1">employee1 | emailid1</option>
  			<option value="emailid2">employee2 | emailid2</option>
  			<option value="emailid3">employee3 | emailid3</option>
  			<option value="emailid4">employee4 | emailid4</option>
			</select></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td><button class="btn btn-primary" type="submit" style="width: 118px;"	name="Assign">Assign</button></td>
			<td><button class="btn btn-danger" type="submit" style="width: 118px;" name="Reject">Reject</button></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	</form>
</div>
</div>
</div>
</body>
</html>