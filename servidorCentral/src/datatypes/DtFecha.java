package datatypes;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DtFecha {

	private int anio,  mes,  dia,  horas,  minutos,  segundos;
	public DtFecha() {
		LocalDateTime tiempo = LocalDateTime.now();
		this.anio = tiempo.getYear();
		this.mes = tiempo.getMonthValue();
		this.dia = tiempo.getDayOfMonth();
		this.horas = tiempo.getHour();
		this.minutos = tiempo.getMinute();
		this.segundos = tiempo.getSecond();
	}
	public DtFecha(int anio,  int mes,  int dia,  int horas,  int minutos,  int segundos) {
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
	}
	public DtFecha(DtFecha fecha) {
		this.anio = fecha.getAnio();
		this.mes = fecha.getMes();
		this.dia = fecha.getDia();
		this.horas = fecha.getHoras();
		this.minutos = fecha.getMinutos();
		this.segundos = fecha.getSegundos();
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
	public void setMinutos(int min) {
		minutos = min;
	}
	
	public int getSegundos() {
		return this.segundos;
	}
	
	public String toFechaHora() {
		String res = new String();
	    res += dia + "/" + mes + "/" + anio + " - ";
	    if (horas < 10)
	        res += "0";
	    res += horas + ":";
	    if (minutos < 10)
	        res += "0";
	    res +=  minutos;
	    return res;
	}
	
	public String toFecha() {
		return dia + "/" + mes + "/" + anio;
	}
	public String toWebFecha() {
		String sanio = String.valueOf(anio);
		String sdia= String.valueOf(dia),  smes=String.valueOf(mes);
		while (sanio.length()<4)
			sanio="0"+sanio;
		if (dia<10)
			sdia="0"+sdia;
		if (mes<10)
			smes="0"+mes;
		return sanio + "-" + smes + "-" + sdia;
	}
	
	public boolean esMenor(DtFecha fechaAComp) {
		long min1,  min2 = 0;
		min1 = minutos + (horas + (dia + (mes + anio * 12) * 31) * 24) * 60;
		min2 = fechaAComp.getMinutos() + (fechaAComp.getHoras() + (fechaAComp.getDia() + (fechaAComp.getMes() + 
				(fechaAComp.getAnio()) * 12) * 31) * 24) * 60;
		return min1 <= min2;
	}

	public boolean equals(DtFecha f) {
		return anio==f.getAnio() && mes==f.getMes() && dia==f.getDia() && horas==f.getHoras() && minutos==f.getMinutos() && segundos==f.getSegundos();
	}
	
	public Calendar toCalendar() {
		return new GregorianCalendar(getAnio(),getMes()-1,getDia(),getHoras(),getMinutos(),getSegundos());
	}
}