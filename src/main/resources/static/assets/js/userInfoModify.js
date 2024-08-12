document.getElementById('change-pw-button').addEventListener('click', function() {
    document.getElementById('pw-row').style.display = 'none';
    document.getElementById('change-pw-section').style.display = 'table-row-group';
});

document.getElementById('cancel-button').addEventListener('click', function() {
    document.getElementById('pw-row').style.display = 'table-row';
    document.getElementById('change-pw-section').style.display = 'none';
});

/*document.getElementById("save-changes-button").addEventListener("click", function() {
    // 여기에 비밀번호 변경 로직 추가
    alert("비밀번호가 성공적으로 변경되었습니다.");
    document.getElementById("pw-section").style.display = "flex";
    document.getElementById("change-password-section").style.display = "none";
});

document.getElementById("user-info-form").addEventListener("submit", function(event) {
    event.preventDefault();
    // 닉네임 변경 처리 로직 추가
    alert("정보가 성공적으로 변경되었습니다.");
});
*/