<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="datatypes.DtActividadDeportivaExt"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
</head>
<body>
	<jsp:include page="/template/header.jsp"/>
	
    <div class="container-fluid mt-4">
      <div class="row mb-5 mx-3 mx-md-5">
        <div style="background:url(<%=request.getContextPath()%>/assets/images/misc/wallpaperBg.jpg) center" class="bienvenida p-5">
          <div class="texto-bienvenida">
            <h1>Bienvenido a Entrenamos.uy!</h1>
            <p>
              Es hora de moverse! Entrenamos.uy es la plataforma definitiva para hacer ejercicio sin moverse. Vea actividades deportivas,  compre cuponeras e inscribase a clases
              sin tener que despegarse de su pantalla. Gracias a nuestras instituciones afilidadas que proveen profesores experimentados,  usted puede recibir la mejor experiencia
              deportiva que podría llegar a imaginarse. 
            </p>
          </div>
        </div>
      </div>

      <div class="row mx-3 mx-md-5">
        <div class="ins-cat col-2">
          <jsp:include page="/template/stdLeftSection.jsp"/>
        </div>
        <div class="actdep-panel col-7 ms-2 ms-sm-1 ms-md-0">
            <h1 class="h2">Noticias:</h1>
        	<br>
       		<div class="border border-dark row mb-4 p-5 text-dark bg-light rounded-3 ">
       			<div id="carouselNoticias" class="carousel carousel-dark slide" data-ride="carousel">
				  <div class="carousel-inner">
				    <div class="row carousel-item active">
				      	<div class="col">
			        		<img style="height: 16em;" src="<%=request.getContextPath()%>/assets/images/misc/bones.gif">
			        	</div>
				        <div class="col">
				        	<h2>A sacudir los huesos!</h2>
				          	<p>En entrenamos.uy tomamos todas las precauciones necesarias para que los huesos de nuestros clientes se mantengan sanos e integrales.
				         De ahora en adelante,  todas nuestras actividades deportivas son cuidadosamente analizadas y aprobadas como <b>Bone Safe</b>.</p>
				        </div>
				    </div>
				    <div class="carousel-item">
				    	<div class="col">
			        		<img style="height: 16em;" src="<%=request.getContextPath()%>/assets/images/users/viktor.jpg">
			        	</div>
				        <div class="col">
				        	<h2>Profesor del mes: <b>Viktor</b></h2>
				          	<p>El equipo directivo de entrenamos.uy con gran placer felicita a <b>Viktor</b> por su larga trayectoria en el sitio.
				          	 Proveniente de Moscow,  Viktor vino a Uruguay a enseñar como levantar unas buenas pesas. Este no conoce los límites,  y esto mismo
				          	 es lo que tanto motiva a sus estudiantes. Conozca más sobre este profesor
				          	 <a href="<%=request.getContextPath()%>/usuarios?nickname=viktor" class="clase link-dark">visitando su perfil.</a> </p>
				        </div>
				     </div>
				     <div class="carousel-item">
				    	<h2 class="h2">Actividades destacadas:</h2>
			        	<br>
			       		<%for (int i = 1; i < 4; i++) {
			       			DtActividadDeportivaExt datosActividad = (DtActividadDeportivaExt) request.getAttribute("actividad" + i);
			       			if (datosActividad != null) {%>
				        	<div class="actDep row mb-4">
					            <img src="<%=request.getContextPath()%>/assets/images/activities/<%=datosActividad.getNombre()%>.jpg" class="img-fluid d-inline col-5 col-md-7">
					            <p class="d-inline col-12 col-sm-9 col-md-7 text-start"><b><%=datosActividad.getNombre()%>.</b> <%=datosActividad.getDescripcion()%>
					            <a href="<%=request.getContextPath()%>/actividades?actividad=<%=datosActividad.getNombre()%>" class="text-primary">Leer mas.</a></p>
				          	</div>
				    	<%  }
				    	}%>
				     </div>
				  </div>
					  <button class="carousel-control-prev" type="button" data-bs-target="#carouselNoticias" data-bs-slide="prev">
					    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
					    <span class="visually-hidden">Previous</span>
					  </button>
					  <button class="carousel-control-next" type="button" data-bs-target="#carouselNoticias" data-bs-slide="next">
					    <span class="carousel-control-next-icon" aria-hidden="true"></span>
					    <span class="visually-hidden">Next</span>
					  </button>
				</div>
        	</div>
        </div>
        <div class="col-3 ps-1 ps-sm-2">
           <jsp:include page="/template/stdRightSection.jsp"/>
        </div>
      </div>
  	</div>
	<jsp:include page="/template/footer.jsp"/>
</body>
</html>
