package datatypes;

import java.util.Set;

public class DtCuponera {

	private String nombre;
	private String descripcion;
	private int descuento;
	private DtFecha fechaInicio;
	private DtFecha fechaFin;
	private Set<DtActividadDeportiva> acts;
	private Set<int> clases;
	
	public DtCuponera(String nom, String descr, int desc, DtFecha fi, DtFecha ff, Set<DtActividadDeportiva> acs, Set<int> c ) {
		this.nombre = nom;
		this.descripcion = descr;
		this.descuento = desc;
		this.fechaInicio = fi;
		this.fechaFin = ff;
		this.acts = acs;
		this.clases = c;	
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public int getDescuento() {
		return this.descuento;
	}
	
	public DtFecha getFechaInicio() {
		return this.fechaInicio;
	}
	
	public DtFecha getFechaFin() {
		return this.fechaFin;
	}
	
	public Set<DtActividadDeportiva> getActividades() {
		return this.acts;
	}
	
	public Set<int> getClases() {
		return this.clases;
	}

}