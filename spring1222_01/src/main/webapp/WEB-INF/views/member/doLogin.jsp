<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
if(${session_id!=null}){
	alert("ㅊㅋㅊㅋ");
	location.href="/";
	
}else{
	alert("바부 다시 ㄱ");
	location.href="login";
}
</script>

</body>
</html>