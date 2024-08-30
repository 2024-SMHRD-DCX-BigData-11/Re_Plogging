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
			<div>
				<h1 id="market_title">그린마켓</h1>
			</div>
			<div class="market_List_category">
				<div class="market_List_Text">
					<h3>카테고리</h3>
				</div>
				<div class="market_List_categorys">
					<a
						class="market_List_category_li ${selectedCategory == null ? 'active' : ''}"
						href="/boot/market?sort=${currentSort}">전체보기</a> <a
						class="market_List_category_li ${selectedCategory == '560001' ? 'active' : ''}"
						href="/boot/market?category=560001&sort=${currentSort}">캔</a> <a
						class="market_List_category_li ${selectedCategory == '560002' ? 'active' : ''}"
						href="/boot/market?category=560002&sort=${currentSort}">유리</a> <a
						class="market_List_category_li ${selectedCategory == '560003' ? 'active' : ''}"
						href="/boot/market?category=560003&sort=${currentSort}">페트</a> <a
						class="market_List_category_li ${selectedCategory == '560004' ? 'active' : ''}"
						href="/boot/market?category=560004&sort=${currentSort}">플라스틱</a> <a
						class="market_List_category_li ${selectedCategory == '560005' ? 'active' : ''}"
						href="/boot/market?category=560005&sort=${currentSort}">비닐</a> <a
						class="market_List_category_li ${selectedCategory == '560006' ? 'active' : ''}"
						href="/boot/market?category=560006&sort=${currentSort}">스티로폼</a> <a
						class="market_List_category_li ${selectedCategory == '560007' ? 'active' : ''}"
						href="/boot/market?category=560007&sort=${currentSort}">종이</a> <a
						class="market_List_category_li ${selectedCategory == '560000' ? 'active' : ''}"
						href="/boot/market?category=560000&sort=${currentSort}">분류없음</a>
				</div>
			</div>

			<div class="market_List_option">
				<div>
					<button
						class="market_List_option_btn ${currentSort == 'latest' ? 'active' : ''}"
						onclick="sortList('latest')">최신순</button>
					<button
						class="market_List_option_btn ${currentSort == 'lowPrice' ? 'active' : ''}"
						onclick="sortList('lowPrice')">낮은 가격순</button>
					<button
						class="market_List_option_btn ${currentSort == 'highPrice' ? 'active' : ''}"
						onclick="sortList('highPrice')">높은 가격순</button>
				</div>
			</div>

			<c:choose>
				<c:when test="${!empty list}">
					<div class="market_card_wrapper">
						<c:forEach var="market" items="${list}">
							<c:if test="${market.status == '판매중'}">
								<div class="market_card">
									<a class="market_card_link"
										href="${pageContext.request.contextPath}/marketRead?idx=${market.mkIdx}">
										<div class="market_card_desc">
											<div class="market_card_photo">
												<img
													src="${pageContext.request.contextPath}/marketImage1?idx=${market.mkIdx}"
													alt="${market.title}" class="market_card_photo_image">
											</div>
											<div class="market_card_title">
												<h2 class="marketTitle">${market.title}</h2>
											</div>
											<div class="market_card_price">
												<span>${market.mileage}p</span>
											</div>
											<div class="market_card_category">
												<span>${market.category}</span>
											</div>
											<div class="market_card_writer">
												<span>${market.user.userNick}</span>
											</div>
										</div>
									</a>
								</div>
							</c:if>
							<c:if test="${market.status == '판매완료'}">
								<div class="market_card">
									<div class="market_card_link">
										<div class="market_card_desc">
											<div class="market_card_photo">
												<img
													src="${pageContext.request.contextPath}/marketImage1?idx=${market.mkIdx}"
													alt="${market.title}" class="market_card_photo_image">
												<span class="sold-out-label">판매완료</span>
											</div>
											<div class="market_card_title">
												<h2 class="marketTitle">${market.title}</h2>
											</div>
											<div class="market_card_price">
												<span>판매완료</span>
											</div>
											<div class="market_card_category">
												<span>${market.category}</span>
											</div>
											<div class="market_card_writer">
												<span>${market.user.userNick}</span>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</c:when>
				<c:otherwise>
					<div class="market_no_data_message">
						<span>현재 등록된 상품이 없습니다.</span>
					</div>
				</c:otherwise>
			</c:choose>

			<ul class="market_pagination">
				<c:if test="${currentPage > 1}">
					<li><a
						href="?page=${currentPage - 1}&size=8&sort=${currentSort}&category=${selectedCategory}">&laquo;
					</a></li>
				</c:if>

				<c:forEach var="i" begin="1" end="${totalPages}">
					<li><a class="${currentPage == i ? 'active' : ''}"
						href="?page=${i}&size=8&sort=${currentSort}&category=${selectedCategory}">${i}
					</a></li>
				</c:forEach>

				<c:if test="${currentPage < totalPages}">
					<li><a
						href="?page=${currentPage + 1}&size=8&sort=${currentSort}&category=${selectedCategory}">&raquo;
					</a></li>
				</c:if>
			</ul>

			<div class="market_Wirte">
				<c:if test="${!empty user}">
					<a href="${pageContext.request.contextPath}/marketWrite"
						class="market_Wirte_Btn">글쓰기</a>
				</c:if>
			</div>
		</div>
	</div>
	<script>
		function sortList(sortCriteria) {
			const currentUrl = window.location.href;
			const newUrl = new URL(currentUrl);
			newUrl.searchParams.set('sort', sortCriteria);

			const category = newUrl.searchParams.get('category');
			if (category) {
				newUrl.searchParams.set('category', category);
			}

			window.location.href = newUrl.href;
		}

		function setActive(button) {
			var buttons = document.querySelectorAll('.market_List_option_btn');
			buttons.forEach(function(btn) {
				btn.classList.remove('active');
			});

			button.classList.add('active');
		}

		document.addEventListener('DOMContentLoaded', function() {
			var selectedSort = new URL(window.location.href).searchParams
					.get('sort');
			if (selectedSort) {
				var buttons = document
						.querySelectorAll('.market_List_option_btn');
				buttons.forEach(function(button) {
					if (button.getAttribute('onclick').includes(selectedSort)) {
						button.classList.add('active');
					}
				});
			}
		});
	</script>
	<script type="text/javascript">
		window.onload = function() {
			const urlParams = new URLSearchParams(window.location.search);
			if (urlParams.has('purchaseSuccess')
					&& urlParams.get('purchaseSuccess') === 'true') {
				alert("🥳 상품 구매가 완료되었습니다.");
			}
		}
	</script>

	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>
</body>
</html>
