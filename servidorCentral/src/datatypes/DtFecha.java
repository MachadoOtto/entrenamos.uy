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
		String sdia= String.valueOf(dia),smes=String.valueOf(mes);
		while(sanio.length()<4)
			sanio="0"+sanio;
		if(dia<10)
			sdia="0"+sdia;
		if(mes<10)
			smes="0"+mes;
		return sanio + "-" + smes + "-" + sdia;
	}
	
	public boolean esMenor(DtFecha fechaAComp) {
		long min1, min2 = 0;
		min1 = minutos + (horas + (dia + (mes + (anio) * 12) * 31) * 24) * 60;
		min2 = fechaAComp.getMinutos() + (fechaAComp.getHoras() + (fechaAComp.getDia() + (fechaAComp.getMes() + 
				(fechaAComp.getAnio()) * 12) * 31) * 24) * 60;
		return (min1 <= min2);
	}
}