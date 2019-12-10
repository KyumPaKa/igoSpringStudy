<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board list</title>
<%@ include file="../include/header.jsp" %>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script src="//cdn.ckeditor.com/4.13.0/standard/ckeditor.js"></script>

</head>
<script>
$(function(){
	$("#btnSave").click(function(){

		var refidxVal = document.getElementsByName("refidx");

		for (var i=0; i< refidxVal.length; i++) {
			if(refidxVal[i].checked) {
				/* alert(refidxVal[i].value); */
				console.log(refidxVal[i].value);
			}
		}

		document.form1.action = "${path}/notice/insert.do";
		document.form1.submit();
	});

});

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
	<form id="form1" name="form1" method="post" action="${path}/board/insert.do">
	
  		<input type='checkbox' name="refidx" value='2' />자유게시판
  		<input type='checkbox' name="refidx" value='3' />익명게시판
  		<input type='checkbox' name="refidx" value='4' />건의게시판
		
		<div>
			제목
			<input name="title" id="title" size="80" value="${list.title}">
		</div>
		
		<%-- <div>
			내용
			<textarea name="content" id="content" row="3" cols="80" value="${list.content}" ></textarea>
		</div> --%>
		
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
			<input type="hidden" id="refwriter" name="refwriter" value="${list.refwriter}">
			<!-- <input type="hidden" id="refwriter" name="refwriter" value="Admin1234"> -->
			<button type="button" id="btnSave">확인</button>
		</div>		
		
	</form>
	
</body>
</html>
