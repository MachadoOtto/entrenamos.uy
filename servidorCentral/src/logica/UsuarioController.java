/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package logica;

import java.util.Set;
import datatypes.DtUsuario;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtProfesorExt;
import datatypes.DtSocioExt;

public class UsuarioController implements IUsuarioController {
	
	private static UsuarioController instancia = null;
	
	private UsuarioController() { }
	
	public static UsuarioController getInstance() {
		if (instancia == null) {
			instancia = new UsuarioController();
		}
		return instancia;
	}
	
	public Set<String> obtenerUsuarios() {
		HandlerUsuario hu = HandlerUsuario.getInstance();
		return hu.getNicknameUsuarios();		
	}

	public Set<String> obtenerInstituciones() {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		return hi.obtenerInstituciones();
	}
	
	/* ATENCION: Para el que venga a editar esto luego, tengan cuidado al editar lugares donde se usa el 'instanceof' o de la
	 * 	forma '((Subclase)Clase)'. Esto lo hago para que al crear el objeto nuevo y luego retornarlo en la forma de la clase 
	 * 	'padre', no se pierdan datos en ese Upcasting (por eso veran que en algunos casos uso el constructor de los 'hijos').*/
	
	// Retorna 0 si se logro ingresar/crear el Usuario de forma exitosa, retorna 1 en otro caso.
	public int ingresarDatosUsuario(DtUsuario datoUser) {
		HandlerUsuario hu = HandlerUsuario.getInstance();
		if (hu.existeNick(datoUser.getNickname()) || hu.existeCorreo(datoUser.getEmail())) {
			return 1;
		} else {
			if (datoUser instanceof DtSocio) {
				Socio newSocio = new Socio(((DtSocio)datoUser));
				hu.addUser(newSocio);
			} else {
				Profesor newProfesor = new Profesor(((DtProfesor)datoUser));
				hu.addUser(newProfesor);
				HandlerInstitucion hi = HandlerInstitucion.getInstance();
				Institucion instituto = hi.findInstitucion(((DtProfesor)datoUser).getNombreInstitucion());
				newProfesor.setInstitucion(instituto);
			}
			return 0;
		}
	}
	
	// Ver la nota ATENCION mas arriba.
	public DtUsuario seleccionarUsuario(String userNick) {
		HandlerUsuario hu = HandlerUsuario.getInstance();
		Usuario user = hu.findUsuario(userNick);
		if (user instanceof Socio) {
			DtSocioExt dtExt = ((Socio)user).getDtExt();
			return dtExt;
		} else {
			DtProfesorExt dtExt = ((Profesor)user).getDtExt();
			return dtExt;
		}
	}
	
	// Ver la nota ATENCION mas arriba.
	// Precaucion: Esta funcion no edita/ ni reemplaza la Institucion que tiene Profesor asignada.
	public void editarDatosBasicos(String userNick, DtUsuario datoUser) {
		HandlerUsuario hu = HandlerUsuario.getInstance();
		Usuario user = hu.findUsuario(userNick);
		if (user instanceof Profesor) {
			((Profesor)user).editarDatos(((DtProfesor)datoUser));
		} else {
			user.editarDatos(datoUser);
		}
	}
}
