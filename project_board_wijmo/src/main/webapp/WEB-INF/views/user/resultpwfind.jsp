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
<%@ include file="../include/menuUser.jsp"%>
<table>
	<tr>
		<td>비밀번호 찾기 결과입니다.</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">입력하신 "${userid}"의 비밀번호가 0000 으로 초기화 되었습니다.</td>
	</tr>
	<tr>
		<td colspan="2" align="center">보안을위해 로그인 후 MyPage에서 비밀번호를 변경해주세요.</td>
	</tr>
	<tr>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="button" value="로그인하러가기" onclick="loginpagereturn()">
		</td> 
	</tr>
</table>

</body>
</html>