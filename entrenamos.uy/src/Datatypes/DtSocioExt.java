package datatypes;

import java.util.Set;

public class DtSocioExt extends DtSocio {

	private Set<String> clases;
	
	public DtSocioExt(String nickname, String nombre, String apellido, String email, DtFecha fechaNacimiento, Set<String> clases){
		super(nickname, nombre, apellido, email, fechaNacimiento);
		this.clases = clases;
	}
	
	public Set<String> getClases(){
		return this.clases;
	}
	
}
