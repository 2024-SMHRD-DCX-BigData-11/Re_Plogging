const page = document.getElementById("page");

var data = [];
var i = 0;

// 페이지 로드 시 #전체를 기본으로 설정
window.onload = function() {
    fetchRecycleData(''); // 페이지가 로드될 때 전체 데이터를 불러옵니다.
    setActiveCategory(''); // 전체 버튼을 기본으로 활성화합니다.
};

function fetchRecycleData(category) {
    var xhr = new XMLHttpRequest();
    if (category) {
        xhr.open('GET', '/boot/recycle/data?main-category=' + category, true);
    } else {
        xhr.open('GET', '/boot/recycle/data', true);
    }
    xhr.onload = function() {
        if (xhr.status === 200) {
            data = JSON.parse(xhr.responseText);
            if (data.length > 0) {
                i = 0;
                loadData();
            }
        }
    };
    xhr.send();

    setActiveCategory(category); // 버튼 활성화 함수 호출
}

function setActiveCategory(category) {
    // 모든 버튼의 active 클래스를 제거합니다.
    var buttons = document.querySelectorAll('.recycle_List_category_li');
    buttons.forEach(function(button) {
        button.classList.remove('active');
    });

    // 선택된 카테고리 버튼에 active 클래스를 추가합니다.
    var activeButton;
    if (category) {
        activeButton = document.querySelector('[onclick="fetchRecycleData(\'' + category + '\')"]');
    } else {
        activeButton = document.querySelector('[onclick="fetchRecycleData(\'\')"]');
    }
    if (activeButton) {
        activeButton.classList.add('active');
    }
}

document.getElementById('prev').addEventListener('click', function(event) {
    event.preventDefault(); // 기본 동작을 취소
    if (data.length > 0 && i > 0) {
        i -= 1;
        loadData();
    }
});

document.getElementById('next').addEventListener('click', function(event) {
    event.preventDefault(); // 기본 동작을 취소
    if (data.length > 0 && i + 1 < data.length) {
        i += 1;
        loadData();
    }
});

function loadData() {
    // 애니메이션 클래스를 제거하여 다시 추가할 수 있도록 초기화
    document.querySelector('.recycle-content-left').classList.remove('bounce-effect');
    document.querySelector('.recycle-content-right').classList.remove('bounce-effect');
    
    // 데이터를 업데이트한 후에 애니메이션 적용
    setTimeout(function() {
        page.textContent = (i + 1) + ' / ' + data.length;
        document.getElementById('recycleImage').src = 'data:image/jpeg;base64,' + data[i].recycleImage;
        document.getElementById('recycleTarget').textContent = '#' + data[i].recycleTarget;
        document.getElementById('recycleStatus').textContent = data[i].recycleStatus;
        document.getElementById('recycleCategory').innerHTML = data[i].recycleCategory;
        document.getElementById('recycleMethod').innerHTML = data[i].recycleMethod;
        
        // 애니메이션을 적용하여 효과 부여
        document.querySelector('.recycle-content-left').classList.add('bounce-effect');
        document.querySelector('.recycle-content-right').classList.add('bounce-effect');
    }, 100); // 약간의 지연을 줘서 애니메이션이 잘 적용되도록 합니다.
}
