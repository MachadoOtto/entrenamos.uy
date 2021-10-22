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
	private String nombre,descripcion,img;
	private DtFecha fechaInicio,fechaFin,fechaAlta;
	private float descuento,costo;
	
	private List<ClasesCuponera> clasesCuphead;
	private List<ReciboCuponera> recibosCuponardos;
	private Set<Categoria> categorias;
	
	Cuponera(String nombre, String descripcion, int descuento, DtFecha fechaInicio, DtFecha fechaFin, DtFecha fechaAlta){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descuento = (float)descuento;
		this.fechaInicio = new DtFecha(fechaInicio);
		this.fechaFin = new DtFecha(fechaFin);
		this.fechaAlta = new DtFecha(fechaAlta);
		
		clasesCuphead = new ArrayList<>();
		recibosCuponardos = new ArrayList<>();
		categorias = new HashSet<>();
		costo = 0;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public DtFecha getFechaInicio() {
		DtFecha fecha = new DtFecha(fechaInicio);
		return fecha;
	}
	
	public DtFecha getFechaFin() {
		DtFecha fecha = new DtFecha(fechaFin);
		return fecha;
	}
	
	public DtFecha getFechaAlta() {
		DtFecha fecha = new DtFecha(fechaAlta);
		return fecha;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public List<String> getNombresActDep(){
		List<String> nomnom = new ArrayList<>();
		for(ClasesCuponera cc: clasesCuphead) {
			nomnom.add(cc.getNombreActDep());
		}
		return nomnom;
	}
	
	public void addActDep(ActividadDeportiva act, int num) throws CuponeraInmutableException{
		if(recibosCuponardos.size()>0)
			throw new CuponeraInmutableException("No es posible modificar la cuponera dado que ya hay socios que la compraron.");
		ClasesCuponera claCup = new ClasesCuponera(num,this,act);
		clasesCuphead.add(claCup);
		categorias.addAll(act.getCategorias());
		act.addClasesCup(claCup);
		costo = costo + (1 - descuento/100)*act.getCosto()*num;
	}
	
	public int cantidadClases(ActividadDeportiva actDep) {
		for(ClasesCuponera cc: clasesCuphead) {
			if(cc.getNombreActDep() == actDep.getNombre())
				return cc.getCantidadClases();
		}
		return 0;
	}
	public boolean tieneActividadDeportiva(ActividadDeportiva actDep) {
		for(ClasesCuponera cc: clasesCuphead) {
			if(cc.getNombreActDep() == actDep.getNombre())
				return true;
		}
		return false;
	}
	public DtCuponera getDt() {
		List<DtClasesCuponera> datosClases = new ArrayList<>();
		List<String> nombresCat = new ArrayList<>();
		for(ClasesCuponera cc: clasesCuphead) {
			DtClasesCuponera datosClaseCuponera = new DtClasesCuponera(cc.getNombreActDep(),cc.getCantidadClases());
			datosClases.add(datosClaseCuponera);
		}
		for(Categoria c: categorias) {
			nombresCat.add(c.getNombre());
		}
		DtCuponera datosCup = new DtCuponera(getNombre(), getDescripcion(), getDescuento(), getCosto(), getFechaInicio(),
				getFechaFin(), getFechaAlta(), datosClases,nombresCat,getImg());
		return datosCup;
	}
	public void addRecibo(ReciboCuponera reciboCup) {
		recibosCuponardos.add(reciboCup);
	}
	public List<ReciboCuponera> getRc() {
		return recibosCuponardos;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
