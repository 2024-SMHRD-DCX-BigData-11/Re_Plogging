    // 로그아웃 버튼 클릭 이벤트
    document.getElementById('logoutButton').addEventListener('click', function() {
        // 세션에서 사용자 정보를 제거하는 서버 로직이 필요하다면, 로그아웃을 처리하는 URL로 리디렉션
        window.location.href = 'logout'; // 로그아웃 처리 후 로그인 페이지로 리디렉션
    });