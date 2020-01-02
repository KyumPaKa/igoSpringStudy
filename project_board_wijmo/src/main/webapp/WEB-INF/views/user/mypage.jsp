<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "../include/header.jsp" %>
<script>
$(function(){
	$("#btnUpdate").click(function(){
		document.form1.action="${path}/user/update.do";
		document.form1.submit();
	});
});
</script>
</head>
<body>
<%@ include file="../include/menuUser.jsp" %>
<h2>회원정보</h2>
<form name="form1" method="post">
<table border="1" width="400px">
	<tr>
		<td>아이디</td>
		<td><input name="userid" value="${map.userid}" readonly></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input name="email" value="${map.email}" readonly></td>

	<tr>
		<td colspan="2" align="center">
			<input type="button" value="수정" id="btnUpdate">
			<div style="color:red; ">${message}</div>
		</td>
	</tr>
</table>
</form>
</body>
</html>