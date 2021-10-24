package servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.NoExisteCuponeraException;
import excepciones.UsuarioNoExisteException;
import logica.IActividadDeportivaController;
import logica.ICuponeraController;
import logica.IDictadoClaseController;
import logica.IUsuarioController;
import logica.LaFabrica;
import models.GestorWeb;

/**
 * Servlet implementation class ContentHandler
 */
@WebServlet("/api/content")
public class ContentHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUsuarioController IUC;
    private IActividadDeportivaController IADC;
    private ICuponeraController ICC;
    private IDictadoClaseController IDCC;
    
    public ContentHandler() {
        super();
         IUC = GestorWeb.getIUC();
         IADC = GestorWeb.getIADC();
         ICC = GestorWeb.getICC();
         IDCC = GestorWeb.getIDCC();
    }

	private void serveContent(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
		String c=request.getParameter("c"); //c=clase de imagen (usuario, actividad, etc.).
		String cc=null;
		String id=request.getParameter("id");
		String idf=null;
		if (c==null || id ==null)
			r404(request, response);
		if (c.equals("usu")) {
			try {
				if (IUC.seleccionarUsuario(id).getImagen()!=null)
					idf = new String(IUC.seleccionarUsuario(id).getImagen());
			}catch(UsuarioNoExisteException ex) {r404(request, response);return;}
			cc = "users";
		}
		else if (c.equals("cla")) {
			try {
			idf = IDCC.buscarClase(id).getImgName();
			}catch(ClaseException ex) {r404(request, response);return;}
			cc = "classes";
		}
		else if (c.equals("act")) {
			boolean fnghfn = false;
			for (String i: IADC.obtenerInstituciones())
				try {
					idf = IADC.getActDepExt(i,  id).getImgName();
					fnghfn = true;
				} catch(Exception e){}
			if (idf==null && !fnghfn) {r404(request, response);return;}
			cc = "activities";
		}
		else if (c.equals("cup")) {
			try {
			idf = ICC.seleccionarCuponera(id).getImgName();
			}catch(NoExisteCuponeraException ex) {r404(request, response);return;}
			cc = "cups";
		}
		if (idf==null || cc ==null) {
			idf="default.png";
		}
		//---
		PrintWriter stream;
		int s=0;
		
		try {
			stream = response.getWriter();
			try(FileInputStream inputStream = new FileInputStream(request.getServletContext().getRealPath("/assets/images/"+cc+"/"+idf))) {     
				for (int ch; (ch = inputStream.read()) != -1; ) {
				    stream.write(ch);
				    s++;
				}
				response.setHeader("cache-control",  "max-age=5");
				response.setContentType("image/"+idf.split("[.]")[idf.split("[.]").length-1]);
				response.setContentLength(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//---
	}
	
	private void r404(HttpServletRequest request,  HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath()+"/pages/404.jsp");
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		serveContent(request, response);
	}

	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		// TODO Auto-generated method stub
		doGet(request,  response);
	}

}
