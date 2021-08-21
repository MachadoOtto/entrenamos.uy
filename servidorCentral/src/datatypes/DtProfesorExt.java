package datatypes;

import java.util.Set;

public class DtProfesorExt extends DtProfesor{
	
	private Set<String> clasesDictadas;
	private Set<String> actividadesDepAsociadas;
	
	public DtProfesorExt (String nickname, String nombre, String apellido, String email, DtFecha fechaNacimiento, String nombreInstitucion, String descripcion, String biografia, String link, Set<String> clases, Set<String> acts) {
		super(nickname, nombre, apellido, email, fechaNacimiento, nombreInstitucion, descripcion, biografia, link); 
		this.actividadesDepAsociadas = acts;
		this.clasesDictadas = clases;
	}
	
	public Set<String> getActividadesDepAsociadas(){
		return this.actividadesDepAsociadas;
	}
	
	public Set<String> getClasesDictadas(){
		return this.clasesDictadas;
	}
}
