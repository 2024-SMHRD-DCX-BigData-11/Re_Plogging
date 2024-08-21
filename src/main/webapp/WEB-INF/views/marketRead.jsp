<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="assets/css/marketRead.css">
</head>
<body>
    <div class="container">
            <a href="marketBack" class="market_Read_btn_back">뒤로가기</a>
        <div class="market_Read_wrapper">
            <div class="marketRead_img_view">
                <div class="marketRead_img_load">
                    <img src="${market.img1}" alt="${market.title}" class="marketRead_img__image">
                </div>
            </div>
            <div class="marketRead_info">
                <span>카테고리 |</span>
                <span>${market.category}</span><br>
                <span>상품명</span>
                <span>${market.title}</span><br>
                <h3>판매자</h3>
                <span>${market.user.userNick}</span><br>
                <h3>등록일</h3>
                <span>${market.createdAt}</span><br>
                <div>
                    <c:if test="${sessionScope.user != null && market.user.userIdx != sessionScope.user.userIdx}">
                        <form action="${pageContext.request.contextPath}/marketPurchase" method="post">
                            <input type="hidden" name="mkIdx" value="${market.mkIdx}">
                            <button type="submit">구매하기</button>
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.user != null && market.user.userIdx == sessionScope.user.userIdx}">
                        <form action="#" method="post">
                            <input type="hidden" name="mkIdx" value="${market.mkIdx}">
                            <button type="submit">수정하기</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/market/delete" method="post">
                            <input type="hidden" name="mkIdx" value="${market.mkIdx}">
                            <button type="submit">삭제하기</button>
                        </form>
                    </c:if>
                </div>
            </div>
            <div class="marketRead_content">
                <span>${market.content}</span>
            </div>
        </div>
    </div>
</body>
</html>
