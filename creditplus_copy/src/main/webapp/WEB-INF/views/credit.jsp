<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>신용평가</title>
<%@ include file="header.jsp"%>
<script type="text/javascript">
onload = function() {
	var empdiv = ['', '건설업', '유통업', '서비스업'];
	var modeldiv = ['', '비외감', '은행', '부동산', '외감 대기업'];
	var grade = ['', 'A', 'B', 'C'];
	var evastate = ['', '완료', '진행중', '미평가'];
	
	var data = [];
	<c:forEach items="${company}" var="list">
		data.push({
			empno : '${list.empno}',
			empname : '${list.empname}',
			empdiv : empdiv[${list.empdiv}],
			modeldiv : modeldiv[${list.modeldiv}],
			grade : grade[${list.grade}],
			evastate : evastate[${list.evastate}],
			dat	: '${list.dat}'
		});
	</c:forEach>
	var cvPaging = new wijmo.collections.CollectionView(data);
	var theGrid = new wijmo.grid.FlexGrid('#theGrid', {
	 	autoGenerateColumns: false,
	    columns: [
			{ binding: 'chk', header: ' ', width: '*', align: 'center'},
			{ binding: 'empno', header: '고객번호', width: '3*', align: 'center'},
			{ binding: 'empname', header: '업체명', width: '3*', align: 'center'},
			{ binding: 'empdiv', header: '구분코드', width: '3*', align: 'center'},
			{ binding: 'modeldiv', header: 'Sales', width: '3*', align: 'center'},
			{ binding: 'grade', header: '최종등급', width: '3*', align: 'center'},
			{ binding: 'evastate', header: '상태', width: '3*', align: 'center'},
			{ binding: 'dat', header: '상태', width: '3*', align: 'center'}
	    ],
	    itemsSource: data,
	    formatItem: function(s, e) {
	    	if (e.panel == s.cells && s.columns[e.col].binding == 'chk') {
	      	var item = s.rows[e.row].dataItem,
	      			html = wijmo.format(document.getElementById('theTemplate').innerHTML, item);
	  			e.cell.innerHTML = html;
	      }
	    }
	 });
	
    btnFirstPage = document.getElementById('btnMoveToFirstPage'),
    btnPreviousPage = document.getElementById('btnMoveToPreviousPage'),
    btnNextPage = document.getElementById('btnMoveToNextPage'),
    btnLastPage = document.getElementById('btnMoveToLastPage'),
    btnCurrentPage = document.getElementById('btnCurrentPage');
    totalCount = document.getElementById('totalCount');
    
 	// 그리드 초기화
    theGrid.initialize({
      isReadOnly: true,
      itemsSource: cvPaging
    });
 	
 	// 페이지 사이즈 5로 설정.
    cvPaging.pageSize = 5;
 	
 	// 버튼 상태 초기화
    updateNaviagteButtons();
 
  	//사용자의 입력에 따라 collectionview의 페이지 크기를 업데이트하십시오.
    document.getElementById('pagingInput').addEventListener('change', function () {
	      var pagesize = this.value;

	      if (!pagesize) {
	        pagesize = 0;
	      } else {
	        pagesize = wijmo.Globalize.parseInt(pagesize);
	      }

	      cvPaging.pageSize = pagesize;
	      updateNaviagteButtons();
    });
  
 	// 탐색 버튼의 상태 업데이트
	function updateNaviagteButtons() {

	      if (cvPaging.totalItemCount <= 0) {
	        document.getElementById('naviagtionPage').style.display = 'none';
	        return;
	      }
	      
	      document.getElementById('naviagtionPage').style.display = 'block';
		  
	      if(cvPaging.pageCount === 1) {
		  	btnFirstPage.setAttribute('disabled', 'disabled');
	        btnPreviousPage.setAttribute('disabled', 'disabled');
	        btnLastPage.setAttribute('disabled', 'disabled');
	        btnNextPage.setAttribute('disabled', 'disabled');
 		  } else if (cvPaging.pageIndex === 0) {
	        btnFirstPage.setAttribute('disabled', 'disabled');
	        btnPreviousPage.setAttribute('disabled', 'disabled');
	        btnNextPage.removeAttribute('disabled');
	        btnLastPage.removeAttribute('disabled');
	      } else if (cvPaging.pageIndex === (cvPaging.pageCount - 1)) {
	        btnFirstPage.removeAttribute('disabled');
	        btnPreviousPage.removeAttribute('disabled');
	        btnLastPage.setAttribute('disabled', 'disabled');
	        btnNextPage.setAttribute('disabled', 'disabled');
	      } else {
	        btnFirstPage.removeAttribute('disabled');
	        btnPreviousPage.removeAttribute('disabled');
	        btnNextPage.removeAttribute('disabled');
	        btnLastPage.removeAttribute('disabled');
	      }
	      totalCount.innerHTML = cvPaging.totalItemCount;
	      btnCurrentPage.innerHTML = (cvPaging.pageIndex + 1) + ' / ' + cvPaging.pageCount;
	}
 	
	btnFirstPage.addEventListener('click', function () {
	  // 첫 번째 페이지로 이동하십시오.
	  cvPaging.moveToFirstPage();
	  updateNaviagteButtons();
	});
	
	btnPreviousPage.addEventListener('click', function () {
	  // 이전 페이지로 이동하십시오.
	  cvPaging.moveToPreviousPage();
	  updateNaviagteButtons();
	});
	
	btnNextPage.addEventListener('click', function () {
	  // 다음 페이지로 이동하십시오.
	  cvPaging.moveToNextPage();
	  updateNaviagteButtons();
	});
	
	btnLastPage.addEventListener('click', function () {
	  // 마지막 페이지로 이동하십시오.
	  cvPaging.moveToLastPage();
	  updateNaviagteButtons();
	});
	
	document.getElementById('search').addEventListener('click', function (event) {
		var chk_radio = document.getElementsByName('no');
		var chk = null;

		for(var i = 0; i < chk_radio.length; i++) {
			if(chk_radio[i].checked == true) {
				chk = chk_radio[i].value;
				chk = 'cform' + chk
				var form = document.getElementById(chk);
				form.method = "post";
				form.action = "${path}/evalContent.do"
				form.submit();
			}
		}
		if(chk == null){
            alert("조회 대상을 선택해주세요."); 
			return false;
		}
	});
	
	document.getElementById('remove').addEventListener('click', function (event) {
		var chk_radio = document.getElementsByName('empno');
		var chk = null;

		for(var i = 0; i < chk_radio.length; i++) {
			if(chk_radio[i].checked == true) {
				chk = chk_radio[i].value;
				
			}
		}
		if(chk == null){
            alert("삭제 대상을 선택해주세요."); 
			return false;
		}
	});

	document.getElementById('new').addEventListener('click', function (event) {
		alert('기존 평가가 있습니다.');
	});
	
	document.getElementById('comSearch').addEventListener('click', function (event) {
		var empName = document.getElementById('comName').value;
		window.open("${path}/comSearch.do?empName=" + empName, "회사 검색", "width=800, height=700, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );
	});
	
	document.getElementById('creditSearch').addEventListener('click', function (event) {
		var empname = document.getElementById('comName').value;
		if(empname != "") {
			var data = [];
			$.ajax({
			    url: "${path}/comSearchByName.do",
			    type: "POST",
			    data: {empName : empname},
			    dataType: "json",
			    success: function(result){
			    	var empdiv = ['', '건설업', '유통업', '서비스업'];
			    	var data = [];
			    	for (var i = 0; i < result.length; i++) {
			    	    data.push({
			    	      	empno: result[i].empno,
			    	      	empname: result[i].empname,
			    	      	empdiv: empdiv[result[i].empdiv],
			    	    	modeldiv : modeldiv[result[i].modeldiv],
			    	    	grade : grade[result[i].grade],
			    	    	evastate : evastate[result[i].evastate]
			    	    });
			    	}
			    	// 그리드 초기화
			        theGrid.initialize({
				          isReadOnly: true,
				          itemsSource: data
			        });
			        document.getElementById('comNo').value = result[0].empno;
			    }
			});
		} else {
			alert("업체명을 입력해주세요.");
		}
	});
	
}
</script>
</head>
<body>
<h1>신용평가</h1>
<table style="height: 50px;">
	<tr>
		<td>
			<div align="left" style="margin-left: 20px;">
			고객번호 &nbsp; | &nbsp;
			<input class="divbox" id="comNo" readonly="readonly" value="${company[0].empno}">&nbsp;
			업체명 &nbsp; | &nbsp;
			<button id="comSearch">ㅁ</button>&nbsp;
			<input id="comName" value="${company[0].empname}">
			</div>
		</td>
		<td>
			<button id="creditSearch">조회</button>
		</td>
	</tr>
</table>


<div id="theGrid"></div>
<div class="btn-group col-md-7" id="naviagtionPage" align="center">
	<select id="pagingInput" class="form-control" style="height: 30px; width: 50px; float: left; margin-left: 20px;">
		<option value="5">5</option>
		<option value="10">10</option>
	</select>
	<button type="button" class="btn btn-default" id="btnMoveToFirstPage">
		<span class="glyphicon glyphicon-fast-backward">|&lt;</span>
	</button>
	<button type="button" class="btn btn-default"
		id="btnMoveToPreviousPage">
		<span class="glyphicon glyphicon-step-backward">&lt;</span>
	</button>
	<button type="button" class="btn btn-default" disabled
		style="width: 100px" id="btnCurrentPage"></button>
	<button type="button" class="btn btn-default" id="btnMoveToNextPage">
		<span class="glyphicon glyphicon-step-forward">&gt;</span>
	</button>
	<button type="button" class="btn btn-default" id="btnMoveToLastPage">
		<span class="glyphicon glyphicon-fast-forward">&gt;|</span>
	</button>
	<span style="float: right; margin-right: 20px;">
	TOTAL : <span id="totalCount">0</span>
	</span>
	<div id="theTemplate" style="display:none;">
	<form id="cform{dat}">
		<input type="radio" name="no" value="{dat}">
		<input type="hidden" name="empno" value="{empno}">
		<input type="hidden" name="evadate" value="{dat}">
	</form>
	</div>
</div>

<table style="height: 50px; margin-top: 30px;">
	<tr>
		<td class="btnArea" style="padding-right: 50px;">
			<button class="btn" id="search">조회</button>
			<button class="btn" id="new">신규평가</button>
			<button class="btn" id="remove">삭제</button>
		</td>
	</tr>
</table>

</body>
</html>
