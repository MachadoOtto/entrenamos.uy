package datatypes;

public class DtCapsula<Content> {
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
