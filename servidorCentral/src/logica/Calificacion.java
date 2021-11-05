package logica;

public class Calificacion {
	private int valor;
	private Clase clase;
	private Socio socio;
	public Calificacion(Clase c, Socio s, int v) {
		valor = v;
		c = clase;
		s = socio;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public Clase getClase() {
		return clase;
	}
	public Socio getSocio() {
		return socio;
	}
}
