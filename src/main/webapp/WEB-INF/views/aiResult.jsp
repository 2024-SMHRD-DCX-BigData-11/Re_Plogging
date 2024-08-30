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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.7.1.slim.min.js"
	integrity="sha256-kmHvs0B+OpCW5GVHUNjv9rOmY0IvSIRcf7zGUDTDQM8="
	crossorigin="anonymous"></script>

<script>
/* $('#fileSubmitBtn').on("click",function(){
	var form = $('#aiFile')[0].files[0];
	var formData = new FormData();
	
	formData.append('files', form);
	 $.ajax({
	        type: "POST",
	        enctype: 'multipart/form-data',
	        url: "http;//localhot:5001/dsjkfsjdfk",
	        data: formData,
	        processData: false,
	        contentType: false,
	        cache: false,
	        timeout: 600000,
	        success: function (data) {
	        	
	        	
	        $('#data1').text(ddjskj);
	        $('#data1').text(ddjskj);
	        
	        
	        js ..dfjsljdfkjs
	        
	        
	        
	        $('#chart').html(ddjskj);
	        
	        $('#data1').text(ddjskj);
	        $('#data1').text(ddjskj);
	        
	        	
	            alert("성공");
	        },
	        error: function (e) {
	            alert("실패");
	        }
	    });
}); */
</script>
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
							<p>이미지 분석 결과가 존재하지 않습니다.</p>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="aiResult-inner-right">
					<p class="inner-header">#이미지 분석 결과</p>
					<canvas id="recyclingChart"></canvas>

					<span class="inner-header">#총 적립 마일리지 <span
						class="tooltip-container"> <i
							class="fa-solid fa-circle-exclamation"></i>
							<div class="tooltip-box">
								<ul>
									<li>종이: <span>10p</span></li>
									<li>캔: <span>50p</span></li>
									<li>유리: <span>50p</span></li>
									<li>페트: <span>30p</span></li>
									<li>플라스틱: <span>25p</span></li>
									<li>비닐: <span>10p</span></li>
									<li>스티로폼: <span>30p</span></li>
									<li>건전지: <span>50p</span></li>
								</ul>
							</div>
					</span>
					</span> <span class="inner-nomal"> <c:forEach var="result"
							items="${list }">
							<%-- 							<div>
								<c:set var="sum" value = "${result.categoryCount * result.category.categoryMileage } "  />
								${result.category.categoryName } ( ${result.categoryCount } ) * ${result.category.categoryMileage } = ${sum }
							</div> --%>
						</c:forEach> ${total }p
					</span>
				</div>
			</div>
			<c:forEach var="result" items="${list }">
				<div class="aiResult-info">
					<p class="aiResult-info-title">#${result.category.categoryName
						}의 분리배출 방법</p>
					<div id="aiResult-info-commentGroup">
						<span class="aiResult-info-comment">${result.category.categoryInfo }</span>
					</div>
				</div>
			</c:forEach>
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

	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>
	
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script type="text/javascript">

$.ajax({
    url: "${pageContext.request.contextPath}/rest/char/createChart",
    type: "post",
    data: { idx : "${anal_idx }" },
    success: function (data) {
    	
    	console.log( data );
    	
    	var ctx = document.getElementById('recyclingChart').getContext('2d');
    	var recyclingChart = new Chart(ctx, {
    	    type: 'bar',
    	    data: {
    	        labels: data.xValue,
    	        datasets: [{
    	            label: '검출된 갯수',
    	            data: data.yValue,
    	            backgroundColor: 'rgba(0, 169, 137, 0.2)', // 수정된 배경색
    	            borderColor: '#00a989', // 수정된 테두리 색상
    	            borderWidth: 1
    	        }]
    	    },
    	    options: {
    	        plugins: {
    	            legend: {
    	                display: true,
    	                position: 'bottom' // 라벨 위치를 아래로 설정
    	            }
    	        },
    	        scales: {
    	            y: {
    	                beginAtZero: true
    	            }
    	        }
    	    }
    	});
    	
    },
    error: function (request, status, error) {
        console.log("code: " + request.status)
        console.log("message: " + request.responseText)
        console.log("error: " + error);
    }
});
	
$(".aiResult-interested").on("click", function(event) {
    var $detectedItemsList = $("#detected-items-list");
    
    // 토글 기능 추가
    if ($detectedItemsList.css("display") === "block") {
        $detectedItemsList.css("display", "none");
    } else {
        $.ajax({
            url: "${pageContext.request.contextPath}/rest/char/getUrlList",
            data: { idx: "${anal_idx }" },
            type: "post"
        }).done(function(data) {
            var youtube = data.youtube,
                nameList = data.nameList,
                urlList = data.urlList,
                appendHtml = "";

            for (var i = 0; i < nameList.length; i++) {
                appendHtml += "<li><a href='" + youtube + urlList[i] + "' >" + nameList[i] + " YOUTUBE 바로가기</a></li>";
            }

            $detectedItemsList.empty().append(appendHtml);
            $detectedItemsList.css("display", "block");
        });
    }
});

	
	
    window.addEventListener('resize', function() {
        if (window.innerWidth <= 768) { // 768px 이하일 때 모바일 화면으로 인식
            document.querySelector('.aiResult-interested-p').innerHTML = 
                '재활용 쓰레기로 만드는 <br><span class="aiResult-point">멋진 공예품</span>에 관심 있으신가요?';
        } else {
            document.querySelector('.aiResult-interested-p').innerHTML = 
                '재활용 쓰레기로 만드는 <span class="aiResult-point">멋진 공예품</span>에 관심 있으신가요?';
        }
    });

    // 초기 로드 시 체크
    if (window.innerWidth <= 768) {
        document.querySelector('.aiResult-interested-p').innerHTML = 
            '재활용 쓰레기로 만드는 <br><span class="aiResult-point">멋진 공예품</span>에 관심 있으신가요?';
    }
	
	
	$(document).ready(function(){
	    $('.tooltip-container').on('click', function(event) {
	        event.stopPropagation(); // 클릭 이벤트 전파 방지
	        $(this).find('.tooltip-box').toggle(); // 툴팁 박스를 토글(보이기/숨기기)
	    });

	    // 페이지 다른 곳을 클릭했을 때 툴팁 닫기
	    $(document).on('click', function(event) {
	        if (!$(event.target).closest('.tooltip-container').length) {
	            $('.tooltip-box').hide(); // 툴팁 박스 숨기기
	        }
	    });
	});
	</script>
	
</body>
</html>