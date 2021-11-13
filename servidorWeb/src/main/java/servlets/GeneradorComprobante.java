package servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;

import datatypes.DtClaseExt;
import datatypes.DtFecha;
import datatypes.DtPremio;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import models.IDictadoClaseController;
import models.LaFabricaWS;

import com.itextpdf.text.Image;


@SuppressWarnings("serial")
@WebServlet("/generarComprobante")
public class GeneradorComprobante extends HttpServlet {

    public GeneradorComprobante() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String ua=request.getHeader("User-Agent").toLowerCase();
    	if(ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")||ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
    	  response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
    	  return;
    	}
    	String id = request.getParameter("id");
    	String cla = request.getParameter("cla");
    	String insti = new String();
    	String actividad = new String();
    	String prof = new String();
    	String descPremio = new String();
		IDictadoClaseController IDCC = LaFabricaWS.getInstance().obtenerIDictadoClaseController();
		DtClaseExt clase = null;
		DtPremio premio = null;
		String fechaVencimiento = new DtFecha().toString();
		boolean frenoDeMano = false;
		for (String x : IDCC.obtenerInstituciones()) {
			try {
				for (String y : IDCC.obtenerActividades(x)) {
					if (IDCC.obtenerClases(x,  y).contains(cla)) {
						insti = x;
						actividad = y;
						clase = IDCC.seleccionarClase(x,  y,  cla);
						prof = clase.getNicknameProfesor();
						premio = clase.getPremio();
						fechaVencimiento = calcularVencimiento(premio.getFechaSorteo());
						descPremio = premio.getDescripcion();
						frenoDeMano = true;
						break;
					}
				}
				if (frenoDeMano) {
					break;
				}
			} catch (InstitucionException ignore) {
			} catch (ActividadDeportivaException ignore) { 
			} catch (ClaseException ignore) { }
		}
		if (premio.getGanadores().contains(id)) {
	    	String texto = "Felicidades socio/a " + id + "! Gracias a su participacion en la clase " + cla +
	    			", dictada por " + prof + " (" + actividad + " / " + insti + "), usted ha sido 'dudosamente' premiado con:\n" + 
	    			"    * " + descPremio + "\n Vencimiento: " + fechaVencimiento;
	        try {
	        	Rectangle pagesize = new Rectangle(510, 269);
	        	Document pdf = new Document(pagesize);
	        	PdfWriter writer = PdfWriter.getInstance(pdf, response.getOutputStream());
	        	pdf.open();
	        	float width = pdf.getPageSize().getWidth();
	            float height = pdf.getPageSize().getHeight();
	        	Image plantilla = Image.getInstance("http://localhost:8080/"+request.getContextPath()+"/assets/images/misc/plantillaPrize.png");
	        	plantilla.scaleAbsolute(width, height);
	        	plantilla.setAbsolutePosition(0, 0);
	        	pdf.add(plantilla);
	            // Texto principal del cupon.
	        	Phrase cuerpoTexto = new Phrase(texto);
	        	ColumnText body_column = new ColumnText(writer.getDirectContent());
	        	body_column.setSimpleColumn(42, 218, 370, 65);
	        	body_column.addText(cuerpoTexto);
	        	body_column.go();
	        	// Premio en segunda division de plantilla
	        	Phrase phPremio = new Phrase("Vale por: " + descPremio);
	        	ColumnText second_column = new ColumnText(writer.getDirectContent());
	        	second_column.setSimpleColumn(390, 120, 500, 50);
	        	second_column.addText(phPremio);
	        	second_column.go();
	        	// Fecha
	        	Phrase date = new Phrase(fechaVencimiento);
	        	ColumnText fecha_column = new ColumnText(writer.getDirectContent());
	        	fecha_column.setSimpleColumn(390, 30, 500, 10);
	        	fecha_column.addText(date);
	        	fecha_column.go();
		        pdf.close();
	        }catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
    
    private String calcularVencimiento(DtFecha fecha) {
    	String fString = String.valueOf(fecha.getAnio()) + "-" + String.valueOf(fecha.getMes()) + "-" + String.valueOf(fecha.getDia());
    	return LocalDate.parse(fString).plusDays(30).toString();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
