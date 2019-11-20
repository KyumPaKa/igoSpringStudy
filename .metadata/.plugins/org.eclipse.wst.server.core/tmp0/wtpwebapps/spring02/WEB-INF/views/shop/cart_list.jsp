<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
$(function(){
	$("#btnList").click(function(){
		location.href="${path}/shop/product/list.do";
	});
	$("#btnDelete").click(function(){
		if(confirm("장바구니를 비우시겠습니까?")){
			location.href="${path}/shop/shop/deleteAll.do";
		}
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>장바구니</h2>
<c:choose>
	<c:when test="${map.count == 0}">
	장바구니가 비었습니다.
	</c:when>
	<c:otherwise>
		<form id="form1" name="form1" method="post" action="${path}/shop/cart/update.do">
			<table border="1" width="400px;">
				<tr>
					<td>상품명</td>
					<td>단가</td>
					<td>수량</td>
					<td>금액</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach var="row" items="${map.list}">
				<tr align="center">
					<td>${row.product_name}</td>
					<td><fmt:formatNumber value="${row.price}" pattern="#,###,###"/></td>
					<td>
					<input type="number" style="width: 30px;" name="amount" value="<fmt:formatNumber value="${row.amount}" pattern="#,###,###" />">
					<input type="hidden" name="cart_id" value="${row.cart_id}">
					</td>
					<td><fmt:formatNumber value="${row.money}" pattern="#,###,###"/></td>
					<td><a href="${path}/shop/cart/delete.do?cart_id=${row.cart_id}">[삭제]</a></td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="right">
					장바구니 금액 합계 : 
					<fmt:formatNumber value="${map.sumMoney}" pattern="#,###,###" /><br>
					배송료 : ${map.fee}<br>
					총합계: <fmt:formatNumber value="${map.sum}" pattern="#,###,###" />
					</td>
				</tr>
			</table>
			<button id="btnUpdate">수정</button>
			<button type="button" id="btnDelete">장바구니 비우기</button>
		</form>
	</c:otherwise>
</c:choose>
</body>
</html>