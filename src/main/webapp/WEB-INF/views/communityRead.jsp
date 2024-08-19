<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>RE:PLOGGING 커뮤니티 읽기 전용 페이지</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/communityRead.css">
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div class="comRead-container">
    <div class="comRead-innner">
        <div class="post-details">
            <h2 class="cr-title">${community.title}</h2>
            <div class="post-meta">
                <div class="meta-left">
                    ${community.category} | ${community.writer.userNick} | 
                    <fmt:formatDate value="${community.indate}" pattern="yyyy.MM.dd" />
                </div>
                <div class="meta-right">
                    조회수 ${community.count} | 
                    <form id="likeForm" action="${pageContext.request.contextPath}/likePost" method="post" style="display: inline;">
                        <input type="hidden" name="idx" value="${community.idx}" />
                        <i class="fas fa-heart" onclick="likePost(${sessionScope.user.userIdx})"></i> ${community.likes}
                    </form>
                </div>
            </div>
            <div class="post-content">
                <c:if test="${!empty community.img}">
                    <img src="${pageContext.request.contextPath}/image/${community.img}" alt="Post Image">
                </c:if>
                <p>${community.content}</p>
            </div>
        </div>

        <!-- Comments Section -->
        <div class="comments-section">
            <h3>댓글</h3>
            
            <!-- Comment List -->
            <c:forEach var="comment" items="${community.comments}">
                <div class="comment">
                    <p><strong>${comment.user.userNick}</strong> - 
                    <fmt:formatDate value="${comment.indate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
                    <p>${comment.message}</p>

                    <!-- 삭제 버튼 (댓글 작성자와 로그인한 사용자가 동일한 경우에만 보임) -->
                    <c:if test="${sessionScope.user != null && comment.user.userIdx == sessionScope.user.userIdx}">
                        <form action="${pageContext.request.contextPath}/comments/deleteComment" method="post" style="display:inline;">
                            <input type="hidden" name="commentId" value="${comment.idx}">
                            <button type="submit" class="btn btn-red">삭제</button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
            
            <!-- Comment Form -->
            <c:if test="${sessionScope.user != null}">
                <form class="comment-form" action="${pageContext.request.contextPath}/comments/addComment" method="post">
                    <input type="hidden" name="communityId" value="${community.idx}">
                    <textarea name="commentContent" placeholder="댓글을 남겨보세요."></textarea>
                    <button type="submit">등록</button>
                </form>
            </c:if>
            <c:if test="${sessionScope.user == null}">
                <p>댓글을 작성하려면 <a href="#" onclick="openModal()">로그인</a> 해주세요.</p>
            </c:if>
        </div>
    </div>

    <!-- Navigation Section -->
    <div class="navigation">
        <div class="navigation-left">
            <c:if test="${sessionScope.user != null}">
                <a href="${pageContext.request.contextPath}/communityWriter" class="btn btn-green">글쓰기</a>
            </c:if>
            <c:if test="${community.writer.userIdx == sessionScope.user.userIdx}">
                <a href="${pageContext.request.contextPath}/editPost?idx=${community.idx}" class="btn btn-gray">수정</a>
                <a href="${pageContext.request.contextPath}/deletePost?idx=${community.idx}" class="btn btn-gray">삭제</a>
            </c:if>
        </div>
        <div class="navigation-right">
            <a href="${pageContext.request.contextPath}/community" class="btn btn-gray">목록</a>
        </div>
    </div>
</div>
<script src="assets/js/communityRead.js"></script>
</body>
</html>
