package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import datatypes.DtFecha;
import datatypes.DtPremio;
import datatypes.DtClase;
import datatypes.DtUsuarioExt;
import tools.Parametrizer;
import models.IDictadoClaseController;
import models.LaFabricaWS;

@MultipartConfig
@WebServlet ("/altaClase")
public class AltaClase extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IDictadoClaseController IDCC;
    public AltaClase() {
        super();
        IDCC = LaFabricaWS.getInstance().obtenerIDictadoClaseController();
    }
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	String ua=request.getHeader("User-Agent").toLowerCase();
    	if(ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")||ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
    	  request.getRequestDispatcher("pages/404.jsp").forward(request,  response);
    	  return;
    	}
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
	        if(rp(request,"descPremio")!=null && rp(request,"descPremio").length()>0) {
		        if (IDCC.ingresarDatosClase(rp(request, "institucionAsociada"),  rp(request, "nombreActDep"),  new DtClase(rp(request, "nombreClase"), datosP.getNickname(), datosP.getEmail(), 
		        	Integer.parseInt(rp(request, "cantMin")),  Integer.parseInt(rp(request, "cantMax")),  rp(request, "url"), 
		        	new DtFecha(Integer.parseInt(fechaClase.substring(0, 4)), Integer.parseInt(fechaClase.substring(5, 7)), Integer.parseInt(fechaClase.substring(8, 10)),
		        		Integer.parseInt(rp(request, "hora")), Integer.parseInt(rp(request, "minutos")), 0),  new DtFecha(),  imgClase,
		        	rp(request,"urlVideo"), new DtPremio(rp(request,"descPremio"),Integer.parseInt(rp(request,"cantPremios")), null, null)))!=0) {
		        	r=Parametrizer.remParam(r,  "e", "9");
		        	r=Parametrizer.addParam(r,  "e",  "8");
		        	savectx(request);
		        	response.sendRedirect(r);
		        	return;
		        }
	        } else {
		        if (IDCC.ingresarDatosClase(rp(request, "institucionAsociada"),  rp(request, "nombreActDep"),  new DtClase(rp(request, "nombreClase"), datosP.getNickname(), datosP.getEmail(), 
		        	Integer.parseInt(rp(request, "cantMin")),  Integer.parseInt(rp(request, "cantMax")),  rp(request, "url"), 
		        	new DtFecha(Integer.parseInt(fechaClase.substring(0, 4)), Integer.parseInt(fechaClase.substring(5, 7)), Integer.parseInt(fechaClase.substring(8, 10)),
		        		Integer.parseInt(rp(request, "hora")), Integer.parseInt(rp(request, "minutos")), 0),  new DtFecha(),  imgClase,
		        	rp(request,"urlVideo"), null))!=0) {
		        	r=Parametrizer.remParam(r,  "e", "9");
		        	r=Parametrizer.addParam(r,  "e",  "8");
		        	savectx(request);
		        	response.sendRedirect(r);
		        	return;
		        }
	        }
    		if (request.getPart("img")!=null && request.getPart("img").getSize()>0) {
	        	Part filePart = request.getPart("img");
        		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
        		String ext = s[s.length-1];
        		request.setAttribute("type", "cla");
        		request.setAttribute("id", rp(request, "nombreClase")+"."+ext);
        		request.setAttribute("attribute_asset_transfer", filePart);
	        	ContentHandler.postContent(request,response);
	        }
	        r=Parametrizer.remParam(r,  "e", "8");
	        r=Parametrizer.addParam(r,  "e",  "9");
	        clearctx(request);
        	DtUsuarioExt userReload = LaFabricaWS.getInstance().obtenerIUsuarioController().seleccionarUsuario(datosP.getNickname());
			request.getSession().setAttribute("loggedUser",  userReload);
        } catch(Exception e) {
        	System.out.println("INFO ALTACL: "+e.getMessage());
        	r=Parametrizer.remParam(r,  "e", "9");
        	r=Parametrizer.addParam(r,  "e",  "8");
        	savectx(request);
        }
        response.sendRedirect(r);
    }
    
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	private String rp(HttpServletRequest request, String param) {
		return request.getParameter(param);
	}
	private void savectx(HttpServletRequest r) {
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLnombreClase",rp(r,"nombreClase"));
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLfechaInicio",rp(r,"fechaInicio"));
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLhora",rp(r,"hora"));
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLminutos",rp(r,"minutos"));
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLcantMin",rp(r,"cantMin"));
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLcantMax",rp(r,"cantMax"));
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLurl",rp(r,"url"));
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLurlVideo",rp(r,"urlVideo"));
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLdescPremio",rp(r,"descPremio"));
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLcantPremios",rp(r,"cantPremios"));
	}
	private void clearctx(HttpServletRequest r) {
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLnombreClase",null);
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLfechaInicio",null);
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLhora",null);
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLminutos",null);
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLcantMin",null);
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLcantMax",null);
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLurl",null);
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLurlVideo",null);
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLdescPremio",null);
		r.getSession().setAttribute("ctx"+rp(r,"nombreActDep")+"CLcantPremios",null);
	}
}
