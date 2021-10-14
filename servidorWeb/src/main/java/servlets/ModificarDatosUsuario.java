package servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
import logica.IActividadDeportivaController;
import logica.IUsuarioController;
import logica.LaFabrica;
import models.GestorWeb;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/modificarDatosUsuario")
public class ModificarDatosUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
	private IActividadDeportivaController IADC;
    public ModificarDatosUsuario() {
        super();
        IUC = LaFabrica.getInstance().obtenerIUsuarioController();
        IADC = LaFabrica.getInstance().obtenerIActDeportivaController();
    } 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Parametrizer.loadStdRequests(request);
    	DtUsuarioExt usrLogged = (DtUsuarioExt) request.getSession().getAttribute("loggedUser");
        try {
        	//Imagen
        	byte [] bimgn = null;
        	if(rp(request,"imagename")!=null)
        		bimgn = rp(request,"imagename").getBytes();
        	
        	//Fecha
        	String[] d = rp(request,"nac").split("-");
        	
        	//Contraseña (opcional)
        	String password1 = new String();
        	String password2 = new String();
        	String passwordNueva = usrLogged.getContrasenia();
        	password1 = (String) rp(request,"pas1");
        	password2 = (String) rp(request,"pas2");
        	if( password1 == password2 ) {
        		passwordNueva = password1;
        	}
        	else {
        		//lanzar error o algo :P
        	}
        	        	
        	//Caso de uso
	        if(usrLogged instanceof DtSocioExt) {
	        	IUC.editarDatosBasicos(usrLogged.getNickname(),new DtSocio(usrLogged.getNickname(),rp(request,"nomm"),rp(request,"ape"),
	        			usrLogged.getEmail(),passwordNueva,new DtFecha(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]),0,0,0), bimgn));
	        }
	        else {
	        	IUC.editarDatosBasicos(usrLogged.getNickname(),new DtProfesor(usrLogged.getNickname(),rp(request,"nomm"),rp(request,"ape"),
	        			usrLogged.getEmail(), passwordNueva, new DtFecha(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]),0,0,0),
	        			((DtProfesorExt)usrLogged).getNombreInstitucion(), rp(request,"desc"), rp(request,"bio") ,rp(request,"webs"),
	        			bimgn));
	        }
	        
	        //Actualización de imagen
	        if(rp(request,"image")!=null) {
	        	String [] fn = rp(request,"imagename").split("[.]");
	        	String path = request.getServletContext().getRealPath("/assets/images/users/"+rp(request,"nickname")+"."+fn[fn.length-1]);
	            try (FileOutputStream f = new FileOutputStream(path)) {
	                f.write(Base64.getDecoder().decode(rp(request,"image").split(",")[1]));
	            }
	        }
	        
	        //Obtener datos actualizados para usuarios.jsp
	        usrLogged = IUC.seleccionarUsuario(usrLogged.getNickname());
	        
	        List<DtActividadDeportivaExt> actIngresadasProfesor = new ArrayList<>();
        	if(usrLogged instanceof DtProfesorExt) {
        		Set<String> actividades = ((DtProfesorExt)usrLogged).getActividadesIngresadas();
        		for (String x : actividades) {
        			actIngresadasProfesor.add(IADC.getActDepExt(((DtProfesorExt)usrLogged).getNombreInstitucion(), x));
        		}
        	}
        	
        	//Envio de la rial data
        	request.getSession().setAttribute("loggedUser",usrLogged);
        	request.setAttribute("datoUsuario", usrLogged);
        	request.setAttribute("actividadesIngresadas", actIngresadasProfesor);
        } catch(Exception e) {
        	e.printStackTrace();
        	response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
        }
    	request.getRequestDispatcher("/usuarios?nickname=" + usrLogged.getNickname()).forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
	
	private String rp(HttpServletRequest request,String param) {
		return request.getParameter(param);
	}

}