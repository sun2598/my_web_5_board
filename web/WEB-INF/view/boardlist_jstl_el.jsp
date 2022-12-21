<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
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

	<!-- JSTL -> EL형태로 써야함 -->

	<!-- jsp 파일 삽입하는 법 3가지 -->
	<%@ include file="/WEB-INF/view/header.jsp"%>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<c:import url="/WEB-INF/view/header.jsp"/> <!-- c:import -->
	
	<c:import url="/WEB-INF/view/header.jsp">
		<c:param name="id" value="abc"/>
	</c:import>
	
	
	
	<!-- c:url : 여러번 사용되는 주소를 변수에 담아 사용 -->
<!-- https://fonts.google.com/?subset=korean&noto.script=Kore 이 주소를 저장하려면 -->
	<c:url value="https://fonts.google.com" var="gfont">
		<c:param name="subset" value="korean"></c:param>
		<c:param name="noto.script" value="Kore"></c:param>
	</c:url>
	
	
	
	<!-- c:out : 변수의 내용을 출력 -->
	


	<!-- set : 변수(var) 생성, 변수에 값(value) 저장 -->
	<c:set var="sum" value="0"></c:set> 
	
	
	<!-- c:forEach : 반복문. (begin="시작값" end="끝값" step="증가치") -->
	<c:forEach begin="3" end="20" step="2" var="i">
<%-- 	<c:set var="sum" value="${sum } + ${i }"></c:set> --%> <!-- 0 + 3 + 5 + 7 + 9 + 11 + 13 + 15 + 17 + 19 형태로 출력됨. (X) -->
		<c:set var="sum" value="${sum + i }"></c:set> <!-- 이게 맞는 방법. (O) -->
		<p>i:${i }, sum: ${sum }</p>
	</c:forEach>
	
	
	<!-- when + otherwise : if + else 문과 같은 개념 -->
	<c:choose>
		<c:when test="${empty aaa }"> 
		</c:when>
		<c:when test="">
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	


	<c:choose>
		<c:when test="${empty aaa}">
			<p>게시글이 없습니다.</p>
		</c:when>
		<c:otherwise>
			<table border="1">
				<tr>
					<td>글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
					<td>조회수</td>
				</tr>
				<c:forEach var="vo" items="${aaa}">
					<!-- var(변수)에 item를 넣음 -->
					<tr>
						<td>${vo.bno}</td>
						<td>${vo.btitle}</td>
						<td>${vo.bwriter}</td>
						<td>${vo.bdate}</td>
						<td>${vo.bcnt}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<%-- </c:set>	--%>

</body>
</html>