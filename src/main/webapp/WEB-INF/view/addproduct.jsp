<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="Free Web tutorials" />
<meta name="keywords" content="HTML,CSS,XML,JavaScript" />
</head>



<body>
	<div>
		<form action="addproduct" method="post">
			Product name:<br> <input type="text" name="name"><br>
			Price:<br> <input type="text" name="price"> <br>
			Description:<br> <input type="text" name="description">
			<p><select size=3 id="selectCategory" name="category">
				<option disabled>Choose category</option>
				<c:forEach items="${category}" var="categ">
					<option value="${categ.id}">${categ.name}</option>
				</c:forEach>
			</select> <p>
			<input type="submit" value="send"/>
		</form>
	</div>
</body>