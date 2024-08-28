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
<link rel="stylesheet" href="assets/css/userWithdrawal.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
</head>
<body>
	<div class="userWithdrawal-container">
		<div class="Withdrawal-container">
			<span>회원 탈퇴</span>
		</div>
		<div class="Withdrawal-line"></div>
			<div class="thanks">
				<span>리플로깅을 이용해 주셔서 감사합니다.</span>
			</div>
		<div class="Withdrawal-container-inner">
			<form id="Withdrawal-form" action="deleteMember" method="post" autocomplete="off">
			<table id="Withdrawal-table">
				<tr>
					<th>아이디</th>
					<td><input type="email" id="WuserId" name="userId" value="${user.userId}"
						readonly></td>
				</tr>
				<tr>
				    <th>비밀번호</th>
				    <td>
				        <input type="password" id="WuserPw" name="userPw" placeholder="비밀번호를 입력하세요.">
				    </td>
				</tr>
			</table>
			<div class="Withdrawal-button-wrapper">
				<button type="submit" class="Withdrawal-button">확인</button>
			</div>
		</form>
		</div>
	</div>
		<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved.
		</footer>
		<script src="assets/js/userWithdrawal.js"></script>
</body>
</html>