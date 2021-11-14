package datatypes;

public class DtReciboClase {

	private String aClase;
	private String aSocio;
	private String cupons;
	private DtFecha fechaInscripcion;
	private TReg tipo;
	private float costo;
	
	public DtReciboClase(String clase, String socio, String cuponera, DtFecha fecha, TReg tipo, float costo) {
		aClase = clase;
		aSocio = socio;
		cupons = cuponera;
		fechaInscripcion = fecha;
		this.tipo = tipo;
		this.costo = costo;
	}

	public String getaClase() {
		return aClase;
	}

	public void setaClase(String aClase) {
		this.aClase = aClase;
	}

	public String getaSocio() {
		return aSocio;
	}

	public void setaSocio(String aSocio) {
		this.aSocio = aSocio;
	}

	public String getCupons() {
		return cupons;
	}

	public void setCupons(String cupons) {
		this.cupons = cupons;
	}

	public DtFecha getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(DtFecha fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public TReg getTipo() {
		return tipo;
	}

	public void setTipo(TReg tipo) {
		this.tipo = tipo;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
}