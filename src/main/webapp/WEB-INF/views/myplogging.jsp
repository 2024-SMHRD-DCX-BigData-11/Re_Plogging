<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE:PLOGGING MYPAGE</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/myplogging.css">
</head>
<body>
	<div class="myplogging-container">
		<div class="cuPlogging-container">
			<div class="current-plogging">
				<span class="current-text">현재 진행 중인 플로깅</span>
			</div>
			<div class="current-line"></div>
			<c:if test="${currentPlogging.qr1 != 1 || currentPlogging.qr2 != 1 || currentPlogging.qr3 != 1}">
    <div class="current-course">
        <div class="inProgress-Name">
            <span>${currentPlogging.courseName} | <fmt:formatDate value="${currentPlogging.startedAt}" pattern="yyyy.MM.dd"/></span>
        </div>
        <div class="inProgress-img">
            <div class="inProgress-courseQR1">
                <img src="img/플로깅_${currentPlogging.qr1 == 1 ? '완료' : '미완료'}.png" alt=""> <span>QR1</span>
            </div>
            <div class="inProgress-courseQR2">
                <img src="img/플로깅_${currentPlogging.qr2 == 1 ? '완료' : '미완료'}.png" alt=""> <span>QR2</span>
            </div>
            <div class="inProgress-courseQR3">
                <img src="img/플로깅_${currentPlogging.qr3 == 1 ? '완료' : '미완료'}.png" alt=""> <span>QR3</span>
            </div>
        </div>
    </div>
</c:if>

		</div>
		<div class="coPlogging-container">
				<div class="completed-plogging">
				<span class="completed-text">완료된 플로깅</span>
			</div>
			<div class="completed-line"></div>
				<c:forEach var="plogging" items="${completedPlogging}">
					<div class="completed-item">
						<span class="completed-date"><fmt:formatDate value="${plogging.qr3Time}" pattern="yyyy.MM.dd"/></span>
						<span class="completed-course">${plogging.courseName}</span>
						<button class="completed-btn">첨부하기</button>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>
		
		<script src="assets/js/myplogging.js"></script>
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>
</html>