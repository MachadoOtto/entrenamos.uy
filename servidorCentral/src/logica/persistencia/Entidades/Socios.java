package logica.persistencia.Entidades;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import datatypes.DtFecha;
import datatypes.DtPremio;
import datatypes.DtSocioExt;
import datatypes.DtUsuarioExt;


/**
 * Entity implementation class for Entity: Socios
 *
 */


@Entity
@Table(name = "SOCIOS")
@PrimaryKeyJoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
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
        hash += (getIdUsuario() != null ? getIdUsuario().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Socios)) {
            return false;
        }
        Socios other = (Socios) object;
        if ((this.getIdUsuario() == null && other.getIdUsuario() != null) || (this.getIdUsuario() != null && !this.getIdUsuario().equals(other.getIdUsuario()))) {
            return false;
        }
        return true;
    }

	public DtUsuarioExt toDtUsuarioExt() {
		DtSocioExt res = new DtSocioExt(nickname, nombre, apellido, email, "", new DtFecha(fechaNacimiento), 
				new HashMap<String, Set<String>>(), null, new HashSet<>(), new HashSet<>(),
				new HashSet<>(), new HashSet<>(), new HashMap<String, DtPremio>(),
				new HashMap<String, Set<String>>());
		return res;
	}

}

