package logica;

/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

public class Usuario {

    private String nickname, nombre, apellido, correo;
    
    private DtFecha fechaNacimiento;

    public Usuario() {
        this.setNickname(new String());
    	this.setNombre(new String());
        this.setApellido(new String());
        this.setCorreo(new String());
        this.setFecha(new DtFecha());
    }

    public Usuario(String nick, String nombre, String apellido, String correo, DtFecha fecha) {
        this.setNickname(nick);
    	this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setFecha(fecha);
    }
    
    public String getNickname() {
    	return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }
    
    public DtFecha getFecha() {
    	return fechaNacimiento;
    }

    /* Sirve para mostrar textualmente la información del usuario, por ejemplo en un ComboBox
     
    public String toString() {
        return getCedulaIdentidad() + " (" + getNombre() + " " + getApellido() + ")";
    }
    */
    
    private void setNickname(String nick) {
    	this.nickname = nick;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void setApellido(String apellido) {
        this.apellido = apellido;
    }

    private void setCorreo(String mail) {
        this.correo = mail;
    }
    
    private void setFecha(DtFecha fecha) {
    	this.fechaNacimiento = fecha;
    }
    
    public DtUsuario getDtExt() {
    	
    }

    public void editarDatos(DtUsuario datos) {
    	
    }
}