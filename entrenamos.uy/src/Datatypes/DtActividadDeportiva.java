package datatypes;

public class DtActividadDeportiva {

	private String nombre;
	private String descripcion;
	private int duracionMinutos;
	private float costo;
	private DtFecha fechaRegistro;

	public DtActividadDeportiva(String nom, String desc, int dur, float cos, DtFecha fech){
		this.nombre = nom;
		this.descripcion = desc;
		this.duracionMinutos = dur;
		this.costo = cos;
		this.fechaRegistro = fech;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public int getDuracionMinutos() {
		return this.duracionMinutos;
	}
	
	public float getCosto() {
		return this.costo;
	}
	
	public DtFecha getFechaRegistro() {
		return this.fechaRegistro;
	}
}

