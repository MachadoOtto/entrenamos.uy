<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/home.css">
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/consultaActividadDeportiva.css">
	<script src="https://kit.fontawesome.com/ba45a4bf02.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/actividadesMobile.css">
</head>
<body>
	<div class="desktopDiv">
		<jsp:include page="/pages/actividadesDesktop.jsp"/>
	</div>
	<div class="mobileDiv">
		<jsp:include page="/pages/actividadesMobile.jsp"/>
	</div>
</body>
</html>