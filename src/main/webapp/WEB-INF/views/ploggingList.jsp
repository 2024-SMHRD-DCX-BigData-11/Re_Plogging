<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE:PLOGGING PLOCOURSE</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/ploggingList.css">
</head>
	<body>
		<div class="plocourse-container">
			<div class="plocourseImg">
				<img alt="" src="img/플로코스.png">
			</div>
			<div class="plocourseInfo">
				<span class="plocourseInfo-top">PLOCOURSE<br>DETAIL</span>
				<span class="plocourseInfo-bottom">플로코스 안내</span>
			</div>
			
			<div class="Acourse-container">
		            <div class="A-arrow-icon"><img src="img/arrow.png"></div>
		            <div class="AC-title">
			            <span class="AC-name">A코스</span>
			            <span class="AC-place">순천대학교</span>
		            </div>
		            <div class="AC-img"><img src="img/순천대학교.png"></div>
		            <div class="Ac-spot">
		            	<div class="Ac-spot-title">
		            	<img alt="" src="img/지구.png"><h2>A코스 경유지</h2>
		            	</div>
		            	<ul class="Acouse-wrap">
		            		<li class="Acourse-box">
		            			<div class="Acourse-innner">01</div>
		            			<img src="img/location.png">
		            			<p class="QR">QR1</p>
		            		</li>
		            		<div class="left-arrow">&gt;</div>
		            		<li class="Acourse-box">
		            			<div class="Acourse-innner">02</div>
		            			<img src="img/location.png">
		            			<p class="QR">QR2</p>
		            		</li>
		            		<div class="left-arrow">&gt;</div>
		            		<li class="Acourse-box">
		            			<div class="Acourse-innner">03</div>
		            			<img src="img/location.png">
		            			<p class="QR">QR3</p>
		            		</li>
		            	</ul>
		            </div>
		            
		            <div class="Ac-Info">
		            <div class="Ac-Info-title">
		            	<img alt="" src="img/지구.png"><h2>코스소개</h2>
		            	</div>
		          		<div class="Acourse-Info">
		          			<span>순천대학교는 넓은 캠퍼스와 다양한 부속시설을 갖추고 있어 플로깅 코스로 이상적인 곳입니다.<br>
		          			캠퍼스 내의 풍부한 녹지와 조경, 그리고 다양한 연구 및 교육 시설을 둘러보며 운동할 수 있는 점이 매력적입니다.<br>
		          			또한, 다양한 나라와의 교류 협정을 통해 국제적인 분위기를 느낄 수 있어, 플로깅을 하며 다문화적인 경험도 할 수 있는 곳입니다.</span>
		          		</div>
		            </div>
			<button type="button" class="Acourse-start" onclick = "location.href='ploggingStart'">플로깅 시작</button>
		    </div>
		</div>
		
		<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>
	
	</body>
</html>