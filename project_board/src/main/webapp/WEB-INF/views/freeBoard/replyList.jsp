<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>질문 게시판</title>
<%@ include file="../include/header.jsp"%>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function() {
	$("#btnWriteReply").click(function() {
		if($("#content2").val() != "") {
			$("#refIdx2").val($("#temp").val());
			var formdata = $("form[id=replyForm]").serialize();
	        $.ajax({
	            type : 'post',
	            url : '${path}/fbr/write.do',
	            data : formdata,
	            success : function(json){
	            	listReply("${pager.curPage}");
	            	$("#replyCnt").html(eval($("#replyCnt").html() + "+1"));
	            }
	        });
		} else {
			alert('내용을 작성해주세요.');
			$("#content2").focus();
		}
	});
});
function updateReplyForm(num, content) {
	<c:forEach items="${list}" var="row">
	if(num != '${row.idx}') {
		$("#textArea${row.idx}").html('${row.content}');
		var temp = '<input type="button" onclick="updateReplyForm(\'${row.idx}\', \'${row.content}\')" value="수정"> ';
			temp += '<input type="button" onclick="deleteReply(\'${row.idx}\')" value="삭제"> ';
// 			temp += '<input type="button" value="신고" onclick="reportrply()">';
		$("#btnArea${row.idx}").html(temp);
	}
	</c:forEach>
	var change = "";
		change += '<input type="button" onclick="updateReply(\'' + num + '\')" value="완료">';
		change += '<input type="button" onclick="listReply(\'1\')" value="취소">';
	$("#btnArea"+num).html(change);
	$("#textArea"+num).html('<input type="text" id="recontent" value="' + content + '" style="width: 100%;">');
}
function updateReply(num) {
	var _content = $("#recontent").val();
	$.ajax({
		  url: '${path}/fbr/update.do',
		  type: 'post',
		  data: {idx:num, content:_content},
		  success : function() {
			  listReply("${pager.curPage}");
		  }
	});
}
function deleteReply(num) {
	if(confirm('삭제하시겠습니까?')) {
		$.ajax({
			  url: '${path}/fbr/delete.do',
			  type: 'post',
			  data: {idx:num},
			  success : function() {
				  listReply("1");
				  $("#replyCnt").html(eval($("#replyCnt").html() + "-1"));
			  }
		});
	}
}
function deleteAdmin(idx) {
	if(confirm("삭제하시겠습니까?")) {
		location.href = "${path}/ubr/deleteAdmin.do?refIdx=${refIdx}&idx=" + idx + "&category=${category}";
	}
}
function btnWriteReReplyForm(num, refNum) {
	<c:forEach items="${list}" var="row">
	if(num != '${row.idx}') {
		$("#tr${row.idx}").html('');
	}
	</c:forEach>
	var appendTag = '<td colspan="2"><form id="rereplyForm" onsubmit="return false;">';
	 	appendTag += '<input type="hidden" name="refIdx" value="' + refNum + '">';
		appendTag += '<input type="hidden" name="groupIdx" value="' + num + '">';
		appendTag += '<input type="text" id="rerecontent" name="content" placeholder="댓글을 작성해주세요." style="width: 100%;">';
		appendTag += '<input type="button" onclick="btnWriteReReply()" value="등록">';
		appendTag += '</form></td>'
	$("#tr"+num).append(appendTag);
}
function btnWriteReReply() {
	if($("#rerecontent").val() != "") {
		var formdata = $("form[id=rereplyForm]").serialize();
        $.ajax({
            type : 'post',
            url : '${path}/fbr/write.do',
            data : formdata,
            success : function(json){
            	listReply("${pager.curPage}");
            	$("#replyCnt").html(eval($("#replyCnt").html() + "+1"));
            }
        });
	} else {
		alert('내용을 작성해주세요.');
		$("#rerecontent").focus();
	}
}
function aTitle(idx) {
	listReply(idx);
}
function aFirst() {
	listReply("1");
}
function aPrev() {
	listReply("${pager.prevPage}");
}
function aNow(cnt) {
	listReply(cnt);
}
function aPost() {
	listReply("${pager.nextPage}");
}
function aLast() {
	listReply("${pager.totPage}");
}
function reportrply(idx,refIdx){	
	//var refidx=$("#refIdx").val();
	//var idx=$("#idx").val();
	console.log(refIdx);
	console.log(idx);
	var urll = "${path}/user/reportrply?idx="+idx+"&refidx="+refIdx;
	window.open(urll,'신고하기','width=430,height=450,status=no,scrollbars=yes');
}
</script>
</head>
<body>
<sec:authentication property="principal.username" var="loginID" />
<table border="1" style="width: 1000px;">
	<tr>
		<td style="text-align: center;">
		<form id="replyForm" onsubmit="return false;">
			<input type="hidden" name="refIdx" id="refIdx2">
			<input type="text" id="content2" name="content" placeholder="댓글을 작성해주세요." style="width: 100%;">
		</form>
		</td>
		<td style="text-align: center;">
			<input type="button" id="btnWriteReply" value="등록">
		</td>
	</tr>
	<c:forEach items="${list}" var="row">
