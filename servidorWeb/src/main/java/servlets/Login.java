package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtUsuarioExt;
import excepciones.UsuarioNoExisteException;
import models.GestorWeb;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/api/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("nick-login");
        PrintWriter stream = response.getWriter();
        //verificar contraseña
		try {
			DtUsuarioExt usr = GestorWeb.getInstance().buscarUsuario(name);
			if(!usr.getContrasenia().equals(request.getParameter("pass-login")))
				stream.println("FAIL_PWD");
			else {
				stream.println("OK"); 
				request.getSession().setAttribute("nickname", usr.getNickname());
			}
		} catch(UsuarioNoExisteException ex) {
			stream.println("FAIL_USR");
		}
    } 
    
	static public DtUsuarioExt getUsuarioLogueado(HttpServletRequest request) {
		try {
			return GestorWeb.getInstance().buscarUsuario((String) request.getSession().getAttribute("nickname"));
		} catch (Exception e){
			return null;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}

}