<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE:PLOGGING RECYCLE</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="assets/css/recycle.css">
</head>
<body>
	 <div class="recycle-container">
        <select id="main-category" name="main-category">
            <option value="" disabled selected>원하는 분리배출 대분류를 선택해 주세요.</option>
            <option value="종이류">종이류</option>
            <option value="금속캔">금속캔</option>
            <option value="고철">고철</option>
            <option value="유리병">유리병</option>
            <option value="페트병">페트병</option>
            <option value="플라스틱 용기류">플라스틱 용기류</option>
            <option value="비닐류(필름류)">비닐류(필름류)</option>
            <option value="스티로폼">스티로폼</option>
            <option value="기타">기타</option>
        </select>

        <!-- 두 번째 드롭다운: 세부 분류 -->
        <select id="sub-category" name="sub-category" style="display:none;">
            <option value="" disabled selected>세부 분류를 선택해 주세요.</option>
        </select>
        <div class="recycle-content">
            <div class="recycle-content-left">
                <img src="data:image/jpeg;base64,${recycleImage}" alt="recycle" class="item-image">
                <h2>#${recycle.recycleTarget}</h2>
            </div>
            <div class="recycle-content-right">
            	<div class="recycle-group">
	                <p class="right-Pone">#재활용 여부<p>
	                <span class="right-Sone">${recycle.recycleStatus}</span>
            	</div>
            	<div class="recycle-group">
	                <p class="right-Pone">#분류<p>
	                <span class="right-Sone">${recycle.recycleCategory}</span>
            	</div>
            	<div class="recycle-group">
	                <p class="right-Pone">#분리배출 방법<p>
	                <span class="right-Sone">${recycle.recycleMethod}</span>
            	</div>
            </div>
        </div>
        <div class="pagination">
            <c:choose>
                <c:when test="${currentPage > 1}">
                    <a href="?page=${currentPage - 1}">이전</a>
                </c:when>
                <c:otherwise>
                    <span>이전</span>
                </c:otherwise>
            </c:choose>

            <span class="page-number">${currentPage} / ${totalPages}</span>

            <c:choose>
                <c:when test="${currentPage < totalPages}">
                    <a href="?page=${currentPage + 1}">다음</a>
                </c:when>
                <c:otherwise>
                    <span>다음</span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <script src="assets/js/recycle.js"></script>
</body>
</html>