package logica.persistencia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import datatypes.DtActividadDeportiva;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtClaseExt;
import datatypes.DtFecha;
import datatypes.DtProfesorExt;
import datatypes.DtReciboClase;
import datatypes.DtSocioExt;
import datatypes.TReg;
import excepciones.ClaseException;
import excepciones.UsuarioNoExisteException;
import logica.LaFabrica;
import logica.persistencia.Datatypes.TipoUsuario;
import logica.persistencia.Entidades.*;

public class DataPersistencia {

	private static DataPersistencia instancia = null;
	
	private EntityManager em;
	
	private DataPersistencia() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ActividadDB");
		this.em = emf.createEntityManager();
	}

	public static DataPersistencia getInstance(){
		if (instancia == null)
			instancia = new DataPersistencia();
		return instancia;
	}
	
	public void persistir(DtActividadDeportivaExt dtAct) {
		// Inicia la transaccion
		em.getTransaction().begin();
		// Creacion de Entidad ActividadesDeportivas.
		ActividadesDeportivas actPersist = new ActividadesDeportivas();
		actPersist.setNombre(dtAct.getNombre());
		actPersist.setDescripcion(dtAct.getDescripcion());
		actPersist.setDuracion(dtAct.getDuracionMinutos());
	    actPersist.setCosto(dtAct.getCosto());
	    Calendar fechaAlta = new GregorianCalendar();
	    fechaAlta.set(dtAct.getFechaRegistro().getAnio(), dtAct.getFechaRegistro().getMes(), dtAct.getFechaRegistro().getDia());
	    actPersist.setFechaAlta(fechaAlta);
	    Calendar fechaFinalizacion = new GregorianCalendar();
	    DtFecha fechaActual = new DtFecha();
	    fechaFinalizacion.set(fechaActual.getAnio(), fechaActual.getMes(), fechaActual.getDia());
	    actPersist.setFechaFinalizacion(fechaFinalizacion);
	    // Creacion o busqueda de Entidad Profesor.
	    Profesores profViktor;
	    TypedQuery<Profesores> selectProf = em.createQuery("SELECT a FROM Profesores a WHERE a.NICKNAME = :nickname",
				Profesores.class);
	    selectProf.setParameter("nickname", dtAct.getCreador());
	    if (!selectProf.getResultList().isEmpty()) {
	    	// Solo hay uno en la lista.
	    	profViktor = (Profesores) selectProf.getSingleResult();
	    } else {
	    	// No hay profesores con ese nombre, debemos crear uno nuevo.
	    	// Buscamos el profesor a la logica.
	    	DtProfesorExt dataProfe = new DtProfesorExt(null, null, null, null, null, fechaActual, null, null, null, null, null, null, null, null, null, 0);
	    	try {
	    		dataProfe = (DtProfesorExt) LaFabrica.getInstance().obtenerIUsuarioController()
	    				.seleccionarUsuario(dtAct.getCreador());
	    	} catch (UsuarioNoExisteException ignore) { }
	    	profViktor = new Profesores();
	    	profViktor.setNickname(dataProfe.getNickname());
	        profViktor.setEmail(dataProfe.getEmail());
	        profViktor.setNombre(dataProfe.getNombre());
	        profViktor.setApellido(dataProfe.getApellido());
	        Calendar felizCumple = new GregorianCalendar();
	        felizCumple.set(dataProfe.getFechaNacimiento().getAnio(), dataProfe.getFechaNacimiento().getMes(), 
	        		dataProfe.getFechaNacimiento().getDia());
	        profViktor.setFechaNacimiento(felizCumple);
	        profViktor.setTipoUsuario(TipoUsuario.Profesor);
	    }
	    actPersist.setProfesor(profViktor);
	    // Creacion de las clases
	    Collection<Clases> collectionClase = new ArrayList<>();
	    Map<String, Socios> sociosAniadidos = new HashMap<>();
	    for (String clAct : dtAct.getClases()) {
	    	// Creacion o busqueda de Entidad Clases.
		    Clases salon307 = new Clases();
		    DtClaseExt datosClase = new DtClaseExt(clAct, clAct, clAct, 0, 0, clAct, fechaActual, fechaActual, null, null);
		    try {
		    	datosClase = LaFabrica.getInstance().obtenerIDictadoClaseController().buscarClase(clAct);
		    } catch (ClaseException ignore) { }
		    salon307.setNombre(datosClase.getNombre());
		    Calendar fechaClaseAlta = new GregorianCalendar();
		    fechaClaseAlta.set(datosClase.getFechaRegistro().getAnio(), datosClase.getFechaRegistro().getMes(), 
		    		datosClase.getFechaRegistro().getDia());
		    salon307.setFechaAlta(fechaClaseAlta);
		    Calendar fechaClaseInicio = new GregorianCalendar();
		    fechaClaseInicio.set(datosClase.getFechaClase().getAnio(), datosClase.getFechaClase().getMes(), 
		    		datosClase.getFechaClase().getDia(), datosClase.getFechaClase().getHoras(), datosClase.getFechaClase().getMinutos());
		    salon307.setFechaInicio(fechaClaseInicio);
		    salon307.setHoraInicio(fechaClaseInicio);
		    salon307.setSociosMinimos(datosClase.getMinSocios());
		    salon307.setSociosMaximos(datosClase.getMaxSocios());
		    salon307.setUrl(datosClase.getURL());
		    // Creacion Registros
		    Collection<Registros> registros = new ArrayList<>();
		    for (String nickSocio : datosClase.getNickAlumnos()) {
		    	Registros regBedelias = new Registros();
		    	if (sociosAniadidos.containsKey(nickSocio)) {
		    		regBedelias.setSocio(sociosAniadidos.get(nickSocio));
		    	} else {
		    		TypedQuery<Socios> selectSocio = em.createQuery("SELECT a FROM Socios a WHERE a.NICKNAME = :nickname",
		    				Socios.class);
		    	    selectSocio.setParameter("nickname", nickSocio);
		    	    if (!selectSocio.getResultList().isEmpty()) {
		    	    	// Solo hay uno en la lista.
		    	    	regBedelias.setSocio((Socios) selectSocio.getSingleResult());
		    	    } else {
		    	    	// Se crea por primera vez el Socio.
		    	    	Socios clubPenguin = new Socios();
		    	    	DtSocioExt dataPinguino = new DtSocioExt(clAct, clAct, clAct, clAct, clAct, fechaActual, null, null, null, null, null, null, null, null);
		    	    	try {
		    	    		dataPinguino = (DtSocioExt) LaFabrica.getInstance().obtenerIUsuarioController().seleccionarUsuario(nickSocio);
		    	    	} catch (UsuarioNoExisteException ignore) { }
		    	    	clubPenguin.setNickname(dataPinguino.getNickname());
		    	    	clubPenguin.setEmail(dataPinguino.getEmail());
		    	    	clubPenguin.setNombre(dataPinguino.getNombre());
		    	    	clubPenguin.setApellido(dataPinguino.getApellido());
		    	    	Calendar felizCumpleSocio = new GregorianCalendar();
		    	    	felizCumpleSocio.set(dataPinguino.getFechaNacimiento().getAnio(), dataPinguino.getFechaNacimiento().getMes(),
		    	    			dataPinguino.getFechaNacimiento().getDia());
		    	    	clubPenguin.setFechaNacimiento(felizCumpleSocio);
		    	    	clubPenguin.setTipoUsuario(TipoUsuario.Socio);
		    	    	sociosAniadidos.put(nickSocio, clubPenguin);
		    	    	regBedelias.setSocio(clubPenguin);
		    	    }
		    	}
		    	Set<DtReciboClase> recibos = null;
		    	try {
		    		recibos = LaFabrica.getInstance().obtenerIDictadoClaseController().bringTheRegistersPls(clAct);
		    	} catch (ClaseException ex) {
		    		recibos = new HashSet<>();
		    	}
		    	for (DtReciboClase receipt : recibos) {
		    		Calendar fechaRecibo = new GregorianCalendar();
		    		fechaRecibo.set(receipt.getFechaInscripcion().getAnio(), receipt.getFechaInscripcion().getMes(),
		    				receipt.getFechaInscripcion().getDia());
		    		regBedelias.setFechaRegistro(fechaRecibo);
		    		regBedelias.setCosto(receipt.getCosto());
		    		regBedelias.setClase(salon307);
		    	}
		    	registros.add(regBedelias);
		    }
		    salon307.setRegistros(registros);
		    salon307.setActividad(actPersist);
		    collectionClase.add(salon307);
	    }
	    actPersist.setClases(collectionClase);
	    em.persist(actPersist);
	    em.flush();
	    em.getTransaction().commit();
	}
}