<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>질문 게시판</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
</script>
</head>
<body >
	<table border="1" style="text-align: center; width: 1000px;">
		<c:forEach items="${list}" var="list">
		<tr>
			<td style="width: 50px;">&nbsp;</td>
			<td align="left" style="width: 400px;"><a href="${path}/notice/view.do?idx=${list.idx}">[공지사항] ${list.title}</a></td>
			<td style="width: 80px;">${list.refwriter}</td>
			<td>
				<jsp:useBean id="now" class="java.util.Date" />
				<fmt:formatDate value="${now}" pattern="yyyyMMdd" var="today" />
				<fmt:formatDate value="${list.postdate}" pattern="yyyyMMdd" var="date"/>
				<c:if test="${today != date}">
				<fmt:formatDate value="${list.postdate}" pattern="yyyy년 MM월 dd일" />
				</c:if>
				<c:if test="${today == date}">
				<fmt:formatDate value="${list.postdate}" pattern="hh시 MM분" />
				</c:if>
			</td>
			<td style="width: 78px;">${list.viewcnt}</td>
			<td style="width: 79px;">&nbsp;</td>
			<td style="width: 79px;">&nbsp;</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
