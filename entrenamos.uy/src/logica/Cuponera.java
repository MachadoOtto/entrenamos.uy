package logica;
import java.util.ArrayList;
import java.util.List;

public class Cuponera {
	private String nombre;
	private String descripcion;
	private DtFecha fechaInicio;
	private DtFecha fechaFin;
	private float descuento;
	
	private List<ClasesCuponera> cp;
	private List<ReciboCuponera> rc;
	
	Cuponera(String nombre, String descripcion, float descuento, DtFecha fechaInicio, DtFecha fechaFin){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descuento = descuento;
		this.fechaInicio = new DtFecha(fechaInicio);
		this.fechaFin = new DtFecha(fechaFin);
		this.cp = new ArrayList<>();
		this.rc = new ArrayList<>();
	}
	
	public String getNombre() {
		return d;
	}
	public String getDescripcion() {
		return d;
	}
	public DtFecha getFechaInicio() {
		DtFecha d = new DtFecha(fechaInicio);
		return d;
	}
	public DtFecha getFechaFin() {
		DtFecha d = new DtFecha(fechaFin);
		return d;
	}
	public float getDescuento() {
		return descuento;
	}
	
	public List<String> getNombresActDep(){
		List<String> nomnom = new ArrayList<>();
		for(ClasesCuponera cc: cp) {
			nomnom.add(cc.getNombreActDep());
		}
		return nomnom;
	}
	public void addActDep(ActividadDeportiva act, int num) {
		claCup = new ClasesCuponera(num,this,act);
		cp.add(claCup);
	}
	public int cantidadClases(ActividadDeportiva actDep) {
		for(ClasesCuponera cc: cp) {
			if(cc.getNombreActDep() == actDep.getNombre())
				return cc.getCantidadClases();
		}
	}
	public boolean tieneActividadDeportiva(ActividadDeportiva n) {
		for(ClasesCuponera cc: cp) {
			if(cc.getNombreActDep() == actDep.getNombre())
				return true;
		}
		return false;
	}
	public DtCuponera getDt() {
		List<String> actividadesNombre = new ArrayList<>();
		List<Integer> actividadesCantidad = new ArrayList<>();
		for(ClasesCuponera cc: cp) {
			actividadesNombre.add(cc.getNombreActDep());
			actividadesCantidad.add(cc.getCantidadClases());
		}
		return DtCuponera(nombre,descripcion,this.getFechaInicio(),this.getFechaFin(),descuento,actividadesNombre,actividadesCantidad);		
	}
}
