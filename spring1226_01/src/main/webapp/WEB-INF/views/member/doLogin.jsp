<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>doLogin</title>
		</head>
		<body>
		<script>
		if(${result}==1){
			alert("로그인 됨");
			location.href="/";
			
		}else{
			alert("로그인 안됨 확인 바람");
			location.href="login";
		}
		</script>
	
		</body>
</html>