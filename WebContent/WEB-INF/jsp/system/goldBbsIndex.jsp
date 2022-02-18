<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<div class = "left">
	<html>
		<head>
			<meta charset="EUC-KR">
				<title>초간단 테이블</title>
		</head>
		
		<div class="header", text-align="center">
			<h1 class="drgH1">룡게시판</h1>
<%-- 			<br>총 게시물 수 : ${InFoNum} --%>
<%-- 			<br>한 페이지당 게시물 수 : ${BoardCnt}  --%>
<%-- 			<br>총 페이지 수 : ${TotalPage} --%>
<%-- 			<br>현재 페이지 : ${nowPage} --%>
<%-- 			<br>시작 페이지 : ${startPage} --%>
<%-- 			<br>끝 페이지 : ${endPage} --%>
		</div>
		
		<a href="javascript:sub_all('chk[]');"><input type="button" value="제출처리"/></a><br>
		<body>
			<table class ="gold">
				<tr>
					<th><input type="checkbox" name="ch_all" id="ch_all" value="" onclick="CheckAll();"></th>
					<th>ID</th>
					<th>제출상태</th>					
					<th>제목</th>
					<th>내용</th>
					<th>글쓴이</th>
					<th>작성일자</th>
<!-- 					<th>수정일자</th> -->
					<th></th>
			    </tr>
				<c:forEach items="${InFo}" var="Info1">
					<tr>
						<td style="text-align:center;"><input type="checkbox" name='chk[]' value=${Info1.bbsId}></td>
						<td><a href="/system/goldBbsDetail.do?bbsId=${Info1.bbsId}" >${Info1.bbsId}</a></td>						
						<td>${Info1.bbsProgrsStatuss}</td>
						<td>${Info1.bbsTitles}</td>
						<td>${Info1.bbsCn}</td>
						<td>${Info1.bbsWriter}</td>
						<td>${Info1.bbsWriteDt}</td>
<%-- 						<td>${Info1.bbsUpdateDt}</td> --%>
						<td><a href="javascript:openWindowPop('/system/detailPopup.do?bbsId=${Info1.bbsId}&bbsLevel=${Info1.bbsLevel}', '상세정보');">상세정보</a></td>
					</tr>
				</c:forEach>
			</table>	
		</body>
		
		
		
			<ul class="ulStluy1">
				<c:if test="${nowPage != 1}">
					<a class="box" href="/system/goldBbsPage.do?nowPage=1"><<</a>
				</c:if>
				<c:if test="${nowPage > 1}">
					<a class="box" href="/system/goldBbsPage.do?nowPage=${nowPage-1}"><</a>
				</c:if>
				<c:forEach begin="${startPage}" end="${endPage}" var="idx">
<!-- 				현재 페이지 표시 (현재페이지랑 번호가 같으면 파란색으로 표시)           -->
					<c:if test="${nowPage == idx}"> 
						<a class="pageLiSty">${idx}</a>
					</c:if>
					<c:if test="${nowPage != idx}">
						<a class="pageStluy1" href="/system/goldBbsPage.do?nowPage=${idx}">${idx}</a>
					</c:if>
				</c:forEach>
				<c:if test="${TotalPage != endPage}">
					<a class="box" href="/system/goldBbsPage.do?nowPage=${endPage+1}">></a>
				</c:if>
				<c:if test="${TotalPage != endPage}">
					<a class="box" href="/system/goldBbsPage.do?nowPage=${TotalPage}">	>></a>
				</c:if>
			</ul>
			
<%-- 			<c:if test="${InFoNum == 4}"> --%>
<!-- 				<br>4개요 -->
<%-- 			</c:if> --%>
			
<%-- 			<c:if test="${InFoNum != 4}"> --%>
<!-- 				<br>4개 아니요 -->
<%-- 			</c:if> --%>
			
		<br><br><br>
		<a href="javascript:del_all('chk[]');"><input type="button" value="일괄삭제"/></a><br>
		<a href="/system/mulInputPage.do"><input type="button" value="다중작성"></a>
		<a href="/system/InputPage.do"><input type="button" value="신규작성"/></a>
		
	
		<form id="searchFrm" name="searchFrm" method="post">
			<input id="bbsId" type="hidden" name="bbsId">
			<input id="bbsIdStr" type="hidden" name="bbsIdStr"><br>
