// 메뉴 상단바 스크롤 효과
let lastScrollY = window.scrollY;

window.addEventListener('scroll', function() {
    const header = document.querySelector('.header');
    const currentScrollY = window.scrollY;

    if (currentScrollY > lastScrollY) {
        // 스크롤이 아래로 이동 중이면 헤더를 숨기기
        header.classList.add('hidden');
    } else {
        // 스크롤이 위로 이동 중이면 헤더를 보이게 하기
        header.classList.remove('hidden');
    }

    lastScrollY = currentScrollY;
});