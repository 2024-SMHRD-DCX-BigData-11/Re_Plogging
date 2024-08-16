    function likePost(user) {
        if ( user != null ) {
            document.getElementById('likeForm').submit();
        } else {
            alert('로그인 후 좋아요를 누를 수 있습니다.');
        }
    }