<%-- 			<input id="nowPage" type="text" name="nowPage" value="${nowPage}"><br> --%>
			<sec:csrfInput />
		</form>
	</html>
</div>

<script type="text/javascript">
	
	/*함수 작업 하는 곳!!! 주석 지우지 말어 ==== 시작*/
	/*화면 로딩 전 실행함수=== 시작*/
	$(document).ready(function() {
// 		var url = new URL(window.location.href); // 현재 페이지(http://localhost:5050/system/goldBbsDetail.do?bbsId=10000)를 URL에 입력
// 		var urlParams = url.searchParams;		 // URLSearchParams()     URLSearchParams 객체 인스턴스를 반환합니다.
// 		var a = urlParams.get('bbsId');			 // URLSearchParams.get() 주어진 검색 매개변수에 연결된 첫 번째 값을 반환합니다.
// 		selectDetailBbs(a); //자바 스크립트 형식
	});
	
	function openWindowPop(url, name){
    	var options = 'top=100, left=100, width=1000, height=600, status=no, menubar=no, toolbar=no, resizable=no';
    	window.open(url, name, options);
	}
	

	function selectDetailBbs(data){
		var TESId = data;
		//그래서 아까 넘어온 a값을 그냥 여기서는 data 라는 변수로 설정해서 받은거야 자바로 표현하면 (int data = a)요런식? 쨌든 지금 값은 a로 해서 넘겼지만
		//여기서 중요한거는 이름이 아니라 값이닌깜 10000이라는 값은 다시 data라는 이름으로 변경해서 넣은거야 int data = 10000;이렇게
		//그래서 그 data값을 다시 bbsId라른 이름으로 넣어주고 그거를 ajax에서 data부분에 넣어주는거지
		//여기서 포인트는 data에 스트링 부분있지? 어어 요 이름으로 sql에 넘어가게 되는거임 그닌깐 { 이름 : 값 }이건거지 그래서 지금 둘다 bbsID여서 햇갈리면
		//요렇게 해도 넘어가때는 bbsId라고 이름을 설정했기 때문에 아까 우리가 sql에 요 이름으로 넘어가게 된거야
		//자바에서 로그를 찍어본거는 reqmap이 jsp -> sql로 넘어가는 값
		//map이 sql-> jsp로 넘겨받은 값 이해가 어느정도 됨??? 기달 한 5분 동안 이해해봐
		$.ajax({
	         type: 'post' 
	        ,data: {'bbsId' : TESId}/*$("#searchFrm").serialize()*/
	        ,url: '/system/selectBbsDetil.do'
   	    ,beforeSend : function(xhr){
				xhr.setRequestHeader(header, token);
	        }
	        ,dataType:'json' 
	        ,error: function(data, status, err){
				alert("err");
	        }
	        ,success: function(jsonData){
	        	//불러온 데이터를 넣어줘야된다	        	
	        	$('#bbsId').val(jsonData.result[0].bbsId);
	        	$('#title').val(jsonData.result[0].bbsTitle);
	        	$('#writer').val(jsonData.result[0].bbsWriter);
	        	$('#cn').val(jsonData.result[0].bbsCn);
	        }
		});
	}

	function del_all(obj){
	    var i, sum=0, tag=[], str="";
	    var chk = document.getElementsByName(obj);
	    var tot = chk.length;
	    for (i=0; i < tot; i++) {
	        if (chk[i].checked == true) {
	            tag[sum] = chk[i].value;
	            sum++;
				//alert(sum);
	        }
	    }
	    if(tag.length == 0){
	    	alert("선택된 글이 없습니다.");
	    }else if(tag.length > 0) {
		    //str += "선택갯수 : "+sum;
	    	str += tag.join(",");
		    alert(str);
		    $('#bbsIdStr').val(str); 
		    //먼저 여기는 데이터를 ajax로 통신하면 form을 넘기지?/searchFrm이거?/이해?/ㅇㅇ
		    //오키 그래서 저기 form 데이터를 컨트롤러로 넘기는데 너가 지금 str이라고 해서 선택한 bbsId를 쭉이어서 만들었지?/10000,10001으로 만듦
		    //그치 그래서 이제 그거 만든거를 컨트롤러로 넘겨줘야 거기서 저걸로 멀 하든 말든 할거 아니여? 어어 그래서 serchFrm에 bbsIdStr를 만들어서
		    //그 만든곳에 너가 만든 $('#bbsIdStr').val(str); str을 넣어준거영 그러면 bbsIdstr에 1000,2000머 이런식으로 값을 가지고 있을거 아니여? 
		    //처리는 히든으로 해서 보이지는 않지만?/어어
			//그러고 나서 JSP에서는 그 BBSiD정보를 일단 FORM에 담아서 넘겨줬으닌ㄴ깐 여기서 할거는 끝인거영 일단 여기까지 이해?/한번 훑고 옴/이해ON
			//이해했다는거지?/얍얍/
		    //window.location.href="url";
		    var delConfirm = confirm('당신의 파일이 삭제됩니다');
			if(delConfirm){
				$.ajax({
			         type: 'post' 
			        ,data: $("#searchFrm").serialize()
			        ,url: '/system/AllDeleteBbs.do'
		    	    ,beforeSend : function(xhr){
						xhr.setRequestHeader(header, token);
			        }
			        ,dataType:'json' 
			        ,error: function(data, status, err){
						alert("err")
			        }
			        ,success: function(jsonData){
// 			      		$("#searchFrm").attr("action", "/system/goldBbsPage.do");
// 			           	$("#searchFrm").submit();
						location.replace(location.href);
			        }
				});
				alert('삭제되었습니다.');
			}else{
				alert('삭제가 취소되었습니다.');
			}	
	    }

	}
	
	
	function sub_all(obj){
	    var i, sum=0, tag=[], str="";
	    var chk = document.getElementsByName(obj);
	    var tot = chk.length;
	    for (i=0; i < tot; i++) {
	        if (chk[i].checked == true) {
	            tag[sum] = chk[i].value;
	            sum++;
				//alert(sum);
	        }
	    }
	    if(tag.length == 0){
	    	alert("선택된 글이 없습니다.");
	    }else if(tag.length > 0) {
		    //str += "선택갯수 : "+sum;
	    	str += tag.join(",");
// 		    alert(str);
		    $('#bbsIdStr').val(str);
		    var delConfirm = confirm('당신의 파일이 제출됩니다.');
			if(delConfirm){
				$.ajax({
			         type: 'post' 
			        ,data: $("#searchFrm").serialize()
			        ,url: '/system/submit.do'
		    	    ,beforeSend : function(xhr){
						xhr.setRequestHeader(header, token);
			        }
			        ,dataType:'json' 
			        ,error: function(data, status, err){
						alert("err")
			        }
			        ,success: function(jsonData){
// 			      		$("#searchFrm").attr("action", "/system/goldBbsPage.do");
// 			           	$("#searchFrm").submit();
						location.replace(location.href);
			        }
				});
				alert('제출되었습니다.');
			}else{
				alert('제출되지 않았습니다.');
			}	
	    }

	}

	var check = false; 
	function CheckAll(){ 
		var chk = document.getElementsByName("chk[]"); 
		if(check == false){ 
			check = true; 
			for(var i=0; i<chk.length;i++){ 
				chk[i].checked = true;//모두 체크 
				document.getElementById("ch_all").checked = true;

			} //for
		}else{ 
			check = false; 
			for(var i=0; i<chk.length;i++){ 
				chk[i].checked = false;//모두 해제 
				document.getElementById("ch_all").checked = false;
			}//for
		}

	/*
	// jquery 버전 특정 체크박스 체크하기/풀기
	$("#checkbox").prop("checked", true); //id 값으로
	$("#checkbox").prop("checked", false); //id 값으로 
	*/

	}


/*함수 작업 하는 곳!!! 주석 지우지 말어 ==== 끝*/
</script>
