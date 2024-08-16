// URL의 쿼리 파라미터 확인
const urlParams = new URLSearchParams(window.location.search);
const deleteError = urlParams.get('deleteError');

// 에러 메시지를 swal로 띄우는 함수
function showAlertWithDeleteError() {
    // 에러 메시지를 swal로 표시
    swal('회원탈퇴 실패!', "비밀번호를 다시 확인해 주세요.", 'warning');
}

// 페이지 로드 시 deleteError 파라미터가 있는지 확인하고, 있으면 swal을 띄움
window.onload = function() {
    if (deleteError) {
        showAlertWithDeleteError();
    }
};
