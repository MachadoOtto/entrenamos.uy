package datatypes;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DtProfesorExt extends DtUsuarioExt{

	private Map<String,TEstado> historalActDepIngresadas;
	private Map<String,Set<String>> actDepAsociadas;
	private String nombreInstitucion, descripcion, biografia, link;
	
	public DtProfesorExt (String nickname, String nombre, String apellido, String email, String contrasenia,
						  DtFecha fechaNacimiento, String nombreInstitucion, String descripcion, String biografia,
						  String link, Map<String,Set<String>> actxClase, byte[] imagen, Set<String> seguidosNickname,
						  Set<String> seguidoresNickname,Map<String,TEstado> h) {
		super(nickname, nombre, apellido, email, contrasenia, fechaNacimiento,imagen, seguidosNickname, seguidoresNickname); 
		actDepAsociadas = actxClase;
		this.nombreInstitucion = nombreInstitucion;
		this.descripcion = descripcion;
		this.biografia = biografia;
		this.link = link;
		this.historalActDepIngresadas = h;
	}
	public String getNombreInstitucion() {
		return this.nombreInstitucion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getBiografia() {
		return this.biografia;
	}
	
	public String getLink() {
		return this.link;
	}
	public Set<String> getActividadesDepAsociadas(){
		return actDepAsociadas.keySet();
	}
	
	public Set<String> getClasesDictadas(){
		Set<String> y = new HashSet<>();
		for(Entry<String, Set<String>> q: actDepAsociadas.entrySet())
			y.addAll(q.getValue());
		return y;
	}
	
	public Map<String,Set<String>> getClasesxActividades(){
		return actDepAsociadas;
	}
}
