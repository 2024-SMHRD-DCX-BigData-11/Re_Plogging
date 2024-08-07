// 로그인 모달 열기
function openModal() {
	document.getElementById('login-modal').style.display = 'flex';
}

// 로그인 모달 닫기
function closeModal() {
	document.getElementById('login-modal').style.display = 'none';
}

// 회원가입 모달 열기
function openJoinModal() {
    // 로그인 모달이 열려 있을 때만 회원가입 모달을 열 수 있음
    if (document.getElementById('login-modal').style.display === 'flex') {
        closeModal(); // 로그인 모달을 닫고
        document.getElementById('join-modal').style.display = 'flex'; // 회원가입 모달을 열기 (block -> flex로 수정)
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