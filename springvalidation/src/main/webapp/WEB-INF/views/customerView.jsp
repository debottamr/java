<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Spring MVC Form Handling</title>
</head>
<body>

	<h2>Submitted Customer Information</h2>
	<table>
		<tr>
			<td>Customer Id :</td>
			<td>${customerId}</td>
		</tr>
		<tr>
			<td>Customer Name :</td>
			<td>${customerName}</td>
		</tr>
		<tr>
			<td>Customer Contact :</td>
			<td>${customerContact}</td>
		</tr>
		<tr>
			<td>Customer Email :</td>
			<td>${customerEmail}</td>
		</tr>
	</table>
</body>
</html>