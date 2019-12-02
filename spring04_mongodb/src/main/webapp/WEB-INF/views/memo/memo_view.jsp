<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
$(function(){
	$("#btnUpdate").click(function(){
		$("#form1").attr("action", "/memo_update.do");
		$("#form1").submit();
	});
	$("#btnDelete").click(function(){
		$("#form1").attr("action", "/memo_delete.do");
		$("#form1").submit();
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>메모장</h2>
<form id="form1" name="form1" method="post">
<input type="hidden" name="_id" value="${dto._id}">
이름 <input name="writer" id="writer" value="${dto.writer}">
메모 <input name="memo" id="memo" value="${dto.memo}" size="50">
<br>
<input type="button" id="btnUpdate" value="수정">
<input type="button" id="btnDelete" value="삭제">
<input type="button" id="btnList" value="목록" onclick="location.href = '/memo.do'">
</form>

</body>
</html>