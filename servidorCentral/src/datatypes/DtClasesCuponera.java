package datatypes;

public class DtClasesCuponera {
	private String nombreActividad;
	private int cantidadClases;
	public DtClasesCuponera(String s, int t) {
		nombreActividad = s;
		cantidadClases = t;
	}
	public String getNombreActividad() {
		return nombreActividad;
	}
	public int getCantidadClases() {
		return cantidadClases;
	}
	
}
