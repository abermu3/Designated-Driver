<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>User Home</title>
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



<hr>
<h3>My Drinks</h3>
<table>
    <thead> 
    	<tr>
    		<th>Drink Type</th>
    		<th>Number Of Drinks</th>
    		<th>Estimated BAC</th>
    		<th>Date Created</th>
    		<th colspan="2">Actions</th>
    	</tr>
    </thead>
    <tbody>
    	<c:forEach var="drink" items="${assignedDrinks}">
		<tr>
			<td><a href="/drinks/${drink.id}">${drink.spiritType}</a></td>
			<td><c:out value="${drink.numOfDrinks}"></c:out></td>
			
			<c:choose> 
					<c:when test= "${drink.drinker.gender== 0}">
						<td><fmt:formatNumber minFractionDigits="2" value="${((14*drink.numOfDrinks/((454*drink.drinker.weight)*.68))*100)-.015}"/></td>
					</c:when>
					
					<c:otherwise>
						<td><fmt:formatNumber minFractionDigits="2" value="${((14*drink.numOfDrinks/((454*drink.drinker.weight)*.55))*100)-.015}"/></td>
					</c:otherwise>
			</c:choose>
			
			<td><span style="font-size: 10px"><c:out value="${drink.createdAt}"></c:out></span></td>
			
			<c:if test = "${drink.drinker.id==user.id}">
		       <td><a href="/drink/edit/${drink.id}">Edit</a></td>
		       <td><a href="/drink/delete/${drink.id}">Delete</a></td>
		    </c:if>
		   
		</tr>
		
	</c:forEach>
    </tbody>
</table>
<div class="homepage">

<a href="https://www.uber.com/" ><img src="/images/uber.png" alt="Uber" width="300" height="200"></a>
<a href="https://www.lyft.com/ride-with-lyft?utm_source=bing&utm_medium=cpc&utm_campaign=PAX_EXP_SEARCH_ALL_ALL_NEW_MOBILE_ACT_US_BRND_LYFT_EXACT&adgroup=Rider_Lyft_Exact&utm_term=lyft&device=c&matchtype=e&targetid=kwd-80402149782381:loc-190&loc_physical_ms=102704&loc_interest_ms=&network=o&devicemodel=&adposition=&campaign_id=638280642&ad_id=&agid=1286429904215032&placement=&adname=&msclkid=77bbcfa333041a03ec6dab151fb76cdb"><img src="/images/lyft.jpeg" alt="Lyft" width="150" height="200"></a>

</div>

</html>