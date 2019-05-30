<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UpdateForm</title>
</head>
<body>
<h1>Update your data</h1>
<form action="CustomerServlet" method="post">
	
		<input name="action" value="UPDATE" type="text" hidden="true">
		<input name="id" value="${customer.id}" hidden="true" />
		<label for="firstName">FirstName</label>
		<input id="firstName" name="firstName" type="text" value="${customer.firstName}"/>
		
		<br/><br/>
		
		<label for="lastName">LastName</label>
		<input id="lastName" name="lastName" type="text" value="${customer.lastName}"/>
		
		<br/><br/>
		
		<input type="submit" value="Submit" />
	</form>

</body>
</html>