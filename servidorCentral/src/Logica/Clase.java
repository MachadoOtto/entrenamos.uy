package logica;
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
	
	Clase(DtClase d, Profesor p, ActividadDeportiva a){
		this.a = a;
		this.nombre = d.nombre;
		this.fechaClase = d.fechaClase;
		this.minSocios = d.minSocios;
		this.maxSocios = d.maxSocios;
		this.URL = d.URL;
		this.fechaRegistro = d.fechaRegistro;
		this.p = p;
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
	public String getURL() {
		return URL;
	}
	public DtFecha getFechaRegistro() {
		DtFecha ret = new DtFecha(fechaRegistro);
		return ret;
	}
	public DtClaseExt getDt() {
		List<String> ListNombres = new ArrayList<>()
		for(ReciboClase x: ListReciboClase) {
			ListNombres.add(x.getNombreSocio());
		}
		return DtClaseExt(nombre,p.getNombre(),this.getFechaClase(),minSocios,maxSocios,URL,this.getFechaRegistro(),ListNombres);
	}
	public boolean hayLugar() {
		return ListReciboClase.size() < maxSocios;
	}
	public int tieneActividadDeportiva(ActividadDeportiva actDep) {
		if(actDep==a)
			return 1;
		else
			return 0;
	}
}
