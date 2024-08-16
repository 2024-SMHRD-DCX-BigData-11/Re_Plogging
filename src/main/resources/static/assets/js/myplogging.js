// URL의 쿼리 파라미터 확인
        const urlParams = new URLSearchParams(window.location.search);
        const ploggingError = urlParams.get('ploggingError');

        // 에러 메시지를 swal로 띄우는 함수
        function showAlertWithPloggingError() {
            // 에러 메시지를 swal로 표시
            swal('플로깅 진행 실패!', "현재 진행 중인 플로깅이 존재합니다.", 'warning');
        }

        // 페이지 로드 시 ploggingError 파라미터가 있는지 확인하고, 있으면 swal을 띄움
        window.onload = function() {
            if (ploggingError) {
                showAlertWithPloggingError();
            }
        };