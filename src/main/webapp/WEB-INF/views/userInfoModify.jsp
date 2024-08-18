<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>RE:PLOGGING MYPAGE</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/userInfoModify.css">
</head>
<body>
	<div class="userInfoModify-container">
		<div class="Info-container">
			<span>회원 정보 수정</span>
		</div>
		<div class="Info-line"></div>
		<div class="Info-container-inner">
			<div class="ic-inner-left">
				<img src="img/기본_프로필.png" alt="프로필 이미지">
			</div>
			<div class="ic-inner-right">
				<input id="file-input" type="file" style="visibility: hidden;">
				<span>나의 프로필</span>
				<button class="upload-button" onclick="onClickUpload();">사진 업로드</button>
			</div>
		</div>
		<form id="userInfo-form" method="post" autocomplete="off">
			<table id="user-info-table">
				<tr>
					<th>아이디</th>
					<td><input type="email" id="MuserId" name="userId" value="${user.userId}"
						readonly></td>
				</tr>
				<tr id="pw-row">
				    <th>비밀번호</th>
				    <td class="pw-field">
				        <input type="password" id="MuserPw" name="userPw" value="*********" readonly>
				        <button type="button" id="change-pw-button">변경</button>
				    </td>
				</tr>
				<tbody id="change-pw-section" >
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" id="current-Mpw" placeholder="현재 비밀번호"></td>
				</tr>
				<tr>
					<th>새 비밀번호</th>
					<td><input type="password" id="confirm-Mpw" placeholder="새 비밀번호"></td>
				</tr>
				<tr>
					<th>새 비밀번호 확인</th>
					<td><input type="password" id="new-confirm-Mpw" placeholder="새 비밀번호 확인"></td>
				</tr>
				<tr>
					<th></th>
					<td class="button-group">
						<button type="button" id="cancel-button">취소</button>
						<button type="button" id="save-button" onclick="inputReadOnly()">변경사항 저장</button>
					</td>
				</tr>
				</tbody>
				<tr>
					<th>휴대폰번호</th>
					<td><input type="text" id="MuserPhone" name="userPhone" value="${user.userPhone}"
						readonly></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input type="text" id="MuserNick" name="userNick" value="${user.userNick}"></td>
				</tr>
			</table>
			<div class="Modify-button-wrapper">
				<button type="submit" class="Modify-button">확인</button>
			</div>
		</form>
	</div>
		<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved.
		</footer>
		
		<script src="assets/js/userInfoModify.js"></script>
		<script src="assets/js/modal.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(".Modify-button").on( "click", function( event ) {
			$("#userInfo-form").submit();
		});
		
		$("#userInfo-form").submit( function( event ) {
			event.preventDefault(); // 기본 폼 제출 동작을 막음
			var $this = $(this),
				formData = new FormData( $this[0] );
		
			var currentMpw = $('#current-Mpw').val();
			var confirmMpw = $('#confirm-Mpw').val();
			var nconfirmMpw = $('#new-confirm-Mpw').val();
			var MuserNick = $('#MuserNick').val();
			
			
			if( !currentMpw || currentMpw.length === 0) {
				erroAlert("현재 비밀번호를 입력해주세요.",'current-Mpw')
				return false;
			} else if (!confirmMpw || confirmMpw.length === 0) {
				erroAlert("새 비밀번호를 입력해주세요.",'confirm-Mpw')
				return false;
			} else if (!nconfirmMpw || nconfirmMpw.length === 0) {
				erroAlert("새 비밀번호 확인을 입력해주세요.",'new-confirm-Mpw')
				return false;
			} else if ( confirmMpw.match( nconfirmMpw ) == null ) {
				erroAlert("비밀번호가 일치하지 않습니다.",'new-confirm-Mpw')
				return false;
			} else if (!MuserNick || MuserNick.length === 0) {
				erroAlert("닉네임을 입력해주세요.",'MuserNick')
				return false;
			}else {
				commonMultiAjax( "${ctx }/rest/member/memberUpdate", formData, function( response ) {
					if( response.code == 0 ) {
						//등록 성공
						alert("회원정보 수정 성공!!");
					} else if ( response.code != 0 ) {
						//등록 실패
						alert("회원정보 수정 실패");
					} else if (response.code == -500) {
						//아이디 중복
						alert("비밀번호가 일치하지 않습니다.");
					} else if (response.code == -400) {
						// 전화번호 중복
						alert("이미 사용중인 닉네임입니다!");
					} else{
						// 로그인이 되어있지 않음
						alert("로그인 상태가 아닙니다.");
					}
				});
				return false;
			}	
			return false;
		});
		
	</script>
</html>