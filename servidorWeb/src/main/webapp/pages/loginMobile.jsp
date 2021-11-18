<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtSocioExt"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/homeMobile.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/loginMobile.css">
</head>
<body>
<div class="main col-11">
    <div class="row">
      <div class="row">
            <img id="iconoEUY" src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="" class="">
            <h1 id="titulo">Entrenamos.uy</h1>
      </div>
      <form id="formulario-sesion" action="<%=request.getContextPath()%>/login" method="POST" data-root="<%=request.getContextPath()%>"> 
        <div class="form-floating mb-3 mt-3 mx-4">
            <input type="text" class="form-control rounded-4" id="user" name="nick-login" placeholder="name@example.com">
            <label for="user"><i class="fas fa-user"></i> Correo electrónico / Nickname</label>
        </div>
        <div class="form-floating mb-3 mx-4">
            <input type="password" class="form-control rounded-4" id="pass" name="pass-login" placeholder="Password">
            <label for="pass"><i class="fas fa-lock"></i> Contraseña</label>                  
        </div>
        <div id="btn-recordarme" class="mx-4 mb-2">
          <input class="form-check-input" name="rememberme" type="checkbox" value="true" id="recordarme">
          <label class="form-check-label" for="recordarme">
          Recordarme
          </label>
        </div>
        <div id="btn-is" class="form-floating mb-3 mx-4">
          <input style="display: none;" class="form-control miurl" name="miurl" type="text">
          <button class="w-100 mt-2 btn btn-lg rounded-4 btn-primary" type="submit">Iniciar Sesión</button>
        </div>
        <%  String x = "";
			String q = (String) request.getParameter("e");
			if (q!=null) {
				x=q;
				String texto = "La combinación Usuario/Contraseña no es correcta.";
				if (Integer.parseInt(q) == 9)
					texto = "Solo los Socios/as pueden iniciar sesion.";
			%>
			<div class="form-floating mb-3 mx-4">
			<h3 class="nav-link"><b>Error: </b> <i></i><%=texto%></h3>
			</div>
		<% } %>
    </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1/dist/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/scripts/main.js"></script>
</body>
</html>
