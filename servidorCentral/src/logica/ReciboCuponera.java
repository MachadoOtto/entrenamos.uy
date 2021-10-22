package logica;
import datatypes.DtFecha;

public class ReciboCuponera {
	private DtFecha fechaCompra;
	private Cuponera cuponera;
	private Socio socio;
	
	ReciboCuponera(DtFecha compra,  Cuponera cupi,  Socio socio){
		this.cuponera = cupi;
		this.socio = socio;
		fechaCompra = new DtFecha(compra);
	}
	
	public DtFecha getFechaCompra() {
		DtFecha fecha = new DtFecha(fechaCompra);
		return fecha;
	}
	public int cantidadClases(ActividadDeportiva act) {
		return cuponera.cantidadClases(act);
	}

	public Socio getSocio() {
		return socio;
	}
	
	public Cuponera getCuponera() {
		return cuponera;
	}
}
