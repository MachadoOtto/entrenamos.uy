package datatypes;

import java.util.Set;

public class DtActividadDeportivaExt extends DtActividadDeportiva{
	private Set<String> cl;
	private Set<String> cup;
	public DtActividadDeportivaExt(String nombre, String desc, int durmin, float costo, DtFecha fechaReg, Set<String> cat, Set<String> clases, Set<String> cuponeras, TEstado e,String creador){
		super(nombre,desc,durmin,costo,fechaReg,cat,e,creador);
		cl = clases;
		cup = cuponeras;
	}
	public DtActividadDeportivaExt(String nombre, String desc, int durmin, float costo, DtFecha fechaReg, Set<String> cat, Set<String> clases, Set<String> cuponeras, TEstado e,String creador,String img){
		super(nombre,desc,durmin,costo,fechaReg,cat,e,creador,img);
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
