<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>관리자 페이지</title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


</head>
<body>
	<div id="memberList">
	<h1>회원 목록</h1>
		<table id="list">
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>전화번호</td>
					<td>닉네임</td>
					<td>가입일자</td>
					<td>플로깅횟수</td>
					<td>보유 마일리지</td>
				</tr>
			</thead>
			<tbody>
				<%-- 회원 목록 출력--%>
				<c:forEach var="member" items="${ulist}" >
				<tr>
					<td>${member.userIdx}</td>
					<td>${member.userId}</td>
					<td>${member.userPhone}</td>
					<td>${member.userNick}</td>
					<td>${member.joinedAt}</td>
					<td>${member.ploggingCount}</td>
					<td>${member.mileageAmount}</td>
					<td><a href="${ctx }/udelete?idx=${member.userIdx}">X</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	
	<div id="CommunityList">
	<h1>게시글 목록</h1>
		<table id="list">
			<thead>
				<tr>
					<td>글번호</td>
					<td>카테고리</td>
					<td>글제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>좋아요수</td>
					<td>첨부파일</td>
				</tr>
			</thead>
			<tbody>
				<%--게시글 목록 출력--%>
				<c:forEach var="community" items="${clist}" >
				<tr>
					<td></td>
					<td>${community.idx}</td>
					<td>${community.category}</td>
					<td><a href="${ctx }/cview?idx=${community.idx}">${community.title}</a></td>
					<td>${community.writer.userNick}</td>
					<td>${community.count}</td>
					<td>${community.likes}</td>
					<td>${community.img}</td>
					<td><a href="${ctx }/cdelete?idx=${community.idx}">X</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	<div id="MarketList">
	<h1>마켓판매글목록</h1>
		<table id="list">
			<thead>
				<tr>
					<td>상품번호</td>
					<td>카테고리</td>
					<td>상품명</td>
					<td>상품 마일리지</td>
					<td>판매자</td>
					<td>이미지1</td>
					<td>이미지2</td>
					<td>이미지3</td>
					<td>게시날짜</td>
					<td>판매상태</td>
					<td>완료날짜</td>
				</tr>
			</thead>
			<tbody>
				<%--마켓 게시글 목록 출력--%>
				<c:forEach var="market" items="${mlist}" >
				<tr>
					<td></td>
					<td>${market.mkIdx}</td>
					<td>${market.category}</td>
					<td><a href="${ctx }/mview?idx=${market.mkIdx}">${market.title}</a></td>
					<td>${market.mileage}</td>
					<td>${market.user.userNick}</td>
					<td>${market.img1}</td>
					<td>${market.img2}</td>
					<td>${market.img3}</td>
					<td>${market.createdAt}</td>
					<td>${market.status}</td>
					<td>${market.closedAt}</td>
					<td><a href="${ctx }/mdelete?idx=${market.mkIdx}">X</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>



</body>
</html>