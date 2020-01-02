<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
function join(){
	location.href="${path}/user/join.do";
}
</script>
<script type="text/javascript">
function idfind(){
	location.href="${path}/user/idfind.do";
}
</script>
<script type="text/javascript">
function pwfind(){
	location.href="${path}/user/pwfind.do";
}
</script>
</head>
<body>
<%@ include file="../include/menuUser.jsp" %>
<h2>로그인 페이지</h2>
<span style="color:red;">${errMsg}</span>
<form action="${path}/user/login_check.do" method="post">
<table>
	<tr>
		<td>아이디</td>
		<td><input name="userid"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="submit" value="로그인">
			<input type="button" value="회원가입" onclick="join()">
		</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="button" value="ID 찾기" onclick="idfind()"> 
			<input type="button" value="PassWord 찾기" onclick="pwfind()">
		</td>
	</tr>
</table>
</form>
</body>
</html>



















