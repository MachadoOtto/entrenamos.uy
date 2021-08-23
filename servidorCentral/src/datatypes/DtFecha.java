package datatypes;
import java.time.LocalDateTime;    

public class DtFecha {

	private int anio, mes, dia, horas, minutos, segundos;
	public DtFecha() {
		LocalDateTime t = LocalDateTime.now();
		this.anio = t.getYear();
		this.mes = t.getMonthValue();
		this.dia = t.getDayOfMonth();
		this.horas = t.getHour();
		this.minutos = t.getMinute();
		this.segundos = t.getSecond();
	}
	public DtFecha (int anio,int mes,int dia,int horas,int minutos,int segundos) {
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
	}
	public DtFecha (DtFecha q) {
		this.anio = q.getAnio();
		this.mes = q.getMes();
		this.dia = q.getDia();
		this.horas = q.getHoras();
		this.minutos = q.getMinutos();
		this.segundos = q.getSegundos();
	}
	
	public int getAnio() {
		return this.anio;
	}
	
	public int getMes() {
		return this.mes;
	}
	
	public int getDia() {
		return this.dia;
	}
	
	public int getHoras() {
		return this.horas;
	}
	
	public int getMinutos() {
		return this.minutos;
	}
	
	public int getSegundos() {
		return this.segundos;
	}
	public String toFechaHora() {
		return ""+dia+"/"+mes+"/"+anio+" "+horas+":"+minutos+":"+segundos;
	}
	public String toFecha() {
		return ""+dia+"/"+mes+"/"+anio;
	}
}
