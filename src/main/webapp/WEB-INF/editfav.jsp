<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Edit Favorite Drink</title>
</head>
<body>

<h3><a href="/home">Home</a></h3>
<h3><a href="/populardrinks">Back</a></h3>

<h1>Edit Favorite Drink</h1>

<form:form action="/favdrink/edit/${drink.id}" method="post" modelAttribute="drink">

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
	            <td class="float-left">Brand:</td>
	            <td class="float-left">
	            	<form:errors path="brand" class="text-danger"/>
					<form:input path="brand" class="text-danger" value="${drink.brand}"/>
	            </td>
	        </tr>
	        
	        <tr>
	            <td class="float-left">Alcohol Percentage:</td>
	            <td class="float-left">
	            	<form:errors path="alcPercent" class="text-danger"/>
					<form:input path="alcPercent" class="text-danger" value="${drink.alcPercent}"/>
	            </td>
	        </tr>
	      	
	      	<tr>
	            <td class="float-left">Taste Description:</td>
	            <td class="float-left">
	            	<form:errors path="description" class="text-danger"/>
					<form:input path="description" class="text-danger" value="${drink.description}"/>
	            </td>
	        </tr>
	       
	        
	        <tr>
	        	<td><a class="linkBtn" href="/populardrinks">Cancel</a></td>
	        	<td><input class="input" type="submit" value="Save"/></td>
	        </tr>
	    </thead>
	</table>
</form:form>
</body>
</html>