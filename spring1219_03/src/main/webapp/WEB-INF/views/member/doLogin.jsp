<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<script>
if(${session_id!=null}){
alert("로그인됨");
location.href="../index";
} else{
	alert("틀림");
	location.href="login";
}
</script>

</body>
</html>