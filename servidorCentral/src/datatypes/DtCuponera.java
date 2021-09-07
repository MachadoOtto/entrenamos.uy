package datatypes;

import java.util.List;

public class DtCuponera {

	private String nombre,descripcion;
	private float descuento,costo;
	private DtFecha fechaInicio,fechaFin,fechaAlta;
	private List<DtClasesCuponera> contenido;
	private List<String> categorias;
	
	public DtCuponera(String nom, String descr, float desc, float cc, DtFecha fi, DtFecha ff, DtFecha fa, List<DtClasesCuponera> v, List<String> cat){
		nombre = nom;
		descripcion = descr;
		descuento = desc;
		fechaInicio = fi;
		fechaFin = ff;
		fechaAlta = fa;
		costo = cc;
		contenido = v;
		categorias=cat;
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
	
	public DtFecha getFechaAlta() {
		return this.fechaAlta;
	}
	
	public List<DtClasesCuponera> getContenido() {
		return this.contenido;
	}
	public List<String> getCategorias() {
		return categorias;
	}
	
	public float getCosto() {
		return costo;
	}

	public String toString() {
		String res = new String();
		res += "Nombre: " + this.getNombre() + "\n";
		res += "Descripcion: " + this.getDescripcion() + "\n";
		res += "Descuento: " + this.getDescuento() + "%\n";
		res += "Fecha inicio: " + this.getFechaInicio().toFecha() + "\n";
		res += "Fecha fin: " + this.getFechaFin().toFecha() + "\n";
		res += "Fecha registro: " + this.getFechaAlta().toFecha() + "\n";
		res += "Costo: " + this.getCosto() + " pesos.\n";
		res += "Contenido: " + this.getContenido() + "\n";
		return res;
	}
}