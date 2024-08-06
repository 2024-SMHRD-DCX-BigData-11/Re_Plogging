// 아이디 중복체크 하기
// input 과 change 이벤트 : 둘 다 값이 변화했을 때 일어나는 이벤트 but 언제 실행하느냐에 따라 달라짐
// input의 경우 input 태그 안에 데이터를 "입력"할 때 실행
// change의 경우 input 태그의 값이 "변경"될 때 실행

$('#emailInput').on( "input", request );

function request() {
	
	// 비동기 요청 방식
	$.ajax({
		url : "check",
		type : "get",
		data : {
			"email" : $('#emailInput').val()
		},
		success : function(res){
			if(res == "true"){
				$('#result').html("사용 가능한 email 입니다.").css("color", "green");
			} else{
				$('#result').html("사용할 수 없는 email 입니다.").css("color", "red");
			}
		},
		error : function(e){
			
		}
	});
}