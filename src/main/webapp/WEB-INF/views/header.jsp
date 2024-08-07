<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE: PLOGGING</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="assets/css/header.css">
</head>
<body>
	<div class="header">
		<div class="menu-icon" onclick="openMenu()">
			<img src="img/menu.png">
		</div>
		<div class="logo">
			<a href="#"><img src="img/Re_Plogging_로고.png" alt=""></a>
		</div>
		<ul class="menu">
			<li>플로코스</li>
			<li>분리배출</li>
			<li>커뮤니티</li>
			<li>그린마켓</li>
			<li onclick="openModal()">로그인</li>
			<li class="menu-close" onclick="closeMenu()">X</li>
		</ul>

		<div class="user-icon" onclick="openModal()">
			<img src="img/비로그인.png">
		</div>
	</div>
	
	<!-- Scripts -->
	<script src="assets/js/header.js"></script>
</body>
</html>