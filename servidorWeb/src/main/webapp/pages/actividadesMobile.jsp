<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtSocioExt"%>
<%@ page import="java.util.Set"%>
<%@ page import="datatypes.DtActividadDeportivaExt"%>
<%@ page import="datatypes.DtClaseExt"%>
<%@ page import="datatypes.DtCuponera"%>
<%@ page import="datatypes.DtFecha"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/home.css">
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/consultaActividadDeportiva.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/actividadesMobile.css">
</head>
<body>
<jsp:include page="/template/headerMobile.jsp"/>
<%  DtUsuarioExt loggedUser = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
if ((loggedUser != null) && (loggedUser instanceof DtSocioExt)) {%>
   <div id="section-socio" class="row mt-4 mb-3">
       <p class="bienvenida-socio mt-3"><i class="fas fa-user-circle"></i> Bienvenido <b><%=loggedUser.getNickname()%></b></p>
   </div>
<%  DtActividadDeportivaExt datosActDep = (DtActividadDeportivaExt) request.getAttribute("actDep");
	DtUsuarioExt datosCreador = (DtUsuarioExt) request.getAttribute("datosCreador");
	String institucion = (String) request.getAttribute("institucion");
	Set<?> datosClases = (Set<?>) request.getAttribute("datosClases");
	Set<?> datosCuponeras = (Set<?>) request.getAttribute("datosCuponeras");
	%>
   <div id="section-infoActDep" class="row pt-4 pb-4">
       <div class="row">
           <h1 id="title"><%=datosActDep.getNombre()%></h1>
       </div>
       <div class="row pt-2">
           <img src="<%=request.getContextPath()%>/api/content?c=act&id=<%=datosActDep.getNombre()%>" alt="<%=datosActDep.getNombre()%>" class="imagProfMobile">
       </div>
       <div class="row mt-2 mb-4 pb-2">
           <p><%=datosActDep.getDescripcion()%></p>
       </div>
       <div class="row mb-1" id="fichaInfo">
           <p><i class="fas fa-clipboard-list"></i> Ficha Informativa</p>
       </div>
       <hr style="width: 73%; height: 2px;">
      <div class="row">
          <p><i class="fas fa-user-tie"></i> Ingresada por: <%=datosCreador.getNickname()%></p>
      </div>
      <div class="row">
          <p><i class="fas fa-school"></i> Institucion: <%=institucion%></p>
      </div>
      <div class="row pt-1">
          <p><i class="fas fa-clock"></i> Duracion: <%=datosActDep.getDuracionMinutos()%> min</p>
      </div>
      <div class="row pt-1">
          <p><i class="fas fa-money-bill-wave"></i> Costo: $<%=datosActDep.getCosto()%></p>
      </div>
      <div class="row pt-1">
          <p><i class="fas fa-calendar-alt"></i> Fecha Alta: <%=datosActDep.getFechaRegistro().toFecha()%></p>
      </div>
      <div class="row pt-4" id="cats">
      <%String text = "Categorias: ";
        Set<String> categorias = datosActDep.getCategorias();
	  	int contador = 0;
        for (String cat : categorias) { 
	  		text += cat;
	  		contador++;
	  		if (contador < categorias.size())
	  			text += ", ";
	  	}%>
          <p><%=text%></p>
      </div>
  </div>
  
  <div class="row pt-4 pb-4" id="section-clases">
      <div class="row mb-2">
          <h3><i class="fas fa-book"></i> Clases</h3>
      </div>
      <div id="infoClases" class="row">
          <p><i class="fas fa-info-circle"></i> Presione una clase para ver mas informacion</p>
      </div>
      <hr style="width:88%; height: 2px;">
	  <%contador = 0;
      	for (Object dtClase : datosClases) { %>
      <a href="<%=request.getContextPath()%>/clases?clase=<%=((DtClaseExt)dtClase).getNombre()%>">
          <img alt="imgClase"  src="<%=request.getContextPath()%>/api/content?c=cla&id=<%=((DtClaseExt)dtClase).getNombre()%>" class="vertical-align-middle imgSelectMobile">
          <div class="row">
              <h5><%=((DtClaseExt)dtClase).getNombre()%></h5>
          </div>
      </a>
      <%contador++;
		if (contador < datosClases.size()) {%>
      		<hr style="width:70%">
     	<% } %>   
     <% } %>
  </div>
  
  <div class="row pt-4 pb-4" id="section-cuponeras">
      <div class="row mb-2">
          <h3><i class="fas fa-ticket-alt"></i> Cuponeras</h3>
      </div>
      <hr style="width:88%; height: 2px;">
      <%contador = 0;
      	for (Object datosCup : datosCuponeras) { %>
          <img alt="imgCup"  src="<%=request.getContextPath()%>/api/content?c=cup&id=<%=((DtCuponera)datosCup).getNombre()%>" class="vertical-align-middle imgSelectMobile">
          <div class="row">
              <h5><%=((DtCuponera)datosCup).getNombre()%></h5>
          </div>
      <%contador++;
		if (contador < datosCuponeras.size()) {%>
      		<hr style="width:70%">
     	<% } %>   
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
