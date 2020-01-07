<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>평가내역</title>
<%@ include file="header.jsp"%>
<script type="text/javascript">
onload = function() {

		var data = [];
		<c:forEach items="${evalDTOList}" var="dto">
			data.push({
				accname : '${dto.accname}',
				price : ${dto.price},
				evadate : '<fmt:formatDate value="${dto.evadate}" pattern="yyyy/MM/dd"/>'
			 });
		</c:forEach>
		
		var theGrid = new wijmo.grid.FlexGrid('#theGrid', {
			autoGenerateColumns: false,
			itemsSource: data,
			columns: [
					{ binding: 'accname', header: '계정명', width: '*', align: 'left'},
					{ binding: 'price', header: '금액', width: '2*', align: 'right'},
					{ binding: 'evadate', header: '날짜', width: '*', align: 'center'}
			   		],
	   		formatItem: function (s, e) {
	   			if (s.columns[e.col].binding == 'price' && e.panel == s.cells) {
	   				var item = s.rows[e.row].dataItem;
	   				var html = e.cell.innerHTML + " 원";
	    			e.cell.innerHTML = html;
	   			}
	   		}
		});
		
		// 그리드 초기화
		theGrid.initialize({
			isReadOnly: true,
			itemsSource: data
		});
	  
		document.getElementById('one').addEventListener('click', function (event) {
			$.ajax({
			    url: "${path}/evalcon.do",
			    type: "POST",
			    data: {empno : ${empno},
			    	   accgroup: 1,
			    	   evadate: '<fmt:formatDate value="${evadate}" pattern="yy/MM/dd"/>'},
			    dataType: "json",
			    success: function(result){
			    	var data = [];
			    	for (var i = 0; i < result.length; i++) {
			    		var date = new Date(result[i].evadate);
			    		var y = zeroCheck(date.getFullYear());
			    		var m = zeroCheck(date.getMonth() + 1);
			    		var d = zeroCheck(date.getDate());
			    	    data.push({
			    	    	accname: result[i].accname,
			    	    	price: result[i].price,
			    	    	evadate: y + '/' + m + '/' + d
			    	    });
			    	}
			    	// 그리드 초기화
			        theGrid.initialize({
				          isReadOnly: true,
				          itemsSource: data
			        });
			    }
			});
		});
		document.getElementById('two').addEventListener('click', function (event) {
			$.ajax({
			    url: "${path}/evalcon.do",
			    type: "POST",
			    data: {empno : ${empno},
			    	   accgroup: 2,
			    	   evadate: '<fmt:formatDate value="${evadate}" pattern="yy/MM/dd"/>'},
			    dataType: "json",
			    success: function(result){
			    	var data = [];
			    	for (var i = 0; i < result.length; i++) {
			    		var date = new Date(result[i].evadate);
			    		var y = zeroCheck(date.getFullYear());
			    		var m = zeroCheck(date.getMonth() + 1);
			    		var d = zeroCheck(date.getDate());
			    	    data.push({
			    	    	accname: result[i].accname,
			    	    	price: result[i].price,
			    	    	evadate: y + '/' + m + '/' + d
			    	    });
			    	}
			    	// 그리드 초기화
			        theGrid.initialize({
				          isReadOnly: true,
				          itemsSource: data
			        });
			    }
			});
		});
		document.getElementById('three').addEventListener('click', function (event) {
			$.ajax({
			    url: "${path}/evalcon.do",
			    type: "POST",
			    data: {empno : ${empno},
			    	   accgroup: 3,
			    	   evadate: '<fmt:formatDate value="${evadate}" pattern="yy/MM/dd"/>'},
			    dataType: "json",
			    success: function(result){
			    	console.log(result);
			    	var data = [];
			    	for (var i = 0; i < result.length; i++) {
			    		var date = new Date(result[i].evadate);
			    		var y = zeroCheck(date.getFullYear());
			    		var m = zeroCheck(date.getMonth() + 1);
			    		var d = zeroCheck(date.getDate());
			    	    data.push({
			    	    	accname: result[i].accname,
			    	    	price: result[i].price,
			    	    	evadate: y + '/' + m + '/' + d
			    	    });
			    	}
			    	// 그리드 초기화
			        theGrid.initialize({
				          isReadOnly: true,
				          itemsSource: data
			        });
			    }
			});
		});
}
function zeroCheck(num) {
	if(num < 10) {
		return '0' + num;
	} else {
		return num;
	}
}
</script>
</head>
<body>
<table style="height: 100px;">
	<tr>
		<td>
			<div align="left" style="margin-left: 20px;">
			고객번호 &nbsp; | &nbsp; ${company[0].empno}
			</div>
		</td>
		<td>
			<div align="left" style="margin-left: 20px;">
			업체명 &nbsp; | &nbsp; ${company[0].empname}
			</div>
		</td>
		<td>
			<div align="left" style="margin-left: 20px;">
			업종구분 &nbsp; | &nbsp; 
			<c:choose>
				<c:when test="${company[0].empdiv == 1}">건설업</c:when>
				<c:when test="${company[0].empdiv == 2}">유통업</c:when>
				<c:when test="${company[0].empdiv == 3}">서비스업</c:when>
			</c:choose>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<div align="left" style="margin-left: 20px;">
			평가 날짜 &nbsp; | &nbsp; 
			2020-01-02
			</div>
		</td>
		<td>
			<div align="left" style="margin-left: 20px;">
			모형구분 &nbsp; | &nbsp; 
			<c:choose>
				<c:when test="${company[0].modeldiv == 1}">비외감</c:when>
				<c:when test="${company[0].modeldiv == 2}">은행</c:when>
				<c:when test="${company[0].modeldiv == 3}">부동산</c:when>
				<c:when test="${company[0].modeldiv == 4}">외감 대기업</c:when>
			</c:choose>
			</div>
		</td>
		<td>
			<div align="left" style="margin-left: 20px;">
			최종등급 &nbsp; | &nbsp; 
			<c:choose>
				<c:when test="${company[0].grade == 1}">A</c:when>
				<c:when test="${company[0].grade == 2}">B</c:when>
				<c:when test="${company[0].grade == 3}">C</c:when>
			</c:choose>
			</div>
		</td>
	</tr>
</table>
<br>
<ul>
	<li id="one">재무상태표</li>
	<li id="two">손익계산서</li>
	<li id="three">자본변동표</li>
</ul>
<br>
<div id="theGrid"></div>
</body>
</html>