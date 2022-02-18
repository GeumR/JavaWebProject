<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
	
<div class = "left">

		
		<div class="header">
			<h1 text-align="center">게시물 작성</h1>
		</div><br>
		
		<input type="button" value="추가하기" onclick="javascript:createDiv()"/>
		<form id="searchFrm" name="searchFrm" method="post" >
			<div id="bbsDivTest" name="bbsDivTest">
				<input id="testid" type="text" name='testid' value="1">
		  &nbsp;제목 : <input id="title" type="text" name="title[]">
	&nbsp;&nbsp;글쓴이 : <input id="writer" type="text" name="writer[]">
	&nbsp;&nbsp;내용 : <input id="cn" type="text" name="cn[]">
				<input id="testTitle" type="hidden" name="testTitle">
				<input id="testWrite" type="hidden" name="testWrite">
				<input id="testCn" type="hidden" name="testCn">
			</div>
			<sec:csrfInput />
		</form>
		
		<a href="javascript:bbsInsert('title[]','writer[]','cn[]');"><input type="button" value="작성"/></a><br>
</div>

<script type="text/javascript">

	
/*함수 작업 하는 곳!!! 주석 지우지 말어 ==== 시작*/
	//와? 나 아무것도 안했는딩? 마우스 움직이길래 무슨 일 있는줄ㅋㅋㅋㅋㅋㅋ
	var testid = 1;
	function createDiv(){
		testid++;
		$("#bbsDivTest").append("<br><input id='testid' type='text' name='testid' value="+testid+">");
		$("#bbsDivTest").append("&nbsp; 제목 : <input id='title' type='text' name='title[]'>");
		$("#bbsDivTest").append("&nbsp; &nbsp;글쓴이 : <input id='writer' type='text' name='writer[]'>");
		$("#bbsDivTest").append("&nbsp; &nbsp;내용 : <input id='cn' type='text' name='cn[]'>");
	}
	
	function bbsInsert(title,write,cn){ /* 신규 게시글 저장 함수 */
		var i, sum=0, tag1=[], tag2=[], tag3=[], str1="", str2="", str3="";
	    var ti = document.getElementsByName(title);
	    var wr = document.getElementsByName(write);
	    var cn = document.getElementsByName(cn);
	    var tot = ti.length;
	    for (i = 0; i < tot; i++) {
            tag1[sum] = ti[i].value;
            tag2[sum] = wr[i].value;
            tag3[sum] = cn[i].value;            
            sum++;
			//alert(sum);
	    }
		var a = $('#title').val();
		var b = $('#writer').val();
		var c = $('#cn').val();
		var inConfirm = confirm('당신이 작성한 파일이 저장됩니다.');
		if(inConfirm){
			if(a.length ==0){
				alert('제목 입력');			
				return false;
			}else if(b.length ==0){
				alert('글쓴이 입력');
				return false;
			}else if(c.length ==0){
				alert('내용 입력');
				return false;
			}else{
				//join이 뭐여? 몰러? 나도 ㅋㅋㅋ 함 찾아봐	
				str1 += tag1.join(',');
				str2 += tag2.join(',');
				str3 += tag3.join(',');
// 			    alert(str1);
// 			    alert(str2);
// 			    alert(str3);
			    $('#testTitle').val(str1); 
			    $('#testWrite').val(str2);
			    $('#testCn').val(str3);
				$.ajax({
			         type: 'post' 
			        ,data: $("#searchFrm").serialize()
			        ,url: '/system/multiInput.do'
		    	    ,beforeSend : function(xhr){
						xhr.setRequestHeader(header, token);
			        }
			        ,dataType:'json' 
			        ,error: function(data, status, err){
						alert("err")
			        }
			        ,success: function(jsonData){
						alert('저장되었습니다.');
 			      		$("#searchFrm").attr("action", "/system/goldBbsPage.do");
 			           	$("#searchFrm").submit();
// 			        	location.replace(location.href);
			        }
				});
			}
		}else{
			alert('저장이 안 되었습니다.');
		}
		
	}
/*함수 작업 하는 곳!!! 주석 지우지 말어 ==== 끝*/
</script>
