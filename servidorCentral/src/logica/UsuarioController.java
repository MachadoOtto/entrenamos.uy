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
import datatypes.DtUsuarioExt;
import datatypes.DtFecha;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtProfesorExt;
import datatypes.DtSocioExt;
import excepciones.CuponeraNoExisteException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;

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
	public int ingresarDatosUsuario(DtUsuario datoUser) throws InstitucionException {
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
				instituto.addProfesor(newProfesor);
			}
			return 0;
		}
	}
	
	// Ver la nota ATENCION mas arriba.
	public DtUsuarioExt seleccionarUsuario(String userNick) throws UsuarioNoExisteException {
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
	public DtUsuarioExt seleccionarUsuarioEmail(String userEmail) throws UsuarioNoExisteException{
		for(String x: getHU().getNicknameUsuarios()) {
			try {
				DtUsuarioExt y = seleccionarUsuario(x);
				if(y.getEmail().equals(userEmail))
					return y;
			}
			catch (Exception ignore) {}
		}
		throw new UsuarioNoExisteException(userEmail);
	}

	// Ver la nota ATENCION mas arriba.
	// Precaucion: Esta funcion no edita/ ni reemplaza la Institucion que tiene Profesor asignada.
	public void editarDatosBasicos(String userNick, DtUsuario datoUser) throws UsuarioNoExisteException {
		HandlerUsuario hu = HandlerUsuario.getInstance();
		Usuario user = hu.findUsuario(userNick);
		if (user instanceof Profesor) {
			((Profesor)user).editarDatos(((DtProfesor)datoUser));
		} else {
			user.editarDatos(datoUser);
		}
	}
	
	public void seguir(String seguidor, String seguido) throws UsuarioNoExisteException {
		HandlerUsuario hu = HandlerUsuario.getInstance();
		Usuario seguidorU = hu.findUsuario(seguidor);
		Usuario seguidoU = hu.findUsuario(seguido);
		seguidorU.agregarSeguido(seguidoU);
		seguidoU.agregarSeguidor(seguidorU);
	}
	
	public void dejarDeSeguir(String s1, String s2) throws UsuarioNoExisteException {
		getHU().findUsuario(s1).removerSeguido(getHU().findUsuario(s2));
		getHU().findUsuario(s2).removerSeguidor(getHU().findUsuario(s1));
	}
	
	
	public boolean verificarIdentidadNickname(String nick,String pass) {
		if(getHU().existeNick(nick)) {
			try {
				return getHU().findUsuario(nick).getContrasenia() == pass;
			} catch (UsuarioNoExisteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean verificarIdentidadEmail(String email, String pass) {
		if(getHU().existeCorreo(email)) {
			try {
				return getHU().findUsuarioByEmail(email).getContrasenia() == pass;
			} catch (UsuarioNoExisteException e) {
				e.printStackTrace();
			}
		}
		return false;	
	}
	
	public void comprarCuponera(String cuponera, String socio,DtFecha fechaCompra) throws UsuarioNoExisteException,CuponeraNoExisteException{
		ReciboCuponera rc = new ReciboCuponera(fechaCompra, getHC().getCup(cuponera), ((Socio) getHU().findUsuario(socio)));
		((Socio) getHU().findUsuario(socio)).addReciboCuponera(rc);
		getHC().getCup(cuponera).addRecibo(rc);
	}
	
	private HandlerUsuario getHU() {
		return HandlerUsuario.getInstance();
	}
	private HandlerCuponera getHC() {
		return HandlerCuponera.getInstance();
	}

}
