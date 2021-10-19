<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set"%>
<%@ page import="datatypes.DtActividadDeportivaExt"%>
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtClaseExt"%>
<%@ page import="datatypes.DtCuponera"%>
<%@ page import="datatypes.DtFecha"%>
<%@ page import="logica.LaFabrica"%>
<!DOCTYPE html>

<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/home.css">
	<link rel="stylesheet" 
		href="<%=request.getContextPath()%>/assets/styles/consultaActividadDeportiva.css">
</head>
<body>
	<jsp:include page="/template/header.jsp"/>
	<div class="container-fluid mt-4">
		<%  DtActividadDeportivaExt datosActDep = (DtActividadDeportivaExt) request.getAttribute("actDep");
			boolean esSocio = (boolean) request.getAttribute("esSocio");
			%>
		<div class="row mx-3 mx-md-5">
        	<div class="ins-cat col-2">
          		<jsp:include page="/template/stdLeftSection.jsp"/>
        	</div>
        	<div class="col-sm-7" id="actd-general" >
		        <div id="actd-superior" class="row ">
			        <div class="col-3 py-3">
					    <div id="mainImgDiv" class="">
						    <img alt="aparatos+Pesas" id="mainImgDiv" src="<%=request.getContextPath()%>/assets/images/activities/<%=datosActDep.getImgName()%>">
					    </div>
                    </div>
                	<div class="col-9 py-3">
				        <div id="user-info" class="row" name="nombreActDep">
                            <h1><%=datosActDep.getNombre()%> %></h1>
				        </div>
                        <div id="creatorDiv" class="row">
                            <div class="col-auto">
                                <h6>Ingresada por:</h6>
                            </div>
                            <div class="col-auto">
                            	<%DtUsuarioExt datosCreador = LaFabrica.getInstance().obtenerIUsuarioController().seleccionarUsuario(datosActDep.getCreador());%>
                                <img id="actDepCreator" alt="viktor" id="img-perfil" src="<%=request.getContextPath()%>/assets/images/users/<%=datosCreador.getImgName()%>">
                            </div>
                            <div class="col-auto">
                                <%=datosActDep.getCreador()%>
                            </div>
                        </div>
                    </div>
                </div>
		        <div id="actd-inferior" class= "row card-body mb-3">
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0"><strong>Institución asociada:</strong></h6>
                        </div>
                        <div class="col-sm-9 text-secondary" name="institucionAsociada">
                            <%=LaFabrica.getInstance().obtenerIDictadoClaseController().obtenerInstitucionActDep(datosActDep.getNombre())%>
                        </div>
                        <div class="col-sm-3">
                            <h6 class="mb-0"><strong>Descripción:</strong></h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <%=datosActDep.getDescripcion()%>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0"><strong>Duración:</strong></h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <%=datosActDep.getDuracionMinutos()%>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0"><strong>Costo:</strong></h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <%=datosActDep.getCosto()%>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0"><strong>Fecha de Alta:</strong></h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <%=datosActDep.getFechaRegistro().toFecha()%>
                        </div>
                    </div>
		        </div>
                <br>
                <%if(!esSocio) { %>
	                <button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit" data-bs-toggle="modal" data-bs-target="#altaClaseModal" >
	                    Dar de alta una clase para esta actividad
	                </button>
	            <% } else { %>
	            	<button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary disabled" type="submit" data-bs-toggle="modal" data-bs-target="#altaClaseModal" >
	                    Dar de alta una clase para esta actividad
	                </button>
	            <% } %>
            </div>
            <div class="col-sm-3 ps-1 ps-sm-2">
                <div class="extraInfoDiv row">
                    <h5>Clases</h5>
					<ul id="listaActividades" class=" py-3">
						<%Set<String> clases = datosActDep.getClases();%>
						<%for(String clase : clases) { %>
							<li class="container border card-body elementoLista"> 
								<%DtClaseExt datosClase = LaFabrica.getInstance().obtenerIDictadoClaseController().buscarClase(clase);%>
                            	<img alt="calistenia"  src="<%=request.getContextPath()%>/assets/images/classes/<%=datosClase.getImgName()%>" class="vertical-align-middle imagenSeleccionable">
                            	<a href="<%=request.getContextPath()%>/clases?nombre=<%=clase%>" class="clase color-blue"><%=clase%></a>
                        	</li> 
						<% } %>              
					</ul>
                </div>
                <div class="extraInfoDiv row">
                    <h5>Cuponeras</h5>
					<ul id="listaActividades" class=" py-3">
						<%Set<String> cuponeras = datosActDep.getCuponeras();%>
						<%for(String cup : cuponeras) { %>
							<li class="container border card-body elementoLista"> 
								<%DtCuponera datosCup = LaFabrica.getInstance().obtenerICuponeraController().seleccionarCuponera(cup);%>
                            	<img alt="calistenia"  src="<%=request.getContextPath()%>/assets/images/cups/<%=datosCup.getImgName()%>" class="vertical-align-middle imagenSeleccionable">
                            	<a class="clase color-blue"><%=cup%></a>
                        	</li>
                        <% } %>                
					</ul>
                </div>
                <div class="extraInfoDiv row">
                    <h5>Categorías</h5>
					<ul id="listaActividades" class=" py-3">
						<%Set<String> categorias = datosActDep.getCategorias();%>
						<%for(String cat : categorias) { %>
							<li class="container border card-body elementoLista"> 
                            	<a class="clase color-blue"><%=cat%></a>
                        	</li>
                        <% } %>               
					</ul>
                </div>
            </div>
        </div>
	</div>
	
	<jsp:include page="/template/footer.jsp"/>
	
	<!--MODAL ALTA CLASE-->
    <div class="modal fade" id="altaClaseModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <img src=".././img/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                    <h2 class="fw-bold mb-0">Alta de Clase</h2>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control rounded-4" name="nombreClase" id="floatingInput" placeholder="">
                            <label for="floatingInput">Nombre</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="date" class="form-control rounded-4" name="fechaInicio" id="floatingInput" placeholder="">
                            <label for="floatingPassword">Fecha de inicio</label>                  
                        </div>
                        <div id="nombreCompletoDiv" class="row form-floating mb-3">
                            <div id="divNombre" class="col-6 form-check float-left">
                                <div class="form-floating mb-2">
                                    <input type="text" class="form-control rounded-4" name="cantMin" id="nomm" >
                                    <label for="nomm">Cant. Mínima de inscriptos</label>           
                                </div>      
                            </div>
                            <div id="divApellido" class="col-6 form-check float-left">
                                <div class="form-floating mb-2">
                                    <input type="text" class="form-control rounded-4" name="cantMax" id="ape" >
                                    <label for="ape">Cant. Máxima de inscriptos</label>           
                                </div>                           
                            </div>             
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control rounded-4" name="url" id="floatingInput" placeholder="">
                            <label for="floatingInput">URL</label>                  
                        </div>
                        <div id="imgPick" class="custom-file">
                            <input type="file" class="custom-file-input" name="img" id="customFile">
                            <label class="custom-file-label" for="customFile">Imagen</label>
                        </div>
                        <button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit">Confirmar Registro</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <hr class="my-6">
                    <div>
                        <i>Entrenamos.uy - Alta Dictado de Clase</i>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
</body>
</html>