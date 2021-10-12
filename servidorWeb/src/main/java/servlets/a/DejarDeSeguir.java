package servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtActividadDeportivaExt;
import datatypes.DtFecha;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuarioExt;
import logica.IUsuarioController;
import logica.LaFabrica;
import models.GestorWeb;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/api/dejarDeSeguir")
public class DejarDeSeguir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
    public DejarDeSeguir() {
        super();
        IUC = LaFabrica.getInstance().obtenerIUsuarioController();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		IUC.dejarDeSeguir((String) request.getSession().getAttribute("user"),request.getParameter("nickname"));
		} catch (Exception e){
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
		}
    	request.getRequestDispatcher("pages/perfilUsuario.jsp").forward(request, response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}


}