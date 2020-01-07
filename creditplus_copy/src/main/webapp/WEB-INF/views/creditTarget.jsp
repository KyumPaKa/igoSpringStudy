<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>신용평가대상</title>
<%@ include file="header.jsp"%>
<script type="text/javascript">
onload = function() {
	var empdiv = ['', '건설업', '유통업', '서비스업'];
	var modeldiv = ['', '비외감', '은행', '부동산', '외감 대기업'];
	var grade = ['', 'A', 'B', 'C'];
	var evastate = ['', '완료', '진행중', '미평가'];
	
	var data = [];
	<c:forEach items="${companyList}" var="list">
		data.push({
			empno : '${list.empno}',
			empname : '${list.empname}',
			empdiv : empdiv[${list.empdiv}],
			modeldiv : modeldiv[${list.modeldiv}],
			grade : grade[${list.grade}],
			evastate : evastate[${list.evastate}],
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
			{ binding: 'evastate', header: '상태', width: '3*', align: 'center'}
	    ],
	    itemsSource: data,
	    formatItem: function(s, e) {
	    	if (e.panel == s.cells && s.columns[e.col].binding == 'chk') {
		      	var item = s.rows[e.row].dataItem;
	      		var html = wijmo.format(document.getElementById('theTemplate').innerHTML, item);
	  			e.cell.innerHTML = html;
	      }
	    }
	 });
	var filteringInput = document.getElementById('filteringInput');
	var toFilter;
	
	// 사용자가 입력할때 필터
	filteringInput.addEventListener('input', filterGrid);
	
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
	
	// define the filter function for the collection view.
	function filterFunction(item) {
	  var filter = filteringInput.value.toLowerCase();
	  if (!filter) {
	    return true;
	  }
	  var innerSearch = document.getElementById('innerSearch').value;
	  
	  if(innerSearch === 'empno') {
		  return item.empno.toLowerCase().indexOf(filter) > -1;
	  } else if(innerSearch === 'empname') {
		  return item.empname.toLowerCase().indexOf(filter) > -1;
	  }
	}

	// apply filter (applied on a 500 ms timeOut)
	function filterGrid() {
	  if (toFilter) {
	    clearTimeout(toFilter);
	  }

	  toFilter = setTimeout(function () {
	    toFilter = null;
	    if (cvPaging.filter === filterFunction) {
	      cvPaging.refresh();
	      totalCount.innerHTML = cvPaging.totalItemCount;
	    }
	    else {
	      cvPaging.filter = filterFunction;
	      totalCount.innerHTML = cvPaging.totalItemCount;
	    }
	  }, 500);
	}
	
	document.getElementById('search').addEventListener('click', function (event) {
		var chk_radio = document.getElementsByName('empno');
		var chk = null;

		for(var i = 0; i < chk_radio.length; i++) {
			if(chk_radio[i].checked == true) {
				chk = chk_radio[i].value;
				var form = document.getElementById('ctform');
				form.method = "post";
				form.action = "${path}/credit.do";
				form.submit();
			}
		}
		if(chk == null){
            alert("조회 대상을 선택해주세요."); 
			return false;
		}
	});
	
	document.getElementById('comHistory').addEventListener('click', function (event) {
		var chk_radio = document.getElementsByName('empno');
		var chk = null;

		for(var i = 0; i < chk_radio.length; i++) {
			if(chk_radio[i].checked == true) {
				chk = chk_radio[i].value;
				var form = document.getElementById('ctform');
				form.method = "post";
				form.action = "${path}/comHistory.do";
				form.submit();
			}
		}
		if(chk == null){
            alert("조회 대상을 선택해주세요."); 
			return false;
		}
	});
	
}
</script>
</head>
<body>
<h1>신용평가대상</h1>
<table>
	<tr>
		<th colspan="5" class="htitle">대상 현황</th>
	</tr>
	<tr>
		<th></th>
		<th>1개월 초과</th>
		<th>1개월 이내</th>
		<th>평가 진행중</th>
		<th>미평가</th>
	</tr>
	<tr>
		<td>업체수</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
	</tr>
</table>

<div class="explanArea">※ 위 업체수를 클릭하면 상세목록이 표시됩니다.</div>

<table style="height: 50px;">
	<tr>
		<td>
			<ul>
				<li class="ctitle">
					조회구분 &nbsp; | &nbsp;
					<input class="divbox" readonly="readonly">
				</li>
			</ul>
			<ul>
				<li class="ctitle">
					조회내검색 &nbsp; | &nbsp;
					<select style="height: 25px;" id="innerSearch">
						<option value="empname">업체명</option>
						<option value="empno">고객코드</option>
					</select>
					<input id="filteringInput">
				</li>
			</ul>
			<ul>
				<li class="btnArea">
<!-- 					<button class="btn">조회</button> -->
				</li>
			</ul>
		</td>
	</tr>
</table>

<form id="ctform">
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
		<input type="radio" name="empno" value="{empno}">
	</div>
</div>
</form>

<table style="height: 50px; margin-top: 30px;">
	<tr>
		<td class="btnArea" style="padding-right: 50px;">
			<button class="btn" id="comHistory">업력</button>
			<button class="btn" id="search">조회</button>
		</td>
	</tr>
</table>

</body>
</html>