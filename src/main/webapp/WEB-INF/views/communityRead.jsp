<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE:PLOGGING 커뮤니티 읽기 전용 페이지</title>
<meta http-equiv="Content-Type" text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/communityRead.css">
<!-- 하트 아이콘을 위해 FontAwesome CDN 추가 -->
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style>
.post-title {
    font-size: 2em; /* 타이틀 크기 증가 */
    margin-top: 20px;
}

.post-meta {
    font-size: 1em;
    color: #555;
    margin-bottom: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center; /* 중앙 정렬 */
}

.meta-left {
    display: flex;
    gap: 10px; /* 항목 사이에 간격 추가 */
}

.meta-right {
    text-align: right;
    display: flex;
    gap: 10px; /* 항목 사이에 간격 추가 */
    align-items: center; /* 중앙 정렬 */
    justify-content: flex-end;
}

.post-meta .fa-heart {
    color: red;
    cursor: pointer;
}
</style>
<script>
    function likePost() {
        const loggedIn = '<%= session.getAttribute("user") != null %>';
        if (loggedIn === 'true') {
            document.getElementById('likeForm').submit();
        } else {
            alert('로그인 후 좋아요를 누를 수 있습니다.');
        }
    }
</script>
</head>
<body>
    <div class="container">
        <div class="post-details">
            <h2 class="post-title">${community.title}</h2>
            <div class="post-meta">
                <div class="meta-left">
                    카테고리: ${community.category} | 작성자: ${community.writer.userNick} |
                    작성날짜:
                    <fmt:formatDate value="${community.indate}" pattern="yyyy-MM-dd" />
                </div>
                <div class="meta-right">
                    조회수: ${community.count} 
                    <!-- 좋아요 기능 추가 -->
                    <form id="likeForm" action="${pageContext.request.contextPath}/likePost" method="post">
                        <input type="hidden" name="idx" value="${community.idx}" />
                        <i class="fas fa-heart" onclick="likePost()"></i> <span>${community.likes}</span>
                    </form>
                </div>
            </div>
            <c:if test="${community.img != null}">
                <img src="${pageContext.request.contextPath}${community.img}">
            </c:if>
            <div class="post-content">
                <p>${community.content}</p>
            </div>
        </div>
        <div class="navigation">
            <a href="${pageContext.request.contextPath}/community" class="btn">목록으로 돌아가기</a>
        </div>
    </div>
</body>
</html>
