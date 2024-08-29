<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<%-- <c:set var="ctx" value="${pageContext.request.contextPath}"/> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
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
				<c:choose>
					<c:when test="${user.userProfileImg != null}">
						<img src="<%=request.getContextPath()%>/profileImage" alt="프로필 이미지">
					</c:when>
					<c:otherwise>
						<img src="img/기본_프로필.png" alt="기본 프로필 이미지">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="ic-inner-right">
				<form id="image-upload-form" action="<%=request.getContextPath()%>/updateProfileImage" method="post" enctype="multipart/form-data">
					<input id="file-input" type="file" name="file" style="visibility: hidden;" onchange="submitImageForm()">
					<span>나의 프로필</span>
					<button type="button" class="upload-button" onclick="document.getElementById('file-input').click();">사진 업로드</button>
				</form>
			</div>
		</div>
		<form id="userInfo-form" method="post" autocomplete="off">
			<table id="user-info-table">
				<!-- 기존 코드 유지 -->
				<tr>
					<th>아이디</th>
					<td><input type="email" id="MuserId" name="userId" value="${user.userId}" readonly></td>
				</tr>
				<tr id="pw-row">
				    <th>비밀번호</th>
				    <td class="pw-field">
				        <input type="password" id="MuserPw" name="userPw" value="*********" readonly>
				        <button type="button" id="change-pw-button">변경</button>
				    </td>
				</tr>
				<tbody id="change-pw-section">
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" id="current-Mpw" placeholder="현재 비밀번호" name="currentMpw"></td>
				</tr>
				<tr>
					<th>새 비밀번호</th>
					<td><input type="password" id="confirm-Mpw" placeholder="새 비밀번호" name="confirmMpw"></td>
				</tr>
				<tr>
					<th>새 비밀번호 확인</th>
					<td><input type="password" id="new-confirm-Mpw" placeholder="새 비밀번호 확인" name="nconfirmMpw"></td>
				</tr>
				<tr>
					<th></th>
					<td class="button-group">
						<button type="button" id="cancel-button" >취소</button>
						<button type="button" id="save-button" onclick="inputReadOnly()">변경사항 저장</button>
					</td>
				</tr>
				</tbody>
				<tr>
					<th>휴대폰번호</th>
					<td><input type="text" id="MuserPhone" name="userPhone" value="${user.userPhone}" readonly></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input type="text" id="MuserNick" name="MuserNick" placeholder="${user.userNick}"></td>
				</tr>
			</table>
			<div class="Modify-button-wrapper">
				<button type="submit" class="Modify-button">확인</button>
			</div>
		</form>
	</div>
	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved.</footer>
	<script src="assets/js/userInfoModify.js"></script>
	<script src="assets/js/modal.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(".Modify-button").on("click", function(event) {
			$("#userInfo-form").submit();
		});
		
		$("#userInfo-form").submit(function(event) {
			event.preventDefault(); // 기본 폼 제출 동작을 막음
			var $this = $(this),
				formData = new FormData($this[0]);
			
			var currentMpw = $('#current-Mpw').val();
			var confirmMpw = $('#confirm-Mpw').val();
			var nconfirmMpw = $('#new-confirm-Mpw').val();
			var MuserNick = $('#MuserNick').val();
			
			// formData 안에 데이터 확인
		    for (var pair of formData.entries()) {
		        console.log(pair[0]+ ': ' + pair[1]);
		    }
			
			if (currentMpw==null || currentMpw.length == 0) {
				erroAlert("🤔 현재 비밀번호를 입력해 주세요.", 'current-Mpw');
				return false;
			} else {
				commonMultiAjax("${ctx}/rest/member/memberUpdate", formData, function(response) {
					
					if (response.code == 0) {
						alert("😊 회원정보 수정이 완료되었습니다.");
						window.location.href = "${ctx}/main"; 
					} else {
						switch(response.code){
						case "-600":
							alert("로그인 상태가 아닙니다.");
							break;
						case "-500":
							alert("😣 비밀번호가 일치하지 않습니다.");
							break;
						case "-400":
							alert("😣 이미 사용 중인 닉네임입니다.");
							break;
						case "-300":
							alert("😣 입력한 값을 다시 확인해 주세요.");
							break;
						case "-200":
							alert("😣 중복된 닉네임 입니다.");
							break;
						}
						return false;
					}
				});
				return false;
			}	
			return false;
		});
		
		function submitImageForm() {
			document.getElementById('image-upload-form').submit();
		}
	</script>
	
	<script type="text/javascript">
    var ctx = "${ctx}";
    console.log("Context Path: " + ctx);
	</script>
	
</body>
</html>
