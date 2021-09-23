package datatypes;

import java.util.Set;

public class DtProfesor extends DtUsuario{

	private String nombreInstitucion, descripcion, biografia, link;
		
	public DtProfesor(String nickname, String nombre, String apellido, String email, String contrasenia, DtFecha fechaNacimiento, String nombreInstitucion, String descripcion, String biografia, String link, byte[] imagen) {
		super(nickname, nombre, apellido, email, contrasenia, fechaNacimiento, imagen);
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
