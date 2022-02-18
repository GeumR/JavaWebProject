<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
	
<div class = "left">
	<div class="header">
		<h2>게시물</h2>
	</div>
	<table class="detail">
		<form id="searchFrm" name="searchFrm" method="post" >
			<input id="bbsId" type="hidden" name="bbsId"><br>
			<tr> <th>제목</th>  <td><input class="det_text" id="title" type="text" name="title"><br></td></tr>
			<tr> <th>내용</th>  <td><input class="det_text" style="height: 400px; Response : No Trackback , 2 Comments;" id="cn" type="text" name="cn"></td></tr>
			<tr> <th>작성자</th> <td><input class="det_text" id="writer" type="text" name="writer"></td></tr>
			<br>
			<sec:csrfInput />
		
	</table>
	<br>
	<input type="button" value="수정" onclick="javascript:bbsUpdate()">&nbsp;
	<input type="button" value="삭제" onclick="javascript:bbsDelete()">&nbsp;
	<a href="/system/goldBbsPage.do"><input type="button" value="목록"/></a>
	
		<div class="container">
		    <br><br><br><br>
		        <div>
		            <div>
		                <span><strong>Comments</strong></span><!-- Comment -->
		            </div>
		            <div>
		                <table class="tableComIuput">
		                  <tr>
		                	<td>
		                	  <textarea style="width: 1100px" rows="7" cols="30" id="commentCn" name="commentCn" placeholder="댓글을 입력하세요"></textarea><br>
		                		<div>
		                 	      <a onClick="javascript:insertComment()" class="enBtn">등록</a><br><br>
		                		</div>
		                    </td>
		                  </tr>
		                </table>
		            </div>
		        </div>      
		    </form>
		</div>
		
		<div class="container">
			<table class = "tableComSelect">
		   		<c:forEach items="${InFoCom}" var="InfoCom1">
						<th><strong>${InfoCom1.userNm}</strong></th><br>
						<tr><td>${InfoCom1.commentCn}</td></tr>
						<tr><th></th></tr>
				</c:forEach>
	    	</table>
		</div>
</div>

<script type="text/javascript">

	/*화면 로딩 전 실행함수=== 시작*/
	$(document).ready(function() {
		var url = new URL(window.location.href); // 현재 페이지(http://localhost:5050/system/goldBbsDetail.do?bbsId=10000)를 URL에 입력
		var urlParams = url.searchParams;		 // URLSearchParams()     URLSearchParams 객체 인스턴스를 반환합니다.
		var a = urlParams.get('bbsId');			 // URLSearchParams.get() 주어진 검색 매개변수에 연결된 첫 번째 값을 반환합니다.
		selectDetailBbs(a);						 //자바 스크립트 형식
	});
	
	/*
	 * 댓글 등록하기(Ajax)
	 */
	function insertComment(){
		var a = $('#commentCn').val();
		var inConfirm = confirm('당신이 작성한 댓글이 저장됩니다.');
		
		if(inConfirm){
			if(a.length ==0){
				alert('댓글내용 입력바람');
			}else{
				$.ajax({
			         type: 'post' 
			        ,data: $("#searchFrm").serialize()
			        ,url: '/system/insertDetailComment.do'
		    	    ,beforeSend : function(xhr){
						xhr.setRequestHeader(header, token);
			        }
			        ,dataType:'json' 
			        ,error: function(data, status, err){
						alert("err")
			        }
			        ,success: function(jsonData){
//				      	$("#searchFrm").attr("action", "/system/goldBbsPage.do");
//				        $("#searchFrm").submit();
						location.replace(location.href);
			        }
				});
			}
			alert('저장되었습니다.');
		}else{
			alert('저장이 안 되었습니다.');
		}
// 		$.ajax({
// 	         type: 'post' 
// 	        ,data: $("#commentForm").serialize()
// 	        ,url: '/system/insertDetailComment.do'
//    	    	,beforeSend : function(xhr){
// 				xhr.setRequestHeader(header, token);
// 	        }
// 	        ,dataType:'json' 
// 	        ,error: function(data, status, err){
// 				alert("err")
// 	        }
// 	        ,success: function(jsonData){
// //		      	$("#searchFrm").attr("action", "/system/goldBbsPage.do");
// //		        $("#searchFrm").submit();
// 				location.replace(location.href);
// 	        }
// 		}); 
	}
	
	function selectDetailBbs(data){
		var TESId = data;
				//그래서 아까 넘어온 a값을 그냥 여기서는 data 라는 변수로 설정해서 받은거야 자바로 표현하면 (int data = a)요런식? 쨌든 지금 값은 a로 해서 넘겼지만
				//여기서 중요한거는 이름이 아니라 값이닌깜 10000이라는 값은 다시 data라는 이름으로 변경해서 넣은거야 int data = 10000;이렇게
				//그래서 그 data값을 다시 bbsId라른 이름으로 넣어주고 그거를 ajax에서 data부분에 넣어주는거지
				//여기서 포인트는 data에 스트링 부분있지? 어어 요 이름으로 sql에 넘어가게 되는거임 그닌깐 { 이름 : 값 }이건거지 그래서 지금 둘다 bbsID여서 햇갈리면
				//요렇게 해도 넘어가때는 bbsId라고 이름을 설정했기 때문에 아까 우리가 sql에 요 이름으로 넘어가게 된거야
				//자바에서 로그를 찍어본거는 reqmap이 jsp -> sql로 넘어가는 값
				// map이 sql-> jsp로 넘겨받은 값 이해가 어느정도 됨??? 기달 한 5분 동안 이해해봐
		$.ajax({
	         type: 'post' 
	        ,data: {'bbsId' : TESId}/*$("#searchFrm").serialize()*/
	        ,url: '/system/selectBbsDetil.do'
   	    ,beforeSend : function(xhr){
				xhr.setRequestHeader(header, token);
	        }
	        ,dataType:'json' 
	        ,error: function(data, status, err){
				alert("err")
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
	
	function bbsUpdate(){ /* 게시글 수정 함수 */
		var a = $('#title').val();
		var b = $('#writer').val();
		var c = $('#cn').val();
		var upbtConfirm = confirm('당신의 파일이 수정됩니다');
		if(upbtConfirm){
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
			        ,url: '/system/detailInput.do'
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
			alert('수정되었습니다.');
		}else{
			alert('수정이 취소되었습니다.');
		}	
	}
	
	/*함수 작업 하는 곳!!! 주석 지우지 말어 ==== 시작*/
	function bbsDelete(){ /* 게시글 삭제 함수 */
		var upbtConfirm = confirm('당신의 파일이 삭제됩니다');
		if(upbtConfirm){
			$.ajax({
		         type: 'post' 
		        ,data: $("#searchFrm").serialize()
		        ,url: '/system/deleteBbs.do'
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
			alert('삭제되었습니다.');
		}else{
			alert('삭제가 취소되었습니다.');
		}	
	}

	
/*함수 작업 하는 곳!!! 주석 지우지 말어 ==== 끝*/
</script>
