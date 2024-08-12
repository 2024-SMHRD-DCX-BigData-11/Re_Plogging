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
            <div class="ic-inner-left"><img src="img/기본_프로필.png" alt="프로필 이미지"></div>
            <div class="ic-inner-right">
            <span>나의 프로필</span>
            <button class="upload-button">사진 업로드</button>
			</div>            
        </div>
        <form id="user-info-form">
            <label for="email">아이디</label>
            <input type="email" id="email" value="o_o226@naver.com" readonly>
            
            <div id="password-section">
                <label for="password">비밀번호</label>
                <input type="password" id="password" value="**********" readonly>
                <button type="button" id="change-password-button">변경</button>
            </div>

            <div id="change-password-section" style="display: none;">
                <label for="current-password">현재 비밀번호</label>
                <input type="password" id="current-password">

                <label for="new-password">새 비밀번호</label>
                <input type="password" id="new-password">

                <label for="confirm-password">새 비밀번호 확인</label>
                <input type="password" id="confirm-password">

                <button type="button" id="cancel-button">취소</button>
                <button type="button" id="save-changes-button">변경사항 저장</button>
            </div>

            <label for="phone">전화번호</label>
            <input type="text" id="phone" value="010-XXXX-XXXX" readonly>

            <label for="nickname">닉네임</label>
            <input type="text" id="nickname" value="지구수호대">
            
            <button type="submit">확인</button>
        </form>
    </div>
	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>
	
    <script src="assets/js/userInfoModify.js"></script>
</body>
</html>