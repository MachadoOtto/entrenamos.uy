package logica;

public class ClasesCuponera {
	private int cantidadClases;
	private ActividadDeportiva ad;
	private Cuponera c;
	
	ClasesCuponera(int x,Cuponera c, ActividadDeportiva ad){
		cantidadClases = x;
		this.c = c;
		this.ad = ad;
	}
	public int getCantidadClases() {
		return cantidadClases;
	}
	public String getNombreCuponera() {
		return c.getNombre();
	}
	public String getNombreActDep() {
		return ad.getNombre();
	}
	public ActividadDeportiva getAd() {
		return ad;
	}
}
