<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>뷰페이지</title>
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="../css/read.css">
</head>
<body>
<section>
    <h1>NOTICE</h1>
	<form type="" method="post" id="bFrm" name="bFrm" action="">
	<input type="hidden" name="bno" value="${map.bdto.bno}">
	<input type="hidden" name="bno" value="${bdto.bno }">
	<input type="hidden" name="btitle" value="${bdto.btitle }">
	<input type="hidden" name="bcontent" value="${bdto.bcontent }">
	<input type="hidden" name="id" value="${bdto.id }">
	<input type="hidden" name="bhit" value="${bdto.bhit }">
	<input type="hidden" name="bfile" value="${bdto.bfile }">
    <table>
      <colgroup>
        <col width="15%">
        <col width="50%">
        <col width="15%">
        <col width="20%">
        
      </colgroup>
      <tr>
      <th>글번호</th>
        <th colspan="3">${map.bdto.bno }</th>
      </tr>
      <tr>
      <td><strong>제목</strong></td>
        <td colspan="3">${map.bdto.btitle }</td>
      </tr>
      <tr>
        <td><strong>작성자</strong></td>
        <td>${map.bdto.id }</td>
        <td><strong>조회수</strong></td>
        <td>${map.bdto.bhit }</td>
      </tr>
      <tr>
        <td colspan="4" class="article">${map.bdto.bcontent }</td>
      </tr>
      <tr>
      	<td><strong>파일이름</strong></td>
        <td colspan="3">${map.bdto.bfile}</td>
      </tr>
          <tr>
      <td><strong>파일</strong></td>
        <td colspan="3"><img src="/upload/${map.bdto.bfile }"></td>
      </tr>
      <tr>
      <c:if test="${map.nextdto.bno != null }">
      <tr>
      <td><strong>NEXT</strong></td>
        <td colspan="3"><a href="bView?bno=${map.nextdto.bno }"> ${map.nextdto.btitle }</a></td>
      </tr>
	 </c:if> 
	      
      <c:if test="${map.nextdto.bno == null }">
      <tr>
      <td><strong>NEXT</strong></td>
        <td colspan="3"> 다음글이 없음 </td>
      </tr>
	 </c:if>      
      
      <c:if test="${map.prevdto.bno!=null }">
      <tr>
      <td><strong>PREV</strong></td>
        <td colspan="3"><a href="bView?bno=${map.prevdto.bno }">${map.prevdto.btitle }</a></td>
      </tr>
      </c:if>
      
      <c:if test="${map.prevdto.bno==null }">
      <tr>
      <td><strong>PREV</strong></td>
        <td colspan="3">이전글이 존재 하지 않습니다</td>
      </tr>
      </c:if>
      
    </table>
    <script>
    $(function(){
    	//수정페이지 이동
    	$("#updateBtn").click(function(){
    	alert("수정페이지 이동")	;
    	$("#bFrm").attr("action","bUpdate").submit();
    	});
    	//삭제페이지 이동
    	$(".delBtn").click(function(){
    		if(confirm("게시글을 삭제해 ?")){
    		$("#bFrm").attr("action","bDelete").submit();
    		}
    	});
    	//답변달기 페이지 이동
    	$(".replyBtn").click(function(){
    		alert("답변달기 페이지 이동")
    		$("#bFrm").attr("action","bReply").submit();
    	});
    	
    }); //function
    
    </script>
	

    <a href="bList"><div class="list">목록</div></a>
    <div class="list delBtn" style="cursor: pointer;">삭제</div>
    <div class="list updateBtn" >수정</div>
    <c:if test="${session_id == map.bdto.id}">
    </c:if>
    <div class="list replyBtn" style="cursor: pointer;">답변달기</div>
    <c:if test="${session_id!=null }">
    </c:if>
    </form>
  </section>
</body>
</html>