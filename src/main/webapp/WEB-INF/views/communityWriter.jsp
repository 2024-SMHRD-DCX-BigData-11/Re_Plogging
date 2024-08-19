<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/communityWriter.css">
<style>
    /* 삭제 버튼의 크기를 글자 크기만큼만 */
    #deleteButton {
        display: none;
        width: auto; /* 버튼 크기를 텍스트에 맞추기 위해 자동 조정 */
        padding: 2px 5px; /* 최소한의 패딩 */
        margin-left: 5px; /* 파일 입력과 버튼 사이에 약간의 간격 */
        font-size: 14px; /* 버튼 내 텍스트의 크기 */
        cursor: pointer; /* 마우스를 올렸을 때 포인터 커서 표시 */
        box-sizing: border-box; /* 패딩과 테두리를 포함하여 버튼 크기 조정 */
        border: 1px solid #ccc; /* 기본 테두리 */
        background-color: #f0f0f0; /* 버튼 배경색 */
    }
    #files {
       display: block;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>글쓰기</h1>
        <form action="${pageContext.request.contextPath}/${!empty community ? 'edit' : 'submitPost'}" method="post" enctype="multipart/form-data">
            <label for="title">글 제목</label>
            <input type="text" id="title" name="title" placeholder="제목을 입력하세요" value="${community.title}" required>

            <label for="category">카테고리</label>
            <select id="category" name="category" required>
                <option value="plogging" ${community.category == '플로깅' ? 'selected' : ''}>플로깅</option>
                <option value="separation" ${community.category == '분리배출' ? 'selected' : ''}>분리배출</option>
                <option value="freeboard" ${community.category == '자유게시판' ? 'selected' : ''}>자유게시판</option>
            </select>

            <label for="content">내용</label>
            <textarea id="content" name="content" placeholder="내용을 입력하세요" required>${community.content}</textarea>

            <label for="file">첨부파일</label>
            <div id="files">
               <c:if test="${!empty community.img}">
                  <p id="uploaded">현재 파일: ${community.img}</p>
              </c:if>
      
              <!-- 새로운 파일 선택 -->
              <input type="file" id="file" name="file">
               <button type="button" id="deleteButton" onclick="removeFile()">삭제</button>
            </div>
            <input type="hidden" id="uploaded_" name="uploaded" value="${community.img}">

            <input type="hidden" name="writerId" value="${sessionScope.user.userIdx}">
            <c:if test="${!empty community}">
                <input type="hidden" name="editId" value="${community.idx}">
            </c:if>

            <div class="button-group">
                <button type="submit" class="btn">저장</button>
                <a href="${pageContext.request.contextPath}/community" class="btn btn-cancel">취소</a>
            </div>
        </form>
    </div>
    <script>
        // Get references to the file input and delete button
        const fileInput = document.getElementById('file');
        const uploaded = document.getElementById('uploaded');
        const uploaded_ = document.getElementById('uploaded_');
        const deleteButton = document.getElementById('deleteButton');

        // Event listener for file selection
        fileInput.addEventListener('change', function() {
            if (fileInput.files.length > 0) {
                // If a file is selected, show the delete button
                deleteButton.style.display = 'inline-block';
                if (uploaded) {
                   uploaded.remove();
                }
            } else {
                // If no file is selected, hide the delete button
                deleteButton.style.display = 'none';
            }
        });
        
        if (uploaded != null) {
            deleteButton.style.display = 'inline-block';
        }

        // Function to remove the selected file
        function removeFile() {
            // Clear the file input
            fileInput.value = '';
            // Hide the delete button
            deleteButton.style.display = 'none';
            uploaded.style.display = 'none';
           uploaded_.value = null;
        }
    </script>
</body>
</html>
