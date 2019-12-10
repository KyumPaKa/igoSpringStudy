<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- jstl core tag -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="path" value="${pageContext.request.contextPath}" />

<style>
#nav_menu_right ul {
	list-style-type: none;
	/* 좌측 여백 없애기 */
	padding-left: 0px;
	/* 우측 정렬 하기 */
	text-align: right;
	margin-top: 45px;
}

#nav_menu_right ul li {
	display: inline;
	border-left: 1px solid #c0c0c0;
	/* 테두리와 메뉴 간격 벌리기. padding: 위 오른쪽 아래 왼쪽; */
	padding: 0px 10px 0px 10px;
	/* 메뉴와 테두리 사이 간격 벌리기. margin: 위 오른쪽 아래 왼쪽; */
	margin: 5px 0px 5px 0px;
}

#nav_menu_right ul li:first-child {
	border-left: none;
}

/* 로그인 회원가입 -------------------------------*/
#nav_menu ul {
	list-style-type: none;
	/* 좌측 여백 없애기 */
	padding-left: 0px;
	/* 우측 정렬 하기 */
	text-align: left;
}

/* 게시판 목록 -------------------------------*/
#nav_menu_vt ul {
	list-style-type: none;
	/* 좌측 여백 없애기 */
	padding-left: 0px;
	/* 우측 정렬 하기 */
	text-align: left;
}

#nav_menu_vt ul li {
	/*        border-left: 1px solid #c0c0c0;*/
	/* 테두리와 메뉴 간격 벌리기. padding: 위 오른쪽 아래 왼쪽; */
	padding: 0px 10px 0px 10px;
	/* 메뉴와 테두리 사이 간격 벌리기. margin: 위 오른쪽 아래 왼쪽; */
	margin: 5px 0px 5px 0px;
}

#nav_menu ul li:first-child {
	border-left: none;
}

vertical_line {
	border-left: 1px solid;
	height: 500px;
	position: absolute;
	left: 15%;
	margin-left: 60px;
	top: 83px;
}
</style>

<div id="nav_menu_right">
	<ul>
		<sec:authorize access="isAnonymous()">
		<li>
		<a href="${path}/user/login.do">로그인</a>
		</li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		<li>
			<a href="${path}/user/logout.do">Logout</a>
		</li>
		<li>
			<a href="${path}/user/mypage.do?userid=
			<sec:authentication property="principal.username" />">My page</a>
		</li>
		</sec:authorize>
	</ul>
</div>

<hr />

	<div id="nav_menu_vt">
    		
			<script type="text/javascript">

				
			</script>
    		
	        <ul>
	            
	            <li>
	                <a href="${path}/mm/list.do?refIdx=1">한줄게시판</a>
	            </li>
	            
	            <li>
	                <a href="${path}/fb/list.do?refIdx=2">자유게시판</a>
	            </li>
	            
	            <li>
	                <a href="${path}/ub/list.do?refIdx=3">익명게시판</a>
	            </li>
	            
	            <li>
	                <a href="${path}/qa/list.do?refIdx=4">건의사항</a>
	            </li>
	        </ul>
		
	</div>

<vertical_line />