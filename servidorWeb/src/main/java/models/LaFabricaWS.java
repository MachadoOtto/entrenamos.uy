package models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import datatypes.DtActividadDeportiva;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtCategoria;
import datatypes.DtClase;
import datatypes.DtClaseExt;
import datatypes.DtCuponera;
import datatypes.DtFecha;
import datatypes.DtInstitucion;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import datatypes.DtUsuarioExt;
import datatypes.TEstado;
import datatypes.TReg;
import excepciones.ActividadDeportivaException;
import excepciones.CategoriaException;
import excepciones.ClaseException;
import excepciones.CuponeraInmutableException;
import excepciones.CuponeraNoExisteException;
import excepciones.CuponeraRepetidaException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;
import excepciones.UsuarioNoExisteException;
import webservices.ActividadDeportivaException_Exception;
import webservices.ClaseException_Exception;
import webservices.CuponeraNoExisteException_Exception;
import webservices.DtFechaWS;
import webservices.DtSocioWS;
import webservices.DtUsuarioWS;
import webservices.InstitucionException_Exception;
import webservices.UsuarioNoExisteException_Exception;
import tools.Cnv;

public class LaFabricaWS {
	public class UsuarioController implements IUsuarioController {
		webservices.WSUsuarioControllerService service = new webservices.WSUsuarioControllerService();
		webservices.WSUsuarioController port = service.getWSUsuarioControllerPort();
		
		@Override
		public void comprarCuponera(String cuponera,  String socio,  DtFecha fechaCompra)
				throws UsuarioNoExisteException, CuponeraNoExisteException {
			try {
				port.comprarCuponera(cuponera, socio, Cnv.fecha(fechaCompra));
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException("no existe tal usuario");
			} catch (CuponeraNoExisteException_Exception e) {
				throw new CuponeraNoExisteException("no existe tal cuponera");
			}
		}

		@Override
		public void dejarDeSeguir(String seguidor, String seguido) throws UsuarioNoExisteException {
			try {
				port.dejarDeSeguir(seguidor, seguido);
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException("no existe tal usuario");
			}
		}

		@Override
		public void editarDatosBasicos(String userNick, DtUsuario datoUser) throws UsuarioNoExisteException {
			try {
				port.editarDatosBasicos(userNick, Cnv.usuario(datoUser));
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException("no existe tal usuario");
			}
		}

