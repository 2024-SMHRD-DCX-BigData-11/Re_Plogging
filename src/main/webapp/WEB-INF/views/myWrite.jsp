<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>RE:PLOGGING MYPAGE</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/myWrite.css">
</head>
<body>
	<div class="checkMyWrote-container">
		<div id="MyCommunityList">
			<h1>커뮤니티 작성 글</h1>
			<c:choose>
				<c:when test="${empty MyClist}">
					<div class="MyCommunityList-line"></div>
				<div class="no-writeCommunity">작성하신 게시글이 없습니다.</div>
				</c:when>
				<c:otherwise>
					<table id="list">
						<thead>
							<tr>
								<th>글번호</th>
								<th>카테고리</th>
								<th>글 제목</th>
								<th>작성 날짜</th>
								<th>조회수</th>
								<th>좋아요수</th>
								<th>첨부파일</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%--게시글 목록 출력--%>
							<c:forEach var="myCommunity" items="${MyClist}">
								<tr>
									<td>${myCommunity.idx}</td>
									<td>${myCommunity.category}</td>
									<td><a href="${ctx }/communityRead?idx=${myCommunity.idx}">${myCommunity.title}</a></td>
									<td><fmt:formatDate value="${myCommunity.indate}"
											pattern="yyyy.MM.dd HH:mm:ss" /></td>
									<td>${myCommunity.count}</td>
									<td>${myCommunity.likes}</td>
									<td>${myCommunity.img != null ? myCommunity.img : '-'}</td>
									<td><a href="${ctx }/Mycdelete?idx=${myCommunity.idx}">삭제하기</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>


		<div id="MyMarketList">
			<h1>그린마켓 작성 글</h1>
			<c:choose>
				<c:when test="${empty MyMlist}">
				<div class="MyMarketList-line"></div>
				<div class="no-writeMaket">작성하신 그린마켓 글이 없습니다.</div>
				</c:when>
				<c:otherwise>
					<table id="list">
						<thead>
							<tr>
								<th>상품번호</th>
								<th>카테고리</th>
								<th>상품명</th>
								<th>상품 마일리지</th>
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
							<%--마켓 판매글 목록 출력--%>
							<c:forEach var="myMarket" items="${MyMlist}">
								<tr>
									<td>${myMarket.mkIdx}</td>
									<td>${myMarket.category}</td>
									<td><a href="${ctx }/marketRead?idx=${myMarket.mkIdx}">${myMarket.title}</a></td>
									<td>${myMarket.mileage}</td>
									<td>${myMarket.img1 != null ? myMarket.img1 : '-'}</td>
									<td>${myMarket.img2 != null ? myMarket.img2 : '-'}</td>
									<td>${myMarket.img3 != null ? myMarket.img3 : '-'}</td>
									<td>${myMarket.status}</td>
									<td><fmt:formatDate value="${myMarket.createdAt}"
											pattern="yyyy.MM.dd HH:mm:ss" /></td>
																		<td><c:choose>
									<c:when test="${myMarket.closedAt != null}">
										<fmt:formatDate value="${myMarket.closedAt}"
												pattern="yyyy.MM.dd HH:mm:ss" />
											</c:when>
											<c:otherwise>-</c:otherwise>
										</c:choose></td>
									<td><a href="${ctx }/Mymdelete?idx=${myMarket.mkIdx}">삭제하기</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<footer> © 2024 지구수호대 Korea Corporation All Rights Reserved. </footer>

</body>
</html>