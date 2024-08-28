// URL의 쿼리 파라미터 확인
const urlParams = new URLSearchParams(window.location.search);
const ploggingError = urlParams.get('ploggingError');
const ploggingSuccess = urlParams.get('plogging');

// 에러 메시지를 swal로 띄우는 함수
function showAlertWithPloggingError() {
    Swal.fire({
        title: '',
        html: "플로깅 시작에 실패했습니다.<br>현재 진행 중인 플로깅이 존재합니다.",
        icon: 'warning',
        confirmButtonText: '확인',
        customClass: {
            confirmButton: 'custom-error-button'
        }
    });
}

// 성공 메시지를 swal로 띄우는 함수
function showAlertWithPloggingSuccess() {
	Swal.fire({
		title: '',
		html: "플로깅을 시작합니다.<br>카메라로 QR을 인식해 주세요.",
		icon: 'success',
		confirmButtonText: '확인',
		customClass: {
			confirmButton: 'custom-success-button'
		}
	});
}


// 페이지 로드 시 ploggingError 또는 plogging 파라미터가 있는지 확인하고, 있으면 swal을 띄움
window.onload = function() {
    if (ploggingError) {
        showAlertWithPloggingError();
    }
    if (ploggingSuccess === 'true') {
        showAlertWithPloggingSuccess();
    }
};
