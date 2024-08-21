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

			<form id="joinForm">
				<table>
					<tr>
						<th>아이디</th>
						<td><input type="email" id="join_user_id" name="userId"
							class="join-id-input" placeholder="ID@example.com" autocomplete="off">
							<span id="idMsg" class="idMsg">중복된 아이디 입니다.</span>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" id="join_user_pw" name="userPw"
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
							<input type="hidden" id="telCheck" name="telCheck" value="0" />
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
	
	
	<!-- 지도 이미지 모달 -->
	<div id="courseModal" class="courseModal">
    <div class="courseModal-content">
        <span class="close" onclick="closeCourseModal()">&times;</span>
    	<div class="courseModal-inner-content">
        <img id="courseImage" src="" alt="코스 이미지" style="width: 100%; height: auto;">
        </div>
    </div>
</div>
	<!-- <script  src="http://code.jquery.com/jquery-latest.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="assets/js/modal.js"></script>
	<script type="text/javascript">
		$(".join-button").on( "click", function( event ) {
			$("#joinForm").submit();
		});
		
		$("#joinForm").submit( function( event ) {
			event.preventDefault(); // 기본 폼 제출 동작을 막음
			var $this = $(this),
				formData = new FormData( $this[0] );
		
			var user_id = $('#join_user_id').val();
			var user_pw = $('#join_user_pw').val();
			var user_pw_c = $('#user_pw_confirm').val();
			var user_phone = $('#mobile4').val();
			var sms_check = $('#otp').val();
			var user_nick = $('#user_nick').val();
			
			
			if( user_id.length == 0 ) {
				$("#idMsg").css("display", "flex");
				return false;
			} else if ( user_pw.length == 0 ) {
				erroAlert("비밀번호를 입력해주세요.",join_user_pw)
				return false;
			} else if ( user_pw_c.length == 0 ) {
				erroAlert("비밀번호 확인을 입력해주세요.",user_pw_confirm)
				return false;
			} else if ( user_pw.match( user_pw_c ) == null ) {
				$("#pwMsg").css("display", "flex");
				return false;
			} else if ( user_phone.length == 0 ) {
				erroAlert("전화번호를 입력해주세요.")
				return false;
			} else if ( $("#telCheck").val() == "0" ) {
				erroAlert("전화번호를 인증해주세요.")
				return false;
			}else if (sms_check.length == 0 || sms_check.length != 6){
				erroAlert("인증번호를 다시 입력해주세요.",otp)
				return false;
			} else if ( user_nick.length == 0 ) {
				$("#idMsg").css("display", "flex");
				return false;
			} else {
				commonMultiAjax( "${ctx }/rest/member/join", formData, function( response ) {
					if( response.code == 200 ) {
						//등록 성공
						alert("회원가입 성공!!");
						location.replace("${ctx }/main") // 회원가입 성공 시 메인 페이지로 이동
					} else if ( response.code == -100 ) {
						//등록 실패
						alert("회원가입 실패 다시 시도해주세요");
						return false;
					} else if (response.code == -500) {
						//아이디 중복
						alert("이미 가입된 이메일입니다!!");
						return false;
					} else if (response.code == -400) {
						// 전화번호 중복
						alert("이미 가입된 전화번호입니다!!");
						return false;
					} else{
						// 닉네임 중복
						alert("이미 사용중인 닉네임입니다!!")
						return false;;
					}
				});
				return false;
			}	
			return false;
		});
		
		$("#btnSubmit").on("click", function( event ) {
			smsCheck('${ctx }/rest/sms2/smsCheck');
		});
		
	</script>
</body>
</html>