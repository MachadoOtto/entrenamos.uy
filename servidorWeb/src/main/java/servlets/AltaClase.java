package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

import org.apache.tomcat.jni.Time;

import datatypes.DtFecha;
import datatypes.DtClase;
import datatypes.DtUsuarioExt;
import models.GestorWeb;
import tools.Parametrizer;
import logica.IDictadoClaseController;
import logica.LaFabrica;

@MultipartConfig
@WebServlet ("/altaClase")
public class AltaClase extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IDictadoClaseController IDCC;
    public AltaClase() {
        super();
        IDCC = LaFabrica.getInstance().obtenerIDictadoClaseController();
    }
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	String r = new String() + request.getContextPath() + "/actividades?actividad=" + URLEncoder.encode(rp(request, "nombreActDep"), "utf-8");
        try {
        	String imgClase = null;
            if (request.getPart("img")!=null && request.getPart("img").getSize()>0) {
            	Part filePart = request.getPart("img");
        		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
        		String ext = s[s.length-1];
        		imgClase=rp(request, "nombreClase")+"."+ext;
            }
        	String fechaClase = rp(request, "fechaInicio");        	
	        DtUsuarioExt datosP = ((DtUsuarioExt) request.getSession().getAttribute("loggedUser"));

	        if (IDCC.ingresarDatosClase(rp(request, "institucionAsociada"),  rp(request, "nombreActDep"),  new DtClase(rp(request, "nombreClase"), datosP.getNickname(), datosP.getEmail(), 
	        	Integer.parseInt(rp(request, "cantMin")),  Integer.parseInt(rp(request, "cantMax")),  rp(request, "url"), 
	        	new DtFecha(Integer.parseInt(fechaClase.substring(0, 4)), Integer.parseInt(fechaClase.substring(5, 7)), Integer.parseInt(fechaClase.substring(8, 10)), 				   Integer.parseInt(rp(request, "hora")), Integer.parseInt(rp(request, "minutos")), 0),  new DtFecha(),  imgClase))!=0) {
	        	r=Parametrizer.addParam(r,  "e",  "8");
	        	DtUsuarioExt userReload = LaFabrica.getInstance().obtenerIUsuarioController().seleccionarUsuario(datosP.getNickname());
				request.getSession().setAttribute("loggedUser",  userReload);
	        	response.sendRedirect(r);
	        	return;
	        }	        
    		if (request.getPart("img")!=null && request.getPart("img").getSize()>0) {
	        	Part filePart = request.getPart("img");
	        	InputStream fileContent = filePart.getInputStream();
        		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
        		String ext = s[s.length-1];
	        	String path = request.getServletContext().getRealPath("/assets/images/classes/"+imgClase);
	        	Files.copy(fileContent,  Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
	           System.out.println( request.getServletContext().getRealPath("/assets/images/users/"+imgClase));
	        }
	        r=Parametrizer.remParam(r,  "e", "8");
	        r=Parametrizer.addParam(r,  "e",  "9");
	        //Guille: Por que haces esto??? request.getSession().setAttribute("loggedUser", GestorWeb.buscarUsuario(rp(request, "nickk")));
        } catch(Exception e) {
        	e.printStackTrace();
        	r=Parametrizer.addParam(r,  "e",  "2");
        }
        response.sendRedirect(r);
    }
    
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	private String rp(HttpServletRequest request, String param) {
		return request.getParameter(param);
	}
}
