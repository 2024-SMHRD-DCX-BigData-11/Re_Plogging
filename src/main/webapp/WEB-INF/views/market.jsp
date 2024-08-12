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
			<span>정렬</span>
			<span>카테고리</span>
		</div>
			<div class="market_card">
				<a class="market_card_link">
					<div class="market_card_photo">
						<img src="img/임시.png" alt="임시 상품 이미지" class="market_card_photo_image">
					</div>
					<div class="market_card_desc">
						<div class="market_card_title">{상품명}</div>
						<div class="market_card_price">{상품가격}</div>
						<div class="market_card_writer">{판매자}</div>
					</div>
				</a>
				<a class="market_card_link">
					<div class="market_card_photo">
						<img src="img/임시.png" alt="임시 상품 이미지" class="market_card_photo_image">
					</div>
					<div class="market_card_desc">
						<div class="market_card_title">{상품명}</div>
						<div class="market_card_price">{상품가격}</div>
						<div class="market_card_writer">{판매자}</div>
					</div>
				</a>
				<a class="market_card_link">
					<div class="market_card_photo">
						<img src="img/임시.png" alt="임시 상품 이미지" class="market_card_photo_image">
					</div>
					<div class="market_card_desc">
						<div class="market_card_title">{상품명}</div>
						<div class="market_card_price">{상품가격}</div>
						<div class="market_card_writer">{판매자}</div>
					</div>
				</a>
			</div>
		</div>
	</div>
</body>
</html>