package datatypes;

public class DtUsuario {

	private String nickname, nombre, apellido, email;
	private DtFecha fechaNacimiento;
	
	public DtUsuario (String nickname, String nombre, String apellido, String email, DtFecha fechaNacimiento) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	
	public String getApellida() {
		return this.apellido;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public DtFecha getFechaNacimiento() {
		return this.fechaNacimiento;
	}
}
