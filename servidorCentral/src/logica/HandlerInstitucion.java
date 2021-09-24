/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import excepciones.InstitucionException;

public class HandlerInstitucion {
	
	private static HandlerInstitucion instance = null;
	private Logger log;
	private Map<String, Institucion> instituciones;
	
	private HandlerInstitucion() {
		instituciones = new HashMap<>();
		
		log = Logger.getLogger("LoggerVasileano");
		log.setLevel(Level.INFO);
		Handler handler = new ConsoleHandler();
		log.addHandler(handler);
	}
	
	public static HandlerInstitucion getInstance() {
		if (instance == null)
			instance = new HandlerInstitucion(); 
		return instance;
	}
	
	public Institucion findInstitucion(String nombreIns) throws InstitucionException {
		Institucion res = instituciones.get(nombreIns);
		if (res != null)
			return instituciones.get(nombreIns);
		else 
			throw new InstitucionException("La institucion no existe en el Sistema.");
	}

	public Set<String> obtenerInstituciones() { 
		Set<String> nombreInstituciones = new HashSet<>();
		for(Map.Entry<String, Institucion> x: instituciones.entrySet())
			nombreInstituciones.add(x.getKey());
		return nombreInstituciones; 
	}

	public int addInstitucion(Institucion ins) {
		if (!existeInstitucion(ins.getNombre())) {
			instituciones.put(ins.getNombre(), ins);
			log.info("Institucion "+ins.getNombre()+"registered, total: "+instituciones.size());
			return 0;
		}
		return 1;
	}

	public boolean existeInstitucion(String nombre) {
		return instituciones.containsKey(nombre);
	}
}	
