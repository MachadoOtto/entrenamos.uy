package logica;

import java.util.HashSet;
import java.util.Set;

import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;

import datatypes.DtActividadDeportiva;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtClaseExt;
import datatypes.DtInstitucion;

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
	public Set<String> obtenerActividades(String ins) throws InstitucionException {
		return getHI().findInstitucion(ins).obtenerNombresActDep();
	}
	public Boolean ingresarDatosActividadDep(String nombreInsti, DtActividadDeportiva datosAD) throws InstitucionException,
			ActividadDeportivaException{
		Institucion inst = getHI().findInstitucion(nombreInsti);
		for (String x : getHI().obtenerInstituciones()) {
			if (getHI().findInstitucion(x).existeActDep(datosAD.getNombre())) {
				throw new ActividadDeportivaException("La Actividad Deportiva ya existe en el Sistema.");
			}
		}
		if (!inst.existeActDep(datosAD.getNombre())) {
			inst.addActividadDeportiva(datosAD);
			return true;
		}
		return false;
	}
	
	public Set<String> seleccionarInstitucion(String ins) throws InstitucionException {
		return getHI().findInstitucion(ins).obtenerNombresActDep();
	}
	
	public Set<String> obtenerDeltaInstituciones(String nombreCup, String ins) throws InstitucionException {
		Set<String> x = new HashSet<>();
		for(String y: getHI().findInstitucion(ins).getActsDeps().keySet()) {
			x.add(y);
			for(String z: getHC().getCup(nombreCup).getNombresActDep()) {
				if(y.equals(z)) {
					x.remove(y);
					break;
				}
			}
		}
		return x;
	}
	
	public DtClaseExt seleccionarClase(String inst, String actDep, String clase) throws InstitucionException, 
			ActividadDeportivaException, ClaseException {
		return getHI().findInstitucion(inst).getActDep(actDep).findClase(clase).getDt();
	}
	
	public Set<String> obtenerSocios(){
		return getHU().obtenerNicknameSocios(); 
	}
	
	public DtActividadDeportivaExt getActDepExt(String ins, String actDep) throws InstitucionException, 
			ActividadDeportivaException {
		return getHI().findInstitucion(ins).getActDep(actDep).getDtExt();
	}
	
	private static HandlerInstitucion getHI() {
		return  HandlerInstitucion.getInstance();
	}
	private static HandlerUsuario getHU() {
		return  HandlerUsuario.getInstance();
	}
	private static HandlerCuponera getHC() {
		return  HandlerCuponera.getInstance();
	}
	
	public int altaInstitucion(String nombre, String descripcion, String URL) {
		if(!getHI().existeInstitucion(nombre)){
			Institucion i = new Institucion(nombre,descripcion,URL);
			getHI().addInstitucion(i);
			return 0;
		}
		return 1;
	}
	
	public DtInstitucion obtenerDatosInstitucion(String inst) throws InstitucionException {
		Institucion instit = getHI().findInstitucion(inst);
		return instit.obtenerDatos();
	}
}
