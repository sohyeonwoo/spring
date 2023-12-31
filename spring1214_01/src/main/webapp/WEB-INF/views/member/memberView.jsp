<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보보기</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<style>
		   *{margin:0; padding:0;}
		   div{width:1000px; margin:30px auto; text-align: center;}
		   h1{margin-bottom:30px; }
		   table{width:100%;}
		   table,th,td{border:1px solid black; border-collapse: collapse;
		   font-size: 16px; }
		   th{height:40px; }
		   img{width:80%;}
		   button{width:150px; height:40px; margin-top:30px; }
		</style>
		<script>
		$(function(){
			$("#fbtn").click(function(){
				upDate.submit();
			})
				
			
		})
		  
		</script>
	</head>
	<body>
	  
	  <div>
	   <h1>회원정보보기</h1>
		   <table>
		   <form name="upDate" method="post" action="mUpdate">
		     <colgroup>
		       <col width="20%">
		       <col width="80%">
		     </colgroup>
		     <tr>
		       <th>아이디</th>
		       <td><input type="text" name="id" value="${mdto.id }" readonly="readonly"> </td>
		     </tr>
		     <tr>
		       <th>패스워드</th>
		       <td><input type="text" name="pw" value="${mdto.pw }" readonly="readonly"></td>
		     </tr>
		     <tr>
		       <th>이름</th>
		       <td><input type="text" name="name" value="${mdto.name }" readonly="readonly"></td>
		     </tr>
		     <tr>
		       <th>전화번호</th>
		       <td><input type="text" name="phone" value="${mdto.phone }" readonly="readonly"></td>
		     </tr>
		     <tr>
		       <th>성별</th>
		       <td><input type="text" name="gender" value="${mdto.gender }" readonly="readonly"></td>
		     </tr>
		     <tr>
		       <th>취미</th>
		       <td><input type="text" name="hobby" value="${mdto.hobby }" readonly="readonly"></td>
		     </tr>
		     </form>
		   </table>
		   <a><button type="button" id="fbtn">회원정보수정</button></a>
		   <a href=""><button type="button" id="fbtn">회원삭제</button></a>
		   <a href="/"><button type="button">메인페이지</button></a>
	  </div>
	
	</body>
</html>