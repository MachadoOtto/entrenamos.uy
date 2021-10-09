<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
    
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtProfesorExt"%>
<%@ page import="datatypes.DtSocioExt"%>
<%@ page import="datatypes.DtActividadDeportivaExt"%>
<%@ page import="datatypes.TEstado"%>


<!doctype html>
<html lang="en">
  <head>
    <jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/usuarios.css">
    <title>Entrenamos.uy</title>
  </head>
  <body>
    
    <!--BARRA DE NAVEGACION-->
    <jsp:include page="/template/header.jsp"/>

    <!--CUERPO DE LA PAGINA WEB-->
    <div class="container-fluid mt-4">

      <div class="row mx-3 mx-md-5">
        <div class="ins-cat col-2">
          <jsp:include page="/template/stdLeftSection.jsp"/>
        </div>
        
        <!--  Comienzo consulta usuario -->
        

        
        <% DtUsuarioExt usrLogged = (DtUsuarioExt) request.getSession().getAttribute("datoUsuarioLogged"); %>
        <% DtUsuarioExt usrProfile = (DtUsuarioExt) request.getAttribute("datoUsuario"); %>
        
        <div id="user-general" class="col-sm-8">
		<div id="user-superior" class="row ">
			<div class="row ">
				<div id="user-img-btn" class="col-auto py-4" >
					<div id="user-imagen" class="">
						<img id="img-perfil" width="180" height="180" alt="<%=usrProfile.getNickname()%>" id="img-perfil" src="<%=request.getContextPath()%>/assets/images/users/<%= usrProfile.getNickname() %>.jpg"></img>
					</div>
					<div>
						<% if(usrLogged != null) {	/*Está logueado*/ %>
							<% if(usrLogged.getNickname() == usrProfile.getNickname()) { /* Son el mismo usuario */ %>
							<div id="user-editar" class="flex-sm-fill text-sm-center nav-link ">
							<button type="button" class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#modifModal">
				            	Editar Perfil
				            </button>
							</div>
							<% } else if( !usrProfile.getSeguidosNickname().contains(usrLogged.getNickname()) ) {	/*No sigue al usuario del perfil*/ %>
							<div id="user-seguir" class="flex-sm-fill text-sm-center nav-link ">
								<form id="seguirForm" action="<%=request.getContextPath()%>/api/seguir" method="GET">
								  <button type="submit" class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#seguirModal" >
					            	Seguir
					              </button>
					          </form>
							</div>
							<% } else{ /* Sigue al usuario*/%>
							<div id="user-dejarSeguir" class="flex-sm-fill text-sm-center nav-link ">
							  <form id="dejarDeSeguirForm" action="<%=request.getContextPath()%>/api/dejarDeSeguir" method="GET">
								<button type="submit" class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#dejarDeSeguirModal" >
					            	Dejar de Seguir
					            </button>
					          </form>
							</div>
							<% } %>
						<% } %>
					</div>
					
				</div>
				<div id="user-info" class="col-auto py-3">
					<% String tipo = (usrProfile instanceof DtProfesorExt) ? "Profesor":"Socio"; %>
					<p><strong id="user-nickname"> <%=usrProfile.getNickname()%> </strong> <a id="user-type"><small class="text-muted"> (<%=tipo%>)</small>  </a></p>
					<p><strong>Nombre: </strong> <%=usrProfile.getNombre()%> <strong>Apellido: </strong> <%=usrProfile.getApellido()%> </p>
					<p><strong>Correo: </strong> <%=usrProfile.getEmail()%> </p>
					<strong>Seguidores: </strong> <%=usrProfile.getSeguidoresNickname().size()%> <strong>Seguidos: </strong> <%=usrProfile.getSeguidosNickname().size()%>
				</div>
			</div>
		</div>

		<div id="user-inferior" class= "row ">
			<div id="user-navegador" class="row">
				<nav class="nav nav-pills flex-column flex-sm-row">
					<button id="nav-perfil" type="button" onclick="cambioNavegador('user-consultaPerfil','nav-perfil)" class="flex-sm-fill text-sm-center nav-link border border-bottom-radius-radius-0 active" >Perfil</button>
					<% if(usrProfile instanceof DtProfesorExt) {%>
						<button id="nav-inscripciones" type="button" onclick="cambioNavegador('user-consultaInscripciones','nav-inscripciones')" class="flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " >Inscripciones</button>
					<% } else { %>
						<button id="nav-clasesDictadas" type="button" onclick="cambioNavegador('user-consultaClases','nav-clasesDictadas')" class="flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 ">Clases Dictadas</button>
					<% } %>
					<button id="nav-seguidores" type="button" onclick="cambioNavegador('user-seguidores','nav-seguidores')" class="flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " >Seguidores</button>
					<button id="nav-seguidos" type="button" onclick="cambioNavegador('user-seguidos','nav-seguidos')" class="flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 ">Seguidos</button>
					<% if(!(usrProfile instanceof DtProfesorExt) && usrLogged.getNickname() == usrProfile.getNickname()) {	/*Socio mirando su propio perfil*/ %>	
						<button id="nav-cuponeras" hidden="hidden" type="button" onclick="cambioNavegador('user-cuponeras','nav-cuponeras')" class="flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " >Cuponeras</button>
					<% } %>
					<% if(usrProfile instanceof DtProfesorExt) { %>
						<button id="nav-actAsoc" type="button" onclick="cambioNavegador('user-consultaAD','nav-actAsoc')" class="flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 ">Actividades Asociadas</button>
						<button id="nav-actIng" hidden="hidden" type="button" onclick="cambioNavegador('user-consultaADI','nav-actIng')" class="flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " >Actividades Ingresadas</button>
					<% } %>
				</nav>
			</div>
			<div id="user-consultaPerfil" class="col-sm-11 border">
              <div class="mb-3">
                <div class="card-body">
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Nombre completo</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <%=usrProfile.getNombre()%> <%=usrProfile.getApellido()%>
                    </div>
                  </div>
                  <br>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Correo electrónico:</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <%=usrProfile.getEmail()%>
                    </div>
                  </div>
                  <br>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Fecha de nacimiento:<strong></strong></strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <%=usrProfile.getFechaNacimiento().toFechaHora()%>
                    </div>
                  </div>
                  <div>
                  	  <% if(usrProfile instanceof DtProfesorExt) { %>
	                  <div hidden="hidden" class="row">
	                    <div class="col-sm-3">
	                        <br>
	                      <h6 class="mb-0"><strong>Institución Asociada:</strong></h6>
	                    </div>
	                    <div class="col-sm-9 text-secondary">
	                      <%= ((DtProfesorExt)usrProfile).getNombreInstitucion() %>
	                      <br>
	                    </div>
	                  </div>
	                  <div hidden="hidden" class="row">
	                    <div class="col-sm-3">
	                      <h6 class="mb-0"><strong>Descripción: </strong></h6>
	                    </div>
	                    <div class="col-sm-9 text-secondary">
	                      <%= ((DtProfesorExt)usrProfile).getDescripcion() %>
	                      <br>
	                    </div>
	                  </div>
	                  <div hidden="hidden" class="row">
	                    <div class="col-sm-3">
	                      <h6 class="mb-0"><strong>Biografía:</strong></h6>
	                    </div>
	                    <div class="col-sm-9 text-secondary">
	                      <%= ((DtProfesorExt)usrProfile).getBiografia() %>
	                      <br>
	                    </div>
	                  </div>
	                  <div hidden="hidden" class="row">
	                    <div class="col-sm-3">
	                      <h6 hidden="hidden" class="mb-0"><strong>Website:</strong></h6>
	                    </div>
	                    <div class="col-sm-9 text-secondary">
	                    	<a href="<%= ((DtProfesorExt)usrProfile).getLink() %>">
							  <%= ((DtProfesorExt)usrProfile).getLink() %>
	                    	</a>
	                    </div>
	                  </div>
	            	<% } %>
	            </div>
               </div>
              </div>
				</div>
				<% if(usrProfile instanceof DtProfesorExt) {	/*Clases dictadas*/ %>
				<div id= "user-consultaClases" class=" border card-body">
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for( Map.Entry<String,Set<String>> xy: (((DtProfesorExt)usrProfile).getClasesxActividades()).entrySet() ) { %>
							<% for( String nombreClase: xy.getValue() ) { %>
			         	<li class="list-group-item container border card-body elementoLista"> <img alt="<%=nombreClase%>"  src="<%=request.getContextPath()%>/assets/images/classes/<%= usrProfile.getNickname() %>.jpg"
			         	class="vertical-align-middle imagenSeleccionable"><b><%=nombreClase%></b><small class="text-muted"> (<%=xy.getKey()%>)</small></li>
							<% } %>
						<% } %>
					</ul>
				</div>
				<% } else {		/*Inscripciones*/%>
				<div id= "user-consultaInscripciones" class=" border card-body">
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for( Map.Entry<String,Set<String>> xy: (((DtSocioExt)usrProfile).getAguadeUwu()).entrySet() ) { %>
							<% for( String nombreClase: xy.getValue() ) { %>
			         	<li class="list-group-item container border card-body elementoLista"> <img alt="<%=nombreClase%>"  src="<%=request.getContextPath()%>/assets/images/classes/<%= usrProfile.getNickname() %>.jpg"
			         	class="vertical-align-middle imagenSeleccionable"><b><%=nombreClase%></b><small class="text-muted"> (<%=xy.getKey()%>)</small></li>
							<% } %>
						<% } %>
					</ul>
				</div>
				<% }%>
				<div id= "user-seguidores" class=" border card-body">
					<ul id="listaActividadesActDep" class="list-group list-group-horizontal-sm">
						<% for( String nick: usrProfile.getSeguidoresNickname() ) { %>
						<li class="list-group-item container border card-body elementoLista">
						 <img alt="Qries"  src="../img/users/euge.jpg" class="vertical-align-middle imagenSeleccionable">
							<b><br> <%=nick%></b><small class="text-muted"> (Socio)</small>
						 </li>
						 <% } %>
					</ul>
				</div>
				<div id= "user-seguidos" class=" border card-body">
					<ul id="listaActividadesActDep" class="list-group list-group-horizontal-sm">
						<% for( String nick: usrProfile.getSeguidosNickname() ) { %>
						<li class="list-group-item container border card-body elementoLista">
						 <img alt="Qries"  src="../img/users/guille.jpg" class="vertical-align-middle imagenSeleccionable">
							<b><br><%=nick%></b><small class="text-muted"> (Socio)</small>
						 </li>
						 <% } %>
					</ul>
				</div>
				<% if(usrProfile instanceof DtSocioExt && usrProfile.getNickname() == usrLogged.getNickname()) {	/* Socio viendo su propio perfil */ %>
				<div id= "user-cuponeras" class="col-sm-9 border card-body">
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for( String cup: ((DtSocioExt)usrProfile).getCuponerasCompradas() ) { %>
						<li class="list-group-item container border card-body elementoLista"><a href="../cuponeras/pelota.html"  class="link-dark">
						 <img alt="<%=cup%>"  src=".././img/cups/pelota.gif" class="vertical-align-middle imagenSeleccionable">
							<b><%=cup%></b></a>
						 </li>
						 <% } %>
						</ul>
				</div>
				<% } else if(usrProfile instanceof DtProfesorExt) {%>
				<div id= "user-consultaAD" class="col-sm-9 border card-body">
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for( String ad: ((DtProfesorExt)usrProfile).getActividadesDepAsociadas() ) { %>
						<li class="list-group-item container border card-body elementoLista"><a href="../actividades/voleibol.html"  class="link-dark">
						 <img alt="Qries"  src=".././img/activities/voleibol.jpg" class="vertical-align-middle imagenSeleccionable">
							<b><%=ad%></b></a>
						 </li>
						 <% } %>
						</ul>
				</div>
				<div id= "user-consultaADI" class="col-sm-9 border card-body">
				
				<% List<?> dtad = (List<?>) request.getAttribute("activiadesIngresadas"); %>
				
				<% if(usrProfile.getNickname() == usrLogged.getNickname()) { /* Profesor viendo su perfil*/%>
				<h5>Actividades Aceptadas</h5>
				<% } %>
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for( Object ad: dtad ) { %>
							<% if(((DtActividadDeportivaExt)ad).getEstado() == TEstado.aceptada) { %>
							<li class="list-group-item container border card-body elementoLista"><a href="../actividades/voleibol.html"  class="link-dark">
							 <img alt="Qries"  src=".././img/activities/voleibol.jpg" class="vertical-align-middle imagenSeleccionable">
								<b><%=ad%></b></a>
							 </li>
							<% } %>
						 <% } %>
					</ul>
					<br>
					<% if(usrProfile.getNickname() == usrLogged.getNickname()) { %>
					<h5>Actividades Ingresadas</h5>
					  <ul id="listaActividadesClases" class="list-group list-group-horizontal">
					  	<% for( Object ad: dtad ) { %>
					  		<% if(((DtActividadDeportivaExt)ad).getEstado() == TEstado.ingresada) { %>
							<li class="list-group-item container border card-body elementoLista"><a class="link-dark">
							 <img alt="Qries"  src=".././img/activities/basquetbol2.jpg" class="vertical-align-middle imagenSeleccionable">
								<b><%=ad%></b></a>
							</li>
							<% } %>
						<% } %>
					  </ul>
					<br>
					<h5>Actividades Rechazadas</h5>
					  <ul id="listaActividadesClases" class="list-group list-group-horizontal">
					  	<% for( Object ad: dtad ) { %>
							<% if(((DtActividadDeportivaExt)ad).getEstado() == TEstado.aceptada) { %>
						    <li class="list-group-item container border card-body elementoLista"><a  class="link-dark">
							 <img alt="Qries"  src=".././img/activities/voleibol2.jpg" class="vertical-align-middle imagenSeleccionable">
								<b><%=ad%></b></a>
							 </li>
							 <% } %>
						 <% } %>
					  </ul>
					<% } %>
				</div>
				<% } %>
		</div>
	</div>
	
	<!--MODALS-->
    <div class="modal fade" id="modifModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <img src=".././img/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                <h2 class="fw-bold mb-0">Modificar datos de Usuario</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                   <h5>Cambiar contraseña</h5>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control rounded-4" id="pas1">
                        <label for="pas1">Contraseña</label>                  
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control rounded-4" id="pas2">
                        <label for="pas2">Confirmar Contraseña</label>                  
                    </div>
                    <h5>Sobre ti</h5>
                    <div id="nombreCompletoDiv" class="row form-floating mb-3">
                        <div id="divNombre" class="col-6 form-check float-left">
                            <div class="form-floating mb-2">
                                <input type="text" class="form-control rounded-4" id="nomm"  value="<%=usrProfile.getNombre()%>">
                                <label for="nomm">Nombre</label>           
                            </div>      
                        </div>
                        <div id="divApellido" class="col-6 form-check float-left">
                            <div class="form-floating mb-2">
                                <input type="text" class="form-control rounded-4" id="ape"  value="<%=usrProfile.getApellido()%>">
                                <label for="ape">Apellido</label>           
                            </div>                           
                        </div>             
                    </div>
                    <div class="form-floating mb-3">
                        <input type="date" class="form-control rounded-4" id="nac"  value="<%=usrProfile.getFechaNacimiento().toFecha()%>">
                        <label for="nomm">Fecha de nacimiento</label>     
                    </div>
                  	<div class="mb-3">
						  <label for="formFile" class="form-label">Cambiar foto de Perfil</label>
						  <input class="form-control" type="file" id="formFile">
					</div>
					<% if(usrProfile instanceof DtProfesorExt) { %>
						<div id="modifdescDiv" class="form-group form-floating mb-3">
	                        <textarea class="form-control" id="desc" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' > <%=((DtProfesorExt)usrProfile).getDescripcion()%>
	                        </textarea>
	                        <label for="desc">Descripción</label>     
	                    </div>
	                    <div id="newbioDiv" class="form-group form-floating mb-3">
	                        <textarea class="form-control" id="bio" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' ><%=((DtProfesorExt)usrProfile).getBiografia()%></textarea>
	                        <label for="desc">Biografía</label>     
	                    </div>
	                    <div id="newwebDiv" class="form-floating mb-3">
	                        <input type="text" class="form-control rounded-4" id="webs"  value="<%=((DtProfesorExt)usrProfile).getLink()%>">
	                        <label for="webs">Sitio web</label>
	                    </div>
                  	<% } %>
                    <button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit">Confirmar</button>
                </form>
            </div>
            <div class="modal-footer">
                <hr class="my-6">
                <div>
                    <i>Entrenamos.uy - modificar datos de usuario</i>
                </div>
            </div>
        </div>
      </div>
    </div>
	
        <!-- Fin consulta usuario -->

        <div class="col-2 ps-1 ps-sm-2">
           <jsp:include page="/template/stdRightSection.jsp"/>
        </div>
      </div>
    </div>

    <!--FOOTER-->
    <jsp:include page="/template/footer.jsp"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/scripts/usuarios.js"></script>
	
</body>
</html>