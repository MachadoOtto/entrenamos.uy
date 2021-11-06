
package webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtClaseWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtClaseWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alumnos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="calificacionesData" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="calificacionesHead" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="correoProfesor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaClase" type="{http://webServices/}dtFechaWS" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://webServices/}dtFechaWS" minOccurs="0"/>
 *         &lt;element name="imgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxSocios" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minSocios" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nicknameProfesor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="premio" type="{http://webServices/}dtPremioWS" minOccurs="0"/>
 *         &lt;element name="soloNickAlumnos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="urlVideo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlwebsite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtClaseWS", propOrder = {
    "alumnos",
    "calificacionesData",
    "calificacionesHead",
    "correoProfesor",
    "fechaClase",
    "fechaRegistro",
    "imgName",
    "maxSocios",
    "minSocios",
    "nicknameProfesor",
    "nombre",
    "premio",
    "soloNickAlumnos",
    "urlVideo",
    "urlwebsite"
})
public class DtClaseWS {

    @XmlElement(nillable = true)
    protected List<String> alumnos;
    @XmlElement(nillable = true)
    protected List<Integer> calificacionesData;
    @XmlElement(nillable = true)
    protected List<String> calificacionesHead;
    protected String correoProfesor;
    protected DtFechaWS fechaClase;
    protected DtFechaWS fechaRegistro;
    protected String imgName;
    protected int maxSocios;
    protected int minSocios;
    protected String nicknameProfesor;
    protected String nombre;
    protected DtPremioWS premio;
    @XmlElement(nillable = true)
    protected List<String> soloNickAlumnos;
    protected String urlVideo;
    protected String urlwebsite;

    /**
     * Gets the value of the alumnos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alumnos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlumnos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAlumnos() {
        if (alumnos == null) {
            alumnos = new ArrayList<String>();
        }
        return this.alumnos;
    }

    /**
     * Gets the value of the calificacionesData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calificacionesData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalificacionesData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getCalificacionesData() {
        if (calificacionesData == null) {
            calificacionesData = new ArrayList<Integer>();
        }
        return this.calificacionesData;
    }

    /**
     * Gets the value of the calificacionesHead property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calificacionesHead property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalificacionesHead().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCalificacionesHead() {
        if (calificacionesHead == null) {
            calificacionesHead = new ArrayList<String>();
        }
        return this.calificacionesHead;
    }

    /**
     * Gets the value of the correoProfesor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreoProfesor() {
        return correoProfesor;
    }

    /**
     * Sets the value of the correoProfesor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreoProfesor(String value) {
        this.correoProfesor = value;
    }

    /**
     * Gets the value of the fechaClase property.
     * 
     * @return
     *     possible object is
     *     {@link DtFechaWS }
     *     
     */
    public DtFechaWS getFechaClase() {
        return fechaClase;
    }

    /**
     * Sets the value of the fechaClase property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtFechaWS }
     *     
     */
    public void setFechaClase(DtFechaWS value) {
        this.fechaClase = value;
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
     * Gets the value of the maxSocios property.
     * 
     */
    public int getMaxSocios() {
        return maxSocios;
    }

    /**
     * Sets the value of the maxSocios property.
     * 
     */
    public void setMaxSocios(int value) {
        this.maxSocios = value;
    }

    /**
     * Gets the value of the minSocios property.
     * 
     */
    public int getMinSocios() {
        return minSocios;
    }

    /**
     * Sets the value of the minSocios property.
     * 
     */
    public void setMinSocios(int value) {
        this.minSocios = value;
    }

    /**
     * Gets the value of the nicknameProfesor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknameProfesor() {
        return nicknameProfesor;
    }

    /**
     * Sets the value of the nicknameProfesor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknameProfesor(String value) {
        this.nicknameProfesor = value;
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
     * Gets the value of the premio property.
     * 
     * @return
     *     possible object is
     *     {@link DtPremioWS }
     *     
     */
    public DtPremioWS getPremio() {
        return premio;
    }

    /**
     * Sets the value of the premio property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPremioWS }
     *     
     */
    public void setPremio(DtPremioWS value) {
        this.premio = value;
    }

    /**
     * Gets the value of the soloNickAlumnos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the soloNickAlumnos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoloNickAlumnos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSoloNickAlumnos() {
        if (soloNickAlumnos == null) {
            soloNickAlumnos = new ArrayList<String>();
        }
        return this.soloNickAlumnos;
    }

    /**
     * Gets the value of the urlVideo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlVideo() {
        return urlVideo;
    }

    /**
     * Sets the value of the urlVideo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlVideo(String value) {
        this.urlVideo = value;
    }

    /**
     * Gets the value of the urlwebsite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlwebsite() {
        return urlwebsite;
    }

    /**
     * Sets the value of the urlwebsite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlwebsite(String value) {
        this.urlwebsite = value;
    }

}
