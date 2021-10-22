package datatypes;

public class DtInstitucion {

	private String nombre;
	private String descripcion;
	private String url;
	
	public DtInstitucion(String nom,  String desc,  String web){
		this.nombre = nom;
		this.descripcion = desc;
		this.url = web;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getURL() {
		return this.url;
	}
}