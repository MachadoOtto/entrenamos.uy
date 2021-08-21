package datatypes;

import java.util.List;

public class DtCuponera {

	private String nombre;
	private String descripcion;
	private float descuento;
	private DtFecha fechaInicio;
	private DtFecha fechaFin;
	private List<DtActividadDeportiva> acts;
	private List<Integer> clases;
	
	public DtCuponera(String nom, String descr, float desc, DtFecha fi, DtFecha ff, List<DtActividadDeportiva> acs, List<Integer> c ){
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
	
	public float getDescuento() {
		return this.descuento;
	}
	
	public DtFecha getFechaInicio() {
		return this.fechaInicio;
	}
	
	public DtFecha getFechaFin() {
		return this.fechaFin;
	}
	
	public List<DtActividadDeportiva> getActividades() {
		return this.acts;
	}
	
	public List<Integer> getClases() {
		return this.clases;
	}

}