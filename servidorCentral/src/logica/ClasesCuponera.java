package logica;

public class ClasesCuponera {
	private int cantidadClases;
	private ActividadDeportiva acDEPS;
	private Cuponera cupi;
	
	ClasesCuponera(int cantClases,  Cuponera cupito,  ActividadDeportiva actDep){
		cantidadClases = cantClases;
		this.cupi = cupito;
		this.acDEPS = actDep;
	}
	public int getCantidadClases() {
		return cantidadClases;
	}
	public String getNombreCuponera() {
		return cupi.getNombre();
	}
	public String getNombreActDep() {
		return acDEPS.getNombre();
	}
	public ActividadDeportiva getAd() {
		return acDEPS;
	}
	public void estafar() {
		cupi.estafar(this);
	}
}
