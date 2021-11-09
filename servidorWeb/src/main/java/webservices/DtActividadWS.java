
package webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtActividadWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtActividadWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clases" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cup" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="categorias" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duracionMinutos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="fechaRegistro" type="{http://webServices/}dtFechaWS" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://webServices/}tEstadoWS" minOccurs="0"/>
 *         &lt;element name="favoritos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtActividadWS", propOrder = {
    "clases",
    "cup",
    "categorias",
    "nombre",
    "descripcion",
    "creador",
    "imgName",
    "duracionMinutos",
    "costo",
    "fechaRegistro",
    "estado",
    "favoritos"
})
public class DtActividadWS {

    @XmlElement(nillable = true)
    protected List<String> clases;
    @XmlElement(nillable = true)
    protected List<String> cup;
    @XmlElement(nillable = true)
    protected List<String> categorias;
    protected String nombre;
    protected String descripcion;
    protected String creador;
    protected String imgName;
    protected int duracionMinutos;
    protected float costo;
    protected DtFechaWS fechaRegistro;
    protected TEstadoWS estado;

    protected int favoritos;

	/**
     * Gets the value of the clases property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clases property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClases().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getClases() {
        if (clases == null) {
            clases = new ArrayList<String>();
        }
        return this.clases;
    }

    /**
     * Gets the value of the cup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCup() {
        if (cup == null) {
            cup = new ArrayList<String>();
        }
        return this.cup;
    }

    /**
     * Gets the value of the categorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<String>();
        }
        return this.categorias;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the creador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreador() {
        return creador;
    }

    /**
     * Sets the value of the creador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreador(String value) {
        this.creador = value;
    }

    /**
     * Gets the value of the imgName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * Sets the value of the imgName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgName(String value) {
        this.imgName = value;
    }

    /**
     * Gets the value of the duracionMinutos property.
     * 
     */
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    /**
     * Sets the value of the duracionMinutos property.
     * 
     */
    public void setDuracionMinutos(int value) {
        this.duracionMinutos = value;
    }

    /**
     * Gets the value of the costo property.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Sets the value of the costo property.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Gets the value of the fechaRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link DtFechaWS }
     *     
     */
    public DtFechaWS getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Sets the value of the fechaRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtFechaWS }
     *     
     */
    public void setFechaRegistro(DtFechaWS value) {
        this.fechaRegistro = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link TEstadoWS }
     *     
     */
    public TEstadoWS getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link TEstadoWS }
     *     
     */
    public void setEstado(TEstadoWS value) {
        this.estado = value;
    }

	/**
     * Gets the value of the favoritos property.
     * 
     */
    public int getFavoritos() {
        return favoritos;
    }

	/**
     * Sets the value of the favoritos property.
     * 
     */
    public void setFavoritos(int value) {
        this.favoritos = value;
    }

}
