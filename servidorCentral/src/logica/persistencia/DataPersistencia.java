package logica.persistencia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import datatypes.DtActividadDeportivaExt;
import datatypes.DtClaseExt;
import datatypes.DtFecha;
import datatypes.DtProfesorExt;
import datatypes.DtReciboClase;
import datatypes.DtSocioExt;
import excepciones.ClaseException;
import excepciones.UsuarioNoExisteException;
import logica.LaFabrica;
import logica.persistencia.Datatypes.TipoUsuario;
import logica.persistencia.Entidades.*;

public class DataPersistencia {
	@PersistenceUnit(name="ActividadDB")
	private EntityManagerFactory emFabrica = Persistence.createEntityManagerFactory("ActividadDB");

	private static DataPersistencia instancia = null;
	
	private DataPersistencia() { }

	public static DataPersistencia getInstance() {
		if (instancia == null)
			instancia = new DataPersistencia();
		return instancia;
	}
	
	public void persistir(DtActividadDeportivaExt dtAct) {
		// Inicia la transaccion
		EntityManager em = emFabrica.createEntityManager();
		try {
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
		    TypedQuery<Profesores> selectProf = em.createQuery("SELECT a FROM Profesores a WHERE a.nickname = :nickname",
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
			    		TypedQuery<Socios> selectSocio = em.createQuery("SELECT a FROM Socios a WHERE a.nickname = :nickname",
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
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public Set<String> obtenerActividades() {
		EntityManager em = emFabrica.createEntityManager();
		Set<String> nombreActividades = new HashSet<>();
		try {
			em.getTransaction().begin();
			TypedQuery<ActividadesDeportivas> select = em.createQuery("SELECT a FROM ActividadesDeportivas a ORDER BY a.nombre DESC",
					ActividadesDeportivas.class);
    	    System.out.print("\n\n" + select.getResultList().size() + "\n\n");
			for (ActividadesDeportivas actDep : select.getResultList()) {
				System.out.print(actDep.toString());
				nombreActividades.add(actDep.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return nombreActividades;
	}
	
	public Set<String> obtenerClases(String nombreActividad) {
		EntityManager em = emFabrica.createEntityManager();
		Set<String> nombreClases = new HashSet<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Clases> select = em.createQuery("SELECT c FROM Clases c INNER JOIN ActividadesDeportivas ad" +
					" WHERE (ad.nombre = :nombre) ORDER BY c.nombre DESC",	Clases.class);
    	    select.setParameter("nombre", nombreActividad);
    	    System.out.print("\n\n" + select.getResultList().size() + "\n\n");
			for (Clases claseDB : select.getResultList()) {
				System.out.print(claseDB.toString());
				nombreClases.add(claseDB.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return nombreClases;
	}
	
	public Set<String> obtenerActividades(String nickProfesor){
		EntityManager em = emFabrica.createEntityManager();
		Set<String> nombresAD = new HashSet<>();
		try {
			em.getTransaction().begin();
			TypedQuery<ActividadesDeportivas> select = em.createQuery("SELECT ad FROM ActividadesDeportivas ad INNER JOIN Profesores p "
					+ "WHERE p.nickname=:nombre",ActividadesDeportivas.class);
			select.setParameter("nombre", nickProfesor);
    	    System.out.print("\n\n" + select.getResultList().size() + "\n\n");
			for (ActividadesDeportivas adDB : select.getResultList()) {
				System.out.print(adDB.toString());
				if(adDB.getProfesor().getNickname().equals(nickProfesor))
					nombresAD.add(adDB.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return nombresAD;
	}
	
	public void nuketownDetonator() {
		EntityManager em = emFabrica.createEntityManager();
		em.getTransaction().begin();
		try {
			//CriteriaBuilder builder = em.getCriteriaBuilder();
			//CriteriaDelete<ActividadesDeportivas> queryAct = builder.createCriteriaDelete(ActividadesDeportivas.class);
			//queryAct.from(ActividadesDeportivas.class);
			//em.createQuery(queryAct).executeUpdate();
			//CriteriaDelete<Usuarios> querySos = builder.createCriteriaDelete(Usuarios.class);
			//querySos.from(Usuarios.class);
			//em.createQuery(querySos).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public Map<String, Set<String>> obtenerActividadxClasesSocio(String nombreSocio) {
		EntityManager em = emFabrica.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Clases> select = em.createQuery("SELECT cla FROM Clases cla INNER JOIN Registros reg INNER JOIN Socios s WHERE s.nombre=:nombre ",Clases.class);
		select.setParameter("nombre", nombreSocio);
		Map<String,String> clasexact = new HashMap<>();
		for(Clases x: select.getResultList()) {
			clasexact.put(x.getNombre(), x.getActividad().getNombre());
		}
		for(Entry<String, String> x : clasexact.entrySet()) {
			System.out.println("Result: "+x.getKey()+" :"+x.getValue());
		}
		Map<String, Set<String>> res = new HashMap<>();
		for(Entry<String, String> x: clasexact.entrySet()) {
			if(!res.containsKey(x.getValue()))
				res.put(x.getValue(), new HashSet<String>());
			res.get(x.getValue()).add(x.getKey());
		}
		return res;
	}
}