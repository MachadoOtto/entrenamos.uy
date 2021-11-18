package logica.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import datatypes.DtActividadDeportivaExt;
import datatypes.DtFecha;
import datatypes.DtUsuarioExt;
import excepciones.ActividadDeportivaException;
import excepciones.UsuarioNoExisteException;
import logica.ActividadDeportiva;
import logica.Clase;
import logica.ReciboClase;
import logica.Socio;
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
	
	public void persistir(ActividadDeportiva act) {
		EntityManager em = emFabrica.createEntityManager();
		try {
			
			//GetActDepCreator
			em.getTransaction().begin();
		    TypedQuery<Profesores> creatorQuery = em.createQuery("SELECT a FROM Profesores a WHERE a.nickname = :nickname",Profesores.class);
		    Profesores creador=null;
		    creatorQuery.setParameter("nickname",act.getCreador().getNickname());
		    List<Profesores> creatorQueryRes = creatorQuery.getResultList();
		    em.getTransaction().commit();
		    if (creatorQueryRes.isEmpty()) {
		    	creador = new Profesores();
		    	creador.setNickname(act.getCreador().getNickname());
		    	creador.setEmail(act.getCreador().getCorreo());
		    	creador.setNombre(act.getCreador().getNombre());
		    	creador.setApellido(act.getCreador().getApellido());
		    	creador.setFechaNacimiento(act.getCreador().getFecha().toCalendar());
		    	creador.setTipoUsuario(TipoUsuario.Profesor);
		    	em.getTransaction().begin();
		    	em.persist(creador);
		    	em.getTransaction().commit();
		    } else
		    	creador = creatorQueryRes.get(0);
		    
		    //CreateActivity
		    ActividadesDeportivas ap = new ActividadesDeportivas();
		    ap.setNombre(act.getNombre());
		    ap.setDescripcion(act.getDescripcion());
		    ap.setDuracion(act.getDuracionMinutos());
		    ap.setCosto(act.getCosto());
		    ap.setFechaAlta(act.getFechaRegistro().toCalendar());
		    ap.setFechaFinalizacion((new DtFecha()).toCalendar());
		    ap.setProfesor(creador);

			
			//Create clases
			Collection<Clases> collectionClase = new ArrayList<>();
			for(Entry<String, Clase> cl: act.getClases().entrySet()) {
				Clase y = cl.getValue();
				Clases x = new Clases();
				x.setNombre(y.getNombre());
				x.setFechaInicio(y.getFechaClase().toCalendar());
				x.setFechaAlta(y.getFechaRegistro().toCalendar());
				x.setHoraInicio(y.getFechaClase().toCalendar());
				x.setSociosMaximos(y.getMaxSocios());
				x.setSociosMinimos(y.getMinSocios());
				x.setUrl(y.getURL());
				x.setActividad(ap);
				collectionClase.add(x);
				
				//Ver cada recibo
				Collection<Registros> registros = new ArrayList<>();
				for(ReciboClase rc: y.getRecibo()) {
					
					//Create socios inscriptos
					Socio s = rc.getSocio();
					em.getTransaction().begin();
					TypedQuery<Socios> leSocios = em.createQuery("SELECT s FROM Socios s WHERE s.nickname=:nick",Socios.class);
					leSocios.setParameter("nick", s.getNickname());
					List<Socios> leSociosRes = leSocios.getResultList();
					em.getTransaction().commit();
					Socios sp = null;
					if(leSociosRes.isEmpty()) {
						sp = new Socios();
						sp.setNickname(s.getNickname());
						sp.setNombre(s.getNombre());
						sp.setApellido(s.getApellido());
						sp.setEmail(s.getCorreo());
						sp.setFechaNacimiento(s.getFecha().toCalendar());
						sp.setTipoUsuario(TipoUsuario.Socio);
						em.getTransaction().begin();
						em.persist(sp);
						em.getTransaction().commit();
					} else
						sp = leSociosRes.get(0);
					//Create registros
					Registros r = new Registros();
					r.setFechaRegistro(rc.getFechaInscripcion().toCalendar());
					r.setCosto(rc.getCosto());
					r.setSocio(sp);
					r.setClase(x);
					registros.add(r);
					
				}
				x.setRegistros(registros);
			}
			
			ap.setClases(collectionClase);
			em.getTransaction().begin();
			em.persist(ap);
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
			for (ActividadesDeportivas actDep : select.getResultList()) {
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
			for (Clases claseDB : select.getResultList()) {
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
			for (ActividadesDeportivas adDB : select.getResultList()) {
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
		    Query q1 = em.createQuery("DELETE FROM Registros");
		    Query q2 = em.createQuery("DELETE FROM Clases");
		    Query q3 = em.createQuery("DELETE FROM ActividadesDeportivas");
		    Query q4 = em.createQuery("DELETE FROM Profesores");
		    Query q5 = em.createQuery("DELETE FROM Socios");
		    q1.executeUpdate();
		    q2.executeUpdate();
		    q3.executeUpdate();
		    q4.executeUpdate();
		    q5.executeUpdate();
		    em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public Map<String, Set<String>> obtenerActividadxClasesSocio(String nombreSocio) {
		EntityManager em = emFabrica.createEntityManager();
		Map<String, Set<String>> res = new HashMap<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Clases> select = em.createQuery("SELECT cla FROM Clases cla INNER JOIN Registros reg INNER JOIN Socios s WHERE s.nickname=:nombre ",Clases.class);
			select.setParameter("nombre", nombreSocio);
			Map<String,String> clasexact = new HashMap<>();
			for(Clases x: select.getResultList()) {
				clasexact.put(x.getNombre(), x.getActividad().getNombre());
			}			
			for(Entry<String, String> x: clasexact.entrySet()) {
				if(!res.containsKey(x.getValue()))
					res.put(x.getValue(), new HashSet<String>());
				res.get(x.getValue()).add(x.getKey());
			}
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return res;
	}

	public DtActividadDeportivaExt getActividad(String nombreActDep) throws ActividadDeportivaException {
		EntityManager em = emFabrica.createEntityManager();
		try {
			em.getTransaction().begin();
			TypedQuery<ActividadesDeportivas> select = em.createQuery("SELECT act FROM ActividadesDeportivas act WHERE act.nombre=:nombre",ActividadesDeportivas.class);
			select.setParameter("nombre", nombreActDep);
			ActividadesDeportivas act = select.getSingleResult();
			return act.toDtActividadDeportivaExt();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback(); 
		} finally {
			em.close();
		}
		throw new ActividadDeportivaException("La actividad deportiva "+nombreActDep+" no se encuentra presente en el sistema.");
	}
	
	public DtUsuarioExt getUsuario(String nombreSocio) throws UsuarioNoExisteException {
		EntityManager em = emFabrica.createEntityManager();
		try {
			em.getTransaction().begin();
			TypedQuery<Socios> select = em.createQuery("SELECT s FROM Socios s WHERE s.nickname=:nombre",Socios.class);
			select.setParameter("nombre", nombreSocio);
			if(select.getResultList().size()>0) {
				Socios s = select.getSingleResult();
				em.getTransaction().commit();
				System.out.println(s.toString());
				return s.toDtUsuarioExt();
			}
			else{
				TypedQuery<Profesores> select2 = em.createQuery("SELECT s FROM Profesores s WHERE s.nickname=:nombre",Profesores.class);
				select2.setParameter("nombre", nombreSocio);
				if(select2.getResultList().size()>0) {
					Profesores s = select2.getSingleResult();
					em.getTransaction().commit();
					System.out.println(s.toString());
					return s.toDtUsuarioExt();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback(); 
		} finally {
			em.close();
		}
		throw new UsuarioNoExisteException("El usuario "+nombreSocio+" no se encuentra presente en el sistema.");
	}
}