<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="servlets.Login"%>
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtProfesorExt"%>
<%@ page import="logica.LaFabrica"%>
<%@ page import="logica.IUsuarioController"%>

<nav id="header" class="col-12 navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand fs-2 me-5 mb-2 mt-2 text-primary" href="<%=request.getContextPath()%>/home">
            <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EUimg" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
            Entrenamos.uy
        </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <form class="d-flex me-5 pe-5 container-fluid">
            <input class="form-control me-2" type="search" placeholder="Actividades Deportivas, Cuponeras" aria-label="Search">
            <button type="submit" class="btn btn-outline-primary">Buscar</button>
        </form>
        
        <% if(request.getSession().getAttribute("nickname")==null){ %>
        <ul id="nav-iniReg"  class="navbar-nav me-auto mb-2 mb-lg-0 d-flex text-right">
            <li class="nav-item">
              <button type="button" id="btn-inicio-sesion" class="btn-ir btn btn-primary mt-3 ms-3 mb-3" data-bs-toggle="modal" data-bs-target="#inicioSesionModal">
                Iniciar Sesion
              </button>
            </li>
            <li>
              <button type="button" id="btn-registrarse" class="btn-ir btn btn-primary mt-3 ms-3 mb-3" data-bs-toggle="modal" data-bs-target="#registroModal">
                Registrarse
              </button>
            </li>
        </ul>
        

        <% } else { 
        	DtUsuarioExt u = Login.getUsuarioLogueado(request);
        %>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex text-right">
               <li>
            		<a href="<%=request.getContextPath()%>/usuarios?nickname=<%=u.getNickname()%>"><img id="profilePicCreator" alt="<%=u.getNickname()%>" id="img-perfil" src="<%=request.getContextPath()%>/assets/images/users/<%= u.getNickname() %>.jpg"></img></a>
           	   </li>
            </ul>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex text-right">
               <li>
               	 <form id="logout-session" action="<%=request.getContextPath()%>/api/logout" method="GET">
                 	<button class="btn-ir btn btn-primary mt-3 ms-3 mb-3" type="submit">
                   		Cerrar Sesion
                 	</button>
                 </form>
               </li>
            </ul>        
        <% } %>
      </div>
    </div>
</nav>

