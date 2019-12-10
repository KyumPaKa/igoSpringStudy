<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<%@ include file="include/header.jsp"%>
</head>
<body>
	<sec:authorize access="isAnonymous()">
		<%@ include file="include/menuUser.jsp"%>
	</sec:authorize>
	
	<sec:authorize
		access="hasAnyRole('ROLE_BABY','ROLE_MEMBER','ROLE_BLACK')">
		<%@ include file="include/menuUser.jsp"%>
	</sec:authorize>
	
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
		<%@ include file="include/menuAdmin.jsp"%>
	</sec:authorize>

	<h1>게시판 홈페이지</h1>

	<h1>${msg}</h1>
	<h2>환영합니다! 방문해주셔서 감사합니다.</h2>

	<P>지금 현재 시간은  ${serverTime}입니다.</P>
</body>
</html>
