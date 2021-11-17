package datatypesWS;

import java.io.Serializable;

public class LogEntryWS implements Serializable{
	private static final long serialVersionUID = 8789214547838459909L;
	
	private String ip,url,browser,so;
	private DtFechaWS date;
	
	public LogEntryWS() { }

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

	public String getSo() {
		return so;
	}

	public void setSo(String so) {
		this.so = so;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DtFechaWS getDate() {
		return date;
	}

	public void setDate(DtFechaWS date) {
		this.date = date;
	}
}
