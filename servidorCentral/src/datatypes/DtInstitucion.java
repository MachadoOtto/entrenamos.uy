package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


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