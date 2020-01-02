<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<script src="https://code.jquery.com/jquery-3.3.1.js" type="text/javascript"></script>

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

<%@ include file="../include/header.jsp"%>
<script>
	$(function() {
		$(".change").click(function() {
			var refuserid = $(this).parent().siblings().eq(0).text();
			var authority =$(this).siblings().eq(0).val();
			var value = {
				"refuserid" : refuserid,"authority":authority
			};
			$.ajax({
				url : "${path}/admin/changelevel.do",
				data : value,
				dataType : "text",
				type : "post",
				success : function(data) {
					changeresult("1");
				}
			});
			function changeresult(num){
				$.ajax({
					type:"post",
					url:"${path}/admin/level.do",
					success:function(result){
						
						}
					});
				}
		});
	})
	
// DB상으로는 바뀌는데 UI상에는 바뀌지 않는 authority의 값을 DB값대로 나오게 바꾸는 AJAX
$(function() {
    $(".change").click(function() {
       var refuserid = $(this).parent().siblings().eq(0).text();
       var authority =$(this).siblings().eq(0).val();
       var value = {
          "refuserid" : refuserid,"authority":authority
       };
       $.ajax({
          url : "${path}/admin/changelevel.do",
          data : value,
          dataType : "text",
          type : "post",
          success : function(data) {
             $("#"+refuserid).html(authority);
          }
       });
    });
 });

	onload = function() {

	var auth = 'ROLE_BLACK,ROLE_BABY,ROLE_MANAGER,ROLE_ADMIN'.split(',');

	  var data = [];

	  // DB상에 입력된 데이터를 호출후 User가 볼수있는 View에서 데이터를 뿌려준다
	 <c:forEach var="row" items="${map.list}" varStatus="status">
	 	var i = 0;
		if('${row.authority}' == 'ROLE_BLACK') {}
		else if('${row.authority}' == 'ROLE_BABY') {i = 1}
		else if('${row.authority}' == 'ROLE_MEMBER') {i = 2}
		else if('${row.authority}' == 'ROLE_MANAGER') {i = 3}
		else if('${row.authority}' == 'ROLE_ADMIN') {i = 4}

	  	data.push({
	  		userid: "${row.userid}",
	  		name: "${row.name}",
	  		email: "${row.email}",
	  		active: "게시글 수 : ${row.board} 댓글 수 : ${row.rply}",
	  		authority: '${row.authority}',
	  		joinDate: "<fmt:formatDate value='${row.joinDate}' pattern='yyyy-MM-dd HH:mm:ss' />",
	  		aut: i
	  	});
	  </c:forEach>
		
	  // create a CollectionView with 5 items per page
	  var view = new wijmo.collections.CollectionView(data, {
	  	pageSize: 5,
	    pageChanged: updateCurrentPage
	  });

	  var theTemplate = document.getElementById('theTemplate');

	  // 헤더 컬럼 설정
	  var theGrid = new wijmo.grid.FlexGrid('#theGrid', {
		  	autoGenerateColumns: false,
		    columns: [
		    	{ binding: 'userid', header: '아이디', isReadOnly: true},
		    	{ binding: 'name', header: '이름', isReadOnly: true},
		    	{ binding: 'email', header: '이메일', isReadOnly: true},
		    	{ binding: 'active', header: '활동내역', isReadOnly: true},
		    	{ binding: 'authority', header: '권한', dataMap: auth},
		    	{ binding: 'joinDate', header: '날짜', isReadOnly: true},
		    	{ binding: 'levelUp', header: '등업'},
		    ],
		    itemsSource: data
	  });
	  var toFilter;
	  var filteringInput = document.getElementById('filteringInput');

	  // grid 초기화
	  theGrid.initialize({
	    itemsSource: view
	  });

	  // 버튼 생성, 버튼 클릭시 전송될 데이터 세팅
	  theGrid.formatItem.addHandler(function(s, e) {
          if (e.panel == s.cells) {
            var col = s.columns[e.col],
                 item = s.rows[e.row].dataItem;
			
              // create buttons for items not being edited
              switch (col.binding) {
                case 'levelUp':
                     e.cell.innerHTML = document.getElementById('theTemplate').innerHTML;
                     e.cell.dataItem = item;
                     break;
              }
          }
      });

	  // 세팅된 데이터 Ajax와 컨트롤러에 전송
	  theGrid.addEventListener(theGrid.hostElement, 'click', function(e) {
		  	if (e.target instanceof HTMLButtonElement && e.target.name) {
			  	
		    	var item = wijmo.closest(e.target, '.wj-cell').dataItem;
		    	var refuserid = item.userid;
		    	var authority = item.authority;
				var value = {
					"refuserid" : refuserid,"authority":authority
				};
				$.ajax({
					url : "${path}/admin/changelevel.do",
					data : value,
					dataType : "text",
					type : "post",
					success : function(data) {
						changeresult("1");
					}
				});
				function changeresult(num){
					$.ajax({
						type:"post",
						url:"${path}/admin/level.do",
						success:function(result){
							
							}
						});
					}
		    }
	  });
	  
	  // 사용자가 입력할때 필터
	  filteringInput.addEventListener('input', filterGrid);

	  // define the filter function for the collection view.
	  function filterFunction(item) {
	    var filter = filteringInput.value.toLowerCase();
	    if (!filter) {
	      return true;
	    }

	    return item.userid.toLowerCase().indexOf(filter) > -1;
	  }

	  // apply filter (applied on a 500 ms timeOut)
	  function filterGrid() {
	    if (toFilter) {
	      clearTimeout(toFilter);
	    }

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
	  
	  
	  // update pager status
	  view.onPageChanged();
	  function updateCurrentPage() {
	  	var curr = wijmo.format('Page {index:n0} of {cnt:n0}', {
	    	index: view.pageIndex + 1,
	      cnt: view.pageCount
	    });
	  	document.getElementById('spanCurrent').textContent = curr;
	  }
	  
	  // implement pager
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
	  theGrid.autoSizeColumns();
	}

</script>
<style>
.wj-flexgrid {  
  max-height: 250px; 
  margin:10px 0;
}

/* 사용자 정의 Wijmo Style */
/* Material Style */

.wj-content {
    border-radius: 4px;
    background-color: #ffffff;
    color: #333333;
}
.wj-header {
    background-color: #3e50b4;
    color: #ffffff;
}
.wj-state-selected {
    background-color: #ff3f80;
    color: #ffffff;
}
.wj-state-multi-selected {
    background-color: #f2a9c2;
    color: #ffffff;
}                    
.wj-tooltip {
    background-color: #ffffe5;
    color: #333333;
}
.wj-cell:not(.wj-header):not(.wj-group):not(.wj-alt):not(.wj-state-selected):not(.wj-state-multi-selected) {
    background-color: #ffffff;
    color: #333333;
}
.wj-alt:not(.wj-header):not(.wj-group):not(.wj-state-selected):not(.wj-state-multi-selected) {
    background-color: #f9f9f9;
    color: #333333;
}
</style>
</head>
<body>
	<%@ include file="../include/menuAdmin.jsp"%>


<h2>등급관리</h2>

<!-- 검색창 -->
<div class="row-fluid well">
  아이디 검색 : <input id="filteringInput" type="text" class="form-control app-pad" />
</div>

<div class="container">
    <div class="jumbotron">
    
	  <!-- DB에 입력된 데이터를 입력할 div -->    
	  <div id="theGrid"></div>
	  
	  <!-- 등업창 생성 -->
	  <div id="theTemplate" style="display:none">
	    <button name="{aut}" id="change">
	      등업
	    </button>
	  </div>
	  
	  <!-- 페이징 -->
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



















