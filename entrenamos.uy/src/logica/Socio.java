package logica;

/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

public class Socio extends Usuario {
	public Socio() {
		super();
	}
	
	public Socio(String nickname, String nombre, String apellido, String correo, DtFecha fecha) {
		super(nickname, nombre, apellido, correo, fecha);
	}
	
	public int inscribirSocio(ActividadDeportiva actDep, Clase cl, TReg tipoRegistro) {
		
	}
}