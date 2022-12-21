<%@page import="kh.s0.myboard.member.model.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- errorPage="./view/error/alert.jsp" --%>    
<%-- JSP주석

<%! 선언 %>  -> 변수선언O, 함수선언O

<%= 표현식 %>  -> 문자열, 변수, 함수리턴값 출력, (클라이언트에 출력할 내용), ; 사용하면 안됨.

<% 스크립틀릿 %> -> java 문법 그대로 사용 가능한 영역, 변수선언X, 함수선언O

--%> 

<%! int maxCnt = 5000; %>
<% String ctxPath = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 계층형 게시판 메인페이지</title>
<link href="<%=ctxPath%>/css/reset.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/header.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/footer.css" rel="stylesheet">
<script src="<%=ctxPath%>/js/jquery-3.6.1.js"></script>
</head>
<body>

<%@ include file="/WEB-INF/view/header.jsp" %>


<%
// 스크립틀릿
MemberVo loginSsInfo = (MemberVo)request.getSession().getAttribute("loginSsInfo"); // 형변환
%>


<h1>메인페이지</h1>
<button class="btn board">게시판</button>

<%
if(loginSsInfo == null) {
%>
	<button class="btn login">그냥로그인</button>
	
	<div class="wrap_login">
	
	<!-- 여기서부터 ajax -->
	<!-- <form action="<%=ctxPath %>/login.lo" method="post"> -->
	<form id="frm_login">
		<fieldset>
			<legend>로그인</legend>
			id: <input type="text" name="mid" id="mid"> <!-- name은 db의 컬럼명으로 하는게 좋음 -->
			<br>
			pwd: <input type="password" name="mpwd" id="mpwd">
			<br>
			<button type="button" class="btn loginajax">ajax로그인</button>
		</fieldset>
	</form>
	<!-- </form> -->
	</div>
	
	<div class="wrap_logout" style="display:none;">
		<button class="btn mypage">마이페이지</button>
		<button class="btn logout">로그아웃</button>
	</div>
	
	
	
<%
} else {
%>
	<button class="btn mypage"><%= loginSsInfo.getMname() %> 페이지</button>
	<button class="btn logout">로그아웃</button>
<%
}
%>


<!-- iframe -->
<iframe width="560" height="315" src="https://www.youtube.com/embed/kMYIja9m-no" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

<script>
$(function(){
	$(".btn.board").on("click", BoardClickHandler);
	$(".btn.login").on("click", LoginClickHandler);
	$(".btn.loginajax").on("click", LoginajaxClickHandler);
	$(".btn.mypage").on("click", MypageClickHandler);
	$(".btn.logout").on("click", LogoutClickHandler);
});
function BoardClickHandler(){
	console.log("BoardClickHandler");
	location.href = "<%=ctxPath%>/board/list";
}
function LoginClickHandler(){
	console.log("LoginClickHandler");
	location.href = "<%=ctxPath%>/login";
}
function LoginajaxClickHandler(){
	console.log("LoginajaxClickHandler");
	
	// 방법 2 -> mid=user1&mpwd=user1 형태의 쿼리 (회원가입처럼 여러개 항목을 넘겨야할때 이 방법 사용)
	var formQuery = $("#frm_login").serialize();
	console.log(formQuery);
	
	// 장바구니 -> 배열 형태
	var orderList = [];
	var order1 = {pno:21, amount:2}; // for문 돌려야함(지금은 그냥)
	var order2 = {pno:31, amount:7};
	var order3 = {pno:41, amount:3};
	orderList.push(order1);
	orderList.push(order2);
	orderList.push(order3);
	
	console.log(JSON.stringify(orderList));
	
	
	$.ajax({
		url : "<%=ctxPath%>/login.lo",
		type : "post",
		
		contentType : "application/json", // url로 전달'할' 데이터의 타입
		data : // url로 전달'할' 데이터. Object 타입으로 전달됨
			
			// 방법 3 -> json 형태
			JSON.stringify(orderList)
		
			// 방법 2 -> mid=user1&mpwd=user1 형태의 쿼리 (회원가입처럼 여러개 항목을 넘겨야할때 이 방법 사용)
//			$("#frm_login").serialize()
		
			// 방법 1 -> object 형태의 쿼리 (로그인할땐 두개만 넘기면 되니까 이 방법 사용해도됨)
//			{ 
//				mid : $("#mid").val(),
//				mpwd : $("#mpwd").val()
//			}
		, 
		
		dataType : "json", // url로부터 전달'받은' 데이터의 타입 (json, xml, text 등)
		// success의 콜백함수
		success : function(data){ // (data) : url로부터 전달'받은' 데이터
			console.log(data);
		
		
			// 세션 체크 
			// (ajax는 데이터를 주고받는데 목적이 있어 세션의 변화를 감지하지 못함. url이 그대로이기 때문에)
			// -> 그래서 ajax는 로그인에서 잘 사용하진 않음.
			var sessionCheck = '<%=request.getSession().getAttribute("loginSsInfo")%>';
//			console.log(sessionCheck);
		
//			location.reload(); // f5 새로고침 효과

			if(.data)
		},
		
		// error의 콜백함수
		error : function(request, status, error){ // (매개변수) : url로부터 전달'받은' 데이터 + 오류내용
			console.log(request);
			console.log(status);
			console.log(error);
			alert("code:" + request.status + "\n" + 
					"message" + request.responseText + "\n" + 
					"error" + error );
		}
	});
}
function MypageClickHandler(){
	console.log("MypageClickHandler");
	location.href = "<%=request.getContextPath()%>/mypage";
}
function LogoutClickHandler(){
	console.log("LogoutClickHandler");
	// session.invalidate(); --> 스크립틀릿에서만 사용 가능함. 여기선 사용 불가
	location.href = "<%=request.getContextPath()%>/logout";
}
</script>
</body>
</html>