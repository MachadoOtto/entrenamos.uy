package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtActividadDeportivaExt;
import datatypes.DtClaseExt;
import datatypes.DtCuponera;
import datatypes.DtProfesorExt;
import datatypes.DtSocioExt;
import datatypes.DtUsuarioExt;
import logica.IActividadDeportivaController;
import logica.ICuponeraController;
import logica.IDictadoClaseController;
import logica.IUsuarioController;
import logica.LaFabrica;
import models.GestorWeb;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/usuarios")
public class PerfilUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IActividadDeportivaController IADC;
	private IUsuarioController IUC;
	private ICuponeraController ICC;
	private IDictadoClaseController IDCC;
    public PerfilUsuario() {
        super();
        IUC = LaFabrica.getInstance().obtenerIUsuarioController();
        IADC = LaFabrica.getInstance().obtenerIActDeportivaController();
        ICC = LaFabrica.getInstance().obtenerICuponeraController();
        IDCC = LaFabrica.getInstance().obtenerIDictadoClaseController();
    }
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
        try {
        	//Obtención de datos de usuario
        	String nickname = (String) request.getParameter("nickname");
        	if (nickname.equals("Administrador")) {
            	response.sendRedirect(request.getContextPath() + "/pages/403.jsp");
            	return;
        	}
        	DtUsuarioExt usr = IUC.seleccionarUsuario(nickname);
        	
        	//Obtención de clases dictadas (Profesor)
        	List<DtClaseExt> clasesDictadasProfesor = new ArrayList<>();
        	if (usr instanceof DtProfesorExt) {
        		Set<String> clases = ((DtProfesorExt)usr).getClasesDictadas();
        		for (String x : clases) {
        			clasesDictadasProfesor.add(IDCC.buscarClase(x));
        		}
        	}
        	
        	//Obtención de clases a las que se está inscripto (Socio)
        	List<DtClaseExt> clasesInscriptoSocio = new ArrayList<>();
        	if (usr instanceof DtSocioExt) {
        		Set<String> clases = ((DtSocioExt)usr).getClases();
        		for (String x : clases) {
        			clasesInscriptoSocio.add(IDCC.buscarClase(x));
        		}
        	}
        	
        	//Obtención de seguidores
        	List<DtUsuarioExt> seguidores = new ArrayList<>();
    		Set<String> seguidoresNick = usr.getSeguidoresNickname();
    		for (String x : seguidoresNick) {
    			seguidores.add(IUC.seleccionarUsuario(x));
        	}
        	
    		//Obtención de seguidores
        	List<DtUsuarioExt> seguidos = new ArrayList<>();
    		Set<String> seguidosNick = usr.getSeguidosNickname();
    		for (String x : seguidosNick) {
    			seguidos.add(IUC.seleccionarUsuario(x));
        	}
    		
    		//Obtención de cuponeras
        	List<DtCuponera> cuponerasIngresadasSocio = new ArrayList<>();
        	if (usr instanceof DtSocioExt) {
        		Set<String> cuponeras = ((DtSocioExt)usr).getCuponerasCompradas();
        		for (String x : cuponeras) {
        			cuponerasIngresadasSocio.add(ICC.seleccionarCuponera(x));
        		}
        	}
        	
        	//Obtención de actividades asociadas
        	List<DtActividadDeportivaExt> actAsociadasProfesor = new ArrayList<>();
        	if (usr instanceof DtProfesorExt) {
        		Set<String> actividades = ((DtProfesorExt)usr).getActividadesDepAsociadas();
        		for (String x : actividades) {	
        			actAsociadasProfesor.add(IADC.getActDepExt(((DtProfesorExt)usr).getNombreInstitucion(),  x));
        		}
        	}
        	
        	//Obtención de actividades ingresadas
        	List<DtActividadDeportivaExt> actIngresadasProfesor = new ArrayList<>();
        	if (usr instanceof DtProfesorExt) {
        		Set<String> actividades = ((DtProfesorExt)usr).getActividadesIngresadas();
        		for (String x : actividades) {
        			actIngresadasProfesor.add(IADC.getActDepExt(((DtProfesorExt)usr).getNombreInstitucion(),  x));
        		}
        	}
        	        	
        	request.setAttribute("datoUsuario",  usr);
        	request.setAttribute("clasesDictadas",  clasesDictadasProfesor);
        	request.setAttribute("clasesInscripto",  clasesInscriptoSocio);
        	request.setAttribute("seguidores",  seguidores);
        	request.setAttribute("seguidos",  seguidos);
        	request.setAttribute("cuponeras",  cuponerasIngresadasSocio);
        	request.setAttribute("actividadesAsociadas",  actAsociadasProfesor);
        	request.setAttribute("actividadesIngresadas",  actIngresadasProfesor);
        	
        } catch(Exception e) {
        	e.printStackTrace();
        	response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
        	return;
        }
        request.getRequestDispatcher("pages/usuarios.jsp").forward(request,  response);
    }
    
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
}