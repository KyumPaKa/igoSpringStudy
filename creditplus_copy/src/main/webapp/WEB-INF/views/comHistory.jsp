<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>업력</title>
<%@ include file="header.jsp"%>
<script type="text/javascript">
onload = function() {

	var data = [];
	<c:forEach items="${history}" var="history">
	data.push({
		hisno: '${history.hisno}',
		hisstr: '<fmt:formatDate value="${history.hisdate}" pattern="yyMMdd"/>',
		hiscontent: '${history.hiscontent}'
	});
	</c:forEach>
	
	var cvPaging = new wijmo.collections.CollectionView(data);
	var theGrid = new wijmo.grid.FlexGrid('#theGrid', {
		autoGenerateColumns : false,
		columns : [ {
			binding : 'hisno', header : '번호', width : '*', align: 'center'
		}, {
			binding : 'hisstr', header : '날짜', width : '2*', align: 'center'
		}, {
			binding : 'hiscontent', header : '내용', width : '10*', align: 'center'
		} ],
		itemsSource : cvPaging,
		allowAddNew: true,
	    allowDelete: true,
	    cellEditEnding: function(s, e) {
			var col = s.columns[e.col];
			if (col.binding == 'hisstr') {
				var value = wijmo.changeType(s.activeEditor.value, wijmo.DataType.String, col.format);
				var hisdateformat =  /^[0-9]{6}$/;
				if (!hisdateformat.test(value)) {
				    e.cancel = true;
				    alert('000000 형식으로 입력해주세요.')
				}
			} else if(col.binding == 'hisno') {
				var value = wijmo.changeType(s.activeEditor.value, wijmo.DataType.Number, col.format);
				var hisnoformat = /^[0-9]$/;
				if (!hisnoformat.test(value)) {
				    e.cancel = true;
				    alert('숫자만 입력해주세요.')
				}
			}
	    }
	});
	
	btnFirstPage = document.getElementById('btnMoveToFirstPage'),
    btnPreviousPage = document.getElementById('btnMoveToPreviousPage'),
    btnNextPage = document.getElementById('btnMoveToNextPage'),
    btnLastPage = document.getElementById('btnMoveToLastPage'),
    btnCurrentPage = document.getElementById('btnCurrentPage');
    totalCount = document.getElementById('totalCount');
    
 	// 페이지 사이즈 5으로 설정.
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
	
	// 위쪽에 새행 추가
	theGrid.newRowAtTop = true;
	
	document.getElementById('save').addEventListener('click', function () {
		for(var i = 0; i < data.length; i++) {
			data[i].empno = '${company[0].empno}';
		}

		$.ajax({
		    url: "${path}/comHistorySave.do",
		    type: "POST",
		    data: JSON.stringify(data),
		    contentType : "application/json; charset=UTF-8",
		    success: function(){
		    	alert('저장 성공');
		    	totalCount.innerHTML = cvPaging.totalItemCount;
		    	updateNaviagteButtons();
		    }
		});
  	});
	
}
</script>
</head>
<body>
<h1>업력</h1>
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

<div id="theGrid"></div>
<div class="btn-group col-md-7" id="naviagtionPage" align="center">
	<select id="pagingInput" class="form-control" style="height: 30px; width: 50px; float: left; margin-left: 20px;">
		<option value="5">5</option>
		<option value="10">10</option>
		<option value="20">20</option>
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
</div>

<table style="height: 50px; margin-top: 30px;">
	<tr>
		<td class="btnArea" style="padding-right: 50px;">
			<button class="btn" id="research">조회</button>
			<button class="btn" id="save">저장</button>
		</td>
	</tr>
</table>

</body>
</html>
