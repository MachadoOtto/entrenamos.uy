package webServices;

import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatypesWS.DtCuponeraWS;
import excepciones.NoExisteCuponeraException;
import logica.ICuponeraController;
import logica.LaFabrica;
import main.Main;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSCuponeraController {

	private Endpoint endpoint = null;
	private LaFabrica factory = LaFabrica.getInstance();
	private ICuponeraController ICC = factory.obtenerICuponeraController();

	public WSCuponeraController(){}
	
    @WebMethod(exclude = true)
    public void publicar(){
    	Properties prp = Main.config;
    	endpoint = Endpoint.publish("http://"+prp.getProperty("hostIP")+":"+prp.getProperty("hostPort")+prp.getProperty("cuponeraController_ServiceName"), this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
    	return endpoint;
    }	
    /*  OPERACION DESACTIVADA
	public int ingresarCuponera(String nombre,  String descripcion,  DtFecha inicio,  DtFecha fin,  
			int descuento,  DtFecha alta, String imagen) throws CuponeraRepetidaException,  FechaInvalidaException{
		return ICC.ingresarCuponera(nombre, descripcion, inicio, fin, descuento, alta, imagen);
	}
	*/
	public String [] getNombreCuponeras() {
		return ICC.getNombreCuponeras().toArray(new String[0]);
	}
	/* OPERACION DESACTIVADA
	public void agregarActividadCuponera(String nombre,  String instituto,  String actividadDeportiva,  int cantidadClases) 
			throws InstitucionException,  ActividadDeportivaException,  CuponeraInmutableException{
		ICC.agregarActividadCuponera(nombre, instituto, actividadDeportiva, cantidadClases);
	}
	*/
	
	public DtCuponeraWS seleccionarCuponera(String nombre) throws NoExisteCuponeraException{
		return new DtCuponeraWS(ICC.seleccionarCuponera(nombre));
	}
	
	public String [] getNombreCuponerasSinRecibos() {
		return ICC.getNombreCuponerasSinRecibos().toArray(new String[0]);
	}
}
