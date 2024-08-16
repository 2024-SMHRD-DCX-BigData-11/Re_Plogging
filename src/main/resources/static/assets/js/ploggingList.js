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

    