<%@page import="kh.s0.myboard.board.model.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="<%=request.getContextPath()%>/css/reset.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
</head>
<body>
<%-- JSP주석

<%! 선언 %>  -> 변수선언O, 함수선언O

<%= 표현식 %>  -> 문자열, 변수, 함수리턴값 출력, (클라이언트에 출력할 내용), ; 사용하면 안됨.

<% 스크립틀릿 %> -> java 문법 그대로 사용 가능한 영역, 변수선언X, 함수선언O

--%> 

<%= request.getAttribute("aaa") %>
<br>
<button class="btn boardwrite">글등록</button>
<script>
	$(".btn.boardwrite").on("click", function (){
		location.href = "<%=request.getContextPath()%>/board/write";
	});
</script>


	<h1>게시글목록</h1>
	
<%
// 전혀 무관한 학습용 코드!!
// JSP의 내장 객체
//out.append("aaa"); // response.getWriter(); // JSP out 객체
//session.getAttribute("aaa"); // request.getSession(); // JSP session 객체

	List<BoardVo> volist = (List<BoardVo>)request.getAttribute("aaa"); // 형변환.
	
	if (volist == null) { 
		// 게시글이 없는 경우
%>
	<p>게시글이 없습니다.</p>
<%		
	} else { 
		// 게시글이 있는 경우
%>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
<%		
		// tr이 volist.size()만큼 반복
		for(int i=0; i<volist.size(); i++) {
%>			
			<tr>
				<td><%= volist.get(i).getBno() %></td>
				<td><%= volist.get(i).getBtitle() %></td>
				<td><%= volist.get(i).getBwriter() %></td>
				<td><%= volist.get(i).getBdate() %></td>
				<td><%= volist.get(i).getBcnt() %></td>
			</tr>			
<%			
		}
%>
	</table>
<%
	}
%>


</body>
</html>