<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<div class = "left">
	<html>
		<head>
			<meta charset="EUC-KR">
				<title>초간단 테이블</title>
		</head>
	
		<body>
    		<table border="1", bordercolor="blue", width ="500", height="300">
				<th>ID</th>
				<th>제목</th>
				<th>글쓴이</th>
				<c:forEach items="${InFo}" var="Info1">
					<tr>
		   				<td>${Info1.bbsId}</td>
		    			<td>${Info1.bbsTitle}</td>
		    			<th>${Info1.bbsWriter}</th>
					</tr>
				</c:forEach>
    		</table>
		</body>
	</html>
</div>

<script type="text/javascript">

</script>