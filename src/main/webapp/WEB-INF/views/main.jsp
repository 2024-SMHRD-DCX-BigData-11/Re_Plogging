<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>RE: PLOGGING</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/main.css">
</head>
<body>
	<div class="container">
		<div class="text">
			<img src="img/왕관.png" alt="왕관">
			<P class="normal">이달의</P>
			<P class="plo-point">플로킹!</P>
		</div>

		<div class="banner">
			<ul class="ploking">
				<c:forEach var="item" items="${topUser}" varStatus="status">
					<li>${status.index + 1}위 ${item.userNick}님! ${item.ploggingCount}회</li>
				</c:forEach>
			</ul>
			<!--<span>${topUserNick}</span>님! ${topPloggingCount}회-->
		</div>


	</div>

	<div class="plogging_01">
		<img src="img/메인_01.png" alt="Plogging Image">
		<div class="plogging_01-TC">
			<div id="plogging_01-title">
				<p class="title">
					Plogging<span class="dot">.</span>
				</p>
			</div>
			<div class="plogging_01-content">
				<span> 2016년에 스웨덴에서 시작된 플로깅은<br> 스웨덴어 ‘줍다’라는 의미의 ‘플로카
					우프’와<br> 영어 ‘조깅’의 합성어로<br> <span class="point">조깅하면서
						쓰레기를 줍는 행동</span>을 말합니다.
				</span>
			</div>
		</div>
	</div>

	<div class="plogging_02 fade-in-right">
		<img src="img/메인_02.png" alt="Plogging Image">
		<div class="plogging_02-TC">
			<div id="plogging_02-title">
				<p class="title">
					좋은점<span class="dot">.</span>
				</p>
			</div>
			<div class="plogging_02-content">
				<span> 달리기에 스쿼트 동작과 유사한 쓰레기 줍는 동작이 더해지면<br> 운동 효과가 강화되고
					칼로리 소모도 증가합니다.<br> 이를 통해<span class="point">체력 단련과 건강
						증진을 도모</span>할 수 있으며,<br> <span class="point">환경 보호 운동</span>으로
					지역사회에 공헌도 할 수 있습니다.
				</span>
			</div>
		</div>
	</div>


	<div class="plogging_03 fade-in-left">
		<img src="img/메인_03.png" alt="Plogging Image">
		<div class="plogging_03-TC">
			<div id="plogging_03-title">
				<p class="title">
					참여방법<span class="dot">.</span>
				</p>
			</div>
			<div class="plogging_03-content">
				<span> 플로깅에 참여하려면,<br> 쓰레기를 담을 튼튼한 <span class="point">봉투</span>와<br>손을
					보호하기 위한 <span class="point">장갑</span>을 준비하세요.<br> 또는, 쓰레기를 줍기
					위한 <span class="point">집게</span>를 사용할 수도 있습니다.<br> <br> <span
					class="point">친구</span>나 <span class="point">가족</span>과 함께 참여하면<br>
					더 즐겁고 효과적으로 플로깅을 즐길 수 있습니다.
				</span>
			</div>
		</div>
	</div>

	<div class="plogging_04 fade-in-right">
		<img src="img/메인_04.png" alt="Plogging Image">
		<div class="plogging_04-TC">
			<div id="plogging_04-title">
				<p class="title">
					분리배출<span class="dot">.</span>
				</p>
			</div>
			<div class="plogging_04-content">
				<span> 분리배출을 어려워하는 여러분들을 위해 플로깅을 통해<br> 수집한 쓰레기 이미지를
					첨부하거나,<br> <span class="point">분리배출 탭</span>에서 배출방법을 안내해드립니다.<br>
					<p>
						<a href="#" class="custom-button">분리배출 방법 보러가기</a>
					</p>
				</span>
			</div>
		</div>
	</div>

	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>

	<!-- Scripts -->
	<script src="assets/js/header.js"></script>
	<script src="assets/js/modal.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>