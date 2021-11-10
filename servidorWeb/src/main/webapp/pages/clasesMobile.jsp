<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="datatypes.DtClaseExt"%>
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtFecha"%>
<jsp:include page="/template/headerMobile.jsp"/>

<%  DtClaseExt datosClase = (DtClaseExt) request.getAttribute("clase");
	String nombreActividad = (String) request.getAttribute("actividad");
	DtUsuarioExt loggedUser = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
	%>

<div id="section-socio" class="row mt-4 mb-3">
    <p class="bienvenida-socio mt-3"><i class="fas fa-user-circle"></i> Bienvenido <b><%=loggedUser.getNickname()%></b></p>
</div>

<div id="section-infoActDep" class="row pt-4 pb-4">
    <div class="row">
        <h1 id="title"><%=datosClase.getNombre()%></h1>
    </div>
    <div class="row">
        <p class="fs-4">(<%=nombreActividad%>)</p>
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

<div class="row pt-4 pb-4" id="section-cuponeras">
    <div class="row mb-2">
        <h3><i class="far fa-chart-bar"></i> Valoraciones</h3>
    </div>
    <hr style="width:88%; height: 2px;">
    <div class="row">
        <h5>andy (4)</h5>
    </div>
    <hr style="width: 70%">
      <div class="row">
          <h5>tonyp (5)</h5>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>