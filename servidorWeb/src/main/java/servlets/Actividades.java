package servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Parametrizer;
import models.LaFabricaWS;
import datatypes.DtSocioExt;
import datatypes.DtUsuarioExt;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtClaseExt;
import datatypes.DtCuponera;
import datatypes.DtFecha;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;

@WebServlet ("/actividades")
public class Actividades extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Actividades() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	String ua=request.getHeader("User-Agent").toLowerCase(); // UserAgent
    	String nombreActDep = request.getParameter("actividad");
		DtActividadDeportivaExt datosActDep = null;
    	String nickUser = (String) request.getSession().getAttribute("nickname");
    	boolean esSocio = false;
    	boolean finalizable = false;
    	DtUsuarioExt datosCreador = null;
    	String institucion = null;
    	Set<DtClaseExt> datosClases = new HashSet<>();
    	Set<DtCuponera> datosCuponeras = new HashSet<>();
		try {
			datosActDep = buscarActDep(nombreActDep);
			institucion = LaFabricaWS.getInstance().obtenerIDictadoClaseController().obtenerInstitucionActDep(datosActDep.getNombre());
		} catch(ActividadDeportivaException e) {
			request.setAttribute("nombreActDep",  null);
			request.setAttribute("institucion",  null);
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
			return;
		}
		if (datosActDep != null) {
			if (datosActDep.getCreador().equals("Administrador")) {
				datosCreador = new DtUsuarioExt("Administrador", null, null, null, null, null, "Administrador.png".getBytes(), null, null);
				request.setAttribute("datosCreador",  datosCreador);
			} else {
				try {
					datosCreador = LaFabricaWS.getInstance().obtenerIUsuarioController().seleccionarUsuario(datosActDep.getCreador());
					request.setAttribute("datosCreador",  datosCreador);
				} catch(UsuarioNoExisteException ignore) { }
			}
			
			//Esto verifica que todas las clases de dicha actividad hayan finalizado para saber si es "finalizable".
			boolean f1 = true;
			for(String x: datosActDep.getClases()) {
				try {
					DtClaseExt cl = LaFabricaWS.getInstance().obtenerIActDeportivaController().seleccionarClase(institucion, datosActDep.getNombre(), x);
					f1 = cl.getFechaClase().esMenor(new DtFecha());
				} catch (InstitucionException | ActividadDeportivaException | ClaseException e) {
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
				}
			}
			finalizable = f1;
			request.setAttribute("finalizable", finalizable);
		}
		
		try {
			if (nickUser!=null && LaFabricaWS.getInstance().obtenerIUsuarioController().seleccionarUsuario(nickUser) instanceof DtSocioExt) {
				esSocio = true;
			}
		} catch(UsuarioNoExisteException ignore) { } 
		
		//Set de DtClaseExt
		try {
			Set<String> clases = datosActDep.getClases();
			for (String clase : clases) { 
				datosClases.add(LaFabricaWS.getInstance().obtenerIDictadoClaseController().buscarClase(clase));
			}
		} catch(ClaseException e) {
			request.setAttribute("datosClases",  null);
		}
		
		//Set de DtCuponera
		try {
			Set<String> cuponeras = datosActDep.getCuponeras();
			for (String cup : cuponeras) {
				datosCuponeras.add(LaFabricaWS.getInstance().obtenerICuponeraController().seleccionarCuponera(cup));
			}
		} catch(Exception e) {
			request.setAttribute("datosCuponeras",  null);
		}
		request.setAttribute("actDep",  datosActDep);
		request.setAttribute("institucion",  institucion);
		request.setAttribute("esSocio",  esSocio);
		request.setAttribute("datosClases",  datosClases);
		request.setAttribute("datosCuponeras",  datosCuponeras);
		if (ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")||ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
			request.getRequestDispatcher("pages/actividadesMobile.jsp").forward(request,  response);
		} else {
			request.getRequestDispatcher("pages/actividades.jsp").forward(request,  response);
		}
	}
    
    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		processRequest(request,  response);
	}
    
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	
	private DtActividadDeportivaExt buscarActDep(String nombreActDep) throws ActividadDeportivaException {
    	DtActividadDeportivaExt datosActDep = LaFabricaWS.getInstance().obtenerIActDeportivaController().buscarActDep(nombreActDep);
    	return datosActDep;
    }
}
