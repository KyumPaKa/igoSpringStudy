<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../include/header.jsp" %>
<title>Insert title here</title>
<script type="text/javascript">
function join() {
	location.href = "${path}/user/join.do"
}
</script>
</head>
<body>
<h2>로그인하세요</h2>
<span style="color: red;">${errMsg}</span>
<form action="${path}/user/login_check.do" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<table>
	<tr>
		<td>아이디</td>
		<td><input type="text" name="userid"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="로그인">
			<input type="button" value="회원가입" onclick="join()">
		</td>
	</tr>
</table>
</form>
</body>
</html>