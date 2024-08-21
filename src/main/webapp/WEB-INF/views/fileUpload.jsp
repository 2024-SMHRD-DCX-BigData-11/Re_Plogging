<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>이미지 업로드</h1>

<form th:action="@{/upload}" method="post" enctype="multipart/form-data">
    <input type="file" name="file" accept="image/*">
    <br>
    <button type="submit">업로드</button>
</form>


<a th:href="@{/image-list}">List로 가기</a>

</body>
</html>