<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax 계산기</title>
<link href="<%=request.getContextPath()%>/css/reset.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
<script>
	$(function() {
		$("#btnSend").click(function(){
			
			var num1 = $("#num1").val();
			var oper = $("#oper").val();
			var num2 = $("#num2").val();
			
			$.ajax({
				type : "post",
				url : "/test/calc.do",
				data :
					
			});
		});
	});
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