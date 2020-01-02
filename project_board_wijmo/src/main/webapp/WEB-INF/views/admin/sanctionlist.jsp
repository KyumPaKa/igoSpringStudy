<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

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

<%@ include file="../include/header.jsp" %>
<script>
onload = function() {
  // 데이터 생성
  var data = [];
  <c:forEach var="row" items="${map.list}" varStatus="status">
  	data.push({
  		refuserid: "${row.refuserid}",
  		name: "${row.name}",
  		email: "${row.email}",
  		category: "${row.category}",
  		count: "${row.count}",
  		status: "${row.status}",
  		add: "등록",
  		remove: "삭제",
  		refboardcategory: "${row.refboardcategory}",
  		refidx: "${row.refidx}"
  	});
   </c:forEach>

  // collectionview 생성
  var view = new wijmo.collections.CollectionView(data, {
  	pageSize: 10,
    pageChanged: updateCurrentPage
  });
  // grid 생성
  var theGrid = new wijmo.grid.FlexGrid('#theGrid', {
	    // 데이터 헤더 자동생성 방지
	  	autoGenerateColumns: false,
	    // 데이터 헤더 설정
	    columns: [
	    	{ binding: 'refuserid', header: '아이디' },
	    	{ binding: 'name', header: '이름' },
	    	{ binding: 'email', header: '이메일' },
	    	{ binding: 'category', header: '사유' },
	    	{ binding: 'count', header: '신고횟수' },
	    	{ binding: 'status', header: '처리상황' },
	    	{ binding: 'add', header: '블랙 등록' },
	    	{ binding: 'remove', header: '삭제' }
	    ],
	    // 데이터 추가
	    itemsSource: data,
	    // 형식 변경
	    formatItem: function(s, e) {
	    	if (e.panel == s.cells) {
			      	if (s.columns[e.col].binding == 'category') {
			        	var item = s.rows[e.row].dataItem;
			        	var brdPath;
			        	if(item.refboardcategory == 1) brdPath = 'mm/list';
			        	else if(item.refboardcategory == 2) brdPath = 'fb/view';
			        	else if(item.refboardcategory == 3) brdPath = 'ub/view';
			        	var html = '<a href="${path}/' + brdPath + '.do?refIdx=' + item.refboardcategory + '&idx=' + 
			        				item.refidx + '&category=' + item.category + '">' + e.cell.innerHTML + "</a>";
			        	e.cell.innerHTML = html;
			        }
				}
	    }
	  });
  // 타임 아웃 필터, 필터 입력 용 텍스트 박스를 생성
  var toFilter;
  var filteringInput = document.getElementById('filteringInput');
  
  // grid 초기화
  theGrid.initialize({
    isReadOnly: true,
    itemsSource: view
  });
  
  // 사용자가 입력할때 이벤트 추가
  filteringInput.addEventListener('input', filterGrid);

  // 컬렉션 뷰에 대한 필터 함수 정의
  function filterFunction(item) {
    var filter = filteringInput.value.toLowerCase();
    if (!filter) {
      return true;
    }

    return item.refuserid.toLowerCase().indexOf(filter) > -1;
  }

  function filterGrid() {
    if (toFilter) {
      clearTimeout(toFilter);
    }
    
    // 입력값으로 5초마다 자동 필터
    toFilter = setTimeout(function () {
      toFilter = null;
      if (view.filter == filterFunction) {
        view.refresh();
      }
      else {
        view.filter = filterFunction;
      }
    }, 500);
  }
  
  // 현재 페이지 보여주기
  view.onPageChanged();
  function updateCurrentPage() {
  	var curr = wijmo.format('Page {index:n0} of {cnt:n0}', {
    	index: view.pageIndex + 1,
      cnt: view.pageCount
    });
  	document.getElementById('spanCurrent').textContent = curr;
  }
  
  // 버튼 클릭 이벤트 추가
  document.getElementById('pager').addEventListener('click', function(e) {
		var btn = wijmo.closest(e.target, 'button');
    var id = btn ? btn.id : '';
  	switch (id) {
    	case 'btnFirst':
				view.moveToFirstPage();
        break;
    	case 'btnPrev':
				view.moveToPreviousPage();
        break;
    	case 'btnNext':
				view.moveToNextPage();
        break;
    	case 'btnLast':
				view.moveToLastPage();
        break;
    }
  });
	// 버튼에 데이터 넣기
  	theGrid.formatItem.addHandler(function(s, e) {
	    if (e.panel == s.cells) {
	      var col = s.columns[e.col],
	      	  item = s.rows[e.row].dataItem;

	        switch (col.binding) {
	          case 'add':
	        	    e.cell.innerHTML = document.getElementById('theTemplateAdd').innerHTML;
		            e.cell.dataItem = item;
		            break;
	          case 'remove':
					e.cell.innerHTML = document.getElementById('theTemplateRemove').innerHTML;
					e.cell.dataItem = item;
					break;
	        }
	    }
	});
	
    // 버튼 클릭 이벤트 추가
  	theGrid.addEventListener(theGrid.hostElement, 'click', function(e) {
	    if (e.target.tagName == 'BUTTON') {
	        var item = wijmo.closest(e.target, '.wj-cell').dataItem;
			if(e.target.id == "add") {
				if(confirm("등록하시겠습니까?")) {
					var status =  item.status;
					if(status != "처리완료"){
						var refuserid = item.refuserid;
						var category = item.category;
						var value={"refuserid":refuserid,"category":category};
					 	$.ajax({
							url:"${path}/admin/updateblacklist.do",
							data:value,
							type:"post",
							dataType:"text",
							success:function(data){
								alert('처리완료');
								item.status = '처리완료';
								theGrid.invalidate();
							}
						});
					}else{
						alert("이미 처리하셨습니다.");
					}
				}
			} else if(e.target.id == "remove") {
				if(confirm("신고 내역에서 삭제하시겠습니까?")) {
					var refuserid = item.refuserid;
					var value={"refuserid":refuserid};
					$.ajax({
						url:"${path}/admin/clearsanction.do",
						data:value,
						dataType:"text",
						type:"post",
						success:function(data){
							alert('삭제완료');
							theGrid.collectionView.remove(item);
						}
					});
				}
			}
	    }
	  });
  	
    // 자동 사이즈 정렬
    theGrid.autoSizeColumns();
}
</script>
</head>
<body>
<%@ include file="../include/menuAdmin.jsp" %>
<h2>신고내역</h2>

<div class="row-fluid well">
  아이디 검색 : <input id="filteringInput" type="text" class="form-control app-pad" />
</div>
<div class="container">
    <div class="jumbotron"><br>
		<div id="theGrid"></div>
		<div id="theTemplateAdd" style="display:none">
		    <button class="btn btn-default" name="{refuserid}" id="add">등록</button>
		</div>
		<div id="theTemplateRemove" style="display:none">
		    <button class="btn btn-default" name="{refuserid}" id="remove">삭제</button>
		</div>
	    <div id="pager" align="center">
		    <button id="btnFirst" class="btn"><span class="wj-glyph-step-backward"></span></button>
		    <button id="btnPrev" class="btn"><span class="wj-glyph-left"></span></button>
		    &nbsp;&nbsp;&nbsp;<span id="spanCurrent"></span>&nbsp;&nbsp;&nbsp;
		    <button id="btnNext" class="btn"><span class="wj-glyph-right"></span></button>
		    <button id="btnLast" class="btn"><span class="wj-glyph-step-forward"></span></button>
	    </div> 
    </div>
</div>

</body>
</html>



















