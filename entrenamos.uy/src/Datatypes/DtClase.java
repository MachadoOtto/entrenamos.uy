package datatypes;

public class DtClase{

	private String nombre;
	private String nombreProfesor;
	private int minSocios;
	private int maxSocios;
	private String URL;
	private DtFecha fechaClase;	
	private DtFecha fechaRegistro;
	
	public DtClase(String nom, String nomP, int min, int max, String url, DtFecha fechC, DtFecha fechR){
		this.nombre = nom;
		this.nombreProfesor = nomP;
		this.minSocios = min;
		this.maxSocios = max;
		this.URL = url;
		this.fechaClase = fechC;
		this.fechaRegistro = fechR;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getNombreProfesor() {
		return this.nombreProfesor;
	}
	
	public int getMinSocios() {
		return this.minSocios;
	}
	
	public int getMaxSocios() {
		return this.maxSocios;
	}
	
	public String getURL() {
		return this.URL;
	}
	
	public DtFecha getFechaClase() {
		return this.fechaClase;
	}
	
	public DtFecha getFechaRegistro() {
		return this.fechaRegistro;
	}
}
