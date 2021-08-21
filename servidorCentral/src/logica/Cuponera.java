package logica;
import java.util.ArrayList;
import java.util.List;

import datatypes.DtFecha;
import datatypes.DtActividadDeportiva;
import datatypes.DtCuponera;

public class Cuponera {
	private String nombre,descripcion;
	private DtFecha fechaInicio,fechaFin;
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
		ClasesCuponera claCup = new ClasesCuponera(num,this,act);
		cp.add(claCup);
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
		List<DtActividadDeportiva> actsdt = new ArrayList<>();
		List<Integer> actscan = new ArrayList<>();
		for(ClasesCuponera cc: cp) {
			actsdt.add(cc.getAd().getDt());
			actscan.add(cc.getCantidadClases());
		}
		DtCuponera x = new DtCuponera(nombre, descripcion, descuento, fechaInicio, fechaFin, actsdt, actscan);
		return x;
	}

	public List<ReciboCuponera> getRc() {
		return rc;
	}
}
