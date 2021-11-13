package logica.persistencia.Entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * Entity implementation class for Entity: Socios
 *
 */


@Entity
@Table(name = "SOCIOS")
@PrimaryKeyJoinColumn(referencedColumnName = "ID_USUARIO")
public class Socios extends Usuarios {
    private static final long serialVersionUID = 1L;
    
    public Long getIdUsuario() {
        return getId();
    }

    public void setIdUsuario(Long idUsuario) {
        setId(idUsuario);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Socios)) {
            return false;
        }
        Socios other = (Socios) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }
}

