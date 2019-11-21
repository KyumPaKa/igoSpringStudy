<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function show_result() {
	location.href = "show_result.do?survey_idx=${dto.survey_idx}";
}
</script>
</head>
<body>
<h2>온라인 설문조사</h2>
<h2>${dto.question}</h2>
<form method="post" action="save_survey.do">
	<input type="radio" name="num" value="1">${dto.ans1}<br>
	<input type="radio" name="num" value="2">${dto.ans2}<br>
	<input type="radio" name="num" value="3">${dto.ans3}<br>
	<input type="radio" name="num" value="4">${dto.ans4}<br>
	<br>
	<input type="hidden" name="survey_idx" value="${dto.survey_idx}">
	<input type="submit" value="투표">
	<input type="button" value="결과 확인" onclick="show_result()">
</form>
</body>
</html>