<%@page import="kh.s0.myboard.member.model.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<%-- JSP주석

<%! 선언 %>  -> 변수선언O, 함수선언O

<%= 표현식 %>  -> 문자열, 변수, 함수리턴값 출력, (클라이언트에 출력할 내용), ; 사용하면 안됨.

<% 스크립틀릿 %> -> java 문법 그대로 사용 가능한 영역, 변수선언X, 함수선언O

--%> 

<%= "JSP 표현식" %> <br>
${"EL 표현언어"} <br>
<% out.println("스크립틀릿"); %> <br>
그냥 HTML <br>
<hr>
<% if(session.getAttribute("loginSsInfo") != null) { %>
<%= ((MemberVo)session.getAttribute("loginSsInfo")).getMname() %> <br> <!-- null일 시 : 화면에 null 출력 -->
<% } %>
${loginSsInfo.mname} <br> <!-- null일 시 : 화면에 아무것도 출력 X -->
<% out.println(session.getAttribute("loginSsInfo")); %> <br> <!-- null일 시 : 화면에 null 출력 -->

<% int a = 50; %>
<%= a %> <!-- 표현식에선 표현되고 -->
${a} <!-- EL에선 안됨  -->

	<h1>로그인</h1>
	<form action="<%= request.getContextPath() %>/login.do" method="post">
		<fieldset>
			<legend>로그인</legend>
			id: <input type="text" name="mid"> <!-- name은 db의 컬럼명으로 하는게 좋음 -->
			<br>
			pwd: <input type="password" name="mpwd">
			<br>
			<button type="submit">로그인</button>
		</fieldset>
	</form>
</body>
</html>