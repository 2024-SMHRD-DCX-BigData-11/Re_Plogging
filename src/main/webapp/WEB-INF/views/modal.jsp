<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE: PLOGGING</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="assets/css/modal.css">
</head>
<body>
	<!-- 로그인 모달 -->
	<div class="modal-overlay" id="modal">
		<div class="modal-container">
			<img src="img/Re_Plogging_로고.png" alt="Re: Plogging Logo">

			<form id="loginForm" action="" method="post">
				<input type="text" id="useremail" name="user_id"
					placeholder="ID@example.com"> <input type="password"
					id="password" name="user_pw" placeholder="비밀번호를 입력하세요.">
				<div id="errorMessage" class="error-message">
					이메일 또는 비밀번호를 잘못 입력했습니다.<br>입력하신 내용을 다시 확인해주세요.
				</div>

				<button type="submit" class="login-button">로그인</button>
				<div class="or">
					<div class="line"></div>
					<div>또는</div>
					<div class="line"></div>
				</div>
				<button class="kakao-login">Kakao 로그인</button>
			</form>

			<div class="login-options">
				<a href="#">회원가입</a> <a href="#">비밀번호 찾기</a>
			</div>

			<!-- 모달 닫기 버튼 -->
			<div class="modal-close" onclick="closeModal()">닫기</div>
		</div>
		
		<!-- 회원가입 모달 -->
		
	</div>
	
	<!-- Scripts -->
	<script src="assets/js/modal.js"></script>
</body>
</html>