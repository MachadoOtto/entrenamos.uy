<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/search.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/searchMobile.css">
</head>
<body>
	<div class="desktopDiv">
		<jsp:include page="/pages/searchDesktop.jsp"/>
	</div>
	<div class="mobileDiv">
		<jsp:include page="/pages/searchMobile.jsp"/>
	</div>
</body>
</html>