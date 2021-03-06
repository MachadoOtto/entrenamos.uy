package datatypes;

import java.util.List;

public class DtCuponera {

	private String nombre,  descripcion;
	private float descuento,  costo;
	private DtFecha fechaInicio,  fechaFin,  fechaAlta;
	private List<DtClasesCuponera> contenido;
	private List<String> categorias;
	private String img;
	public DtCuponera(String nom,  String descr,  float desc,  float precio,  DtFecha diaDeComienzo,  DtFecha horaDelRelojEnLaQueFinaliza,  DtFecha fechaBaja,  List<DtClasesCuponera> content,  List<String> cat){
		nombre = nom;
		descripcion = descr;
		descuento = desc;
		fechaInicio = diaDeComienzo;
		fechaFin = horaDelRelojEnLaQueFinaliza;
		fechaAlta = fechaBaja;
		costo = precio;
		contenido = content;
		categorias=cat;
		img = null;
	}
	public DtCuponera(String nom,  String descr,  float desc,  float precio,  DtFecha fechaIni,  DtFecha fechaFini,  DtFecha fechaAltaini,  List<DtClasesCuponera> content,  List<String> cat,  String imagen){
		this(nom, descr, desc, precio, fechaIni, fechaFini, fechaAltaini, content, cat);
		img = imagen;
	}
	
	public String getImgName() {
		return img;
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