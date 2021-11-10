package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Parametrizer;
import tools.NameComparator;
import tools.FechaComparator;
import models.LaFabricaWS;
import models.IActividadDeportivaController;
import models.ICuponeraController;
import models.IDictadoClaseController;
import models.IUsuarioController;
import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import datatypes.DtClasesCuponera;
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
    		HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	IActividadDeportivaController IADC = LaFabricaWS.getInstance().obtenerIActDeportivaController();
    	Set<String> categorias = IADC.obtenerCategorias();
    	Set<String> instituciones = IADC.obtenerInstituciones();
    	String orden = request.getParameter("sort");
    	if (orden == null)
    		orden = "alfaDesc";
    	String texto = request.getParameter("campoTexto");
    	Set<String> fltrInstituciones = new HashSet<>();
    	for (int i = 1; i <= instituciones.size(); i++) {
    		String filtro = request.getParameter("fltrI" + i);
    		if (filtro != null)
    			fltrInstituciones.add(filtro);
    	}
    	Set<String> fltrCategorias = new HashSet<>();
    	for (int i = 1; i <= categorias.size(); i++) {
    		String filtro = request.getParameter("fltrC" + i);
    		if (filtro != null)
    			fltrCategorias.add(filtro);
    	}
    	if (texto == null)
    		texto = new String();
    	String actividad = request.getParameter("actividades");
    	String clase = request.getParameter("clases");
    	String cuponera = request.getParameter("cuponeras");
    	String usuario = request.getParameter("usuarios");
    	String filtroAct = request.getParameter("fltrA");
    	if (filtroAct == null)
    		filtroAct = new String();
    	List<DtActividadDeportiva> listaActividades = null;
    	List<DtClase> listaClases = null;
    	List<DtCuponera> listaCuponeras = null;
    	List<DtUsuario> listaUsuarios = null;
    	if ((actividad != null) && (actividad.equals("yes"))) {
    		try {
        		listaActividades = obtenerActividades(texto,  fltrInstituciones,  fltrCategorias);
        		switch(orden) {
        			case "alfaDesc":
                    	Collections.sort(listaActividades,  new NameComparator());
        				break;
        			case "alfaAsc":
                    	Collections.sort(listaActividades,  new NameComparator());
                    	Collections.reverse(listaActividades);
        				break;
        			case "fechaDesc":
                    	Collections.sort(listaActividades,  new FechaComparator());
                    	Collections.reverse(listaActividades);
        				break;
        			case "fechaAsc":
                    	Collections.sort(listaActividades,  new FechaComparator());
        				break;
        			default:
        				break;
        		}
    		} catch(ActividadDeportivaException ex) {
    			ex.printStackTrace();
				request.setAttribute("contxError",  ex);
				response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
    			return;
    		}
    	}
    	if ((clase != null) && (clase.equals("yes"))) {
    		try {
        		listaClases = obtenerClases(fltrInstituciones, filtroAct);
        		switch(orden) {
	    			case "alfaDesc":
	                	Collections.sort(listaClases,  new NameComparator());
	    				break;
	    			case "alfaAsc":
	                	Collections.sort(listaClases,  new NameComparator());
	                	Collections.reverse(listaClases);
	    				break;
	    			case "fechaDesc":
	                	Collections.sort(listaClases,  new FechaComparator());
	                	Collections.reverse(listaClases);
	    				break;
	    			case "fechaAsc":
	                	Collections.sort(listaClases,  new FechaComparator());
	    				break;
	    			default:
	    				break;
        		}
    		} catch(ClaseException ex) {
    			ex.printStackTrace();
				request.setAttribute("contxError",  ex);
				response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
    			return;
    		}
    	}
    	if ((cuponera != null) && (cuponera.equals("yes"))) {
    		try {
        		listaCuponeras = obtenerCuponeras(texto,  fltrInstituciones,  fltrCategorias);
        		switch(orden) {
	    			case "alfaDesc":
	                	Collections.sort(listaCuponeras,  new NameComparator());
	    				break;
	    			case "alfaAsc":
	                	Collections.sort(listaCuponeras,  new NameComparator());
	                	Collections.reverse(listaCuponeras);
	    				break;
	    			case "fechaDesc":
	                	Collections.sort(listaCuponeras,  new FechaComparator());
	                	Collections.reverse(listaCuponeras);
	    				break;
	    			case "fechaAsc":
	                	Collections.sort(listaCuponeras,  new FechaComparator());
	    				break;
	    			default:
	    				break;
        		}
    		} catch(NoExisteCuponeraException ex) {
    			ex.printStackTrace();
				request.setAttribute("contxError",  ex);
				response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
    			return;
    		}
    	}
    	if ((usuario != null) && (usuario.equals("yes"))) {
    		try {
    			listaUsuarios = obtenerUsuarios();
        		switch(orden) {
	    			case "alfaDesc":
	                	Collections.sort(listaUsuarios,  new NameComparator());
	    				break;
	    			case "alfaAsc":
	                	Collections.sort(listaUsuarios,  new NameComparator());
	                	Collections.reverse(listaUsuarios);
	    				break;
	    			default:
	    				break;
        		}
    		} catch(UsuarioNoExisteException ex) {
    			ex.printStackTrace();
				request.setAttribute("contxError",  ex);
				response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
    			return;
    		}
    	}
    	request.setAttribute("actividades",  listaActividades);
    	request.setAttribute("categorias",  categorias);
    	request.setAttribute("clases",  listaClases);
    	request.setAttribute("cuponeras",  listaCuponeras);
    	request.setAttribute("instituciones",  instituciones);
    	request.setAttribute("usuarios",  listaUsuarios);
    	request.setAttribute("searchText",  texto);
    	request.setAttribute("orden",  orden);
    	request.setAttribute("filtroInsti",  fltrInstituciones);
    	request.setAttribute("filtroCat",  fltrCategorias);
    	request.setAttribute("filtroAct", filtroAct);
    	request.getRequestDispatcher("/pages/search.jsp").forward(request,  response);
	}
    
    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		processRequest(request,  response);
	}
    
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	
	// Devuelve las Actividades Aprobadas que contengan 'texto' y pertenezcan a 'filtros'.
	private List<DtActividadDeportiva> obtenerActividades(String texto,  Set<String> fltrI,  Set<String> fltrC) throws ActividadDeportivaException {
		List<DtActividadDeportiva> lista = new ArrayList<>();
		IActividadDeportivaController IADC = LaFabricaWS.getInstance().obtenerIActDeportivaController();
		for (String x :	IADC.obtenerInstituciones()) {
			if (fltrI.isEmpty() || fltrI.contains(x)) {
				try {
					for (String y : IADC.obtenerActividades(x)) {
						DtActividadDeportiva datosActividad = IADC.getActDepExt(x,  y);
						if (((datosActividad.getNombre().contains(texto)) || (datosActividad.getDescripcion().contains(texto))) && 
								(datosActividad.getEstado() == TEstado.aceptada) && ((fltrC == null) || 
								(datosActividad.getCategorias().containsAll(fltrC)))) {
							lista.add(datosActividad);
						}
					}
				} catch(InstitucionException ignore) { }
			}
		}
		return lista;
	}
	
	private List<DtClase> obtenerClases(Set<String> fltrI, String fltrA) throws ClaseException {
		List<DtClase> lista = new ArrayList<>();
		IDictadoClaseController IDCC = LaFabricaWS.getInstance().obtenerIDictadoClaseController();
		for (String x : IDCC.obtenerInstituciones()) {
			if (fltrI.isEmpty() || fltrI.contains(x)) {
				try {
					for (String y : IDCC.obtenerActividades(x)) {
						if (fltrA.isEmpty() || fltrA.equals(x)) {
							for (String z : IDCC.obtenerClases(x,  y)) {
								lista.add(IDCC.seleccionarClase(x,  y,  z));
							}
						}
					}
				} catch(InstitucionException ignore) {
				} catch (ActividadDeportivaException ignore) { }
			}
		}
		return lista;
	}
	
	// Devuelven las Cuponeras que contengan 'texto' y pertenezcan a 'filtros'.
	private List<DtCuponera> obtenerCuponeras(String texto,  Set<String> fltrI,  Set<String> fltrC) throws NoExisteCuponeraException {
		List<DtCuponera> lista = new ArrayList<>();
		ICuponeraController ICC = LaFabricaWS.getInstance().obtenerICuponeraController();
		IActividadDeportivaController IADC = LaFabricaWS.getInstance().obtenerIActDeportivaController();
		for (String x : ICC.getNombreCuponeras()) {
			DtCuponera cup = (ICC.seleccionarCuponera(x));
			if (cup.getNombre().contains(texto) || cup.getDescripcion().contains(texto)) {
				if (fltrC.isEmpty() || cup.getCategorias().containsAll(fltrC)) {
					if (fltrI.isEmpty()) {
						lista.add(cup);
					} else {
						List<DtClasesCuponera> clCup = cup.getContenido();
						for (DtClasesCuponera y : clCup) {
							boolean freno = false;
							for (String z : fltrI) {
								try {
									if (IADC.obtenerActividades(z).contains(y.getNombreActividad())) {
										lista.add(cup);
										freno = true;
										break;
									}
								} catch(InstitucionException ignore) { }
							}
							if (freno)
								break;
						}
					}
				}
			}
		}
		return lista;
	}
	
	private List<DtUsuario> obtenerUsuarios() throws UsuarioNoExisteException {
		List<DtUsuario> lista = new ArrayList<>();
		IUsuarioController IUC = LaFabricaWS.getInstance().obtenerIUsuarioController();
		for (String x : IUC.obtenerUsuarios()) {
			lista.add(IUC.seleccionarUsuario(x));
		}
		return lista;
	}
}