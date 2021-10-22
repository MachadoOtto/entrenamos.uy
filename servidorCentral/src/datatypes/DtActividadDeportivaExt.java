package datatypes;

import java.util.Set;

public class DtActividadDeportivaExt extends DtActividadDeportiva{
	private Set<String> clases;
	private Set<String> cup;
	public DtActividadDeportivaExt(String nombre, String desc, int durmin, float costo, DtFecha fechaReg, Set<String> cat, Set<String> clases, Set<String> cuponeras, TEstado state,String creador){
		super(nombre, desc, durmin, costo, fechaReg, cat, state, creador);
		this.clases = clases;
		cup = cuponeras;
	}
	public DtActividadDeportivaExt(String nombre, String desc, int durmin, float costo, DtFecha fechaReg, Set<String> cat, Set<String> clases, Set<String> cuponeras, TEstado state,String creador,String img){
		super(nombre, desc, durmin, costo, fechaReg, cat, state, creador, img);
		this.clases = clases;
		cup = cuponeras;
	}
	public Set<String> getClases(){
		return clases;
	}
	public Set<String> getCuponeras(){
		return cup;
	}
}
