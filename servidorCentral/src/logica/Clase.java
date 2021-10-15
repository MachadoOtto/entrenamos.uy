package logica;

import datatypes.DtClaseExt;
import datatypes.DtClase;
import datatypes.DtFecha;

import java.util.ArrayList;
import java.util.List;

public class Clase {
	private String nombre;
	private DtFecha fechaClase;
	private int minSocios;
	private int maxSocios;
	private String URL;
	private DtFecha fechaRegistro;
	private List<ReciboClase> ListReciboClase;
	private Profesor p;
	private ActividadDeportiva a;
	private String imgName;
	
	Clase(DtClase d, Profesor p, ActividadDeportiva a){
		this.a = a;
		this.nombre = d.getNombre();
		this.fechaClase = d.getFechaClase();
		this.minSocios = d.getMinSocios();
		this.maxSocios = d.getMaxSocios();
		this.URL = d.getURL();
		this.fechaRegistro = d.getFechaRegistro();
		this.p = p;
		this.ListReciboClase = new ArrayList<ReciboClase>();
		imgName = d.getImgName();
	}
	
	public String getNombre() {
		String s = nombre;
		return s;
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
		return p;
	}
	
	public String getURL() {
		return URL;
	}
	
	public DtFecha getFechaRegistro() {
		DtFecha ret = new DtFecha(fechaRegistro);
		return ret;
	}
	
	public DtClaseExt getDt() {
		List<String> SoloNombres = new ArrayList<>();
		List<String> ListNombres = new ArrayList<>();
		for(ReciboClase x: ListReciboClase) {
			ListNombres.add(x.getNickCorreoSocio());
			SoloNombres.add(x.getNick());
		}
		DtClaseExt x = new DtClaseExt(nombre, p.getNickname(), p.getCorreo(), minSocios, maxSocios, URL, this.getFechaClase(),
				this.getFechaRegistro(), ListNombres, SoloNombres,imgName);
		return x;
	}
	
	public boolean hayLugar() {
		return ListReciboClase.size() < maxSocios;
	}
	
	public boolean tieneActividadDeportiva(ActividadDeportiva actDep) {
		return actDep == a;
	}
	
	public void addRecibo(ReciboClase rc) {
		ListReciboClase.add(rc);
	}
	
	public boolean tieneActividadDeportiva(String aa) {
		return a.getNombre().equals(aa);
	}
	
	public ActividadDeportiva getAD() {
		return a;
	}
	
}