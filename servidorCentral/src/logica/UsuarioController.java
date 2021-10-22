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
		HandlerUsuario hUsuario = HandlerUsuario.getInstance();
		return hUsuario.getNicknameUsuarios();		
	}

	public Set<String> obtenerInstituciones() {
		HandlerInstitucion handlerInstitucion = HandlerInstitucion.getInstance();
		return handlerInstitucion.obtenerInstituciones();
	}
	
	/* ATENCION: Para el que venga a editar esto luego, tengan cuidado al editar lugares donde se usa el 'instanceof' o de la
	 * 	forma '((Subclase)Clase)'. Esto lo hago para que al crear el objeto nuevo y luego retornarlo en la forma de la clase 
	 * 	'padre', no se pierdan datos en ese Upcasting (por eso veran que en algunos casos uso el constructor de los 'hijos').*/
	
	// Retorna 0 si se logro ingresar/crear el Usuario de forma exitosa, retorna 1 en otro caso.
	public int ingresarDatosUsuario(DtUsuario datoUser) throws InstitucionException {
		HandlerUsuario handlerUsuario = HandlerUsuario.getInstance();
		if (handlerUsuario.existeNick(datoUser.getNickname()) || handlerUsuario.existeCorreo(datoUser.getEmail())) {
			return 1;
		} else {
			if (datoUser instanceof DtSocio) {
				Socio newSocio = new Socio(((DtSocio)datoUser));
				handlerUsuario.addUser(newSocio);
			} else {
				Profesor newProfesor = new Profesor(((DtProfesor)datoUser));
				handlerUsuario.addUser(newProfesor);
				HandlerInstitucion handlerInstitucion = HandlerInstitucion.getInstance();
				Institucion instituto = handlerInstitucion.findInstitucion(((DtProfesor)datoUser).getNombreInstitucion());
				newProfesor.setInstitucion(instituto);
				instituto.addProfesor(newProfesor);
			}
			return 0;
		}
	}
	
	// Ver la nota ATENCION mas arriba.
	public DtUsuarioExt seleccionarUsuario(String userNick) throws UsuarioNoExisteException {
		HandlerUsuario handlerUsuario = HandlerUsuario.getInstance();
		Usuario user = handlerUsuario.findUsuario(userNick);
		if (user instanceof Socio) {
			DtSocioExt dtExt = ((Socio)user).getDtExt();
			return dtExt;
		} else {
			DtProfesorExt dtExt = ((Profesor)user).getDtExt();
			return dtExt;
		}
	}
	public DtUsuarioExt seleccionarUsuarioEmail(String userEmail) throws UsuarioNoExisteException{
		for(String nombreUsuariox: getHU().getNicknameUsuarios()) {
			try {
				DtUsuarioExt datoU = seleccionarUsuario(nombreUsuariox);
				if(datoU.getEmail().equals(userEmail))
					return datoU;
			}
			catch (Exception ignore) {}
		}
		throw new UsuarioNoExisteException(userEmail);
	}

	// Ver la nota ATENCION mas arriba.
	// Precaucion: Esta funcion no edita/ ni reemplaza la Institucion que tiene Profesor asignada.
	public void editarDatosBasicos(String userNick, DtUsuario datoUser) throws UsuarioNoExisteException {
		HandlerUsuario handlerUsuario = HandlerUsuario.getInstance();
		Usuario user = handlerUsuario.findUsuario(userNick);
		if (user instanceof Profesor) {
			((Profesor)user).editarDatos(((DtProfesor)datoUser));
		} else {
			user.editarDatos(datoUser);
		}
	}
	
	public void seguir(String seguidor, String seguido) throws UsuarioNoExisteException {
		HandlerUsuario handlerUsuario = HandlerUsuario.getInstance();
		Usuario seguidorU = handlerUsuario.findUsuario(seguidor);
		Usuario seguidoU = handlerUsuario.findUsuario(seguido);
		seguidorU.agregarSeguido(seguidoU);
		seguidoU.agregarSeguidor(seguidorU);
	}
	
	public void dejarDeSeguir(String user1, String user2) throws UsuarioNoExisteException {
		getHU().findUsuario(user1).removerSeguido(getHU().findUsuario(user2));
		getHU().findUsuario(user2).removerSeguidor(getHU().findUsuario(user1));
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
		ReciboCuponera reciboClase = new ReciboCuponera(fechaCompra, getHC().getCup(cuponera), ((Socio) getHU().findUsuario(socio)));
		((Socio) getHU().findUsuario(socio)).addReciboCuponera(reciboClase);
		getHC().getCup(cuponera).addRecibo(reciboClase);
		((Socio) getHU().findUsuario(socio)).addReciboCuponera(reciboClase);
	}
	
	private HandlerUsuario getHU() {
		return HandlerUsuario.getInstance();
	}
	private HandlerCuponera getHC() {
		return HandlerCuponera.getInstance();
	}

}
