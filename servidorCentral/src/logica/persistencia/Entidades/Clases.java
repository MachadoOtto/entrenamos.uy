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
import javax.persistence.OneToMany;


/**
 * Entity implementation class for Entity: Clases
 *
 */


@Entity
public class Clases implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "FECHA_INICIO")
    private Calendar fechaInicio;
    @Column(name = "HORA_INICIO")
    private Calendar horaInicio;
    @Column(name = "SOCIOS_MINIMOS")
    private Integer sociosMinimos;
    @Column(name = "SOCIOS_MAXIMOS")
    private Integer sociosMaximos;
    @Column(name = "URL")
    private String url;
    @Column(name = "FECHA_ALTA")
    private Calendar fechaAlta;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID")
    private ActividadesDeportivas actividadDeportiva;

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
                ", " + "mm" +
                "]";
    }

}
