<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script src="//cdn.ckeditor.com/4.13.0/standard/ckeditor.js"></script>



<script>
$(function(){
	
	$("#btnUpdate").click(function(){

		alert("업데이트" + idx);
			
		var str = "";
		$("#form1").append(str);
		/* location.href = "${path}/notice/updateForm.do"; */
		document.form1.action = "${path}/notice/updateForm.do";
		document.form1.submit();
	});
	
	$("#btnDelete").click(function(){
	
		if (confirm("삭제하시겠습니까?")) {
			document.form1.action = "${path}/notice/delete.do";
			document.form1.submit();
		}
	});

	/* $("#btnList").click(function(){
			document.form1.action = "${path}/notice/list.do";
			document.form1.submit();
	}); */

	$("#btnList").click(function() {
		history.go(-1);
	});
	
});

</script>
</head>
<body>
<sec:authorize
	access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_BLACK')">
	<%@ include file="../include/menuUser.jsp"%>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
	<%@ include file="../include/menuAdmin.jsp"%>
</sec:authorize>
	
	<form id="form1" name="form1" method="post" action="${path}/notice/updateForm.do">
	
		<div>작성일자: <fmt:formatDate value="${list.postdate}" pattern="yyyy-MM-dd a HH:mm:ss" /></div>
		
		<div>조회수: ${list.viewcnt}</div>
		
		<div>작성자: ${list.refwriter}</div>
		
		<div>
			제목<input name="title" id="title" size="80" value="${list.title}" placeholder="제목을 입력하세요" readonly>
		</div>
		
		<div style="width:800px;"><textarea id="content" name="content"
			rows="3" cols="80" readonly>${list.content}</textarea>
		</div>
		
		<script>
			CKEDITOR.replace("content", {
				filebrowserUploadUrl: "${path}/imageUpload.do",
				height: "300px"
			});
		</script>
		
		<div style="width:700px; text-align:center;">
			<input type="hidden" id="idx" name="idx" value="${list.idx}">
			
			<sec:authentication property="principal.username" var="loginID" />
			<c:if test="${list.refwriter == loginID}">
				<button type="button" id="btnUpdate">수정</button>
				<button type="button" id="btnDelete">삭제</button>
			</c:if>
		
			<button type="button" id="btnList">목록</button>	
		</div>
		
	</form>
	
</body>
</html>