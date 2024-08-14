<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/communityWriter.css">
</head>
<body>
    <div class="container">
        <h1>글쓰기</h1>
        <form action="${pageContext.request.contextPath}/submitPost" method="post" enctype="multipart/form-data">
            <label for="title">글 제목</label>
            <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required>

            <label for="category">카테고리</label>
            <select id="category" name="category" required>
                <option value="plogging">플로깅</option>
                <option value="separation">분리배출</option>
                <option value="freeboard">자유게시판</option>
            </select>

            <label for="content">내용</label>
            <textarea id="content" name="content" placeholder="내용을 입력하세요" required></textarea>

            <label for="file">첨부파일</label>
            <input type="file" id="file" name="file">

            <input type="hidden" name="writerId" value="${sessionScope.user.userIdx}">

            <div class="button-group">
                <button type="submit" class="btn">저장</button>
                <a href="${pageContext.request.contextPath}/community" class="btn btn-cancel">취소</a>
            </div>
        </form>
    </div>
</body>
</html>
