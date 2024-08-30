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
                <tbody id="change-pw-section" style="display:none;">
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
                        <button type="button" id="cancel-button">취소</button>
                        <button type="button" id="save-button">비밀번호 변경</button>
                    </td>
                </tr>
                </tbody>
                <tr>
                    <th>휴대폰번호</th>
                    <td><input type="text" id="MuserPhone" name="userPhone" value="${user.userPhone}" readonly></td>
                </tr>
                <tr>
                    <th>닉네임</th>
                    <td><input type="text" id="MuserNick" name="MuserNick" value="${user.userNick}" placeholder="새 닉네임을 입력하세요"></td>
                </tr>
            </table>
            <div class="Modify-button-wrapper">
                <button type="submit" class="Modify-button" id="submit-button">확인</button>
            </div>
        </form>
    </div>
    <footer> © 2024 지구수호대 Korea Corporation All Rights Reserved.</footer>
    <script src="assets/js/userInfoModify.js"></script>
    <script src="assets/js/modal.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">
        // 닉네임 변경 폼 제출
        $("#userInfo-form").submit(function(event) {
            event.preventDefault(); // 기본 폼 제출 동작을 막음
            var formData = new FormData($(this)[0]);
            
            var MuserNick = $('#MuserNick').val();
            
            if (MuserNick === '') {
                alert("닉네임을 입력해 주세요.");
                return false;
            }
            
            // 닉네임 중복 체크 및 닉네임 변경을 위한 AJAX 요청
            $.ajax({
                type: "POST",
                url: "${ctx}/rest/member/updateNickname",
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    console.log(response); // 서버 응답을 콘솔에 출력
                    
                    if (response.code == 0) {
                        alert("😊 닉네임이 성공적으로 변경되었습니다.");
                        window.location.href = "${ctx}/mypage"; 
                    } else if (response.code == -400) {
                        alert("😣 이미 사용 중인 닉네임입니다.");
                    } else {
                        alert("닉네임 변경에 실패했습니다. 다시 시도해 주세요.");
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("Error: " + textStatus + ", " + errorThrown); // 오류 메시지 출력
                    alert("닉네임 변경 중 오류가 발생했습니다.");
                }
            });
        });

        // 비밀번호 변경 버튼 기능
        $("#save-button").on("click", function() {
            var currentMpw = $('#current-Mpw').val();
            var confirmMpw = $('#confirm-Mpw').val();
            var nconfirmMpw = $('#new-confirm-Mpw').val();

            if (currentMpw === '' || confirmMpw === '' || nconfirmMpw === '') {
                alert("모든 비밀번호 필드를 입력해 주세요.");
                return false;
            }

            // 새 비밀번호와 새 비밀번호 확인이 일치하지 않는지 확인
            if (confirmMpw !== nconfirmMpw) {
                alert("😣 새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
                return false;
            }

            // 비밀번호 변경을 위한 AJAX 요청
            $.ajax({
                type: "POST",
                url: "${ctx}/rest/member/updatePassword",
                data: { currentMpw: currentMpw, nconfirmMpw: nconfirmMpw },
                success: function(response) {
                    console.log(response); // 서버 응답을 콘솔에 출력
                    
                    if (response.code == 0) {
                        alert("😊 비밀번호가 성공적으로 변경되었습니다.");
                        window.location.href = "${ctx}/mypage"; 
                    } else if (response.code == -500) {
                        alert("😣 현재 비밀번호가 일치하지 않습니다.");
                    } else {
                        alert("비밀번호 변경에 실패했습니다. 다시 시도해 주세요.");
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("Error: " + textStatus + ", " + errorThrown); // 오류 메시지 출력
                    alert("비밀번호 변경 중 오류가 발생했습니다.");
                }
            });
        });

        // 비밀번호 변경 취소 버튼
        $("#cancel-button").on("click", function() {
            document.getElementById('pw-row').style.display = 'table-row';
            document.getElementById('change-pw-section').style.display = 'none';
            document.getElementById('save-button').style.display = 'none'; // 비밀번호 변경 버튼 숨기기
            document.getElementById('submit-button').style.display = 'block'; // 확인 버튼 다시 보이게 하기
            document.getElementById('MuserNick').disabled = false; // 닉네임 입력창 다시 활성화
        });

        // 비밀번호 변경 버튼을 클릭하여 비밀번호 변경 섹션을 표시
        document.getElementById('change-pw-button').addEventListener('click', function() {
            document.getElementById('pw-row').style.display = 'none';
            document.getElementById('change-pw-section').style.display = 'table-row-group';
            document.getElementById('save-button').style.display = 'block'; // 비밀번호 변경 버튼 표시
            document.getElementById('submit-button').style.display = 'none'; // 확인 버튼 숨기기
            document.getElementById('MuserNick').disabled = true; // 닉네임 입력창 비활성화
        });

        function submitImageForm() {
            document.getElementById('image-upload-form').submit();
        }

        function onClickUpload() {
            let fileInput = document.getElementById("file-input");
            fileInput.click();
        }

    </script>
    
    <script type="text/javascript">
        var ctx = "${ctx}";
        console.log("Context Path: " + ctx);
    </script>
    
</body>
</html>
