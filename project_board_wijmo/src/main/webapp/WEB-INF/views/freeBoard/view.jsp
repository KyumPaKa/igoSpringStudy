<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>질문 게시판</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
$(function() {
	listReply("1");
	$("#btnWriteForm").click(function() {
		location.href = "${path}/fb/writeForm.do?refIdx=${refIdx}";
	});
	$("#btnWriteFormBlack").click(function() {
		location.href = "${path}/qa/writeForm.do?refIdx=${refIdx}";
	});
	$("#btnList").click(function() {
		location.href = "${path}/fb/list.do?refIdx=${refIdx}&search_option=${search_option}&keyword=${keyword}";
	});
	$("#btnList2").click(function() {
		location.href = "${path}/fb/list.do?refIdx=${refIdx}&search_option=${search_option}&keyword=${keyword}";
	});
	$("#btnListBlack").click(function() {
		location.href = "${path}/qa/list.do?refIdx=${refIdx}&search_option=${search_option}&keyword=${keyword}";
	});
	$("#btnList2Black").click(function() {
		location.href = "${path}/qa/list.do?refIdx=${refIdx}&search_option=${search_option}&keyword=${keyword}";
	});
	$("#btnUpdateForm").click(function() {
		location.href = "${path}/fb/updateForm.do?refIdx=${refIdx}&idx=${dto.idx}&search_option=${search_option}&keyword=${keyword}";
	});
	$("#btnUpdateFormBlack").click(function() {
		location.href = "${path}/qa/updateForm.do?refIdx=${refIdx}&idx=${dto.idx}&search_option=${search_option}&keyword=${keyword}";
	});
	$("#btnDelete").click(function() {
		if(confirm("삭제하시겠습니까?")) {
			location.href = "${path}/fb/delete.do?refIdx=${refIdx}&idx=${dto.idx}";
		}
	});
	$("#btnDeleteBlack").click(function() {
		if(confirm("삭제하시겠습니까?")) {
			location.href = "${path}/qa/delete.do?refIdx=${refIdx}&idx=${dto.idx}";
		}
	});
	$("#btnDeleteAdmin").click(function() {
		if(confirm("삭제하시겠습니까?")) {
			location.href = "${path}/fb/deleteAdmin.do?refIdx=${refIdx}&idx=${dto.idx}&category=${category}";
		}
	});
	$("#btnGood").click(function() {
		$("input:checkbox[id='btnGood']").prop("checked", false);
		$("input:checkbox[id='btnBad']").prop("checked", false);
		$.ajax({
			  url: '${path}/fb/good.do',
			  type: 'post',
			  data: {idx:${dto.idx}},
			  success : function(result) {
				  if(result == 1) {
				  	  var good = $("#good").html();
				   	  $("#good").html(eval(good + '+1'));
				   	  $("input:checkbox[id='btnGood']").prop("checked", true);
				  } else if(result == 2) {
				  	  var good = $("#good").html();
				   	  $("#good").html(eval(good + '-1'));
				  } else if(result == 3) {
				  	  var good = $("#good").html();
				  	  var bad = $("#bad").html();
				   	  $("#good").html(eval(good + '+1'));
				   	  $("#bad").html(eval(bad + '-1'));
				   	  $("input:checkbox[id='btnGood']").prop("checked", true);
				  }
			  }
		});
	});
	$("#btnBad").click(function() {
		$("input:checkbox[id='btnGood']").prop("checked", false);
		$("input:checkbox[id='btnBad']").prop("checked", false);
		$.ajax({
			  url: '${path}/fb/bad.do',
			  type: 'post',
			  data: {idx:${dto.idx}},
			  success : function(result) {
				  if(result == 1) {
				  	  var bad = $("#bad").html();
				   	  $("#bad").html(eval(bad + '+1'));
				   	  $("input:checkbox[id='btnBad']").prop("checked", true);
				  } else if(result == 2) {
				  	  var bad = $("#bad").html();
				   	  $("#bad").html(eval(bad + '-1'));
				  } else if(result == 3) {
				  	  var good = $("#good").html();
				  	  var bad = $("#bad").html();
				   	  $("#good").html(eval(good + '-1'));
				   	  $("#bad").html(eval(bad + '+1'));
				   	  $("input:checkbox[id='btnBad']").prop("checked", true);
				  }
			  }
		});
	});
});
function listReply(num){
	var _category = '${category}';
	$.ajax({
		type: "post",
		data: {refIdx:${dto.idx},curPage:num,category:_category},
		url: "${path}/fbr/list.do",
		success: function(result){
			$("#listReply").html(result); 
		}
	});
}
function fileDown(_fileName) {
	location.href = "${path}/fb/download.do?fileName=" + _fileName;
}
function fileDownBlack(_fileName) {
	location.href = "${path}/qa/download.do?fileName=" + _fileName;
}
function report(){	
	var refidx=$("#refIdx").val();
	var idx=$("#idx").val();
	var urll = "${path}/user/report.do?refidx=${refIdx}&idx=${dto.idx}";
	window.open(urll,'신고하기','width=430,height=450,status=no,scrollbars=yes');
}
</script>
</head>
<body>
<sec:authorize
	access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_BLACK')">
	<%@ include file="../include/menuUser.jsp"%>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
	<%@ include file="../include/menuAdmin.jsp"%>
