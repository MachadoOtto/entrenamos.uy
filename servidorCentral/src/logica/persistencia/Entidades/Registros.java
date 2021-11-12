package logica.persistencia.Entidades;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Entity implementation class for Entity: Registros
 *
 */


@Entity
public class Registros implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private RegistrosId registrosId;
    
    private Calendar fechaRegistro;
    private Float costo;

    public Long getIdSocio() {
        return registrosId.getIdSocio();
    }

    public void setId(Long idSocio) {
        registrosId.setIdSocio(idSocio);;
    }
    
    public Long getIdClase() {
        return registrosId.getIdClase();
    }

    public void setIdClase(Long idClase) {
        registrosId.setIdClase(idClase);
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
        hash += (registrosId.getIdSocio() != null ? registrosId.getIdSocio().hashCode() : 0); //ojo
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registros)) {
            return false;
        }
        Registros other = (Registros) object; //ojo
        if ((this.registrosId.getIdSocio() == null && other.registrosId.getIdSocio() != null) || (this.registrosId.getIdSocio() != null && !this.registrosId.getIdSocio().equals(other.registrosId.getIdSocio()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Registros[idSocio=" + registrosId.getIdSocio() +
        		", " + registrosId.getIdClase() +
        		", " + fechaRegistro +
        		", " + costo +
                "]";
    }
    
    @Embeddable
    private class RegistrosId {
    	private Long idSocio;
        private Long idClase;
        
		public Long getIdSocio() {
			return idSocio;
		}
		public void setIdSocio(Long idSocio) {
			this.idSocio = idSocio;
		}
		public Long getIdClase() {
			return idClase;
		}
		public void setIdClase(Long idClase) {
			this.idClase = idClase;
		}
    }
}


