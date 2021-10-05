<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
              Es hora de moverse! Entrenamos.uy es la plataforma definitiva para hacer ejercicio sin moverse. Vea actividades deportivas, compre cuponeras e inscribase a clases
              sin tener que despegarse de su pantalla. Gracias a nuestras instituciones afilidadas que proveen profesores experimentados, usted puede recibir la mejor experiencia
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
            <h2 class="h2">Actividades destacadas:</h2>
        	<br>
          <div class="actDep row mb-4">
            <img src="<%=request.getContextPath()%>/assets/images/misc/crossFit.jpg" class="img-fluid d-inline col-5 col-md-7">
            <p class="d-inline col-12 col-sm-9 col-md-7 text-start"><b>CrossFit</b> es una tecnica de entrenamiento que
            conecta movimientos de diferentes disciplinas, tales
            como la halterofilia, el entrenamiento metabolico o
            el gimnastico. <a href="" class="text-primary">Leer mas</a></p>
          </div>
          <div class="actDep row mb-4">
            <img src="<%=request.getContextPath()%>/assets/images/misc/yogaImg.jpg" class="img-fluid d-inline col-5 col-md-7">
            <p class="d-inline col-12 col-sm-9 col-md-7 text-start"><b>Yoga</b> es una practica que conecta el cuerpo,
            la respiracion y la mente. Esta practica utiliza posturas fisicas, ejercicios de respiracion
            y meditacion. <a href="" class="text-primary">Leer mas</a></p>
          </div>
          <div class="actDep row mb-4">
            <img src="<%=request.getContextPath()%>/assets/images/misc/futbol11.jpg" class="img-fluid d-inline col-5 col-md-7">
            <p class="d-inline col-12 col-sm-9 col-md-7 text-start"><b>Futbol 11</b> y el deporte en general es una herramienta
            educativa que brinda un aprendizaje en valores y habilidades blandas que se cohesionan con el 
            aprendizaje y la practica deportiva. <a href="" class="text-primary">Leer mas</a></p>
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