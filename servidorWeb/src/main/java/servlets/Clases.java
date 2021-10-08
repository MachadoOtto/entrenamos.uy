package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.GestorWeb;
import logica.LaFabrica;
import logica.IDictadoClaseController;
import datatypes.DtSocioExt;
import datatypes.DtClaseExt;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;

@WebServlet ("/api/clases")
public class Clases extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Clases() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, 
    		HttpServletResponse response) throws ServletException, IOException {
    	String nombreClase = request.getParameter("clase");
		DtClaseExt datosClase = null;
    	String nombreActividad = null;
    	String nickUser = (String) request.getSession().getAttribute("nickname");
    	boolean esSocio = false;
		try {
			datosClase = buscarClase(nombreClase);
			nombreActividad = nombreActDeClase(nombreClase);
		} catch(ClaseException ex) {
			// la clase no existe
			request.setAttribute("clase", null);
			request.setAttribute("actividad", null);
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
			return;
		}
		try {
			if (GestorWeb.getInstance().buscarUsuario(nickUser) instanceof DtSocioExt) {
				esSocio = true;
			}
		} catch(UsuarioNoExisteException ignore) { } 
		// setea los datos
		request.setAttribute("clase", datosClase);
		request.setAttribute("actividad", nombreActividad);
		request.setAttribute("esSocio", esSocio);
		request.getRequestDispatcher("/pages/clases.jsp").forward(request, response);
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
	
	/* buscarClase es una funcion nueva que busca una Clase solo por su
	 nombre sin necesidad de conocer la Institucion o la ActDeportiva */
	private DtClaseExt buscarClase(String nombreClase) throws ClaseException {
    	DtClaseExt datosClase = LaFabrica.getInstance().obtenerIDictadoClaseController().buscarClase(nombreClase);
    	return datosClase;
    }
    
    private String nombreActDeClase(String nombreClase) {
    	String nombreActividad = null;
    	IDictadoClaseController IDCC = LaFabrica.getInstance().obtenerIDictadoClaseController();
		for (String x: IDCC.obtenerInstituciones()) {
			try {
				for (String y: IDCC.obtenerActividades(x)) {
					if (IDCC.obtenerClases(x, y).contains(nombreClase)) {
						nombreActividad = y;
						return nombreActividad;
					}
				}
			} catch(InstitucionException ignore) { }
		}
		return nombreActividad;
    }
}