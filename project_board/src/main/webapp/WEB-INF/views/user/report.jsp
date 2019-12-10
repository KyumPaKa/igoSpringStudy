<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>신고하기</title>
<%@ include file="../include/header.jsp"%>
</head>
<body>
	<div class="report">
	<br>
		<h1>신고하기</h1>
		<br>
		<div class="Area">
			<form action="${path}/user/insertreport.do" method="post">
				<input type="hidden" id="refIdx" name="refboardcategory"
					value="${list.refIdx}">
					 <input type="hidden" id="idx"name="refidx" value="${list.idx}">
					 <input type="hidden" id="refuserid" name="refuserid" value="${list.refWriter}">
				<table>
					<tr>
						<td><input type="text" value="${list.title}" readonly/></td>
					</tr>
					<tr>
						
						<td><input type="radio" value="욕설" id="category" name="category">욕설<br>
							<input type="radio" value="도배" id="category" name="category">도배<br> <input
							type="radio" value="음란물 게시" id="category" name="category">음란물게시<br> <input
							type="radio" value="기타" id="category" name="category">기타</td>
					</tr>
				</table>
				<textarea id="content" name="content" placeholder="신고 사유를 적어주세요"></textarea>
				<br> <br>
				<button type="button" value="확인" class="postbtn btn-danger"
					onclick="postbtn();">신고하기</button>
			</form>
		</div>
	</div>
</body>
<script>
	function postbtn() {
		if(!$("input:radio[name='category']").is(":checked")){
		alert("사유를 선택하세요.")
		}else{
		$("form").submit();
		self.close();
		alert("신고가 완료되었습니다.");
		}
	}
</script>
</html>