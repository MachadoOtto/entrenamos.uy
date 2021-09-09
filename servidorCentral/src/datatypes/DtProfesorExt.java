package datatypes;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DtProfesorExt extends DtProfesor{

	private Map<String,Set<String>> x;
	
	public DtProfesorExt (String nickname, String nombre, String apellido, String email, String contrasenia, DtFecha fechaNacimiento, String nombreInstitucion, String descripcion, String biografia, String link, Map<String,Set<String>> actxClase, byte[] imagen) {
		super(nickname, nombre, apellido, email, contrasenia, fechaNacimiento, nombreInstitucion, descripcion, biografia, link, imagen); 
		x = actxClase;
	}
	
	public Set<String> getActividadesDepAsociadas(){
		return x.keySet();
	}
	
	public Set<String> getClasesDictadas(){
		Set<String> y = new HashSet<>();
		for(Entry<String, Set<String>> q: x.entrySet())
			y.addAll(q.getValue());
		return y;
	}
	
	public Map<String,Set<String>> getClasesxActividades(){
		return x;
	}
}
