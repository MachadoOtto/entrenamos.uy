package servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtActividadDeportivaExt;
import datatypes.DtFecha;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtSocioExt;
import datatypes.DtProfesorExt;
import datatypes.DtUsuarioExt;
import logica.IUsuarioController;
import logica.LaFabrica;
import models.GestorWeb;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/api/modificarDatosUsuario")
public class ModificarDatosUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
    public ModificarDatosUsuario() {
        super();
        IUC = LaFabrica.getInstance().obtenerIUsuarioController();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	DtUsuarioExt usrLogged = IUC.seleccionarUsuario( (String)request.getSession().getAttribute("user"));
        	byte [] bimgn = null;
        	if(rp(request,"imagename")!=null)
        		bimgn = rp(request,"imagename").getBytes();
        	String[] d = rp(request,"nac").split("-");
	        if(usrLogged instanceof DtSocioExt) {
	        	IUC.editarDatosBasicos(usrLogged.getNickname(),new DtSocio(usrLogged.getNickname(),rp(request,"nomm"),rp(request,"ape"),
	        			usrLogged.getEmail(), rp(request,"pas1"),new DtFecha(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]),0,0,0), bimgn));
	        }
	        else {
	        	IUC.editarDatosBasicos(usrLogged.getNickname(),new DtProfesor(usrLogged.getNickname(),rp(request,"nomm"),rp(request,"ape"),
	        			usrLogged.getEmail(), rp(request,"pas1"), new DtFecha(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]),0,0,0),
	        			((DtProfesorExt)usrLogged).getNombreInstitucion(), rp(request,"desc"), rp(request,"bio") ,rp(request,"webs"),
	        			bimgn));
	        }
	        if(rp(request,"image")!=null) {
	        	String [] fn = rp(request,"imagename").split("[.]");
	        	String path = request.getServletContext().getRealPath("/assets/images/users/"+rp(request,"nickname")+"."+fn[fn.length-1]);
	            try (FileOutputStream f = new FileOutputStream(path)) {
	                f.write(Base64.getDecoder().decode(rp(request,"image").split(",")[1]));
	            }
	        }
        } catch(Exception e) {
        	e.printStackTrace();
        	response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
        }
    	request.getRequestDispatcher("pages/perfilUsuario.jsp").forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
	private String rp(HttpServletRequest request,String param) {
		return request.getParameter(param);
	}

}