<% if(request.getSession().getAttribute("nickname")==null){ %>

    <!--MODAL INICIAR SESION-->
    <div class="modal fade" id="inicioSesionModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content">
              <div class="modal-header">
                  <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                  <h2 class="fw-bold mb-0">Entrenamos.uy</h2>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                  <form id="formulario-sesion" action="javascript:iniciarSesion()" data-root="<%=request.getContextPath()%>"> 
                      <div class="form-floating mb-3">
                          <input type="text" class="form-control rounded-4" id="user" name="nick-login" placeholder="name@example.com">
                          <label for="user">Correo electrónico / Nickname</label>
                      </div>
                      <div class="form-floating mb-3">
                          <input type="password" class="form-control rounded-4" id="pass" name="pass-login" placeholder="Password">
                          <label for="pass">Contraseña</label>                  
                      </div>
                      <button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit">Iniciar Sesión</button>
                  </form>
              </div>
              <div class="modal-footer">
                  <hr class="my-6">
                  <div>
                      <i>Aún no tienes cuenta? </i>
                      <a id="regLink" href="#">Registrarte Aquí</a>
                  </div>
              </div>
          </div>
      </div>
    </div>

    <!--MODAL REGISTRO-->
    <div class="modal fade" id="registroModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                <h2 class="fw-bold mb-0">Unéte a Entrenamos.uy</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="formulario-registro" action="javascript:registrarse()" data-root="<%=request.getContextPath()%>">
                    <div class="form-floating mb-3">
                        <h5>Que eres?</h5>
                        <div id="socioRadio" class="form-check float-left">
                            <input class="form-check-input" type="radio" name="tipoUsuario" id="radioSocio" checked>
                            <label class="form-check-label" for="radioSocio">
                            Socio
                            </label>
                        </div>
                        <div id="profeRadio" class="form-check float-left">
                            <input class="form-check-input" type="radio" name="tipoUsuario" id="radioProfe">
                            <label class="form-check-label" for="radioProfe">
                            Profesor
                            </label>
                        </div>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control rounded-4" id="nickk" >
                        <label for="nickk">Nickname</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control rounded-4" id="emaill">
                        <label for="emaill">Correo electrónico</label>                  
                    </div>
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
                                <input type="text" class="form-control rounded-4" id="nomm" >
                                <label for="nomm">Nombre</label>           
                            </div>      
                        </div>
                        <div id="divApellido" class="col-6 form-check float-left">
                            <div class="form-floating mb-2">
                                <input type="text" class="form-control rounded-4" id="ape" >
                                <label for="ape">Apellido</label>           
                            </div>                           
                        </div>             
                    </div>
                    <div class="form-floating mb-3">
                        <input type="date" class="form-control rounded-4" id="nac" >
                        <label for="nomm">Fecha de nacimiento</label>     
                    </div>
                    <div id="institDiv" class="form-floating mb-3">
                        <select id="instit" class="form-select" data-live-search="true">
                        <% for(String t : LaFabrica.getInstance().obtenerIUsuarioController().obtenerInstituciones()){ %>
                            <option data-tokens="<%=t%>"><%=t%></option>
                        <% } %>
                        </select>
                        <label for="instit">Institución asociada</label>                               
                    </div>
                    <div id="descDiv" class="form-group form-floating mb-3">
                        <textarea class="form-control" id="desc" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' ></textarea>
                        <label for="desc">Descripción</label>     
                    </div>
                    <div id="bioDiv" class="form-group form-floating mb-3">
                        <textarea class="form-control" id="bio" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' ></textarea>
                        <label for="desc">Biografía <i style="font-size:0.7rem;"> (opcional)</i></label>     
                    </div>
                    <div id="webDiv" class="form-floating mb-3">
                        <input type="text" class="form-control rounded-4" id="webs" >
                        <label for="webs">Sitio web <i style="font-size:0.7rem;"> (opcional)</i></label>
                    </div>
					<div class="mb-3">
					  <label for="formFile" class="form-label">Imagen de perfil <i style="font-size:0.7rem;"> (opcional)</i></label>
					  <input class="form-control" type="file" id="imgPerfil">
					</div>
                    <button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit">Registrarse</button>
                </form>
            </div>
            <div class="modal-footer">
                <hr class="my-6">
                <div>
                    <i>Ya tienes cuenta? </i>
                    <a id="iniLink" href="#">Inicia Sesión Aquí</a>
                </div>
            </div>
        </div>
      </div>
    </div>
    
<% } else{
	DtUsuarioExt u = Login.getUsuarioLogueado(request);
	if(u instanceof DtProfesorExt){ %>
    <div class="modal fade" id="altaActModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                    <h2 class="fw-bold mb-0">Alta Actividad Deportiva</h2>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control rounded-4" id="floatingInput" placeholder="">
                            <label for="floatingInput">Nombre</label>
                        </div>
                                            <div id="descDiv" class="form-group form-floating mb-3">
                        <textarea class="form-control" id="desc" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' ></textarea>
                        <label for="desc">Descripción</label>     
                    </div>
                        <div id="nombreCompletoDiv" class="row form-floating mb-3">
                            <div id="divNombre" class="col-6 form-check float-left">
                                <div class="form-floating mb-2">
                                    <input type="text" class="form-control rounded-4" id="nomm" >
                                    <label for="nomm">Costo</label>           
                                </div>      
                            </div>
                            <div id="divApellido" class="col-6 form-check float-left">
                                <div class="form-floating mb-2">
                                    <input type="text" class="form-control rounded-4" id="ape" >
                                    <label for="ape">Duración</label>           
                                </div>                           
                            </div>           
                         </div>  
						<div class="mb-3">
						  <label for="formFile" class="form-label">Imagen asociada</label>
						  <input class="form-control" type="file" id="formFile">
						</div>
                        <div id="catDiv" class="form-floating mb-3">
                        <p>Categorías asociadas</p>
							<select style="width:29em;" class="cat" multiple aria-label="multiple select example">
							  <%for(String x: LaFabrica.getInstance().obtenerIActDeportivaController().obtenerCategorias()){ %>
							  	<option value="<%=x%>"><%=x%></option>
							  <%} %>
							</select>                          
                    </div>
                        
                        <button class="w-100 mb-2 btn btn-lg rounded-4 btn-primary" type="submit">Confirmar Registro</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <hr class="my-6">
                    <div>
                        <i>Entrenamos.uy - Alta Actividad Deportiva</i>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<%} %>
<%}%>