package models;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import datatypes.DtActividadDeportiva;
import datatypes.DtActividadDeportivaExt;

import datatypes.DtClase;
import datatypes.DtClaseExt;
import datatypes.DtCuponera;
import datatypes.DtFecha;
import datatypes.DtInstitucion;

import datatypes.DtUsuario;
import datatypes.DtUsuarioExt;

import datatypes.TReg;
import excepciones.ActividadDeportivaException;

import excepciones.ClaseException;

import excepciones.CuponeraNoExisteException;

import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;
import excepciones.UsuarioNoExisteException;
import net.java.dev.jaxb.array.StringArray;
import servlets.ConfigListener;
import servlets.Login;
import webservices.ActividadDeportivaException_Exception;
import webservices.ClaseException_Exception;
import webservices.CuponeraNoExisteException_Exception;

import webservices.FechaInvalidaException_Exception;
import webservices.IOException_Exception;
import webservices.InstitucionException_Exception;
import webservices.LogEntryWS;
import webservices.LogEntryWSArray;
import webservices.NoExisteCuponeraException_Exception;
import webservices.TRegWS;
import webservices.UsuarioNoExisteException_Exception;
import tools.Cnv;

public class LaFabricaWS {
	//private String USUARIOCONTROLLERURL = (new Login()).getServletContext().getInitParameter("webServiceUsuarioController");

	public class UsuarioController implements IUsuarioController {
		webservices.WSUsuarioControllerService service;
		webservices.WSUsuarioController port;

		public UsuarioController() {
		    try {
		    	service = new webservices.WSUsuarioControllerService(new URL(ConfigListener.cfg.getProperty("usuarioControllerURL")));
		    } catch (MalformedURLException ex) {
		        throw new RuntimeException(ex);
		    }
		    port = service.getWSUsuarioControllerPort();
		}

		@Override
		public void comprarCuponera(String cuponera,  String socio,  DtFecha fechaCompra)
				throws UsuarioNoExisteException, CuponeraNoExisteException {
			try {
				port.comprarCuponera(cuponera, socio, Cnv.fecha(fechaCompra));
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException(e.getMessage());
			} catch (CuponeraNoExisteException_Exception e) {
				throw new CuponeraNoExisteException(e.getMessage());
			}
		}

		@Override
		public void dejarDeSeguir(String seguidor, String seguido) throws UsuarioNoExisteException {
			try {
				port.dejarDeSeguir(seguidor, seguido);
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException(e.getMessage());
			}
		}

		@Override
		public void editarDatosBasicos(String userNick, DtUsuario datoUser) throws UsuarioNoExisteException {
			try {
				port.editarDatosBasicos(userNick, Cnv.usuario(datoUser));
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException(e.getMessage());
			}
		}

