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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Parametrizer.loadStdRequests(request);
    	String r = new String() + request.getContextPath() + "/actividades?actividad=" + rp(request,"nombreActDep");
        try {
        	String imgClase = null;
        	if(rp(request,"img")!=null && !(rp(request,"img").equals(""))) {
        		String [] s = rp(request,"img").split("[.]");
        		String ext = s[s.length-1];
        		imgClase = (rp(request,"nombreClase")+"."+ext);
        	}
        	String fechaClase = rp(request,"fechaInicio");
	        DtUsuarioExt datosP = ((DtUsuarioExt) request.getSession().getAttribute("loggedUser"));
	        System.out.print("\n \n" + fechaClase + "\n");
	        System.out.print(fechaClase.substring(8,10));
	        if(IDCC.ingresarDatosClase(rp(request,"institucionAsociada"), rp(request,"nombreActDep"), new DtClase(rp(request,"nombreClase"),datosP.getNickname(),datosP.getEmail(),
	        	Integer.parseInt(rp(request,"cantMin")), Integer.parseInt(rp(request,"cantMax")), rp(request,"url"),
	        	new DtFecha(Integer.parseInt(fechaClase.substring(0,4)),Integer.parseInt(fechaClase.substring(5,7)),Integer.parseInt(fechaClase.substring(8,10)),0,0,0), new DtFecha(), imgClase))!=0) {
	        	r=Parametrizer.addParam(r, "e", "2");
	        	DtUsuarioExt userReload = LaFabrica.getInstance().obtenerIUsuarioController().seleccionarUsuario(datosP.getNickname());
				request.getSession().setAttribute("loggedUser", userReload);
	        	response.sendRedirect(r);
	        	return;
	        }
	        if(rp(request,"imgClase")!=null && !(rp(request,"imgClase").equals(""))) {
        		String [] s = rp(request,"imgClase").split("[.]");
        		String ext = s[s.length-1];
	        	String path = request.getServletContext().getRealPath("/assets/images/classes/"+rp(request,"nickk")+"."+ext);
	            try (FileOutputStream f = new FileOutputStream(path)) {
	                f.write(Base64.getDecoder().decode(rp(request,"imgBlob").split(",")[1]));
	            }
	           //System.out.println(request.getServletContext().getRealPath("/assets/images/users/"+rp(request,"nickk")+"."+ext));
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