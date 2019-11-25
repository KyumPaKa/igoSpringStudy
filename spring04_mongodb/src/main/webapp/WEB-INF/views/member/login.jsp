<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
$(function(){
	$("#btnLogin").click(function() {
		$("#form1").attr("action","/member/login_check.do");
		$("#form1").submit();
	});
	
	$("#btnJoin").click(function() {
		$(location).attr("href", "/member/join.do");
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>로그인 하세요.</h2>
<form name="form1" id="form1" method="post">
<table border="1" style="width: 400px">
	<tr>
		<td>아이디</td>
		<td><input type="text" name="_id" id="_id" /></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd" id="passwd" / ><br>
		<font color="red">${map.message}</font></td>	
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" id="btnLogin" value="로그인" />
			<input type="button" id="btnJoin" value="회원가입" />
		</td>
	</tr>
</table>
</form>
</body>
</html>