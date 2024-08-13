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
		<form id="userInfo-form" action="" method="post" autocomplete="off">
			<table id="user-info-table">
				<tr>
					<th>아이디</th>
					<td><input type="email" id="MuserId" name="userId" value="o_o226@naver.com"
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
						<button type="button" id="save-button">변경사항 저장</button>
					</td>
				</tr>
				</tbody>
				<tr>
					<th>휴대폰번호</th>
					<td><input type="text" id="MuserPhone" name="userPhone" value="010-XXXX-XXXX"
						readonly></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input type="text" id="MuserNick" name="userNick" value="지구수호대"></td>
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
</html>