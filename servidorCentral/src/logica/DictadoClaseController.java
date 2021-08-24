/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package logica;

import java.util.HashSet;
import java.util.Set;
import datatypes.DtClase;
import datatypes.DtClaseExt;
import datatypes.TReg;

public class DictadoClaseController implements IDictadoClaseController {
	
	private static DictadoClaseController instance = null;
	
	private DictadoClaseController() { }
	
	public static DictadoClaseController getInstance() {
		if (instance == null) {
			instance = new DictadoClaseController();
		}
		return instance;
	}
	
	public Set<String> obtenerUsuarios() {
		HandlerUsuario hu = HandlerUsuario.getInstance();
		return hu.getNicknameUsuarios();		
	}
	
	public Set<String> obtenerInstituciones() {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		return hi.obtenerInstituciones();
	}
	
	public Set<String> obtenerActividades(String ins) {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion instituto = hi.findInstitucion(ins);
		return instituto.obtenerActDep();
	}
	
	public Set<String> obtenerProfesores(String ins) {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion instituto = hi.findInstitucion(ins);
		Set<Profesor> profes = instituto.getProfesores();
		Set<String> nickP = new HashSet<>();
		for (Profesor x: profes) {
			nickP.add(x.getNickname());
		}
		return nickP;
	}
	
	public Set<String> obtenerClases(String ins, String actDep) {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion instituto = hi.findInstitucion(ins);
		ActividadDeportiva actividad = instituto.findActividad(actDep);
		return actividad.getNombreClases();
	}
	
	public DtClaseExt seleccionarClase(String inst, String actDep, String clase) {
		 HandlerInstitucion hi = HandlerInstitucion.getInstance();
		 Institucion ins = hi.findInstitucion(inst);
		 ActividadDeportiva act = ins.getActDep(actDep);
		 Clase clas = act.findClase(clase);
		 return clas.getDt();
	}
	
	public int ingresarDatosClase(String ins, String actDep, DtClase datos) {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion i = hi.findInstitucion(ins);
		ActividadDeportiva a = i.getActDep(actDep);
		Clase cc = i.findClase(actDep, datos.getNombre());
		Profesor profe = cc.getProfesor();
		return a.addClase(datos,profe);
	}
	
	public int inscribirSocio(String ins, String actDep, String clase, String socio, TReg tipoRegistro) {
		HandlerUsuario hu = HandlerUsuario.getInstance();
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Clase c = hi.findClase(ins,actDep,clase);
		Institucion i = hi.findInstitucion(ins);
		ActividadDeportiva ad = i.getActDep(actDep);
		Usuario usu = hu.findUsuario(socio);
		return ((Socio)usu).inscribirSocio(ad, c, tipoRegistro);
	}

// Guille: Esta funcion creo que no va.
//	public void modificarDatosClase(String ins, String actDep,DtClase datos) {
//		HandlerInstitucion hi = HandlerInstitucion.getInstance();
//		Institucion i = hi.findInstitucion(ins);
//		ActividadDeportiva actDept = i.getActDep(actDep);
//		Clase clase = actDept.findClase(datos.getNombre());
//		clase.modificarDatosClase(datos);
//	}
}
