package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


public class DtUsuario {

	private String nickname,   nombre,   apellido,   email,   contrasenia;
	private DtFecha fechaNacimiento;
	private byte[] imagen;
	
	public DtUsuario(String nickname,   String nombre,   String apellido,   String email,   String contrasenia,  DtFecha fechaNacimiento,  byte[] imagen) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.contrasenia = contrasenia;
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getContrasenia() {
		return this.contrasenia;
	}
	
	public DtFecha getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	
	public byte[] getImagen() {
		return imagen;
	}
}
