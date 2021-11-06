package datatypesWS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import datatypes.DtClaseExt;
import datatypes.DtFecha;
import datatypes.DtPremio;

public class DtClaseWS {

	private String nombre,  correoProfesor,  nicknameProfesor,  urlwebsite,  imgName=null, urlVideo=null;
	private int minSocios,  maxSocios;
	private DtFecha fechaClase,  fechaRegistro;	
	private DtPremioWS premio=null;
	//private List<String> alumnos;
	//private List<String> soloNickAlumnos;
	//private Map<String, Integer> calificaciones;
	private String[] alumnos;
	private String[] soloNickAlumnos;
	private String[] calificacionesHead;
	private int[] calificacionesData;
	
	public DtClaseWS() { }
	public DtClaseWS(DtClaseExt c) {
		int i=0;
		calificacionesHead = new String[c.getCalificaciones().size()];
		calificacionesData = new int[c.getCalificaciones().size()];
		for(Entry<String, Integer> x: c.getCalificaciones().entrySet()) {
			calificacionesHead[i] = x.getKey();
			calificacionesData[i++] = x.getValue();
		}
		alumnos = c.getAlumnos().toArray(new String[0]);
		soloNickAlumnos = c.getNickAlumnos().toArray(new String[0]);
		this.setNombre(c.getNombre());
		this.setCorreoProfesor(c.getCorreoProfesor());
		this.setNicknameProfesor(c.getNicknameProfesor());
		this.setUrlwebsite(c.getURL());
		this.setImgName(c.getImgName());
		this.setUrlVideo(c.getUrlVideo());
		this.setMinSocios(c.getMinSocios());
		this.setMaxSocios(c.getMaxSocios());
		this.setFechaClase(c.getFechaClase());
		this.setFechaRegistro(c.getFechaRegistro());
		if (c.getPremio()!=null)
			this.setPremio(new DtPremioWS(c.getPremio()));
	}
	public DtClaseExt adapt() {
		List<String> als = Arrays.asList(alumnos);
		List<String> sna = Arrays.asList(soloNickAlumnos);
		Map<String,  Integer> calificaciones = new HashMap<>();
		for(int i=0; i<calificacionesHead.length; i++) {
			calificaciones.put(calificacionesHead[i], calificacionesData[i]);
		}
		return new DtClaseExt(this.getNombre(),this.getNicknameProfesor(),this.getCorreoProfesor(), minSocios, maxSocios,urlwebsite, fechaClase, fechaRegistro,   
				als,sna,imgName,urlVideo, premio.adapt(), calificaciones);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreoProfesor() {
		return correoProfesor;
	}
	public void setCorreoProfesor(String correoProfesor) {
		this.correoProfesor = correoProfesor;
	}
	public String getNicknameProfesor() {
		return nicknameProfesor;
	}
	public void setNicknameProfesor(String nicknameProfesor) {
		this.nicknameProfesor = nicknameProfesor;
	}
	public String getUrlwebsite() {
		return urlwebsite;
	}
	public void setUrlwebsite(String urlwebsite) {
		this.urlwebsite = urlwebsite;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getUrlVideo() {
		return urlVideo;
	}
	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}
	public int getMinSocios() {
		return minSocios;
	}
	public void setMinSocios(int minSocios) {
		this.minSocios = minSocios;
	}
	public int getMaxSocios() {
		return maxSocios;
	}
	public void setMaxSocios(int maxSocios) {
		this.maxSocios = maxSocios;
	}
	public DtFecha getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(DtFecha fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public DtFecha getFechaClase() {
		return fechaClase;
	}
	public void setFechaClase(DtFecha fechaClase) {
		this.fechaClase = fechaClase;
	}
	public DtPremioWS getPremio() {
		return premio;
	}
	public void setPremio(DtPremioWS premio) {
		this.premio = premio;
	}
	public String[] getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(String[] alumnos) {
		this.alumnos = alumnos;
	}
	public String[] getSoloNickAlumnos() {
		return soloNickAlumnos;
	}
	public void setSoloNickAlumnos(String[] soloNickAlumnos) {
		this.soloNickAlumnos = soloNickAlumnos;
	}
	public String[] getCalificacionesHead() {
		return calificacionesHead;
	}
	public void setCalificacionesHead(String[] calificacionesHead) {
		this.calificacionesHead = calificacionesHead;
	}
	public int[] getCalificacionesData() {
		return calificacionesData;
	}
	public void setCalificacionesData(int[] calificacionesData) {
		this.calificacionesData = calificacionesData;
	}
	
}
