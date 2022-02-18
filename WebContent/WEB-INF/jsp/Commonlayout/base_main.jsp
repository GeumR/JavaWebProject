<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Make Rich!!</title>


<sec:csrfMetaTags />
<%-- <meta name="_csrf" content="${_csrf.token}"/> --%>
<%-- <meta name="_csrf_header" content="${_csrf.headerName}"/> --%>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover, maximum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<meta charset="utf-8" />
<meta name="description" content="" />
<meta name="author" content="" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		}
	})
</script>

<!-- 반응형 웹 으로 만들기! -->
<link rel="stylesheet" href="/css/web.css" />
<link rel="stylesheet" href="/css/gold.css" />
<link rel="stylesheet" media="screen and (max-width: 480px)" href="/css/mobile.css" />
<!-- 반응형 웹 으로 만들기! -->

</head>

<body>
	<div>
		<tiles:insertAttribute name="topMenu"/>
	</div>
	
	<div class="center">
		<tiles:insertAttribute name="body"/>
	</div>
	
	<div class="bottom" style="background-color : white;">
		<tiles:insertAttribute name="hadanPage"/>
	</div>
	
</body>
</html>