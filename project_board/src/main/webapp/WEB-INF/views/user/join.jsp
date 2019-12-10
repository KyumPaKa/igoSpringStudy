<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function showPostcode() {
 new daum.Postcode({
     oncomplete: function(data) {
// 팝업에서 검색결과 항목을 클릭했을때 
//실행할 코드를 작성하는 부분.

         // 각 주소의 노출 규칙에 따라 주소를 조합한다.
// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 
//이를 참고하여 분기 한다.
         var fullAddr = ''; // 최종 주소 변수
         var extraAddr = ''; // 조합형 주소 변수

         // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 
         //가져온다.
// 사용자가 도로명 주소를 선택했을 경우         
         if (data.userSelectedType === 'R') { 
             fullAddr = data.roadAddress;

         } else { // 사용자가 지번 주소를 선택했을 경우(J)
             fullAddr = data.jibunAddress;
         }

         // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
         if(data.userSelectedType === 'R'){
             //법정동명이 있을 경우 추가한다.
             if(data.bname !== ''){
                 extraAddr += data.bname;
             }
             // 건물명이 있을 경우 추가한다.
             if(data.buildingName !== ''){
                 extraAddr += (extraAddr !== '' ? ', ' 
+ data.buildingName : data.buildingName);
             }
             // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
             fullAddr += (extraAddr !== '' ? 
            		 ' ('+ extraAddr +')' : '');
         }

         // 우편번호와 주소 정보를 해당 필드에 넣는다.
         document.getElementById('post_code').value = data.zonecode; //5자리 새우편번호 사용
         document.getElementById('address1').value = fullAddr;

         // 커서를 상세주소 필드로 이동한다.
         document.getElementById('address2').focus();
     }
 }).open();
}
</script>
<script>
var flag = false;
$(function(){
	$("#idcheck").click(function(){
		var query = {userid : $("#userid").val()};
		$.ajax({
			url : "${path}/user/idcheck.do",
			type : "post",
			data : query,
	
			success : function(data) {
				$(".result .msg").attr("style", "display:span");
				if(data ==1) {
					$(".result .msg").html("아이디를 확인하여 주십시오.(중복)");
					$(".result .msg").attr("style", "color:#f00");
				} else {
					$(".result .msg").html("사용가능");
					$(".result .msg").attr("style", "color:#00f");
					flag = true;
					}
				}
			});
	});
})
function idreset() {
	$(".result .msg").html("아이디를 확인하여 주십시오.");
	flag = false;
}
</script>
<script>
var flag = false;
$(function(){

$("#emailcheck").click(function(){
	var query  = {email : $("#email").val()};
	$.ajax({
		url : "${path}/user/emailcheck.do",
		type : "post",
		data : query,

		success : function(data) {
			$(".resultemail .msg").attr("style", "display:span");
			if(data ==1) {
				$(".resultemail .msg").html("이메일를 확인하여 주십시오.(중복된 이메일로 확인됨)");
				$(".resultemail .msg").attr("style", "color:#f00");
			} else {
				$(".resultemail .msg").html("사용가능");
				$(".resultemail .msg").attr("style", "color:#00f");
				flag = true;
				}
			}
		});
});
$("#btnJoin").click(function(){
	if(flag) {
		$("form").submit();
	} else {
		alert("아이디 및 이메일 중복확인을 해주세요.");
	}
});
})
function idreset1() {
	$(".resultemail .msg").html("이메일을 확인하여 주십시오.");
	flag = false;
}
</script>
</head>
<body>
<%@ include file="../include/menuUser.jsp"%>
<h2>회원가입</h2>
<form action="${path}/user/insertUser.do" method="post">

<table>
	<tr>
		<td>아이디</td>
		<td><input name="userid" id="userid" onkeydown="idreset()"></td>
	</tr>
	
	<tr>
		<td><button type="button" id="idcheck">중복아이디 확인</button></td>
		<td class="result">
		<span class="msg" style="display: none;"></span>
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input name="name"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input name="email" id = email onkeydown="idreset1()"></td>
	</tr>
		<tr>
		<td><button type="button" id="emailcheck">중복이메일 확인</button></td>
		<td class="resultemail">
		<span class="msg" style="display: none;"></span>
		</td>
	</tr>
<form name="form1" method="post" id="form1">
	<tr>
		<td>주소</td>
		<td>
			우편번호 : <input name="zipcode" id="post_code" readonly size="10">
			<input type ="button" onclick="showPostcode()" value="우편으로 찾기">
			<br>
			주소 : <input name="address1" id="address1" size="60">
			<br>
			상세 주소 : <input name="address2" id="address2">
		</td>
	</tr>
</form>
<!-- 	사용권한 정해서 가입하기  -->
<!-- 	<tr> -->
<!-- 		<td>사용권한</td> -->
<!-- 		<td> -->
<!-- 			<select name="authority"> -->
<!-- 				<option value="ROLE_BABY">일반사용자</option> -->
<!-- 				<option value="ROLE_ADMIN">관리자</option> -->
<!-- 			</select> -->
<!-- 		</td> -->
<!-- 	</tr> -->
	<tr>
		<td colspan="2" align="right">	
			<input type="button" id="btnJoin" value="회원가입">
		</td>
	</tr>
</table>
</form>
</body>
</html>



















