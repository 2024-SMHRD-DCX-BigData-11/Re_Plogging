<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE: PLOGGING</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="assets/css/header.css">
</head>
<body>
	<div class="header">
		<c:if test="${empty user}">
			<div class="user-icon" onclick="openModal()">
				<img src="img/비로그인.png" alt="">
			</div>
		</c:if>
		<c:if test="${!empty user}">
			<div class="user-icon">
				<a href="${pageContext.request.contextPath}/mypage"><img src="img/로그인.png" alt=""></a>
			</div>
		</c:if>
		<div class="logo">
			<a href="${pageContext.request.contextPath}/main"><img
				src="img/Re_Plogging_로고.png" alt=""></a>
		</div>
		<button class="menu-icon" id="menu-icon">
			<span class="burgur" id="burgur"> <span class="top-line"></span>
				<span class="bot-line"></span>
			</span>
		</button>
		<ul class="menu">
			<li><a href="${pageContext.request.contextPath}/ploggingList">플로코스</a></li>
			<li><a href="${pageContext.request.contextPath}/recycle">분리배출</a></li>
			<li><a href="${pageContext.request.contextPath}/community">커뮤니티</a></li>
			<li><a href="${pageContext.request.contextPath}/market">그린마켓</a></li>
		<c:if test="${empty user}">
			<li><a href="#" onclick="openModal()">로그인</a></li>
		</c:if>
		<c:if test="${!empty user}">
			<li><a href="${pageContext.request.contextPath}/mypage">마이페이지</a></li>
		</c:if>
		</ul>
	</div>

	<div id="overlay"></div>

	<div id="aside" class="aside">
		<div class="aside-inner-container">
            <c:if test="${!empty user}">
                <div class="aside-user">
                    <span class="aside-userNick">${user.userNick}</span><span class="asid-nim">님</span><br>
                    <span class="aside-mileage">그린 마일리지<span class="aside-p"> ${user.mileageAmount}p</span>
                </div>
                <div class="aside-line"></div>
            </c:if>
			<ul class="aside-category">
			<li><a href="${pageContext.request.contextPath}/ploggingList">플로코스</a></li>
			<li><a href="${pageContext.request.contextPath}/recycle">분리배출</a></li>
			<li><a href="${pageContext.request.contextPath}/community">커뮤니티</a></li>
			<li><a href="${pageContext.request.contextPath}/market">그린마켓</a></li>
		</div>
		</ul>
        <c:if test="${!empty user}">
            <div class="aside-footer">
                <div class="aside-inner-footer">
                    <li><a href="logout">로그아웃</a></li>
                </div>
            </div>
        </c:if>
	</div>
	<!-- Scripts -->
	<script src="assets/js/header.js"></script>
</body>
</html>