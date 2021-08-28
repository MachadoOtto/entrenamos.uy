package logica;

import java.util.Set;

import datatypes.DtActividadDeportiva;
import datatypes.DtClaseExt;
import datatypes.DtActividadDeportivaExt;

public interface IActividadDeportivaController {
	
	public Set<String> obtenerInstituciones(); 
	
	public Boolean ingresarDatosActividadDep(String nombreInsti, DtActividadDeportiva datosAD);
	
	public Set<Set<String>> seleccionarActividadDeportiva(String ins, String nActDep, DtActividadDeportiva actDep);
	
	public Set<String> obtenerActividades(String ins);
	
	public Set<String> obtenerDeltaInstituciones(String nombreCup, String ins);
	
	// Migue: Esta funcion va a cambiar de controller, me lo llevo a DictadoClaseController, si no la usan en otro lado favor borrar
	public DtClaseExt seleccionarClase(String  ins, String actDep, String clase);
	
	public Set<String> obtenerSocios();
	
	public DtActividadDeportivaExt getActDepExt(String ins, String actDep);
}