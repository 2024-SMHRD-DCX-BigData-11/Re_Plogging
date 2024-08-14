<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp" %>
<%@ include file="modal.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>A코스 QR1번 인증완료 페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
span {
	display: inline;
	font-size: 15px;
}
</style>
</head>
<body>
	<div>
		<span>A코스 QR1번 인증완료!!</span>
		<br>
		<img src="img/grey-9026_512.gif" alt="Certification Complete" />
		
		loginCheck(${user})
		
		<button type="button" class="btn text-white" onclick = "location.href='${pageContext.request.contextPath}/myplogging'" style="background-color: green;">나의 플로깅으로 이동</button>
		
	</div>
	
	<script type="text/javascript">
	
		updatePlogging( idx, value );
		$( document ).ready( function( event ) {
			updatePlogging( idx, value );
		});
		
		function updatePlogging( idx, value ) {
			$.ajax({
				url : url,
				data : { idx : idx, value : value },
				type : "post"
			}).done( function ( response ) {
				//성공 
				
				//tlfvo
				
			})
		}



	</script>


</body>
</html>