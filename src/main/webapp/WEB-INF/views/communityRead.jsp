<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE:PLOGGING 커뮤니티 읽기 전용 페이지</title>
<meta http-equiv="Content-Type" text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/communityRead.css">
<!-- 하트 아이콘을 위해 FontAwesome CDN 추가 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style>
    .post-footer {
        display: flex;
        justify-content: flex-end;
        align-items: center;
        margin-top: 20px;
    }

    .post-footer h3 {
        margin: 0;
    }
</style>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>게시물 상세보기</h1>
		</div>
		<div class="post-details">
			<h2 class="post-title">플로깅의 장점에 대해</h2>
			<p class="post-meta">카테고리: 플로깅 | 작성자: 홍길동 | 작성날짜: 2024-08-10</p>
			<div class="post-content">
				<h3>내용</h3>
				<p>
					플로깅은 스웨덴에서 시작된 환경 보호 운동으로, 달리기를 하면서 쓰레기를 줍는 활동을 의미합니다.
					이 활동은 개인의 체력 증진과 환경 정화라는 두 가지 목표를 동시에 달성할 수 있는 매우 유익한 운동입니다.
					특히, 도시에서 흔히 볼 수 있는 각종 플라스틱 쓰레기와 담배꽁초 등을 줍는 것은 환경 보호에 중요한 기여를 하며, 동시에 달리기를 통해 신체 건강도 챙길 수 있습니다.
				</p>
				<p>
					플로깅을 통해 개인은 자연과 더 가까워지고, 환경 문제에 대한 의식이 높아집니다. 또한, 지역 사회와 연대감을 느낄 수 있으며, 다른 사람들과 함께 플로깅을 하면
					더욱 즐겁고 동기 부여가 됩니다. 플로깅은 단순한 운동을 넘어 환경을 생각하는 활동으로 자리잡아가고 있으며, 많은 사람들이 이 활동에 동참하고 있습니다.
				</p>
				<p>
					이와 더불어, 플로깅은 스트레스를 해소하고, 정신 건강을 개선하는 데에도 도움이 됩니다. 자연 속에서 운동을 하며 마음을 정화하고, 환경 보호에 기여하는 성취감을 느낄 수 있기 때문입니다.
					또한, 플로깅은 특별한 장비나 기술이 필요하지 않아 누구나 쉽게 참여할 수 있습니다. 이런 이유로 전 세계적으로 플로깅의 인기는 점점 더 높아지고 있습니다.
				</p>
			</div>
			<div class="post-footer">
				<h3>
					<i class="fas fa-heart" style="color:red;"></i> <span>123</span>
				</h3>
			</div>
			<div class="attachments">
				<h3>첨부파일</h3>
				<a href="${pageContext.request.contextPath}/files/sample.pdf" class="btn">첨부파일 다운로드</a>
			</div>
		</div>
		<div class="navigation">
			<a href="${pageContext.request.contextPath}/community" class="btn">목록으로 돌아가기</a>
		</div>
	</div>
</body>
</html>
