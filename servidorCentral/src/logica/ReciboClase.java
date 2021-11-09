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
//	ReciboClase(DtReciboClase d, Clase c, Socio s){
//		aClase = c;
//		aSocio = s;
//		this.fechaInscripcion = d.fechaInscripcion;
//		this.tipo = d.tipo;
//		this.costo = d.costo;
//	}
	
	ReciboClase(DtFecha fecha,  TReg tipoRegistro,  float precio,  Clase aula,  Socio individuoAsociadoAlClub,  Cuponera cupon){
		aSocio = individuoAsociadoAlClub;
		aClase = aula;
		fechaInscripcion = fecha;
		tipo = tipoRegistro;
		costo = precio;
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
		return aSocio.getNickname() + " <" + aSocio.getCorreo() + "> - " 
				+ fechaInscripcion.toFecha() + " (" + tipo.toString() + ")";
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
	public Socio getSocio() {
		return aSocio;
	}
}
