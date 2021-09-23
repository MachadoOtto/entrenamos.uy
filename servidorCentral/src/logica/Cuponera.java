package logica;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datatypes.DtFecha;
import excepciones.CuponeraInmutableException;
import datatypes.DtClasesCuponera;
import datatypes.DtCuponera;

public class Cuponera {
	private String nombre,descripcion;
	private DtFecha fechaInicio,fechaFin,fechaAlta;
	private float descuento,costo;
	
	private List<ClasesCuponera> cp;
	private List<ReciboCuponera> rc;
	private Set<Categoria> cc;
	
	Cuponera(String nombre, String descripcion, int descuento, DtFecha fechaInicio, DtFecha fechaFin, DtFecha fechaAlta){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descuento = (float)descuento;
		this.fechaInicio = new DtFecha(fechaInicio);
		this.fechaFin = new DtFecha(fechaFin);
		this.fechaAlta = new DtFecha(fechaAlta);
		
		cp = new ArrayList<>();
		rc = new ArrayList<>();
		cc = new HashSet<>();
		costo = 0;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public DtFecha getFechaInicio() {
		DtFecha d = new DtFecha(fechaInicio);
		return d;
	}
	
	public DtFecha getFechaFin() {
		DtFecha d = new DtFecha(fechaFin);
		return d;
	}
	
	public DtFecha getFechaAlta() {
		DtFecha d = new DtFecha(fechaAlta);
		return d;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public List<String> getNombresActDep(){
		List<String> nomnom = new ArrayList<>();
		for(ClasesCuponera cc: cp) {
			nomnom.add(cc.getNombreActDep());
		}
		return nomnom;
	}
	
	public void addActDep(ActividadDeportiva act, int num) throws CuponeraInmutableException{
		if(rc.size()>0)
			throw new CuponeraInmutableException("No es posible modificar la cuponera dado que ya hay socios que la compraron.");
		ClasesCuponera claCup = new ClasesCuponera(num,this,act);
		cp.add(claCup);
		cc.addAll(act.getCategorias());
		act.addClasesCup(claCup);
		costo = costo + (1 - descuento/100)*act.getCosto()*num;
	}
	
	public int cantidadClases(ActividadDeportiva actDep) {
		for(ClasesCuponera cc: cp) {
			if(cc.getNombreActDep() == actDep.getNombre())
				return cc.getCantidadClases();
		}
		return 0;
	}
	public boolean tieneActividadDeportiva(ActividadDeportiva n) {
		for(ClasesCuponera cc: cp) {
			if(cc.getNombreActDep() == n.getNombre())
				return true;
		}
		return false;
	}
	public DtCuponera getDt() {
		List<DtClasesCuponera> r = new ArrayList<>();
		List<String> r2 = new ArrayList<>();
		for(ClasesCuponera cc: cp) {
			DtClasesCuponera rr = new DtClasesCuponera(cc.getNombreActDep(),cc.getCantidadClases());
			r.add(rr);
		}
		for(Categoria c: cc) {
			r2.add(c.getNombre());
		}
		DtCuponera x = new DtCuponera(getNombre(), getDescripcion(), getDescuento(), getCosto(), getFechaInicio(),
				getFechaFin(), getFechaAlta(), r,r2);
		return x;
	}

	public List<ReciboCuponera> getRc() {
		return rc;
	}
}
