package com;

import java.io.Serializable;
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSocio;
    private Long idClase;
    
    private Date fechaRegistro;
    private Float costo;

    public Long getIdSocio() {
        return idSocio;
    }

    public void setId(Long idSocio) {
        this.idSocio = idSocio;
    }
    
    public Long getIdClase() {
        return idClase;
    }

    public void setIdClase(Long idClase) {
        this.idClase = idClase;
    }
    
    public String getFechaRegistro() {
        return nickname;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public Float getCosto() {
        return email;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    	/*	DOBLE CLAVE
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idSocio == null && other.idSocio != null) || (this.idSocio != null && !this.idSocio.equals(other.idSocio))) {
            return false;
        }
        return true;
    }
    */
    
    @Override
    public String toString() {
        return "Registros[idSocio=" + idSocio +
        		", " + idClase +
        		", " + email +
        		", " + fechaRegistro +
        		", " + costo +
                "]";
    }

}

