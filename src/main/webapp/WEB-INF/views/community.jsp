<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <div class="community-container">
        <div class="page-header">
            <h1>자유 게시판</h1>
        </div>
        <div class="search-box">
            <form action="${pageContext.request.contextPath}/community" method="get"
                style="display: flex; width: 100%;">
                <select name="category" class="search-select">
                    <option value="" ${selectedCategory == null ? 'selected' : ''}>전체 카테고리</option>
                    <option value="plogging" ${selectedCategory == 'plogging' ? 'selected' : ''}>플로깅</option>
                    <option value="separation" ${selectedCategory == 'separation' ? 'selected' : ''}>분리배출</option>
                    <option value="freeboard" ${selectedCategory == 'freeboard' ? 'selected' : ''}>자유게시판</option>
                </select> 
                <input type="text" name="keyword" class="search-input"
                    placeholder="검색어를 입력하세요" value="${keyword}">
                <button type="submit" class="search-btn">검색</button>
            </form>
        </div>
        <table class="community-table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>카테고리</th>
                    <th>작성자</th>
                    <th>작성날짜</th>
                    <th>조회수</th>
                    <th>좋아요</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${fn:length(list) != 0}">
                        <c:forEach var="community" items="${list}">
                            <tr>
                                <td>${community.idx}</td>
                                <td>
                                   <a href="${pageContext.request.contextPath}/communityRead?idx=${community.idx}">${community.title}</a>
                                   <c:if test="${community.comments.size() > 0}">
                                      [${community.comments.size()}]
                                   </c:if>
                                </td>
                                <td>${community.category}</td>
                                <td>${community.writer.userNick}</td>
                                <td><fmt:formatDate value="${community.indate}" pattern="yyyy-MM-dd"/></td>
                                <td>${community.count}</td>
                                <td>${community.likes}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="7">no data</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        <div style="text-align: right;">
            <a href="${pageContext.request.contextPath}/community"
                class="commListBtn">목록</a>
                
            <c:if test="${!empty user}">
            <a href="${pageContext.request.contextPath}/communityWriter"
                class="commWirteBtn">글쓰기</a>
            </c:if>
        </div>
    </div>

    <nav aria-label="Page navigation" style="text-align: center;">
        <ul class="pagination">
            <c:if test="${currentPage > 1}">
                <li><a href="${pageContext.request.contextPath}/community?page=1">&laquo;</a></li>
                <li><a href="${pageContext.request.contextPath}/community?page=${currentPage - 1}">&lsaquo;</a></li>
            </c:if>
            <c:forEach var="i" begin="1" end="${totalPages}">
                <li class="${i == currentPage ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/community?page=${i}">${i}</a>
                </li>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <li><a href="${pageContext.request.contextPath}/community?page=${currentPage + 1}">&rsaquo;</a></li>
                <li><a href="${pageContext.request.contextPath}/community?page=${totalPages}">&raquo;</a></li>
            </c:if>
        </ul>
    </nav>
</body>
</html>
