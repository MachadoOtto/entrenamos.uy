package logica;
import datatypes.DtFecha;

public class ReciboCuponera {
	private DtFecha fechaCompra;
	private Cuponera c;
	private Socio s;
	
	ReciboCuponera(DtFecha compra,Cuponera c,Socio s){
		this.c = c;
		this.s = s;
		fechaCompra = new DtFecha(compra);
	}
	
	public DtFecha getFechaCompra() {
		DtFecha r = new DtFecha(fechaCompra);
		return r;
	}
	public int cantidadClases(ActividadDeportiva act) {
		return c.cantidadClases(act);
	}

	public Socio getSocio() {
		return s;
	}
}
