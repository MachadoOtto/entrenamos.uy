<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
	<style>
		#ohno {
		    background-image: url(<%=request.getContextPath()%>/assets/images/misc/clown.jpg);
  			background-size: contain;
  			height: 39rem;
		}
    </style>
</head>
<body>
	<div id="ohno" class="page-wrap d-flex flex-row align-items-center">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-12 text-center">
	                <span class="display-1 d-block">500</span>
	                <div class="mb-4 lead">Internal Server Error: </div>
	                <div class="mb-4 lead">
		                <%  Exception ex = (Exception) request.getAttribute("contxError");
		                	if (ex != null) {%>
		                		<%=ex.getMessage()%> 
		                <% 	}%>
	                </div>
	                <a href="<%=request.getContextPath()%>/home" class="link-dark">Volver al inicio</a>
		        </div>
		    </div>
		</div>
	</div>
	<jsp:include page="/template/footer.jsp"/>
</body>
</html>