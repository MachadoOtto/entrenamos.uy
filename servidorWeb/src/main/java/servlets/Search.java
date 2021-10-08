package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.LaFabrica;
import logica.IActividadDeportivaController;
import logica.ICuponeraController;
import logica.IDictadoClaseController;
import logica.IUsuarioController;
import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import datatypes.DtCuponera;
import datatypes.DtUsuario;
import datatypes.TEstado;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;
import excepciones.UsuarioNoExisteException;

@WebServlet ("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Search() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, 
    		HttpServletResponse response) throws ServletException, IOException {
    	String texto = request.getParameter("campoTexto");
    	if (texto == null)
    		texto = new String();
    	String actividad = request.getParameter("actividades");
    	String clase = request.getParameter("clases");
    	String cuponera = request.getParameter("cuponeras");
    	String usuario = request.getParameter("usuarios");
    	List<DtActividadDeportiva> listaActividades = null;
    	List<DtClase> listaClases = null;
    	List<DtCuponera> listaCuponeras = null;
    	List<DtUsuario> listaUsuarios = null;
    	if (actividad.equals("yes")) {
    		try {
        		listaActividades = obtenerActividades(texto);
    		} catch(ActividadDeportivaException ex) {
    			ex.printStackTrace();
				request.setAttribute("contxError", ex);
				response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
    			return;
    		}
    	}
    	if (clase.equals("yes")) {
    		try {
        		listaClases = obtenerClases();
    		} catch(ClaseException ex) {
    			ex.printStackTrace();
				request.setAttribute("contxError", ex);
				response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
    			return;
    		}
    	}
    	if (cuponera.equals("yes")) {
    		try {
        		listaCuponeras = obtenerCuponeras(texto);
    		} catch(NoExisteCuponeraException ex) {
    			ex.printStackTrace();
				request.setAttribute("contxError", ex);
				response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
    			return;
    		}
    	}
    	if (usuario.equals("yes")) {
    		try {
    			listaUsuarios = obtenerUsuarios();
    		} catch(UsuarioNoExisteException ex) {
    			ex.printStackTrace();
				request.setAttribute("contxError", ex);
				response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
    			return;
    		}
    	}
    	request.setAttribute("actividades", listaActividades);
    	request.setAttribute("clases", listaClases);
    	request.setAttribute("cuponeras", listaCuponeras);
    	request.setAttribute("usuarios", listaUsuarios);
    	request.getRequestDispatcher("/pages/search.jsp").forward(request, response);
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
	
	// Devuelve las Actividades Aprobadas que contengan 'texto'.
	private List<DtActividadDeportiva> obtenerActividades(String texto) throws ActividadDeportivaException {
		List<DtActividadDeportiva> lista = new ArrayList<>();
		IActividadDeportivaController IADC = LaFabrica.getInstance().obtenerIActDeportivaController();
		for (String x :	IADC.obtenerInstituciones()) {
			try {
				for (String y : IADC.obtenerActividades(x)) {
					DtActividadDeportiva datosActividad = IADC.getActDepExt(x, y);
					if ((x.contains(texto)) && (datosActividad.getEstado() == TEstado.aceptada)) {
						lista.add(datosActividad);
					}
				}
			} catch(InstitucionException ignore) { }
		}
		return lista;
	}
	
	private List<DtClase> obtenerClases() throws ClaseException {
		List<DtClase> lista = new ArrayList<>();
		IDictadoClaseController IDCC = LaFabrica.getInstance().obtenerIDictadoClaseController();
		for (String x : IDCC.obtenerInstituciones()) {
			try {
				for (String y : IDCC.obtenerActividades(x)) {
					for (String z : IDCC.obtenerClases(x, y)) {
						lista.add(IDCC.seleccionarClase(x, y, z));
					}
				}
			} catch(InstitucionException ignore) {
			} catch (ActividadDeportivaException ignore) { }
		}
		return lista;
	}
	
	// Devuelven las Cuponeras que contengan 'texto'.
	private List<DtCuponera> obtenerCuponeras(String texto) throws NoExisteCuponeraException {
		List<DtCuponera> lista = new ArrayList<>();
		ICuponeraController ICC = LaFabrica.getInstance().obtenerICuponeraController();
		for (String x : ICC.getNombreCuponeras()) {
			if (x.contains(texto))
				lista.add(ICC.seleccionarCuponera(x));
		}
		return lista;
	}
	
	private List<DtUsuario> obtenerUsuarios() throws UsuarioNoExisteException {
		List<DtUsuario> lista = new ArrayList<>();
		IUsuarioController IUC = LaFabrica.getInstance().obtenerIUsuarioController();
		for (String x : IUC.obtenerUsuarios()) {
			lista.add(IUC.seleccionarUsuario(x));
		}
		return lista;
	}
}