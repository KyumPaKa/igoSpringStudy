<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>질문 게시판</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
$(function() {
	$("#btnCancle").click(function() {
		history.back();
	});
	$("#btnWrite").click(function() {
		if(submitCheck()) {
			$("#writeForm").attr("action", "${path}/ub/write.do");
			$("#writeForm").submit();
		}
	});
});
function submitCheck() {
	var title = $("#title").val();
	if(title == "") {
		alert('제목을 작성해주세요.');
		return false;
	}
	if (CKEDITOR.instances.content.getData() == "")
	{
		alert('내용을 입력 하세요');
		CKEDITOR.instances.content.focus();
		return false;
	}
	return true;
}
</script>
<script src="${path}/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
<sec:authorize
	access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_BLACK')">
	<%@ include file="../include/menuUser.jsp"%>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
	<%@ include file="../include/menuAdmin.jsp"%>
</sec:authorize>	
<form id="writeForm" method="post" enctype="multipart/form-data">
<table style="text-align: center; width: 1000px;" border="1">
	<tr>
		<td>제목</td>
		<td>
			<input type="text" name="title" id="title" style="width: 100%;">
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="5"><textarea cols="30" rows="20" name="content" id="content" style="width: 100%;"></textarea></td>
	</tr>
	<tr>
		<td style="height: 100px;">파일 목록</td>
		<td colspan="5"><input type="file" name="file" multiple="multiple"></td>
	</tr>
	<tr>
		<td colspan="6">
			<input type="hidden" name="search_option" value="${search_option}">
			<input type="hidden" name="keyword" value="${keyword}">
			<input type="hidden" name="refIdx" value="${refIdx}">
			<input type="button" id="btnWrite" value="등록">
			<input type="button" id="btnCancle" value="취소">
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
CKEDITOR.replace("content", {
	filebrowserUploadUrl: "${path}/ub/imageUpload.do",
	height: "500px"
});
</script>
</html>
