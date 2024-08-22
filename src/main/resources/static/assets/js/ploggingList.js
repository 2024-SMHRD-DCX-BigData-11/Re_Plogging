    function checkWindowSize() {
        var image = document.getElementById('plocourseImage');
        if (window.innerWidth <= 768) {
            image.src = 'img/플로코스(모바일.ver).png';
        } else {
            image.src = 'img/플로코스.png';
        }
    }

    // 페이지 로딩 시 체크
    checkWindowSize();

    // 윈도우 크기 변경 시 체크
    window.addEventListener('resize', checkWindowSize);
    
    
document.addEventListener('DOMContentLoaded', function() {
    const observerOptions = {
        threshold: 0.1 // 요소가 10% 보일 때 트리거
    };

    const observer = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('visible');
                observer.unobserve(entry.target); // 한 번 애니메이션이 실행되면 더 이상 관찰하지 않음
            }
        });
    }, observerOptions);

    document.querySelectorAll('.fade-up').forEach(element => {
        observer.observe(element);
    });
});


// URL의 쿼리 파라미터 확인
        const urlParams = new URLSearchParams(window.location.search);
        const ploggingStartError = urlParams.get('ploggingStartError');

        // 에러 메시지를 swal로 띄우는 함수
        function showAlertWithPloggingStartError() {
            // 에러 메시지를 swal로 표시
            swal('플로깅 시작 실패', "로그인 후 플로깅을 시작해 주세요.", 'warning');
        }

        // 페이지 로드 시 쿼리 파라미터에 따라 경고 메시지 표시
        if (ploggingStartError) {
            showAlertWithPloggingStartError();
        }