<input type="hidden" id="refIdx" value="${row.refIdx}">
<input type="hidden" id="idx" value="${row.idx}">
	<tr>
		<c:if test="${row.deleteCheck == 0}">
		<td>
		<c:if test="${row.groupNo != 1}">
			<c:forEach begin="1" end="${row.depth}">
			&nbsp;
			</c:forEach>
			└&nbsp;
		</c:if>
		<div style="display: inline-block;">
		${row.refWriter} | <fmt:formatDate value="${row.regDate}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" />
		<c:if test="${row.groupNo == 1}">
			<input type="button" onclick="btnWriteReReplyForm('${row.idx}', '${row.refIdx}')" value="답글">
		</c:if>
		<br>
			<div id="textArea${row.idx}">
			${row.content}
			</div>
		</div>
		</td>
		<td style="text-align: center; width: 200px;" id="btnArea${row.idx}">
		<c:if test="${row.refWriter == loginID}">
			<input type="button" onclick="updateReplyForm('${row.idx}', '${row.content}')" value="수정">
			<input type="button" onclick="deleteReply('${row.idx}')" value="삭제">
		</c:if>
<%-- 			<input type="button" value="신고" onclick="reportrply('${row.idx}','${row.refIdx}')"> --%>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<br><input type="button" id="deleteAdmin('${row.idx}')" value="관리자삭제">
		</sec:authorize>
		</td>
		</c:if>
		<c:if test="${row.deleteCheck != 0}">
		<td colspan="2">삭제된 댓글 입니다.</td>
		</c:if>
	</tr>
	<tr id="tr${row.idx}"></tr>
	</c:forEach>
	<tr align="center">
		<td colspan="2">
			<c:if test="${pager.curBlock != 1}">
				<a onclick="aFirst()" href="#">[처음]</a>
			</c:if>
			<c:if test="${pager.curBlock != 1}">
				<a onclick="aPrev()" href="#">[이전]</a>
			</c:if>
			<c:forEach var="cnt" begin="${pager.blockBegin}" end="${pager.blockEnd}">
				<c:choose>
					<c:when test="${cnt == pager.curPage}">
						<font color="red">${cnt}</font>
					</c:when>
					<c:otherwise>
						<a onclick="aNow('${cnt}')" href="#">${cnt}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pager.curBlock < pager.totBlock}">
				<a onclick="aPost()" href="#">[다음]</a>
			</c:if>
			<c:if test="${pager.curBlock < pager.totBlock}">
				<a onclick="aLast()" href="#">[끝]</a>
			</c:if>
		</td>
	</tr>
</table>
</body>
</html>