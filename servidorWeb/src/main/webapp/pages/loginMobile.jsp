<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
	  <a class="navbar-brand" href="<%=request.getContextPath()%>/home" id="brand">
	    <img id="img-EUY" src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="" class="">
	    Entrenamos.uy
	  </a>
	</div>
</nav>
<div class="main col-11">
    <div class="row">
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
          <input class="form-check-input" type="checkbox" value="" id="recordarme">
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
			%>
			<div class="form-floating mb-3 mx-4">
			<h3 class="nav-link"><b>Error: </b> <i>La combinación Usuario/Contraseña no es correcta.</i></h3>
			</div>
		<% } %>
    </form>
    </div>
</div>
<jsp:include page="/template/footerMobile.jsp"/>