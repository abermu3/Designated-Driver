<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Popular Drinks</title>
</head>
<body>

<div class="top">
        <div class="nav">
            <h2 class="nav-title">Designated Driver</h2>
            <ul class="nav-links">
            	<li><a href="/home">Home</a></li>
                <li><a href="/drinks/new">Add Personal Drink</a></li>
                <li><a href="/favdrinks/new">Add Favorite Drink</a></li>
                <li><a href="/populardrinks">Popular Drinks</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>
        </div>  
</div>

<h4>Explore Popular Drinks:</h4>

<table>
    <thead> 
    	<tr>
    		<th>Spirit Type</th>
    		<th>Brand</th>
    		<th>Alcohol %</th>
    		<th>Taste</th>
    		<c:if test = "${drink.drinker.id==user.id}">
    			<th colspan="2">Actions</th>
    		</c:if>
    	</tr>
    </thead>
    <tbody>
    	<c:forEach var="drink" items="${allDrinks}">
		<tr>
			<td><a href="/favdrinks/${drink.id}">${drink.spiritType}</a></td>
			<td><c:out value="${drink.brand}"></c:out></td>
			<td><c:out value="${drink.alcPercent}"></c:out></td>
			<td><c:out value="${drink.description}"></c:out></td>
			<c:if test = "${drink.drinker.id==user.id}">
				<td><a href="/favdrink/edit/${drink.id}">Edit</a></td>
		       	<td><a href="/favdrink/delete/${drink.id}">Delete</a></td>
		    </c:if>
		</tr>	
		</c:forEach>
    </tbody>
</table>

</body>
</html>