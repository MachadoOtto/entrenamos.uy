package datatypes;

public class DtClase{

	private String nombre, correoProfesor, nicknameProfesor, URL;
	
	private int minSocios, maxSocios;
	
	private DtFecha fechaClase, fechaRegistro;	
	
	public DtClase(String nom, String nickP, String emailP, int min, int max, String url, DtFecha fechC, DtFecha fechR){
		nombre = nom;
		correoProfesor = emailP;
		nicknameProfesor = nickP;
		minSocios = min;
		maxSocios = max;
		URL = url;
		fechaClase = fechC;
		fechaRegistro = fechR;
	}
	
	public String getNombre() { return nombre; }
	
	public String getNicknameProfesor() { return nicknameProfesor; }
	
	public String getCorreoProfesor() { return correoProfesor; }
	
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