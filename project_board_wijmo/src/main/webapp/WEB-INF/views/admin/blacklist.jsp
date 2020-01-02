<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<link href="${path}/resources/grid.css" rel="stylesheet"/>
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

<script>
onload = function() {
	
	// 데이터 생성
    data = [];
	<c:forEach var="row" items="${map.list}">
    data.push({
    	idx: '${row.idx}',
    	refuserid: '${row.refuserid}',
    	reason: '${row.reason}',
    	regdate: '<fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>',
    	name: '${row.name}',
    	email: '${row.email}',
    	btn: '복구'
    });
	</c:forEach>

	var theTemplate = document.getElementById('theTemplate');
	// collectionview, grid 요소 생성
	var collectionView = new wijmo.collections.CollectionView(data),
	    theGrid = new wijmo.grid.FlexGrid('#theGrid', {
	    	// 데이터 헤더 자동생성 방지
	    	autoGenerateColumns: false,
	    	// 데이터 헤더 설정
	    	columns: [
		          { binding: 'idx', header: '번호'},
		          { binding: 'refuserid', header: '아이디'},
		          { binding: 'name', header: '이름'},
		          { binding: 'reason', header: '사유'},
		          { binding: 'regdate', header: '등록날짜'},
		          { binding: 'email', header: '이메일'},
		          { binding: 'btn', header: '비고'}
	        ],
	        // 데이터 추가
	        itemsSource: data
	    }),
	    // 페이지 이동 버튼
	    btnFirstPage = document.getElementById('btnMoveToFirstPage'),
	    btnPreviousPage = document.getElementById('btnMoveToPreviousPage'),
	    btnNextPage = document.getElementById('btnMoveToNextPage'),
	    btnLastPage = document.getElementById('btnMoveToLastPage'),
	    btnCurrentPage = document.getElementById('btnCurrentPage');

	// 그리드 초기화
	theGrid.initialize({
	  // 읽기 전용 설정
	  isReadOnly: true,
	  itemsSource: collectionView
	});

	// 페이지 사이즈 설정.
	collectionView.pageSize = 10;

	// 버튼 상태 초기화
	updateNaviagteButtons();
	
	// 탐색 버튼의 상태 업데이트
	function updateNaviagteButtons() {
	  document.getElementById('naviagtionPage').style.display = 'block';
	
	  if (collectionView.pageIndex === 0) {
	    btnFirstPage.setAttribute('disabled', 'disabled');
	    btnPreviousPage.setAttribute('disabled', 'disabled');
	    btnNextPage.removeAttribute('disabled');
	    btnLastPage.removeAttribute('disabled');
	  } else if (collectionView.pageIndex === (collectionView.pageCount - 1)) {
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
	  // 현재 페이지 보여주기
	  btnCurrentPage.innerHTML = 'Page ' + (collectionView.pageIndex + 1) + ' of ' + collectionView.pageCount;
	}

	btnFirstPage.addEventListener('click', function () {
	  // 첫 번째 페이지로 이동하십시오.
	  collectionView.moveToFirstPage();
	  updateNaviagteButtons();
	});

	btnPreviousPage.addEventListener('click', function () {
	  // 이전 페이지로 이동하십시오.
	  collectionView.moveToPreviousPage();
	  updateNaviagteButtons();
	});

	btnNextPage.addEventListener('click', function () {
	  // 다음 페이지로 이동하십시오.
	  collectionView.moveToNextPage();
	  updateNaviagteButtons();
	});

	btnLastPage.addEventListener('click', function () {
	  // 마지막 페이지로 이동하십시오.
	  collectionView.moveToLastPage();
	  updateNaviagteButtons();
	});
	
	// 타임 아웃 필터, 필터 입력 용 텍스트 박스를 생성
    var toFilter;
	var filteringInput = document.getElementById('filteringInput');

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
	    
	    if (collectionView.filter === filterFunction) {
	    	collectionView.refresh();
	    	// 현재 페이지 보여주기
	    	document.getElementById('btnCurrentPage').innerHTML = 'Page ' + (collectionView.pageIndex + 1) + ' of ' + collectionView.pageCount;
	    }
	    else {
	    	collectionView.filter = filterFunction;
	    	// 현재 페이지 보여주기
	    	document.getElementById('btnCurrentPage').innerHTML = 'Page ' + (collectionView.pageIndex + 1) + ' of ' + collectionView.pageCount;
	    }
	  }, 500);
	}
	
	// 버튼에 데이터 넣기
	theGrid.formatItem.addHandler(function(s, e) {
	    if (e.panel == s.cells) {
	      var col = s.columns[e.col],
	      	  item = s.rows[e.row].dataItem;

	        if(col.binding == 'btn') {
	            e.cell.innerHTML = document.getElementById('theTemplate').innerHTML;
	            e.cell.dataItem = item;
	        }
	    }
	});
	
	// 버튼 클릭 이벤트 추가
	theGrid.addEventListener(theGrid.hostElement, 'click', function(e) {
	    if (e.target.tagName == 'BUTTON') {
	        // 버튼에 넣은 데이터 가져오기
	        var item = wijmo.closest(e.target, '.wj-cell').dataItem;
	       	
			if(confirm("일반 회원으로 복구하시겠습니까?")) {
				var refuserid = item.refuserid;
				var value={"refuserid":refuserid};
				
				$.ajax({
					url:"${path}/admin/clearblacklist.do",
					data:value,
					dataType:"text",
					type:"post",
					success:function(data){
						alert('복구완료');
						// 행 제거
						theGrid.collectionView.remove(item);
					}
				});
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
<h2>블랙리스트 목록</h2>

	<div class="container">
		<div class="jumbotron">
			<div class="row-fluid well">
			  아이디 검색 : <input id="filteringInput" type="text" class="form-control app-pad" />
			</div>
			<div id="theGrid"></div>
			<div id="theTemplate" style="display:none">
			    <button class="btn btn-default" name="{refuserid}">복구</button>
			</div>  
			<div class="row-fluid well row" align="center">
				<div class="btn-group col-md-7" id="naviagtionPage">
					<button type="button" class="btn btn-default" id="btnMoveToFirstPage">
						<span class="glyphicon glyphicon-fast-backward"><span class="wj-glyph-step-backward"></span></span>
					</button>
					<button type="button" class="btn btn-default" id="btnMoveToPreviousPage">
						<span class="glyphicon glyphicon-step-backward"><span class="wj-glyph-left"></span></span>
					</button>
					<span id="btnCurrentPage"></span>
					<button type="button" class="btn btn-default" id="btnMoveToNextPage">
						<span class="glyphicon glyphicon-step-forward"><span class="wj-glyph-right"></span></span>
					</button>
					<button type="button" class="btn btn-default" id="btnMoveToLastPage">
						<span class="glyphicon glyphicon-fast-forward"><span class="wj-glyph-step-forward"></span></span>
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



















