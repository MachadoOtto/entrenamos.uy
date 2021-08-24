package logica;

import java.util.Set;

import datatypes.DtActividadDeportiva;
import datatypes.DtClaseExt;
import datatypes.TReg;

public interface IActividadDeportivaController {
	
	public Set<String> obtenerInstituciones(); 
	
	public Boolean ingresarDatosActividadDep(String nombreInsti, DtActividadDeportiva datosAD);
	
	//public /*DtActividadDeportiva,*/ Set<Set<String>> seleccionarActividadDeportiva(String ins, String nActDep);
	public Set<Set<String>> seleccionarActividadDeportiva(String ins, String nActDep, DtActividadDeportiva actDep);
	
	public Set<String> obtenerDeltaInstituciones(String nombreCup, String ins);
	
	// Migue: Esta funcion va a cambiar de controller, me lo llevo a DictadoClaseController, si no la usan en otro lado favor borrar
	public DtClaseExt seleccionarClase(String  ins, String actDep, String clase);
	
	public Set<String> obtenerSocios();
	
	// Migue: Esta funcion va a cambiar de controller, me lo llevo a DictadoClaseController, si no la usan en otro lado favor borrar
	public int inscribirSocio(String ins, String actDep, String clase, String socio, TReg tipoRegistro);
}