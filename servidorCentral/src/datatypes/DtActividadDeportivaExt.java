package datatypes;

import java.util.Set;

public class DtActividadDeportivaExt extends DtActividadDeportiva{
	private Set<String> clases;
	private Set<String> cup;
	private int favoritos=0;
	
	public DtActividadDeportivaExt(String nombre,   String desc,   int durmin,   float costo,   DtFecha fechaReg,   Set<String> cat,   Set<String> clases,   Set<String> cuponeras,   TEstado state,  String creador){
		super(nombre,   desc,   durmin,   costo,   fechaReg,   cat,   state,   creador);
		this.clases = clases;
		cup = cuponeras;
	}
	public DtActividadDeportivaExt(String nombre,   String desc,   int durmin,   float costo,   DtFecha fechaReg,   Set<String> cat,   Set<String> clases,   Set<String> cuponeras,   TEstado state,  String creador,  String img){
		super(nombre,   desc,   durmin,   costo,   fechaReg,   cat,   state,   creador,   img);
		this.clases = clases;
		cup = cuponeras;
	}
	public DtActividadDeportivaExt(String nombre,   String desc,   int durmin,   float costo,   DtFecha fechaReg,   Set<String> cat,   Set<String> clases,   Set<String> cuponeras,   TEstado state,  String creador,  String img, int cantfav){
		super(nombre,   desc,   durmin,   costo,   fechaReg,   cat,   state,   creador,   img);
		this.clases = clases;
		cup = cuponeras;
		favoritos = cantfav;
	}
	public Set<String> getClases(){
		return clases;
	}
	public Set<String> getCuponeras(){
		return cup;
	}
	public int getFavoritos() {
		return favoritos;
	}
	public void setFavoritos(int favoritos) {
		this.favoritos = favoritos;
	}
}
