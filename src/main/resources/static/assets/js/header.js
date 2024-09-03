// 메뉴 상단바 스크롤 효과
let lastScrollY = window.scrollY;

window.addEventListener('scroll', function() {
    const header = document.querySelector('.header');
    const currentScrollY = window.scrollY;

    if (currentScrollY === 0) {
        // 최상단에서는 항상 헤더를 보이게 하기
        header.classList.remove('hidden');
    } else if (currentScrollY > lastScrollY) {
        // 스크롤이 아래로 이동 중이면 헤더를 숨기기
        header.classList.add('hidden');
    } else {
        // 스크롤이 위로 이동 중이면 헤더를 보이게 하기
        header.classList.remove('hidden');
    }

    lastScrollY = currentScrollY;
});

// 메뉴 버튼
$(function(){
    $("#menu-icon").click(function(){
        if($("#burgur").hasClass("on")){
            // 메뉴 버튼에 "on" 클래스가 있을 경우 해당 클래스를 제거
            $("#burgur").removeClass("on");
        } else {
            // 메뉴 버튼에 "on" 클래스가 없을 경우 해당 클래스를 추가
            $("#burgur").addClass("on");
        }
    });
});

// 슬라이더
document.getElementById('menu-icon').addEventListener('click', function() {
    const aside = document.getElementById('aside');
    const overlay = document.getElementById('overlay');
    const listItems = document.querySelectorAll('.aside_category li');

    // 슬라이더 열기
    aside.classList.toggle('open');
    // 오버레이 표시
    overlay.classList.toggle('show');

    if (aside.classList.contains('open')) {
        // 슬라이더가 열렸을 때, 리스트 아이템에 애니메이션 클래스 추가
        listItems.forEach((item, index) => {
            setTimeout(() => {
                item.classList.add('animate');
            }, index * 300); // 각 아이템의 애니메이션이 순차적으로 실행되도록 딜레이 설정
        });
    } else {
        // 슬라이더가 닫혔을 때, 애니메이션 클래스 제거
        listItems.forEach(item => {
            item.classList.remove('animate');
        });
    }
});

// 숫자를 천 단위로 포맷팅하는 함수
		function formatNumber(num) {
			return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}

		// 모든 Mileage-amount 요소를 선택하여 포맷팅
		var mileageElements = document.querySelectorAll('.aside-p');
		mileageElements.forEach(function(element) {
		    var mlAmount = parseInt(element.textContent, 10);
		    element.textContent = formatNumber(mlAmount) + 'p';
		});