package servlets;

import java.io.IOException;

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
    	
    	DtFecha f = new DtFecha();
    	try {
    		IUC.comprarCuponera((String) request.getSession().getAttribute("user"),request.getParameter("cuponera"), f);
		} catch (UsuarioNoExisteException e){
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
		} catch (CuponeraNoExisteException e){
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
		}
    	request.getRequestDispatcher("pages/cuponeras.jsp").forward(request, response);
    }    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}