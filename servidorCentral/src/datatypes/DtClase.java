package datatypes;

public class DtClase{

	private String nombre,  correoProfesor,  nicknameProfesor,  urlwebsite,  imgName=null, urlVideo=null;
	private int minSocios,  maxSocios;
	private DtFecha fechaClase,  fechaRegistro;	
	private DtPremio premio=null;
	
	public DtClase(String nom,  String nickP,  String emailP,  int min,  int max,  String url,  DtFecha fechC,  DtFecha fechR){
		nombre = nom;
		correoProfesor = emailP;
		nicknameProfesor = nickP;
		minSocios = min;
		maxSocios = max;
		urlwebsite = url;
		fechaClase = fechC;
		fechaRegistro = fechR;
	}
	public DtClase(String nom,  String nickP,  String emailP,  int min,  int max,  String url,  DtFecha fechC,  DtFecha fechR,  String img) {
		this(nom, emailP, nickP, min, max, url, fechC, fechR);
		imgName = img;
	}	
	public DtClase(String nom,  String nickP,  String emailP,  int min,  int max,  String url,  DtFecha fechC,  DtFecha fechR,  String img, String uURLVideo) {
		this(nom, emailP, nickP, min, max, url, fechC, fechR, img);
		urlVideo = uURLVideo;
	}
	public DtClase(String nom,  String nickP,  String emailP,  int min,  int max,  String url,  DtFecha fechC,  DtFecha fechR,  String img, String uURLVideo, DtPremio prize) {
		this(nom, emailP, nickP, min, max, url, fechC, fechR, img, uURLVideo);
		premio = prize;
	}
	
	public String getNombre() {
		return nombre; 
	}
	
	public String getNicknameProfesor() { 
		return nicknameProfesor; 
	}
	
	public String getCorreoProfesor() { 
		return correoProfesor;
	}
	
	public int getMinSocios() {
		return this.minSocios;
	}
	
	public int getMaxSocios() {
		return this.maxSocios;
	}
	
	public String getURL() {
		return this.urlwebsite;
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
	public String getUrlVideo() {
		return urlVideo;
	}
	public DtPremio getPremio() {
		return premio;
	}
}