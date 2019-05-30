<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8 /">
<title>HomePage</title>
</head>
<body>
	<h2>Welcome to your home page</h2>
	<c:url var="addCustomer" value="CustomerServlet">
		<c:param name="action" value="ADD_FORM"></c:param>
	</c:url>
	<c:url var="listCustomers" value="CustomerServlet">
		<c:param name="action" value="LIST"></c:param>
	</c:url>
	<a href="${addCustomer}">Add Customer</a>
	<a href="${listCustomers}">Display all customers</a>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>FisrtName</th>
				<th>LastName</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${customers != null }">
				<c:forEach items="${customers }" var="customer">
					<c:url var="updateCustomer" value="CustomerServlet">
						<c:param name="action" value="UPDATE_FORM"></c:param>
						<c:param name="customerId" value="${customer.id}"></c:param>
					</c:url>
					<c:url var="deleteCustomer" value="CustomerServlet">
						<c:param name="action" value="DELETE"></c:param>
						<c:param name="customerId" value="${customer.id}"></c:param>
					</c:url>
					<tr>
						<td>${customer.id}</td>
						<td>${customer.firstName }</td>
						<td>${customer.lastName }</td>
						<td><a href="${updateCustomer}">UPDATE</a>  |  <a href="${deleteCustomer}">DELETE</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</body>
</html>
