package logica.persistencia;


import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class LogEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	private String url;
	private String ip;
	private String browser;
	private String os;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar expires;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getExpires() {
		return expires;
	}

	public void setExpires(Calendar expires) {
		this.expires = expires;
	}
}
