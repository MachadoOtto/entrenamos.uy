<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.ArrayList"%>


<%@ page import="datatypes.DtPremio"%>
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtProfesorExt"%>
<%@ page import="datatypes.DtSocioExt"%>
<%@ page import="datatypes.DtCuponera"%>
<%@ page import="datatypes.DtClaseExt"%>
<%@ page import="datatypes.DtActividadDeportivaExt"%>
<%@ page import="datatypes.TEstado"%>


<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/usuarios.css">
	
</head>

		<% DtUsuarioExt usrLogged = (DtUsuarioExt) request.getSession().getAttribute("loggedUser"); %>
        <% DtUsuarioExt usrProfile = (DtUsuarioExt) request.getAttribute("datoUsuario"); %>

  <body <% if(usrProfile instanceof DtProfesorExt) { %>
	  		<%="onload = \"cargarEstrellitas();\""%>
  		   <% } %>
  		   >
    
    <jsp:include page="/template/header.jsp"/>


    <div class="container-fluid mt-4">

      <div class="row mx-3 mx-md-5">
        <div class="ins-cat col-2">
          <jsp:include page="/template/stdLeftSection.jsp"/>
        </div>
        
        <!--  Comienzo consulta usuario -->
        
        
        
        <% String imagenPerfil = (usrProfile.getImagen() != null) ? new String(usrProfile.getImagen(), "UTF-8"):"default.png"; %>
        
        <div id="user-general" class="col-sm-8">
		<div id="user-superior" class="row ">
			<div class="row ">
				<div id="user-img-btn" class="col-auto py-4" >
					<div id="user-imagen" class="">
						<img id="img-perfil" width="180" height="180" alt="<%=usrProfile.getNickname()%>" src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=usrProfile.getNickname()%>"></img>
					</div>
					<div>
						<% if (usrLogged != null) { /*Está logueado*/%>
							<% if (usrLogged.getNickname().equals(usrProfile.getNickname())) { /* Son el mismo usuario */ %>
							<div id="user-editar" class="flex-sm-fill text-sm-center nav-link ">
							<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifModal">
				            	Editar Perfil
				            </button>
							</div>
							<% } else if (!usrLogged.getSeguidosNickname().contains(usrProfile.getNickname())) { /*No sigue al usuario del perfil*/ %>
							<div id="user-seguir" class="flex-sm-fill text-sm-center nav-link ">
								 <a href="<%=request.getContextPath()%>/seguir?nickname=<%=usrProfile.getNickname()%>">
								  <button class="btn-ir btn btn-primary" type="submit" >
					            	Seguir
					              </button>
					              </a>
							</div>
							<% } else{ /* Sigue al usuario*/ %>
							<div id="user-dejarSeguir" class="flex-sm-fill text-sm-center nav-link ">
								<a href="<%=request.getContextPath()%>/dejarDeSeguir?nickname=<%=usrProfile.getNickname()%>">
								<button class="btn btn-primary" type="submit" >
					            	Dejar de Seguir
					            </button>
					            </a>
							</div>
							<% } %>
						<% } %>
					</div>
				</div>
				<div id="user-info" class="col-auto py-2">
					<% String tipo = (usrProfile instanceof DtProfesorExt) ? "Profesor":"Socio"; %>
					<p><strong id="user-nickname"> <%=usrProfile.getNickname()%> </strong> <a id="user-type"><small class="text-muted"> (<%=tipo%>)</small>  </a></p>
					<p><strong>Nombre: </strong> <%=usrProfile.getNombre()%> <strong>Apellido: </strong> <%=usrProfile.getApellido()%> </p>
					<p><strong>Correo: </strong> <%=usrProfile.getEmail()%> </p>
					<p><strong>Seguidores: </strong> <%=usrProfile.getSeguidoresNickname().size()%> <strong>Seguidos: </strong> <%=usrProfile.getSeguidosNickname().size()%></p>
				</div>
				<%if (usrProfile instanceof DtProfesorExt) { %>
				<div  id="user-infoRating" class="col-sm-5 py-3">
				  <div class="row">
				  			 <% List<?> val = (List<?>) request.getAttribute("valoraciones"); %>
		                     <% int total = val.size(); %>
		                     <% float promedio = (float) request.getAttribute("valoracion"); %>
	                        <div class="col-sm-5 user-estrelltasYPuntaje">
	                          <div class="row">
	                            <a id="user-rating"><strong id="usr-promedio"><%=promedio%></strong></a>
	                          </div>
	                          <div class="estrellitas">
						      <div id="estrellitas-5" class= "row">
						        <div id="estrellitas-exterior">
						          <div id="estrellitas-interior">
						            <!-- aca se cargan las estrellas -->
						          </div>
						        </div>
						        <div id="estrellitas-votos">
						            Votos: <%=total%>
						        </div>
						      </div>
						    </div>
		                   </div>
		                   <div id="estrellitas-cantEstrellas" class="col-1">
		                     <% int valoraciones[] = new int[6]; %>
		                     <% for (int i = 1; i <= 5; i++) { %>
		                     	<% valoraciones[i] = 0; %>
		                     <% } %>
		                     <% for (Object v: val) { %>
		                     	<% valoraciones[(int) v]++; %>
		                     <% } %>
		                     <p class="puntaje"><strong>5</strong></p>
		                     <p class="puntaje"><strong>4</strong></p>
		                     <p class="puntaje"><strong>3</strong></p>
		                     <p class="puntaje"><strong>2</strong></p>
		                     <p class="puntaje"><strong>1</strong></p>
		                   </div>
		                   <div id="estrellitas-barras" class="col-6">
		                   	 <% for (int i = 5; i >= 1; i--) { %>
						      <div class="progress">
								<div class="progress-bar" role="progressbar" style="width:<%=100*((float) valoraciones[i])/total%>%" aria-valuemin="0" aria-valuemax="100">
								  <div class="porcentajes"><strong><%=valoraciones[i]%></strong></div>
								</div>
						      </div>
							 <% } %>
						  </div>
	             </div>
	           </div>
	           <% } %>
			</div>
		</div>

		<div id="user-inferior" class= "row ">
			<div id="user-navegador" class="row">
				<nav class="nav nav-pills flex-column flex-sm-row">
					<button id="nav-perfil" type="button" onclick="cambioNavegador('user-consultaPerfil', 'nav-perfil')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-radius-0 active" >Perfil</button>
					<% if (usrProfile instanceof DtSocioExt) {%>
					<button id="nav-inscripciones" type="button" onclick="cambioNavegador('user-consultaInscripciones', 'nav-inscripciones')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " >Inscripciones</button>
					<% } else { %>
					<button id="nav-clasesDictadas" type="button" onclick="cambioNavegador('user-consultaClases', 'nav-clasesDictadas')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 ">Clases</button>
					<% } %>
					<button id="nav-seguidores" type="button" onclick="cambioNavegador('user-seguidores', 'nav-seguidores')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " >Seguidores</button>
					<button id="nav-seguidos" type="button" onclick="cambioNavegador('user-seguidos', 'nav-seguidos')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 ">Seguidos</button>
					<% if ((usrProfile instanceof DtSocioExt) && (usrLogged != null) && usrLogged.getNickname().equals(usrProfile.getNickname())) {	/*Socio mirando su propio perfil*/ %>	
					<button id="nav-cuponeras" type="button" onclick="cambioNavegador('user-cuponeras', 'nav-cuponeras')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " >Cuponeras</button>
					<button id="nav-premios" type="button" onclick="cambioNavegador('user-premios', 'nav-premios')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " >Premios</button>
					<% } %>
					<% if (usrProfile instanceof DtProfesorExt) { %>
					<button id="nav-actAsoc" type="button" onclick="cambioNavegador('user-consultaAD', 'nav-actAsoc')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 ">Actividades Asociadas</button>
					<button id="nav-actIng"  type="button" onclick="cambioNavegador('user-consultaADI', 'nav-actIng')" class="user-nav flex-sm-fill text-sm-center nav-link border border-bottom-radius-0 p-2 " >Actividades Ingresadas</button>
					<% } %>
				</nav>
			</div>
			<div id="user-consultaPerfil" class="col-sm-11 border">
              <div class="mb-3">
                <div class="card-body">
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Nombre completo:</strong></h6>
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
                  	  <% if (usrProfile instanceof DtProfesorExt) { %>
                  	  <br>
	                  <div class="row">
	                    <div class="col-sm-3">
	                      <h6 class="mb-0"><strong>Institución Asociada:</strong></h6>
	                    </div>
	                    <div class="col-sm-9 text-secondary">
	                      <%= ((DtProfesorExt)usrProfile).getNombreInstitucion() %>
	                      <br>
	                    </div>
	                  </div>
	                  <br>
	                  <div  class="row">
	                    <div class="col-sm-3">
	                      <h6 class="mb-0"><strong>Descripción: </strong></h6>
	                    </div>
	                    <div class="col-sm-9 text-secondary">
	                      <%= ((DtProfesorExt)usrProfile).getDescripcion() %>
	                      <br>
	                    </div>
	                  </div>
	                  <br>
	                  <div  class="row">
	                    <div class="col-sm-3">
	                      <h6 class="mb-0"><strong>Biografía:</strong></h6>
	                    </div>
	                    <div class="col-sm-9 text-secondary">
	                      <%= ((DtProfesorExt)usrProfile).getBiografia() %>
	                      <br>
	                    </div>
	                  </div>
	                  <br>
	                  <div  class="row">
	                    <div class="col-sm-3">
	                      <h6 class="mb-0"><strong>Website:</strong></h6>
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
				<% if (usrProfile instanceof DtProfesorExt) {	/*Clases dictadas*/ %>
				<div id= "user-consultaClases" class=" border card-body">
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						
						<% List<?> clases = (List<?>) request.getAttribute("clasesDictadas"); %>
						
						<% for ( Object cl: clases ) { %>
						<% String imagenClase = (((DtClaseExt)cl).getImgName() != null) ? ((DtClaseExt)cl).getImgName():"default.png"; %>
						<li class="list-group-item container border card-body elementoLista">
							<div class="row">
								  <div class="col-auto">
							 		 <a href="<%=request.getContextPath()%>/clases?clase=<%=((DtClaseExt)cl).getNombre()%>" class="link-dark">
							 			<img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=cla&id=<%=((DtClaseExt)cl).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
							 		 </a>
							 	  </div>
							 	  <div class="col-auto">
										<b><%=((DtClaseExt)cl).getNombre()%></b>
									
								<% for ( Map.Entry<String, Set<String>> xy: (((DtProfesorExt)usrProfile).getClasesxActividades()).entrySet() ) { %>
									<% if (xy.getValue().contains(((DtClaseExt)cl).getNombre())) { %>
										<small class="text-muted">(<%=xy.getKey()%>)</small>
									<% break; } %>
								<% } %>
								</div>
							</div>
						</li>
					 <% } %>
						
						
				</ul>
			</div>
			<% } else {		/*Inscripciones*/%>
				<div id= "user-consultaInscripciones" class=" border card-body">
				
				     <% if (usrProfile instanceof DtSocioExt && (usrLogged != null) && usrProfile.getNickname().equals(usrLogged.getNickname())) {	/* Socio viendo su propio perfil */ %>
						<h6 class="row"><strong>Todas las Clases</strong></h6>
					<% } %>
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% List<?> clases = (List<?>) request.getAttribute("clasesInscripto"); %>
						<% for ( Object cl: clases ) { %>
							<li class="list-group-item container border card-body elementoLista">
								<div class="row">
									<div class="col-auto">
										<a href="<%=request.getContextPath()%>/clases?clase=<%=((DtClaseExt)cl).getNombre()%>" class="link-dark">
											 <img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=cla&id=<%=((DtClaseExt)cl).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
										 </a>
									</div>
									<div class="col-auto">
										<b><%=((DtClaseExt)cl).getNombre()%></b>
										<% for ( Map.Entry<String, Set<String>> xy: (((DtSocioExt)usrProfile).getAguadeUwu()).entrySet() ) { %>
											<% if (xy.getValue().contains(((DtClaseExt)cl).getNombre())) { %>
												<small class="text-muted">(<%=xy.getKey()%>)</small>
												<% break; }%>
									    <% } %>
									</div>
								</div>
							</li>
					<% } %>
					</ul>
					<% if (usrProfile instanceof DtSocioExt && (usrLogged != null) && usrProfile.getNickname().equals(usrLogged.getNickname())) {	/* Socio viendo su propio perfil */ %>
					<br>
					<h6 class="row"><strong>Clases de Actividades Finalizadas</strong></h6>
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for(Map.Entry<?,?> x: ( (Map<?,?>) request.getAttribute("clasesFinalizadas")).entrySet()) { %>
							<% for(Object y : (Set<?>) x.getValue()) {%>
							<li class="list-group-item container border card-body elementoLista">
								<div class="row">
								 	<div class="col-auto">
								 		<a href="<%=request.getContextPath()%>/clases?clase=<%=((String) y)%>" class="link-dark">
											<b><%=(String) y%></b>
										<small class="text-muted">(<%=(String) x.getKey()%>)</small>
										</a>
									</div>
								</div>
							</li>
							<% } %>
						 <% } %>
					</ul>
				  <%} %>
				</div>
				<% }%>
				<div id= "user-seguidores" class=" border card-body">
				<% {  //Truco para que no se guarde la lista en memoria%>
					<% List<?> seguidores = (List<?>) request.getAttribute("seguidores"); %>
					<ul id="listaActividadesActDep" class="list-group list-group-horizontal-sm">
						<% for ( Object u: seguidores ) { %>
							<% String imagenSeguidor = (((DtUsuarioExt)u).getImagen() != null) ? new String(((DtUsuarioExt)u).getImagen(), "UTF-8"):"default.png"; %>
							
							<li class="list-group-item container border card-body elementoLista elementoListaPequenio">
								<div class="row">
									<div class="col-auto">
									 	<a href="<%=request.getContextPath()%>/usuarios?nickname=<%=((DtUsuarioExt)u).getNickname()%>" class="link-dark">
									 		<img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=((DtUsuarioExt)u).getNickname()%>" class="vertical-align-middle imagenSeleccionable">
									 	</a>
								 	</div>
								 	<div class="col-auto">
										<b><%=((DtUsuarioExt)u).getNickname()%></b>
									</div>
								</div>
							</li> 
						<% } %>
					</ul>
				<% } %>
				</div>
				<div id= "user-seguidos" class=" border card-body" >
				<% {  //Truco para que no se guarde la lista en memoria%>
					<% List<?> seguidos = (List<?>) request.getAttribute("seguidos"); %>
					<ul id="listaActividadesActDep" class="list-group list-group-horizontal-sm">
						<% for ( Object u: seguidos ) { %>
						<% String imagenSeguido = (((DtUsuarioExt)u).getImagen() != null) ? new String(((DtUsuarioExt)u).getImagen(), "UTF-8"):"default.png"; %>
						<li class="list-group-item container border card-body elementoLista elementoListaPequenio">
							<div class="row">
								<div class="col-auto">
							 		<a href="<%=request.getContextPath()%>/usuarios?nickname=<%=((DtUsuarioExt)u).getNickname()%>" class="link-dark">
							 			<img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=((DtUsuarioExt)u).getNickname()%>" class="vertical-align-middle imagenSeleccionable">
							 		</a>
							 	</div>
							 	<div class="col-auto">
									<b><%=((DtUsuarioExt)u).getNickname()%></b>
								</div>
							</div>
						</li>
						<% } %>
				</ul>
			  </div>
			  <% } %>
				<% if (usrProfile instanceof DtSocioExt && (usrLogged != null) && usrProfile.getNickname().equals(usrLogged.getNickname())) {	/* Socio viendo su propio perfil */ %>
				<% List<?> cups = (List<?>) request.getAttribute("cuponeras"); %>
				<div id= "user-cuponeras" class="col-sm-9 border card-body">
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for ( Object cup: cups ) { %>
						<% String imagenCup = (((DtCuponera)cup).getNombre() != null) ? ((DtCuponera)cup).getNombre():"default.png"; %>
						<li class="list-group-item container border card-body elementoLista elementoListaPequenio">
							<div class="row">
								<div class="col-auto">
								 <a href="<%=request.getContextPath()%>/cuponeras?cuponera=<%=((DtCuponera)cup).getNombre()%>" class="link-dark">
								 	<img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=cup&id=<%=((DtCuponera)cup).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
								 </a>
								 </div>
								 <div class="col-auto">
									<b><%=((DtCuponera)cup).getNombre()%></b>
								</div>
							</div>
						</li>
						<% } %>
					</ul>
				</div>
				<% Map<?,?> premios = (Map<?,?>) request.getAttribute("premios"); %>
				<div id= "user-premios" class="row col-sm-9 border card-body">
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% int i = 0; %>
						<% for ( Object p: premios.keySet() ) { %>
						<% i++; %>
						<li class="list-group-item container border card-body elementoLista">
						  <div class="row">
							<div class="col-auto">
							 <img alt="trophy" src="<%=request.getContextPath()%>/assets/images/misc/trophy.png" class="vertical-align-middle imagenSeleccionable">
								<b><%="Premio #" + i%></b>
							 </div>
							 <div class="col-auto">
							    <div>
							    	<strong><%= (String) p%></strong>
							    	<% for ( Map.Entry<String, Set<String>> xy: (((DtSocioExt)usrProfile).getAguadeUwu()).entrySet() ) { %>
											<% if (xy.getValue().contains((String) p)) { %>
												<small class="text-muted">(<%=xy.getKey()%>)</small>
											<% break; }%>
									<% } %>
								</div>
							 	<div>
							 		<%= (((DtPremio) premios.get(p)).getFechaSorteo()).toWebFecha() %>
							 	</div>
					    	</div>
							 <div id="imagenSeleccionableSecundaria" class="col-sm">
							 	<a href="<%=request.getContextPath()%>/generarComprobante?id=<%=usrProfile.getNickname()%>&cla=<%=(String)p%>"><img alt="PDF" src="<%=request.getContextPath()%>/assets/images/misc/certificate.png" class="vertical-align-middle imagenSeleccionableSecundaria"></a>
					    	</div>
					      </div>
					    </li>
						<% } %>
						</ul>
				</div>

				<% } else if (usrProfile instanceof DtProfesorExt) {%>
				
				<% List<?> dtadas = (List<?>) request.getAttribute("actividadesAsociadas"); %>
				
				<div id= "user-consultaAD" class="col-sm-9 border card-body" >
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for ( Object ad: dtadas ) { %>
						<li class="list-group-item container border card-body elementoLista elementoListaPequenio">
							<div class="row">
								<div class="col-auto">
									<a href="<%=request.getContextPath()%>/actividades?actividad=<%=((DtActividadDeportivaExt)ad).getNombre()%>" class="link-dark">
							 			<img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=act&id=<%=((DtActividadDeportivaExt)ad).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
							 		</a>
							 	</div>
							 	<div class="col-auto">
									<b><%=((DtActividadDeportivaExt)ad).getNombre()%></b>
								</div>
							</div>
						</li>
						<% } %>
					</ul>
				</div>
				<div id= "user-consultaADI" class="col-sm-9 border card-body" >
				
				<% List<?> dtad = (List<?>) request.getAttribute("actividadesIngresadas"); %>
				
				<% if ((usrLogged != null) && (usrProfile.getNickname().equals(usrLogged.getNickname()))) { /* Profesor viendo su perfil*/%>
				<h5>Actividades Aceptadas</h5>
				<% } %>
					<ul id="listaActividadesClases" class="list-group list-group-horizontal">
						<% for ( Object ad: dtad ) { %>
							<% if (((DtActividadDeportivaExt)ad).getEstado() == TEstado.aceptada) { %>
							<% String imagenAct = (((DtActividadDeportivaExt)ad).getImgName() != null) ? ((DtActividadDeportivaExt)ad).getImgName():"default.png"; %>
							<li class="list-group-item container border card-body elementoLista elementoListaPequenio">
								<div class="row">
									<div class="col-auto">
							 			<a href="<%=request.getContextPath()%>/actividades?actividad=<%=((DtActividadDeportivaExt)ad).getNombre()%>" class="link-dark">
							 				<img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=act&id=<%=((DtActividadDeportivaExt)ad).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
							 			</a>
							 		</div>
							 		<div class="col-auto">
										<b><%=((DtActividadDeportivaExt)ad).getNombre()%></b>
									</div>
								</div>
							 </li>
							<% } %>
						 <% } %>
					</ul>
					<% if ((usrLogged != null) && (usrProfile.getNickname().equals(usrLogged.getNickname()))) { %>
					<br>
					<h5>Actividades Finalizadas</h5>
					  <ul id="listaActividadesClasesFin" class="list-group list-group-horizontal">
					  	<% for ( Object ad: dtad ) { %>
					  		<% if (((DtActividadDeportivaExt)ad).getEstado() == TEstado.finalizada) { %>
							<li class="list-group-item container border card-body elementoLista elementoListaPequenio">
								<div class="row">
							 		<div class="col-auto">
							 			<a href="<%=request.getContextPath()%>/actividades?actividad=<%=((DtActividadDeportivaExt)ad).getNombre()%>" class="link-dark">
							 				<b><%=((DtActividadDeportivaExt)ad).getNombre()%></b>
							 			</a>
							 		</div>
								  </div>
							 </li>
							<% } %>
						<% } %>
					  </ul>
					<br>
					<h5>Actividades Ingresadas</h5>
					  <ul id="listaActividadesClases" class="list-group list-group-horizontal">
					  	<% for ( Object ad: dtad ) { %>
					  		<% if (((DtActividadDeportivaExt)ad).getEstado() == TEstado.ingresada) { %>
							<li class="list-group-item container border card-body elementoLista elementoListaPequenio">
								<div class="row">
									<div class="col-auto">
										 <a href="<%=request.getContextPath()%>/actividades?actividad=<%=((DtActividadDeportivaExt)ad).getNombre()%>" class="link-dark">
										 	<img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=act&id=<%=((DtActividadDeportivaExt)ad).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
										 </a>
									</div>
									<div class="col-auto">
										<b><%=((DtActividadDeportivaExt)ad).getNombre()%></b>
									</div>
								</div>
							 </li>
							<% } %>
						<% } %>
					  </ul>
					<br>
					<h5>Actividades Rechazadas</h5>
					  <ul id="listaActividadesClases" class="list-group list-group-horizontal">
					  	<% for ( Object ad: dtad ) { %>
							<% if (((DtActividadDeportivaExt)ad).getEstado() == TEstado.rechazada) { %>
							<li class="list-group-item container border card-body elementoLista elementoListaPequenio">
								<div class="row">
									<div class="col-auto">
										<a href="<%=request.getContextPath()%>/actividades?actividad=<%=((DtActividadDeportivaExt)ad).getNombre()%>" class="link-dark">
											<img alt="Qries" src="<%=request.getContextPath()%>/api/content?c=act&id=<%=((DtActividadDeportivaExt)ad).getNombre()%>" class="vertical-align-middle imagenSeleccionable">
										</a>
									</div>
									<div class="col-auto">
										<b><%=((DtActividadDeportivaExt)ad).getNombre()%></b>
									</div>
								</div>
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
                <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                <h2 class="fw-bold mb-0">Modificar datos de Usuario</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="formulario-modif" action="<%=request.getContextPath()%>/modificarDatosUsuario?nickname=<%=usrProfile.getNickname()%>" method="POST" onsubmit="return modif()" enctype="multipart/form-data" accept-charset="UTF-8">
                   <h5>Cambiar contraseña <small class="text-muted">(Opcional)</small></h5>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control rounded-4" name="pas1" id="pas1">
                        <label for="pas1">Contraseña</label>                  
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control rounded-4" name="pas2" id="pas2">
                        <label for="pas2">Confirmar Contraseña</label>                  
                    </div>
                    <h5>Sobre ti</h5>
                    <div id="nombreCompletoDiv" class="row form-floating mb-3">
                        <div id="divNombre" class="col-6 form-check float-left">
                            <div class="form-floating mb-2">
                                <input type="text" class="form-control rounded-4" id="nomm" name="nomm" value="<%=usrProfile.getNombre()%>">
                                <label for="nomm">Nombre</label>           
                            </div>      
                        </div>
                        <div id="divApellido" class="col-6 form-check float-left">
                            <div class="form-floating mb-2">
                                <input type="text" class="form-control rounded-4" id="ape" name="ape" value="<%=usrProfile.getApellido()%>">
                                <label for="ape">Apellido</label>           
                            </div>                           
                        </div>             
                    </div>
                    <div class="form-floating mb-3">
                        <input type="date" class="form-control rounded-4" id="nac" name="nac" value="<%=usrProfile.getFechaNacimiento().toWebFecha()%>">
                        <label for="nac">Fecha de nacimiento</label>     
                    </div>
                  	<div class="mb-3">
						  <label for="formFile" class="form-label">Cambiar foto de Perfil</label>
						  <input class="form-control" type="file" name="formFile" id="formFile" accept=".jpg, .jpeg, .png, .webp, .gif, .tiff">
					</div>
					<% if (usrProfile instanceof DtProfesorExt) { %>
						<div id="modifdescDiv" class="form-group form-floating mb-3">
	                        <textarea class="form-control" id="desc" name="desc" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' ><%=((DtProfesorExt)usrProfile).getDescripcion()%>
	                        </textarea>
	                        <label for="desc">Descripción</label>     
	                    </div>
	                    <div id="newbioDiv" class="form-group form-floating mb-3">
	                        <textarea class="form-control" id="bio" name="bio" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' ><%=((DtProfesorExt)usrProfile).getBiografia()%></textarea>
	                        <label for="desc">Biografía <i style="font-size:0.7rem;"> (opcional)</i></label>     
	                    </div>
	                    <div id="newwebDiv" class="form-floating mb-3">
	                        <input type="text" class="form-control rounded-4" name="webs" id="webs"  value="<%=((DtProfesorExt)usrProfile).getLink()%>">
	                        <label for="webs">Sitio web <i style="font-size:0.7rem;"> (opcional)</i></label>
	                    </div>
                  	<% } %>
                    <button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit" >Confirmar</button>
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
