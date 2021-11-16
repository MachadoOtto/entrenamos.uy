package logica.persistencia.Entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import datatypes.DtFecha;
import datatypes.DtSocioExt;
import datatypes.DtUsuarioExt;
import datatypes.TEstado;
import logica.persistencia.Datatypes.TipoUsuario;

import javax.persistence.DiscriminatorType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


/**
 * Entity implementation class for Entity: Usuarios
 *
 */

@MappedSuperclass
@Table(name = "USUARIOS")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIPO_USUARIO",
discriminatorType = DiscriminatorType.INTEGER)
public abstract class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    protected Long id;
    
    @Column(name = "NICKNAME",
    		nullable = false,
    		unique = true)
    protected String nickname;
    
    @Column(name = "EMAIL",
    		nullable = false,
    		unique = true)
    protected String email;
    
    @Column(name = "NOMBRE")
    protected String nombre;
    
    @Column(name = "APELLIDO")
    protected String apellido;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_NACIMIENTO")
    protected Calendar fechaNacimiento;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO")
    protected TipoUsuario tipoUsuario;
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object object);
    
    @Override
    public String toString() {
    	return "Usuarios[id=" + id +
        		", nickname =  " + nickname +
        		", email = " + email +
        		", nombre = " + nombre +
        		", apellido = " + apellido +
                ", fechaNacimiento = " + new DtFecha(fechaNacimiento) +
                "]";
    }
    
    @Override
    public abstract DtUsuarioExt toDtUsuarioExt();

}

