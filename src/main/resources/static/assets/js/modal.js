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
		alert( "비밀번호가 일치합니다." );
	} else {
		alert( "비밀번호가 일치 하지않습니다." );
	}
	return false;
});
*/

/*// 닉네임 중복체크
        $( '#user_nick' ).change(function() {
			var userNick = $('#user_nick').val().trim();
			
			if(userNick === ''){
				alert("닉네임을 입력해주세요.");
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
						alert("중복된 닉네임입니다.");
						
					}
					else{
						alert("사용할 수 있는 닉네임입니다.");
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
    document.getElementById("#mobile4").value = fullPhoneNumber;

    
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
			alert("인증번호 발송");
		}
		else{
			alert("인증번호 발송 실패");
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
			alert("인증번호 확인 성공!");
		} else{
			$("#telCheck").val( "0" );
			alert("인증번호 확인 실패!");
		}
	});   
	return false;
}

/*
commonAjax( "url", { idx : idx }, "post", function( response ) {
	if( response.code == 200 ) {
		alert( "success" );
	}else {
		alert( "fail" );
	}
})*/

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
/*
function join(formData, url){
	
	var user_id = $('#user_id').val();
	var user_pw = $('#user_pw').val();
	var user_pw_c = $('#user_pw_confirm').val();
	var user_phone = $('#mobile4').val();
	var sms_check = $('#otp').val();
	var user_nick = $('#user_nick').val();
	
	
	if( user_id.length == 0 ) {
		erroAlert("이메일을 입력해주세요.1")
	} else if ( user_pw.length == 0 ) {
		erroAlert("비밀번호를 입력해주세요.")
	} else if ( user_pw_c.length == 0 ) {
		erroAlert("비밀번호 확인을 입력해주세요.")
	} else if ( user_pw.match( user_pw_c ) == null ) {
		erroAlert("비밀번호를 맞게 썼는지 확인해주세요.")
	} else if ( user_phone.length == 0 ) {
		erroAlert("전화번호를 입력해주세요.")
	} else if (sms_check.length == 0){
		erroAlert("이메일을 입력해주세요.2")
	} else if ( user_nick.length == 0 ) {
		erroAlert("닉네임을 입력해주세요.")
	} else {
		commonMultiAjax( url, formData, function( response ) {
			if( response.code == 200 ) {
				//등록 성공
			} else if ( response.code == -100 ) {
				//등록 실패
			} else {
				//email 중복
			}
		});
		return false;
	}
	return false;
}
*/
function erroAlert( msg, id ) {
	alert( msg );
	$("#" + id ).focus();
	return false;
}




/*// 인증 버튼 교체
function telconfirmButton() {
    const telconfirm = document.getElementById('telconfirm');
    
    if (telconfirm.classList.contains('tel-confirm')) {
        telconfirm.textContent = '재인증';
    }
}

// 타이머
window.onload = function() {
    var duration = 180; // 3분 (180초)
    var timer = duration, minutes, seconds;
    var countdownElement = document.getElementById('countdown');

    var interval = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        countdownElement.value = minutes + ":" + seconds;

        if (--timer < 0) {
            clearInterval(interval);
            countdownElement.value = "시간 초과";
            // 타이머 종료 후 추가 동작 (예: 입력 필드 비활성화)
            document.getElementById('otp').disabled = true;
            document.getElementById('btnSubmit').disabled = true;
        }
    }, 1000);

    // 인증번호 검증 예시
    document.getElementById('btnSubmit').addEventListener('click', function() {
        var userInput = document.getElementById('otp').value;
        var correctOtp = "123456"; // 예시로 사용한 정답 OTP

        if (userInput === correctOtp) {
            alert("인증 성공!");
            // 회원가입 절차로 이동
        } else {
            alert("잘못된 인증번호입니다.");
        }
    });
};*/
