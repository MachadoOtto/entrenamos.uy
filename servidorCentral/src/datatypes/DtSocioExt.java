package datatypes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class DtSocioExt extends DtSocio {
	
	private Map<String,Set<String>> x;
	
	public DtSocioExt(String nickname, String nombre, String apellido, String email, String contrasenia, DtFecha fechaNacimiento, Map<String,Set<String>> clases, byte[] imagen){
		super(nickname, nombre, apellido, email, contrasenia, fechaNacimiento, imagen);
		this.x = clases;
	}
	
	public Map<String,Set<String>> getAguadeUwu(){
		return x;
	}
	
	public Set<String> getClases(){
		Set<String> y = new HashSet<>();
		for(Entry<String, Set<String>> q: x.entrySet())
			y.addAll(q.getValue());
		return y;
	}
	
}
