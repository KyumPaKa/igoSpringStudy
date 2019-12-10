<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board list</title>
<%@ include file="../include/header.jsp" %>
<script src="//cdn.ckeditor.com/4.13.0/standard/ckeditor.js"></script>

</head>
<script>
$(function(){ 
	$("#btnSave").click(function(){
		document.form1.action = "${path}/notice/update.do";
		document.form1.submit();
	});
	check();
});
function check() {
	<c:forEach var="a" items="${checkedList}" varStatus="status" >
		<c:set var="name" value="${a.refpoint}" />
		var chkb${name} = '${name}';
	</c:forEach>
	
	for(var i = 1; i <= 4; i++) {
		if(i == chkb1) {
			$("input:checkbox[id='chkb" + i + "']").prop("checked", true);
		}
		if(i == chkb2) {
			$("input:checkbox[id='chkb" + i + "']").prop("checked", true);
		}
		if(i == chkb3) {
			$("input:checkbox[id='chkb" + i + "']").prop("checked", true);
		}
		if(i == chkb4) {
			$("input:checkbox[id='chkb" + i + "']").prop("checked", true);
		}
	}
}
</script>
<body>
<sec:authorize
	access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_BLACK')">
	<%@ include file="../include/menuUser.jsp"%>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
	<%@ include file="../include/menuAdmin.jsp"%>
</sec:authorize>

<h2>글쓰기</h2>

	<form id="form1" name="form1" method="post" action="${path}/notice/update.do">
	
		<input type='checkbox' name="refidx" value='2' id="chkb2"/>자유게시판
		<input type='checkbox' name="refidx" value='3' id="chkb3"/>익명게시판
		<input type='checkbox' name="refidx" value='4' id="chkb4"/>건의게시판
		
		<div>
			제목
			<input name="title" id="title" size="80" value="${list.title}">
			${npList.refidx}
		</div>
		
		
		<div style="width:800px;"><textarea id="content" name="content"
			rows="3" cols="80" >${list.content}</textarea>
		</div>
		
		<script>
			CKEDITOR.replace("content", {
				filebrowserUploadUrl: "${path}/imageUpload.do",
				height: "300px",
				enterMode : CKEDITOR.ENTER_BR,
		        shiftEnterMode: CKEDITOR.ENTER_P
			});
		</script>
		
		<div style="width:700px; text-align=center;">
			<input type="hidden" id="idx" name="idx" value="${list.idx}">
			<button type="button" id="btnSave">확인</button>
		</div>		
		
	</form>
	
</body>
</html>
