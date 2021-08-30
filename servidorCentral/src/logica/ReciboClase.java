package logica;

import datatypes.DtFecha;
import datatypes.TReg;

public class ReciboClase {
	private DtFecha fechaInscripcion;
	private TReg tipo;
	private float costo;
	private Clase aClase;
	private Socio aSocio;
	private Cuponera cup;
	
	//
//	ReciboClase(DtReciboClase d,Clase c,Socio s){
//		aClase = c;
//		aSocio = s;
//		this.fechaInscripcion = d.fechaInscripcion;
//		this.tipo = d.tipo;
//		this.costo = d.costo;
//	}
	
	ReciboClase(DtFecha f,TReg t, float cc, Clase c, Socio s, Cuponera cupon){
		aSocio = s;
		aClase = c;
		fechaInscripcion = f;
		tipo = t;
		costo = cc;
		cup = cupon;
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
	
	public boolean tieneActividadDeportiva(ActividadDeportiva actDep) {
		return aClase.tieneActividadDeportiva(actDep);
	}
	
	public String getNickCorreoSocio() {
		return aSocio.getNickname() + " <" + aSocio.getCorreo() + ">";
	}
	
	public String getNick() {
		return aSocio.getNickname();
	}
	
	public Cuponera getCuponera() {
		return cup;
	}

	public Clase getClase() {
		return aClase;
	}
}
