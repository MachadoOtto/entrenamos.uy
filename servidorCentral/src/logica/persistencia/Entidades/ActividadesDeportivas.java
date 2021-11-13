package logica.persistencia.Entidades;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Entity implementation class for Entity: ActividadesDeportivas
 *
 */


@Entity
public class ActividadesDeportivas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "DURACION")
    private Float duracion;
    @Column(name = "COSTO")
    private Float costo;
    @Column(name = "FECHA_ALTA")
    private Calendar fechaAlta;
    @Column(name = "FECHA_FINALIZACION")
    private Calendar fechaFinalizacion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID")
    private Profesores profesor;

    //private tipoUsuario tipo

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Float getDuracion() {
        return duracion;
    }

    public void setDuracion(Float duracion) {
        this.duracion = duracion;
    }
    
    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }
    
    public Calendar getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
    public Calendar getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Calendar fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadesDeportivas)) {
            return false;
        }
        ActividadesDeportivas other = (ActividadesDeportivas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "ActividadesDeportivas[id=" + id +
        		", " + nombre +
        		", " + descripcion +
        		", " + duracion +
        		", " + costo +
                ", " + fechaAlta +
                ", " + fechaFinalizacion +
                ", " + "nadadad" +
                "]";
    }

}

