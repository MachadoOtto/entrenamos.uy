<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="datatypes.DtClaseExt"%>
<%@ page import="datatypes.DtFecha"%>
<!DOCTYPE html>

<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/home.css">
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/consultaClases.css">
</head>
<body>
	<jsp:include page="/template/header.jsp"/>
	<div class="container-fluid mt-4">
		<%  DtClaseExt datosClase = (DtClaseExt) request.getAttribute("clase");
			String nombreActividad = (String) request.getAttribute("actividad");
			boolean esSocio = (boolean) request.getAttribute("esSocio");
			%>
		<div class="row mx-3 mx-md-5">
        	<div class="ins-cat col-2">
          		<jsp:include page="/template/stdLeftSection.jsp"/>
        	</div>
        	<div id="user-general" class="col-sm-7">
		      	<div id="user-superior" class="row ">
            		<div class="col-3 py-3">
			    		<div id="mainImgDiv" class="">
				    		<img alt="imagenClase" id="mainImgDiv" src="<%=request.getContextPath()%>/assets/images/classes/<%=datosClase.getNombre()%>.jpg">
			    		</div>
           			</div>
            		<div class="col-9 py-3">
				      	<div id="user-info" class="row">
                			<p><strong id="user-nickname"> <%=datosClase.getNombre()%> </strong> <a id="user-type" href="<%=request.getContextPath()%>/actividades?actividad=<%=nombreActividad%>"> (<%=nombreActividad%>) </a></p>
				      	</div>
              			<div id="creatorDiv" class="row">
                			<div class="col-auto">
                  				<h4><strong>Dictada por:</strong></h4>
                			</div>
              				<div class="col-auto">
                 				<img alt="Qries"  src="<%=request.getContextPath()%>/assets/images/users/<%=datosClase.getNicknameProfesor()%>.jpg" class="vertical-align-middle imagenSeleccionable">
                 				<a class="clase color-blue" href="<%=request.getContextPath()%>/usuarios?nickname=<%=datosClase.getNicknameProfesor()%>"><%=datosClase.getNicknameProfesor()%></a>
              				</div>
              				<%if (esSocio) { %>
	              				<div class="col-auto">
					                <button id="botonInsc" type="button" class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit" data-bs-toggle="modal" data-bs-target="#inscModal">
					                  Inscribirse
					                </button>
	              				</div>
	              			<% } %>
             			</div>
          			</div>
		      	</div>
		      	<div id="user-inferior" class= "row card-body mb-3">
	           		<div class="row">
	             			<div class="col-sm-3">
	                 			<h6 class="mb-0"><strong>Fecha y Hora de Inicio:</strong></h6>
	             			</div>
	             			<div class="col-sm-9 text-secondary">
	               				<%=datosClase.getFechaClase().toFechaHora()%>
	             			</div>
	           		</div>
		           	<div class="row">
		              	<div class="col-sm-3">
		                  	<h6 class="mb-0"><strong>Cupos minimos:</strong></h6>
		              	</div>
		              	<div class="col-sm-9 text-secondary">
		                  	<%=datosClase.getMinSocios()%> socios.
		              	</div>
		            </div>
		            <div class="row">
		              	<div class="col-sm-3">
		                  	<h6 class="mb-0"><strong>Cupos maximos:</strong></h6>
		              	</div>
		              	<div class="col-sm-9 text-secondary">
		                  	<%=datosClase.getMaxSocios()%> socios.
		              	</div>
		            </div>
		            <div class="row">
		              	<div class="col-sm-3">
		                  	<h6 class="mb-0"><strong>URL:</strong></h6>
		              	</div>
		              	<div class="col-sm-9 text-secondary">
		                	<%=datosClase.getURL()%>
		              	</div>
		            </div>
	            	<div class="row">
	              		<div class="col-sm-3">
	                  		<h6 class="mb-0"><strong>Fecha de registro:</strong></h6>
	              		</div>
	              		<div class="col-sm-9 text-secondary">
	                		<%=datosClase.getFechaRegistro().toFecha()%>
	              		</div>
	            	</div>
				</div>
			</div>
		    <div class="col-sm-3 ps-1 ps-sm-3">
		    	<div id= "user-consultaInscriptos" class="extraInfoDiv row">
		    		<%  List<String> nickAlumnos = datosClase.getNickAlumnos();%>
		      		<h5>Usuarios Inscriptos (<%=nickAlumnos.size()%>)</h5>
		      		<ul id="listaInscriptos" class="py-3">
		      			<%  for(String alumno : nickAlumnos) { %>
		      				<li class="container border card-body elementoLista">
				           		<img alt="Default"  src="<%=request.getContextPath()%>/assets/images/users/<%=alumno%>.jpg" class="vertical-align-middle imagenSeleccionable">
				           		<a class="clase color-blue" href="<%=request.getContextPath()%>/usuarios?nickname=<%=alumno%>"><%=alumno%></a>
				        	</li>
		      			<%  } %>
		      		</ul>
		    	</div>
		    	<div class="col-3 ps-1 ps-sm-2">
			    	<jsp:include page="/template/stdRightSection.jsp"/>
				</div>
		  	</div>
		</div>
	</div>
	
	<jsp:include page="/template/footer.jsp"/>
</body>
</html>