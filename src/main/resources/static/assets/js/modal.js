// 로그인 모달 열기
function openModal() {
    document.getElementById('login-modal').style.display = 'flex';
}

// 로그인 모달 닫기
function closeLoginModal() {
    document.getElementById('login-modal').style.display = 'none';
}

// 회원가입 모달 열기
function openJoinModal() {
    // 로그인 모달이 열려 있을 때만 회원가입 모달을 열 수 있음
    if (document.getElementById('login-modal').style.display === 'flex') {
        closeLoginModal();
        document.getElementById('join-modal').style.display = 'flex';
    }
}

// 회원가입 모달 닫기 (필요할 경우 추가)
function closeJoinModal() {
    document.getElementById('join-modal').style.display = 'none';
}

// 로그인 모달 내 회원가입 링크 클릭 시 회원가입 모달 열기
document.getElementById('join-link').addEventListener('click', function() {
    openJoinModal();
});

/*
$('#user_pw_confirm').on( "focusout", function( event ) {
	var pw = $("#user_pw").val();
	var	cpw = $("#user_pw_confirm").val();
	
	
	if( pw.match( cpw ) != null ) {
		alert( "😊 비밀번호가 일치합니다." );
	} else {
		alert( "😣 비밀번호가 일치하지 않습니다." );
	}
	return false;
});
*/

/*// 닉네임 중복체크
        $( '#user_nick' ).change(function() {
			var userNick = $('#user_nick').val().trim();
			
			if(userNick === ''){
				alert("🤔 닉네임을 입력해 주세요.");
				return;
			}
			
			$.ajax({
				type : "POST",
				url : '${ctx}/nickCheck',
				data : {
					userNick : userNick
				},
				success : function(data){
					
					if(data == 1){
						alert("😣 중복된 닉네임 입니다.");
						
					}
					else{
						alert("😊 사용할 수 있는 닉네임입니다.");
					}
				},
				error:function(request, error){
					alert("code : " + request.status+ "\n" + "message : " + request.reponseText + "\n" + "error : " + error);
				}
				
			})
	
        } );*/



//*/ 인증 버튼 누르면 mobile값 1개로 합치는 함수
function telconfirmButton( url ) {
    // mobile1, mobile2, mobile3 값을 가져옴
    var mobile1 = document.getElementById("mobile1").value;
    var mobile2 = document.getElementById("mobile2").value;
    var mobile3 = document.getElementById("mobile3").value;
    
    // 값을 합쳐서 하나의 전화번호로 만듦
    var fullPhoneNumber = mobile1 + "-" + mobile2 + "-" + mobile3;

	// fullPhoneNumber 값을 mobile4의 value로 설정
    document.getElementById("mobile4").value = fullPhoneNumber;

    
   	var formData = new FormData();
    formData.append('phoneNumber', fullPhoneNumber);
	
	// AJAX 요청을 통해 서버로 fullPhoneNumber를 전달
	$.ajax({
		url : url,
		type : "POST",
		data : formData,
		processData: false,
        contentType: false,
		enctype: 'multipart/form-data',
	}).done( function( response ) {
		//결과 alert
		if(response.code == 200){
			alert("😊 인증번호가 발송되었습니다.");
		}
		else{
			alert("😣 인증번호 발송에 실패했습니다.");
		}
		
	}).fail( function( error ) {
		console.log( error );
	});   
}

// 인증번호 확인
function smsCheck( url ) {
	
	var u_input = $('#otp').val().trim();

	
	// AJAX 요청을 통해 서버로 사용자가 입력한 인증번호 전달
    $.ajax({
		url : url,
		type : "POST",
		data : { code : u_input },
	}).done( function( response ) {
		if( response.code == 200 ) {
			$("#telCheck").val( "1" );
			alert("😊 인증번호가 확인되었습니다.");
		} else{
			$("#telCheck").val( "0" );
			alert("😣 인증번호 확인에 실패했습니다. 다시 시도해 주세요.");
		}
	});   
	return false;
}

function commonAjax( url, data, type, callback ) {
	$.ajax({
		url : url,
		data : data,
		type : type
	}).done( function( response ) {
		if( callback != null ) {
			callback( response );
		}
	});
}

function commonMultiAjax( url, data, callback ) {
	$.ajax({
		url : url,
		type : "post",
		data : data,
		processData: false,
        contentType: false,
		enctype: 'multipart/form-data',
	}).done( function( response ) {
		if( callback != null ) {
			callback( response );
		}
	});   
}

function erroAlert( msg, id ) {
	alert( msg );
	$("#" + id ).focus();
	return false;
}


// 지도 이미지 모달
function showCourseImage(courseName) {
    var modal = document.getElementById("courseModal");
    var courseImage = document.getElementById("courseImage");

    // 코스 이름에 따라 이미지를 설정
    if (courseName === 'A코스(순천대학교)') {
        courseImage.src = 'img/A코스.png';
    } else if (courseName === 'B코스(순천 조례호수공원)') {
        courseImage.src = 'img/B코스.png';
    } else if (courseName === 'C코스(순천 오천그린광장)') {
        courseImage.src = 'img/C코스.png';
    } else if (courseName === 'MY플로깅코스') {
        courseImage.src = 'img/myplogging.png';
    }

    modal.style.display = "block"; // 모달을 보여줌
}

function closeCourseModal() {
    var modal = document.getElementById("courseModal");
    modal.style.display = "none"; // 모달을 숨김
}

// 모달 외부 클릭 시 모달 닫기
window.onclick = function(event) {
    var modal = document.getElementById("courseModal");
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


// ai 모달(메인) 열기
function openAiModal() {
    var modal = document.getElementById('ai-modal');
    modal.style.display = 'flex';
    setTimeout(function() {
        modal.classList.add('show');
    }, 10);
}

// ai 모달(메인) 닫기
function closeAiModal() {
    var modal = document.getElementById('ai-modal');
    modal.classList.remove('show');
    setTimeout(function() {
        modal.style.display = 'none';
    }, 300);
}


// ai 모달(나의 플로깅) 열기
function openMyploAiModal() {
    var modal = document.getElementById('myplo-ai-modal');
    modal.style.display = 'flex';
    setTimeout(function() {
        modal.classList.add('show');
    }, 10);
}

// ai 모달(나의 플로깅) 닫기
function closeMyPloAiModal() {
    var modal = document.getElementById('myplo-ai-modal');
    modal.classList.remove('show');
    setTimeout(function() {
        modal.style.display = 'none';
    }, 300);
}