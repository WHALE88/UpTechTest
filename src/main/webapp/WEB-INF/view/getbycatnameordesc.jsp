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
		<h2>Get all products by category name</h2>
		<form action="getbycatname" method="post">
			<p>
				<select size=3 id="selectCategory" name="category">
					<option disabled>Choose category</option>
					<c:forEach items="${category}" var="categ">
						<option value="${categ.name}">${categ.name}</option>
					</c:forEach>
				</select>
			<p>
				<input type="submit" value="send" />
		</form>
		<h2>Get all products by category description</h2>
		<form action="getbycatdesc" method="post">
			<p>
				<select size=3 id="selectCategory" name="category">
					<option disabled>Choose category</option>
					<c:forEach items="${category}" var="categ">
						<option value="${categ.description}">${categ.description}</option>
					</c:forEach>
				</select>
			<p>
				<input type="submit" value="send" />
		</form>
	</div>
</body>