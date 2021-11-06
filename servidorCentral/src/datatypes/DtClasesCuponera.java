package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


public class DtClasesCuponera {
	private String nombreActividad;
	private int cantidadClases;
	public DtClasesCuponera(String nombreAct,  int cantClase) {
		nombreActividad = nombreAct;
		cantidadClases = cantClase;
	}
	public String getNombreActividad() {
		return nombreActividad;
	}
	public int getCantidadClases() {
		return cantidadClases;
	}
	
}
