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
import datatypes.DtCuponera;
import datatypes.DtFecha;
import datatypes.DtUsuarioExt;

@WebServlet("/ComprarCuponera")
public class ComprarCuponera extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
	
    public ComprarCuponera() {
    	super();
        IUC = LaFabrica.getInstance().obtenerIUsuarioController();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UsuarioNoExisteException {
    	System.out.print((String) request.getParameter("cuponera")+ " ------- "+((DtUsuarioExt) request.getSession().getAttribute("loggedUser")).getNickname());
    	DtFecha f = new DtFecha();
    	try {
    		IUC.comprarCuponera((String) request.getParameter("cuponera"),((DtUsuarioExt) request.getSession().getAttribute("loggedUser")).getNickname(), f);
		} catch (UsuarioNoExisteException e){
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
			return;
		} catch (CuponeraNoExisteException e){
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
			return;
		}
    	
    	DtUsuarioExt usrLogged = IUC.seleccionarUsuario( ((DtUsuarioExt) request.getSession().getAttribute("loggedUser")).getNickname() );
    	
    	
    	//Envio de información actualizada
    	request.getSession().setAttribute("loggedUser",usrLogged);
    	response.sendRedirect(request.getContextPath()+"/cuponeras?cuponera="+request.getParameter("cuponera"));
    }    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}