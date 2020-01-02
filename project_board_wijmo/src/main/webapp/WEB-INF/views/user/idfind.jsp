<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 찾기</title>
<%@ include file="../include/header.jsp" %>
<script>
function find(){
	var email = $("#email").val();
	console.log(email);
	if(email == ""){
		alert("이메일을 입력해주세요");
		}
	else{
		$("form").submit();
	}
}
</script>

</head>
<body>
<%@ include file="../include/menuUser.jsp" %>
	<div >
		<div >
			<form action="${path}/user/resultidfind.do" method="post">
				
				<div >
					<h3>아이디 찾기</h3>
				</div>
				<div>
					<p>
						<label>Email</label>
						<input type="text" id="email" name="email" >
						
					</p>
					<center>
					<p >
						<button type="button" id=findBtn onclick = "find()">find</button>
						<button type="button" onclick="history.go(-1);">Cancel</button>
					</p>
					</center>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
