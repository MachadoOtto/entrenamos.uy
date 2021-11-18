package logica.persistencia.Entidades;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import datatypes.DtActividadDeportivaExt;
import datatypes.DtFecha;
import datatypes.TEstado;


/**
 * Entity implementation class for Entity: ActividadesDeportivas
 *
 */


@Entity
@Table(name = "ACTIVIDADES_DEPORTIVAS")
public class ActividadesDeportivas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NOMBRE",
    		nullable = false,
    		unique = true)
    private String nombre;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "DURACION")
    private Integer duracion;
    
    @Column(name = "COSTO")
    private Float costo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_ALTA")
    private Calendar fechaAlta;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_FINALIZACION")
    private Calendar fechaFinalizacion;
    
    @ManyToOne
    @JoinColumn(name = "ID_PROFESOR")
    private Profesores profesor;

    @OneToMany(mappedBy = "actividad", cascade=CascadeType.PERSIST)
    private Collection<Clases> clases;

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
    
    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
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
    
    public Profesores getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesores profesor) {
		this.profesor = profesor;
	}

	public Collection<Clases> getClases() {
		return clases;
	}

	public void setClases(Collection<Clases> clases) {
		this.clases = clases;
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
    	String fechaAlt = fechaAlta.get(Calendar.DAY_OF_MONTH) + "/" + fechaAlta.get(Calendar.MONTH) + 
    			"/" + fechaAlta.get(Calendar.YEAR);
    	String fechaFin = fechaFinalizacion.get(Calendar.DAY_OF_MONTH) + "/" + fechaFinalizacion.get(Calendar.MONTH) + 
    			"/" + fechaFinalizacion.get(Calendar.YEAR);
        return "ActividadesDeportivas[id=" + id +
        		", nombre = " + nombre +
        		", desripcion = " + descripcion +
        		", duracion = " + duracion +
        		", costo = " + costo +
                ", fechaAlt = " + fechaAlt +
                ", fechaFin = " + fechaFin +
                ", Profesor = " + profesor.getNickname() +
                ", Clases = " + clases.toString() +
                "]";
    }
    
    public DtActividadDeportivaExt toDtActividadDeportivaExt() {
    	Set<String> nombreClases = new HashSet<>();
    	for (Clases cl : clases) {
    		nombreClases.add(cl.getNombre());
    	}
    	DtActividadDeportivaExt res = new DtActividadDeportivaExt(nombre, descripcion, duracion, costo,
    			new DtFecha(fechaAlta), new HashSet<>(), nombreClases, new HashSet<>(), 
    			TEstado.finalizada, profesor.getNickname(), "", 0);
    	return res;
    }
}