		@Override
		public int ingresarDatosUsuario(DtUsuario datoUser) throws InstitucionException {
			try {
				return port.ingresarDatosUsuario(Cnv.usuario(datoUser));
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
		}

		@Override
		public Set<String> obtenerInstituciones() {
			Set<String> x = new HashSet<>();
			StringArray a = (StringArray) port.obtenerInstituciones().getContenido();
			x.addAll(a.getItem());
			return x;
		}


		@Override
		public Set<String> obtenerUsuarios() {
			Set<String> x = new HashSet<>();
			StringArray a = (StringArray) port.obtenerUsuarios().getContenido();
			x.addAll(a.getItem());
			return x;
		}

		@Override
		public void seguir(String seguidor, String seguido) throws UsuarioNoExisteException {
			try {
				port.seguir(seguidor, seguido);
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException(e.getMessage());
			}
		}

		@Override
		public DtUsuarioExt seleccionarUsuario(String userNick) throws UsuarioNoExisteException {
			try {
				return (DtUsuarioExt) Cnv.usuario(port.seleccionarUsuario(userNick));
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException(e.getMessage());
			}
		}

		@Override
		public DtUsuarioExt seleccionarUsuarioEmail(String userEmail) throws UsuarioNoExisteException {
			try {
				return (DtUsuarioExt) Cnv.usuario(port.seleccionarUsuarioEmail(userEmail));
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException(e.getMessage());
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
				throw new UsuarioNoExisteException(e.getMessage());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
		}

		@Override
		public void valorarProfesor(String nickSocio, String ins, String actDep, String cla, int valor)
				throws UsuarioNoExisteException, ClaseException, InstitucionException {
			try {
				port.valorarProfesor(nickSocio, ins, actDep, cla, valor);
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException(e.getMessage());
			} catch (ClaseException_Exception e) {
				throw new ClaseException(e.getMessage());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
		}

	}
	public class ActividadDeportivaController implements IActividadDeportivaController{

		webservices.WSActividadControllerService service = new webservices.WSActividadControllerService();
		webservices.WSActividadController port = service.getWSActividadControllerPort();
		
		public ActividadDeportivaController() {
		    try {
		    	service = new webservices.WSActividadControllerService(new URL(ConfigListener.cfg.getProperty("actividadControllerURL")));
		    } catch (MalformedURLException ex) {
		        throw new RuntimeException(ex);
		    }
		    port = service.getWSActividadControllerPort();
		}
		
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
				throw new ActividadDeportivaException(e.getMessage());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
		}

		@Override
		public Set<String> obtenerActividades(String ins) throws InstitucionException {
			Set<String> x = new HashSet<>();
			try {
				x.addAll(port.obtenerActividades(ins).getItem());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
			return x;
		}

		@Override
		public Set<String> obtenerDeltaInstituciones(String nombreCup, String ins) throws InstitucionException {
			Set<String> x = new HashSet<>();
			try {
				x.addAll(port.obtenerDeltaInstituciones(nombreCup, ins).getItem());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
			return x;
		}

		@Override
		public DtClaseExt seleccionarClase(String ins, String actDep, String clase)
				throws InstitucionException, ActividadDeportivaException, ClaseException {
			try {
				return (DtClaseExt) Cnv.clase(port.seleccionarClase(ins, actDep, clase));
			} catch (ActividadDeportivaException_Exception e) {
				throw new ActividadDeportivaException(e.getMessage());
			} catch (ClaseException_Exception e) {
				throw new ClaseException(e.getMessage());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
		}

		@Override
		public Set<String> obtenerSocios() {
			Set<String> x = new HashSet<>();
			x.addAll(port.obtenerSocios().getItem());
			return x;
		}

		@Override
		public DtActividadDeportivaExt getActDepExt(String ins, String actDep)
				throws InstitucionException, ActividadDeportivaException {
			try {
				return (DtActividadDeportivaExt) Cnv.actividad(port.getActDepExt(ins, actDep));
			} catch (ActividadDeportivaException_Exception e) {
				throw new ActividadDeportivaException(e.getMessage());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
		}

		@Override
		public DtInstitucion obtenerDatosInstitucion(String inst) throws InstitucionException {
			try {
				return Cnv.institucion(port.obtenerDatosInstitucion(inst));
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
		}

		@Override
		public void finalizarActividad(String actividadDeportiva) {
			port.finalizarActividad(actividadDeportiva);
		}

		@Override
		public Set<String> obtenerActDepIngresadas() {
			Set<String> x = new HashSet<>();
			x.addAll(port.obtenerActDepIngresadas().getItem());
			return x;
		}

		@Override
		public Set<String> obtenerCategorias() {
			Set<String> x = new HashSet<>();
			x.addAll(port.obtenerCategorias().getItem());
			return x;
		}

		@Override
		public DtActividadDeportivaExt buscarActDep(String nombreActDep) throws ActividadDeportivaException {
			try {
				return (DtActividadDeportivaExt) Cnv.actividad(port.buscarActDep(nombreActDep));
			} catch (ActividadDeportivaException_Exception e) {
				throw new ActividadDeportivaException(e.getMessage());
			}
		}
		
	}
	public class DictadoClaseController implements IDictadoClaseController{
		webservices.WSClaseControllerService service = new webservices.WSClaseControllerService();
		webservices.WSClaseController port = service.getWSClaseControllerPort();
		
		public DictadoClaseController() {
		    try {
		    	service = new webservices.WSClaseControllerService(new URL(ConfigListener.cfg.getProperty("claseControllerURL")));
		    } catch (MalformedURLException ex) {
		        throw new RuntimeException(ex);
		    }
		    port = service.getWSClaseControllerPort();
		}
		
		@Override
		public Set<String> obtenerUsuarios() {
			Set<String> x = new HashSet<>();
			x.addAll(port.obtenerUsuarios().getItem());
			return x;
		}

		@Override
		public Set<String> obtenerInstituciones() {
			Set<String> x = new HashSet<>();
			x.addAll(port.obtenerInstituciones().getItem());
			return x;
		}

		@Override
		public String obtenerInstitucionActDep(String actDep) {
			return port.obtenerInstitucionActDep(actDep);
		}

		@Override
		public Set<String> obtenerActividades(String ins) throws InstitucionException {
			Set<String> x = new HashSet<>();
			try {
				x.addAll(port.obtenerActividades(ins).getItem());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
			return x;
		}

		@Override
		public Set<String> obtenerActividadesAprobadas(String ins) throws InstitucionException {
			Set<String> x = new HashSet<>();
			try {
				x.addAll(port.obtenerActividadesAprobadas(ins).getItem());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
			return x;
		}

		@Override
		public Set<String> obtenerProfesores(String ins) throws InstitucionException {
			Set<String> x = new HashSet<>();
			try {
				x.addAll(port.obtenerProfesores(ins).getItem());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
			return x;
		}

		@Override
		public Set<String> obtenerClases(String ins, String actDep) throws InstitucionException {
			Set<String> x = new HashSet<>();
			try {
				x.addAll(port.obtenerClases(ins,actDep).getItem());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
			return x;
		}

		@Override
		public DtClaseExt seleccionarClase(String ins, String actDep, String clase)
				throws InstitucionException, ClaseException, ActividadDeportivaException {
			try {
				return (DtClaseExt) Cnv.clase(port.seleccionarClase(ins, actDep, clase));
			} catch (ClaseException_Exception e) {
				throw new ClaseException(e.getMessage());
			} catch (ActividadDeportivaException_Exception e) {
				throw new ActividadDeportivaException(e.getMessage());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
		}

		@Override
		public DtClaseExt buscarClase(String nombreClase) throws ClaseException {
			try {
				return (DtClaseExt) Cnv.clase(port.buscarClase(nombreClase));
			} catch (ClaseException_Exception e) {
				throw new ClaseException(e.getMessage());
			}
		}

		@Override
		public int ingresarDatosClase(String ins, String actDep, DtClase datos) throws InstitucionException,
				FechaInvalidaException, ClaseException, UsuarioNoExisteException, ActividadDeportivaException {
			try {
				return port.ingresarDatosClase(ins, actDep, Cnv.clase(datos));
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException(e.getMessage());
			} catch (FechaInvalidaException_Exception e) {
				throw new FechaInvalidaException("bad date");
			} catch (ClaseException_Exception e) {
				throw new ClaseException(e.getMessage());
			} catch (ActividadDeportivaException_Exception e) {
				throw new ActividadDeportivaException(e.getMessage());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
		}

		@Override
		public void inscribirSocio(String ins, String actDep, String clase, String socio, TReg tipoRegistro,
				DtFecha fechaReg, String cuponera) throws ClaseException, FechaInvalidaException,
				NoExisteCuponeraException, InstitucionException, UsuarioNoExisteException, ActividadDeportivaException {
			try {
				cuponera = ((cuponera==null) ? "" : cuponera);
				port.inscribirSocio(ins, actDep, clase, socio, TRegWS.values()[tipoRegistro.ordinal()], Cnv.fecha(fechaReg), cuponera);
			} catch (FechaInvalidaException_Exception e) {
				throw new FechaInvalidaException("bad date");
			} catch (ClaseException_Exception e) {
				throw new ClaseException(e.getMessage());
			} catch (ActividadDeportivaException_Exception e) {
				throw new ActividadDeportivaException(e.getMessage());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			} catch (NoExisteCuponeraException_Exception e) {
				throw new NoExisteCuponeraException(e.getMessage());
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException(e.getMessage());
			}
			
		}

		@Override
		public Set<String> obtenerSocios() {
			Set<String> x = new HashSet<>();
			x.addAll(port.obtenerSocios().getItem());
			return x;
		}

		@Override
		public Set<String> getCuponerasSocioClase(String nombreSocio, String nombreInst, String nombreAd,
				String nombreClase) {
			Set<String> x = new HashSet<>();
			x.addAll(port.getCuponerasSocioClase(nombreSocio,nombreInst,nombreAd,nombreClase).getItem());
			return x;
		}

		@Override
		public Set<String> getCuponerasDisponibles(String nombreSocio, String nombreInst, String nombreAd)
				throws UsuarioNoExisteException, InstitucionException, ActividadDeportivaException {
			Set<String> x = new HashSet<>();
			try {
				x.addAll(port.getCuponerasDisponibles(nombreSocio, nombreInst, nombreAd).getItem());
			} catch (UsuarioNoExisteException_Exception e) {
				throw new UsuarioNoExisteException(e.getMessage());
			} catch (ActividadDeportivaException_Exception e) {
				throw new ActividadDeportivaException(e.getMessage());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
			return x;
		}

		@Override
		public Set<String> sortearPremios(String ins, String actDep, String clase)
				throws InstitucionException, ClaseException, ActividadDeportivaException {
			Set<String> x = new HashSet<>();
			try {
				x.addAll(port.sortearPremios(ins,actDep,clase).getItem());
			} catch (ClaseException_Exception e) {
				throw new ClaseException(e.getMessage());
			} catch (ActividadDeportivaException_Exception e) {
				throw new ActividadDeportivaException(e.getMessage());
			} catch (InstitucionException_Exception e) {
				throw new InstitucionException(e.getMessage());
			}
			return x;
		}
		
	}
	public class CuponeraController implements ICuponeraController{
		webservices.WSCuponeraControllerService service = new webservices.WSCuponeraControllerService();
		webservices.WSCuponeraController port = service.getWSCuponeraControllerPort();

		public CuponeraController() {
		    try {
		    	service = new webservices.WSCuponeraControllerService(new URL(ConfigListener.cfg.getProperty("cuponeraControllerURL")));
		    } catch (MalformedURLException ex) {
		        throw new RuntimeException(ex);
		    }
		    port = service.getWSCuponeraControllerPort();
		}
		
		@Override
		public Set<String> getNombreCuponeras() {
			Set<String> x = new HashSet<>();
			x.addAll(port.getNombreCuponeras().getItem());
			return x;
		}

		@Override
		public DtCuponera seleccionarCuponera(String nombre) throws NoExisteCuponeraException {
			try {
				return Cnv.cuponera(port.seleccionarCuponera(nombre));
			} catch (NoExisteCuponeraException_Exception e) {
				throw new NoExisteCuponeraException(e.getMessage());
			}
		}

		@Override
		public Set<String> getNombreCuponerasSinRecibos() {
			Set<String> x = new HashSet<>();
			x.addAll(port.getNombreCuponerasSinRecibos().getItem());
			return x;
		}
		
	}
	public static class ContentController implements IContentController{
		static LogEntryWSArray logpool = new LogEntryWSArray();
		webservices.WSContentControllerService service = new webservices.WSContentControllerService();
		webservices.WSContentController port = service.getWSContentControllerPort();
		
		public ContentController() {
		    try {
		    	service = new webservices.WSContentControllerService(new URL(ConfigListener.cfg.getProperty("contentControllerURL")));
		    } catch (MalformedURLException ex) {
		        throw new RuntimeException(ex);
		    }
		    port = service.getWSContentControllerPort();
		}
		
		
		@Override
		public byte[] get(String type, String id) throws IOException{
			try {
				return port.get(type, id);
			} catch (IOException_Exception e) {
				throw new IOException();
			}
		}

		@Override
		public void post(String type, String id, byte[] content) throws IOException{
			try {
				port.post(type, id, content);
			} catch (IOException_Exception e) {
				throw new IOException();
			}
		}

		@Override
		public void sendReport(LogEntryWS entry) {
			if(logpool==null)
				logpool = new LogEntryWSArray();
			logpool.getItem().add(entry);
			try {
				port.sendReports(logpool);
			} catch(Exception e) {
				sendReport(entry);
				return;
			}
			if(logpool.getItem().size()>=Integer.parseInt(ConfigListener.cfg.getProperty("logthreshold")))
				logpool = new LogEntryWSArray();
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

    public IContentController obtenerIContentController() {
    	IContentController icon = new ContentController();
    	return icon;
    }
}
