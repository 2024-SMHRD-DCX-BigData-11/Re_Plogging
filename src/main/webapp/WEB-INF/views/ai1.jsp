<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>이미지 업로드</title>


</head>
<body>
	<form action="AiImageUpload" method = "post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>이미지</td>
				<td><input name = "file" type="file"></td>
				<td><input type="submit" value="이미지 업로드"></td>
			</tr>
		</table>
	</form>	
</body>
</html>