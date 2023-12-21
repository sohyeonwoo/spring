<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>Title</title>



</head>
<body>
<script>
	if(${result}==1){
		alert("올 ㅋㅋㅋ 러그인됐네 ?.");
	
		location.href="/";
		
	}else{
		alert("로그인 실패다 바보야야야야");
		
		location.href="login";
	}
</script>

</body>
</html>