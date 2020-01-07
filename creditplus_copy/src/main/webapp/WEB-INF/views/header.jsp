<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="${path}/resources/style.css">

<!-- Wijmo 레퍼런스 (필수) -->
<script src="https://cdn.grapecity.com/wijmo/5.20193.637/controls/wijmo.min.js"></script>
<link href="https://cdn.grapecity.com/wijmo/5.20193.637/styles/wijmo.min.css" rel="stylesheet"/>

<!-- Wijmo 컨트롤 (옵션, 필요한 컨트롤 만 추가) -->
<script src="https://cdn.grapecity.com/wijmo/5.20193.637/controls/wijmo.grid.min.js"></script>
<script src="https://cdn.grapecity.com/wijmo/5.20193.637/controls/wijmo.chart.min.js"></script>
<script src="https://cdn.grapecity.com/wijmo/5.20193.637/controls/wijmo.input.min.js"></script>
<script src="https://cdn.grapecity.com/wijmo/5.20193.637/controls/wijmo.gauge.min.js"></script>

<!-- Wijmo custom theme (옵션, 원하는 테마를 추가) -->
<link href="https://cdn.grapecity.com/wijmo/5.20193.637/styles/themes/wijmo.theme.modern.min.css" rel="stylesheet"/>

<!-- Wijmo custom culture (옵션, 원하는 문화권을 추가) -->
<script src="https://cdn.grapecity.com/wijmo/5.20193.637/controls/cultures/wijmo.culture.ja.min.js"></script>