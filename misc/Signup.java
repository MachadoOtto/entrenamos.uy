package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import datatypes.DtFecha;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuarioExt;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;
import models.GestorWeb;
import logica.IUsuarioController;
import logica.LaFabrica;

@MultipartConfig
@WebServlet ("/api/signup")
public class Signup extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
    public Signup() {
        super();
        IUC = LaFabrica.getInstance().obtenerIUsuarioController();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter stream = response.getWriter();
        try {
        	byte [] bimgn = null;
        	if(rp(request,"imagename")!=null)
        		bimgn = rp(request,"imagename").getBytes();
	        if(rp(request,"institucion")==null) {
	        	if(IUC.ingresarDatosUsuario(new DtSocio(rp(request,"nickname"),rp(request,"nombre"),rp(request,"apellido"),
	        			rp(request,"email"), rp(request,"pwd"), new DtFecha(1937,5,8,0,0,0), bimgn))!=0) {
	        		stream.println("FAIL DATA_IN_USE");
	        		return;
	        	}
	        }
	        else {
	        	if(IUC.ingresarDatosUsuario(new DtProfesor(rp(request,"nickname"),rp(request,"nombre"),rp(request,"apellido"),
	        			rp(request,"email"), rp(request,"pwd"), new DtFecha(1997,1,1,0,0,0),
	        			rp(request,"institucion"), rp(request,"descripcion"), rp(request,"biografia") ,rp(request,"website"),
	        			bimgn))!=0) {
	        		stream.println("FAIL DATA_IN_USE");
	        		return;
	        	}
	        }
	        if(rp(request,"image")!=null) {
	        	String [] fn = rp(request,"imagename").split("[.]");
	        	String path = request.getServletContext().getRealPath("/assets/images/users/"+rp(request,"nickname")+"."+fn[fn.length-1]);
	            try (FileOutputStream f = new FileOutputStream(path)) {
	                f.write(Base64.getDecoder().decode(rp(request,"image").split(",")[1]));
	            }
	        }
	        stream.println("OK");
	        request.getSession().setAttribute("nickname", rp(request,"nickname"));
        } catch(Exception e) {
        	e.printStackTrace();
        	stream.println("FAIL");
        }
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
	private String rp(HttpServletRequest request,String param) {
		return request.getParameter(param);
	}
}
