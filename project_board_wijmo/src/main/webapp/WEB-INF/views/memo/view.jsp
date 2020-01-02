<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>질문 게시판</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
$(function() {
	$("#btnWrite").click(function() {
		if($("#content").val() != "") {
	        $.ajax({
	            type : 'post',
	            url : '${path}/mm/write.do',
	            data :{refIdx:${refIdx}, content:$("#content").val()},
	            success : function(json){
	            	listReply("1");
	            }
	        });
		} else {
			alert('내용을 작성해주세요.');
			$("#content").focus();
		}
	});
});
function btnDelete(num, curPage) {
	if(confirm('삭제하시겠습니까?')) {
		$.ajax({
			  url: '${path}/mm/delete.do',
			  type: 'post',
			  data: {idx:num},
			  success : function() {
				  listReply(curPage);
			  }
		});
	}
}
function deleteAdmin(idx) {
	if(confirm("삭제하시겠습니까?")) {
		location.href = "${path}/mm/deleteAdmin.do?refIdx=${refIdx}&idx=" + idx + "&category=${category}";
	}
}
function btnUpdateForm(num, _content, curPage) {
	<c:forEach items="${list}" var="list">
	if(num != '${list.idx}') {
		var temp = "";
			temp += '<div align="left">';
			temp += '  <input type="checkbox" id="good${list.idx}" onclick="btnGood(\'${list.idx}\')" <c:out value="${list.feel == \'0\' ? \'checked\' : \'\'}" />>좋아요';
			temp += ' <input type="checkbox" id="bad${list.idx}" onclick="btnBad(\'${list.idx}\')" <c:out value="${list.feel == \'1\' ? \'checked\' : \'\'}" />>싫어요';
			temp += '</div><br><div align="right">';
			temp += '  <input type="button" value="신고" onclick="report()">';
			temp += '  <sec:authentication property="principal.username" var="loginID" />';
			temp += '  <c:if test="${list.refWriter == loginID}">';
			temp += '  <input type="button" onclick="btnUpdateForm(\'${list.idx}\', \'${list.content}\',\'${pager.curPage}\')" value="수정">';
			temp += '  <input type="button" onclick="btnDelete(\'${list.idx}\',\'${pager.curPage}\')" value="삭제">';
			temp += '  </c:if>';
			temp += '</div>';
		$("#btnArea${list.idx}").html(temp);
		$("#originContent${list.idx}").html('${list.content}');
	}
	</c:forEach>
	$("#originContent"+num).attr("style", "padding:0px;");
	var change = '';
		change += '<textarea placeholder="내용을 작성해주세요." name="content" id="recontent" style="width: 100%; height: 50px;">' + _content + '</textarea>';
	$("#originContent"+num).html(change);
	var btn = '<input type="button" onclick="btnUpdate(\'' + num + '\',\''+ curPage+'\')" value="수정"> ';
		btn += '<input type="button" onclick="listReply(\'' + curPage + '\');" value="취소">';
	$("#btnArea"+num).html(btn);
}
function btnUpdate(num, curPage) {
	$.ajax({
		  url: '${path}/mm/update.do',
		  type: 'post',
		  data: {idx:num, content:$("#recontent").val()},
		  success : function() {
			  alert('수정 완료');
			  listReply(curPage);
		  }
	});
}
function aTitle(idx) {
	location.href = "${path}/ub/view.do?refIdx=${refIdx}&idx=" + idx + "";
}
function aFirst() {
	location.href = "${path}/ub/list.do?refIdx=${refIdx}&curPage=1";
}
function aPrev() {
	location.href = "${path}/ub/list.do?refIdx=${refIdx}&curPage=${pager.prevPage}";
}
function aNow(cnt) {
	location.href = "${path}/ub/list.do?refIdx=${refIdx}&curPage=" + cnt + "";	
}
function aPost() {
	location.href = "${path}/ub/list.do?refIdx=${refIdx}&curPage=${pager.nextPage}";
}
function aLast() {
	location.href = "${path}/ub/list.do?refIdx=${refIdx}&curPage=${pager.totPage}";
}
function report(num){
	var refidx=$("#refIdx").val();
	var urll = "${path}/user/report.do?refidx=${refIdx}&idx="+num;
	window.open(urll,'신고하기','width=430,height=450,status=no,scrollbars=yes');
}
function btnGood(num) {
	$("input:checkbox[id='good"+num+"']").prop("checked", false);
	$("input:checkbox[id='bad"+num+"']").prop("checked", false);
	$.ajax({
		  url: '${path}/mm/good.do',
		  type: 'post',
		  data: {idx:num},
		  success : function(result) {
			  if(result == 1) {
			  	  var good = $("#goodCnt"+num).html();
			   	  $("#goodCnt"+num).html(eval(good + '+1'));
			   	  $("input:checkbox[id='good"+num+"']").prop("checked", true);
			  } else if(result == 2) {
			  	  var good = $("#goodCnt"+num).html();
			   	  $("#goodCnt"+num).html(eval(good + '-1'));
			  } else if(result == 3) {
			  	  var good = $("#goodCnt"+num).html();
			  	  var bad = $("#badCnt"+num).html();
			   	  $("#goodCnt"+num).html(eval(good + '+1'));
			   	  $("#badCnt"+num).html(eval(bad + '-1'));
			   	  $("input:checkbox[id='good"+num+"']").prop("checked", true);
			  }
		  }
	});
}
function btnBad(num) {
	$("input:checkbox[id='good"+num+"']").prop("checked", false);
	$("input:checkbox[id='bad"+num+"']").prop("checked", false);
	$.ajax({
		  url: '${path}/mm/bad.do',
		  type: 'post',
		  data: {idx:num},
		  success : function(result) {
			  if(result == 1) {
			  	  var bad = $("#badCnt"+num).html();
			   	  $("#badCnt"+num).html(eval(bad + '+1'));
				  $("input:checkbox[id='bad"+num+"']").prop("checked", true);
			  } else if(result == 2) {
			  	  var bad = $("#badCnt"+num).html();
			   	  $("#badCnt"+num).html(eval(bad + '-1'));
			  } else if(result == 3) {
			  	  var good = $("#goodCnt"+num).html();
			  	  var bad = $("#badCnt"+num).html();
			   	  $("#goodCnt"+num).html(eval(good + '-1'));
			   	  $("#badCnt"+num).html(eval(bad + '+1'));
				  $("input:checkbox[id='bad"+num+"']").prop("checked", true);
			  }
			  if(result != 2) {

				}
		  }
	});
}

