<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>질문 게시판</title>
<%@ include file="../include/header.jsp"%>

		
<script type="text/javascript">
noticeBoard("1");
$(function() {
	listReply("1");
});
function listReply(num){
	var _category = '${category}';
	$.ajax({
		type: "post",
		data: {refIdx:${refIdx},curPage:num,category:_category},
		url: "${path}/mm/view.do",
		success: function(result){
			$("#list").html(result); 
		}
	});
}
function noticeBoard(num){
	$.ajax({
		type: "post",
		data: {refIdx:${refIdx},curPage:num},
		url: "${path}/notice/list.do",
		success: function(result){
			$("#noticeBoard").html(result); 
		}
	});
}
</script>
</head>
<body >
<sec:authorize access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_BLACK')">
	<%@ include file="../include/menuUser.jsp"%>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
	<%@ include file="../include/menuAdmin.jsp"%>
</sec:authorize>
<div id="list"></div>
</body>
</html>
