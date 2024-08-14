<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE: PLOGGING</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="assets/css/modal.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 로그인 모달 -->
	<div id="login-modal">
		<div class="login-container">
			<img src="img/Re_Plogging_로고.png" alt="Re: Plogging Logo">

			<form id="loginForm" action="login" method="post">
				<input type="email" id="user_id" name="userId"
					class="login-id-input" placeholder="ID@example.com" autocomplete="off"> <input
					type="password" id="user_pw" name="userPw" class="login-pw-input"
					placeholder="비밀번호를 입력하세요.">
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
				<div id="join-link">회원가입</div>
				<div id="">비밀번호 찾기</div>
			</div>

			<!-- 모달 닫기 버튼 -->
			<div class="modal-close" onclick="closeLoginModal()">닫기</div>
		</div>
	</div>

	<!-- 회원가입 모달 -->
	<div id="join-modal">
		<div class="join-container">
			<img src="img/Re_Plogging_로고.png" alt="Re: Plogging Logo">

			<form id="joinForm" action="join" method="post">
				<table>
					<tr>
						<th>아이디</th>
						<td><input type="email" id="user_id" name="userId"
							class="join-id-input" placeholder="ID@example.com" autocomplete="off">
							<span id="idMsg" class="idMsg">중복된 아이디 입니다.</span>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" id="user_pw" name="userPw"
							class="join-pw-input" placeholder="비밀번호를 입력하세요."></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" id="user_pw_confirm"
							name="user_pw_confirm" class="join-pw-confirm">
							<span id="pwMsg" class="pwMsg">비밀번호가 일치하지 않습니다.</span>	
						</td>
					</tr>
					<tr>
						<th>휴대폰번호</th>
						<td>
							<div class="phone-container">
								<select id="mobile1" name="mobile[]">
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
									<option value="018">018</option>
									<option value="019">019</option>
								</select>
								<input id="mobile2" name="mobile[]" type="tel">
								<input id="mobile3" name="mobile[]" type="tel">
								<input id="mobile4" name="userPhone" type="hidden" value = "">
							</div>
								<button type="button" id="telconfirm" class="tel-confirm" onclick="telconfirmButton('${ctx }/rest/sms/shipping1234');">인증</button>
						</td>
					</tr>
					<tr>
						<th>SNS 인증번호</th>
						<td>
						<div class="join-sns-input">
							<input type="tel" id="otp" name="otp" class="otp" maxlength="6" placeholder="숫자 6자리 입력">
								<span type="text" name="inputTime" id="countdown" class="countdown"></span>
						</div>
						<button type="button" id="btnSubmit" class="btnSubmit">확인</button>
						</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td><input type="text" id="user_nick" name="userNick"
							class="join-nick-input">
							<span id="nickMsg" class="nickMsg">중복된 닉네임 입니다.</span>
						</td>
					</tr>
				</table>
				<button type="submit" class="join-button">가입하기</button>
			</form>
			<div class="modal-close" onclick="closeJoinModal()">닫기</div>
		</div>
	</div>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="assets/js/modal.js"></script>
</body>
</html>