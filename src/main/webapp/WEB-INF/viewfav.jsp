<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Drink Details</title>
</head>
<body>

<h2><a href="/populardrinks">Back</a></h2>

<h1>View Favorite Drink</h1>
<table>
    <tbody>
    
    	<tr>
        	<td class="float-left">Spirit Type:</td>
        	<td><c:out value="${drink.spiritType}"></c:out></td>
        </tr>
        
        <tr>
        	<td class="float-left">Brand:</td>
            <td><c:out value="${drink.brand}"></c:out></td>
        </tr>
        
        <tr>
        	<td class="float-left">Alc %</td>
        	<td><c:out value="${drink.alcPercent}"></c:out></td>
        </tr>
        
        <tr>
        	<td class="float-left">Taste Description:</td>
        	<td><c:out value="${drink.description}"></c:out></td>
        </tr>
            
    </tbody>
</table>

<c:if test = "${drink.drinker.id==userId}">
	<h3><a href="/favdrink/edit/${drink.id}">Edit Drink</a></h3>
    <h3><a href="/favdrink/delete/${drink.id}">Delete Drink</a></h3>
</c:if>

</body>
</html>