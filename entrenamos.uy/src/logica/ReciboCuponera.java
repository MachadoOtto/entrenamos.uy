package logica;

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
		DtFecha r = new DtFEcha(fechaCompra);
		return r;
	}
	public int cantidadClases(ActividadDeportiva act) {
		c.cantidadClases(act);
	}
}
