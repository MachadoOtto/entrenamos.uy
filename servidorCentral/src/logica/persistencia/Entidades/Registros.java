package logica.persistencia.Entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * Entity implementation class for Entity: Registros
 *
 */


@Entity
public class Registros implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private RegistrosId id;
    
    @Embeddable
    private class RegistrosId {
    	@ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "ID_USUARIO")
        private Socios socio;
        
    	@ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "ID")
        private Clases clase;
        
		public Long getIdSocio() { //AAA
			return socio.getId();
		}
		public void setIdSocio(Long idSocio) {
			socio.setId(idSocio);
		}
    }

    @Column(name = "FECHA_REGISTRO")
    private Calendar fechaRegistro;
    @Column(name = "COSTO")
    private Float costo;
    
    public Long getIdSocio() {
        return id.getIdSocio();
    }

    public void setId(Long idSocio) {
        id.setIdSocio(idSocio);;
    }
    
    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getIdSocio() != null ? this.getIdSocio().hashCode() : 0); //ojo
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registros)) {
            return false;
        }
        Registros other = (Registros) object; //ojo
        if ((this.id.getIdSocio() == null && other.id.getIdSocio() != null) || (this.id.getIdSocio() != null && !this.id.getIdSocio().equals(other.id.getIdSocio()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Registros[idSocio=" + id.getIdSocio() +
        		", " + "nadadada" +
        		", " + fechaRegistro +
        		", " + costo +
                "]";
    }
    
}


