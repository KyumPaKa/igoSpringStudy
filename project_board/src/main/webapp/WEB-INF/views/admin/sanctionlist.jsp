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
	$(".delete").click(function(){
	alert("신고 내역에서 삭제하시겠습니까?");
		var refuserid = $(this).parent().siblings().eq(0).text();
		var value={"refuserid":refuserid};
		console.log(value);
		$.ajax({
			url:"${path}/admin/clearsanction.do",
			data:value,
			dataType:"text",
			type:"post",
			success:function(data){
				console.log("성공");
				$("#"+refuserid).remove();
				}
			});
	});

});
</script>
<script>
$(function(){
	$(".update").click(function(){
	alert("등록하시겠습니까?");
	var status =  $(this).parent().siblings().eq(5).html();
	console.log(status);
	if(status!="처리완료"){
		var refuserid = $(this).parent().siblings().eq(0).text();
		var category = $(this).parent().siblings().eq(3).children().eq(0).val();
		var refboardcategory = $(this).parent().siblings().eq(3).children().eq(1).val();
		var refidx = $(this).parent().siblings().eq(3).children().eq(2).val();
		var value={"refuserid":refuserid,"category":category,"refboardcategory":refboardcategory,"refidx":refidx};
	 	 $.ajax({
			url:"${path}/admin/updateblacklist.do",
			data:value,
			type:"post",
			dataType:"text",
			success:function(data){
			$("#"+status).html("처리완료");
				}
			});  
	}else{
alert("이미 처리하셨습니다.");
		} 
		}); 
	
})
</script>
</head>
<body>
<%@ include file="../include/menuAdmin.jsp" %>

	<h2>신고내역 관리</h2>

	<!-- 검색폼 -->
	<form name="form1" method="post" action="${path}/admin/sanction.do">
		<select name="search_option">
			<option value="refuserid"
				<c:if test="${map.search_option == 'refuserid'}">selected</c:if>>아이디</option>
			<option value="email"
				<c:if test="${map.search_option == 'email'}">selected</c:if>>이메일</option>
			<option value="all"
				<c:if test="${map.search_option == 'all'}">selected</c:if>>전체</option>
		</select> <input name="keyword" value="${map.keyword}"> <input
			type="submit" value="조회">
	</form>

	${map.count}개의 게시물이 있습니다.
	<table border="1" width="1000px">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>사유</th>
			<th>신고횟수</th>
			<th>처리상태</th>
			<th>블랙리스트</th>
			<th>내역삭제</th>
		</tr>
		
		<c:forEach var="row" items="${map.list}">
		<tr id="${row.refuserid}">
		<td>${row.refuserid}</td>
		<td>${row.name}</td>
		<td>${row.email}</td>
		<td>
		<input type="hidden" value="${row.category}">
<input type="hidden" value="${row.refboardcategory}">
		<input type="hidden" value="${row.refidx}">
		<c:if test="${row.refboardcategory==1}">
				<a href="${path}/mm/list.do?refIdx=${row.refboardcategory}&idx=${row.refidx}&search_option=${search_option}&keyword=${keyword}&category=${row.category}">${row.category}</a>
		</c:if>
		<c:if test="${row.refboardcategory==2}">
				<a href="${path}/fb/view.do?refIdx=${row.refboardcategory}&idx=${row.refidx}&search_option=${search_option}&keyword=${keyword}&category=${row.category}">${row.category}</a>
		</c:if>
		<c:if test="${row.refboardcategory==3}">
				<a href="${path}/ub/view.do?refIdx=${row.refboardcategory}&idx=${row.refidx}&search_option=${search_option}&keyword=${keyword}&category=${row.category}">${row.category}</a>
		</c:if>
		</td>
		<td>${row.count}</td>
		<td id="${row.status}">${row.status}</td>
		<td><button type="button" class="update">등록</button></td>
		<td><button type="button" class="delete">삭제</button></td>
		
		</tr>
		</c:forEach>
		
		<!-- 페이지 네비게이션 -->
	<tr>
		<td colspan="8" align="center">
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



















