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
		<select id="main-category" name="main-category"
			onchange="fetchRecycleData()">
			<option value="" disabled selected>원하는 분리배출 대분류를 선택해 주세요.</option>
			<option value="종이류">종이류</option>
			<option value="종이팩·종이컵">종이팩·종이컵</option>
			<option value="금속캔">금속캔</option>
			<option value="고철류">고철류</option>
			<option value="유리병">유리병</option>
			<option value="페트병">페트병</option>
			<option value="플라스틱 용기류">플라스틱 용기류</option>
			<option value="비닐류(필름류)">비닐류(필름류)</option>
			<option value="발포합성수지(스티로폼)">발포합성수지(스티로폼)</option>
			<option value="기타">기타</option>
		</select>
		<div class="recycle-content">
			<div class="recycle-content-left">
				<img src="data:image/jpeg;base64,${recycleImage}" alt="recycle"
					class="item-image" id="recycleImage">
				<h2 id="recycleTarget">#${recycle.recycleTarget}</h2>
			</div>
			<div class="recycle-content-right">
				<div class="recycle-group">
					<p class="right-Pone">#재활용 여부
					<p>
						<span class="right-Sone" id="recycleStatus">${recycle.recycleStatus}</span>
				</div>
				<div class="recycle-group">
					<p class="right-Pone">#분류
					<p>
						<span class="right-Sone" id="recycleCategory">${recycle.recycleCategory}</span>
				</div>
				<div class="recycle-group">
					<p class="right-Pone">#분리배출 방법
					<p>
						<span class="right-Sone" id="recycleMethod">${recycle.recycleMethod}</span>
				</div>
			</div>
		</div>
		<div class="pagination">
			<a href="#" id="prev">이전</a>
			<span class="page-number" id="page">${currentPage} / ${totalPages}</span>
			<a href="#" id="next">다음</a>
		</div>
	</div>
	<script src="assets/js/recycle.js"></script>
</body>
</html>