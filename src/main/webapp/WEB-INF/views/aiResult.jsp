<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AI Result Image</title>
</head>
<body>
    <h1>AI Result Image</h1>
    
    <c:choose>
        <c:when test="${not empty imageData}">
            <img src="data:image/jpeg;base64,${imageData}" alt="AI Result Image"/>
        </c:when>
        <c:otherwise>
            <p>No image available</p>
        </c:otherwise>
    </c:choose>
    
</body>
</html>