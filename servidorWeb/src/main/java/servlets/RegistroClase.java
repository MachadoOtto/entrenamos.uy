package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Parametrizer;
import logica.LaFabrica;
import logica.IDictadoClaseController;
import logica.IUsuarioController;
import datatypes.DtUsuarioExt;
import datatypes.TReg;
import datatypes.DtFecha;

@WebServlet ("/api/registroClase")
public class RegistroClase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RegistroClase() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, 
    		HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	IDictadoClaseController IDCC = LaFabrica.getInstance().obtenerIDictadoClaseController();
    	IUsuarioController IUC = LaFabrica.getInstance().obtenerIUsuarioController();
    	String nombreClase = request.getParameter("clase");
    	String nombreActividad = request.getParameter("actividad");
    	String nombreInstitucion = request.getParameter("institucion");    	
    	String tipoInsc = request.getParameter("tipoInsc");
    	String cuponera = request.getParameter("cups");
    	DtUsuarioExt user = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
    	String link = request.getContextPath() + "/clases?clase=" + nombreClase;
		try {
	    	if ((nombreClase != null) && (nombreActividad != null) && (nombreInstitucion != null) && (user != null)) {
		    	if (tipoInsc.equals("general")) {
		    		IDCC.inscribirSocio(nombreInstitucion, nombreActividad, nombreClase, user.getNickname(), 
		    				TReg.general, new DtFecha(), null);
		    	} else if ((tipoInsc.equals("cuponera")) && (cuponera != null)) {
		    		IDCC.inscribirSocio(nombreInstitucion, nombreActividad, nombreClase, user.getNickname(), 
		    				TReg.cuponera, new DtFecha(), cuponera);
		    	}
	    	}
			DtUsuarioExt userReload = IUC.seleccionarUsuario(user.getNickname());
			request.getSession().setAttribute("loggedUser", userReload);
			link += "&e=6";
		} catch(Exception ex) {
			// error al implementar la logica
			ex.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/pages/500.jsp");
			return;
		}
		// devolver a servlet Clases
		response.sendRedirect(link);
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
}