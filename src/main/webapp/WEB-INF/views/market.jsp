<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp" %>
<%@ include file="modal.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE: PLOGGING</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/market.css">
</head>
<body>
	<div class="container">
		<div class="market_List_wrap">
		<div class="market_List_option">
			<button id="market_List_option1_desc" style="display: ;" onclick="market_List_option1_ch()">▼ 정렬</button>
			<button id="market_List_option1_asc" style="display: none;" onclick="market_List_option1_ch()">▲ 정렬</button>
			<button id="market_List_option2">카테고리</button>
			<div class="market_List_option2_content">
        		<ul>
        			<li>캔</li>
        			<li>종이</li>
        			<li>페트병</li>
        			<li>플라스틱</li>
    			</ul>
      		</div>
		</div>
		<c:forEach var="market" items="${list}">
    		<div class="market_card">
        	<a class="market_card_link" href="#">
            <div class="market_card_photo">
                <img src="${market.img1}" alt="${market.title}" class="market_card_photo_image">
            </div>
            <div class="market_card_desc">
                <div class="market_card_title"><h2>${market.title}</h2></div>
                <div class="market_card_price"><span>${market.mileage}</span></div> <!-- 상품 가격 대신 mileage 출력 -->
                <div class="market_card_writer"><span>${market.user.userNick}</span></div> <!-- 판매자 바인딩 -->
            </div>
        </a>
    </div>
</c:forEach>
		</div>
	</div>
	<script>
	function market_List_option1_ch() {
		descButton = document.getElementById('market_List_option1_desc');
		ascButton = document.getElementById('market_List_option1_asc');
		
		if (descButton.style.display == 'none') {
	        descButton.style.display = '';
	        ascButton.style.display = 'none';
	    } else {
	        descButton.style.display = 'none';
	        ascButton.style.display = '';
	    }
	  }
	</script>
	<script>
	document.getElementById('market_List_option2').addEventListener('click', function() {
	    var dropdownContent = document.querySelector('.market_List_option2_content');
	    dropdownContent.classList.toggle('show');
	});

	// 페이지의 다른 부분을 클릭하면 드롭다운이 닫히도록 설정
	window.onclick = function(event) {
	    if (!event.target.matches('#market_List_option2')) {
	        var dropdowns = document.querySelectorAll('.market_List_option2_content');
	        dropdowns.forEach(function(dropdown) {
	            if (dropdown.classList.contains('show')) {
	                dropdown.classList.remove('show');
	            }
	        });
	    }
	}
	</script>
</body>
</html>