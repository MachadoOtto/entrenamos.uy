package datatypesWS;

import java.io.Serializable;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({String.class})
@XmlRootElement()
public class DtCapsula<Content> implements Serializable{

	private Content contenido;
	public DtCapsula() {}
	public DtCapsula(Content contenido) {
		this.contenido = contenido;
	}
	public void setContenido(Content cont) {
		contenido = cont;
	}
	public Content getContenido() {
		return contenido;
	}
}