		@Override
		public int ingresarDatosUsuario(DtUsuario datoUser) throws InstitucionException {
			try {
				return port.ingresarDatosUsuario(Cnv.usuario(datoUser));
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException("no existe tal institucion");
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public Set<String> obtenerInstituciones() {
			Set<String> x = new HashSet<>();
			x.addAll((Collection<? extends String>) port.obtenerInstituciones().getContenido());
			return x;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Set<String> obtenerUsuarios() {
			Set<String> x = new HashSet<>();
			x.addAll((Collection<? extends String>) port.obtenerUsuarios().getContenido());
			return x;
		}

		@Override
		public void seguir(String seguidor, String seguido) throws UsuarioNoExisteException {
			try {
				port.seguir(seguidor, seguido);
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException("no existe tal usuario");
			}
		}

		@Override
		public DtUsuarioExt seleccionarUsuario(String userNick) throws UsuarioNoExisteException {
			try {
				return (DtUsuarioExt) Cnv.usuario(port.seleccionarUsuario(userNick));
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException("no existe tal usuario");
			}
		}

		@Override
		public DtUsuarioExt seleccionarUsuarioEmail(String userEmail) throws UsuarioNoExisteException {
			try {
				return (DtUsuarioExt) Cnv.usuario(port.seleccionarUsuarioEmail(userEmail));
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException("no existe tal usuario");
			}
		}

		@Override
		public boolean verificarIdentidadEmail(String email,  String pass) {
			return port.verificarIdentidadEmail(email, pass);
		}

		@Override
		public boolean verificarIdentidadNickname(String nick,  String pass) {
			return port.verificarIdentidadNickname(nick, pass);
		}

		@Override
		public void favoritearActividad(String nick, String ins, String actDep)
				throws UsuarioNoExisteException, InstitucionException {
			try {
				port.favoritearActividad(nick, ins, actDep);
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException("no existe tal usuario");
			} catch (InstitucionException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void valorarProfesor(String nickSocio, String ins, String actDep, String cla, int valor)
				throws UsuarioNoExisteException, ClaseException, InstitucionException {
			try {
				port.valorarProfesor(nickSocio, ins, actDep, cla, valor);
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException("no existe tal usuario");
			} catch (ClaseException_Exception e) {
				throw new ClaseException("no existe tal clase");
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException("no existe tal institucion");
			}
		}

	}
	public class ActividadDeportivaController implements IActividadDeportivaController{

		webservices.WSActividadControllerService service = new webservices.WSActividadControllerService();
		webservices.WSActividadController port = service.getWSActividadControllerPort();
		
		@Override
		public Set<String> obtenerInstituciones() {
			Set<String> x = new HashSet<>();
			x.addAll(port.obtenerInstituciones().getItem());
			return x;
		}

		@Override
		public Boolean ingresarDatosActividadDep(String nombreInsti, DtActividadDeportiva datosAD)
				throws InstitucionException, ActividadDeportivaException {
			try {
				return port.ingresarDatosActividadDep(nombreInsti, Cnv.actividad(datosAD));
			} catch (ActividadDeportivaException_Exception e) {
				throw new ActividadDeportivaException("no existe tal actividad");
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException("no existe tal institucion");
			}
		}

		@Override
		public Set<String> obtenerActividades(String ins) throws InstitucionException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<String> obtenerDeltaInstituciones(String nombreCup, String ins) throws InstitucionException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DtClaseExt seleccionarClase(String ins, String actDep, String clase)
				throws InstitucionException, ActividadDeportivaException, ClaseException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<String> obtenerSocios() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DtActividadDeportivaExt getActDepExt(String ins, String actDep)
				throws InstitucionException, ActividadDeportivaException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int altaInstitucion(String nombre, String descripcion, String URL) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public DtInstitucion obtenerDatosInstitucion(String inst) throws InstitucionException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void aprobarActividad(String actividadDeportiva, TEstado estado) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void finalizarActividad(String actividadDeportiva) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Set<String> obtenerActDepIngresadas() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void ingresarCatergoria(DtCategoria datos) throws CategoriaException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Set<String> obtenerCategorias() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DtActividadDeportivaExt buscarActDep(String nombreActDep) throws ActividadDeportivaException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	public class DictadoClaseController implements IDictadoClaseController{

		@Override
		public Set<String> obtenerUsuarios() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<String> obtenerInstituciones() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String obtenerInstitucionActDep(String actDep) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<String> obtenerActividades(String ins) throws InstitucionException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<String> obtenerActividadesAprobadas(String ins) throws InstitucionException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<String> obtenerProfesores(String ins) throws InstitucionException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<String> obtenerClases(String ins, String actDep) throws InstitucionException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DtClaseExt seleccionarClase(String ins, String actDep, String clase)
				throws InstitucionException, ClaseException, ActividadDeportivaException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DtClaseExt buscarClase(String nombreClase) throws ClaseException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int ingresarDatosClase(String ins, String actDep, DtClase datos) throws InstitucionException,
				FechaInvalidaException, ClaseException, UsuarioNoExisteException, ActividadDeportivaException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void inscribirSocio(String ins, String actDep, String clase, String socio, TReg tipoRegistro,
				DtFecha fechaReg, String cuponera) throws ClaseException, FechaInvalidaException,
				NoExisteCuponeraException, InstitucionException, UsuarioNoExisteException, ActividadDeportivaException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Set<String> obtenerSocios() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<String> getCuponerasSocioClase(String nombreSocio, String nombreInst, String nombreAd,
				String nombreClase) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<String> getCuponerasDisponibles(String nombreSocio, String nombreInst, String nombreAd)
				throws UsuarioNoExisteException, InstitucionException, ActividadDeportivaException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<String> sortearPremios(String ins, String actDep, String clase)
				throws InstitucionException, ClaseException, ActividadDeportivaException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	public class CuponeraController implements ICuponeraController{

		@Override
		public int ingresarCuponera(String nombre, String descripcion, DtFecha inicio, DtFecha fin, int descuento,
				DtFecha alta, String imagen) throws CuponeraRepetidaException, FechaInvalidaException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Set<String> getNombreCuponeras() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void agregarActividadCuponera(String nombre, String instituto, String actividadDeportiva,
				int cantidadClases)
				throws InstitucionException, ActividadDeportivaException, CuponeraInmutableException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public DtCuponera seleccionarCuponera(String nombre) throws NoExisteCuponeraException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<String> getNombreCuponerasSinRecibos() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	private static LaFabricaWS instancia = null;

	public static LaFabricaWS getInstance(){
		if (instancia == null)
			instancia = new LaFabricaWS();
		return instancia;
	}
	
	public IActividadDeportivaController obtenerIActDeportivaController() {
		IActividadDeportivaController iact = new ActividadDeportivaController();
		return iact;
	}
	
	public IUsuarioController obtenerIUsuarioController() {
		IUsuarioController iusu = new UsuarioController();
		return iusu;
	}
	
    public IDictadoClaseController obtenerIDictadoClaseController(){
    	IDictadoClaseController idic = new DictadoClaseController();
    	return idic;
     }
    
    public ICuponeraController obtenerICuponeraController() {
    	ICuponeraController idep = new CuponeraController();
    	return idep;
     }

}
