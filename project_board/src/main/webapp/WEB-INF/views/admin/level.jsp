<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script>
	$(function() {
		$(".change").click(function() {
			//var value=$(this).parent().siblings().val();
			var refuserid = $(this).parent().siblings().eq(0).text();
			var authority =$(this).siblings().eq(0).val();
			var value = {
				"refuserid" : refuserid,"authority":authority
			};
			console.log(value);
			$.ajax({
				url : "${path}/admin/changelevel.do",
				data : value,
				dataType : "text",
				type : "post",
				success : function(data) {
					changeresult("1");
				}
			});
			function changeresult(num){
				$.ajax({
					type:"post",
					url:"${path}/admin/level.do",
					success:function(result){
						console.log(result);
						}
					});
				}
		});
	})
$(function() {
    $(".change").click(function() {
       //var value=$(this).parent().siblings().val();
       var refuserid = $(this).parent().siblings().eq(0).text();
       var authority =$(this).siblings().eq(0).val();
       var value = {
          "refuserid" : refuserid,"authority":authority
       };
       $.ajax({
          url : "${path}/admin/changelevel.do",
          data : value,
          dataType : "text",
          type : "post",
          success : function(data) {
             $("#"+refuserid).html(authority);
          }
       });
    });
 });















</script>
</head>
<body>
	<%@ include file="../include/menuAdmin.jsp"%>
	<h2>등급관리</h2>

	<!-- 검색폼 -->
	<form name="form1" method="post" action="${path}/admin/level.do">
		<select name="search_option">
			<option value="userid"
				<c:if test="${map.search_option == 'userid'}">selected</c:if>>아이디</option>
			<option value="email"
				<c:if test="${map.search_option == 'email'}">selected</c:if>>이메일</option>
			<option value="all"
				<c:if test="${map.search_option == 'all'}">selected</c:if>>전체</option>
		</select> <input name="keyword" value="${map.keyword}"> <input
			type="submit" value="조회">
	</form>

	${map.count}개의 게시물이 있습니다.
	<table border="1" width="1000px" align="center">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>활동 내역</th>
			<th>등급</th>
			<th>가입일</th>
			<th>등업</th>
		</tr>
		<c:forEach var="row" items="${map.list}">
			<tr>
				<td>${row.userid}</td>
				<td>${row.name}</td>
				<td>${row.email}</td>
				<td>게시글 수 : ${row.board} 댓글 수 : ${row.rply}</td>
				<td id="${row.userid}">${row.authority}</td>
				<td><fmt:formatDate value="${row.joinDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><select name="authority" id="authority">
						<option value="ROLE_BABY">준회원</option>
						<option value="ROLE_MEMBER">정회원</option>
						<option value="ROLE_MANAGER">매니저</option>
				</select>
					<button type="button" class="change">변경하기</button></td>
				
			</tr>
		</c:forEach>

		<!-- 페이지 네비게이션 -->
		<tr>
			<td colspan="8" align="center"><c:if
					test="${map.pager.curBlock > 1 }">
					<a href="javascript:list('1')">[처음]</a>
				</c:if> <c:if test="${map.pager.curBlock > 1 }">
					<a href="javascript:list('${map.pager.prevPage}')">[이전]</a>
				</c:if> <c:forEach var="num" begin="${map.pager.blockBegin}"
					end="${map.pager.blockEnd}">
					<c:choose>
						<c:when test="${num == map.pager.curPage }">
							<span style="color: red;">${num}</span>&nbsp;
				</c:when>
						<c:otherwise>
							<a href="javascript:list('${num}')">${num}</a>&nbsp;
				</c:otherwise>
					</c:choose>
				</c:forEach> <c:if test="${map.pager.curBlock <= map.pager.totBlock}">
					<a href="javascript:list('${map.pager.nextPage}')">[다음]</a>
				</c:if> <c:if test="${map.pager.curPage <= map.pager.totPage}">
					<a href="javascript:list('${map.pager.totPage}')">[끝]</a>
				</c:if></td>
		</tr>

	</table>
</body>
</html>



















