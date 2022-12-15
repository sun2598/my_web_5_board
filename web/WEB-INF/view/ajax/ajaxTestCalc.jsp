<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax 계산기</title>
<link href="<%=request.getContextPath()%>/css/reset.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
<script>
$(function(){
	$("#btnSend").on("click", btnSendClickHandler);

});

function btnSendClickHandler(){
			
	var num1 = $("#num1").val();
	var num2 = $("#num2").val();
	var oper = $("#oper").val();
	
	
	$.ajax({
		type : "post",
		url : "test/calc.lo",
		
		data : {n1:num1, n2:num2, oper:oper}, // url로 전달'할' 데이터. Object 타입으로 전달됨
// 		data : "n1="+num1+"&n2"+num2+"&oper="+oper,

//		dataType : "json", // url로부터 전달'받은' 데이터의 타입 (json, xml, text 등)
		// success의 콜백함수
		success : btnSendSuccessCb, // (data) : url로부터 전달'받은' 데이터
		
		// error의 콜백함수
		error : ajaxErrorCb // (매개변수들) : url로부터 전달'받은' 데이터 + 오류내용
	});				
}	

function btnSendSuccessCb(data) { 
	console.log(data); // js object 에서 접근하는 방법 3가지
	console.log(data.result);
	console.log(data["result"]); // 배열
}
function ajaxErrorCb(request, status, error) { 
	alert("code:" + request.status + "\n" + 
			"message" + request.responseText + "\n" + 
			"error" + error );
}
</script>
</head>
<body>
	<input type="text" id="num1">
	<select id="oper">
		<option value="add">더하기</option>
		<option value="sub">뺴기</option>
		<option value="mul">곱하기</option>
		<option value="div">나누기</option>
	</select>
	<input type="text" id="num2">
	<input type="button" value="=" id="btnSend">
	<br>
	<hr>
	<div id="result"></div>
</body>
</html>