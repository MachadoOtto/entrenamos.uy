package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtUsuarioExt;
import excepciones.UsuarioNoExisteException;
import models.GestorWeb;
import tools.Parametrizer;


@WebServlet ("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
        String name = request.getParameter("nick-login");
        String r = request.getParameter("miurl");
        //verificar contraseña
		try {
			DtUsuarioExt usr = GestorWeb.buscarUsuario(name);
			if (!usr.getContrasenia().equals(request.getParameter("pass-login")))
				r=Parametrizer.addParam(r,  "e",  "1");
			else {
				request.getSession().setAttribute("loggedUser",  usr);
				r=Parametrizer.remParam(r,  "e",  "1");
			}
		} catch(UsuarioNoExisteException ex) {
			r=Parametrizer.addParam(r,  "e",  "1");
		}
		response.sendRedirect(r);
    } 

	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}

}