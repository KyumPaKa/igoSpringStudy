<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <title>jQuery(제이쿼리)</title>
  <b>거래내역 조회</b>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>

<!-- 예제 시작 -->

<style type="text/css">

	table {
    width: 500px;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
  }


</style>


<div id="first">
	 </br>
	 <input type="radio" id="r0" name="radioSet" value="0"/><label for="r0">전체</label>
   <input type="radio" id="r1" name="radioSet" value="1"/><label for="r1">입금</label>
   <input type="radio" id="r2" name="radioSet" value="2"/>출금
   
   <input type="text"  id="input1"/>
   <input type="button" id="input2" value="조회"/>
</div>

</br>

<div id="div1">
  <table id="tb1">
    <tr>
       <th width="100px">구분</th>       
       <th>날짜</th>
       <th>이름</th>
       <th>금액</th>
    </tr>
    <tbody>
		<tr>
			<td style="text-align:center;">입금</td>
			<td style="text-align:center;">2019.09.09</td>
			<td style="text-align:center;">아이지오</td>
			<td style="text-align:right;">5,000,000</td>				
		</tr>
    </tbody>
  </table>
</div>

<script>
//--------------------------- 작성부분 ------------------------------
	$(document).ready(function(){
		$("#div1").hide();
		$(':radio[name="radioSet"]:input[value="0"]').attr("checked",true);
		
		$("#input2").click(function (){
			var inqTp = $("input[name=radioSet]:checked").val();
			var inqNm = $("#input1").val();
			
			$("#div1").show();
			$('#divInqRst > table > tbody:last > tr').remove();
			var content = "<tr>"
			    content += "<td style='text-align:center;'>입금</td>"
				content += "<td style='text-align:center;'>2019.01.02</td>"
				content += "<td style='text-align:center;'>아이지오</td>"
				content += "<td style='text-align:right;'>10,000,000</td>"
				content += "</tr>"
				content += "<tr>"
				content += "<td style='text-align:center;'>출금</td>"
				content += "<td style='text-align:center;'>2019.07.13</td>"
				content += "<td style='text-align:center;'>조규민</td>"
				content += "<td style='text-align:right;'>3,000,000</td>"
				content += "</tr>"
			$('#divInqRst > table > tbody:last').append(content);
		});	
  	});
//---------------------------------------------------------------
</script>

<!-- 예제 종료 -->

</body>
</html>