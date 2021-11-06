package datatypes;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class DtInstitucion implements Serializable{

	private String nombre;
	private String descripcion;
	private String url;
	
	public DtInstitucion(String nom,  String desc,  String web){
		this.setNombre(nom);
		this.setDescripcion(desc);
		this.setUrl(web);
	}
	public DtInstitucion() { }
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getURL() {
		return this.url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}