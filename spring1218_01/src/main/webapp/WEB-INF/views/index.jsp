<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화몀ㄴ</title>
</head>
<body>
<h1>1218 아침수업 index 페이지 입니다</h1>
<ul>
<c:if test="${session_id==null }">
<li><strong style="font-size:20px">로그인을 해주세요</strong></li>
<a href="member/login"><li>로그인</li></a>
</c:if>
<c:if test="${session_id!=null }">
<li><strong style="font-size:20px">${session_name }님 환영</strong></li>
<a href="member/logout"><li>로그아웃</li></a>
</c:if>

<a href="board/bList"><li>공지사항리스트(bList)</li><a>
<a href="mInsert"><li>회원가입</li><a>
<a href="boardBno/15/100"><li>글번호</li><a>
<a href="bInsert"><li>글쓰기(bInsert)</li><a>
<a href="board/bView"><li>공지사항보기(noticeView)</li><a>
</ul>
</body>
</html>