<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE:PLOGGING MYPAGE</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/mypage.css">
</head>
<body>
	<div class="mypage-container">
		<div class="profile-container">
			<span>프로필</span>
		</div>
		<div class="profile-line"></div>
		<div>
			<div class="profile-container-inner">
				<div class="pc-inner-left">
					<c:choose>
						<c:when test="${user.userProfileImg != null}">
							<img src="<%=request.getContextPath()%>/profileImage" alt="프로필 이미지">
						</c:when>
						<c:otherwise>
							<img src="img/기본_프로필.png" alt="">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="pc-inner-right">
					<div class="profile-nContainer">
						<div class="profile-nick">
							<span class="pro-usernick">${user.userNick}</span>
							<span class="pro-nim">님</span>
						</div>
						<div class="logout-container" id="logoutButton">
							<span class="logout-text">로그아웃</span>
							<img src="img/로그아웃.png" alt="" class="icon">
						</div>
					</div>
					<div class="profile-info">
						<span>리플로깅과 함께</span><br>
						<span>오늘도 활기찬 하루 보내세요<span class="profile-heart">♥</span></span>
					</div>
					<div class="profile-activity">
						<span>완료한 플로깅</span>
						<span class="point">${completedPloggingCount}회</span><br>
						<span>그린 마일리지</span>
						<span class="point" id="mileage-point">${totalMileage}p</span>
					</div>
				</div>
			</div>
			<ul class="mypage-ul">
				<li><a href="${pageContext.request.contextPath}/userInfoModify"><sapn>회원 정보 수정</sapn> <img
						src="img/right-arrow.png" alt="Arrow"> </a></li>
				<li><a href="${pageContext.request.contextPath}/myplogging"><sapn>나의 플로깅</sapn> <img
						src="img/right-arrow.png" alt="Arrow"> </a></li>
				<li><a href="${pageContext.request.contextPath}/myWrite"><sapn>작성 글 조회</sapn> <img
						src="img/right-arrow.png" alt="Arrow"> </a></li>
				<li><a href="${pageContext.request.contextPath}/myMileage"><sapn>그린 마일리지 내역</sapn> <img
						src="img/right-arrow.png" alt="Arrow"> </a></li>
				<li><a href="${pageContext.request.contextPath}/userWithdrawal"><sapn>회원 탈퇴</sapn> <img
						src="img/right-arrow.png" alt="Arrow"> </a></li>
			</ul>
		</div>
	</div>
	
	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>
	
	<script src="assets/js/mypage.js"></script>
</body>
</html>
