<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
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
			<div class="market_List_category">
				<div>
				카테고리
				</div>
				<div>
				<a class="market_List_category_li">종이</a>
				<a class="market_List_category_li">캔</a>
				<a class="market_List_category_li">플라스틱</a>
				<a class="market_List_category_li">페트</a>
				<a class="market_List_category_li">비닐</a>
				</div>
			</div>
			<div class="market_List_option">
				<div>
				<button class="market_List_option_btn">최신순</button>
				<button class="market_List_option_btn">낮은가격순</button>
				<button class="market_List_option_btn">높은가격순</button>
				</div>
			</div>
			<div class="market_card">
				<c:forEach var="market" items="${list}">
					<a class="market_card_link" href="${pageContext.request.contextPath}/marketRead?idx=${market.mkIdx}">
						<div class="market_card_photo">
							<img src="${market.img1}" alt="${market.title}"
								class="market_card_photo_image">
						</div>
						<div class="market_card_desc">
							<div class="market_card_title">
								<h2>${market.title}</h2>
							</div>
							<div class="market_card_price">
								<span>${market.mileage}</span>
							</div>
							<div class="market_card_writer">
								<span>${market.user.userNick}</span>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		</div>
	</div>
	<script>
		/* function market_List_option1_ch() {
			descButton = document.getElementById('market_List_option1_desc');
			ascButton = document.getElementById('market_List_option1_asc');

			if (descButton.style.display == 'none') {
				descButton.style.display = '';
				ascButton.style.display = 'none';
			} else {
				descButton.style.display = 'none';
				ascButton.style.display = '';
			}
		} */
	</script>
	<script>
	</script>
</body>
</html>