package servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.UsuarioNoExisteException;
import excepciones.CuponeraNoExisteException;
import logica.IUsuarioController;
import logica.LaFabrica;
import datatypes.DtFecha;

@WebServlet("/ComprarCuponera")
public class ComprarCuponera extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
	
    public ComprarCuponera() {
    	super();
        IUC = LaFabrica.getInstance().obtenerIUsuarioController();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		IUC.comprarCuponera((String) request.getSession().getAttribute("user"),request.getParameter("cuponera"), getFechaActual());
		} catch (UsuarioNoExisteException e){
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
		} catch (CuponeraNoExisteException e){
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
		}
    	request.getRequestDispatcher("pages/cuponeras.jsp").forward(request, response);
    }    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public DtFecha getFechaActual() {
		LocalDateTime ora = LocalDateTime.now();
		DtFecha actual = new DtFecha(ora.getYear(),ora.getMonthValue(),ora.getDayOfMonth(),ora.getHour(),ora.getMinute(),ora.getSecond());
		return actual;
	}
}