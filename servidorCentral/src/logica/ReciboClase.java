package logica;


import datatypes.DtFecha;
import datatypes.TReg;


public class ReciboClase {
	private DtFecha fechaInscripcion;
	private TReg tipo;
	private float costo;
	private Clase aClase;
	private Socio aSocio;
	
	//
//	ReciboClase(DtReciboClase d,Clase c,Socio s){
//		aClase = c;
//		aSocio = s;
//		this.fechaInscripcion = d.fechaInscripcion;
//		this.tipo = d.tipo;
//		this.costo = d.costo;
//	}
	
	ReciboClase(DtFecha f,TReg t, float cc, Clase c, Socio s){
		aSocio = s;
		aClase = c;
		fechaInscripcion = f;
		tipo = t;
		costo = cc;
	}
	public DtFecha getFechaInscripcion() {
		DtFecha ret = new DtFecha(fechaInscripcion);
		return ret;
	}
	public float getCosto() {
		return costo;
	}
	public boolean esTipoCuponera() {
		return tipo.equals(TReg.cuponera);
	}
	public String getNombreClase() {
		return aClase.getNombre();
	}
	public int tieneActividadDeportiva(ActividadDeportiva actDep) {
		return aClase.tieneActividadDeportiva(actDep);
	}
	public String getNombreSocio() {
		return aSocio.getNombre();
	}
}
