<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE: PLOGGING</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/market.css">
</head>
<body>
<div class="container">
   <div class="market_Write_wrap">
      <form action="${pageContext.request.contextPath}/marketPost" method="post" enctype="multipart/form-data">
         <h2>상품정보</h2>
      <label for="category">카테고리</label>
      <select id="category" name="category" required>
                <option value="560000">분류없음</option>
                <option value="560001">캔</option>
                <option value="560002">유리</option>
                <option value="560003">페트</option>
                <option value="560004">플라스틱</option>
                <option value="560005">비닐</option>
                <option value="560006">스티로폼</option>
                <option value="560007">종이</option>
            </select><br>
      <label for="title">상품명</label>
      <input type="text" id="title" name="title" placeholder="상품명을 입력하세요." required><br>
      
        <label for="mileage">상품가격</label>
      <input type="text" id="mileage" name="mileage" placeholder="상품가격을 입력하세요." required><br>
      
        <label for="content">상품설명</label><br>
        <textarea id="content" name="content" placeholder="내용을 입력하세요." required></textarea><br>
        
        <h2>상품사진</h2>
    <input type="file" id="img1" name="img1" onchange="showNextFileInput('img2-container')"><br>

    <div id="img2-container" style="display: none;">
        <input type="file" id="img2" name="img2" onchange="showNextFileInput('img3-container')"><br>
    </div>

    <div id="img3-container" style="display: none;">
        <input type="file" id="img3" name="img3">
    </div>
        
        <input type="hidden" name="user" value="${sessionScope.user.userIdx}">
        <input type="hidden" name="status" value="0">
        
        <div class="button-group">
                <button type="submit" class="market_write_btn">저장</button>
                <a href="${pageContext.request.contextPath}/market" class="market_write_btn_cancel">취소</a>
            </div>
      </form>
   </div>
</div>
<script>
        function showNextFileInput(nextContainerId) {
            const nextContainer = document.getElementById(nextContainerId);
            nextContainer.style.display = 'block';
        }
    </script>
</body>
</html>