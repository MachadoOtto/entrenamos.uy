package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import excepciones.ActividadDeportivaException;
import excepciones.CategoriaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;

import datatypes.DtActividadDeportiva;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtCategoria;
import datatypes.DtClaseExt;
import datatypes.DtInstitucion;
import datatypes.TEstado;

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
			Map<String,Categoria> cat = new HashMap<>();
			for(String x: datosAD.getCategorias())
				try {
					cat.put(x, getHCAT().findCategoria(x));
				} catch (CategoriaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			inst.addActividadDeportiva(datosAD,cat);
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
	private static HandlerCategoria getHCAT() {
		return  HandlerCategoria.getInstance();
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
	
	public Set<String> obtenerActDepIngresadas(){
		Set<String> r = new HashSet<>();
		for(String i: getHI().obtenerInstituciones()) {
			try {
				for(Entry<String, ActividadDeportiva> a: getHI().findInstitucion(i).getActsDeps().entrySet()) {
					if(a.getValue().getEstado() == TEstado.ingresada) {
						r.add(new String(a.getKey()));
					}
				}
			} catch (InstitucionException e) {
				// Que buena idea agregar un exception a un handler xd.
				e.printStackTrace();
			}
		}
		return r;
	}
	
	public void aprobarActividad(String ad, TEstado ok) {
		for(String i: getHI().obtenerInstituciones()) {
			 try {
				if(getHI().findInstitucion(i).getActsDeps().containsKey(ad)) {
					 getHI().findInstitucion(i).getActsDeps().get(ad).setEstado(ok);
					 break;
				 }
			} catch (InstitucionException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ingresarCatergoria(DtCategoria datos) throws CategoriaException {
		getHCAT().addCategoria(new Categoria(datos.getNombre(), datos.getDescripcion()));
	}

	public Set<String> obtenerCategorias() {
		HandlerCategoria hc = HandlerCategoria.getInstance();
		return hc.getNombreCategorias();
	}
}