package datatypes;

public class DtInstitucion {

	private String nombre;
	private String descripcion;
	private String URL;
	
	public DtInstitucion(String nom, String desc, String web){
		this.nombre = nom;
		this.descripcion = desc;
		this.URL = web;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getURL() {
		return this.URL;
	}
}