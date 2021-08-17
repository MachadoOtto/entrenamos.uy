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

public class DictadoClaseController implements IDictadoClaseController {
	
	private static DictadoClaseController instance = null;
	
	private DictadoClaseController() { }
	
	public static DictadoClaseController getInstance() {
		if (instance == null) {
			instance = new DictadoClaseController();
		}
		return instance;
	}
	
	public Set<String> obtenerInstituciones() {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		return hi.obtenerInstituciones();
	}

	public int ingresarDatosClase(String ins,String actDep,DtClase datos) {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion i = hi.findInstitucion(ins);
		HandlerUsuario hu = HandlerUsuario.getInstance();
		Profesor profe = hu.findUsuario(datos.nombreProfesor);
		return addClase(actDep,datos,pp);
	}

	public void modificarDatosClase(String ins, String actDep,DtClase datos) {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion i = hi.findInstitucion(ins);
		ActividadDeportiva actDept = i.getActDep(actDep);
		Clase clase = actDep.findClase(datos.nombre);
		clase.modificarDatosClase(datos);
	}
