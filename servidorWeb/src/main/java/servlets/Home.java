package servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.GestorWeb;
import logica.LaFabrica;
import logica.IActividadDeportivaController;
import datatypes.DtActividadDeportivaExt;
import excepciones.ActividadDeportivaException;
import excepciones.InstitucionException;

@WebServlet ("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Home() {
        super();
        GestorWeb.getInstance();
    }

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("instituciones", GestorWeb.getIADC().obtenerInstituciones());
		req.setAttribute("categorias", GestorWeb.getIADC().obtenerCategorias());
		
		// Agregar Actividades Random a visualizar en el Home (se pasa en el request).
		IActividadDeportivaController IADC = LaFabrica.getInstance().obtenerIActDeportivaController();
		Random rand = new Random();
		for (int i = 1 ; i < 4; i++) {
			// Numero random entre 0..Collection<>.size()-1. 
			int numRandom = IADC.obtenerInstituciones().size();
			if (numRandom > 0)
				numRandom = rand.nextInt(numRandom) + 1;
			int contador = 0;
			Iterator<String> iter = IADC.obtenerInstituciones().iterator();
			String nombreInstitucion = null;
			while (iter.hasNext() && (contador < numRandom)) {
				contador++;
				nombreInstitucion = iter.next();
			}
			if (nombreInstitucion != null) {
				try {
					numRandom = IADC.obtenerActividades(nombreInstitucion).size();
					if (numRandom > 0)
						numRandom = rand.nextInt(numRandom) + 1;
					contador = 0;
					String nombreActividad = null;
					iter = IADC.obtenerActividades(nombreInstitucion).iterator();
					while (iter.hasNext() && (contador < numRandom)) {
						contador++;
						nombreActividad = iter.next();
					}
					if (nombreActividad != null) {
						DtActividadDeportivaExt datosActividad = IADC.getActDepExt(nombreInstitucion, nombreActividad);
						req.setAttribute("actividad" + i, datosActividad);
					}
				} catch(InstitucionException ex) {
					ex.printStackTrace();
					req.setAttribute("contxError", ex);
					resp.sendRedirect(req.getContextPath() + "/pages/500.jsp");
					return;
				} catch(ActividadDeportivaException ex) {
					ex.printStackTrace();
					req.setAttribute("contxError", ex);
					resp.sendRedirect(req.getContextPath() + "/pages/500.jsp");
					return;
				}
			}
		}
		req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
}
