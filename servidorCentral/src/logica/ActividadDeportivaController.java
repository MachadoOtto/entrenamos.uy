package logica;

import java.util.HashSet;
import java.util.Set;

import datatypes.DtActividadDeportiva;
import datatypes.DtClaseExt;
import datatypes.TReg;

public class ActividadDeportivaController implements IActividadDeportivaController {
	private static ActividadDeportivaController instance = null;
	
	private ActividadDeportivaController() {}
	
	public static ActividadDeportivaController getInstance(){
		if(instance == null)
			instance = new ActividadDeportivaController();
		return instance;
	}	
	
	public Set<String> obtenerInstituciones(){
		return getHI().obtenerInstituciones();
	}		
	
	public Boolean ingresarDatosActividadDep(String nombreInsti, DtActividadDeportiva datosAD){
		Institucion inst = getHI().findInstitucion(nombreInsti);
		if (!inst.existeActDep(datosAD.getNombre())) {
			inst.addActividadDeportiva(datosAD);
			return true;
		}
		return false;
	}
	
	public Set<String> seleccionarInstitucion(String ins){
		return getHI().findInstitucion(ins).obtenerNombresActDep();
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
		return getHI().findInstitucion(inst).getActDep(actDep).findClase(clase).getDt();
	}
	
	public Set<String> obtenerSocios(){
		return getHU().obtenerNicknameSocios(); 
	}
	
	public int inscribirSocio(String ins, String actDep, String clase, String socio, TReg tipoRegistro){
//	//Funcion critica. Analizar bien!!! (ARREGLAR)
//	HandlerUsuario hu = HandlerUsuario.getInstance();
//	HandlerInstitucion hi = HandlerInstitucion.getInstance();
//	Clase c = hi.findClase(ins,actDep,clase);
//	Institucion i = hi.findInstitucion(ins);
//	ActividadDeportiva ad = i.getActDep(actDep);
//	Usuario usu = hu.findUsuario(socio);
//	int res = 1;
//	if (c != null) {
//		res = ((Socio) usu).inscribirSocio(ad, c, tipoRegistro);
//	}
	return 0;
	}
	
	private static HandlerInstitucion getHI() {
		return  HandlerInstitucion.getInstance();
	}
	private static HandlerUsuario getHU() {
		return  HandlerUsuario.getInstance();
	}
}
