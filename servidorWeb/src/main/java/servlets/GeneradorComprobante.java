package servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import datatypes.DtFecha;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;


@SuppressWarnings("serial")
@WebServlet("/generarComprobante")
public class GeneradorComprobante extends HttpServlet {

    public GeneradorComprobante() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id = request.getParameter("id");
    	String cla = request.getParameter("cla");  
        try {
        	Font title = new Font(Font.FontFamily.HELVETICA,50f,Font.NORMAL,BaseColor.BLUE);
        	
        	Document pdf = new Document();
        	PdfWriter.getInstance(pdf, response.getOutputStream());
        	pdf.open();
        	
        	Paragraph date = new Paragraph((new DtFecha()).toFechaHora());
        	date.setAlignment(Element.ALIGN_RIGHT);
        	pdf.add(date);
        	
        	Image logo = Image.getInstance("http://localhost:8080/"+request.getContextPath()+"/assets/images/misc/iconoEntrenamos-uy.png");
        	logo.scaleAbsolute(80f, 80f);
        	pdf.add(logo);
        	
        	pdf.add(new Paragraph("Entrenamos.uy",title));
        	pdf.add(Chunk.NEWLINE);
        	pdf.add(new Paragraph("Tremendo pdf!"));
	        pdf.close();
        }catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
