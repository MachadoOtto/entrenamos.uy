package webServices;


import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

import adapters.MapArrayAdapter;
import adapters.SetArrayAdapter;
import datatypes.DtFecha;
import datatypes.DtProfesorExt;
import datatypes.DtSocio;
import datatypes.DtSocioExt;
import datatypes.DtUsuario;
import datatypes.DtUsuarioExt;
import datatypesWS.DtCapsula;
import datatypesWS.DtProfesorWS;
import datatypesWS.DtSocioWS;
import datatypesWS.DtUsuarioWS;
import excepciones.ClaseException;
import excepciones.CuponeraNoExisteException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;
import logica.IActividadDeportivaController;
import logica.ICuponeraController;
import logica.IDictadoClaseController;
import logica.IUsuarioController;
import logica.LaFabrica;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSUsuarioController {

	private Endpoint endpoint = null;
	private LaFabrica factory = LaFabrica.getInstance();
	private IActividadDeportivaController IADC = factory.obtenerIActDeportivaController();
	private ICuponeraController ICC = factory.obtenerICuponeraController();
	private IDictadoClaseController IDCC = factory.obtenerIDictadoClaseController();
	private IUsuarioController IUC = factory.obtenerIUsuarioController();
	
	public WSUsuarioController(){}
	
    @WebMethod(exclude = true)
    public void publicar(){
    	endpoint = Endpoint.publish("http://localhost:9129/entrenamosuy/usuarioController", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
    	return endpoint;
    }

    @WebMethod
	public String[] obtenerInstituciones(){
    	Set<String> r = IUC.obtenerInstituciones();
    	String [] x = new String[r.size()];
    	int i=0;
    	for(String s : r) {
    		x[i++]=s;
    	}
    	return x;
	}
    
    @WebMethod
    public String[] obtenerUsuarios() {
    	Set<String> r = IUC.obtenerUsuarios();
    	String [] x = new String[r.size()];
    	int i=0;
    	for(String s : r) {
    		x[i++]=s;
    	}
    	return x;
    }

    @WebMethod
	public int ingresarDatosUsuario(DtUsuarioWS datoUser)  throws InstitucionException {
		return IUC.ingresarDatosUsuario(datoUser.adapt());
	}
	
    @WebMethod
	public DtUsuarioWS seleccionarUsuario(String userNick) throws UsuarioNoExisteException{
		DtUsuarioExt d = IUC.seleccionarUsuario(userNick);
		if(d instanceof DtSocioExt)
			return new DtSocioWS((DtSocioExt) d);
		else
			return new DtProfesorWS((DtProfesorExt) d);
	}

    @WebMethod
	public DtUsuarioWS seleccionarUsuarioEmail(String userEmail) throws UsuarioNoExisteException{
		DtUsuarioExt d = IUC.seleccionarUsuarioEmail(userEmail);
		if(d instanceof DtSocioExt)
			return new DtSocioWS((DtSocioExt) d);
		else
			return new DtProfesorWS((DtProfesorExt) d);
	}
	
    @WebMethod
	public void editarDatosBasicos(String userNick,  DtUsuarioWS datoUser) throws UsuarioNoExisteException{
		IUC.editarDatosBasicos(userNick, datoUser.adapt());
	}
	
    @WebMethod
	public void seguir(String seguidor,  String seguido) throws UsuarioNoExisteException{
		IUC.seguir(seguidor, seguido);
	}
	
    @WebMethod
	public void dejarDeSeguir(String seguidor,  String seguido) throws UsuarioNoExisteException{
		IUC.dejarDeSeguir(seguidor, seguido);
	}

    @WebMethod
	public void comprarCuponera(String cuponera,  String socio,  DtFecha fechaCompra) throws UsuarioNoExisteException,  CuponeraNoExisteException{
		IUC.comprarCuponera(cuponera, socio, fechaCompra);
	}
	
    @WebMethod
	public boolean verificarIdentidadEmail(String email,  String pass) {
		return IUC.verificarIdentidadEmail(email, pass);
	}
    @WebMethod
	public boolean verificarIdentidadNickname(String nick,  String pass) {
		return IUC.verificarIdentidadNickname(nick, pass);
	}
	
    @WebMethod
	public void favoritearActividad(String nick, String ins, String actDep) throws UsuarioNoExisteException, InstitucionException{
		IUC.favoritearActividad(nick, ins, actDep);
	}
    
    @WebMethod
	public void valorarProfesor(String nickSocio, String ins, String actDep, String cla, int valor) throws UsuarioNoExisteException, ClaseException, InstitucionException{
		IUC.valorarProfesor(nickSocio, ins, actDep, cla, valor);
	}
    
    
    /*
     * Curiosamente estas dos funciones son necesarias para que JAXBL o como demonios se llame utilize los tipos de datos DtSocioWS y DtProfesorWS
     * en las funciones abstractas con usuario. Si se quitan estas funciones los DtSocios y DtProfesor se retornan como DtUsuarios.
     */
    @WebMethod
	public DtProfesorWS seleccionarProfesor(String nick) throws UsuarioNoExisteException{
		DtUsuarioExt d = IUC.seleccionarUsuario(nick);
		return new DtProfesorWS((DtProfesorExt) d);
	}
    
    @WebMethod
	public DtSocioWS seleccionarSocio(String nick) throws UsuarioNoExisteException{
		DtUsuarioExt d = IUC.seleccionarUsuario(nick);
		return new DtSocioWS((DtSocioExt) d);
	}
	/*
	 * Esta función no solamente sirve para verificar que el ws está operativo pero tambien para verificar que estén utilizando la codificación correcta en el
	 * eclipse 🤣;
	 */
    @WebMethod
    public String ping() {
    	return "🏓";
    }
}
