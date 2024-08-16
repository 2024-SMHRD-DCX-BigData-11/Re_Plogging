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
<link rel="stylesheet" href="assets/css/myplogging.css">
</head>
<body>
	<div class="myplogging-container">
		<div class="cuPlogging-container">
			<div class="current-plogging">
				<span>현재 진행 중인 플로깅</span>
			</div>
			<div class="current-line"></div>
			<div class="current-course">
				<div class="inProgress-Name">
					<span>코스명 | 날짜</span>
				</div>
				<div class="inProgress-img">
					<div class="inProgress-courseQR1">
						<img src="img/플로깅_미완료.png" alt=""> <span>QR1</span>
					</div>
					<div class="inProgress-courseQR2">
						<img src="img/플로깅_미완료.png" alt=""> <span>QR2</span>
					</div>
					<div class="inProgress-courseQR3">
						<img src="img/플로깅_미완료.png" alt=""> <span>QR3</span>
					</div>
				</div>
			</div>
		</div>
		<div class="coPlogging-container">
				<div class="completed-plogging">
				<span>현재 진행 중인 플로깅</span>
			</div>
			<div class="completed-line"></div>
				<c:forEach var="plogging" items="">
					<div class="completed-item">
						<span class="completed-date">날짜</span>
						<span class="completed-course">코스</span>
						<button class="completed-btn">첨부하기</button>
					</div>
				</c:forEach>
			</div>
		</div>
</body>
</html>