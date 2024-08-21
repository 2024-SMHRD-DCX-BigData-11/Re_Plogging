const page = document.getElementById("page");

var data = [];
var i = 0;

function fetchRecycleData(category) {
    var xhr = new XMLHttpRequest();
    if (category) {
        xhr.open('GET', '/boot/recycle/data?main-category=' + category, true);
    } else {
        xhr.open('GET', '/boot/recycle/data');
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
