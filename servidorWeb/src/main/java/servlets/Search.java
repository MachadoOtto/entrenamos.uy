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
import datatypes.DtFecha;
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
				request.getRequestDispatcher("pages/500.jsp").forward(request,  response);
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
				request.getRequestDispatcher("pages/500.jsp").forward(request,  response);
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
				request.getRequestDispatcher("pages/500.jsp").forward(request,  response);
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
				request.getRequestDispatcher("pages/500.jsp").forward(request,  response);
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
    	String ua=request.getHeader("User-Agent").toLowerCase();
    	if(ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")||ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
    		request.getRequestDispatcher("/pages/searchMobile.jsp").forward(request,  response);
    	} else {
    		request.getRequestDispatcher("/pages/search.jsp").forward(request,  response);
    	}
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
		IActividadDeportivaController IADC = LaFabricaWS.getInstance().obtenerIActDeportivaController();
		for (String x : IDCC.obtenerInstituciones()) {
			if (fltrI.isEmpty() || fltrI.contains(x)) {
				try {
					for (String y : IDCC.obtenerActividades(x)) {
						if (fltrA.isEmpty() || (fltrA.equals(y) && (IADC.getActDepExt(x, y).getEstado() == TEstado.aceptada))) {
							for (String z : IDCC.obtenerClases(x,  y)) {
								DtFecha fechaActual = new DtFecha();
								if (fechaActual.esMenor(IDCC.seleccionarClase(x,  y,  z).getFechaClase())) {
									lista.add(IDCC.seleccionarClase(x,  y,  z));
								}
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