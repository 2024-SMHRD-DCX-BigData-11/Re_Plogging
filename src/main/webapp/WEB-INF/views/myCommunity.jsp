<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>내가 작성한 게시글</title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


</head>
<body>

	<div id="MyCommunityList">
	<h1>게시글 목록</h1>
		<table id="list">
			<thead>
				<tr>
					<td>글번호</td>
					<td>카테고리</td>
					<td>글제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>좋아요수</td>
					<td>첨부파일</td>
				</tr>
			</thead>
			<tbody>
				<%--게시글 목록 출력--%>
				<c:forEach var="myCommunity" items="${MyClist}" >
				<tr>
					<td></td>
					<td>${myCommunity.idx}</td>
					<td>${myCommunity.category}</td>
					<td><a href="${ctx }/communityRead?idx=${myCommunity.idx}">${myCommunity.title}</a></td>
					<td>${myCommunity.writer.userNick}</td>
					<td>${myCommunity.count}</td>
					<td>${myCommunity.likes}</td>
					<td>${myCommunity.img}</td>
					<td><a href="${ctx }/Mycdelete?idx=${myCommunity.idx}">X</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>