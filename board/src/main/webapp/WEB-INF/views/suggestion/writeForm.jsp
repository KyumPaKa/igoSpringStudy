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
	$("#btnWrite").click(function() {
		document.suggestionForm.submit();
	});
});
</script>
<script src="${path}/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
	<%@ include file="../include/menu.jsp"%>	
<form action="${path}/suggestion/write.do" name="suggestionForm" method="post" enctype="multipart/form-data" >
<table style="text-align: center;">
	<tr>
		<td>제목</td>
		<td><input type="text" name="sg_title" style="width: 100%;"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="5"><textarea cols="30" rows="20" name="sg_content"></textarea></td>
<script>
CKEDITOR.replace("sg_content", {
	filebrowserUploadUrl: "${path}/suggestion/imageUpload.do",
	height: "500px"
});
</script>
	</tr>
	<tr>
		<td style="height: 100px;">업로드 파일</td>
		<td colspan="5"><input type="file" name="file"></td>
	</tr>
	<tr>
		<td colspan="6">
			<input type="button" id="btnWrite" value="등록">
			<input type="button" id="btnList" value="취소">
		</td>
	</tr>
</table>
</form>
</body>
</html>
