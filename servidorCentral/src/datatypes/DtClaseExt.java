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
}
