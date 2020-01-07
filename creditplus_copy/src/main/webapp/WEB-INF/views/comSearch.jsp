<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회사 검색</title>
<%@ include file="header.jsp"%>
<script type="text/javascript">
onload = function() {
	var empdiv = ['', '건설업', '유통업', '서비스업'];
	
	var theGrid = new wijmo.grid.FlexGrid('#theGrid', {
		autoGenerateColumns: false,
	    columns: [
				{ binding: 'no', header: '순서', width: '*' },
				{ binding: 'empno', header: '고객번호', width: '2*' },
				{ binding: 'empname', header: '업체명', width: '2*' },
				{ binding: 'empdiv', header: '업종구분', width: '2*' },
				{ binding: 'chk', header: ' ', width: '*' }
	    ],
	    itemsSource: [{
	    	no: '${company[0].empno}',
	    	empno: '${company[0].empno}',
	    	empname: '${company[0].empname}',
	    	empdiv: empdiv[${company[0].empdiv}],
	    }]
	});
	
	document.getElementById('comSearchByName').addEventListener('click', function (event) {
		var empname = document.getElementById('empname').value;
		if(empname != '') {
			$.ajax({
			    url: "${path}/comSearchByName.do",
			    type: "POST",
			    data: {empName : empname},
			    dataType: "json",
			    success: function(result){
			    	var data = [];
			    	for (var i = 0; i < result.length; i++) {
			    	    data.push({
			    	      	no: i + 1,
			    	      	empno: result[i].empno,
			    	      	empname: result[i].empname,
			    	      	empdiv: empdiv[result[i]]
			    	    });
			    	}
			    	// 그리드 초기화
			        theGrid.initialize({
				          isReadOnly: true,
				          itemsSource: data
			        });
			    }
			});
		} else {
			alert("업체명을 입력해주세요.");
		}
	});
	
	// 버튼에 데이터 넣기
	theGrid.formatItem.addHandler(function(s, e) {
	    if (e.panel == s.cells) {
	      	var col = s.columns[e.col];
	      	var item = s.rows[e.row].dataItem;

	        if(col.binding == 'chk') {
	            e.cell.innerHTML = document.getElementById('theTemplate').innerHTML;
	            e.cell.dataItem = item;
	        }
	    }
	});
	
	theGrid.addEventListener(theGrid.hostElement, 'click', function(e) {
		if (e.target.tagName == 'BUTTON') {
	  		var item = wijmo.closest(e.target, '.wj-cell').dataItem;
	  		console.log(item);
	  		opener.document.getElementById("comName").value = item.empname;
	  		opener.document.getElementById("comNo").value = item.empno;
			window.close();
	    }
	});
	
}
</script>
</head>
<body>
<table style="height: 50px;">
	<tr>
		<td>
			<div align="left" style="margin-left: 20px;">
			업체명 &nbsp; | &nbsp;
			<input id="empname" value="${empName}">
			</div>
		</td>
		<td>
			<button id="comSearchByName">조회</button>
		</td>
	</tr>
</table>

<div id="theGrid"></div>
<div id="theTemplate" style="display:none;">
	<button>선택</button>
</div>
</body>
</html>