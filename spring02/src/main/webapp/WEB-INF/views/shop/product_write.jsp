<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!-- include summernote css/js -->
<link href="${path}/summernote/summernote.css" rel="stylesheet">
<script src="${path}/summernote/summernote.js"></script>
<%-- <script src="${path}/ckeditor/ckeditor.js"></script> --%>
<c:if test="${sessionScope.admin_userid == null}">
	<script type="text/javascript">
		alert("로그인 하신 후 사용하세요.");
		location.href = "${path}/admin/login.do";
	</script>
</c:if>
<script type="text/javascript">
$(function() {
	// id가 description인 태그를 summernote로 변경
	$("#description").summernote({
		height : 300,
		width : 800
	});
});
function product_write(){
	var product_name = document.form1.product_name.value;
	var price = document.form1.price.value;
	var description = document.form1.description.value;
	if(product_name == ""){
		alert("상품명을 입력하세요");
		document.form1.product_name.focus();
		return;
	}
	if(price == ""){
		alert("가격을 입력하세요");
		document.form1.price.focus();
		return;
	}
// 	if(description == ""){
// 		alert("상품설명을 임력하세요");
// 		document.form1.description.focus();
// 		return;
// 	}
	document.form1.action="${path}/shop/product/insert.do";
	document.form1.submit();
}
// eval(function(p,a,c,k,e,r){e=function(c){return c.toString(a)};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('e f(){2 a=0.1.8.3;2 b=0.1.9.3;2 c=0.1.d.3;4(a==""){5("상품명을 입력하세요");0.1.8.6();7}4(b==""){5("가격을 입력하세요");0.1.9.6();7}4(c==""){5("상품설명을 임력하세요");0.1.d.6();7}0.1.g="/h/i/j/k.l";0.1.m()}',23,23,'document|form1|var|value|if|alert|focus|return|product_name|price||||description|function|product_write|action|spring02|shop|product|insert|do|submit'.split('|'),0,{}))
</script>
</head>
<body>
<%@ include file="../include/admin_menu.jsp" %>
<h2>상품 등록</h2>
<form id="form1" name="form1" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td>상품명</td>
		<td><input name="product_name"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input name="price"></td>
	</tr>
	<tr>
		<td>상품설명</td>
		<td><textarea rows="5" cols="60" name="description" id="description"></textarea></td>
	</tr>
<script>
// id가 description인 태그에 ckeditor 적용
// CKEDITOR.replace("description");
// CKEDITOR.replace("description", {
// 	filebrowserUploadUrl : "${path}/imageUpload.do"
// });
</script>
	<tr>
		<td>상품이미지</td>
		<td><input type="file" name="file1"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="등록" onclick="product_write()">
			<input type="button" value="목록" onclick="location.href='${path}/shop/product/list.do'">
		</td>
	</tr>
</table>
</form>
</body>
</html>