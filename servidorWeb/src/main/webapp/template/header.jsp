<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="servlets.Login"%>
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtProfesorExt"%>
<%@ page import="java.util.Set"%>
<%! @SuppressWarnings("unchecked") %>

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
        <form class="d-flex me-5 pe-5 container-fluid" action="<%=request.getContextPath()%>/search?actividades=yes&cuponeras=yes" method="POST">
            <input class="form-control me-2" type="search" id="campoTexto" name="campoTexto" placeholder="Actividades Deportivas,  Cuponeras" aria-label="Search">
            <button type="submit" class="btn btn-outline-primary">Buscar</button>
        </form>
        
        <% if (request.getSession().getAttribute("loggedUser")==null){ %>
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
        	DtUsuarioExt u = (DtUsuarioExt)request.getSession().getAttribute("loggedUser");
        	String f = "default.png";
        	if (u.getImagen()!=null)
        		f = new String(u.getImagen(), "UTF-8");
        		
        %>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex text-right">
               <li id="wtm">
            		<a href="<%=request.getContextPath()%>/usuarios?nickname=<%=u.getNickname()%>"><img id="profilePicCreator" 
            		onerror="this.onerror=null; this.src='<%=request.getContextPath()%>/assets/images/misc/loading.gif'" alt="<%=u.getNickname()%>" id="img-perfil" src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=u.getNickname()%>"></img></a>
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

<% if (request.getSession().getAttribute("loggedUser")==null){ %>

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
                  <form id="formulario-sesion" action="<%=request.getContextPath()%>/login" method="POST" data-root="<%=request.getContextPath()%>"> 
                      <div class="form-floating mb-3">
                          <input type="text" class="form-control rounded-4" id="user" name="nick-login" placeholder="name@example.com">
                          <label for="user">Correo electrónico / Nickname</label>
                      </div>
                      <div class="form-floating mb-3">
                          <input type="password" class="form-control rounded-4" id="pass" name="pass-login" placeholder="Password">
                          <label for="pass">Contraseña</label>                  
                      </div>
                      <input style="display: none;" class="form-control miurl" name="miurl" type="text">
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
                <form id="formulario-registro" action="<%=request.getContextPath()%>/signup" method="POST" data-root="<%=request.getContextPath()%>" onsubmit="return registrarse()" accept-charset="UTF-8" enctype="multipart/form-data">
                
                    <div class="form-floating mb-3">
                        <h5>Que eres?</h5>
                        <div id="socioRadio" class="form-check float-left">
                            <input class="form-check-input" type="radio" name="tipoU" value="0" name="radioSocio" id="radioSocio" checked>
                            <label class="form-check-label" for="radioSocio">
                            Socio
                            </label>
                        </div>
                        <div id="profeRadio" class="form-check float-left">
                            <input class="form-check-input" type="radio" name="tipoU" value="1" name="radioProfe" id="radioProfe">
                            <label class="form-check-label" for="radioProfe">
                            Profesor
                            </label>
                        </div>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control rounded-4" id="nickk" name="nickk" >
                        <label for="nickk">Nickname</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control rounded-4" id="emaill" name="emaill">
                        <label for="emaill">Correo electrónico</label>                  
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control rounded-4" id="pas1" name="pas1">
                        <label for="pas1">Contraseña</label>                  
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control rounded-4" id="pas2" name="pas2">
                        <label for="pas2">Confirmar Contraseña</label>                  
                    </div>
                    <h5>Sobre ti</h5>
                    <div id="nombreCompletoDiv" class="row form-floating mb-3">
                        <div id="divNombre" class="col-6 form-check float-left">
                            <div class="form-floating mb-2">
                                <input type="text" class="form-control rounded-4" id="nomm" name="nomm">
                                <label for="nomm">Nombre</label>           
                            </div>      
                        </div>
                        <div id="divApellido" class="col-6 form-check float-left">
                            <div class="form-floating mb-2">
                                <input type="text" class="form-control rounded-4" id="ape" name="ape" >
                                <label for="ape">Apellido</label>           
                            </div>                           
                        </div>             
                    </div>
                    <div class="form-floating mb-3">
                        <input type="date" class="form-control rounded-4" id="nac" name="nac" >
                        <label for="nomm">Fecha de nacimiento</label>     
                    </div>
                    <div id="institDiv" class="form-floating mb-3">
                        <select name="instit" id="instit" class="form-select" data-live-search="true">
							  <%
							  Set<String> s = (Set<String>)request.getAttribute("stdInstituciones");
							  for (String t: s){ %>
                            <option data-tokens="<%=t%>"><%=t%></option>
                        <% } %>
                        </select>
                        <label for="instit">Institución asociada</label>                               
                    </div>
                    <div id="descDiv" class="form-group form-floating mb-3">
                        <textarea class="form-control" id="desc" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' name="descRU" ></textarea>
                        <label for="desc">Descripción</label>     
                    </div>
                    <div id="bioDiv" class="form-group form-floating mb-3">
                        <textarea class="form-control" id="bio" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' name="bioRU" ></textarea>
                        <label for="desc">Biografía <i style="font-size:0.7rem;"> (opcional)</i></label>     
                    </div>
                    <div id="webDiv" class="form-floating mb-3">
                        <input type="text" class="form-control rounded-4" id="webs" name="websRU" >
                        <label for="webs">Sitio web <i style="font-size:0.7rem;"> (opcional)</i></label>
                    </div>
					<div class="mb-3">
					  <label for="formFile" class="form-label">Imagen de perfil <i style="font-size:0.7rem;"> (opcional)</i></label>
					  <input class="form-control" type="file" id="imgPerfil" name="imgPerfil" accept=".jpg, .jpeg, .png, .webp, .gif, .tiff">
					</div>
					<input style="display: none;" class="form-control miurl" name="miurl" type="text">
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
	DtUsuarioExt u = (DtUsuarioExt)request.getSession().getAttribute("loggedUser");
	if (u instanceof DtProfesorExt){ %>
    <div class="modal fade" id="altaActModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <img src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="EntrenamosUYLogo" width="40" height="30" class="d-inline-block align-text-top img-fluid me-2 ms-2 mb-3">
                    <h2 class="fw-bold mb-0">Alta Actividad Deportiva</h2>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="formulario-altaAD" action="<%=request.getContextPath()%>/alta_ad" data-root="<%=request.getContextPath()%>" method="post" onsubmit="return altaAD()" enctype="multipart/form-data" accept-charset="UTF-8">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control rounded-4" name="nombreAD" id="nombreAD" placeholder="">
                            <label for="floatingInput">Nombre</label>
                        </div>
                                            <div id="descDiv" class="form-group form-floating mb-3">
                        <textarea class="form-control" name="descAD" id="descAD" rows="15" oninput='this.style.height = "";this.style.height = this.scrollHeight +3+ "px"' ></textarea>
                        <label for="desc">Descripción</label>     
                    </div>
                        <div id="nombreCompletoDiv" class="row form-floating mb-3">
                            <div id="divNombre" class="col-6 form-check float-left">
                                <div class="form-floating mb-2">
                                    <input type="number" class="form-control rounded-4" name="costoAD" id="costoAD" >
                                    <label for="nomm">Costo</label>           
                                </div>      
                            </div>
                            <div id="divApellido" class="col-6 form-check float-left">
                                <div class="form-floating mb-2">
                                    <input type="number" class="form-control rounded-4" name="durAD" id="durAD" >
                                    <label for="ape">Duración <i style="font-size:0.7rem;"> (min)</i></label>           
                                </div>                           
                            </div>           
                         </div>  
						<div class="mb-3">
						  <label for="formFile" class="form-label">Imagen asociada <i style="font-size:0.7rem;"> (opcional)</i></label>
						  <input class="form-control" type="file" name="imgAD" id="imgAD" accept=".jpg, .jpeg, .png, .webp, .gif, .tiff">
						</div>
                        <div id="catDiv" class="form-floating mb-3">
                        <p>Categorías asociadas <i style="font-size:0.7rem;"> (opcional)</i></p>
							<select style="width:29em;" name="catAD" id="catAD" class="cat" multiple aria-label="multiple select example">
							  <%
							  Set<String> s = (Set<String>)request.getAttribute("stdCategorias");
							  for (String x: s){ %>
							  	<option value="<%=x%>"><%=x%></option>
							  <%} %>
							</select>                          
                    </div>
                        <input style="display: none;" class="form-control miurl" name="miurl" type="text">
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