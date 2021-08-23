package datatypes;

import java.util.List;

public class DtCuponera {

	private String nombre,descripcion;
	private float descuento,costo;
	private DtFecha fechaInicio,fechaFin;
	private List<DtClasesCuponera> contenido;
	
	public DtCuponera(String nom, String descr, float desc, float cc, DtFecha fi, DtFecha ff, List<DtClasesCuponera> v){
		this.nombre = nom;
		this.descripcion = descr;
		this.descuento = desc;
		this.fechaInicio = fi;
		this.fechaFin = ff;
		this.costo = cc;
		this.contenido = v;
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
	
	public List<DtClasesCuponera> getContenido() {
		return this.contenido;
	}
	
	public float getCosto() {
		return costo;
	}

}