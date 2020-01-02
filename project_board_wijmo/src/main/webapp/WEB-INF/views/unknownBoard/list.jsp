<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>질문 게시판</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
$(function() {
	noticeBoard("1");
	$("#btnWrite").click(function() {
		location.href = "${path}/ub/writeForm.do?refIdx=${refIdx}";
	});
	$("#btnSearch").click(function() {
		$("#searchForm").attr("action", "${path}/ub/list.do");
		$("#searchForm").submit();
	});
});
function aTitle(idx) {
	location.href = "${path}/ub/view.do?refIdx=${refIdx}&idx=" + idx + "&search_option=${search_option}&keyword=${keyword}";
}
function aFirst() {
	location.href = "${path}/ub/list.do?refIdx=${refIdx}&curPage=1&search_option=${search_option}&keyword=${keyword}";
}
function aPrev() {
	location.href = "${path}/ub/list.do?refIdx=${refIdx}&curPage=${pager.prevPage}&search_option=${search_option}&keyword=${keyword}";
}
function aNow(cnt) {
	location.href = "${path}/ub/list.do?refIdx=${refIdx}&curPage=" + cnt + "&search_option=${search_option}&keyword=${keyword}";	
}
function aPost() {
	location.href = "${path}/ub/list.do?refIdx=${refIdx}&curPage=${pager.nextPage}&search_option=${search_option}&keyword=${keyword}";
}
function aLast() {
	location.href = "${path}/ub/list.do?refIdx=${refIdx}&curPage=${pager.totPage}&search_option=${search_option}&keyword=${keyword}";
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
<sec:authorize
	access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_BLACK')">
	<%@ include file="../include/menuUser.jsp"%>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
	<%@ include file="../include/menuAdmin.jsp"%>
</sec:authorize>
<table border="1" style="text-align: center; width: 1000px;">
	<tr>
		<th>번호</th>
		<th style="width: 400px;">제목</th>
		<th>작성자</th>
		<th>등록날짜</th>
		<th>조회수</th>
		<th>좋아요</th>
		<th>싫어요</th>
	</tr>
	<tr>
		<td colspan="7" id="noticeBoard">&nbsp;</td>
	</tr>
<c:choose>
	<c:when test="${fn:length(list) != 0}">
	<c:forEach items="${list}" var="list">
	<tr>
		<td>${list.rn}</td>
		<td>
			<a onclick="aTitle('${list.idx}')" href="#">${list.title}</a>
			(<c:out value="${list.replyCnt}" />)
		</td>
		<td>익명작성자</td>
		<td>
		<jsp:useBean id="now" class="java.util.Date" />
		<fmt:formatDate value="${now}" pattern="yyyyMMdd" var="today" />
		<fmt:formatDate value="${list.postDate}" pattern="yyyyMMdd" var="date"/>
		<c:if test="${today != date}">
		<fmt:formatDate value="${list.postDate}" pattern="yyyy년 MM월 dd일" />
		</c:if>
		<c:if test="${today == date}">
		<fmt:formatDate value="${list.postDate}" pattern="hh시 mm분" />
		</c:if>
		</td>
		<td>${list.viewCnt}</td>
		<td>${list.goodCnt}</td>
		<td>${list.badCnt}</td>
	</tr>
	</c:forEach>
	</c:when>
	<c:otherwise>
	<tr>
		<td colspan="7">게시글이 없습니다.</td>
	</tr>
	</c:otherwise>
</c:choose>
	<tr>
		<td colspan="4">
			<form id="searchForm">
				<select name="search_option" style="width: 100px; height: 30px;">
					<option value="all" <c:out value="${search_option=='all'?'selected':''}" />>전체</option>
					<option value="title" <c:out value="${search_option=='title'?'selected':''}" />>제목</option>
					<option value="content" <c:out value="${search_option=='content'?'selected':''}" />>내용</option>
				</select>
				<input name="keyword" type="text" value="${keyword}" style="width: 300px; height: 30px;">
				<input type="hidden" name="refIdx" value="${refIdx}">
				<button id="btnSearch">검색</button>
			</form>
		</td>
		<td colspan="3">
			<input type="button" value="등록" id="btnWrite">
		</td>
	</tr>
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
</table>
</body>
</html>
