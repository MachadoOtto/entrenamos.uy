package servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.GestorWeb;
import tools.Parametrizer;
import logica.LaFabrica;
import datatypes.DtSocioExt;
import datatypes.DtUsuarioExt;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtClaseExt;
import datatypes.DtCuponera;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;

@WebServlet ("/actividades")
public class Actividades extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Actividades() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	String nombreActDep = request.getParameter("actividad");
		DtActividadDeportivaExt datosActDep = null;
    	String nickUser = (String) request.getSession().getAttribute("nickname");
    	boolean esSocio = false;
    	DtUsuarioExt datosCreador = null;
    	String institucion = null;
    	Set<DtClaseExt> datosClases = new HashSet<>();
    	Set<DtCuponera> datosCuponeras = new HashSet<>();
    	
		try {
			datosActDep = buscarActDep(nombreActDep);
			institucion = LaFabrica.getInstance().obtenerIDictadoClaseController().obtenerInstitucionActDep(datosActDep.getNombre());
		} catch(ActividadDeportivaException e) {
			request.setAttribute("nombreActDep",  null);
			request.setAttribute("institucion",  null);
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
			return;
		}
		
		if (datosActDep != null) {
			if (datosActDep.getCreador().equals("Administrador")) {
				datosCreador = new DtUsuarioExt("Administrador", null, null, null, null, null, "Administrador.png".getBytes(), null, null);
				request.setAttribute("datosCreador",  datosCreador);
			} else {
				try {
					datosCreador = LaFabrica.getInstance().obtenerIUsuarioController().seleccionarUsuario(datosActDep.getCreador());
					request.setAttribute("datosCreador",  datosCreador);
				} catch(UsuarioNoExisteException ignore) { }
			}
		}
		
		try {
			if (LaFabrica.getInstance().obtenerIUsuarioController().seleccionarUsuario(nickUser) instanceof DtSocioExt) {
				esSocio = true;
			}
		} catch(UsuarioNoExisteException ignore) { } 
		
		//Set de DtClaseExt
		try {
			Set<String> clases = datosActDep.getClases();
			for (String clase : clases) { 
				datosClases.add(LaFabrica.getInstance().obtenerIDictadoClaseController().buscarClase(clase));
			}
		} catch(ClaseException e) {
			request.setAttribute("datosClases",  null);
		}
		
		//Set de DtCuponera
		try {
			Set<String> cuponeras = datosActDep.getCuponeras();
			for (String cup : cuponeras) {
				datosCuponeras.add(LaFabrica.getInstance().obtenerICuponeraController().seleccionarCuponera(cup));
			}
		} catch(Exception e) {
			request.setAttribute("datosCuponeras",  null);
		}
		request.setAttribute("actDep",  datosActDep);
		request.setAttribute("institucion",  institucion);
		request.setAttribute("esSocio",  esSocio);
		request.setAttribute("datosClases",  datosClases);
		request.setAttribute("datosCuponeras",  datosCuponeras);
		request.getRequestDispatcher("pages/actividades.jsp").forward(request,  response);
	}
    
    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		processRequest(request,  response);
	}
    
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	
	private DtActividadDeportivaExt buscarActDep(String nombreActDep) throws ActividadDeportivaException {
    	DtActividadDeportivaExt datosActDep = LaFabrica.getInstance().obtenerIActDeportivaController().buscarActDep(nombreActDep);
    	return datosActDep;
    }
}
