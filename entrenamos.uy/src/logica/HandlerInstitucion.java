/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package logica;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

public class HandlerInstituciones {
	
	private static HandlerInstitucion instance = null;
	
	private Map<String, Institucion> instituciones;
	
	private HandlerInstitucion() {
		instituciones = new HashMap<>();
	}
	
	public static HandlerInstitucion getInstance() {
		if (instance == null) {
			instance = new HandlerInstitucion(); 
		}
		return instance;
	}
	
	public Institucion findInstitucion(String nombreIns) {
		return instituciones.get(nombreIns);
	}

	public Set<String> obtenerInstituciones() {
		Set<String> nombreInstituciones = new HashSet<>();
		Collection<Institucion> insCollection = instituciones.values();
		Iterator<Institucion> itIns = insCollection.iterator();
		while (itIns.hasNext()) {
			Institucion insAux = itIns.next();
			nombreInstituciones.add(insAux.getNombre());
		}
		return nombreInstituciones;
	}
	//Hay que ver que hacer con esta operacion
	public void addClase(String ins,String actDep,DtClase datos) {

	}

	public Clase findClase(String ins,String actDep,String clase) {
		Institucion insti = instituciones.get(ins);
		return insti.findClase(actDep,clase)
	}

	public void addInstitucion(Institucion ins) {
		if (!existeInstitucion(ins.getNombre()) {
			instituciones.put(ins.getNombre(), ins);
		}
	}

	public bool existeInstitucion(String nombre) {
		return instituciones.containsKey(nombre);
	}
