// URL의 쿼리 파라미터 확인
const urlParams = new URLSearchParams(window.location.search);
const ploggingError = urlParams.get('ploggingError');
const ploggingSuccess = urlParams.get('plogging'); // 성공 여부를 확인

// 에러 메시지를 swal로 띄우는 함수
function showAlertWithPloggingError() {
    swal('플로깅 시작 실패!', "현재 진행 중인 플로깅이 존재합니다.", 'error');
}

// 성공 메시지를 swal로 띄우는 함수
function showAlertWithPloggingSuccess() {
    swal('플로깅 시작!', "카메라로 QR을 인식해 주세요.", 'success');
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
