<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="datatypes.DtCuponera"%>
<%@ page import="datatypes.DtUsuarioExt"%>
<%@ page import="datatypes.DtFecha"%>
<%@ page import="datatypes.DtSocioExt"%>
<%@ page import="datatypes.DtClasesCuponera"%>
<%@ page import="servlets.Login"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="servlets.Cuponeras"%>


<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/cup.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <title>Entrenamos.uy</title>
  </head>
  <body>
    <jsp:include page="/template/header.jsp"/>

    <!--CUERPO DE LA PAGINA WEB-->
    <div class="container-fluid mt-4">
	  <div class="row mx-3 mx-md-5">
        <div class="ins-cat col-sm-3">
          <jsp:include page="/template/stdLeftSection.jsp"/>
        </div>
        
        <div id="user-general" class="col-sm-6">
        <%
          Boolean comprada = false;
          DtUsuarioExt usrLogged = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
          DtCuponera c = (DtCuponera) request.getAttribute("cuponera");
          if (usrLogged != null && usrLogged instanceof DtSocioExt){
        	  DtSocioExt usr = (DtSocioExt)usrLogged;
        	  Set<String> cups = usr.getCuponerasCompradas();
        	  System.out.print("Empieza el caso de uso:" +c.getNombre() + "\n");
        	  System.out.print("Cuponeras:= \n");
              for(String x: cups) {
            	  
            	  System.out.print(x+"\n");
            	  
      			if(x == c.getNombre()){
              		comprada= true;
              	}  
         	 }
          }
        %>
   	       <div id="nombreCup">
    	   </div>
 	       <div id="supbox" class = "row" >
  		      	<div class = "col" id= "coco" >
						<img alt="Qries" id="cupImg" src="<%=request.getContextPath()%>/assets/images/cups/<%= c.getNombre() %>.gif">
        		</div>
        		<div class = "col" id= "comprar">
        			<h1><%= c.getNombre() %></h1>
    	    	</div>
    	    	<div class = "col" id= "coco"> 
    	    	<%  Boolean vot = (usrLogged != null) && (usrLogged instanceof DtSocioExt) && (comprada == false); 
    	          System.out.print("Comprada:= "+ comprada+"\n");
    	    	if(vot == true){ %>
					<button type="button" class="btn btn-primary" id="boto" onclick="location.href='<%=request.getContextPath()%>/ComprarCuponera?cuponera=<%=c.getNombre()%>'">
			            	Comprar
			        </button>	
			      <%}%>
    	    	</div>
          </div>   
          <div class = "row">
        	<div class = "col"id= "coco"> 
        	 <div class="card-body">
        	 	  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Descripción:</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        <%= c.getDescripcion() %>
                    </div>
                  </div>
                    
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0" id= "valido"><strong>Período de validez:</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary" id= "valido">
                        <%= c.getFechaInicio().toFecha() %> - <%= c.getFechaFin().toFecha() %>
                    </div>
                  </div>
                  <br>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Precio:</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                         <%= c.getCosto() %>
                    </div>
                  </div>
                  <br>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Descuento aplicado:<strong></strong></strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <%= c.getDescuento() %>
                    </div>
                  </div>
                  <br>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Fecha de registro:</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        <%= c.getFechaAlta().toFecha() %>
                    </div>
                  </div>  
                        
        	</div>
         </div>        	
        </div>
        </div>
        


        <div class="col-sm-3 ps-1 ps-sm-2" id = "der">
            <div class = "row"id= "coco">
                <div class = "row extraInfoDiv" id = "acts">
                    <h3>Actividades Deportivas</h3>
                    <% 
                    	List<DtClasesCuponera> acts = c.getContenido();
                    	for (DtClasesCuponera x :acts){
                    
                    %>
                    <div class = "row card-body elementoLista">
                        <div class = "col">
                            <img class= "im" alt="Qries" src="<%=request.getContextPath()%>/assets/images/activities/<%= x.getNombreActividad() %>.jpg">
                        </div>
                        <div class = "col" id = "act2">
                          <a href="<%=request.getContextPath()%>/actividades?actividad=<%= x.getNombreActividad()%>"> <%= x.getNombreActividad() %>  </a>
                        </div>
                        
                    </div>
                    	<%}%>
                </div> 
                
                
                <div class = "row extraInfoDiv" id= "cats">
                    <h3>Categorías</h3>
                    <%
                    	List<String> cats = c.getCategorias();
                    	for (String x :cats){ 
                    %>
                    <div class = "row card-body elementoLista">
                        <div class = "col">
                            <img class= "im" alt="Qries" src="<%=request.getContextPath()%>/assets/images/categories/<%=x %>.gif">
                        </div>
                        <div class = "col" id= "act2">
                          <a href="<%=request.getContextPath()%>/search?actividades=yes&cuponeras=yes&fltrC1=<%=x%>"> <%=x%>  </a>
                        </div>
                    </div>
                       <%}%>
                       <jsp:include page="/template/stdRightSection.jsp"/>
                </div>           	
                     
          
               </div>
        </div>
      </div>
    </div>

<jsp:include page="/template/footer.jsp"/>

    </body>
</html>