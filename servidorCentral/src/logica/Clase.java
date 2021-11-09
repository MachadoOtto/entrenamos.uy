package logica;

import datatypes.DtClaseExt;
import datatypes.DtClase;
import datatypes.DtFecha;
import datatypes.DtPremio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Clase {
	private String nombre;
	private DtFecha fechaClase;
	private int minSocios;
	private int maxSocios;
	private String url;
	private DtFecha fechaRegistro;
	private List<ReciboClase> listaReciboClase;
	private Profesor vasilev;
	private ActividadDeportiva actDep;
	private String imgName, urlVideo;
	private Map<String, Calificacion> calificaciones;
	private Premio prize = null;
	
	Clase(DtClase datoClase,  Profesor profe,  ActividadDeportiva actDep){
		this.actDep = actDep;
		this.nombre = datoClase.getNombre();
		this.fechaClase = datoClase.getFechaClase();
		this.minSocios = datoClase.getMinSocios();
		this.maxSocios = datoClase.getMaxSocios();
		this.url = datoClase.getURL();
		this.fechaRegistro = datoClase.getFechaRegistro();
		this.vasilev = profe;
		this.listaReciboClase = new ArrayList<ReciboClase>();
		this.calificaciones = new HashMap<>();
		this.urlVideo = datoClase.getUrlVideo();
		this.imgName = datoClase.getImgName();
		if (datoClase.getPremio() != null)
			prize = new Premio(this, datoClase.getPremio().getDescripcion(), datoClase.getPremio().getCantidad());
	}

	public String getNombre() {
		String res = nombre;
		return res;
	}
	
	public DtFecha getFechaClase() {
		DtFecha ret = new DtFecha(fechaClase);
		return ret;
	}
	
	public int getMinSocios() {
		return minSocios;
	}
	
	public int getMaxSocios() {
		return maxSocios;
	}
	
	public Profesor getProfesor() {
		return vasilev;
	}
	
	public String getURL() {
		return url;
	}
	
	public DtFecha getFechaRegistro() {
		DtFecha ret = new DtFecha(fechaRegistro);
		return ret;
	}
	
	public DtClaseExt getDt() {
		List<String> SoloNombres = new ArrayList<>();
		List<String> ListNombres = new ArrayList<>();
		for (ReciboClase x: listaReciboClase) {
			ListNombres.add(x.getNickCorreoSocio());
			SoloNombres.add(x.getNick());
		}
		Map<String, Integer> calif = new HashMap<>();
		for(Entry<String, Calificacion> x: calificaciones.entrySet()) {
			calif.put(x.getKey(), x.getValue().getValor());
		}
		DtPremio dorado = null;
		if(getPrize()!=null)
			dorado = getPrize().getDt();
		DtClaseExt claseDatos = new DtClaseExt(nombre,  vasilev.getNickname(),  vasilev.getCorreo(),  minSocios,  maxSocios,  url,  this.getFechaClase(), 
				this.getFechaRegistro(),  ListNombres,  SoloNombres,  imgName,
				getUrlVideo(),dorado,calif);
		return claseDatos;
	}
	
	public boolean hayLugar() {
		return listaReciboClase.size() < maxSocios;
	}
	
	public boolean tieneActividadDeportiva(ActividadDeportiva actDep) {
		return this.actDep == actDep;
	}
	public List<ReciboClase> getRecibo(){
		return listaReciboClase;
	}
	
	public void addRecibo(ReciboClase recibo) {
		listaReciboClase.add(recibo);
	}
	public void addCalifiacion(String socioNick, Calificacion calif) {
		calificaciones.put(socioNick, calif);
	}
	
	public boolean tieneActividadDeportiva(String activity) {
		return actDep.getNombre().equals(activity);
	}
	
	public ActividadDeportiva getAD() {
		return actDep;
	}

	public String getUrlVideo() {
		return urlVideo;
	}
	public Map<String, Calificacion> getCalificaciones(){
		return calificaciones;
	}
	
	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public Premio getPrize() {
		return prize;
	}
	
}