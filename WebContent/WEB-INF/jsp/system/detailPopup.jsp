<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
	
<div class = "left">

		<div class="header">
			<h1>게시물 상세정보 팝업</h1>
		</div>
		
		<form id="searchFrm" name="searchFrm" method="post" >
		<body>
			<h1>아이디</h1><li class="listyr2"><span class="test">${bbsId}</span></li><br><br>
			<h1>제목</h1><li class="listyr2"><span class="test">${InFo.result.bbsTitle}</span></li><br><br>
			<h1>글쓴이</h1><li class="listyr2"><span class="test">${InFo.result.bbsWriter}</span></li><br><br>
<%-- 			<input id="bbsLevel" type="hidden" name="bbsLevel" value="${InFo.result.bbsLevel}"><br> --%>
			
<%-- 			내용 : ${InFo.result.bbsCn}<br> --%>
<%-- 			테스트 : ${result.bbsTitle}<br> --%>
		</body>
			<a class="detail" href="javascript:selectValue()"}><input type="button" value="수정"/></a>
			<a class="detail" href="javascript:bbsComment()"}><input type="button" value="답글"/></a>			
			<sec:csrfInput />
		</form>
		
</div>

<script type="text/javascript">
	/*함수 작업 하는 곳!!! 주석 지우지 말어 ==== 시작*/
	
	/*화면 로딩 전 실행함수=== 시작*/
	$(document).ready(function() {
		
	});
	
	function selectValue(){
	    var theURL = "/system/goldBbsDetail.do?bbsId="+${bbsId};   // 전송 URL
	    // 호출 한 부모 페이지에서 URL 호출
	    opener.window.location = theURL;
	    // 호출 한 뒤 현재 팝업 창 닫기 이벤트
	    window.close();
	} 
	
	function bbsComment(){
	    var theURL = "/system/CommentPage.do?bbsId="+${bbsId}+"&bbsLevel="+${bbsLevel};   // 전송 URL
	    // 호출 한 부모 페이지에서 URL 호출
	    opener.window.location = theURL;
	    // 호출 한 뒤 현재 팝업 창 닫기 이벤트
	    window.close();
	} 
	
	
	/*함수 작업 하는 곳!!! 주석 지우지 말어 ==== 끝*/
</script>