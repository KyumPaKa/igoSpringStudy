<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
$(function(){
	$("#btnWrite").click(function(){
		location.href = "${path}/board/write.do";
	});
});
function list(page) {
	location.href = "${path}/board/list.do?curPage=" + page
			+ "&search_option=${map.search_option}"
			+ "&keyword=${map.keyword}";
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>게시판</h2>
<!-- 검색폼 -->
<form name="form1" method="post" action="${path}/board/list.do">
	<select name="search_option">
		<c:choose>
			<c:when test="${map.search_option == 'all'}">
				<option value="all" selected>이름+내용+제목</option>
				<option value="writer">이름</option>
				<option value="content">내용</option>
				<option value="title">제목</option>
			</c:when>
			<c:when test="${map.search_option == 'writer'}">
				<option value="all">이름+내용+제목</option>
				<option value="writer" selected>이름</option>
				<option value="content">내용</option>
				<option value="title">제목</option>
			</c:when>
			<c:when test="${map.search_option == 'content'}">
				<option value="all">이름+내용+제목</option>
				<option value="writer">이름</option>
				<option value="content" selected>내용</option>
				<option value="title">제목</option>
			</c:when>
			<c:when test="${map.search_option == 'title'}">
				<option value="all">이름+내용+제목</option>
				<option value="writer">이름</option>
				<option value="content">내용</option>
				<option value="title" selected>제목</option>
			</c:when>
		</c:choose>
<%-- 		<option value="all" <c:out value="${map.search_option=='all'?'selected':''}" /> >이름+내용+제목</option> --%>
<%-- 		<option value="all" <c:out value="${map.search_option=='name'?'selected':''}" /> >이름</option> --%>
<%-- 		<option value="all" <c:out value="${map.search_option=='content'?'selected':''}" /> >내용</option> --%>
<%-- 		<option value="all" <c:out value="${map.search_option=='title'?'selected':''}" /> >제목</option> --%>
	</select>
	<input name="keyword" value="${map.keyword}">
	<input type="submit" value="조회">
</form>
<button type="button" id="btnWrite">글쓰기</button>
${map.count}개의 게시물이 있습니다.
<table border="1" width="600px">
	<tr>
		<th>번호${map.keyword}</th>
		<th>제목</th>
		<th>이름</th>
		<th>날짜</th>
		<th>조회수</th>
	</tr>
	<c:forEach var="row" items="${map.list}">
	<tr>
		<td>${row.bno}</td>
		<td>
			<a href="${path}/board/view.do?bno=${row.bno}&curPage=${map.pager.curPage}&search_option=${map.search_option}&keyword=${map.keyword}">${row.title}
			<c:if test="${row.cnt > 0}">
				<span style="color: red;">( ${row.cnt} )</span>
			</c:if>
		 	</a>
		</td>
		<td>${row.name}</td>
		<td><fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>${row.viewcnt}</td>
	</tr>
	</c:forEach>
<!-- 페이지 네비게이션 -->
	<tr>
		<td colspan="5" align="center">
			<c:if test="${map.pager.curBlock > 1}">
				<a href="javascript:list('1')">[처음]</a>
			</c:if>
			<c:if test="${map.pager.curBlock > 1}">
				<a href="javascript:list('${map.pager.prevPage}')">[이전]</a>
			</c:if>
			<c:forEach var="num" begin="${map.pager.blockBegin}" end="${map.pager.blockEnd}">
				<c:choose>
					<c:when test="${num == map.pager.curPage}">
						<span style="color:red;">${num}</span>&nbsp;
					</c:when>
					<c:otherwise>
						<a href="javascript:list('${num}')">${num}</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${map.pager.curBlock <= map.pager.totBlock}">
				<a href="javascript:list('${map.pager.nextPage}')">[다음]</a>
			</c:if>
			<c:if test="${map.pager.curPage < map.pager.totPage}">
				<a href="javascript:list('${map.pager.totPage}')">[끝]</a>
			</c:if>
		</td>
	</tr>
</table>
</body>
</html>