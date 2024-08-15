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
            <h1>그린마켓</h1>
         </div>
         <div class="market_List_category">
            <div>
               <h3>카테고리</h3>
            </div>
            <div>
               <a class="market_List_category_li">종이</a> <a
                  class="market_List_category_li">캔</a> <a
                  class="market_List_category_li">유리</a> <a
                  class="market_List_category_li">페트</a> <a
                  class="market_List_category_li">플라스틱</a> <a
                  class="market_List_category_li">비닐</a> <a
                  class="market_List_category_li">스티로폼</a> <a
                  class="market_List_category_li">건전지</a>
            </div>
         </div>
         <div class="market_List_option">
            <div>
               <button
                  class="market_List_option_btn ${currentSort == 'latest' ? 'active' : ''}"
                  onclick="sortList('latest')">최신순</button>
               <button
                  class="market_List_option_btn ${currentSort == 'lowPrice' ? 'active' : ''}"
                  onclick="sortList('lowPrice')">낮은가격순</button>
               <button
                  class="market_List_option_btn ${currentSort == 'highPrice' ? 'active' : ''}"
                  onclick="sortList('highPrice')">높은가격순</button>
            </div>
         </div>
         <div class="market_card_wrapper">
            <c:choose>
               <c:when test="${!empty list}">
                  <c:forEach var="market" items="${list}">
                     <div class="market_card">
                        <a class="market_card_link"
                           href="${pageContext.request.contextPath}/marketRead?idx=${market.mkIdx}">
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
                     </div>
                  </c:forEach>
               </c:when>
               <c:otherwise>
                  <div class="market_no_data_message">
                     <span>현재 등록된 상품이 없습니다.</span>
                  </div>
               </c:otherwise>
            </c:choose>
         </div>

         <ul class="market_pagination">
            <c:if test="${currentPage > 1}">
               <li><a href="?page=${currentPage - 1}&size=8">&laquo;</a></li>
            </c:if>
            <c:forEach var="i" begin="1" end="${totalPages}">
               <li class="${i == currentPage ? 'active' : ''}"><a
                  href="?page=${i}&size=8">${i}</a></li>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
               <li><a href="?page=${currentPage + 1}&size=8">&raquo;</a></li>
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
</body>
</html>