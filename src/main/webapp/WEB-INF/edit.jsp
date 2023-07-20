<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Edit Drink</title>
</head>
<body>

<h2><a href="/home">Home</a></h2>

<h1>Edit Drink</h1>

<form:form action="/drink/edit/${drink.id}" method="post" modelAttribute="drink">

	<table>
	    <thead>
	    	<tr>
	            <td class="float-left">Spirit Type:</td>
	            <td class="float-left">
	            	<form:errors path="spiritType" class="text-danger"/>
					<form:input class="input" path="spiritType" value="${drink.spiritType}"/>
	            </td>
	        </tr>
	        
	        <tr>
	            <td class="float-left">Number of Drinks:</td>
	            <td class="float-left">
	            	<form:errors path="numOfDrinks" class="text-danger"/>
					<form:input path="numOfDrinks"  value="${drink.numOfDrinks}"/>
	            </td>
	        </tr>
	        
	        <tr>
	        	<td><a class="linkBtn" href="/home">Cancel</a></td>
	        	<td><input class="input" type="submit" value="Save"/></td>
	        </tr>
	    </thead>
	</table>
</form:form>
</body>
</html>