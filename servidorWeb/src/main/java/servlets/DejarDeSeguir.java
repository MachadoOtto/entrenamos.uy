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

import datatypes.DtActividadDeportivaExt;
import datatypes.DtProfesorExt;
import datatypes.DtUsuarioExt;
import excepciones.ActividadDeportivaException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;
import logica.IActividadDeportivaController;
import logica.IUsuarioController;
import logica.LaFabrica;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/dejarDeSeguir")
public class DejarDeSeguir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
	private IActividadDeportivaController IADC;
    public DejarDeSeguir() {
        super();
        IUC = LaFabrica.getInstance().obtenerIUsuarioController();
        IADC = LaFabrica.getInstance().obtenerIActDeportivaController();
    } 
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException,  UsuarioNoExisteException,  InstitucionException,  ActividadDeportivaException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	DtUsuarioExt usr = null;
    	try {
    		String nombreUsrProfile = (String) request.getParameter("nickname");
        	usr = IUC.seleccionarUsuario(nombreUsrProfile);
        	DtUsuarioExt usrLogged = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
    		
        	//Caso de uso
        	IUC.dejarDeSeguir(usrLogged.getNickname(), nombreUsrProfile);
    		
    		//Actualización y obtención de datos
    		List<DtActividadDeportivaExt> actIngresadasProfesor = new ArrayList<>();
        	if (usr instanceof DtProfesorExt) {
        		Set<String> actividades = ((DtProfesorExt)usr).getActividadesIngresadas();
        		for (String x : actividades) {
        			actIngresadasProfesor.add(IADC.getActDepExt(((DtProfesorExt)usr).getNombreInstitucion(),  x));
        		}
        	}
        	usrLogged = IUC.seleccionarUsuario(usrLogged.getNickname());
        	usr = IUC.seleccionarUsuario(usr.getNickname());
        	
        	//Envio de información actualizada
        	request.setAttribute("actividadesIngresadas",  actIngresadasProfesor);
        	request.getSession().setAttribute("loggedUser", usrLogged);
        	request.setAttribute("datoUsuario",  usr);
        	//request.getRequestDispatcher("/usuarios?nickname=" + usr.getNickname()).forward(request,  response);
        	response.sendRedirect(request.getContextPath() +"/usuarios?nickname=" + usr.getNickname());
		} catch (Exception e2) {
			e2.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
			return;
		}
    }
    
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        try {
			processRequest(request,  response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/pages/400.jsp");
			return;
		}
	}
	
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		try {
			processRequest(request,  response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/pages/400.jsp");
			return;
		}
	}


}