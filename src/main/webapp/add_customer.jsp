<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddForm</title>
</head>
<body>
<h1>Form to add to new Customer</h1>
	<form action="CustomerServlet" method="post">
	
		<input name="action" value="ADD" type="text" hidden="true">
		<label for="firstName">FirstName</label>
		<input id="firstName" name="firstName" type="text" />
		
		<br/><br/>
		
		<label for="lastName">LastName</label>
		<input id="lastName" name="lastName" type="text" />
		
		<br/><br/>
		
		<input type="submit" value="Submit" />
	</form>
</body>
</html>