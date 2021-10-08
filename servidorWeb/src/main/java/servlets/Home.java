package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.GestorWeb;
import logica.LaFabrica;
import logica.IActividadDeportivaController;
import datatypes.DtActividadDeportiva;
import datatypes.TEstado;
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
		List<DtActividadDeportiva> actividadesAprobadas = null;
		try {
			actividadesAprobadas = obtenerActividades();
		} catch(ActividadDeportivaException ex) {
			ex.printStackTrace();
			req.setAttribute("contxError", ex);
			resp.sendRedirect(req.getContextPath() + "/pages/500.jsp");
			return;
		}
		Set<Integer> numerosRandom = new HashSet<>();
		Random rand = new Random();
		while ((numerosRandom.size() < actividadesAprobadas.size()) && (numerosRandom.size() < 3)) {
			numerosRandom.add(rand.nextInt(actividadesAprobadas.size()));
		}
		int contador = 1;
		for (Integer x : numerosRandom) {
			req.setAttribute("actividad" + contador, actividadesAprobadas.get(x));
			contador++;
		}
		req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	// Devuelve las Actividades Aprobadas.
	private List<DtActividadDeportiva> obtenerActividades() throws ActividadDeportivaException {
		List<DtActividadDeportiva> lista = new ArrayList<>();
		IActividadDeportivaController IADC = LaFabrica.getInstance().obtenerIActDeportivaController();
		for (String x :	IADC.obtenerInstituciones()) {
			try {
				for (String y : IADC.obtenerActividades(x)) {
					DtActividadDeportiva datosActividad = IADC.getActDepExt(x, y);
					if (datosActividad.getEstado() == TEstado.aceptada) {
						lista.add(datosActividad);
					}
				}
			} catch(InstitucionException ignore) { }
		}
		return lista;
	}
	
}
