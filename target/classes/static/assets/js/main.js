// 본문 plogging02-04 스크롤 효과
function handleScroll() {
	const fadeInElements = document.querySelectorAll('.plogging_02, .plogging_03, .plogging_04');

	fadeInElements.forEach((element) => {
		const rect = element.getBoundingClientRect();

		// 요소의 상단이 화면의 80% 이하에 들어오고, 요소의 하단이 화면 바깥에 나가지 않으면 보여줌
		if (rect.top < window.innerHeight * 0.8 && rect.bottom > 0) {
			element.classList.add('show');
		} else {
			element.classList.remove('show');
		}
	});
}

window.addEventListener('scroll', handleScroll);

// 메뉴 슬라이더 효과
function openMenu() {
	const menu = document.querySelector('.menu');
	menu.classList.add('open'); // 부드럽게 슬라이드되도록 'open' 클래스 추가
}

function closeMenu() {
	const menu = document.querySelector('.menu');
	menu.classList.remove('open'); // 'open' 클래스 제거하여 메뉴를 숨김
}