package datatypesWS;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import datatypes.DtCategoria;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class DtCategoriaWS implements Serializable {
	private String nombre;
	
	public DtCategoriaWS(String nombre) {
		this.setNombre(nombre);
	}
	public DtCategoriaWS() { }
	public DtCategoriaWS(DtCategoria d) {
		nombre = d.getNombre();
	}
	public DtCategoria adapt() {
		return new DtCategoria(nombre);
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
