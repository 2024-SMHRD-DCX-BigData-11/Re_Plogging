<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE:PLOGGING MANAGER</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/manager.css">
</head>
<body>
	<div class="manager-header">
		<div class="manager-logo">
			<a href="${pageContext.request.contextPath}/main"><img
				src="img/Re_Plogging_로고(관리자).png" alt=""></a>
		</div>
	</div>

	<div class="manager-container">
		<div id="memberList">
			<h1>회원 목록</h1>
			<table id="list">
				<thead>
					<tr>
						<th>회원번호</th>
						<th>아이디</th>
						<th>전화번호</th>
						<th>닉네임</th>
						<th>가입일자</th>
						<th>플로깅 횟수</th>
						<th>보유 마일리지</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%-- 회원 목록 출력--%>
					<c:forEach var="member" items="${ulist}">
						<tr>
							<td>${member.userIdx}</td>
							<td>${member.userId}</td>
							<td>${member.userPhone}</td>
							<td>${member.userNick}</td>
							<td><fmt:formatDate value="${member.joinedAt}"
									pattern="yyyy.MM.dd HH:mm:ss" /></td>
							<td>${member.ploggingCount}</td>
							<td>${member.mileageAmount}</td>
							<td><a href="${ctx }/udelete?idx=${member.userIdx}">회원
									탈퇴</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>



		<div id="CommunityList">
			<h1>커뮤니티 작성 글 목록</h1>
			<table id="list">
				<thead>
					<tr>
						<th>글번호</th>
						<th>카테고리</th>
						<th>글 제목</th>
						<th>작성자</th>
						<th>작성 날짜</th>
						<th>조회수</th>
						<th>좋아요수</th>
						<th>첨부파일</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%--게시글 목록 출력--%>
					<c:forEach var="community" items="${clist}">
						<tr>
							<td>${community.idx}</td>
							<td>${community.category}</td>
							<td><a href="${ctx }/cview?idx=${community.idx}">${community.title}</a></td>
							<td>${community.writer.userNick}</td>
							<td><fmt:formatDate value="${community.indate}"
									pattern="yyyy.MM.dd HH:mm:ss" /></td>
							<td>${community.count}</td>
							<td>${community.likes}</td>
							<td>${community.img != null ? community.img : '-'}</td>
							<td><a href="${ctx }/cdelete?idx=${community.idx}">게시물
									삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


		<div id="MarketList">
			<h1>그린마켓 작성 글 목록</h1>
			<table id="list">
				<thead>
					<tr>
						<th>상품번호</th>
						<th>카테고리</th>
						<th>상품명</th>
						<th>상품 마일리지</th>
						<th>판매자</th>
						<th>이미지1</th>
						<th>이미지2</th>
						<th>이미지3</th>
						<th>판매 상태</th>
						<th>게시 날짜</th>
						<th>거래 완료 날짜</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%--마켓 게시글 목록 출력--%>
					<c:forEach var="market" items="${mlist}">
						<tr>
							<td>${market.mkIdx}</td>
							<td>${market.category}</td>
							<td><a href="${ctx }/mview?idx=${market.mkIdx}">${market.title}</a></td>
							<td>${market.mileage}</td>
							<td>${market.user.userNick}</td>
							<td>${market.img1 != null ? market.img1 : '-'}</td>
							<td>${market.img2 != null ? market.img2 : '-'}</td>
							<td>${market.img3 != null ? market.img3 : '-'}</td>
							<td>${market.status}</td>
							<td><fmt:formatDate value="${market.createdAt}"
									pattern="yyyy.MM.dd HH:mm:ss" /></td>
							<td><c:choose>
									<c:when test="${market.closedAt != null}">
										<fmt:formatDate value="${market.closedAt}"
											pattern="yyyy.MM.dd HH:mm:ss" />
									</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose></td>
							<td><a href="${ctx }/mdelete?idx=${market.mkIdx}">판매 글
									삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>

	<script src="assets/js/manager.js"></script>
</body>
</html>