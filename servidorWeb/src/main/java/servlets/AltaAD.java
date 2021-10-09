package servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import datatypes.DtActividadDeportiva;
import datatypes.DtFecha;
import datatypes.DtProfesorExt;
import datatypes.DtUsuarioExt;
import datatypes.TEstado;
import excepciones.ActividadDeportivaException;
import logica.IActividadDeportivaController;
import logica.LaFabrica;
import models.GestorWeb;
import tools.Parametrizer;

@MultipartConfig
@WebServlet ("/alta_ad")
public class AltaAD extends HttpServlet {
	private static IActividadDeportivaController IADC;
	private static final long serialVersionUID = 1L;
    public AltaAD() {
        super();
        IADC = LaFabrica.getInstance().obtenerIActDeportivaController();
    }
    protected void processRequest(HttpServletRequest r, HttpServletResponse response) throws ServletException, IOException {
    	String ru = r.getParameter("miurl");
        DtProfesorExt p = (DtProfesorExt) r.getSession().getAttribute("loggedUser");
        Set<String> cats = new HashSet<>();
        for(String c: rp(r,"catAD").split("[,]")) {
        	cats.add(c);
        }
    	int duracion = Integer.parseInt(rp(r,"durAD").trim());
    	float costo = Float.parseFloat(rp(r,"costoAD").trim());
        DtActividadDeportiva datosAD = new DtActividadDeportiva(rp(r,"nombreAD"),rp(r,"descAD"),duracion,costo,
        		new DtFecha(),new HashSet<String>(),TEstado.ingresada,p.getNickname()); //cat    
        try {
	        if (IADC.ingresarDatosActividadDep(p.getNombreInstitucion(), datosAD)) {
	    		ru=Parametrizer.remParam(ru, "e", "4");
	    		ru=Parametrizer.addParam(ru, "e", "5");
		        if(rp(r,"imgAD")!=null && !(rp(r,"imgAD").equals(""))) {
	        		String [] s = rp(r,"ADimgName").split("[.]");
	        		String ext = s[s.length-1];
		        	String path = r.getServletContext().getRealPath("/assets/images/activities/"+rp(r,"nombreAD")+"."+ext);
		            try (FileOutputStream f = new FileOutputStream(path)) {
		                f.write(Base64.getDecoder().decode(rp(r,"ADimgBlob").split(",")[1]));
		            }
		           //System.out.println( r.getServletContext().getRealPath("/assets/images/users/"+rp(r,"nombreAD")+"."+ext));
		        }
			} else {
				ru=Parametrizer.remParam(ru, "e", "5");
				ru=Parametrizer.addParam(ru, "e", "4");
	        }
        }catch(ActividadDeportivaException e) {
        	e.getMessage();
        	ru=Parametrizer.remParam(ru, "e", "5");
        	ru=Parametrizer.addParam(ru, "e", "4");
        }catch(Exception e) {
		   e.printStackTrace();
		   ru=Parametrizer.remParam(ru, "e", "5");
		   ru=Parametrizer.addParam(ru, "e", "4");
	   }
       response.sendRedirect(ru);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
	
	private String rp(HttpServletRequest request,String param) {
		return request.getParameter(param);
	}
}