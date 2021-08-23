package datatypes;
import java.util.List;


public class DtClaseExt extends DtClase {
	
	private List<String> alumnos;
	
	public DtClaseExt(String nom, String nomP, int min, int max, String url, DtFecha fechC, DtFecha fechR, List<String> als) {
		super(nom, nomP, min, max, url, fechC, fechR);
		this.alumnos = als;
	}

	public List<String> getAlumnos() {
		return this.alumnos;
	}
	
	public String toString() {
		String res = new String();
		res += "Nombre de Clase: " + this.getNombre() + "\n";
		res += "Fecha de Inicio: " + this.getFechaClase().toFechaHora() + "\n";
		res += "Profesor que la Dicta: " + this.getNombreProfesor() + "\n";
		res += "Cantidad de Cupos: \n";
		res += "    Minimos: " + this.getMinSocios() + " socios.\n";
		res += "    Maximos: " + this.getMaxSocios() + " socios.\n";
		res += "Direccion Web: " + this.getURL() + "\n";
		res += "Fecha de Registro: " + this.getFechaRegistro().toFecha() + "\n";
		if (alumnos.isEmpty()) {
			res += "Esta clase no presenta alumnos ingresados.";
		} else {
			res += "Listado de Alumnos: \n";
			for (String x: alumnos) {
				res += "    " + x + "\n";
			}
		}
		return res;
	}
}
