<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>이미지 업로드</title>
</head>
<body>

<h1>이미지 업로드</h1>

<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" accept="image/*">
    <br>
    <button type="submit">업로드</button>
</form>

<a href="${pageContext.request.contextPath}/fileList">List로 가기</a>

</body>
</html>
