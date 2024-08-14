    function likePost() {
        const loggedIn = '<%= session.getAttribute("user") != null %>';
        if (loggedIn === 'true') {
            document.getElementById('likeForm').submit();
        } else {
            alert('로그인 후 좋아요를 누를 수 있습니다.');
        }
    }