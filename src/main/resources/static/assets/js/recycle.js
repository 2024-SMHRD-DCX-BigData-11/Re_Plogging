const page = document.getElementById("page");

var data = [];

var i = 0;

function fetchRecycleData() {
	var category = document.getElementById('main-category').value;

	var xhr = new XMLHttpRequest();
	if (category) {
		xhr.open('GET', '/boot/recycle/data?main-category=' + category, true);
	} else {
		xhr.open('GET', '/boot/recycle/data');
	}
	xhr.onload = function() {
		if (xhr.status === 200) {
			data = JSON.parse(xhr.responseText);
			console.log(data.length);
			if (data.length > 0) {
				i = 0;
				loadData();
			}
		}
	};
	xhr.send();
}

fetchRecycleData();

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
	page.textContent = (i + 1) + ' / ' + data.length;
	document.getElementById('recycleImage').src = 'data:image/jpeg;base64,' + data[i].recycleImage;
	document.getElementById('recycleTarget').textContent = '#' + data[i].recycleTarget;
	document.getElementById('recycleStatus').textContent = data[i].recycleStatus;
	document.getElementById('recycleCategory').textContent = data[i].recycleCategory;
	document.getElementById('recycleMethod').innerHTML = data[i].recycleMethod;
}