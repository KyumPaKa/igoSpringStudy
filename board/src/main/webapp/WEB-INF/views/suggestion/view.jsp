<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>질문 게시판</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
$(function() {
	$("#btnList").click(function() {
		location.href = "${path}/suggestion/list.do";
	});
	$("#btnUpdate").click(function() {
		location.href = "${path}/suggestion/updateForm.do?sg_idx=${suggestionDTO.sg_idx}";
	});
	$("#btnDelete").click(function() {
		if(confirm("삭제하시겠습니까?")) {
			location.href = "${path}/suggestion/delete.do?sg_idx=${suggestionDTO.sg_idx}";
		}
	});
	$("#btnGood").click(function() {
		$.ajax({
			  url: '${path}/suggestion/increaseGood.do',
			  type: 'post',
			  data: {sg_idx:${suggestionDTO.sg_idx}},
			  success : function(result) {
				  if(result == 1) {
				  	  var good = $("#sg_good").html();
				   	  $("#sg_good").html(eval(good + '+1'));
					  alert("좋아요!);
				  } else if(result == 2) {
				  	  var good = $("#sg_good").html();
				   	  $("#sg_good").html(eval(good + '-1'));
					  alert("좋아요 취소!")
				  } else if(result == 3) {
				  	  var good = $("#sg_good").html();
				  	  var bad = $("#sg_bad").html();
				   	  $("#sg_good").html(eval(good + '+1'));
				   	  $("#sg_bad").html(eval(bad + '-1'));
					  alert("좋아요로 변경!");
				  }
			  }
		});
	});
	$("#btnBad").click(function() {
		$.ajax({
			  url: '${path}/suggestion/increaseBad.do',
			  type: 'post',
			  data: {sg_idx:${suggestionDTO.sg_idx}},
			  success : function() {
				  if(result == 1) {
				  	  var bad = $("#sg_bad").html();
				   	  $("#sg_bad").html(eval(bad + '+1'));
					  alert("싫어요!);
				  } else if(result == 2) {
				  	  var bad = $("#sg_bad").html();
				   	  $("#sg_bad").html(eval(bad + '-1'));
					  alert("싫어요 취소!")
				  } else if(result == 3) {
				  	  var good = $("#sg_good").html();
				  	  var bad = $("#sg_bad").html();
				   	  $("#sg_good").html(eval(good + '-1'));
				   	  $("#sg_bad").html(eval(bad + '+1'));
					  alert("싫어요로 변경!");
				  }
			  }
		});
	});
});
</script>
</head>
<body>
	<%@ include file="../include/menu.jsp"%>
<form action="">
<table style="text-align: center;">
	<tr>
		<th rowspan="2">${suggestionDTO.sg_idx}</th>
		<th rowspan="2" style="width: 500px;">${suggestionDTO.sg_title}</th>
		<th colspan="4" style="font-size: 15px;"><fmt:formatDate value="${suggestionDTO.sg_postDate}" pattern="yyyy년 MM월 dd일 hh시 MM분 ss초" /></th>
	</tr>
	<tr>
		<th style="font-size: 15px;">조회수 : ${suggestionDTO.sg_view}</th>
		<th style="font-size: 15px;">좋아요 : <span id="sg_good">${suggestionDTO.sg_good}</span></th>
		<th style="font-size: 15px;">싫어요 : <span id="sg_bad">${suggestionDTO.sg_bad}</span></th>
	</tr>
	<tr>
		<td style="height: 300px;">내용</td>
		<td colspan="5">${suggestionDTO.sg_content}</td>
	</tr>
	<tr>
		<td style="height: 100px;">업로드된 파일</td>
		<td colspan="5">
			<c:forTokens items="${suggestionDTO.sg_file}" delims="." var="type" varStatus="status">
				<c:if test="${status.last}">
					<c:choose>
						<c:when test="${type eq 'jpg' || type eq 'png' || type eq 'gif'}">
							<img style="width: 100px; height: 100px;" src="${path}/resources/suggestion/images/<fmt:formatDate value="${suggestionDTO.sg_postDate}" pattern="yyyy/MM/dd/" />${suggestionDTO.sg_file}">
						</c:when>
						<c:otherwise>
						<c:set var="length" value="${fn:length(suggestionDTO.sg_file)}" />
						${fn:substring(suggestionDTO.sg_file, 37, length)}
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:forTokens>
		</td>
	</tr>
	<tr>
		<td colspan="6">
			<input type="button" id="btnGood" value="좋아요">
			<input type="button" id="btnBad" value="싫어요">
		</td>
	</tr>
	<tr>
		<td colspan="6">
			<input type="button" id="btnList" value="목록">
			<input type="button" id="btnUpdate" value="수정">
			<input type="button" id="btnDelete" value="삭제">
		</td>
	</tr>
</table>
</form>
</body>
</html>
