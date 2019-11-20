<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/admin_menu.jsp" %>
<c:if test="${message == 'success'}">
<h2>
	${sessionScope.admin_name} (${sessionScope.admin_userid}) 님 환영합니다.
</h2>
</c:if>

</body>
</html>