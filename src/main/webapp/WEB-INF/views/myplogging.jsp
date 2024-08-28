<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="assets/css/myplogging.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
<style>
</style>
</head>
<body>
	<div class="myplogging-container">
		<div class="cuPlogging-container">
			<div class="current-plogging">
				<span class="current-text">${user.userNick}님이 참여 중인 플로깅</span>
			</div>
			<div class="current-line"></div>
			<c:choose>
				<c:when
					test="${currentPlogging != null && (currentPlogging.qr1 != 1 || currentPlogging.qr2 != 1 || currentPlogging.qr3 != 1)}">
					<div class="current-course">
						<div class="inProgress-Name">
							<span>${currentPlogging.courseName} | <fmt:formatDate
									value="${currentPlogging.startedAt}"
									pattern="yyyy.MM.dd HH:mm:ss" /></span>
							<div class="ploggings-buttons">
								<button type="button" class="course-view-button"
									onclick="showCourseImage('${currentPlogging.courseName}')">코스
									확인하기</button>
								<form action="ploggingCancel" method="post"
									class="cancel-container"
									onsubmit="return confirm('정말로 플로깅을 취소하시겠습니까?')">
									<input type="hidden" name="userIdx"
										value="${currentPlogging.user.userIdx}"> <input
										type="hidden" name="ploggingIdx"
										value="${currentPlogging.idx}">
									<button type="submit">취소하기</button>
								</form>
							</div>
						</div>
						<div class="inProgress-img">
							<div class="inProgress-courseQR1">
								<span class="QRS">QR1</span> <img
									src="img/플로깅_${currentPlogging.qr1 == 1 ? '완료' : '미완료'}.png"
									alt=""> <span class="mycorseName">${currentPlogging.qr1Name}</span>
								<c:choose>
									<c:when test="${currentPlogging.qr1Time != null}">
										<span class="mycorseQrTime"><fmt:formatDate
												value="${currentPlogging.qr1Time}"
												pattern="yyyy.MM.dd HH:mm:ss" /></span>
									</c:when>
									<c:otherwise>
										<span class="mycorseQrTime">QR을 찍어주세요!</span>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="inProgress-courseQR2">
								<span class="QRS">QR2</span> <img
									src="img/플로깅_${currentPlogging.qr2 == 1 ? '완료' : '미완료'}.png"
									alt=""> <span class="mycorseName">${currentPlogging.qr2Name}</span>
								<c:choose>
									<c:when test="${currentPlogging.qr2Time != null}">
										<span class="mycorseQrTime"><fmt:formatDate
												value="${currentPlogging.qr2Time}"
												pattern="yyyy.MM.dd HH:mm:ss" /></span>
									</c:when>
									<c:otherwise>
										<span class="mycorseQrTime">QR을 찍어주세요!</span>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="inProgress-courseQR3">
								<span class="QRS">QR3</span> <img
									src="img/플로깅_${currentPlogging.qr3 == 1 ? '완료' : '미완료'}.png"
									alt=""> <span class="mycorseName">${currentPlogging.qr3Name}</span>
								<c:choose>
									<c:when test="${currentPlogging.qr3Time != null}">
										<span class="mycorseQrTime"><fmt:formatDate
												value="${currentPlogging.qr3Time}"
												pattern="yyyy.MM.dd HH:mm:ss" /></span>
									</c:when>
									<c:otherwise>
										<span class="mycorseQrTime">QR을 찍어주세요!</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="no-current-plogging">현재 참여 중인 플로깅이 존재하지 않습니다.</div>
				</c:otherwise>
			</c:choose>

		</div>

		<div class="coPlogging-container">
			<div class="completed-plogging">
				<span class="completed-text">${user.userNick}님이 완료한 플로깅</span>
			</div>
			<div class="completed-line"></div>
			<c:choose>
				<c:when test="${not empty completedPlogging}">
					<div class="plogging-table-container">
						<table class="plogging-table">
							<thead>
								<tr>
									<th>날짜</th>
									<th>코스명</th>
									<th>QR1</th>
									<th>QR2</th>
									<th>QR3</th>
									<th>총 포인트 적립</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="plogging" items="${completedPlogging}">
									<tr>
										<td><fmt:formatDate value="${plogging.qr3Time}"
												pattern="yyyy.MM.dd" /></td>
										<td style="font-weight: bold; color: #000;">${plogging.courseName}</td>
										<td>${plogging.qr1Name}<br>
										<fmt:formatDate value="${plogging.qr1Time}"
												pattern="yyyy.MM.dd HH:mm:ss" /></td>
										<td>${plogging.qr2Name}<br>
										<fmt:formatDate value="${plogging.qr2Time}"
												pattern="yyyy.MM.dd HH:mm:ss" /></td>
										<td>${plogging.qr3Name}<br>
										<fmt:formatDate value="${plogging.qr3Time}"
												pattern="yyyy.MM.dd HH:mm:ss" /></td>
										<td style="font-weight: bold; color: #00a989;">777p</td>
										<!-- points 컬럼이 총 포인트를 의미한다고 가정 -->
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<div class="no-completed-plogging">완료된 플로깅이 존재하지 않습니다.</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="myplogging-aiHelper-container" onclick="openMyploAiModal()">
		<span class="aiHeperText-nomal">내가 주운 쓰레기의 분리배출 방법이 궁금하다면?</span>
		<div class="aiHelper-content">
		<img src="img/AI.png" alt="AI Image">
		<span class="aiHeperText-point">AI 분리배출 도우미 이용해 보기</span>
		</div>
	</div>


	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>

	<script src="assets/js/myplogging.js"></script>
	
</body>
</html>