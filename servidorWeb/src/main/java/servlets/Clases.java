package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtClaseExt;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import logica.IDictadoClaseController;
import logica.LaFabrica;

@WebServlet ("/api/clases")
public class Clases extends HttpServlet {
	private static IDictadoClaseController IDCC;
	private static final long serialVersionUID = 1L;
	
    public Clases() {
        super();
        IDCC = LaFabrica.getInstance().obtenerIDictadoClaseController();
    }
    
    protected void processRequest(HttpServletRequest request, 
    		HttpServletResponse response) throws ServletException, IOException {
    	String nombreClase = request.getParameter("clase");
    	System.out.printf(nombreClase);
    	/* buscarClase es una funcion nueva que busca una Clase solo por su
    	 nombre sin necesidad de conocer la Institucion o la ActDeportiva */
		DtClaseExt datosClase = null;
		try {
			datosClase = IDCC.buscarClase(nombreClase);
		} catch(ClaseException ex) {
			// la clase no existe
			request.setAttribute("clase", null);
			response.sendRedirect(request.getContextPath()+"/pages/404.jsp");
		}
		// setea los datos
		request.getSession().setAttribute("clase", datosClase);
		request.getRequestDispatcher(request.getContextPath() + 
				"/pages/clases.jsp").forward(request, response);
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
}