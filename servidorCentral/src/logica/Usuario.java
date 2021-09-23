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
import java.util.Set;

import datatypes.DtFecha;
import datatypes.DtUsuario;

public abstract class Usuario {

    protected String nickname, nombre, apellido, correo, contrasenia;
    
    protected DtFecha fechaNacimiento;
    
    protected byte[] imagen;
    
    protected Map<String,Usuario> seguidos;
    protected Map<String,Usuario> seguidores;

    public Usuario(String nick, String nombre, String apellido, String correo, String contrasenia, DtFecha fecha, byte[] imagen) {
        this.setNickname(nick);
    	this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.contrasenia = contrasenia;
        this.setFecha(fecha);
        this.setImagen(imagen);
        seguidos = new HashMap<>();
        seguidores = new HashMap<>();
    }

    public Usuario(String nick, String nombre, String apellido, String correo, String contrasenia, DtFecha fecha, byte[] imagen,Map<String,Usuario> following,Map<String,Usuario> followers){
        this.setNickname(nick);
    	this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.contrasenia = contrasenia;
        this.setFecha(fecha);
        this.setImagen(imagen);
        seguidos = following;
        seguidores = followers;
    }
    
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
    
    public Map<String,Usuario> getSeguidos() {
    	return seguidos;
    }
    
    public Map<String,Usuario> getSeguidores() {
    	return seguidores;
    }
    
    public boolean sigue(String x) {
    	return seguidos.containsKey(x);
    }
    
    public boolean esSeguido(String x) {
    	return seguidores.containsKey(x);
    }
    
    public abstract boolean esSocio();

    public void editarDatos(DtUsuario datos) {
    	this.setNombre(datos.getNombre());
    	this.setApellido(datos.getApellido());
    	this.setFecha(datos.getFechaNacimiento());
    }
    
    public void agregarSeguidor(Usuario u) {
    	seguidores.put(u.getNickname(), u);
    }
    
    public void agregarSeguido(Usuario u) {
    	seguidos.put(u.getNickname(), u);
    }
    
    public void removerSeguidor(Usuario u) {
    	seguidores.remove(u.getNickname());
    }
    
    public void removerSeguido(Usuario u) {
    	seguidores.remove(u.getNickname());
    }
    
}