package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.GestorWeb;
import logica.LaFabrica;
import datatypes.DtSocioExt;
import datatypes.DtActividadDeportivaExt;
import excepciones.InstitucionException;
import excepciones.ActividadDeportivaException;
import excepciones.UsuarioNoExisteException;

@WebServlet ("/api/actividadDeportiva")
public class ActividadDeportivaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ActividadDeportivaServlet() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, 
    		HttpServletResponse response) throws ServletException, IOException {
    	String nombreActDep = request.getParameter("actDep");
		DtActividadDeportivaExt datosActDep = null;
    	String nickUser = (String) request.getSession().getAttribute("nickname");
    	boolean esSocio = false;
		try {
			datosActDep = buscarActDep(nombreActDep);
		} catch(ActividadDeportivaException e) {
			request.setAttribute("actDep", null);
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
			return;
		}
		try {
			if (GestorWeb.getInstance().buscarUsuario(nickUser) instanceof DtSocioExt) {
				esSocio = true;
			}
		} catch(UsuarioNoExisteException ignore) { } 
		request.setAttribute("actDep", datosActDep);
		request.setAttribute("esSocio", esSocio);
		request.getRequestDispatcher("/pages/actividadDeportiva.jsp").forward(request, response);
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
	
	private DtActividadDeportivaExt buscarActDep(String nombreActDep) throws ActividadDeportivaException {
    	DtActividadDeportivaExt datosActDep = LaFabrica.getInstance().obtenerIActDeportivaController().buscarActDep(nombreActDep);
    	return datosActDep;
    }
}