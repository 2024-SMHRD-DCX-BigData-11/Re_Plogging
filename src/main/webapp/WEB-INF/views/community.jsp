<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    <option value="plogging">플로깅</option>
                    <option value="separation">분리배출</option>
                    <option value="freeboard">자유게시판</option>
                </select> <input type="text" name="keyword" class="search-input"
                    placeholder="검색어를 입력하세요">
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
                <c:choose>
                <c:when test="${fn:length( list ) != 0 }">
                        <c:forEach var="community" items="${list }">
                            <tr>
                                <td>${community.idx}</td>
                                <td><a href="${pageContext.request.contextPath}/communityRead?idx=${community.idx}">${community.title}</a></td>
                                <td>${community.category}</td>
                                <td>${community.writer.userNick}</td>
                                <td>${community.indate}</td>
                                <td>${community.count}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" >no data</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
    <br />
    <nav aria-label="Page navigation" style="text-align: center;">
    <ul class="pagination">
        <li><a href="/community?page=1">&laquo;</a></li>
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
