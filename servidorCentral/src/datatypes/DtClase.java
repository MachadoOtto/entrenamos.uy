package datatypes;

public class DtClase{

	private String nombre,nombreProfesor,nicknameProfesor,URL;
	private int minSocios,maxSocios;
	private DtFecha fechaClase,fechaRegistro;	
	
	public DtClase(String nom, String nomP,String nickP, int min, int max, String url, DtFecha fechC, DtFecha fechR){
		nombre = nom;
		nombreProfesor = nomP;
		nicknameProfesor = nickP;
		minSocios = min;
		maxSocios = max;
		URL = url;
		fechaClase = fechC;
		fechaRegistro = fechR;
	}
	
	public String getNombre() {return nombre;}
	public String getNombreProfesor() {return nombreProfesor;}
	public String getNicknameProfesor() {return nicknameProfesor;}
	
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
