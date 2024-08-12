<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 페이지</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f9f9f9;
    }

    .container {
        max-width: 800px;
        margin: 0 auto;
        margin-top: 80px;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
        color: #333;
        margin-bottom: 20px;
        text-align: center;
    }

    form {
        display: flex;
        flex-direction: column;
    }

    label {
        margin-bottom: 10px;
        font-weight: bold;
        color: #333;
    }

    input[type="text"],
    select,
    textarea {
        padding: 10px;
        font-size: 14px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 100%;
        box-sizing: border-box;
    }

    textarea {
        resize: vertical;
        min-height: 150px;
    }

    input[type="file"] {
        margin-bottom: 20px;
    }

    .button-group {
        display: flex;
        justify-content: flex-end;
    }

    .btn {
        padding: 10px 20px;
        font-size: 14px;
        cursor: pointer;
        text-align: center;
        text-decoration: none;
        outline: none;
        color: #fff;
        background-color: #4CAF50;
        border: none;
        border-radius: 5px;
        margin-left: 10px;
    }

    .btn:hover {
        background-color: #45a049;
    }

    .btn-cancel {
        background-color: #f44336;
    }

    .btn-cancel:hover {
        background-color: #e53935;
    }
</style>
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

            <input type="hidden" name="writerId" value="${sessionScope.user.id}"> <!-- 실제 세션에서 가져와야 함 -->

            <div class="button-group">
                <button type="submit" class="btn">저장</button>
                <a href="${pageContext.request.contextPath}/community" class="btn btn-cancel">취소</a>
            </div>
        </form>
    </div>
</body>
</html>
