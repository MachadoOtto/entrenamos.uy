package datatypes;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;


import java.util.Set;

public class DtProfesorExt extends DtUsuarioExt{

	private Map<String,  TEstado> historalActDepIngresadas;
	private Map<String,  Set<String>> actDepAsociadas;
	private String nombreInstitucion,  descripcion,  biografia,  link;
	private float valoracion;
	
	public DtProfesorExt(String nickname,  String nombre,  String apellido,  String email,  String contrasenia, 
						  DtFecha fechaNacimiento,  String nombreInstitucion,  String descripcion,  String biografia, 
						  String link,  Map<String,  Set<String>> actxClase,  byte[] imagen, Set<String> seguidosNickname, 
						  Set<String> seguidoresNickname,  Map<String,  TEstado> actividades, float valoracion) {
		super(nickname,  nombre,  apellido,  email,  contrasenia,  fechaNacimiento,  imagen,  seguidosNickname,  seguidoresNickname); 
		actDepAsociadas = actxClase;
		this.nombreInstitucion = nombreInstitucion;
		this.descripcion = descripcion;
		this.biografia = biografia;
		this.link = link;
		this.historalActDepIngresadas = actividades;
		this.valoracion = valoracion;
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
		Set<String> res = new HashSet<>();
		for (Entry<String,  Set<String>> q: actDepAsociadas.entrySet())
			res.addAll(q.getValue());
		return res;
	}
	
	public Map<String,  Set<String>> getClasesxActividades(){
		return actDepAsociadas;
	}
	
	public Set<String> getActividadesIngresadas(){
		return historalActDepIngresadas.keySet();
	}
	public Map<String,  TEstado> getHistoralActDepIngresadas(){
		return historalActDepIngresadas;
	}
	public float getValoracion() {
		return valoracion;
	}
	public DtProfesor downgrade() {
		return new DtProfesor(this.getNickname(), this.getNombre(), this.getApellido(), this.getEmail(),
				this.getContrasenia(), this.getFechaNacimiento(), this.getNombreInstitucion(), this.getDescripcion(),
				this.getBiografia(), this.getLink(), this.getImagen(), this.getValoracion());
	}
}
