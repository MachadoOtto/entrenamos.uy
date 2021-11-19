package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
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
import datatypes.DtUsuario;
import excepciones.UsuarioNoExisteException;
import models.GestorWeb;
import tools.Parametrizer;
import models.IUsuarioController;
import models.LaFabricaWS;

@MultipartConfig
@WebServlet ("/signup")
public class Signup extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IUsuarioController IUC;
    public Signup() {
        super();
        IUC = LaFabricaWS.getInstance().obtenerIUsuarioController();
    }
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	String ua=request.getHeader("User-Agent").toLowerCase();
    	if(ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")||ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
    		request.getRequestDispatcher("pages/404.jsp").forward(request,  response);
    		return;
    	}
    	String r = request.getParameter("miurl");
        try {
        	request.setCharacterEncoding("utf-8");
        	response.setCharacterEncoding("utf-8");
        	byte [] bimgn = null;
        	if (request.getPart("imgPerfil")!=null && request.getPart("imgPerfil").getSize()>0) {
        		Part filePart = request.getPart("imgPerfil");
        		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
        		String ext = s[s.length-1];
        		bimgn = (rp(request, "nickk")+"."+ext).getBytes();
        	}
        	String [] d = rp(request, "nac").split("-");
	        if (rp(request, "tipoU").equals("0")) {
	        	if (IUC.ingresarDatosUsuario(new DtSocio(rp(request, "nickk"), rp(request, "nomm"), rp(request, "ape"), 
	        			rp(request, "emaill"),  rp(request, "pas1"),  new DtFecha(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]), 0, 0, 0),  bimgn))!=0) {
	        		r=Parametrizer.addParam(r,  "e",  "2");
	        		savectx(request);
	        		response.sendRedirect(r);
	        		return;
	        	}
	        }
	        else {
	        	if (IUC.ingresarDatosUsuario(new DtProfesor(rp(request, "nickk"), rp(request, "nomm"), rp(request, "ape"), 
	        			rp(request, "emaill"),  rp(request, "pas1"),  new DtFecha(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]), 0, 0, 0), 
	        			rp(request, "instit"),  rp(request, "descRU"),  rp(request, "bioRU") , rp(request, "websRU"), 
	        			bimgn))!=0) {
	        		r=Parametrizer.addParam(r,  "e",  "2");
	        		savectx(request);
	        		response.sendRedirect(r);
	        		return;
	        	}
	        }
	        if (request.getPart("imgPerfil")!=null && request.getPart("imgPerfil").getSize()>0) {
	        	Part filePart = request.getPart("imgPerfil");
        		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
        		String ext = s[s.length-1];
	        	request.setAttribute("type", "usu");
	        	request.setAttribute("id", rp(request, "nickk")+"."+ext);
	        	request.setAttribute("attribute_asset_transfer", filePart);
	        	ContentHandler.postContent(request,response);
	        }
	        r=Parametrizer.remParam(r,  "e", "1");
	        r=Parametrizer.remParam(r,  "e", "2");
	        r=Parametrizer.addParam(r,  "e",  "3");
	        clearctx(request);
	        request.getSession().setAttribute("loggedUser", GestorWeb.buscarUsuario(rp(request, "nickk")));
        } catch(Exception e) {
        	System.out.println("INFO SIGNUP: "+e.getMessage());
        	savectx(request);
        	r=Parametrizer.addParam(r,  "e",  "2");
        }
        response.sendRedirect(r);
    }
    protected void validator(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
        String nick = request.getParameter("nick");
        String email = request.getParameter("email");
        PrintWriter stream = response.getWriter();
        if(nick==null || email==null) {
        	stream.println("BAD_FORMAT");
        	return;
        }
        DtUsuario dn,de;
        try {
			 dn = IUC.seleccionarUsuario(nick);
		} catch (UsuarioNoExisteException e) {
			dn = null;
		}
        try {
        	de = IUC.seleccionarUsuarioEmail(email);
		} catch (UsuarioNoExisteException e1) {
			de = null;
		}
        if(dn!=null)
        	stream.println("BAD_NICK");
        if(de!=null)
        	stream.println("BAD_MAIL");
        if(de==null && dn==null)
        	stream.println("OK");
        return;
    } 
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
        processRequest(request,  response);
	}
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		validator(request,response);
	}
	private String rp(HttpServletRequest request, String param) {
		return request.getParameter(param);
	}
	private void savectx(HttpServletRequest r) {
		r.getSession().setAttribute("ctxtipoU",rp(r, "tipoU"));
		r.getSession().setAttribute("ctxnickk",rp(r,"nickk"));
		r.getSession().setAttribute("ctxemaill",rp(r,"emaill"));
		r.getSession().setAttribute("ctxnomm",rp(r,"nomm"));
		r.getSession().setAttribute("ctxape",rp(r,"ape"));
		r.getSession().setAttribute("ctxnac",rp(r,"nac"));
		r.getSession().setAttribute("ctxinstit",rp(r,"instit"));
		r.getSession().setAttribute("ctxdescRU",rp(r,"descRU"));
		r.getSession().setAttribute("ctxbioRU",rp(r,"bioRU"));
		r.getSession().setAttribute("ctxebsRU",rp(r,"websRU"));
	}
	private void clearctx(HttpServletRequest r) {
		r.getSession().setAttribute("ctxtipoU",null);
		r.getSession().setAttribute("ctxnickk",null);
		r.getSession().setAttribute("ctxemaill",null);
		r.getSession().setAttribute("ctxnomm",null);
		r.getSession().setAttribute("ctxape",null);
		r.getSession().setAttribute("ctxnac",null);
		r.getSession().setAttribute("ctxinstit",null);
		r.getSession().setAttribute("ctxdescRU",null);
		r.getSession().setAttribute("ctxbioRU",null);
		r.getSession().setAttribute("ctxebsRU",null);
	}
}
