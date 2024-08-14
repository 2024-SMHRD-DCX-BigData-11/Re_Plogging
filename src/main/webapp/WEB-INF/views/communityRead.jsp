<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
    }

    .post-meta .fa-heart {
        color: red;
        margin-left: 10px;
    }

    .meta-right {
        text-align: right;
    }
</style>
</head>
<body>
    <div class="container">
        <!-- 페이지 헤더 제거됨 -->
        <div class="post-details">
            <h2 class="post-title">${community.title}</h2>
            <div class="post-meta">
                <div>
                    카테고리: ${community.category} | 
                    작성자: ${community.writer.userNick} | 
                    작성날짜: ${community.indate}
                </div>
                <div class="meta-right">
                    조회수: ${community.count} 
                    <i class="fas fa-heart"></i> <span>${community.likes}</span>
                </div>
            </div>
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
