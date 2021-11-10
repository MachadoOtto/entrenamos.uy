<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/search.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/searchMobile.css">
	<script src="https://kit.fontawesome.com/ba45a4bf02.js" crossorigin="anonymous"></script>
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