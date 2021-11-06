package datatypes;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

public class DtUsuarioExt extends DtUsuario{
	private Set<String> seguidosNickname;
	private Set<String> seguidoresNickname;
	
	public DtUsuarioExt(String nickname,  String nombre,  String apellido,  String email,  String contrasenia,  DtFecha fechaNacimiento,  byte[] imagen,  Set<String> seguidosNickname,  Set<String> seguidoresNickname) {
		super(nickname,  nombre,  apellido,  email,  contrasenia,  fechaNacimiento,  imagen);
		this.seguidosNickname = seguidosNickname;
		this.seguidoresNickname = seguidoresNickname;
	}
	public Set<String> getSeguidosNickname() {
    	return seguidosNickname;
    }

    
    public Set<String> getSeguidoresNickname() {
    	return seguidoresNickname;
    }

}
