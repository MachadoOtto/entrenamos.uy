package servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtActividadDeportivaExt;
import datatypes.DtProfesorExt;
import datatypes.DtUsuarioExt;
import excepciones.ActividadDeportivaException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;
import models.IActividadDeportivaController;
import models.IUsuarioController;
import models.LaFabricaWS;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/finalizarActividad")
public class FinalizarActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
	private IActividadDeportivaController IADC;
    public FinalizarActividad() {
        super();
        IUC = LaFabricaWS.getInstance().obtenerIUsuarioController();
        IADC = LaFabricaWS.getInstance().obtenerIActDeportivaController();
    } 
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	DtUsuarioExt usr = null;
    		String actname = (String) request.getParameter("act");
    		DtActividadDeportivaExt actdt;
			try {
				actdt = IADC.buscarActDep(actname);
			} catch (ActividadDeportivaException e) {
				response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
				e.printStackTrace();
				return;
			}
        	DtUsuarioExt usrLogged = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
    		if(usrLogged.getNickname().equals(actdt.getCreador())) {
    			IADC.finalizarActividad(actname);
    		}
    		else {
    			response.sendRedirect(request.getContextPath() + "/pages/403.jsp");
    			return;
    		}
    		try {
				request.getSession().setAttribute("loggedUser", IUC.seleccionarUsuario(usrLogged.getNickname()));
			} catch (UsuarioNoExisteException e) {
				e.printStackTrace();
			}
    		response.sendRedirect(request.getContextPath() +"/actividades?actividad=" +URLEncoder.encode( actname,"utf-8"));
    		return;
    }
    
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		processRequest(request,  response);
	}
	
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		try {
			processRequest(request,  response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
			return;
		}
	}


}