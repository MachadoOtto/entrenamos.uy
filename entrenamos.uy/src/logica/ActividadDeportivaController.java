package logica;
import java.util.Set;

import datatypes.DtActividadDeportiva;
import datatypes.DtClaseExt;
import datatypes.TReg;
import datatypes.tReg;

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
		Institucion inst = hi.find(nombreInsti);
		if (inst.existeActDep(datosAD)) {
			ActividadDeportiva act = new ActividadDeportiva(datosAD);
            inst.add(act);
		}
		return inst.existeActDep(datosAD);
	}
	
	public Set<String> seleccionarInstitucion(String ins){
		
		  HandlerInstitucion hi = HandlerInstitucion.getInstance();
		  Institucion inst = hi.find(ins);
		  
		  /* AIUDAAAAAAAAAAAAAAAAAAAAAAA  
		  Set<String> res;         
		  acts =  actividades del inst*/
		  
		  while (acts.hasNext()) {
			  res.add(acts.getNombre);
		  }
		  return res;
	}
	
	public /*DtActividadDeportiva*/ Set<Set<String>> seleccionarActividadDeportiva(String ins, String nActDep){
		
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion inst = hi.find(ins);
		ActividadDeportiva act = ins.getActDep(nActDep);
		Set<String> nombreClass = act.getNombreClases();
		Set<String> nombreCup = act.getNombreCuponeras(); //Agregar esta función
		Set<Set<String>> res;
		res.add(nombreClass);
		res.add(nombreCup);
		return res;
		
	}
	
	public Set<String> obtenerDeltaInstituciones(String nombreCup, String ins){
		
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion inst = hi.find(ins);
		HandlerCuponera hc = HandlerCuponera.getInstance();
		Cuponera cup = hc.find(nombreCup);
		
		return cup.getNombresActDep();
		}
	
	public DtClaseExt seleccionarClase(String inst, String actDep, String clase) {
		
		 HandlerInstitucion hi = HandlerInstitucion.getInstance();
		 Institucion ins = hi.find(inst);
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
	int res = 0;
	if (c != null) {
		res = usu.inscribirSocio(actDep, c, tipoRegistro);
	}
	return res;
	}
}
