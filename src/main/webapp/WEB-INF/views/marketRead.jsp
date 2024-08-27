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
<title>RE: PLOGGING</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/marketRead.css">
</head>
<body>
	<div class="container">
		<div class="market_Read_btn_back">
			<a href="marketBack" class="market_Read_btn_back_top">목록</a>
		</div>
		<div class="market_Read_wrapper">
			<div class="marketRead_img_view">
				<div class="marketRead_img_load">
					<div id="image1" style="display: block;">
						<img
							src="<%=request.getContextPath()%>/marketImage1?idx=${market.mkIdx}"
							alt="${market.title}" class="marketRead_img__image">
					</div>
					<c:if test="${market.img2 != null}">
						<div id="image2" style="display: none;">
							<img
								src="<%=request.getContextPath()%>/marketImage2?idx=${market.mkIdx}"
								alt="${market.title}" class="marketRead_img__image">
						</div>
					</c:if>
					<c:if test="${market.img3 != null}">
						<div id="image3" style="display: none;">
							<img
								src="<%=request.getContextPath()%>/marketImage3?idx=${market.mkIdx}"
								alt="${market.title}" class="marketRead_img__image">
						</div>
					</c:if>
					<div class="image_btn">
						<c:if test="${market.img2 != null}">
							<button id="showImage1" class="image-btn" onclick="showImage(1)"></button>
							<button id="showImage2" class="image-btn" onclick="showImage(2)"></button>
						</c:if>
						<c:if test="${market.img3 != null}">
							<button id="showImage3" class="image-btn" onclick="showImage(3)"></button>
						</c:if>
					</div>
				</div>
			</div>
			<div class="marketRead_info">
				<span id="marketRead_info_category">카테고리 </span> <span
					id="marketRead_info_category_content"># ${market.category}</span><br>
				<span id="marketRead_info_title">${market.title}</span><br> <span
					id="marketRead_info_mileage">${market.mileage}p</span><br> <span
					id="marketRead_info_span">판매자</span> <span
					id="marketRead_info_content">${market.user.userNick}</span><br>
				<span id="marketRead_info_span">등록일</span> <span
					id="marketRead_info_content"><fmt:formatDate
						value="${market.createdAt}" pattern="yyyy.MM.dd HH:mm" /></span><br>
				<div>
					<c:if
						test="${sessionScope.user != null && market.user.userIdx != sessionScope.user.userIdx}">
						<form id="purchaseForm"
							action="${pageContext.request.contextPath}/marketPurchase"
							method="post">
							<input type="hidden" name="mkIdx" value="${market.mkIdx}">
							<button type="button" class="purchase_btn"
								onclick="checkMileageAndSubmit()">구매하기</button>
						</form>
					</c:if>
					<c:if
						test="${sessionScope.user != null && market.user.userIdx == sessionScope.user.userIdx}">
						<form action="${pageContext.request.contextPath}/market/delete"
							method="post">
							<input type="hidden" name="mkIdx" value="${market.mkIdx}">
							<button type="submit" class="Delete_btn">삭제하기</button>
						</form>
					</c:if>
				</div>
			</div>
		</div>
		<div class="marketRead_content">
			<h4>상품정보</h4>
			<span>${market.content}</span>
		</div>
		<div class="market_Read_btn_back__bottom">
			<a href="marketBack" class="market_Read_btn_back_bottom">목록</a>
		</div>
	</div>

	<script>
	function showImage(imageNumber) {
	    const image1 = document.getElementById("image1");
	    const image2 = document.getElementById("image2");
	    const image3 = document.getElementById("image3");

	    if (image1) image1.style.display = "none";
	    if (image2) image2.style.display = "none";
	    if (image3) image3.style.display = "none";
	    
	    const buttons = document.querySelectorAll('.image-btn');
	    buttons.forEach(button => {
	        if (button) button.classList.remove('selected');
	    });

	    if (imageNumber === 1 && image1) {
	        image1.style.display = "block";
	        const showImage1Button = document.getElementById("showImage1");
	        if (showImage1Button) showImage1Button.classList.add('selected');
	    } else if (imageNumber === 2 && image2) {
	        image2.style.display = "block";
	        const showImage2Button = document.getElementById("showImage2");
	        if (showImage2Button) showImage2Button.classList.add('selected');
	    } else if (imageNumber === 3 && image3) {
	        image3.style.display = "block";
	        const showImage3Button = document.getElementById("showImage3");
	        if (showImage3Button) showImage3Button.classList.add('selected');
	    }
	}

	window.onload = function() {
	    showImage(1);
	};


</script>
	<script type="text/javascript">
var userMileage = ${totalMileage != null ? totalMileage : 0};
var itemMileage = ${market.mileage};

console.log("User Mileage (JS):", userMileage);
console.log("Item Mileage (JS):", itemMileage);

    function checkMileageAndSubmit() {
        if (userMileage < itemMileage) {
            alert("마일리지가 부족합니다!");
        } else {
            document.getElementById("purchaseForm").submit();
        }
    }
    </script>

	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>
</body>
</html>
