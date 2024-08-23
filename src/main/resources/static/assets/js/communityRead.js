function likePost(user) {
    if (user != null) {
        document.getElementById('likeForm').submit();
    } else {
        alert('로그인 후 좋아요를 누를 수 있습니다.');
    }
}

function validateCommentForm() {
    var commentContent = document.getElementById("commentContent").value.trim();
    
    if (commentContent === "") {
        alert("댓글 내용을 입력해주세요.");
        return false; // 폼 제출을 중단
    }
    
    return true; // 폼 제출 허용
}
