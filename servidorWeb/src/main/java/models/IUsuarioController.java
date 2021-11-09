/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package models;

import java.util.Set;

import datatypes.DtFecha;
import datatypes.DtUsuario;
import datatypes.DtUsuarioExt;
import excepciones.ClaseException;
import excepciones.CuponeraNoExisteException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;


public interface IUsuarioController {
	
	public Set<String> obtenerInstituciones();

	public Set<String> obtenerUsuarios();
	
	public int ingresarDatosUsuario(DtUsuario datoUser) throws InstitucionException;
	
	public DtUsuarioExt seleccionarUsuario(String userNick) throws UsuarioNoExisteException;
	public DtUsuarioExt seleccionarUsuarioEmail(String userEmail) throws UsuarioNoExisteException;
	
	public void editarDatosBasicos(String userNick,  DtUsuario datoUser) throws UsuarioNoExisteException;
	
	public void seguir(String seguidor,  String seguido) throws UsuarioNoExisteException ;
	
	public void dejarDeSeguir(String seguidor,  String seguido) throws UsuarioNoExisteException ;

	public void comprarCuponera(String cuponera,  String socio,  DtFecha fechaCompra) throws UsuarioNoExisteException,  CuponeraNoExisteException;
	
	public boolean verificarIdentidadEmail(String email,  String pass);
	public boolean verificarIdentidadNickname(String nick,  String pass);
	
	public void favoritearActividad(String nick, String ins, String actDep) throws UsuarioNoExisteException, InstitucionException;
	public void valorarProfesor(String nickSocio, String ins, String actDep, String cla, int valor) throws UsuarioNoExisteException, ClaseException, InstitucionException;
}
