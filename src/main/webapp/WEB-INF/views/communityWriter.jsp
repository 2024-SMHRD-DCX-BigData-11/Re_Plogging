<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="modal.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RE:PLOGGING COMMUNITYWRITE</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/css/communityWriter.css">
</head>
<body>
    <div class="communityWrite-container">
        <h1>커뮤니티 글쓰기</h1>
        <div class="commWrite-line"></div>
        <form action="${pageContext.request.contextPath}/${!empty community ? 'edit' : 'submitPost'}" method="post" enctype="multipart/form-data">
            <div class="commWrite-group">
            <select id="category" class="commWrite-category" name="category" required>
            	<option value="" disabled selected>카테고리를 선택해 주세요.</option>
                <option value="plogging" ${community.category == '플로깅' ? 'selected' : ''}>플로깅</option>
                <option value="separation" ${community.category == '분리배출' ? 'selected' : ''}>분리배출</option>
                <option value="freeboard" ${community.category == '자유게시판' ? 'selected' : ''}>자유게시판</option>
            </select>
            <input type="text" id="title" class="commWrite-title" maxlength="1200" name="title" autocomplete="off" placeholder="제목을 입력해 주세요." value="${community.title}" required>
			</div>
            <textarea id="content" class="commWrite-content" name="content" autocomplete="off" placeholder="내용을 입력해 주세요." required>${community.content}</textarea>

			<div class="commWrite-bottom-group">
            <div id="files" class="commWrite-file">
               <c:if test="${!empty community.img}">
                  <p id="uploaded">📢&nbsp;<span class="nowFile-point">현재 파일</span> ${community.img}</p>
              </c:if>
      
              <!-- 새로운 파일 선택 -->
              <input type="file" id="file" class="commWrite-file" name="file">
               <button type="button" id="deleteButton" onclick="removeFile()">삭제</button>
            </div>
            <input type="hidden" id="uploaded_" name="uploaded" value="${community.img}">

            <input type="hidden" name="writerId" value="${sessionScope.user.userIdx}">
            <c:if test="${!empty community}">
                <input type="hidden" name="editId" value="${community.idx}">
            </c:if>

            <div class="commWrite-button-group">
                <button type="submit" class="commWrite-btn">등록</button>
                <a href="${pageContext.request.contextPath}/community" class="commWrite-btn-cancel">취소</a>
            </div>
            </div>
        </form>
    </div>
    
    <footer>© 2024 지구수호대 Korea Corporation All Rights Reserved.</footer>
    
    <script>
        const fileInput = document.getElementById('file');
        const uploaded = document.getElementById('uploaded');
        const uploaded_ = document.getElementById('uploaded_');
        const deleteButton = document.getElementById('deleteButton');

        fileInput.addEventListener('change', function() {
            if (fileInput.files.length > 0) {
                deleteButton.style.display = 'inline-block';
                if (uploaded) {
                   uploaded.remove();
                }
            } else {
                deleteButton.style.display = 'none';
            }
        });
        
        if (uploaded != null) {
            deleteButton.style.display = 'inline-block';
        }

        function removeFile() {
            fileInput.value = '';
            deleteButton.style.display = 'none';
            uploaded.style.display = 'none';
           uploaded_.value = null;
        }
    </script>
</body>
</html>
