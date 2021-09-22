package datatypes;

import java.util.Set;

public class DtSocio extends DtUsuario {

	public DtSocio(String nickname, String nombre, String apellido, String email, DtFecha fechaNacimiento) {
		super(nickname, nombre, apellido, email, fechaNacimiento);
	}
	
	public DtSocio(String nickname, String nombre, String apellido, String email, String contrasenia, DtFecha fechaNacimiento, byte[] imagen, 
			Set<String> seguidosNickname, Set<String> seguidosCorreo, Set<String> seguidoresNickname, Set<String> seguidoresCorreo) {
		super(nickname, nombre, apellido, email, contrasenia, fechaNacimiento, imagen, seguidosNickname, seguidosCorreo, seguidoresNickname, seguidoresCorreo);
	}
}
