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
		<form action="addcategory" method="post">
			Category name:<br> <input type="text" name="name"><br>
			Description:<br> <input type="text" name="description">
			<input type="submit" value="send" />
		</form>
	</div>
</body>