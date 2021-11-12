/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */
package logica;

import java.sql.Time;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

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
				ll.setDate(new GregorianCalendar());
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
			/*Expression<Time> l2 = r2.get("date");
			ParameterExpression<String> dayUnit = cb.parameter(String.class,"dayUnit");
			ParameterExpression<String> d30 = cb.parameter(String.class,"30");
			Expression<Time> l21m = cb.function("DATEADD", Time.class, dayUnit,d30,l2);
			q2c.select(r2).where(cb.lessThan(l21m, cb.currentTime()));
			q2c.orderBy(cb.desc(r2.get("date")));
			TypedQuery<LogEntry> q = manager.createQuery("SELECT l FROM LogEntry l ORDER BY date DESC;",LogEntry.class);
			LogEntryWS [] res = new LogEntryWS[manager.createQuery(q2c).getResultList().size()];
			int i=0;
			for(LogEntry ent :  manager.createQuery(q2c).getResultList()) {
				LogEntryWS rq = new LogEntryWS();
				rq.setBrowser(ent.getBrowser());
				rq.setDate(new DtFechaWS(new DtFecha(ent.getDate().get(Calendar.YEAR),ent.getDate().get(Calendar.MONTH),
						ent.getDate().get(Calendar.DAY_OF_MONTH),ent.getDate().get(Calendar.HOUR_OF_DAY),
						ent.getDate().get(Calendar.MINUTE),ent.getDate().get(Calendar.SECOND))));
				rq.setIp(ent.getIp());
				rq.setSo(ent.getOs());
				rq.setUrl(ent.getUrl());
				res[i++] = rq;
			}
			return res;*/
			LogEntryWS [] res = new LogEntryWS[q.getResultList().size()];
			int i=0;
			for(LogEntry ent :  q.getResultList()) {
				LogEntryWS rq = new LogEntryWS();
				rq.setBrowser(ent.getBrowser());
				rq.setDate(new DtFechaWS(new DtFecha(ent.getDate().get(Calendar.YEAR),ent.getDate().get(Calendar.MONTH),
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
		/*EntityManager manager = f.createEntityManager();
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		try {
			manager.getTransaction().begin();
			CriteriaQuery<LogEntry> q = cb.createQuery(LogEntry.class);
			Root<LogEntry> r = q.from(LogEntry.class);
			q.select(r);
			q.orderBy(cb.desc(r.get("date")));
			List<LogEntry> logs = manager.createQuery(q).getResultList();
			if (logs.size() == 10000) {
				LogEntry rip = logs.get(9999);
				manager.remove(rip);
				manager.getTransaction().commit();

			}
			CriteriaQuery<LogEntry> q2c = cb.createQuery(LogEntry.class);
			Root<LogEntry> r2 = q2c.from(LogEntry.class);
			q2c.select(r2).where(cb.lessThan(
					r2.get("date"),
					cb.currentDate()
			));
			List<LogEntry> logsviejos = manager.createQuery(q2c).getResultList();
			Expression<Time> l2 = r2.get("date");
			ParameterExpression<String> dayUnit = cb.parameter(String.class,"dayUnit");
			ParameterExpression<String> d30 = cb.parameter(String.class,"30");
			Expression<Time> l21m = cb.function("DATEADD", Time.class, dayUnit,d30,l2);
			
			q2c.select(r2).where(cb.lessThan(l21m, cb.currentTime()));
			q2c.orderBy(cb.desc(r2.get("date")));
			//TypedQuery<LogEntry> q2 = manager.createQuery("SELECT l FROM LogEntry l WHERE DATEADD(m,1,l.date)<GETDATE() ORDER BY date DESC;",LogEntry.class);
			List<LogEntry> logsviejos = manager.createQuery(q2c).getResultList();
			for(LogEntry l: logsviejos) {
				manager.remove(l);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		*/
	}
}	
