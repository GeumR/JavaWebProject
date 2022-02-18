<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
	
<div class = "left">
		
		<div class="header">
			<h1 text-align="center">게시물 댓글</h1>
		</div>
		
		<form id="searchFrm" name="searchFrm" method="post" >
			<input id="bbsId" type="hidden" name="bbsId">
			<input id="bbsLevel" type="hidden" name="bbsLevel"><br>
			제목 : <input id="title" type="text" name="title"><br>
			글쓴이 : <input id="writer" type="text" name="writer"><br>
			내용 : <input id="cn" type="text" name="cn"><br>
			
			<input type="button" value="댓글작성" onclick="javascript:bbsComment()"><br>
			<sec:csrfInput />
		</form>
		
</div>

<script type="text/javascript">
	
/*함수 작업 하는 곳!!! 주석 지우지 말어 ==== 시작*/
	$(document).ready(function() {
		// 현재 페이지(http://localhost:5050/system/goldBbsDetail.do?bbsId=10000)를 URL에 입력
		var url = new URL(window.location.href);
		// URLSearchParams 객체
		var urlParams = url.searchParams;
		// URLSearchParams.get()
		var a = urlParams.get('bbsId');
		var b = urlParams.get('bbsLevel');
		inserthidden(a,b);
	});
	
	function inserthidden(a,b){   //아이다 값이랑 레벨 값 가져오는 함수
		var bbsId = a;
		var bbsLevel = b;
		$('#bbsId').val(bbsId);
		$('#bbsLevel').val(bbsLevel);
	}


	function bbsComment(){ /* 신규 게시글 저장 함수 */
		var a = $('#title').val();
		var b = $('#writer').val();
		var c = $('#cn').val();
		var inConfirm = confirm('당신이 작성한 파일이 저장됩니다.');
		
		if(inConfirm){
			if(a.length ==0){
				alert('제목 입력')			
			}else if(b.length ==0){
				alert('글쓴이 입력')
			}else if(c.length ==0){
				alert('내용 입력')
			}else{
				$.ajax({
			         type: 'post' 
			        ,data: $("#searchFrm").serialize()
			        ,url: '/system/bbsComment.do'
		    	    ,beforeSend : function(xhr){
						xhr.setRequestHeader(header, token);
			        }
			        ,dataType:'json' 
			        ,error: function(data, status, err){
						alert("err")
			        }
			        ,success: function(jsonData){
			      		$("#searchFrm").attr("action", "/system/goldBbsPage.do");
			           	$("#searchFrm").submit();
			        }
				});
			}
			alert('댓글이 작성되었습니다.');
		}else{
			alert('댓글이 작성되지 않았습니다.');
		}
		
	}
/*함수 작업 하는 곳!!! 주석 지우지 말어 ==== 끝*/
</script>
