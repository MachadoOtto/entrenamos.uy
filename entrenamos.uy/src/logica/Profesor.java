package logica;

/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

public class Profesor extends Usuario {
	private String descripcion, biografia, website;
	
	public Profesor() {
		super();
		this.setDescripcion(new String());
		this.setBiografia(new String());
		this.setWebsite(new String());
	}
	
	public Profesor(String nick, String nombre, String apellido, String mail, DtFecha fecha, String descripcion, 
				String biografia, String website) {
		super(nick, nombre, apellido, mail, fecha);
		this.setDescripcion(descripcion);
		this.setBiografia(biografia);
		this.setWebsite(website);
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
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getBiografia() {
		return biografia;
	}
	
	public String getWebsite() {
		return website;
	}
}