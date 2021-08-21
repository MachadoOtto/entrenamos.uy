/* Taller de Programacion 2021 - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package logica;
import datatypes.DtProfesor;
import datatypes.DtFecha;
import datatypes.DtProfesorExt;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class Profesor extends Usuario {
	
	private String descripcion, biografia, website;
	
	private Institucion instituto;
	
	private Map<String, Clase> misClases;
	//Guille (...)
//	public Profesor() {
//		super();
//		this.setDescripcion(new String());
//		this.setBiografia(new String());
//		this.setWebsite(new String());
//		instituto = null;
//		misClases = new HashMap<>();
//	}
	
	public Profesor(String nick, String nombre, String apellido, String mail, DtFecha fecha, String descripcion, String biografia, String website) {
		super(nick, nombre, apellido, mail, fecha);
		this.setDescripcion(descripcion);
		this.setBiografia(biografia);
		this.setWebsite(website);
		instituto = null;
		misClases = new HashMap<>();
	}
	
	public Profesor(DtProfesor datos) {
		super(datos.getNickname(), datos.getNombre(), datos.getApellido(), datos.getEmail(), datos.getFechaNacimiento());
		this.setDescripcion(datos.getDescripcion());
		this.setBiografia(datos.getBiografia());
		this.setWebsite(datos.getLink());
		instituto = null;
		misClases = new HashMap<>();
	}
	
	public boolean esSocio() {
		return false;
	}
	
	private void setDescripcion(String desc) {
		this.descripcion = desc;
	}
	
	private void setBiografia(String bio) {
		this.biografia = bio;
	}
	
	private void setWebsite(String web) {
		this.website = web;
	}
	
	public void setInstitucion(Institucion insti) {
		this.instituto = insti;
	}
	
	// Devuelve true si la Clase 'cl' se añade con exito al conjunto de Clases asociadas al Profesor.
	public boolean addClase(Clase cl) {
		if (misClases.containsKey(cl.getNombre())) {
			return false;
		} else {
			misClases.put(cl.getNombre(), cl);
			return true;
		}
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getBiografia() {
		return biografia;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public Institucion getInstitucion() {
		return instituto;
	}
	
	public Map<String, Clase> getClasesDictadas() {
		return misClases;
	}
	
	public DtProfesor getDt() {
		// Guille Bruh: DtProfesor datos = new DtProfesor(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.getFecha(), this.getDescripcion(), this.getBiografia(), this.getWebsite());
		DtProfesor datos = new DtProfesor(nickname,nombre,apellido,correo,fechaNacimiento,instituto.getNombre(),descripcion,biografia,website);
		return datos;
	}
	
    public DtProfesorExt getDtExt() {
    	//DtProfesor datos = this.getDt();
    	Set<String> clasesDictadas = misClases.keySet();
    	Set<String> actDeportivas = instituto.getMiTrabajo(this);
    	// Ya quisieras DtProfesorExt datosExt = new DtProfesorExt(datos, clasesDictadas, actDeportivas);
    	DtProfesorExt datosExt = new DtProfesorExt(nickname,nombre,apellido,correo,fechaNacimiento,instituto.getNombre(),descripcion,biografia,website,clasesDictadas, actDeportivas);
    	return datosExt;
    }
    
    public void editarDatos(DtProfesor datos) {
    	super.editarDatos(datos);
    	this.setDescripcion(datos.getDescripcion());
    	this.setBiografia(datos.getBiografia());
    	this.setWebsite(datos.getLink());
    }
}