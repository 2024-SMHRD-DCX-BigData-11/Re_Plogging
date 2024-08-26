<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    <img src="<%=request.getContextPath()%>/marketImage1?idx=${market.mkIdx}" alt="${market.title}" class="marketRead_img__image">
                </div>
            </div>
            <div class="marketRead_info">
                <span id="marketRead_info_category">카테고리 </span>
                <span id="marketRead_info_category_content"># ${market.category}</span><br>
                <span id="marketRead_info_title">${market.title}</span><br>
                <span id="marketRead_info_mileage">${market.mileage}p</span><br>
                <span id="marketRead_info_span">판매자</span>
                <span id="marketRead_info_content">${market.user.userNick}</span><br>
                <span id="marketRead_info_span">등록일</span>
                <span id="marketRead_info_content"><fmt:formatDate value="${market.createdAt}" pattern="yyyy.MM.dd HH:mm" /></span><br>
                <div>
                    <c:if test="${sessionScope.user != null && market.user.userIdx != sessionScope.user.userIdx}">
                        <form action="${pageContext.request.contextPath}/marketPurchase" method="post">
                            <input type="hidden" name="mkIdx" value="${market.mkIdx}">
                            <button type="submit" class="purchase_btn">구매하기</button>
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.user != null && market.user.userIdx == sessionScope.user.userIdx}">
                        <form action="${pageContext.request.contextPath}/market/delete" method="post">
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
</body>
</html>
