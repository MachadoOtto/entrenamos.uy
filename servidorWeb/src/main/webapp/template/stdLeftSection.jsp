<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="servlets.Login"%>
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtProfesorExt"%>
<%@ page import="java.util.Set"%>
<%! @SuppressWarnings("unchecked") %>
<% 
DtUsuarioExt u = (DtUsuarioExt)request.getSession().getAttribute("loggedUser");
if(u!=null){
	%>
	<nav style="margin-bottom: 3em;" class="extraInfoDiv ins-cat-section nav flex-column">
	<h1 class="fs-5">Acciones</h1>
       <button type="button" id="btn-myUser" class="btn btn-link" onclick="location.href='<%=request.getContextPath()%>/users?nickname=<%=u.getNickname()%>'">
        	Ir a mi perfil
    	</button>
	<% if(u instanceof DtProfesorExt){ %>
	   <button type="button" id="btn-altaActDep" class="btn btn-link" data-bs-toggle="modal" data-bs-target="#altaActModal">
            Alta Actividad Deportiva
      </button>
      <% } %>
      
	  </nav>
<%}%>

<nav class="extraInfoDiv ins-cat-section nav flex-column">
  <h1 class="fs-5">Instituciones</h1>
  <%
  Set<String> s = (Set<String>)request.getAttribute("instituciones");
  for(String x: s){ %>
  	<a class="nav-link" href="<%=request.getContextPath()%>/instituciones?institucion=<%=x%>"><%=x%></a>
  <%} %>
</nav>
<nav class="extraInfoDiv ins-cat-section nav flex-column mt-5">
  <h1 class="fs-5">Categorías</h1>
  <%
  Set<String> s2 = (Set<String>)request.getAttribute("categorias");
  for(String x: s2){ %>
  	<a class="nav-link" href="<%=request.getContextPath()%>/categorias?categoria=<%=x%>"><%=x%></a>
  <%} %>
</nav>