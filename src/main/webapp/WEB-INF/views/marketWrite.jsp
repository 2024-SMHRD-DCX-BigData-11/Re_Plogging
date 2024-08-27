<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>RE:PLOGGING MARKETWRITE</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/marketWrite.css">
</head>
<body>
    <div class="marketWrite-container">
                <h1>그린마켓 글쓰기</h1>
                <div class="marWrite-line"></div>
            <form action="${pageContext.request.contextPath}/marketPost" method="post" enctype="multipart/form-data">
                <div class="marWrite-group">
                <select id="category" class="marWrite-category" name="category" required>
                	<option value="" disabled selected>카테고리를 선택해 주세요.</option>
                    <option value="560000">분류없음</option>
                    <option value="560001">캔</option>
                    <option value="560002">유리</option>
                    <option value="560003">페트</option>
                    <option value="560004">플라스틱</option>
                    <option value="560005">비닐</option>
                    <option value="560006">스티로폼</option>
                    <option value="560007">종이</option>
                </select>
                <input type="text" id="mileage" class="marWrite-mileage" name="mileage" placeholder="상품 가격을 입력해 주세요." required>
                </div>
                <input type="text" id="title" class="marWrite-title" name="title" placeholder="상품 명을 입력해 주세요." required>
                <textarea id="content" class="commWrite-content" name="content" placeholder="내용을 입력해 주세요." required></textarea>

                <div class="plz-img">📢&nbsp;<span class="plzImg-point">상품 사진</span>을 올려주세요!</div>
                <div class="market-imgs">
                <div id="img1-container">
                <input type="file" id="img1" name="img1" onchange="showNextFileInput('img2-container')">
                <button type="button" id="deleteButton" class="marDeleteButton" onclick="removeFile()">삭제</button></div>
				
                <div id="img2-container" style="display: none;">
                    <input type="file" id="img2" name="img2" onchange="showNextFileInput('img3-container')">
                    <button type="button" id="deleteButton" class="marDeleteButton" onclick="removeFile()">삭제</button></div>
				
                <div id="img3-container" style="display: none;">
                    <input type="file" id="img3" name="img3">
                    <button type="button" id="deleteButton" class="marDeleteButton" onclick="removeFile()">삭제</button></div>
                </div>

                <input type="hidden" name="user" value="${sessionScope.user.userIdx}">
                <input type="hidden" name="status" value="0">

                <div class="marWrite-button-group">
                    <button type="submit" class="marWrite-btn">등록</button>
                    <a href="${pageContext.request.contextPath}/market" class="marWrite-btn-cancel">취소</a>
                </div>
            </form>
    </div>
    
    <footer>© 2024 지구수호대 Korea Corporation All Rights Reserved.</footer>
    
    <script>       
        function showNextFileInput(containerId) {
            const nextContainer = document.getElementById(containerId);
            if (nextContainer) {
                nextContainer.style.display = 'block';
            }
        }

        function removeFile(button) {
            const container = button.parentElement;
            const fileInput = container.querySelector('input[type="file"]');
            fileInput.value = '';  // 파일 입력 초기화

            // 컨테이너를 숨깁니다. 처음에만 숨기기 때문에 img1-container는 숨기지 않도록 합니다.
            if (container.id !== 'img1-container') {
                container.style.display = 'none';
            }

            button.style.display = 'none'; // 삭제 버튼 숨기기
        }

        // 파일 입력 필드에 파일이 업로드될 때 삭제 버튼 표시
        document.querySelectorAll('.market-imgs input[type="file"]').forEach(fileInput => {
            fileInput.addEventListener('change', function() {
                const deleteButton = this.parentElement.querySelector('button[type="button"]');
                if (this.files.length > 0) {
                    deleteButton.style.display = 'inline-block'; // 파일이 선택된 경우 삭제 버튼 표시
                } else {
                    deleteButton.style.display = 'none'; // 파일이 없으면 삭제 버튼 숨기기
                }
            });
        });

        // 모든 삭제 버튼에 `removeFile` 함수 적용
        document.querySelectorAll('.market-imgs button').forEach(button => {
            button.addEventListener('click', function() {
                removeFile(this);
            });
        });
    </script>
</body>
</html>
