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
import excepciones.UsuarioNoExisteException;
import logica.persistencia.DataPersistencia;
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
		if (instance == null)
			instance = new ActividadDeportivaController();
		return instance;
	}	
	
	public Set<String> obtenerInstituciones(){
		return getHI().obtenerInstituciones();
	}		
	public Set<String> obtenerActividades(String ins) throws InstitucionException {
		return getHI().findInstitucion(ins).obtenerNombresActDep();
	}
	public Boolean ingresarDatosActividadDep(String nombreInsti,  DtActividadDeportiva datosAD) throws InstitucionException, 
			ActividadDeportivaException{
		Institucion inst = getHI().findInstitucion(nombreInsti);
		for (String x : getHI().obtenerInstituciones()) {
			if (getHI().findInstitucion(x).existeActDep(datosAD.getNombre())) {
				throw new ActividadDeportivaException("La Actividad Deportiva ya existe en el Sistema.");
			}
		}
		if (!inst.existeActDep(datosAD.getNombre())) {
			Map<String,  Categoria> cat = new HashMap<>();
			for (String x: datosAD.getCategorias())
				try {
					cat.put(x,  getHCAT().findCategoria(x));
				} catch (CategoriaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				inst.addActividadDeportiva(datosAD,  cat,  (Profesor) getHU().findUsuario(datosAD.getCreador()));
				((Profesor) getHU().findUsuario(datosAD.getCreador())).addActDep(inst.getActDep(datosAD.getNombre()));
			} catch (UsuarioNoExisteException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	public Set<String> obtenerDeltaInstituciones(String nombreCup,  String ins) throws InstitucionException {
		Set<String> actividadesDeportivas = new HashSet<>();
		for (String y: getHI().findInstitucion(ins).getActsDeps().keySet()) {
			actividadesDeportivas.add(y);
			for (String z: getHC().getCup(nombreCup).getNombresActDep()) {
				if (y.equals(z)) {
					actividadesDeportivas.remove(y);
					break;
				}
			}
		}
		Set<String> res = new HashSet<>();
		res.addAll(actividadesDeportivas);
		for (String q: actividadesDeportivas) {
			try {
				if (getHI().findInstitucion(ins).getActDep(q).getEstado()!=TEstado.aceptada)
					res.remove(q);
			} catch (ActividadDeportivaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstitucionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public DtClaseExt seleccionarClase(String inst,  String actDep,  String clase) throws InstitucionException,  
			ActividadDeportivaException,  ClaseException {
		return getHI().findInstitucion(inst).getActDep(actDep).findClase(clase).getDt();
	}
	
	public Set<String> obtenerSocios(){
		return getHU().obtenerNicknameSocios(); 
	}
	
	public DtActividadDeportivaExt getActDepExt(String ins,  String actDep) throws InstitucionException,  
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
	
	public int altaInstitucion(String nombre,  String descripcion,  String URL) {
		if (!getHI().existeInstitucion(nombre)){
			Institucion fing = new Institucion(nombre,  descripcion,  URL);
			getHI().addInstitucion(fing);
			return 0;
		}
		return 1;
	}
	
	public DtInstitucion obtenerDatosInstitucion(String inst) throws InstitucionException {
		Institucion instit = getHI().findInstitucion(inst);
		return instit.obtenerDatos();
	}
	
	public Set<String> obtenerActDepIngresadas(){
		Set<String> res = new HashSet<>();
		for (String i: getHI().obtenerInstituciones()) {
			try {
				for (Entry<String,  ActividadDeportiva> actividades: getHI().findInstitucion(i).getActsDeps().entrySet()) {
					if (actividades.getValue().getEstado() == TEstado.ingresada) {
						res.add(new String(actividades.getKey()));
					}
				}
			} catch (InstitucionException e) {
				// Que buena idea agregar un exception a un handler xd.
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public void aprobarActividad(String actDep,  TEstado estado) {
		for (String i: getHI().obtenerInstituciones()) {
			 try {
				if (getHI().findInstitucion(i).getActsDeps().containsKey(actDep)) {
					 getHI().findInstitucion(i).getActsDeps().get(actDep).setEstado(estado);
					 break;
				 }
			} catch (InstitucionException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ingresarCatergoria(DtCategoria datos) throws CategoriaException {
		getHCAT().addCategoria(new Categoria(datos.getNombre()));
	}

	public Set<String> obtenerCategorias() {
		HandlerCategoria handlerCategoria = HandlerCategoria.getInstance();
		return handlerCategoria.getNombreCategorias();
	}

	public DtActividadDeportivaExt buscarActDep(String nombreActDep) throws ActividadDeportivaException {
		DtActividadDeportivaExt datosActDep = null;
		for (String ins : getHI().obtenerInstituciones()) {
			try {
				datosActDep = getHI().findInstitucion(ins).getActDep(nombreActDep).getDtExt();
				return datosActDep;
			} catch(ActividadDeportivaException | InstitucionException ignore) {
				;
			}
		}
		//BuscarEnLaPersistencia!

		return DataPersistencia.getInstance().getActividad(nombreActDep);
	}

	@Override
	public void finalizarActividad(String actDep) {
		for (String i: getHI().obtenerInstituciones()) {
			 try {
				if (getHI().findInstitucion(i).getActsDeps().containsKey(actDep)) {
					 getHI().findInstitucion(i).getActsDeps().get(actDep).setEstado(TEstado.finalizada);
					 DataPersistencia.getInstance().persistir2(getHI().findInstitucion(i).getActsDeps().get(actDep));
					 getHI().findInstitucion(i).finalizarAct(actDep);
					 break;
				 }
			} catch (InstitucionException e) {
				e.printStackTrace();
			}
		}
	}
}