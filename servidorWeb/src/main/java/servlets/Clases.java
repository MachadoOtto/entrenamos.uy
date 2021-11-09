package servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Parametrizer;
import models.LaFabricaWS;
import models.IActividadDeportivaController;
import models.IDictadoClaseController;
import datatypes.DtUsuarioExt;
import datatypes.DtSocioExt;
import datatypes.DtClaseExt;
import datatypes.DtFecha;
import excepciones.ClaseException;
import excepciones.InstitucionException;

@WebServlet ("/clases")
public class Clases extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Clases() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request,  
    		HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	IActividadDeportivaController IADC = LaFabricaWS.getInstance().obtenerIActDeportivaController();
    	IDictadoClaseController IDCC = LaFabricaWS.getInstance().obtenerIDictadoClaseController();
    	String nombreClase = request.getParameter("clase");
		DtClaseExt datosClase = null;
    	String nombreActividad = null;
    	String nombreInstitucion = null;
    	String precio = null;
    	DtUsuarioExt user = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
    	boolean esSocio = false;
    	boolean estaInscripto = true;
    	boolean inscCaducada = true;
    	boolean estaLlena = true;
    	Set<String> cuponerasCompradas = null;
		try {
			datosClase = buscarClase(nombreClase);
			if (datosClase.getMaxSocios() > datosClase.getAlumnos().size())
				estaLlena = false;
			nombreActividad = nombreActDeClase(nombreClase);
			nombreInstitucion = nombreInstiDeAct(nombreActividad);
			DtFecha horaActual = new DtFecha();
			inscCaducada = datosClase.getFechaClase().esMenor(horaActual);
			if (user instanceof DtSocioExt) {
				esSocio = true;
				if (!((DtSocioExt)user).getClases().contains(nombreClase)) {
					estaInscripto = false;
					precio = Float.toString(IADC.getActDepExt(nombreInstitucion,  nombreActividad).getCosto());
					cuponerasCompradas = IDCC.getCuponerasDisponibles(user.getNickname(),  nombreInstitucion,  nombreActividad);
				}
			}
		} catch(ClaseException ex) {
			// la clase no existe
			ex.printStackTrace();
			request.setAttribute("clase",  null);
			request.setAttribute("actividad",  null);
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
			return;
		} catch(Exception ex) {
			// error al implementar la logica
			ex.printStackTrace();
			request.setAttribute("clase",  null);
			request.setAttribute("actividad",  null);
			response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
			return;
		}
		// setea los datos
		request.setAttribute("clase",  datosClase);
		request.setAttribute("actividad",  nombreActividad);
		request.setAttribute("institucion",  nombreInstitucion);
		request.setAttribute("esSocio",  esSocio);
		request.setAttribute("estaInscripto",  estaInscripto);
		request.setAttribute("cupDisponibles",  cuponerasCompradas);
		request.setAttribute("precio",  precio);
		request.setAttribute("estaCaducada",  inscCaducada);
		request.setAttribute("estaLlena",  estaLlena);
		request.getRequestDispatcher("/pages/clases.jsp").forward(request,  response);
	}
    
    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		processRequest(request,  response);
	}
    
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	
	/* buscarClase es una funcion nueva que busca una Clase solo por su
	 nombre sin necesidad de conocer la Institucion o la ActDeportiva */
	private DtClaseExt buscarClase(String nombreClase) throws ClaseException {
    	DtClaseExt datosClase = LaFabricaWS.getInstance().obtenerIDictadoClaseController().buscarClase(nombreClase);
    	return datosClase;
    }
    
    private String nombreActDeClase(String nombreClase) {
    	String nombreActividad = null;
    	IDictadoClaseController IDCC = LaFabricaWS.getInstance().obtenerIDictadoClaseController();
		for (String x: IDCC.obtenerInstituciones()) {
			try {
				for (String y: IDCC.obtenerActividades(x)) {
					if (IDCC.obtenerClases(x,  y).contains(nombreClase)) {
						nombreActividad = y;
						return nombreActividad;
					}
				}
			} catch(InstitucionException ignore) { }
		}
		return nombreActividad;
    }
    
    private String nombreInstiDeAct(String nombreActividad) {
    	String nombreInsti = null;
    	IDictadoClaseController IDCC = LaFabricaWS.getInstance().obtenerIDictadoClaseController();
		for (String x: IDCC.obtenerInstituciones()) {
			try {
				if (IDCC.obtenerActividades(x).contains(nombreActividad)) {
					nombreInsti = x;
					return nombreInsti;
				}
			} catch(InstitucionException ignore) { }
		}
		return nombreInsti;
    }
}