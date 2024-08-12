<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!-- header.jsp 파일 포함 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 페이지 입니다.</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	margin-top: 80px; /* 헤더와의 간격을 주기 위해 추가 */
}

h1 {
	color: #333;
}

.table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

.table th, .table td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

.table th {
	background-color: #f4f4f4;
}

.table tr:nth-child(even) {
	background-color: #f9f9f9;
}

.btn {
	display: inline-block;
	padding: 10px 20px;
	font-size: 14px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	color: #fff;
	background-color: #4CAF50;
	border: none;
	border-radius: 5px;
}

.btn:hover {
	background-color: #45a049;
}

.page-header {
	margin-bottom: 20px;
}

.pagination {
	list-style: none;
	display: flex;
	justify-content: center;
	padding: 0;
}

.pagination li {
	margin: 0 5px;
}

.pagination a {
	text-decoration: none;
	padding: 8px 16px;
	color: #333;
	border: 1px solid #ddd;
	border-radius: 4px;
}

.pagination a:hover {
	background-color: #ddd;
}

.pagination .active a {
	background-color: #4CAF50;
	color: white;
}

.search-box {
	margin-bottom: 20px;
	display: flex;
	align-items: center;
	width: 100%;
}

.search-select, .search-input, .search-btn {
	margin-right: 10px;
}

.search-select {
	padding: 10px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.search-input {
	flex: 1;
	padding: 10px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.search-btn {
	padding: 10px 20px;
	font-size: 14px;
	cursor: pointer;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
}

.search-btn:hover {
	background-color: #45a049;
}
</style>
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
			<a href="${pageContext.request.contextPath}/communityWriter" class="btn">새
				글 작성</a>
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
						<td>${post.id}</td>
						<td><a href="/community/${post.id}">${post.title}</a></td>
						<td>${post.category}</td>
						<td>${post.author}</td>
						<td>${post.createdDate}</td>
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
