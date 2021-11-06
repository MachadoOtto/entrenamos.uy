package webServices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatypes.DtInstitucion;
import datatypesWS.DtActividadWS;
import datatypesWS.DtClaseWS;
import datatypesWS.DtInstitucionWS;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import logica.IActividadDeportivaController;
import logica.LaFabrica;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSActividadController {

	private Endpoint endpoint = null;
	private LaFabrica factory = LaFabrica.getInstance();
	private IActividadDeportivaController IADC = factory.obtenerIActDeportivaController();
	
	public WSActividadController(){}
	
    @WebMethod(exclude = true)
    public void publicar(){
    	endpoint = Endpoint.publish("http://localhost:9129/entrenamosuy/actividadController", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
    	return endpoint;
    }
    
	public String[] obtenerInstituciones() {
		return IADC.obtenerInstituciones().toArray(new String[0]);
	}
	
	public Boolean ingresarDatosActividadDep(String nombreInsti,  DtActividadWS datosAD) throws InstitucionException, 
			ActividadDeportivaException{
		return IADC.ingresarDatosActividadDep(nombreInsti, datosAD.adapt());
	}
	
	public String[] obtenerActividades(String ins) throws InstitucionException{
		return IADC.obtenerActividades(ins).toArray(new String[0]);
	}
	
	public  String[] obtenerDeltaInstituciones(String nombreCup,  String ins) throws InstitucionException{
		return IADC.obtenerDeltaInstituciones(nombreCup, ins).toArray(new String[0]);
	}
	
	// Migue: Esta funcion va a cambiar de controller,  me lo llevo a DictadoClaseController,  si no la usan en otro lado favor borrar
	// 6/11, la operacion sigue viva :P
	public DtClaseWS seleccionarClase(String  ins,  String actDep,  String clase) throws InstitucionException, 
			ActividadDeportivaException,  ClaseException{
		return new DtClaseWS(IADC.seleccionarClase(ins, actDep, clase));
	}
	
	public String[] obtenerSocios() {
		return IADC.obtenerSocios().toArray(new String[0]);
	}
	
	public DtActividadWS getActDepExt(String ins,  String actDep) throws InstitucionException,  
			ActividadDeportivaException{
		return new DtActividadWS(IADC.getActDepExt(ins, actDep));
	}
	/* OPERACION DESACTIVADA
	public int altaInstitucion(String nombre,  String descripcion,  String URL);
	*/
	
	public DtInstitucionWS obtenerDatosInstitucion(String inst) throws InstitucionException{
		return new DtInstitucionWS(IADC.obtenerDatosInstitucion(inst));
	}
	
	/* OPERACION DESACTIVADA
	public void aprobarActividad(String actividadDeportiva,  TEstado estado);
	*/
	public void finalizarActividad(String actividadDeportiva) {
		IADC.finalizarActividad(actividadDeportiva);
	}
	
	public String[] obtenerActDepIngresadas() {
		return IADC.obtenerActDepIngresadas().toArray(new String[0]);
	}
	
	/* OPERACION DESACTIVADA
	public void ingresarCatergoria(DtCategoria datos) throws CategoriaException;
	 */
	
	public String[] obtenerCategorias() {
		return IADC.obtenerCategorias().toArray(new String[0]);
	}

	public DtActividadWS buscarActDep(String nombreActDep) throws ActividadDeportivaException{
		return new DtActividadWS(IADC.buscarActDep(nombreActDep));
	}
}
