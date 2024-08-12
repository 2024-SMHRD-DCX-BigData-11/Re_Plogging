<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE:PLOGGING 커뮤니티 페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/community.css">
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>자유 게시판</h1>
		</div>
		<div class="search-box">
			<form action="/community" method="get"
				style="display: flex; width: 100%;">
				<select name="category" class="search-select">
					<option value="">전체 카테고리</option>
					<option value="plogging"
						${category eq 'plogging' ? 'selected' : ''}>플로깅</option>
					<option value="separation"
						${category eq 'separation' ? 'selected' : ''}>분리배출</option>
					<option value="freeboard"
						${category eq 'freeboard' ? 'selected' : ''}>자유게시판</option>

				</select> <input type="text" name="keyword" class="search-input"
					placeholder="검색어를 입력하세요" value="${keyword}">
				<button type="submit" class="search-btn">검색</button>
			</form>
		</div>
		<div style="margin-bottom: 20px;">
			<a href="${pageContext.request.contextPath}/communityWriter"
				class="btn">새 글 작성</a>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>카테고리</th>
					<th>작성자</th>
					<th>작성날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${posts}" var="post">
					<tr>
						<td>${post.idx}</td>
						<td><a href="/community/${post.idx}">${post.title}</a></td>
						<td>${post.category}</td>
						<td>${post.writer.userName}</td>
						<!-- 작성자 이름 -->
						<td>${post.indate}</td>
						<td>${post.count}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br />
	<nav aria-label="Page navigation" style="text-align: center;">
		<ul class="pagination">
			<li><a href="/community?page=1">&laquo;</a></li>
			<!-- 여기에 페이지 네비게이션이 들어갈 것입니다. -->
			<li><a href="#">&lsaquo;</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">&rsaquo;</a></li>
			<li><a href="#">&raquo;</a></li>
		</ul>
	</nav>
</body>
</html>
