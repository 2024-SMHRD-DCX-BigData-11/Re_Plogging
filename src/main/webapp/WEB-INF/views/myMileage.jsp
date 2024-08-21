<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>내 마일리지</title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="container">
	<h1>마일리지 내역</h1>
	<h4>현재 적립금</h4>
	<h2></h2>
	<table>
		<thread>
		<tr>
			<td>유형</td>
			<td>마일리지</td>
			<td>날짜</td>
		</tr>
		</thread>
		<tbody>
		<c:forEach var="myMileage" items="${list}">
			<tr>
				<td>${myMileage.mlType}</td>
				<td>${myMileage.mlAmount} 마일리지</td>
				<td>${myMileage.createdAt}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

</body>
</html>