package logica;
import java.util.ArrayList;
import java.util.List;

public class ReciboClase {
	private DtFecha fechaInscripcion;
	private TReg tipo;
	private float costo;
	private Clase aClase;
	private Socio aSocio;
	
	ReciboClase(DtReciboClase d,Clase c,Socio s){
		aClase = c;
		aSocio = s;
		this.fechaInscripcion = d.fechaInscripcion;
		this.tipo = d.tipo;
		this.costo = d.costo;
	}
	ReciboClase(DtFecha f,TReg t, float c, Clase c, Socio s){
		aSocio = s;
		aClase = c;
		clasesList = new ArrayList<Clases>;
		fechaInscripcion = f;
		tipo = t;
		costo = c;
	}
	public DtFecha getFechaInscripcion() {
		DtFecha ret = new DtFecha(fechaInscripcion);
		return ret;
	}
	public float getCosto() {
		return costo
	}
	public boolean esTipoCuponera() {
		return tipo==CUPONERA;
	}
	public String getNombreClase() {
		return aClase.getNombre()
	}
	public int tieneActividadDeportiva(ActividadDeportiva actdep) {
		return aClase.tieneActividadDeportiva(actDep);
	}
	public String getNombreSocio() {
		return aSocio.getNombre();
	}
}
