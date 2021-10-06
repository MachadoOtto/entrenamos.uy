<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ page import="datatypes.DtCuponera"%>
<%@ page import="logica.CuponeraController"%>
<%@ page import="datatypes.DtClasesCuponera"%>
<%@ page import="servlets.Login"%>

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
        <%
        
        //n=???
	  	
        CuponeraController cup; 
	  	DtCuponera c = cup.seleccionarCuponera(n);/*Conseuir cuponera*/
	  	%>
        
        <div id="user-general" class="col-sm-6">
   	       <div id="nombreCup">
    	   </div>
 	       <div id="supbox" class = "row" >
  		      	<div class = "col" id= "coco" >
						<img alt="Qries" id="cupImg" src=".././img/cups/pelota.gif">
        		</div>
        		<div class = "col" id= "comprar">
        			<h1><%  c.getNombre();%></h1>
    	    	</div>
    	    	<div class = "col" id= "coco"> 
    	    	<% if(request.getSession().getAttribute("nickname")!=null){ %>
					<button type="button" class="btn btn-primary" id="boto">
			            	Comprar
			        </button>	
			         <% }	%>	   
    	    	</div>
          </div>   
          <div class = "row">
        	<div class = "col"id= "coco"> 
        	 <div class="card-body">
        	 	  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Descripcin:</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        <% c.getDescripcion(); %>
                    </div>
                  </div>
                    
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0" id= "valido"><strong>Perodo de validez:</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary" id= "valido">
                        <% c.getFechaInicio().toFecha(); %> - <% c.getFechaFin().toFecha(); %>
                    </div>
                  </div>
                  <br>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Precio:</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                         <% c.getCosto(); %>
                    </div>
                  </div>
                  <br>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Descuento aplicado:<strong></strong></strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <% c.getDescuento(); %>
                    </div>
                  </div>
                  <br>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0"><strong>Fecha de registro::</strong></h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        <% c.getFechaAlta().toFecha(); %>
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
                            <img class= "im" alt="Qries" src=".././img/activities/<% x.getNombreActividad(); %>.jpg">
                        </div>
                        <div class = "col" id = "act2">
                          <a href=".././actividades/<% x.getNombreActividad(); %>.jsp"> <% x.getNombreActividad(); %>  </a>
                        </div>
                        
                    </div>
                    	<% } %>
                </div> 
                
                
                <div class = "row extraInfoDiv" id= "cats">
                    <h3>Categoras</h3>
                    <%
                    	List<String> cats = c.getCategorias();
                    	for (String x :cats){ 
                    %>
                    <div class = "row card-body elementoLista">
                        <div class = "col" ">
                            <img class= "im" alt="Qries" src=".././img/categories/<%=x %>.gif">
                        </div>
                        <div class = "col" id= "act2">
                          <%=x%>
                        </div>
                    </div>
                    <% } %>
                </div>           	
                     
          
               </div>
        </div>
      </div>
    </div>

<jsp:include page="/template/footer.jsp"/>

    </body>
</html>