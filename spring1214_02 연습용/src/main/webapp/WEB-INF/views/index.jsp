<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>1214 01index 페이지 입니다</h1>
<ul>
<a href="/"><li>메인화면</li></a>
<a href="/member/memberInsert"><li>회원가입</li><a>
<a href="member/login"><li>로그인</li></a>
<a href="/board/boardList"><li>공지사항리스트(noticeList)</li><a>
<a href="/board/boardView"><li>공지사항보기(noticeView)</li><a>
<a href="/board/bboardInsert"><li>공지사항글쓰기(noticeInsert)</li><a>
</ul>
</body>
</html>