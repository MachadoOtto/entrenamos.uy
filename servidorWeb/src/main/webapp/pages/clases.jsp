<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/home.css">
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/clases.css">
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/stars.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/clasesMobile.css">
</head>
<body>
	<div class="desktopDiv">
		<jsp:include page="/pages/clasesDesktop.jsp"/>
	</div>
	<div class="mobileDiv">
		<jsp:include page="/pages/clasesMobile.jsp"/>
	</div>
</body>
</html>