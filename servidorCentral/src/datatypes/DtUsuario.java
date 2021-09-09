package datatypes;

public class DtUsuario {

	private String nickname, nombre, apellido, email, contrasenia;
	private DtFecha fechaNacimiento;
	private byte[] imagen;
	
	public DtUsuario (String nickname, String nombre, String apellido, String email, DtFecha fechaNacimiento) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		
		//Datos no especificados
		this.contrasenia = "123";
		this.imagen = null;
	}

	
	public DtUsuario (String nickname, String nombre, String apellido, String email, String contrasenia, DtFecha fechaNacimiento, byte[] imagen) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasenia = contrasenia;
		this.fechaNacimiento = fechaNacimiento;
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
