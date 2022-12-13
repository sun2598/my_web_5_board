<%@page import="kh.s0.myboard.member.model.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 계층형 게시판 메인페이지</title>
<link href="<%=request.getContextPath()%>/css/reset.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
</head>
<body>
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
	<%-- <form action="<%= request.getContextPath() %>/login.do" method="post"> --%>
		<fieldset>
			<legend>로그인</legend>
			id: <input type="text" name="mid" id="mid"> <!-- name은 db의 컬럼명으로 하는게 좋음 -->
			<br>
			pwd: <input type="password" name="mpwd" id="mpwd">
			<br>
			<button type="button" class="btn loginajax">ajax로그인</button>
		</fieldset>
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
	location.href = "<%=request.getContextPath()%>/board/list";
}
function LoginClickHandler(){
	console.log("LoginClickHandler");
	location.href = "<%=request.getContextPath()%>/login";
}
function LoginajaxClickHandler(){
	console.log("LoginajaxClickHandler");
	$.ajax({
		url : "/login.lo",
		type : "post",
		data : { // url로 전달할 데이터. Object 타입으로 전달됨
			mid : $("mid").val(),
			mpwd : $("mpwd").val()
		}, 
		
		success : function(responsevalue){ // success의 콜백함수의 매개인자(responsevalue)에 url에서 전달해준 값이 들어있음
			console.log(responsevalue);
			if(responsevalue == "ok") {
				$(".wrap_logout").show();
				$(".wrap_login").hide();
			}
		},
		error : function(e){ // error의 콜백함수의 매개인자(e)에 url에서 전달해준 값 + 오류내용이 들어있음
			console.log(e.responseText);
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