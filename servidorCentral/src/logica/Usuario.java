/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datatypes.DtFecha;
import datatypes.DtUsuario;

public abstract class Usuario {

    protected String nickname, nombre, apellido, correo, contrasenia;
    
    protected DtFecha fechaNacimiento;
    
    protected byte[] imagen;
    
    protected Map<String,Usuario> seguidosNickname;
    protected Map<String,Usuario> seguidoresNickname;
    protected Map<String,Usuario> seguidosCorreo;
    protected Map<String,Usuario> seguidoresCorreo;

    //guille : WHAT?
//    public Usuario() {
//        this.setNickname(new String());
//    	this.setNombre(new String());
//        this.setApellido(new String());
//        this.setCorreo(new String());
//        this.setFecha(new DtFecha());
//    }
    
    public Usuario(String nick, String nombre, String apellido, String correo, DtFecha fecha) {
        this.setNickname(nick);
    	this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setFecha(fecha);
        
        //datos no especificados
        this.contrasenia = "123";
        this.imagen = null;
    }

    public Usuario(String nick, String nombre, String apellido, String correo, String contrasenia, DtFecha fecha, byte[] imagen) {
        this.setNickname(nick);
    	this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.contrasenia = contrasenia;
        this.setFecha(fecha);
        this.setImagen(imagen);
        seguidosNickname = new HashMap<>();
        seguidoresNickname = new HashMap<>();
        seguidosCorreo = new HashMap<>();
        seguidoresCorreo = new HashMap<>();
    }
    
    /* Constructor no usado
    public Usuario(DtUsuario datos) {
    	this.setNickname(datos.getNickname());
    	this.setNombre(datos.getNombre());
        this.setApellido(datos.getApellido());
        this.setCorreo(datos.getEmail());
        this.setFecha(datos.getFechaNacimiento());
    } */
    
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
    
    private void setImagen(byte[] imagen) {
    	this.imagen = imagen;
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
    
    public String getContrasenia() {
    	return contrasenia;
    }
    
    public DtFecha getFecha() {
    	return fechaNacimiento;
    }
    
    public byte[] getImagen() {
    	return imagen;
    }
    
    /*public Map<String,Usuario> getSeguidosNickname() {
    	return seguidosNickname;
    }
    
    public Map<String,Usuario> getSeguidosCorreo() {
    	return seguidosCorreo;
    }
    
    public Map<String,Usuario> getSeguidoresNickname() {
    	return seguidoresNickname;
    }
    
    public Map<String,Usuario> getSeguidoresCorreo() {
    	return seguidoresCorreo;
    }*/
    
    public boolean sigueACorreo(String x) {
    	return seguidosCorreo.containsKey(x);
    }
    
    public boolean sigueANickname(String x) {
    	return seguidosNickname.containsKey(x);
    }
    
    public boolean esSeguidoPorCorreo(String x) {
    	return seguidoresCorreo.containsKey(x);
    }
    
    public boolean esSeguidoPorNickname(String x) {
    	return seguidoresNickname.containsKey(x);
    }
    
    public abstract boolean esSocio();

    public void editarDatos(DtUsuario datos) {
    	this.setNombre(datos.getNombre());
    	this.setApellido(datos.getApellido());
    	this.setFecha(datos.getFechaNacimiento());
    }
    
    public void agregarSeguidor(Usuario u) {
    	seguidoresNickname.put(u.getNickname(), u);
    	seguidoresCorreo.put(u.getCorreo(), u);
    }
    
    public void agregarSeguido(Usuario u) {
    	seguidosNickname.put(u.getNickname(), u);
    	seguidosCorreo.put(u.getCorreo(), u);
    }
    
    public void removerSeguidor(Usuario u) {
    	seguidoresNickname.remove(u.getNickname());
    	seguidoresCorreo.remove(u.getCorreo());
    }
    
    public void removerSeguido(Usuario u) {
    	seguidoresNickname.remove(u.getNickname());
    	seguidoresCorreo.remove(u.getCorreo());
    }
    
}