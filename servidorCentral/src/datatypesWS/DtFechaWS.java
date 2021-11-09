package datatypesWS;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import datatypes.DtFecha;    
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class DtFechaWS implements Serializable{

	private int anio,  mes,  dia,  horas,  minutos,  segundos;
	public DtFechaWS() {
		LocalDateTime tiempo = LocalDateTime.now();
		this.anio = tiempo.getYear();
		this.mes = tiempo.getMonthValue();
		this.dia = tiempo.getDayOfMonth();
		this.horas = tiempo.getHour();
		this.minutos = tiempo.getMinute();
		this.segundos = tiempo.getSecond();
	}
	public DtFechaWS(int anio,  int mes,  int dia,  int horas,  int minutos,  int segundos) {
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
	}
	public DtFechaWS(DtFechaWS fecha) {
		this.anio = fecha.getAnio();
		this.mes = fecha.getMes();
		this.dia = fecha.getDia();
		this.horas = fecha.getHoras();
		this.minutos = fecha.getMinutos();
		this.segundos = fecha.getSegundos();
	}
	public DtFechaWS(DtFecha fecha) {
		this.anio = fecha.getAnio();
		this.mes = fecha.getMes();
		this.dia = fecha.getDia();
		this.horas = fecha.getHoras();
		this.minutos = fecha.getMinutos();
		this.segundos = fecha.getSegundos();
	}
	public DtFecha adapt() {
		return new DtFecha(anio,mes,dia,horas,minutos,segundos);
	}
	
	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
}
