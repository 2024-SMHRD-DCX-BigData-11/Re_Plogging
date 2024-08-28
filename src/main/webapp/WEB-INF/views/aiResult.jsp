<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AI Result</title>
</head>
<body>
    <h1>AI Result Image</h1>
    <c:choose>
        <c:when test="${imageData != null}">
            <img src="data:image/jpeg;Blob,${imageData}" alt="AI Result Image"/>
        </c:when>
        <c:otherwise>
            <p>No image available</p>
        </c:otherwise>
    </c:choose>
</body>
</html>