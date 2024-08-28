// URL의 쿼리 파라미터 확인
const urlParams = new URLSearchParams(window.location.search);
const deleteError = urlParams.get('deleteError');

// 에러 메시지를 swal로 띄우는 함수
function showAlertWithDeleteError() {
    Swal.fire({
        title: '',
        html: "회원탈퇴에 실패했습니다.<br>비밀번호를 다시 확인해 주세요.",
        icon: 'warning',
        confirmButtonText: '확인',
        customClass: {
            confirmButton: 'custom-delete-button'
        }
    });
}

// 페이지 로드 시 deleteError 파라미터가 있는지 확인하고, 있으면 swal을 띄움
window.onload = function() {
    if (deleteError) {
        showAlertWithDeleteError();
    }
};
