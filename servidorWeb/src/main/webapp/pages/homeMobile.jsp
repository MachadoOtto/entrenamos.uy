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
</head>
<body>
	<jsp:include page="/template/headerMobile.jsp"/>
	<%DtUsuarioExt loggedUser = (DtUsuarioExt) request.getSession().getAttribute("loggedUser"); 
	  if ((loggedUser != null) && (loggedUser instanceof DtSocioExt)) {
		String f = "default.png";
	  	if (loggedUser.getImagen()!=null)
	  		f = new String(loggedUser.getImagen(), "UTF-8");
	  	%>
	<div id="section-socio" class="row mt-4 mb-3">
		  <img id="img-perfil" onerror="this.onerror=null; this.src='<%=request.getContextPath()%>/assets/images/misc/loading.gif'" alt="<%=loggedUser.getNickname()%>" id="img-perfil" src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=loggedUser.getNickname()%>">
	    <p class="bienvenida-socio mt-3">Bienvenido <b><%=loggedUser.getNickname()%></b> a la plataforma movil de Entrenamos.uy</p>
	</div>
	
	<div id="section-titular" class="row">
	    <h1>En esta plataforma podras encontrar</h1>
	</div>
	
	<div id="section-actDeps" class="row">
	    <div class="col-5 ms-1 ps-3">
	        <img src="<%=request.getContextPath()%>/assets/images/misc/actDepInfo.png" alt="">
	    </div>
	    <div id="texto-actDeps" class="col-6">
	        <p>Información sobre las actividades deportivas</p>
	    </div>
	</div>
	
	  <div id="section-clases" class="row pb-4">
	      <div id="texto-clases" class="col-6 ms-3 ps-4">
	          <p>Informacion sobre las clases que brinda la plataforma</p>
	      </div>
	      <div class="col-5 ms-3 pe-5">
	          <img src="<%=request.getContextPath()%>/assets/images/misc/claseInfo.png" alt="">
	      </div>
	  </div>
	
	  <div id="section-institucion" class="row pb-4">
	    <div class="col-5 ms-2 pe-5">
	        <img src="<%=request.getContextPath()%>/assets/images/misc/institucionInfo.png" alt="">
	    </div>
	    <div id="texto-institucion" class="col-6 ms-3 ps-4">
	      <p>Las actividades deportivas de tu institución favorita</p>
	  </div>
	</div>
	
	<div id="titular-pdm" class="row pt-4">
	    <h1><i class="fas fa-award"></i> Profesor del Mes</h1>
	</div>
	
	<div id="img-pdm" class="row pt-3 pb-4">
	    <div class="row">
	      <img src="<%=request.getContextPath()%>/assets/images/misc/empleadoMes.png" alt="">
	    </div>
	    <div class="row pt-2">
	      <h1>Viktor (Fuerza Bruta)</h1>
	    </div>
	    <div class="row pt-2 px-3">
	      <p>
	        El equipo directivo de entrenamos.uy con gran placer felicita a Viktor por su larga trayectoria en el sitio. Proveniente de Moscow, Viktor vino a Uruguay a enseñar como levantar unas buenas pesas. Este no conoce los límites, y esto mismo es lo que tanto motiva a sus estudiantes.
	      </p>
	    </div>
	</div>
	
	<div id="section-bs" class="row pt-4 pb-4">
	    <div class="row">
	      <h1>A sacudir los huesos!</h1>
	    </div>
	    <div class="row pt-4 pb-4">
	      <img src="<%=request.getContextPath()%>/assets/images/misc/boneSafe1.jpg" alt="">
	    </div>
	    <div class="row ps-5 pe-4">
	      <p>
	        En entrenamos.uy tomamos todas las precauciones necesarias para que los huesos de nuestros clientes se mantengan sanos e integrales. De ahora en adelante, todas nuestras actividades deportivas son cuidadosamente analizadas y aprobadas como Bone Safe.
	      </p>
	    </div>
	</div>
	
	<div id="section-sponsors" class="row pt-5 pb-5">
	    <div class="row text-center">
	        <h1>Sponsors</h1>
	    </div>
	    <div class="row mt-4">
	        <div class="col-6 text-center">
	            <img src="<%=request.getContextPath()%>/assets/images/misc/logoPowerade.jpg" alt="">
	        </div>
	        <div class="col-6 text-center">
	            <img src="<%=request.getContextPath()%>/assets/images/misc/logoGatorade.jpg" alt="">
	        </div>
	    </div>
	    <div class="row mt-4">
	        <div class="col-6 text-center">
	            <img src="<%=request.getContextPath()%>/assets/images/misc/logoUniversal.png" alt="">
	        </div>
	        <div class="col-6 text-center">
	            <img src="<%=request.getContextPath()%>/assets/images/misc/logoAdidas.jpg" alt="">
	        </div>
	    </div>
	</div>
	<jsp:include page="/template/footerMobile.jsp"/>
	<% } %>
</body>
</html>
