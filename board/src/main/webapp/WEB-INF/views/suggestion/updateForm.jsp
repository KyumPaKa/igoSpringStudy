<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>질문 게시판</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
$(function() {
	$("#btnList").click(function() {
		location.href = "${path}/suggestion/list.do";
	});
	$("#btnUpdate").click(function() {
		document.suggestionForm.submit();
	});
});
</script>
<script src="${path}/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
	<%@ include file="../include/menu.jsp"%>
<form action="${path}/suggestion/update.do" name="suggestionForm" method="post" enctype="multipart/form-data" >
<input type="hidden" name="sg_idx" value="${suggestionDTO.sg_idx}">
<table style="text-align: center;">
	<tr>
		<td>제목</td>
		<td><input type="text" name="sg_title" style="width: 100%;" value="${suggestionDTO.sg_title}"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="5"><textarea cols="30" rows="20" name="sg_content">${suggestionDTO.sg_content}</textarea></td>
<script>
CKEDITOR.replace("sg_content", {
	filebrowserUploadUrl: "${path}/suggestion/imageUpload.do",
	height: "500px"
});
</script>
	</tr>
	<tr>
		<td style="height: 100px;">업로드된 파일</td>
		<td colspan="5">
			<c:forTokens items="${suggestionDTO.sg_file}" delims="." var="type" varStatus="status">
				<c:if test="${status.last}">
					<c:choose>
						<c:when test="${type eq 'jpg' || type eq 'png' || type eq 'gif'}">
							<img style="width: 100px; height: 100px;" src="${path}/resources/suggestion/images/<fmt:formatDate value="${suggestionDTO.sg_postDate}" pattern="yyyy/MM/dd/" />${suggestionDTO.sg_file}">
						</c:when>
						<c:otherwise>
						<c:set var="length" value="${fn:length(suggestionDTO.sg_file)}" />
						${fn:substring(suggestionDTO.sg_file, 37, length)}<br><br>
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:forTokens>
			<input type="file" name="file">
		</td>
	</tr>
	<tr>
		<td colspan="6">
			<input type="button" id="btnUpdate" value="수정">
			<input type="button" id="btnList" value="취소">
		</td>
	</tr>
</table>
</form>
</body>
</html>
