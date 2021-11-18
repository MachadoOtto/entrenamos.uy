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
import java.util.List;
import java.util.Set;

import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;
import excepciones.UsuarioNoExisteException;
import logica.persistencia.DataPersistencia;
import datatypes.DtFecha;
import datatypes.DtReciboClase;
import datatypes.TEstado;
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
	
	public String obtenerInstitucionActDep(String actDep) {
		String res = null;
		Set<String> instituciones = obtenerInstituciones();
		for (String x : instituciones) {
			try {
				for (String y : obtenerActividades(x)) {
					if (y.equals(actDep)) {
						res = x;
						return res;
					}
				}
			} catch(InstitucionException ignore){
				;
			}
		}
		return res;
	}
	
	public Set<String> obtenerActividadesAprobadas(String ins) throws InstitucionException{
		Set<String> res = new HashSet<>();
		for (String x : getHI().findInstitucion(ins).obtenerNombresActDep()) {
			try {
				if (getHI().findInstitucion(ins).getActDep(x).getEstado().equals(TEstado.aceptada)) {
					res.add(x);
				}
			} catch (InstitucionException  ignore) {
				;
			}
			  catch (ActividadDeportivaException ignore) {
				  ;
			  }
		}
		return res;
	}
	
	public Set<String> obtenerProfesores(String ins) throws InstitucionException {
		Set<Profesor> profes = getHI().findInstitucion(ins).getProfesores();
		Set<String> nickP = new HashSet<>();
		for (Profesor x: profes)
			nickP.add(x.getNickname());
		return nickP;
	}
	
	public Set<String> obtenerClases(String ins,  String actDep) throws InstitucionException {
		return getHI().findInstitucion(ins).findActividad(actDep).getNombreClases();
	}
	
	public DtClaseExt seleccionarClase(String inst,  String actDep,  String clase) throws InstitucionException,  ClaseException, 
			ActividadDeportivaException {
		Clase classFind = getHI().findInstitucion(inst).getActDep(actDep).findClase(clase);
		if (classFind != null) {
			return classFind.getDt();
		} else {
			throw new ClaseException("La clase seleccionada no pertenece a esta ActividadDeportiva o Institucion.");
		}
	}
	
	public DtClaseExt buscarClase(String nombreClase) throws ClaseException {
		DtClaseExt datosClase = null;
		for (String x: getHI().obtenerInstituciones()) {
			try {
				for (String y: getHI().findInstitucion(x).obtenerNombresActDep()) {
					try {
						datosClase = getHI().findInstitucion(x).findActividad(y).findClase(nombreClase).getDt();
						return datosClase;
					} catch(ClaseException ignore) {
						;
					}
				}
			} catch(InstitucionException ignore) {
				;
			}
		}
		// Si llega hasta aqui la clase esta en la persistencia o no existe
		return DataPersistencia.getInstance().getClase(nombreClase);
	}
	
	public int ingresarDatosClase(String ins,  String actDep,  DtClase datos) throws InstitucionException,  FechaInvalidaException, 
			ClaseException,  UsuarioNoExisteException,  ActividadDeportivaException {
		for (String x: getHI().obtenerInstituciones()) {
			for (String y: getHI().findInstitucion(x).obtenerNombresActDep()) {
				if (getHI().findInstitucion(x).findActividad(y).getNombreClases().contains(datos.getNombre())) {
					throw new ClaseException("Ya existe una clase con ese nombre en el sistema.");
				}
			}
		}
		Profesor profe = getHI().findInstitucion(ins).getProfesor(datos.getNicknameProfesor());
		ActividadDeportiva actDept = getHI().findInstitucion(ins).getActDep(actDep);
		if (!datos.getFechaRegistro().esMenor(datos.getFechaClase())) {
			throw new FechaInvalidaException("La fecha de registro de la clase debe ser anterior a su fecha de inicio.");
		}
		if (!actDept.getFechaRegistro().esMenor(datos.getFechaRegistro())) {
			throw new FechaInvalidaException("La fecha de registro de la clase debe ser posterior a la fecha de registro de la actividad deportiva.");
		} else {
			return actDept.addClase(datos,  profe);
		}
	}
	
	public void inscribirSocio(String ins,  String actDep,  String clase,  String socio,  TReg tipoRegistro,  DtFecha fechaReg,  String cuponera) 
			throws  ClaseException,  FechaInvalidaException,  NoExisteCuponeraException,  InstitucionException,  
			UsuarioNoExisteException,  ActividadDeportivaException { 
		ActividadDeportiva activity = getHI().findInstitucion(ins).getActDep(actDep);
		Clase claseSelec = activity.findClase(clase);
		claseSelec.hayLugar();
		if (!claseSelec.hayLugar()) {
			throw new ClaseException("La clase seleccionada esta llena.");
		}
		if (!claseSelec.getFechaRegistro().esMenor(fechaReg)) {
			throw new FechaInvalidaException("La Fecha de Inscripcion es anterior a la Fecha en la que se registro la Clase seleccionada.");
		}
		if (!fechaReg.esMenor(claseSelec.getFechaClase())) {
			throw new FechaInvalidaException("La Fecha de Inscripcion es posterior a la Fecha en la que inicia la Clase seleccionada.");
		}
		if (tipoRegistro==TReg.general) {
			((Socio) getHU().findUsuario(socio)).inscribirSocio(activity,  claseSelec,  tipoRegistro,  fechaReg,  null);
		}
		else {
			((Socio) getHU().findUsuario(socio)).inscribirSocio(activity,  claseSelec,  tipoRegistro,  fechaReg,  getHC().getCup(cuponera));
		}
	}
	
	public Set<String> obtenerSocios() {
		return getHU().obtenerNicknameSocios();
	}
	@Override
	public Set<String> getCuponerasSocioClase(String nombreSocio,  String nombreInst,  String nombreAd,  String nombreClase) {
		Set<String> res = new HashSet<>();
		try {
			for (ReciboCuponera r :((Socio) getHU().findUsuario(nombreSocio)).getReciboCuponera())
				if (r.getCuponera().getNombresActDep().contains(nombreAd)) {
					res.add(r.getCuponera().getNombre());
				}
		} catch (UsuarioNoExisteException ignore) {
			;
		}
		return res;
	}

	private static HandlerInstitucion getHI() {
		return  HandlerInstitucion.getInstance();
	}
	private static HandlerCuponera getHC() {
		return  HandlerCuponera.getInstance();
	}	
	private static HandlerUsuario getHU() {
		return  HandlerUsuario.getInstance();
	}

	public Set<String> getCuponerasDisponibles(String nombreSocio,  String nombreInsti,  String nombreAd) 
			throws UsuarioNoExisteException,  InstitucionException,  ActividadDeportivaException {
		Set<String> cupsDisp = new HashSet<>();
		ActividadDeportiva actDep = getHI().findInstitucion(nombreInsti).getActDep(nombreAd);
		Socio socioIt = (Socio) getHU().findUsuario(nombreSocio);
		for (ReciboCuponera r : socioIt.getReciboCuponera()) {
			int iteradorMagico = 0;
			Cuponera cupActual = r.getCuponera();
			for (ReciboClase reciboCl: socioIt.getReciboClase())
				if (reciboCl.esTipoCuponera() && reciboCl.getCuponera() == cupActual)
					iteradorMagico++;
			if (cupActual.getNombresActDep().contains(nombreAd) && iteradorMagico < cupActual.cantidadClases(actDep)) {
				cupsDisp.add(cupActual.getNombre());
			}
		}
		return cupsDisp;
	}
	
	@Override
	public Set<String> sortearPremios(String ins, String actDep, String clase)
			throws InstitucionException, ClaseException, ActividadDeportivaException {
		Clase leclase = getHI().findInstitucion(ins).getActDep(actDep).findClase(clase);
		if (leclase != null) {
			DtFecha fechaf = new DtFecha();
			fechaf.setMinutos(fechaf.getMinutos()-leclase.getAD().getDuracionMinutos());
			if (leclase.getFechaClase().esMenor(fechaf)) {
				List<ReciboClase> recibos = leclase.getRecibo();
				Set<Socio> ssss = new HashSet<>();
				for (ReciboClase x: recibos) {
					ssss.add(x.getSocio());
				}
				ssss = leclase.getPrize().realizarSorteo(ssss);
				Set<String> result = new HashSet<>();
				for (Socio x: ssss) {
					result.add(x.getNickname());
					x.addPremio(leclase.getPrize());
				}
				return result;
			}
			else
				throw new ClaseException("No se puede realizar sorteos de clases no finalizadas.");
		} else {
			throw new ClaseException("La clase seleccionada no pertenece a esta ActividadDeportiva o Institucion.");
		}
	}
	
	public Set<DtReciboClase> bringTheRegistersPls(String nombreClase) throws ClaseException {
		Set<DtReciboClase> recibos = new HashSet<>();
		for (String x: getHI().obtenerInstituciones()) {
			try {
				for (String y: getHI().findInstitucion(x).obtenerNombresActDep()) {
					try {
						Clase laClase = getHI().findInstitucion(x).findActividad(y).findClase(nombreClase);
						for (ReciboClase reciboQueRecibe: laClase.getRecibo()) {
							recibos.add(reciboQueRecibe.getDt());
						}
						return recibos;
					} catch(ClaseException ignore) {
						;
					}
				}
			} catch(InstitucionException ignore) {
				;
			}
		}
		throw new ClaseException("La clase " + nombreClase + " no existe en el Sistema.");
	}
	
	/*public String nombreActDeClase(String nombreClase) {
    	String nombreActividad = null;
    	IDictadoClaseController IDCC = LaFabrica.getInstance().obtenerIDictadoClaseController();
		for (String x: IDCC.obtenerInstituciones()) {
			try {
				for (String y: IDCC.obtenerActividades(x)) {
					if (IDCC.obtenerClases(x,  y).contains(nombreClase)) {
						nombreActividad = y;
						return nombreActividad;
					}
				}
			} catch(InstitucionException ignore) { }
		}
		for (String x: DataPersistencia.getInstance().obtenerActividades()) {
			if (DataPersistencia.getInstance().obtenerClases(x).contains(nombreClase)) {
				nombreActividad = x;
				return nombreActividad;
			}
		}
		return nombreActividad;
    }*/
	
}

//Guille: Esta funcion creo que no va.
//	public void modificarDatosClase(String ins,  String actDep, DtClase datos) {
//		HandlerInstitucion hi = HandlerInstitucion.getInstance();
//		Institucion i = hi.findInstitucion(ins);
//		ActividadDeportiva actDept = i.getActDep(actDep);
//		Clase clase = actDept.findClase(datos.getNombre());
//		clase.modificarDatosClase(datos);
//	}