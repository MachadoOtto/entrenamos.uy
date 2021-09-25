package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datatypes.DtUsuarioExt;
import datatypes.DtProfesorExt;
import excepciones.UsuarioNoExisteException;

import models.EstadoSesion;
import models.GestorWeb;

/**
 * Servlet implementation class Login
 */
@WebServlet ("/iniciar-sesion")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession objSesion = request.getSession();
        String login = request.getParameter("nick-login");
        String password = request.getParameter("pass-login");
        EstadoSesion nuevoEstado;

		// chequea contraseña
		try {
			DtUsuarioExt usr = GestorWeb.getInstance().buscarUsuario(login);
			if(!usr.getContrasenia().equals(password)) {
				nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
				objSesion.setAttribute("estado_sesion", nuevoEstado);
				response.sendRedirect("inicioErroneo/passwordError.html");
			} else {
				nuevoEstado = EstadoSesion.LOGIN_CORRECTO;
				objSesion.setAttribute("estado_sesion", nuevoEstado);
				// setea el usuario logueado
				request.getSession().setAttribute("usuario_logueado", usr.getEmail());
				if (usr instanceof DtProfesorExt)
					response.sendRedirect("vistaDenis/home.html");
				else
					response.sendRedirect("vistaEmi/home.html");
			}
		} catch(UsuarioNoExisteException ex) {
			nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
			objSesion.setAttribute("estado_sesion", nuevoEstado);
			response.sendRedirect("inicioErroneo/usernameError.html");
		}
    } 
    
	/**
	 * Devuelve el usuario logueado
	 * @param request
	 * @return
	 * @throws UsuarioNoExisteException 
	 */
	static public DtUsuarioExt getUsuarioLogueado(HttpServletRequest request)
			throws UsuarioNoExisteException {
		return GestorWeb.getInstance().buscarUsuario(
				(String) request.getSession().getAttribute("usuario_logueado")
			);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}

}