package datatypes;
import java.util.Set;


public class DtClaseExt extends DtClase {
	
	private Set<String> alumnos;
	
	public DtClaseExt(String nom, String nomP, int min, int max, String url, DtFecha fechC, DtFecha fechR, Set<String> als) {
		super(nom, nomP, min, max, url, fechC, fechR);
		this.alumnos = als;
	}

	public Set<String> getAlumnos() {
		return this.alumnos;
	}
}
