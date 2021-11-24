<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Map"%>
<%@ page import="datatypes.DtClaseExt"%>
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtSocioExt"%>
<%@ page import="datatypes.DtFecha"%>
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
	<jsp:include page="/template/headerMobile.jsp"/>
	<%DtUsuarioExt loggedUser = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
	if ((loggedUser != null) && (loggedUser instanceof DtSocioExt)) {
		DtClaseExt datosClase = (DtClaseExt) request.getAttribute("clase");
		String nombreActividad = (String) request.getAttribute("actividad");
		%>
	
	<div id="section-socio" class="row mt-4 mb-3">
	    <p class="bienvenida-socio mt-3"><i class="fas fa-user-circle"></i> Bienvenido <b><%=loggedUser.getNickname()%></b></p>
	</div>
	
	<div id="section-infoActDep" class="row pt-4 pb-4">
	    <div class="row">
	        <h1 id="title"><%=datosClase.getNombre()%></h1>
	    </div>
	    <div class="row" id="actDep-asociada">
	        <p class="fs-4">(<a class="textoHyperMob" href="<%=request.getContextPath()%>/actividades?actividad=<%=nombreActividad%>"><%=nombreActividad%></a>)</p>
	    </div>
		<div id="info-actDep-asociada" class="row pt-2">
          <p><i class="fas fa-info-circle"></i> Presione la actividad para ver su información</p>
      </div>
	    <div class="row pt-4">
	        <img src="<%=request.getContextPath()%>/api/content?c=cla&id=<%=datosClase.getNombre()%>" alt="" class="imagProfMobile">
	    </div>
	    <div class="row mt-4 mb-1" id="fichaInfo">
	        <p><i class="fas fa-clipboard-list"></i> Ficha Informativa</p>
	    </div>
	    <hr style="width: 73%; height: 2px;">
	    <div class="row">
	        <p><i class="fas fa-user-tie"></i> Dictada por: <%=datosClase.getNicknameProfesor()%></p>
	    </div>
	    <div class="row pt-1">
	        <p><i class="fas fa-clock"></i> Fecha y Hora de Inicio: <%=datosClase.getFechaClase().toFechaHora()%></p>
	    </div>
	    <div class="row pt-1">
	        <p><i class="fas fa-minus-circle"></i> Cupos Minimos: <%=datosClase.getMinSocios()%></p>
	    </div>
	    <div class="row pt-1">
	        <p><i class="fas fa-plus-circle"></i> Cupos Maximos: <%=datosClase.getMaxSocios()%></p>
	    </div>
	    <div class="row pt-1">
	        <p><i class="fas fa-globe-americas"></i> URL: <%=datosClase.getURL()%></p>
	    </div>
	    <div class="row pt-1 pb-1">
	        <p><i class="fas fa-calendar-alt"></i> Fecha Registro: <%=datosClase.getFechaRegistro().toFecha()%></p>
	    </div>
	</div>
	
	<div class="row pt-4 pb-4" id="section-clases">
	    <div class="row mb-2">
	        <h3><i class="fas fa-users"></i> Usuarios Inscriptos</h3>
	    </div>
	    <%  List<String> nickAlumnos = datosClase.getNickAlumnos();
	    	int contador = 0;%>
	    <hr style="width:88%; height: 2px;">
	    <%  for (String alumno : nickAlumnos) { %>
		    <div class="row">
		        <h5><%=alumno%></h5>
		    </div>
	    	<%contador++;
	    	if (nickAlumnos.size() > contador) {%>
	    		<hr style="width:70%">
	    	<%  } %>
	    <%  } %>    
	</div>
	
	<%if (datosClase.getCalificaciones().size() > 0) {
		  int s=0,n=0;
	      for(Map.Entry<String,Integer> x: datosClase.getCalificaciones().entrySet()){
	       		n++;
	       		s=s+x.getValue();
	      }
	      float promedio = (n>0)? s/n : 0;
	    %>
	  <div class="row pt-4 pb-4" id="section-cuponeras">
	    <div class="row mb-2">
	        <h3><i class="far fa-chart-bar"></i> Valoraciones</h3>
	    </div>
	    <div class="row pt-1 ">
	   		<h6 class="mb-0"><strong>Valoración promedio:</strong></h6>
			<div class="col-sm-9 text-secondary">
				<div class="Stars col mt-1" style="--rating: <%=String.valueOf(promedio)%> ;" aria-label=" <%=String.valueOf(promedio)%>">
				</div>
				<div class="col mb-1">
		 		<%=String.valueOf(promedio)%>   (<%=datosClase.getCalificaciones().size() %> valoraci<%=(datosClase.getCalificaciones().size()>1) ? "ones":"ón"%>)
				</div>
			</div>
		</div>
	    <hr style="width:88%; height: 2px;">
	    <%contador = 0;
	      for(Map.Entry<String,Integer> x: datosClase.getCalificaciones().entrySet()) {
	      	contador++;%>
		    <div class="row">
		        <h5><%=x.getKey()%> (<%=x.getValue()%>)</h5>
		    </div>
	    	<%if (contador < datosClase.getCalificaciones().size()) {%>
	    		<hr style="width: 70%">
			<% } %>
		<% } %>
	  </div>
	<% } %>
	<%if (datosClase.getPremio()!=null) { %>
		<div class="row pt-4 pb-4" id="section-cuponeras">
		    <div class="row mb-2">
		        <h3><i class="fas fa-trophy"></i> Premio</h3>
		    </div>
		    <div class="row pt-1 ">
		        <p><i class="fas fa-clipboard-list"></i> Descripción: <%=datosClase.getPremio().getDescripcion()%></p>
		    </div>
		    <div class="row pt-1">
		        <p><i class="fas fa-plus-circle"></i> Cantidad: <%=datosClase.getPremio().getCantidad()%></p>
		    </div>
		    <div class="row pt-1">
		    <%if (datosClase.getPremio().getFechaSorteo().equals(new DtFecha(0,0,0,0,0,0))) { %>
		        <p><i class="fas fa-clock"></i> El sorteo aún no se ha realizado!</p>
		    <% } else { %>
		        <p><i class="fas fa-clock"></i> Fecha sorteo: <%=datosClase.getPremio().getFechaSorteo().toFechaHora()%></p>
		    <% } %>
		    </div>
		    <%if (!(datosClase.getPremio().getFechaSorteo().equals(new DtFecha(0,0,0,0,0,0))) && datosClase.getPremio().getGanadores() != null) { %>
		    <div class="row mb-2">
		        <h3><i class="fas fa-ticket-alt"></i> Ganadores</h3>
		    </div>
		    <%  List<String> nickGanadores = datosClase.getPremio().getGanadores();
		    	int contadorG = 0;%>
		    <hr style="width:88%; height: 2px;">
		    <%  for (String ganador : nickGanadores) { %>
			    <div class="row">
			        <h5><%=ganador%></h5>
			    </div>
		    	<%contadorG++;
		    	if (nickGanadores.size() > contadorG) {%>
		    		<hr style="width:70%">
		    	<%  } %>
		    <%  } %>
		    <%} %>
	    </div>
	    <% } %>
		<div class="row pt-3 pb-4" id="section-video">
			<%if ((datosClase.getUrlVideo() != null) && !datosClase.getUrlVideo().isEmpty()) {
				String u = datosClase.getUrlVideo();
		        u=u.replace("watch?v=", "embed/");%>
		    <div class="row pt-1">
				<h3 class="pb-3"><i class="fab fa-youtube"></i> Video</h3>
		    	<iframe width="320" height="180" src="<%=u%>" frameborder="0" allowfullscreen></iframe>
		    </div>
		    <% } %>
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