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

//*/ 인증 버튼 누르면 mobile값 1개로 합치는 함수
function telconfirmButton( url ) {
    // mobile1, mobile2, mobile3 값을 가져옴
    var mobile1 = document.getElementById("mobile1").value;
    var mobile2 = document.getElementById("mobile2").value;
    var mobile3 = document.getElementById("mobile3").value;
    
    // 값을 합쳐서 하나의 전화번호로 만듦
    var fullPhoneNumber = mobile1 + "-" + mobile2 + "-" + mobile3;
    
   	var formData = new FormData();
    formData.append('phoneNumber', fullPhoneNumber);
	
	// AJAX 요청을 통해 서버로 fullPhoneNumber를 전달
	$.ajax({
		url : url,
		type : "post",
		data : formData,
		processData: false,
        contentType: false,
		enctype: 'multipart/form-data',
	}).done( function( response ) {
		//결과 alert
	});   
}
// 인증번호 확인
function smsCheck() {
	u_input = document.getElementById("otp").value;
}


commonAjax( "url", { idx : idx }, "post", function( response ) {
	if( response.code == 200 ) {
		alert( "success" );
	}else {
		alert( "fail" );
	}
})

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

function commonMultiAjax( url, data, type, callback ) {
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
