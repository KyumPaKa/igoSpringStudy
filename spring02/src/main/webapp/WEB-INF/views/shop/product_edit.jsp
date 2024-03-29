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
}
function product_update(){
	product_write();
	document.form1.action="${path}/shop/product/update.do";
	document.form1.submit();
}
function product_delete(){
	product_write();
	if(confirm("삭제하시겠습니까?")){
		document.form1.action="${path}/shop/product/delete.do";
		document.form1.submit();
	}
}
// eval(function(p,a,c,k,e,r){e=function(c){return c.toString(a)};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('3 4(){5 a=0.1.d.6;5 b=0.1.e.6;5 c=0.1.f.6;2(a==""){7("상품명을 입력하세요");0.1.d.8();9}2(b==""){7("가격을 입력하세요");0.1.e.8();9}2(c==""){7("상품설명을 임력하세요");0.1.f.8();9}}3 m(){4();0.1.g="/h/i/j/n.k";0.1.l()}3 o(){4();2(p("삭제하시겠습니까?")){0.1.g="/h/i/j/q.k";0.1.l()}}',27,27,'document|form1|if|function|product_write|var|value|alert|focus|return||||product_name|price|description|action|spring02|shop|product|do|submit|product_update|update|product_delete|confirm|delete'.split('|'),0,{}))
</script>
</head>
<body>
<%@ include file="../include/admin_menu.jsp" %>
<h2>상품 등록</h2>
<form id="form1" name="form1" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td>상품명</td>
		<td><input name="product_name" value="${dto.product_name}"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input name="price" value="${dto.price}"></td>
	</tr>
	<tr>
		<td>상품설명</td>
		<td><textarea rows="5" cols="60" name="description" id="description">${dto.description}</textarea></td>
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
		<td>
		<img src="${path}/images/${dto.picture_url}" width="300px" height="300px"><br>
		<input type="file" name="file1"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="hidden" name="product_id" value="${dto.product_id}">
			<input type="button" value="수정" onclick="product_update()">
			<input type="button" value="삭제" onclick="product_delete()">
			<input type="button" value="목록" onclick="location.href='${path}/shop/product/list.do'">
		</td>
	</tr>
</table>
</form>
</body>
</html>