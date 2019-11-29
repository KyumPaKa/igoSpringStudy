<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <a href="${path}//">Home</a> |
    <a href="${path}//">출석체크</a> |
    <a href="${path}/suggestion/list.do">QnA</a> |
    <a href="${path}//">건의사항</a> |
    
    
    <div style="text-align:right;">
    <c:choose>
    	<c:when test="${sessionScope.userid == null}">
    		<a href="${path}/member/login.do">로그인</a>
    		
    		<a href="${path}/admin/login.do">관리자 로그인</a>
    	</c:when>
    <c:otherwise>
    	${sessionScope.name}님이 로그인 중입니다.
    	<a href="${path}/member/logout.do">로그아웃</a>
    </c:otherwise>
    </c:choose>
    </div>
   
    <hr>
