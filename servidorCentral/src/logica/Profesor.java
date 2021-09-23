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

import datatypes.DtProfesorExt;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class Profesor extends Usuario {
	
	private String descripcion, biografia, website;
	private Institucion instituto;
	private Map<String, Clase> misClases;
	
	/* No se usa constructor
	public Profesor(String nick, String nombre, String apellido, String mail, DtFecha fecha, String descripcion, String biografia, String website) {
		super(nick, nombre, apellido, mail, fecha);
		this.setDescripcion(descripcion);
		this.setBiografia(biografia);
		this.setWebsite(website);
		instituto = null;
		misClases = new HashMap<>();
	} */
	
	public Profesor(DtProfesor datos) {
		super(datos.getNickname(), datos.getNombre(), datos.getApellido(), datos.getEmail(), datos.getContrasenia(), datos.getFechaNacimiento(), datos.getImagen());
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
		DtProfesor datos = new DtProfesor(nickname,nombre,apellido,correo,contrasenia,fechaNacimiento,instituto.getNombre(),descripcion,biografia,website,imagen);
		return datos;
	}
	
    public DtProfesorExt getDtExt() {
    	Set<String> clasesDictadas = new HashSet<>(misClases.keySet());
    	Map<String,Set<String>> x = new HashMap<>();
    	for(String aa: instituto.getMiTrabajo(this)) {
    		Set<String> y = new HashSet<>();
    		x.put(aa,y);
    		for(String c: clasesDictadas) {
    			if(misClases.get(c).tieneActividadDeportiva(aa)) {
    				y.add(c);
    			}
    		}
    	}
    	DtProfesorExt datosExt = new DtProfesorExt(nickname,nombre,apellido,correo,contrasenia,fechaNacimiento,instituto.getNombre(),
    			descripcion,biografia,website,x,imagen,this.getSeguidos().keySet(),this.getSeguidores().keySet());
    	return datosExt;
    }
    
    public void editarDatos(DtProfesor datos) {
    	super.editarDatos(datos);
    	this.setDescripcion(datos.getDescripcion());
    	this.setBiografia(datos.getBiografia());
    	this.setWebsite(datos.getLink());
    }
}