</script>
</head>
<body>
<table border="1" style="text-align: center; width: 1000px;">
	<tr>
		<td>
			<textarea placeholder="내용을 작성해주세요." name="content" id="content" style="width: 100%; height: 50px;"></textarea>
		</td>
		<td>
			<input type="button" value="등록" id="btnWrite">
		</td>
	</tr>
<c:choose>
	<c:when test="${fn:length(list) != 0}">
	<c:forEach items="${list}" var="list">
	<tr>
		<td align="left">
			${list.refWriter} | 
			<fmt:formatDate value="${list.postDate}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" /> | 
			좋아요 : <span id="goodCnt${list.idx}">${list.goodCnt}</span> | 싫어요 : <span id="badCnt${list.idx}">${list.badCnt}</span>
		</td>
		<td rowspan="2" id="btnArea${list.idx}" class="idx">
			<div align="left">
				<input type="checkbox" id="good${list.idx}" onclick="btnGood('${list.idx}')" <c:out value="${list.feel == '0' ? 'checked' : ''}" />>좋아요
				<input type="checkbox" id="bad${list.idx}" onclick="btnBad('${list.idx}')" <c:out value="${list.feel == '1' ? 'checked' : ''}" />>싫어요
			</div>
			<br>
			<div align="right">
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<input type="button" id="deleteAdmin('${list.idx}')" value="관리자삭제">
			</sec:authorize>
				<input type="button" value="신고" onclick="report('${list.idx}')">
			<sec:authentication property="principal.username" var="loginID" />
			<c:if test="${list.refWriter == loginID}">
				<input type="button" onclick="btnUpdateForm('${list.idx}', '${list.content}','${pager.curPage}')" value="수정">
				<input type="button" onclick="btnDelete('${list.idx}','${pager.curPage}')" value="삭제">
			</c:if>
			</div>
		</td>
	</tr>
	<tr>
		<td align="left" style="padding: 10px;" id="originContent${list.idx}">${list.content}</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="7">
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
	</c:when>
	<c:otherwise>
	<tr>
		<td colspan="7">게시글이 없습니다.</td>
	</tr>
	</c:otherwise>
</c:choose>
</table>
</body>
</html>
