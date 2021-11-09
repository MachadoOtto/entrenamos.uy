package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtUsuarioExt;
import models.IActividadDeportivaController;
import models.IDictadoClaseController;
import models.IUsuarioController;
import models.LaFabricaWS;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/favoritear")
public class Favoritear extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
	private IActividadDeportivaController IADC;
	private IDictadoClaseController IDCC;
    public Favoritear() {
        super();
        IUC = LaFabricaWS.getInstance().obtenerIUsuarioController();
        IADC = LaFabricaWS.getInstance().obtenerIActDeportivaController();
        IDCC = LaFabricaWS.getInstance().obtenerIDictadoClaseController();
    } 
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	try {
    		String usu = (String) request.getParameter("usu");
    		String act = (String) request.getParameter("act");
        	DtUsuarioExt usrLogged = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
    		if(!(usu.equals(usrLogged.getNickname()))) {
    				response.sendRedirect(request.getContextPath() + "/pages/403.jsp");
    				return;
    		}
    		request.getSession().setAttribute("loggedUser",IUC.seleccionarUsuario(usrLogged.getNickname()));
    		String ins = IDCC.obtenerInstitucionActDep(act);
        	IUC.favoritearActividad(usu, ins, act);
        	
        	response.sendRedirect(request.getContextPath() +"/actividades?actividad=" + act);
		} catch (Exception e2) {
			e2.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
			return;
		}
    }
    
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		processRequest(request,  response);
	}
	
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		processRequest(request,  response);
	}


}