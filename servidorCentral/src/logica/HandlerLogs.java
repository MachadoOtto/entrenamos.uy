/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */
package logica;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import datatypes.DtFecha;
import datatypesWS.DtFechaWS;
import datatypesWS.LogEntryWS;
import main.Main;
import logica.persistencia.LogEntry;

public class HandlerLogs implements ILogger {
	@PersistenceUnit(name="LoggerDB")
	EntityManagerFactory f = Persistence.createEntityManagerFactory("LoggerDB");
	private static HandlerLogs instance = null;
	
	private HandlerLogs() { }
	
	public static ILogger getInstance() {
		if (instance == null)
			instance = new HandlerLogs(); 
		return instance;
	}
	
	public void addLogs(LogEntryWS [] entries) {
		coherence();
		EntityManager manager = f.createEntityManager();
		try {
			manager.getTransaction().begin();
			for(LogEntryWS w: entries) {
				LogEntry ll = new LogEntry();
				ll.setBrowser(w.getBrowser());
				ll.setOs(w.getSo());
				ll.setIp(w.getIp());
				ll.setUrl(w.getUrl());
				GregorianCalendar today = new GregorianCalendar(w.getDate().getAnio(),w.getDate().getMes()-1,
						w.getDate().getDia(),w.getDate().getHoras(),w.getDate().getMinutos(),w.getDate().getSegundos());
				ll.setDate(today);
				Calendar exp = new GregorianCalendar();
				exp.setTimeInMillis(today.getTimeInMillis()+2592000000L);
				ll.setExpires(exp);
				manager.persist(ll);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	public LogEntryWS [] getLogs() {
		coherence();
		EntityManager manager = f.createEntityManager();
		try {
			manager.getTransaction().begin();
			TypedQuery<LogEntry> q = manager.createQuery("SELECT x FROM LogEntry x ORDER BY x.date DESC",LogEntry.class);
			LogEntryWS [] res = new LogEntryWS[q.getResultList().size()];
			int i=0;
			for(LogEntry ent :  q.getResultList()) {
				LogEntryWS rq = new LogEntryWS();
				rq.setBrowser(ent.getBrowser());
				rq.setDate(new DtFechaWS(new DtFecha(ent.getDate().get(Calendar.YEAR),ent.getDate().get(Calendar.MONTH)+1,
						ent.getDate().get(Calendar.DAY_OF_MONTH),ent.getDate().get(Calendar.HOUR_OF_DAY),
						ent.getDate().get(Calendar.MINUTE),ent.getDate().get(Calendar.SECOND))));
				rq.setIp(ent.getIp());
				rq.setSo(ent.getOs());
				rq.setUrl(ent.getUrl());
				res[(i==q.getResultList().size()) ? i:i++] = rq;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return new LogEntryWS[0];
	}

	private void coherence(){
		EntityManager manager = f.createEntityManager();
		try {
			manager.getTransaction().begin();
			TypedQuery<LogEntry> q = manager.createQuery("SELECT x FROM LogEntry x ORDER BY x.date ASC",LogEntry.class);
			List<LogEntry> logs = q.getResultList();
			int todel = logs.size()-Integer.parseInt(Main.config.getProperty("logsize"));
			if(todel>0) {
				for(int i=0; i<todel;i++) {
					manager.remove(logs.get(i));
				}
			}
			manager.getTransaction().commit();
			manager.getTransaction().begin();
			TypedQuery<LogEntry> q2 = manager.createQuery("SELECT l FROM LogEntry l WHERE l.expires < CURRENT_DATE",LogEntry.class);
			for(LogEntry l: q2.getResultList()) {
				manager.remove(l);
			}
			manager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}
}	