</sec:authorize>
<sec:authentication property="principal.username" var="loginID" />
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')" var="adminCheck" />
<c:choose>
	<c:when test="${dto.secretCheck == 0 || dto.refWriter == loginID || adminCheck}">
<div align="right">
<input type="hidden" id="refIdx" value="${refIdx}">
<input type="hidden" id="idx" value="${dto.idx}">
<input type="button" value="신고" onclick="report()">
<sec:authorize access="hasRole('ROLE_BLACK')">
	<input type="button" id="btnWriteFormBlack" value="등록">
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_MANAGER','ROLE_ADMIN')">
	<input type="button" id="btnWriteForm" value="등록">
</sec:authorize>
<sec:authorize access="hasRole('ROLE_BLACK')">
	<input type="button" id="btnListBlack" value="목록">
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_MANAGER','ROLE_ADMIN')">
	<input type="button" id="btnList" value="목록">
</sec:authorize>
</div>
<table style="text-align: center; width: 1000px;" border="1">
	<tr>
		<th>&nbsp;</th>
		<th style="width: 500px;" id="title">${dto.title}</th>
		<th>${dto.refWriter}</th>
		<th colspan="3" style="font-size: 15px;"><fmt:formatDate value="${dto.postDate}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" /></th>
	</tr>
	<tr>
		<td style="height: 300px;">내용</td>
		<td colspan="5" id="content">${dto.content}</td>
	</tr>
	<tr>
		<td colspan="6">
			<input type="checkbox" id="btnGood" <c:out value="${feel == '0' ? 'checked' : ''}" />>좋아요
			<input type="checkbox" id="btnBad" <c:out value="${feel == '1' ? 'checked' : ''}" />>싫어요
		</td>
	</tr>
	<tr>
		<td colspan="4" style="text-align: left;">&nbsp;
		댓글수 : <span id="replyCnt">${replyCnt}</span> &nbsp;|&nbsp;
		조회수 : ${dto.viewCnt} &nbsp;|&nbsp;
		좋아요 : <span id="good">${dto.goodCnt}</span> &nbsp;|&nbsp;
		싫어요 : <span id="bad">${dto.badCnt}</span></td>
		<td colspan="2">
		<c:if test="${dto.refWriter == loginID}">
		<sec:authorize access="hasRole('ROLE_BLACK')">
			<input type="button" id="btnUpdateFormBlack" value="수정">
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_MANAGER','ROLE_ADMIN')">
			<input type="button" id="btnUpdateForm" value="수정">
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_BLACK')">
			<input type="button" id="btnDeleteBlack" value="삭제">
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_MANAGER','ROLE_ADMIN')">
			<input type="button" id="btnDelete" value="삭제">
		</sec:authorize>
		</c:if>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<input type="button" id="btnDeleteAdmin" value="관리자삭제">
		</sec:authorize>
			<input type="button" value="신고" onclick="report()">
		<sec:authorize access="hasRole('ROLE_BLACK')">
			<input type="button" id="btnList2Black" value="목록">
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_MANAGER','ROLE_ADMIN')">
			<input type="button" id="btnList2" value="목록">
		</sec:authorize>
		</td>
	</tr>
	<c:if test="${fn:length(dto.attachList) != 0}">
	<tr>
		<td style="height: 100px;">파일<br>목록</td>
		<td colspan="5">
			<c:forEach items="${dto.attachList}" var="list">
			<c:forTokens items="${list.fileName}" delims="." var="type" varStatus="status">
				<c:if test="${status.last}">
					<c:choose>
						<c:when test="${type eq 'jpg' || type eq 'png' || type eq 'gif'}">
							<img style="width: 100px; height: 100px;" src="${path}/resources/freeBoard/images/${list.fileName}">
						</c:when>
						<c:otherwise>
						<c:set var="length" value="${fn:length(list.fileName)}" />
						${fn:substring(list.fileName, 37, length)}
						</c:otherwise>
					</c:choose>
					<c:set var="length" value="${fn:length(list.fileName)}" />
				<sec:authorize access="hasRole('ROLE_BLACK')">
					<a href="#" onclick="fileDownBlack('${list.fileName}')">${fn:substring(list.fileName, 37, length)}</a><br>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_MANAGER','ROLE_ADMIN')">
					<a href="#" onclick="fileDown('${list.fileName}')">${fn:substring(list.fileName, 37, length)}</a><br>
				</sec:authorize>
				</c:if>
			</c:forTokens>
			</c:forEach>
		</td>
	</tr>
	</c:if>
</table>
<input type="hidden" id="temp" value="${dto.idx}">
<div id="listReply">&nbsp;</div>
	</c:when>
	<c:otherwise>
<h2>비밀글입니다.</h2>
<input type="button" onclick="history.back()" value="목록">
	</c:otherwise>
</c:choose>
</body>
</html>
