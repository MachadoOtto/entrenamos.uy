<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<nav class="extraInfoDiv nav-listados nav-info nav flex-column">
	 <h1 class="fs-5">Información</h1>
	 <a class="nav-link" href="usuarios">Usuarios</a>
	 <a class="nav-link" href="actividadesDeportivas.">Actividades</a>
	 <a class="nav-link" href="clases">Clases</a>
	 <a class="nav-link" href="cuponeras">Cuponeras</a>
</nav>
<%  String x = "";
	String q = (String) request.getParameter("e");
	if(q!=null)
		x=q;
%>
<div id="tostadas" class="toast-container position-absolute bottom-0 end-0 p-3" data-tcode="<%=x%>">

</div>