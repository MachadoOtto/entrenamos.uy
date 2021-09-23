package datatypes;

public class DtSocio extends DtUsuario {

	public DtSocio(String nickname, String nombre, String apellido, String email, String contrasenia, DtFecha fechaNacimiento, byte[] imagen) {
		super(nickname, nombre, apellido, email, contrasenia, fechaNacimiento, imagen);
	}
}
