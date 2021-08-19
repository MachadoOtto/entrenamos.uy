package datatypes;

public class DtFecha {

	private int anio, mes, dia, horas, minutos, segundos;
	
	public DtFecha (int anio,int mes,int dia,int horas,int minutos,int segundos) {
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
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
}
