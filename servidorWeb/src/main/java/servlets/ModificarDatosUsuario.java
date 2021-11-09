package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import datatypes.DtFecha;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtSocioExt;
import datatypes.DtProfesorExt;
import datatypes.DtUsuarioExt;
import models.IUsuarioController;
import models.LaFabricaWS;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@MultipartConfig
@WebServlet ("/modificarDatosUsuario")
public class ModificarDatosUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
    public ModificarDatosUsuario() {
        super();
        IUC = LaFabricaWS.getInstance().obtenerIUsuarioController();
    } 

    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	DtUsuarioExt usrLogged = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
        try {
        	//Imagen
        	byte [] bimgn = null;
        	if (request.getPart("formFile")!=null && request.getPart("formFile").getSize()>0) {
        		Part filePart = request.getPart("formFile");
        		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
        		String ext = s[s.length-1];
        		bimgn = (usrLogged.getNickname()+"."+ext).getBytes();
        	}
        	else bimgn = usrLogged.getImagen();
        	//Fecha
        	String[] d = rp(request, "nac").split("-");
        	
        	//Contraseña (opcional)
        	String passwordNueva = usrLogged.getContrasenia();
        	if (rp(request, "pas1")!=null && rp(request, "pas1").length()>0) {
            	String password1 = new String();
            	String password2 = new String();
            	password1 = (String) rp(request, "pas1");
            	password2 = (String) rp(request, "pas2");
            	if ( password1.equals(password2) ) {
            		passwordNueva = password1;
            	}
        	}
        	        	
        	//Caso de uso
	        if (usrLogged instanceof DtSocioExt) {
	        	IUC.editarDatosBasicos(usrLogged.getNickname(), new DtSocio(usrLogged.getNickname(), rp(request, "nomm"), rp(request, "ape"), 
	        			usrLogged.getEmail(), passwordNueva, new DtFecha(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]), 0, 0, 0),  bimgn));
	        }
	        else {
	        	IUC.editarDatosBasicos(usrLogged.getNickname(), new DtProfesor(usrLogged.getNickname(), rp(request, "nomm"), rp(request, "ape"), 
	        			usrLogged.getEmail(),  passwordNueva,  new DtFecha(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]), 0, 0, 0), 
	        			((DtProfesorExt)usrLogged).getNombreInstitucion(),  rp(request, "desc"),  rp(request, "bio") , rp(request, "webs"), 
	        			bimgn));
	        }
	        
	        //Actualización de imagen
	        if (request.getPart("formFile")!=null && request.getPart("formFile").getSize()>0) {	        	
	        	Part filePart = request.getPart("formFile");
        		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
        		String ext = s[s.length-1];
	        	request.setAttribute("type", "usu");
	        	request.setAttribute("id", usrLogged.getNickname()+"."+ext);
	        	request.setAttribute("attribute_asset_transfer", filePart);
	        	ContentHandler.postContent(request,response);
	        }

	        
        } catch(Exception e) {
        	e.printStackTrace();
        	response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
        	return;
        }
        response.sendRedirect((request.getContextPath()+"/usuarios?nickname=" + usrLogged.getNickname() + "&e=7"));
    }
    
    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
    
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	
	private String rp(HttpServletRequest request, String param) {
		return request.getParameter(param);
	}

}