<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Recognition Result</title>
</head>
<body>
    <h1>이미지 인식 결과</h1>
    
    <c:choose>
        <c:when test="${not empty imageData}">
            <p>Image data is available.</p>
            <img src="data:image/jpeg;base64,${imageData}" alt="Recognition Result" />
        </c:when>
        <c:otherwise>
            <p>No image available.</p>
        </c:otherwise>
    </c:choose>
    
    <div id="ResultText">
        <h2>인식 결과</h2>
        <table id="list" border="1">
            <thead>
                <tr>
                    <th>품목</th>
                    <th>인식 개수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="text" items="${resultText}">
                    <tr>
                        <td>${text.recycleCategory.category}</td>
                        <td>${text.categoryCount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
</body>
</html>