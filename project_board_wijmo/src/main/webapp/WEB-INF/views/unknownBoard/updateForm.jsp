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
	$("#btnUpdate").click(function() {
		if(submitCheck()) {
			$("#updateForm").attr("action", "${path}/ub/update.do");
			$("#updateForm").submit();
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
function fileDelete(num) {
	$.ajax({
		type: "post",
		data: {idx:num},
		url: "${path}/ub/fileDelete.do",
		success: function(){
			 $("#file"+num).remove();
		}
	});
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
<form id="updateForm" method="post" enctype="multipart/form-data">
<table style="text-align: center; width: 1000px;" border="1">
	<tr>
		<td>제목</td>
		<td>
			<input type="text" name="title" id="title" style="width: 100%;" value="${dto.title}">
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="5"><textarea cols="30" rows="20" name="content" id="content" style="width: 100%;">${dto.content}</textarea></td>
	</tr>
	<tr>
		<td style="height: 100px;">파일<br>목록</td>
		<td colspan="5">
			<c:forEach items="${dto.attachList}" var="list">
			<div id="file${list.idx}">
			<c:forTokens items="${list.fileName}" delims="." var="type" varStatus="status">
				<c:if test="${status.last}">
					<c:choose>
						<c:when test="${type eq 'jpg' || type eq 'png' || type eq 'gif'}">
							<img style="width: 100px; height: 100px;" src="${path}/resources/freeBoard/images/<fmt:formatDate value="${dto.postDate}" pattern="yyyy/MM/dd/" />${list.fileName}">
						</c:when>
						<c:otherwise>
						<c:set var="length" value="${fn:length(list.fileName)}" />
						${fn:substring(list.fileName, 37, length)}
						</c:otherwise>
					</c:choose>
					<c:set var="length" value="${fn:length(list.fileName)}" />
					${fn:substring(list.fileName, 37, length)}
					<a href="#" onclick="fileDelete('${list.idx}')">삭제</a>
					<br>
				</c:if>
			</c:forTokens>
			</div>
			</c:forEach>
		<input type="file" name="file" multiple="multiple">
		</td>
	</tr>
	<tr>
		<td colspan="6">
			<input type="hidden" name="refIdx" value="${refIdx}">
			<input type="hidden" name="idx" value="${dto.idx}">
			<input type="hidden" name="search_option" value="${search_option}">
			<input type="hidden" name="keyword" value="${keyword}">
			<input type="button" id="btnUpdate" value="수정">
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
