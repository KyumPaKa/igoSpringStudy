<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
$(function(){
	memo_list();
	$("#btnWrite").click(function(){
		memo_insert();
	});
});
function memo_insert(){
	var writer = $("#writer").val();
	var memo = $("#memo").val();
	var param = "writer=" + writer + "&memo=" + memo;
	$.ajax({
		type: "post",
		data: param,
		url: "/memo_insert.do",
		success: function(){
			memo_list();
			$("#writer").val("");
			$("#memo").val("");
		}
	});
}
function memo_list(){
	$.ajax({
		type: "post",
		url: "/memo_list.do",
		success: function(data){
			$("#memoList").html(data);
		}
	})
}
function memo_view(num){
	$(location).attr("href", "/memo_view.do?_id=" + num);
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>메모장</h2>
<div id="memoList"></div>
</body>
</html>