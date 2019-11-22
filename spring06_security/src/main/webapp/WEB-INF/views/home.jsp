<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<%@ include file="include/header.jsp" %>
	<title>Home</title>
</head>
<body>
<h2>Welcome!</h2>
<h2>${msg}</h2>
<a href="${path}/admin/main.do">관리자 페이지</a>
<a href="${path}/user/logout.do">로그아웃</a>
</body>
</html>
