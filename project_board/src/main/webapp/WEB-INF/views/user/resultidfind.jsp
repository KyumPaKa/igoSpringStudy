<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기결과</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
function loginpagereturn(){
	location.href="${path}/user/login.do";
}
</script>
</head>
<body>
<%@ include file="../include/menuUser.jsp" %>
<table>
	<tr>
		<td>아이디찾기 결과입니다.</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">"${userid}"입니다.</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="button" value="로그인하러가기" onclick="loginpagereturn()"> 
	</tr>
</table>

</body>
</html>