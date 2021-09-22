package datatypes;

import java.util.Set;

public class DtProfesor extends DtUsuario{

	private String nombreInstitucion, descripcion, biografia, link;
	
	public DtProfesor(String nickname, String nombre, String apellido, String email, DtFecha fechaNacimiento, String nombreInstitucion, String descripcion, String biografia, String link) {
		super(nickname, nombre, apellido, email, fechaNacimiento);
		this.nombreInstitucion = nombreInstitucion;
		this.descripcion = descripcion;
		this.biografia = biografia;
		this.link = link;
	}
	
	public DtProfesor(String nickname, String nombre, String apellido, String email, String contrasenia, DtFecha fechaNacimiento, String nombreInstitucion, String descripcion, String biografia, String link, byte[] imagen, Set<String> seguidosNickname, Set<String> seguidosCorreo, Set<String> seguidoresNickname, Set<String> seguidoresCorreo) {
		super(nickname, nombre, apellido, email, contrasenia, fechaNacimiento, imagen,seguidosNickname,seguidosCorreo,seguidoresNickname,seguidoresCorreo);
		this.nombreInstitucion = nombreInstitucion;
		this.descripcion = descripcion;
		this.biografia = biografia;
		this.link = link;
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
}
