package logica;
import java.util.Set;

import datatypes.DtActividadDeportiva;
import datatypes.DtClaseExt;
import datatypes.TReg;

public class ActividadDeportivaController implements IActividadDeportivaController {

	private static ActividadDeportivaController instance = null;
	
	private ActividadDeportivaController() 
	{		
	}
	
	public static ActividadDeportivaController getInstance(){
		
		if ( instance == null )
		{
			instance = new ActividadDeportivaController();
		}
		return instance;
	}	
	
	public Set<String> obtenerInstituciones(){
		
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		return hi.obtenerInstituciones();		
	}		
	
	public Boolean ingresarDatosActividadDep(String nombreInsti, DtActividadDeportiva datosAD){
		  
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion inst = hi.findInstitucion(nombreInsti);
		if (!inst.existeActDep(datosAD.nombre)) {
			nuevaActDep(datosAD);
		}
		return inst.existeActDep(datosAD.nombre);
	}
	
	public Set<String> seleccionarInstitucion(String ins){
		
		  HandlerInstitucion hi = HandlerInstitucion.getInstance();
		  Institucion inst = hi.findInstitucion(ins);
		  Set<String> res = inst.obtenerActDep();
		  return res;
	}
	
	public Set<Set<String>> seleccionarActividadDeportiva(String ins, String nActDep, DtActividadDeportiva actDep){
		

		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion inst = hi.findInstitucion(ins);
		ActividadDeportiva act = ins.getActDep(nActDep);
		DtActividadDeportiva actDep(act.Nombre, act.Descripcion, act.Duracion, act.Costo, act.Fecha);
		return inst.seleccionarActividadDeportiva(nActDep);

		return res;
	}
	
	public Set<String> obtenerDeltaInstituciones(String nombreCup, String ins){
		
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion inst = hi.find(ins);
		HandlerCuponera hc = HandlerCuponera.getInstance();
		Cuponera cup = hc.getCup(nombreCup);
		
		return cup.getNombresActDep();
		}
	
	public DtClaseExt seleccionarClase(String inst, String actDep, String clase) {
		
		 HandlerInstitucion hi = HandlerInstitucion.getInstance();
		 Institucion ins = hi.findInstitucion(inst);
		 ActividadDeportiva act = inst.getActDep(actDep);
		 Clase clas = act.findClase(clase);
		 return clas.getDt();
	}
	
	public Set<String> obtenerSocios(){
		
		HandlerUsuario hu = HandlerUsuario.getInstance();
		return hu.obtenerNicknameSocios();
		 
	}
	
	public int inscribirSocio(String ins, String actDep, String clase, String socio, TReg tipoRegistro){
	
	HandlerUsuario hu = HandlerUsuario.getInstance();
	HandlerInstitucion hi = HandlerInstitucion.getInstance();
	Clase c = hi.findClase(actDep,clase);
	Socio usu = hu.findUsuario(socio);
	int res = 1;
	if (c != null) {
		res = usu.inscribirSocio(actDep, c, tipoRegistro);
	}
	return res;
	}
}
