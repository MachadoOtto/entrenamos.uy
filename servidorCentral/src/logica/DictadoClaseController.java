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

import excepciones.ClaseLlenaException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;

import datatypes.DtFecha;
import datatypes.DtClase;
import datatypes.DtClaseExt;
import datatypes.TReg;

public class DictadoClaseController implements IDictadoClaseController {
	
	private static DictadoClaseController instance = null;
	private DictadoClaseController() { }
	public static DictadoClaseController getInstance() {
		if (instance == null)
			instance = new DictadoClaseController();
		return instance;
	}
	
	public Set<String> obtenerUsuarios() {
		return getHU().getNicknameUsuarios();		
	}
	
	public Set<String> obtenerInstituciones() {
		return getHI().obtenerInstituciones();
	}
	
	public Set<String> obtenerActividades(String ins) throws InstitucionException {
		return getHI().findInstitucion(ins).obtenerNombresActDep();
	}
	
	public Set<String> obtenerProfesores(String ins) throws InstitucionException {
		Set<Profesor> profes = getHI().findInstitucion(ins).getProfesores();
		Set<String> nickP = new HashSet<>();
		for (Profesor x: profes)
			nickP.add(x.getNickname());
		return nickP;
	}
	
	public Set<String> obtenerClases(String ins, String actDep) throws InstitucionException {
		return getHI().findInstitucion(ins).findActividad(actDep).getNombreClases();
	}
	
	public DtClaseExt seleccionarClase(String inst, String actDep, String clase) throws InstitucionException {
		 return getHI().findInstitucion(inst).getActDep(actDep).findClase(clase).getDt();
	}
	
	public int ingresarDatosClase(String ins, String actDep, DtClase datos) throws InstitucionException, FechaInvalidaException {
		Profesor profe = getHI().findInstitucion(ins).getProfesor(datos.getNicknameProfesor());
		ActividadDeportiva actDept = getHI().findInstitucion(ins).getActDep(actDep);
		if (!actDept.getFechaRegistro().esMenor(datos.getFechaRegistro())) {
			throw new FechaInvalidaException("La fecha de registro de la clase debe ser posterior a la fecha de registro de la actividad deportiva");
		} else {
			return actDept.addClase(datos,profe);
		}
	}
	
	public void inscribirSocio(String ins, String actDep, String clase, String socio, TReg tipoRegistro, DtFecha fechaReg) 
			throws  ClaseLlenaException, FechaInvalidaException, NoExisteCuponeraException, InstitucionException { 
		ActividadDeportiva ad = getHI().findInstitucion(ins).getActDep(actDep);
		Clase claseSelec = ad.findClase(clase);
		if(!claseSelec.hayLugar())
			throw new ClaseLlenaException("La clase seleccionada esta llena.");
		if (fechaReg.esMenor(claseSelec.getFechaRegistro())) {
			throw new FechaInvalidaException("La Fecha de Inscripcion es anterior a la Fecha en la que se registro la Clase seleccionada.");
		}
		if (claseSelec.getFechaClase().esMenor(fechaReg)) {
			throw new FechaInvalidaException("La Fecha de Inscripcion es posterior a la Fecha en la que inicia la Clase seleccionada.");
		}
		((Socio)getHU().findUsuario(socio)).inscribirSocio(ad, claseSelec, tipoRegistro, fechaReg);
	}
	
	public Set<String> obtenerSocios() {
		return getHU().obtenerNicknameSocios();
	}

// Guille: Esta funcion creo que no va.
//	public void modificarDatosClase(String ins, String actDep,DtClase datos) {
//		HandlerInstitucion hi = HandlerInstitucion.getInstance();
//		Institucion i = hi.findInstitucion(ins);
//		ActividadDeportiva actDept = i.getActDep(actDep);
//		Clase clase = actDept.findClase(datos.getNombre());
//		clase.modificarDatosClase(datos);
//	}
	private static HandlerInstitucion getHI() {
		return  HandlerInstitucion.getInstance();
	}
	
	private static HandlerUsuario getHU() {
		return  HandlerUsuario.getInstance();
	}

}
