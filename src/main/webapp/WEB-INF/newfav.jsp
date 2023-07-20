<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>New Favorite Drink</title>
</head>
<body>

<h3><a href="/home">Home</a></h3>
<h3><a href="/populardrinks">Back</a></h3>


<h1>Add a Favorite Drink</h1>

<form:form action="/favdrinks/new" method="post" modelAttribute="drink">

	<table>
	    <thead>
	    	<tr>
	            <td class="float-left">Spirit Type:</td>
	            <td class="float-left">
	            	<form:errors path="spiritType" class="text-danger"/>
					<form:input class="input" path="spiritType"/>
	            </td>
	        </tr>
	        
	        <tr>
	            <td class="float-left">Brand:</td>
	            <td class="float-left">
	            	<form:errors path="brand" class="text-danger"/>
					<form:input path="brand" class="text-danger"/>
	            </td>
	        </tr>
	        
	        <tr>
	            <td class="float-left">Alcohol Percentage:</td>
	            <td class="float-left">
	            	<form:errors path="alcPercent" class="text-danger"/>
					<form:input path="alcPercent" class="text-danger"/>
	            </td>
	        </tr>
	      	
	      	<tr>
	            <td class="float-left">Taste Description:</td>
	            <td class="float-left">
	            	<form:errors path="description" class="text-danger"/>
					<form:input path="description" class="text-danger"/>
	            </td>
	        </tr>
	       
	        
	        <form:errors path="drinker" class="error"/>
			<form:input type="hidden" path="drinker" value="${userId}" class="form-control"/>
			
	        <tr>
	        	<td><a class="linkBtn" href="/populardrinks">Cancel</a></td>
	        	<td><input class="input" type="submit" value="Save"/></td>
	        </tr>
	        
	    </thead>
	</table>
</form:form>
</body>
</html>