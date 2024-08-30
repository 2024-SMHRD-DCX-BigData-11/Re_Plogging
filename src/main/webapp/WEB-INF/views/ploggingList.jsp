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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
</head>
<body>
		<div class="plocourseImg">
			<img id="plocourseImage" alt="" src="img/플로코스.png">
		</div>
	<div class="plocourse-container">
		<div class="plocourseInfo">
			<span class="plocourseInfo-top">PLOCOURSE<br>DETAIL
			</span> <span class="plocourseInfo-bottom">플로코스 안내</span>
		</div>

		<div class="Acourse-container fade-up">
			<div class="arrow-icon">
				<img src="img/arrow.png">
			</div>
			<div class="course-title">
				<span class="course-name">A코스</span> <span class="course-place">순천대학교</span>
			</div>
			<div class="course-img">
				<img src="img/A코스.png">
			</div>
			<div class="course-spot">
				<div class="spot-title">
					<img alt="" src="img/지구.png">
					<h2>A코스 경유지</h2>
				</div>
				<ul class="couse-wrap">
					<li class="course-box">
						<div class="course-innner">01</div> <img src="img/location.png">
						<span class="QR">QR1</span> <span class="QRNick">열린광장</span>

					</li>
					<div class="left-arrow">&gt;</div>
					<li class="course-box">
						<div class="course-innner">02</div> <img src="img/location.png">
						<span class="QR">QR2</span> <span class="QRNick">공과대학 1호관</span>
					</li>
					<div class="left-arrow">&gt;</div>
					<li class="course-box">
						<div class="course-innner">03</div> <img src="img/location.png">
						<span class="QR">QR3</span> <span class="QRNick">대학본부</span>
					</li>
				</ul>
			</div>

			<div class="course-Info">
				<div class="Info-title">
					<img alt="" src="img/지구.png">
					<h2>코스 소개</h2>
				</div>
				<div class="c-Info">
					<span>순천대학교는 넓은 캠퍼스와 다양한 부속시설을 갖추고 있어 플로깅 코스로 이상적인 곳입니다.<br>
						캠퍼스 내의 풍부한 녹지와 조경, 그리고 다양한 연구 및 교육 시설을 둘러보며 운동할 수 있는 점이 매력적입니다.<br>
						또한, 다양한 나라와의 교류 협정을 통해 국제적인 분위기를 느낄 수 있어, 플로깅을 하며 다문화적인 경험도 할 수 있는
						곳입니다.
					</span>
				</div>
			</div>
			<c:if test="${!empty user}">
				<button type="button" class="course-start"
					onclick="location.href='ploggingStartA'">플로깅 시작</button>
			</c:if>

			<!-- 로그인이 되어 있지 않을 때 -->
			<c:if test="${empty user}">
				<button type="button" class="course-start"
					onclick="showAlertWithPloggingStartError()">플로깅 시작</button>
			</c:if>
		</div>

		<!-- B코스 -->
		<div class="Bcourse-container fade-up">
			<div class="arrow-icon">
				<img src="img/arrow.png">
			</div>
			<div class="course-title">
				<span class="course-name">B코스</span> <span class="course-place">순천
					조례호수공원</span>
			</div>
			<div class="course-img">
				<img src="img/B코스.png">
			</div>
			<div class="course-spot">
				<div class="spot-title">
					<img alt="" src="img/지구.png">
					<h2>B코스 경유지</h2>
				</div>
				<ul class="couse-wrap">
					<li class="course-box">
						<div class="course-innner">01</div> <img src="img/location.png">
						<span class="QR">QR1</span> <span class="QRNick">야외무대</span>

					</li>
					<div class="left-arrow">&gt;</div>
					<li class="course-box">
						<div class="course-innner">02</div> <img src="img/location.png">
						<span class="QR">QR2</span> <span class="QRNick">산책로1</span>
					</li>
					<div class="left-arrow">&gt;</div>
					<li class="course-box">
						<div class="course-innner">03</div> <img src="img/location.png">
						<span class="QR">QR3</span> <span class="QRNick">산책로2</span>
					</li>
				</ul>
			</div>

			<div class="course-Info">
				<div class="Info-title">
					<img alt="" src="img/지구.png">
					<h2>코스 소개</h2>
				</div>
				<div class="c-Info">
					<span>조례호수공원은 호수를 중심으로 한 산책로와 넓은 광장, 분수대 등이 조성되어 있어 플로깅을 하기에
						탁월한 장소입니다.<br> 공원은 자연 속에서 편안한 산책과 플로깅을 즐길 수 있는 공간으로, 순천 시민들의
						건강 증진과 여가 생활을 위한 도심 속 쉼터 역할을 합니다.<br> 또한, 정기적으로 열리는 문화 페스티벌과
						소규모 바자회는 플로깅 후 다양한 즐길 거리를 제공해 줍니다.
					</span>
				</div>
			</div>
			<!-- 로그인 상태일 때 -->
			<c:if test="${!empty user}">
				<button type="button" class="course-start"
					onclick="location.href='ploggingStartB'">플로깅 시작</button>
			</c:if>

			<!-- 로그인이 되어 있지 않을 때 -->
			<c:if test="${empty user}">
				<button type="button" class="course-start"
					onclick="showAlertWithPloggingStartError()">플로깅 시작</button>
			</c:if>
		</div>

		<!-- C코스 -->
		<div class="Ccourse-container fade-up">
			<div class="arrow-icon">
				<img src="img/arrow.png">
			</div>
			<div class="course-title">
				<span class="course-name">C코스</span> <span class="course-place">순천
					오천그린광장</span>
			</div>
			<div class="course-img">
				<img src="img/C코스.png">
			</div>
			<div class="course-spot">
				<div class="spot-title">
					<img alt="" src="img/지구.png">
					<h2>C코스 경유지</h2>
				</div>
				<ul class="couse-wrap">
					<li class="course-box">
						<div class="course-innner">01</div> <img src="img/location.png">
						<span class="QR">QR1</span> <span class="QRNick">오천그린광장</span>

					</li>
					<div class="left-arrow">&gt;</div>
					<li class="course-box">
						<div class="course-innner">02</div> <img src="img/location.png">
						<span class="QR">QR2</span> <span class="QRNick">산책로1</span>
					</li>
					<div class="left-arrow">&gt;</div>
					<li class="course-box">
						<div class="course-innner">03</div> <img src="img/location.png">
						<span class="QR">QR3</span> <span class="QRNick">산책로2</span>
					</li>
				</ul>
			</div>

			<div class="course-Info">
				<div class="Info-title">
					<img alt="" src="img/지구.png">
					<h2>코스 소개</h2>
				</div>
				<div class="c-Info">
					<span>오천그린광장은 대규모 저류지를 정원으로 탈바꿈시킨 독특한 공간으로,<br> 다양한 수목과
						어싱길이 조성되어 있어 자연과 함께하는 산책과 플로깅에 최적화된 장소입니다.<br> 사계절 내내 푸른 잔디와
						어싱길은 운동 중 발의 피로를 줄여주며, 쾌적한 환경에서 플로깅을 즐길 수 있습니다.<br> 또한, 경관조명과
						바닥분수 등 다양한 볼거리가 마련되어 있어 운동 중에도 지루하지 않게 주변 경관을 감상할 수 있습니다.
					</span>
				</div>
			</div>
			<!-- 로그인 상태일 때 -->
			<c:if test="${!empty user}">
				<button type="button" class="course-start"
					onclick="location.href='ploggingStartC'">플로깅 시작</button>
			</c:if>

			<!-- 로그인이 되어 있지 않을 때 -->
			<c:if test="${empty user}">
				<button type="button" class="course-start"
					onclick="showAlertWithPloggingStartError()">플로깅 시작</button>
			</c:if>
		</div>
	</div>

	<button type="button" id="button-page-top-btn" onclick="scrolltotop()">
		<i class="fa-solid fa-arrow-up"></i>
	</button>

	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>

	<script src="assets/js/ploggingList.js"></script>
</body>
</html>