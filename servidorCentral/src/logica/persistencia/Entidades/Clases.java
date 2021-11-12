package logica.persistencia.Entidades;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Entity implementation class for Entity: Clases
 *
 */


@Entity
public class Clases implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Calendar fechaInicio;
    private Calendar horaInicio; //ups
    private Integer sociosMinimos;
    private Integer sociosMaximos;
    private String url;
    private Calendar fechaAlta;
    private Long idActividad;

    //private tipoUsuario tipo

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }
    
    public Integer getSociosMaximos() {
        return sociosMaximos;
    }

    public void setSociosMaximos(Integer sociosMaximos) {
        this.sociosMaximos = sociosMaximos;
    }
    
    public Integer getSociosMinimos() {
        return sociosMaximos;
    }

    public void setSociosMinimos(Integer sociosMinimos) {
        this.sociosMinimos = sociosMinimos;
    }
    
    public String getUrl() {
        return url;
    }

    public void getUrl(String url) {
        this.url = url;
    }
    
    public Calendar getFechaAlta() {
        return fechaInicio;
    }

    public void setFechaAlta(Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad =idActividad;
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
        if (!(object instanceof Clases)) {
            return false;
        }
        Clases other = (Clases) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Clases[id=" + id +
        		", " + fechaInicio +
        		", " + horaInicio +
        		", " + sociosMinimos +
        		", " + sociosMaximos +
                ", " + url +
                ", " + fechaAlta +
                ", " + idActividad +
                "]";
    }

}
