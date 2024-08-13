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




// 로그인 실패_URL의 쿼리 파라미터 확인
const urlParams = new URLSearchParams(window.location.search);
const loginError = urlParams.get('loginError');

// 로그인 모달을 열고 에러 메시지를 표시하는 함수
function openLoginModalWithError() {
    // 로그인 모달을 화면에 보이게 설정
    document.getElementById('login-modal').style.display = 'flex';

    // 에러 메시지를 보이게 설정
    document.getElementById('errorMessage').style.display = 'block';
}

// 페이지 로드 시 loginError 파라미터가 있는지 확인하고, 있으면 모달을 띄움
window.onload = function() {
    if (loginError) {
        openLoginModalWithError();
    }
};
