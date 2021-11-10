package servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtProfesorExt;
import datatypes.DtUsuarioExt;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import models.IDictadoClaseController;
import models.LaFabricaWS;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/realizarSorteo")
public class RealizarSorteo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IDictadoClaseController IDCC;
    public RealizarSorteo() {
        super();
        IDCC = LaFabricaWS.getInstance().obtenerIDictadoClaseController();
    } 
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
		String claname = (String) request.getParameter("cla");
		DtUsuarioExt usrLogged = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
		if(usrLogged instanceof DtProfesorExt) {
			DtProfesorExt prof = (DtProfesorExt) usrLogged;
			for(Entry<String, Set<String>> x: prof.getClasesxActividades().entrySet()) {
				if(x.getValue().contains(claname)) {
					try {
						IDCC.sortearPremios(prof.getNombreInstitucion(), x.getKey(), claname);
					} catch (InstitucionException | ClaseException | ActividadDeportivaException e) {
						response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
						e.printStackTrace();
						return;
					}
					response.sendRedirect(request.getContextPath() +"/clases?clase=" + URLEncoder.encode(claname,"utf-8"));
					return;
				}
			}
			response.sendRedirect(request.getContextPath() + "/pages/403.jsp");
			return;
		}
    }
    
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		processRequest(request,  response);
	}
	
}