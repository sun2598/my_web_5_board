<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ isErrorPage="true"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>alert</title>
<link href="<%=request.getContextPath()%>/css/reset.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
</head>
<body>
<script>
	var msg = "<%= request.getAttribute("msg")%>";
	alert(msg);
	location.replace = "<%= request.getContextPath()%>/board/write";
</script>	
</body>
</html>