<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/homeMobile.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/loginMobile.css">
	<script src="https://kit.fontawesome.com/ba45a4bf02.js" crossorigin="anonymous"></script>
</head>
<body>
	<div class="desktopDiv">
		<jsp:include page="/pages/homeDesktop.jsp"/>
	</div>
	<div class="mobileDiv">
		<%if (request.getSession().getAttribute("loggedUser")==null) { %>
			<div id="logMobile" class = "logMobile">
				<jsp:include page="/pages/loginMobile.jsp"/>
			</div>
		<%} else { %>
			<jsp:include page="/pages/homeMobile.jsp"/>
		<%}%>
	</div>
</body>
</html>