<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 찾기</title>
<%@ include file="../include/header.jsp"%>
<script>
	function pwfind() {
		var userid = $("#userid").val();
		if (userid == "") {
			alert("아이디와 이메일을 확인해주세요");
		} else {
			$("form").submit();
		}
	}
</script>

</head>
<body>
	<%@ include file="../include/menuUser.jsp"%>
	<div>
		<div>
			<form action="${path}/user/resultpwfind.do" method="post">
				<div>
					<h3>비밀번호 찾기</h3>
				</div>
				<div>
					<p>
						<label>유저아이디</label> &nbsp;
						<input type="text" id="userid" name="userid">
					</p>
					<P>
						<label>이메일</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" id="email" name="email" >
					</P>
					<p>
						<button type="button" id=findBtn onclick="pwfind()">find</button>
						<button type="button" onclick="history.go(-1);">Cancel</button>
					</p>
				</div>
			</form>
		</div>
	</div>
</body>
</html>