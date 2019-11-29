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
		location.href = "${path}/suggestion/writeForm.do";
	});
});
</script>
</head>
<body>
	<%@ include file="../include/menu.jsp"%>
<table border="1" style="text-align: center;">
	<tr>
		<td colspan="4">
			<form action="${path}/suggestion/list.do" method="get">
				<select name="search_option" style="width: 100px; height: 30px;">
					<option value="all" <c:out value="${search_option=='all'?'selected':''}" />>전체</option>
					<option value="sg_title" <c:out value="${search_option=='sg_title'?'selected':''}" />>제목</option>
					<option value="sg_content" <c:out value="${search_option=='sg_content'?'selected':''}" />>내용</option>
					<option value="sg_writer" <c:out value="${search_option=='sg_writer'?'selected':''}" />>작성자</option>
				</select>
				<input name="keyword" type="text" value="${keyword}" style="width: 300px; height: 30px;">
				<button type="submit">검색</button>
			</form>
		</td>
		<td colspan="2">
			<input type="button" value="글등록" id="btnWrite">
		</td>
	</tr>
	<tr>
		<th>번호</th>
		<th style="width: 300px;">제목</th>
		<th>등록날짜</th>
		<th>조회수</th>
		<th>좋아요</th>
		<th>싫어요</th>
	</tr>
<c:choose>
	<c:when test="${fn:length(list) != 0}">
	<c:forEach items="${list}" var="list">
	<tr>
		<td>${list.sg_idx}</td>
		<td><a href="${path}/suggestion/view.do?sg_idx=${list.sg_idx}">${list.sg_title}</a></td>
		<td><fmt:formatDate value="${list.sg_postDate}" pattern="yyyy년 MM월 dd일 hh시 MM분 ss초" /></td>
		<td>${list.sg_view}</td>
		<td>${list.sg_good}</td>
		<td>${list.sg_bad}</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="6">
			<c:if test="${pager.curBlock != 1}">
				<a href="${path}/suggestion/list.do?curPage=1&search_option=${search_option}&keyword=${keyword}">[처음]</a>
			</c:if>
			<c:if test="${pager.curBlock != 1}">
				<a href="${path}/suggestion/list.do?curPage=${pager.prevPage}&search_option=${search_option}&keyword=${keyword}">[이전]</a>
			</c:if>
			<c:forEach var="cnt" begin="${pager.blockBegin}" end="${pager.blockEnd}">
				<c:choose>
					<c:when test="${cnt == pager.curPage}">
						<font color="red">${cnt}</font>
					</c:when>
					<c:otherwise>
						<a href="${path}/suggestion/list.do?curPage=${cnt}&search_option=${search_option}&keyword=${keyword}">${cnt}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pager.curBlock < pager.totBlock}">
				<a href="${path}/suggestion/list.do?curPage=${pager.nextPage}&search_option=${search_option}&keyword=${keyword}">[다음]</a>
			</c:if>
			<c:if test="${pager.curBlock < pager.totBlock}">
				<a href="${path}/suggestion/list.do?curPage=${pager.totPage}&search_option=${search_option}&keyword=${keyword}">[끝]</a>
			</c:if>
		</td>
	</tr>
	</c:when>
	<c:otherwise>
	<tr>
		<td colspan="6">게시글이 없습니다.</td>
	</tr>
	</c:otherwise>
</c:choose>
</table>
</body>
</html>
