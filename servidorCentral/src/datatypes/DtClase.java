package datatypes;

public class DtClase{

	private String nombre, correoProfesor, nicknameProfesor, URL, imgName;
	private int minSocios, maxSocios;
	private DtFecha fechaClase, fechaRegistro;	
	
	public DtClase(String nom, String nickP, String emailP, int min, int max, String url, DtFecha fechC, DtFecha fechR,String img) {
		nombre = nom;
		correoProfesor = emailP;
		nicknameProfesor = nickP;
		minSocios = min;
		maxSocios = max;
		URL = url;
		fechaClase = fechC;
		fechaRegistro = fechR;
		imgName = img;
	}
	
	public DtClase(String nom, String nickP, String emailP, int min, int max, String url, DtFecha fechC, DtFecha fechR){
		nombre = nom;
		correoProfesor = emailP;
		nicknameProfesor = nickP;
		minSocios = min;
		maxSocios = max;
		URL = url;
		fechaClase = fechC;
		fechaRegistro = fechR;
		imgName= null;
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

	public String getImgName() {
		return imgName;
	}
}