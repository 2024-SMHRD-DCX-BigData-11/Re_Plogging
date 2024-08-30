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
<title>RE:PLOGGING 이미지 결과</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/aiResult.css">
<body>
	<div class="aiResult-container">
		<div class="aiResult-container-inner">
			<div class="aiResult-inner">
				<div class="aiResult-inner-left">
					<c:choose>
						<c:when test="${not empty resultImageName}">
							<img
								src="${pageContext.request.contextPath}/img/resultImg/${resultImageName}"
								alt="AI Result Image" />
						</c:when>
						<c:otherwise>
							<p>No image available</p>
						</c:otherwise>
					</c:choose>

					<%-- <img src="${pageContext.request.contextPath}/img/예시.jpg" alt="분석 결과"> --%>
				</div>
				<div class="aiResult-inner-right">
					<p class="inner-header">이미지 분석 결과</p>
					<canvas id="recyclingChart"></canvas>
					<p class="inner-header">총 적립 마일리지</p>
					<span class="inner-nomal">300p</span>
				</div>
			</div>
			<div class="aiResult-info">
				<p class="aiResult-info-title">#${item.category.categoryName }의 분리배출 방법</p>
				<div id="aiResult-info-commentGroup">
					<span class="aiResult-info-comment">${item.category.categoryInfo }</span>
				</div>
			</div>
			<div class="aiResult-interested">
				<p class="aiResult-interested-p">
					재활용 쓰레기로 만드는 <span class="aiResult-point">멋진 공예품</span>에 관심 있으신가요?
				</p>
				<ul id="detected-items-list">
					<!-- JavaScript로 동적으로 생성된 항목들이 여기에 추가됩니다 -->
				</ul>
				
			</div>
		</div>
	</div>
	
	<c:forEach var ="resultNC" items = "${resultNameAndCount }">
	
	${resultNC.c.categoryName }
	${resultNC.d.categoryCount }
	</c:forEach>
	
	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>
<script type="text/javascript">
	
$.ajax({
    url: "${pageContext.request.contextPath}/rest/char/createChart",
    type: "post",
    data: { idx : "${anal_idx }" },
    success: function (data) {
        console.log(data)
    },
    error: function (request, status, error) {
        console.log("code: " + request.status)
        console.log("message: " + request.responseText)
        console.log("error: " + error);
    }
});
	
	</script>
	<script src="assets/js/ai1.js"></script>
	
</body>
</html>