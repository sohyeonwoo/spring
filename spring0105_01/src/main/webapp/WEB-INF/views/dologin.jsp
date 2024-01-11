<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>로그인</title>
		</head>
		<body>
		
	<c:if test="${session_id !=null }">
		<script>
		 alert("로그인 되었습니다.");
		 location.href="/";
		 </script>
	</c:if>
	<c:if test="${session_id ==null }">
		<script>
		 alert("패스워드 확인.");
		 location.href="login";
		 </script>
	</c:if>
		
		
	
		</body>
</html>