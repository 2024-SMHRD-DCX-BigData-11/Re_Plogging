<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE:PLOGGING MYPAGE</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/myWrite.css">
</head>
<meta charset="UTF-8">
<title>RE:PLOGGING MYPAGE</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/myMileage.css">
</head>
<body>
<div class="checkMyMileage-container">
    <div class="Mileage-Details">
        <span>그린 마일리지 내역</span>
    </div>
    <div class="Mileaget-line"></div>
    <c:if test="${empty list}">
        <div class="Mileage-no-points">
        	<div class="no-Mileage">현재 적립된 마일리지가 존재하지 않습니다.</div>
        </div>
    </c:if>
    
    <c:if test="${not empty list}">
        <div class="Mileage-Details-innner">
            <div class="Mileage-nomal-wrapper">
                <span class="Mileage-nomal">${user.userNick}님의 현재 그린 마일리지</span>
                <span class="Mileage-point">${totalMileage}p</span>
            </div>
        </div>
        
        <div class="Mileage-tab">
            <span class="Mileage-tab-item">전체</span>
        </div>

        <div class="Mileage-list-container">
            <c:forEach var="myMileage" items="${list}">
                <div class="Mileage-item">
                    <div class="Mileage-item-title">
                        <span>${myMileage.mlType}</span>
                        <span class="Mileage-amount">${myMileage.mlAmount}p</span>
                    </div>
                    <div class="Mileage-item-info">
                        <span>${myMileage.mlLog} · <fmt:formatDate value="${myMileage.createdAt}" pattern="yyyy.MM.dd" /></span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>

	<script>
		// 숫자를 천 단위로 포맷팅하는 함수
		function formatNumber(num) {
			return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}

		// 모든 Mileage-amount 요소를 선택하여 포맷팅
		var mileageElements = document.querySelectorAll('.Mileage-amount');
		mileageElements.forEach(function(element) {
		    var mlAmount = parseInt(element.textContent, 10);
		    element.textContent = formatNumber(mlAmount) + 'p';
		});
	</script>

</body>
</html>