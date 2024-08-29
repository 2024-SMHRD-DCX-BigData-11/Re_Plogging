document.getElementById('change-pw-button').addEventListener('click', function() {
    document.getElementById('pw-row').style.display = 'none';
    document.getElementById('change-pw-section').style.display = 'table-row-group';
});

document.getElementById('cancel-button').addEventListener('click', function() {
    document.getElementById('pw-row').style.display = 'table-row';
    document.getElementById('change-pw-section').style.display = 'none';
});

function onClickUpload() {
            let fileInput = document.getElementById("file-input");
            fileInput.click();
        }

function inputReadOnly(){
	// current-Mpw 필드의 readonly 속성 상태를 확인
    var isReadOnly = $('#current-Mpw').attr("readonly");

    if (isReadOnly) {
        // readonly가 설정되어 있으면 해제
         $('#current-Mpw').attr("readonly", false).css("background-color", "");
        $('#confirm-Mpw').attr("readonly", false).css("background-color", "");
        $('#new-confirm-Mpw').attr("readonly", false).css("background-color", "");
		$('#MuserNick').attr("readonly", false).css("background-color", "");
    } else {
        // readonly가 설정되어 있지 않으면 설정
        $('#current-Mpw').attr("readonly", true).css("background-color", "#e0e0e0");
        $('#confirm-Mpw').attr("readonly", true).css("background-color", "#e0e0e0");
        $('#new-confirm-Mpw').attr("readonly", true).css("background-color", "#e0e0e0");
		$('#MuserNick').attr("readonly", true).css("background-color", "#e0e0e0");
		alert("😊 변경사항이 저장되었습니다.")
    }
}

/*document.getElementById("save-changes-button").addEventListener("click", function() {
    // 여기에 비밀번호 변경 로직 추가
    alert("😊 비밀번호가 성공적으로 변경되었습니다.");
    document.getElementById("pw-section").style.display = "flex";
    document.getElementById("change-password-section").style.display = "none";
});

document.getElementById("user-info-form").addEventListener("submit", function(event) {
    event.preventDefault();
    // 닉네임 변경 처리 로직 추가
    alert("😊 정보가 성공적으로 변경되었습니다.");
});
*/