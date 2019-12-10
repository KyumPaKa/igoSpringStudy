<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
$(function(){
	$(".back").click(function(){
		alert("일반 회원으로 복구하시겠습니까?");
		var refuserid = $(this).parent().siblings().eq(1).text();
		var value={"refuserid":refuserid};
		console.log(value);
		$.ajax({
			url:"${path}/admin/clearblacklist.do",
			data:value,
			dataType:"text",
			type:"post",
			success:function(data){
				console.log("성공");
				$("#"+refuserid).remove();
				}
			});
	});
})
</script>
</head>
<body>
<%@ include file="../include/menuAdmin.jsp" %>
	<h2>블랙리스트 목록</h2>

	<!-- 검색폼 -->
	<form name="form1" method="post" action="${path}/admin/blacklist.do">
		<select name="search_option">
			<option value="refuserid"
				<c:if test="${map.search_option == 'refuserid'}">selected</c:if>>아이디</option>
			<option value="u.email"
				<c:if test="${map.search_option == 'email'}">selected</c:if>>이메일</option>
			<option value="all"
				<c:if test="${map.search_option == 'all'}">selected</c:if>>전체</option>
		</select> <input name="keyword" value="${map.keyword}"> <input
			type="submit" value="조회">
	</form>

	${map.count}개의 게시물이 있습니다.
	<table border="1" width="1000px">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>사유</th>
			<th>등록 날짜</th>
			<th>이름</th>
			<th>이메일</th>
			<th>회원으로 복구</th>
		</tr>
		<c:forEach var="row" items="${map.list}">
		<tr id="${row.refuserid}">
		<td>${row.idx}</td>
		<td>${row.refuserid}</td>
		<td>${row.reason}</td>
		<td>
		<fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
		<td>${row.name}</td>
		<td>${row.email}</td>
		<td><button type="button" class="back">복구</button></td>
		</tr>
		</c:forEach>
		
		<!-- 페이지 네비게이션 -->
	<tr>
		<td colspan="7" align="center">
			<c:if test="${map.pager.curBlock > 1 }">
				<a href="javascript:list('1')">[처음]</a>
			</c:if>
			<c:if test="${map.pager.curBlock > 1 }">
				<a href="javascript:list('${map.pager.prevPage}')">[이전]</a>
			</c:if>
			<c:forEach var="num" begin="${map.pager.blockBegin}" end="${map.pager.blockEnd}">
			<c:choose>
				<c:when test="${num == map.pager.curPage }">
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
			<c:if test="${map.pager.curPage <= map.pager.totPage}">
				<a href="javascript:list('${map.pager.totPage}')">[끝]</a>
			</c:if>
		</td>
	</tr>
		
	</table>
</body>
</html>



















