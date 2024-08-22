<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>이미지 목록</title>
</head>
<body>

<h1>이미지 목록</h1>

<ul>
    <c:forEach var="image" items="${images}">
        <li>
            <img src="data:image/jpeg;base64,${image.base64Data}" alt="Image" width="400px" />
        </li>
    </c:forEach>
</ul>

<a href="${pageContext.request.contextPath}/">업로드 페이지로 돌아가기</a>

</body>
</html>
