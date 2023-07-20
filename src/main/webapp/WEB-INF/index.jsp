<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Designated Driver</title>
</head>
<body>

<h1>New User</h1>
<p>Track Your Spirits</p>

<form:form action="/register" method="post" modelAttribute="newUser">

	<table>
		<thead>
	    	<tr>
	            <td colspan="3">Register</td>
	        </tr>
	    </thead>
	    <thead>
	    	<tr>
	            <td class="float-left">First Name:</td>
	            <td colspan="2" class="float-left">
	            	<form:errors path="firstName" class="text-danger"/>
					<form:input class="input" path="firstName"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Last Name:</td>
	            <td colspan="2" class="float-left">
	            	<form:errors path="lastName" class="text-danger"/>
					<form:input class="input" path="lastName"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Email:</td>
	            <td colspan="2" class="float-left">
	            	<form:errors path="email" class="text-danger"/>
					<form:input class="input" path="email"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Weight (lbs):</td>
	            <td colspan="2" class="float-left">
	            	<form:errors path="weight" class="text-danger"/>
					<form:input class="input" path="weight"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Gender:</td>
	            <td class="float-left">Male
	            	<form:errors path="gender" class="text-danger"/>
					<form:radiobutton class="input" name="gen"  value= "0" path="gender"/>
	            </td>
	            <td class="float-left">Female
	            	<form:errors path="gender" class="text-danger"/>
					<form:radiobutton class="input" name="gen"  value= "1" path="gender"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Password:</td>
	            <td colspan="2" class="float-left">
	            	<form:errors path="password" class="text-danger"/>
					<form:password class="input" path="password"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Confirm Password:</td>
	            <td colspan="2" class="float-left">
	            	<form:errors path="confirm" class="text-danger"/>
					<form:password class="input" path="confirm"/>
	            </td>
	        </tr>
	        <tr>
	        	<td colspan=3><input class="input" type="submit" value="Submit"/></td>
	        </tr>
	    </thead>
	</table>
</form:form>
<hr>
<form:form action="/login" method="post" modelAttribute="newLogin">

	<table>
		<thead>
	    	<tr>
	            <td colspan="2">Log In</td>
	        </tr>
	    </thead>
	    <thead>
	        <tr>
	            <td class="float-left">Email:</td>
	            <td class="float-left">
	            	<form:errors path="email" class="text-danger"/>
					<form:input class="input" path="email"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Password:</td>
	            <td class="float-left">
	            	<form:errors path="password" class="text-danger"/>
					<form:password class="input" path="password"/>
	            </td>
	        </tr>
	        <tr>
	        	<td colspan=2><input class="input" type="submit" value="Submit"/></td>
	        </tr>
	    </thead>
	</table>
</form:form>
</body>
</html>