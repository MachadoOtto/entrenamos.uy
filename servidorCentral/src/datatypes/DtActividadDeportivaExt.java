package datatypes;

import java.util.Set;

public class DtActividadDeportivaExt extends DtActividadDeportiva{
	private Set<String> cl;
	private Set<String> cup;
	public DtActividadDeportivaExt(String nombre, String desc, int durmin, float costo, DtFecha fechaReg, Set<String> cat, Set<String> clases, Set<String> cuponeras, TEstado e){
		super(nombre,desc,durmin,costo,fechaReg,cat,e);
		cl = clases;
		cup = cuponeras;
	}
	public Set<String> getClases(){
		return cl;
	}
	public Set<String> getCuponeras(){
		return cup;
	}
}
