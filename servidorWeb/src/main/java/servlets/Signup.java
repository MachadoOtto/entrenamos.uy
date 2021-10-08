package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.tomcat.jni.Time;

import datatypes.DtFecha;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuarioExt;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;
import models.GestorWeb;
import tools.Parametrizer;
import logica.IUsuarioController;
import logica.LaFabrica;

@MultipartConfig
@WebServlet ("/signup")
public class Signup extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
    public Signup() {
        super();
        IUC = LaFabrica.getInstance().obtenerIUsuarioController();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String r = request.getParameter("miurl");
        try {
        	byte [] bimgn = null;
        	if(rp(request,"imgName")!=null && !(rp(request,"imgName").equals(""))) {
        		String [] s = rp(request,"imgName").split("[.]");
        		String ext = s[s.length-1];
        		bimgn = (rp(request,"nickk")+"."+ext).getBytes();
        	}
        	String [] d = rp(request,"nac").split("-");
	        if(rp(request,"tipoU").equals("0")) {
	        	if(IUC.ingresarDatosUsuario(new DtSocio(rp(request,"nickk"),rp(request,"nomm"),rp(request,"ape"),
	        			rp(request,"emaill"), rp(request,"pas1"), new DtFecha(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]),0,0,0), bimgn))!=0) {
	        		r=Parametrizer.addParam(r, "e", "2");
	        		response.sendRedirect(r);
	        		return;
	        	}
	        }
	        else {
	        	if(IUC.ingresarDatosUsuario(new DtProfesor(rp(request,"nickname"),rp(request,"nombre"),rp(request,"apellido"),
	        			rp(request,"email"), rp(request,"pwd"), new DtFecha(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]),0,0,0),
	        			rp(request,"instit"), rp(request,"descRU"), rp(request,"bioRU") ,rp(request,"websRU"),
	        			bimgn))!=0) {
	        		r=Parametrizer.addParam(r, "e", "2");
	        		response.sendRedirect(r);
	        		return;
	        	}
	        }
	        if(rp(request,"imgName")!=null && !(rp(request,"imgName").equals(""))) {
        		String [] s = rp(request,"imgName").split("[.]");
        		String ext = s[s.length-1];
	        	String path = request.getServletContext().getRealPath("/assets/images/users/"+rp(request,"nickk")+"."+ext);
	            try (FileOutputStream f = new FileOutputStream(path)) {
	                f.write(Base64.getDecoder().decode(rp(request,"imgBlob").split(",")[1]));
	            }
	           //System.out.println( request.getServletContext().getRealPath("/assets/images/users/"+rp(request,"nickk")+"."+ext));
	        }
	        r=Parametrizer.remParam(r, "e","1");
	        r=Parametrizer.remParam(r, "e","2");
	        r=Parametrizer.addParam(r, "e", "3");
	        request.getSession().setAttribute("loggedUser",GestorWeb.buscarUsuario(rp(request,"nickk")));
        } catch(Exception e) {
        	e.printStackTrace();
        	r=Parametrizer.addParam(r, "e", "2");
        }
        response.sendRedirect(r);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
	private String rp(HttpServletRequest request,String param) {
		return request.getParameter(param);
	}
}
