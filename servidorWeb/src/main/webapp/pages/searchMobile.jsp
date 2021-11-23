<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ page import="datatypes.DtClaseExt"%>
<%@ page import="datatypes.DtActividadDeportivaExt"%>
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtSocioExt"%>
<%@ page import="datatypes.DtFecha"%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/search.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/searchMobile.css">
</head>
<body>
	<jsp:include page="/template/headerMobile.jsp"/>
    <%DtUsuarioExt loggedUser = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
    if ((loggedUser != null) && (loggedUser instanceof DtSocioExt)) {%>
    <div id="section-socio" class="row mt-4 mb-3">
	    <p class="bienvenida-socio mt-3"><i class="fas fa-user-circle"></i> Bienvenido <b><%=loggedUser.getNickname()%></b></p>
	</div>
	
	<!-- Comienzo Listado usuario -->		
	<%List<?> listaActividades = (List<?>)request.getAttribute("actividades");
	List<?> listaClases = (List<?>)request.getAttribute("clases");
	
	// Guarda el link para la nueva request de los formularios:
	String encabezado = new String();
	String link = request.getContextPath() + "/search?";
	if (request.getAttribute("actividades") != null) {
		link += "actividades=yes";
		encabezado = "Actividades Deportivas";
		if (request.getAttribute("clases") != null) {
			link += "&clases=yes";
			encabezado = "Clases";
		}
	
	// Auxiliares para el orden y filtro:
	String orden = (String) request.getAttribute("orden");
	String mostrarTodas = (String) request.getAttribute("mostrarTodas");
	String linkAct = new String();
	if (request.getAttribute("actividadSelect") != null) {
		linkAct = "&fltrA=" + request.getAttribute("actividadSelect");
	}
	Set<?> listaInstituciones = (Set<?>) request.getAttribute("instituciones");
	Set<?> listaCategorias = (Set<?>) request.getAttribute("categorias");
	Set<?> filtro = null;
	String linkAndFiltro = link;
	filtro = (Set<?>) request.getAttribute("filtroInsti");
	String nombreFltr = new String();
	if (filtro.size() > 0) {
		linkAndFiltro += "&fltrI1=" + (String)filtro.iterator().next();
		nombreFltr = "de Institucion " + (String)filtro.iterator().next();
	}
	filtro = (Set<?>) request.getAttribute("filtroCat");
	if (filtro.size() > 0) {
		linkAndFiltro += "&fltrC1=" + (String)filtro.iterator().next();
		nombreFltr = "de Categoria " + (String)filtro.iterator().next();
	} %>
    <div id="section-verActsDeps" class="row pt-4 pb-4">
        <div class="row">
            <h1 id="title">Ver <%=encabezado%></h1>
        </div>
        <div>
          <div id="filtrarInstitucion" class="row filtrar pt-3">
            <div class="btn-group">
              <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                <i class="fas fa-school"></i> Instituciones
              </button>
              <ul class="dropdown-menu">
              	<%for (Object x : listaInstituciones) {%>
                <li><a class="dropdown-item" href="<%=link%>&fltrI1=<%=(String)x%>"><%=(String)x%></a></li>
                <% } %>
              </ul>
            </div>
        </div>
        <div id="filtrarCategoria" class="row filtrar pt-2 pb-4">
          <div class="btn-group">
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
              <i class="fas fa-align-justify"></i> Categorias
            </button>
            <ul class="dropdown-menu">
            <%for (Object x : listaCategorias) {%>
              <li><a class="dropdown-item" href="<%=link%>&fltrC1=<%=(String)x%>"><%=(String)x%></a></li>
            <% } %>
            </ul>
          </div>
        </div>
        <%if (encabezado.equals("Clases")) { %>
        <div id="filtrarActividades" class="row filtrar pt-2 pb-4">
        <%if ((((Set<?>)request.getAttribute("filtroInsti")).size() > 0) || (((Set<?>)request.getAttribute("filtroCat")).size() > 0)) {%>
          <div class="btn-group">
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
              <i class="fas fa-align-justify"></i> Actividades Deportivas
            </button>
            <ul class="dropdown-menu">
            <%for (Object obj : listaActividades) {%>
              <li><a class="dropdown-item" href="<%=linkAndFiltro%>&mostrar=<%=mostrarTodas%>&fltrA=<%=((DtActividadDeportivaExt)obj).getNombre()%>"><%=((DtActividadDeportivaExt)obj).getNombre()%></a></li>
            <% } %>
            </ul>
          </div>
        <% } %>
        </div>
        <% String linkAndFiltro2 = linkAndFiltro + linkAct;%>
        <div id="filtrarMuestra" class="row filtrar pt-2 pb-4">
          <div class="btn-group">
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
              <i class="fas fa-align-justify"></i> Mostrar Clases
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="<%=linkAndFiltro2%>&mostrar=yes">Todas</a></li>
              <li><a class="dropdown-item" href="<%=linkAndFiltro2%>&mostrar=no">Disponibles</a></li>
            </ul>
          </div>
        </div>
        <% } %>
        <div id="infoActDep" class="row">
        	<% String textoInfo = nombreFltr;
        	String fltrAct = (String) request.getAttribute("filtroAct");
        	if (encabezado.equals("Clases") && (!fltrAct.isEmpty())) {
        		 textoInfo += " y Actividad " + fltrAct;
        	}
        	if (encabezado.equals("Clases") && mostrarTodas.equals("no")) {
        		textoInfo = "Disponibles " + textoInfo;
        	}%>
            <p><i class="fas fa-info-circle"></i> Listado de <%=encabezado%> <%=textoInfo%></p>
            <p><i class="fas fa-info-circle"></i> Presione una para ver mas informacion</p>
        </div>
        </div>
        <hr style="width:88%; height: 2px;">
        <div id="actsDeps" class="row">
        	<%if (encabezado.equals("Clases")) {
        		for (Object obj : listaClases) {%>
	            <a href="<%=request.getContextPath()%>/clases?clase=<%=((DtClaseExt)obj).getNombre()%>">
	            <div class="row pt-2">
	                <img class="imgSearchMobile" src="<%=request.getContextPath()%>/api/content?c=cla&id=<%=((DtClaseExt)obj).getNombre()%>" alt="<%=((DtClaseExt)obj).getNombre()%>">
	            </div>
		        <div class="row pt-2 pb-2">
	                <h3><%=((DtClaseExt)obj).getNombre()%></h3>
	            </div>
	            </a>
            	<% } %>
            <% } else { 
            	for (Object obj : listaActividades) {%>
   	            <a href="<%=request.getContextPath()%>/actividades?actividad=<%=((DtActividadDeportivaExt)obj).getNombre()%>">
   	            <div class="row pt-2">
   	                <img class="imgSearchMobile" src="<%=request.getContextPath()%>/api/content?c=act&id=<%=((DtActividadDeportivaExt)obj).getNombre()%>" alt="<%=((DtActividadDeportivaExt)obj).getNombre()%>">
   	            </div>
   		        <div class="row pt-2 pb-2">
   	                <h3><%=((DtActividadDeportivaExt)obj).getNombre()%></h3>
   	            </div>
   	            </a>
               	<% } %>
            <% } %>
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
	<% } //End If ActividadesDeportivas == yes (necesario para saber que se usaran las listas)
	} //End If es DtSocioExt%>
</body>
</html>