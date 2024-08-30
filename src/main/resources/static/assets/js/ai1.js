/*        var categoriesData = {
            종이: 2,
            캔: 3,
            유리: 0,
            페트: 3,
            플라스틱: 3,
            비닐: 0,
            스티로폼: 0,
            건전지: 0
        };*/

// 차트 생성
var ctx = document.getElementById('recyclingChart').getContext('2d');
var recyclingChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: Object.keys(categoriesData),
        datasets: [{
            label: '검출된 갯수',
            data: Object.values(categoriesData),
            backgroundColor: 'rgba(0, 169, 137, 0.2)', // 수정된 배경색
            borderColor: '#00a989', // 수정된 테두리 색상
            borderWidth: 1
        }]
    },
    options: {
        plugins: {
            legend: {
                display: true,
                position: 'bottom' // 라벨 위치를 아래로 설정
            }
        },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});


/*var ctx = document.getElementById('recyclingChart').getContext('2d');
var recyclingChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: Object.keys(categoriesData),
        datasets: [{
            label: '# of Items',
            data: Object.values(categoriesData),
            backgroundColor: 'rgba(0, 169, 137, 0.2)', // 수정된 배경색
            borderColor: '#00a989', // 수정된 테두리 색상
            borderWidth: 1
        }]
    },
    options: {
        plugins: {
            legend: {
                display: true,
                position: 'bottom' // 라벨 위치를 아래로 설정
            }
        },
        scales: {
            x: {
                grid: {
                    display: false // X축 격자선 제거
                }
            },
            y: {
                beginAtZero: true,
                grid: {
                    display: false // Y축 격자선 제거
                }
            }
        }
    }
});
*/












document.querySelector('.aiResult-interested').addEventListener('click', function() {
    const list = document.getElementById('detected-items-list');
    if (list.style.display === "none" || list.style.display === "") {
        list.style.display = "block"; // 리스트를 보여줌
    } else {
        list.style.display = "none"; // 리스트를 숨김
    }
});



window.onload = function() {
    // 객체 인식 결과
    const detectedItems = ['페트', '플라스틱']; // 이 부분은 실제 객체 인식 결과로 동적으로 교체되어야 합니다

    // 객체 인식 항목에 대한 링크
    const itemLinks = {
        '종이': 'https://youtu.be/urE4nzXFOLE?si=o35GOHZY-h7CtGN2',
        '캔': 'https://youtu.be/DsgUFESLWKw?si=MuQEwH6e0RIDp7uj',
        '유리': 'https://youtu.be/gEeCPIe1JEE?si=JahZgVDseRrlUM6E',
        '페트': 'https://youtu.be/UJdK0h4NQWg?si=RPE-gOSQ3Gln98Kk',
        '플라스틱': 'https://youtu.be/hyXggCmXI7I?si=ikO6BOJ8jQGlMl-l',
        '비닐': 'https://youtu.be/vrNgvgTtowo?si=izg5GkCYXogMsAXT',
        '스티로폼': 'https://youtu.be/Fe3R9PWuI_0?si=ZVQ_Oxcke2B0wOcy',
        '건전지': 'https://youtu.be/Q1H0Yydsv-k?si=RG-YwgMTMNZ-FVex'
    };

    // ul 요소 선택
    let ulElement = document.querySelector('.aiResult-interested ul');
    ulElement.innerHTML = ''; // 기존 항목 제거

    // 검출된 항목에 대해 li 항목 생성
    detectedItems.forEach(item => {
        let li = document.createElement('li');
        let a = document.createElement('a');
        a.href = itemLinks[item];
        a.target = '_blank';
        a.textContent = item + ' YouTube 바로가기';
        li.appendChild(a);
        ulElement.appendChild(li);
    });
};
