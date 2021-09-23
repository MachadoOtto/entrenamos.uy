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
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import datatypes.DtFecha;
import datatypes.DtProfesor;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

import excepciones.UsuarioNoExisteException;

public class HandlerUsuario {
	
	private static HandlerUsuario instancia = null;
	private Logger log;
	private Map<String, Usuario> usuarios;
	
	private Set<String> correos;
	private Profesor adminProf;
	private Set<String> nicksProhibidos;
	
	private HandlerUsuario() {
		usuarios = new HashMap<>();
		correos = new HashSet<>();
		nicksProhibidos = new HashSet<>();
		nicksProhibidos.add("admin");
		nicksProhibidos.add("Admin");
		nicksProhibidos.add("Administrador");
		nicksProhibidos.add("administrador");
		nicksProhibidos.add("Vasílev");
		nicksProhibidos.add("Jesús");
		adminProf = new Profesor(new DtProfesor("Administrador", "Administrador", "Administrador","Administrador","Administrador",new DtFecha(),"Administrador","Administrador","Administrador","Administrador", null));
		log = Logger.getLogger(HandlerInstitucion.class.getName());
		log.setLevel(Level.INFO);
		Handler handler = new ConsoleHandler();
		log.addHandler(handler);
	}
	
	public static HandlerUsuario getInstance() {
		if (instancia == null) {
			instancia = new HandlerUsuario(); 
		}
		return instancia;
	}
	
	/* Retorna true si la accion de añadir el Usuario se realiza de forma exitosa, false en otro caso (ya existe un usuario
		con ese nickname o correo.*/
	public boolean addUser(Usuario user) {
		if (existeNick(user.getNickname()) || existeCorreo(user.getCorreo())) {
			return false;
		} else {
			usuarios.put(user.getNickname(), user);
			correos.add(user.getCorreo());
			log.info("Usuario "+user.getNombre()+"registered, total: "+usuarios.size());
			return true;
		}
	}
	
	public Usuario findUsuario(String userNick) throws UsuarioNoExisteException {
		if(userNick=="Administrador")
			//Devuelve al administrador(profesor)
			return adminProf;
		Usuario res = usuarios.get(userNick);
		if (res == null) {
			log.info("WARNING: Tried to get non existent user "+userNick);
			throw new UsuarioNoExisteException("Usuario no registrado en el sistema.");
		}
		return res;
	}
	public Usuario findUsuarioByEmail(String email) throws UsuarioNoExisteException {
		for(Entry<String, Usuario> x: usuarios.entrySet())
			if(x.getValue().getCorreo()==email)
				return x.getValue();
		throw new UsuarioNoExisteException("Usuario no registrado en el sistema.");
	}
	
	public boolean existeNick(String userNick) {
		return usuarios.containsKey(userNick) || nicksProhibidos.contains(userNick);
	}
	
	public boolean existeCorreo(String userCorreo) {
		return correos.contains(userCorreo);
	}
	
	public Set<String> getNicknameUsuarios() {
		return usuarios.keySet();
	}
	
	public Set<String> obtenerNicknameSocios() {
		Set<String> resultado = new HashSet<>();
		Collection<Usuario> userCollection = usuarios.values();
		Iterator<Usuario> itUser = userCollection.iterator();
		while (itUser.hasNext()) {
			Usuario userAux = itUser.next();
			if (userAux.esSocio()) {
				resultado.add(userAux.getNickname());
			}
		}
		return resultado;
	}
}
