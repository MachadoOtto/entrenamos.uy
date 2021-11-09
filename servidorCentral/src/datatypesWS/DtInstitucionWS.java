package datatypesWS;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import datatypes.DtInstitucion;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class DtInstitucionWS implements Serializable{

	private String nombre;
	private String descripcion;
	private String url;
	public DtInstitucionWS() { }
	public DtInstitucionWS(String nom,  String desc,  String web){
		this.setNombre(nom);
		this.setDescripcion(desc);
		this.setUrl(web);
	}
	public DtInstitucionWS(DtInstitucion i) {
		this.setDescripcion(i.getDescripcion());
		this.setNombre(i.getNombre());
		this.setUrl(i.getUrl());
	}
	public DtInstitucion adapt() {
		return new DtInstitucion(nombre,descripcion,url);
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
