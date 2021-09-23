package datatypes;

import java.util.HashSet;
import java.util.Set;


public class DtActividadDeportiva {

	private String nombre;
	private String descripcion;
	private int duracionMinutos;
	private float costo;
	private DtFecha fechaRegistro;
	private Set<String> categorias;
	private TEstado estado;
	private String creador;
	
	public DtActividadDeportiva(String nom, String desc, int dur, float cos, DtFecha fech,Set<String> categorias,TEstado e,String creador){
		this.nombre = nom;
		this.descripcion = desc;
		this.duracionMinutos = dur;
		this.costo = cos;
		this.fechaRegistro = fech;
		if (categorias==null)
			this.categorias = new HashSet<>();
		else
			this.categorias = categorias;
		this.estado = e;
		this.creador = creador;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public int getDuracionMinutos() {
		return this.duracionMinutos;
	}
	
	public float getCosto() {
		return this.costo;
	}
	
	public DtFecha getFechaRegistro() {
		return this.fechaRegistro;
	}

	public Set<String> getCategorias() {
		return categorias;
	}

	public TEstado getEstado() {
		return estado;
	}

	public String getCreador() {
		return creador;
	}
}

