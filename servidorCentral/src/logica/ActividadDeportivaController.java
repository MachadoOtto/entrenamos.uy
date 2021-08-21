package logica;

import java.util.HashSet;
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
		if (!inst.existeActDep(datosAD.getNombre())) {
			inst.addActividadDeportiva(datosAD);
			return true;
		}
		return false;
	}
	
	public Set<String> seleccionarInstitucion(String ins){
		
		  HandlerInstitucion hi = HandlerInstitucion.getInstance();
		  Institucion inst = hi.findInstitucion(ins);
		  Set<String> res = inst.obtenerActDep();
		  return res;
	}
	
	//Guille: Falta implementar la funcion comentada en inst.
	public Set<Set<String>> seleccionarActividadDeportiva(String ins, String nActDep, DtActividadDeportiva actDep){
		
	//	HandlerInstitucion hi = HandlerInstitucion.getInstance();
	//	Institucion inst = hi.findInstitucion(ins);
		//ActividadDeportiva act = inst.getActDep(nActDep);
		Set<Set<String>> x = new HashSet<>();
		return x;
		//return inst.seleccionarActividadDeportiva(nActDep);

	}
	
	public Set<String> obtenerDeltaInstituciones(String nombreCup, String ins){
		//Aaa pero hicieron cualquiera...
		//HandlerInstitucion hi = HandlerInstitucion.getInstance();
		//Institucion inst = hi.findInstitucion(ins);
		//HandlerCuponera hc = HandlerCuponera.getInstance();
		//Cuponera cup = hc.getCup(nombreCup);
		
		//cup.getNombresActDep();
		Set<String> x = new HashSet<>();
		return x;
		}
	
	public DtClaseExt seleccionarClase(String inst, String actDep, String clase) {
		
		 HandlerInstitucion hi = HandlerInstitucion.getInstance();
		 Institucion ins = hi.findInstitucion(inst);
		 ActividadDeportiva act = ins.getActDep(actDep);
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
	Clase c = hi.findClase(ins,actDep,clase);
	Institucion i = hi.findInstitucion(ins);
	ActividadDeportiva ad = i.getActDep(actDep);
	Usuario usu = hu.findUsuario(socio);
	int res = 1;
	if (c != null) {
		res = ((Socio) usu).inscribirSocio(ad, c, tipoRegistro);
	}
	return res;
	}
}
