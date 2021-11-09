
package webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import net.java.dev.jaxb.array.StringArray;


/**
 * <p>Java class for dtProfesorWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtProfesorWS">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webServices/}dtUsuarioWS">
 *       &lt;sequence>
 *         &lt;element name="historalActDepIngresadasNombre" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="historalActDepIngresadasEstado" type="{http://webServices/}tEstadoWS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actDepAsociadasHead" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actDepAsociadasData" type="{http://jaxb.dev.java.net/array}stringArray" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nombreInstitucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="biografia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valoracion" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtProfesorWS", propOrder = {
    "historalActDepIngresadasNombre",
    "historalActDepIngresadasEstado",
    "actDepAsociadasHead",
    "actDepAsociadasData",
    "nombreInstitucion",
    "descripcion",
    "biografia",
    "link",
    "valoracion"
})
public class DtProfesorWS
    extends DtUsuarioWS
{

    @XmlElement(nillable = true)
    protected List<String> historalActDepIngresadasNombre;
    @XmlElement(nillable = true)
    protected List<TEstadoWS> historalActDepIngresadasEstado;
    @XmlElement(nillable = true)
    protected List<String> actDepAsociadasHead;
    @XmlElement(nillable = true)
    protected List<StringArray> actDepAsociadasData;
    protected String nombreInstitucion;
    protected String descripcion;
    protected String biografia;
    protected String link;
    protected float valoracion;

    /**
     * Gets the value of the historalActDepIngresadasNombre property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the historalActDepIngresadasNombre property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHistoralActDepIngresadasNombre().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getHistoralActDepIngresadasNombre() {
        if (historalActDepIngresadasNombre == null) {
            historalActDepIngresadasNombre = new ArrayList<String>();
        }
        return this.historalActDepIngresadasNombre;
    }

    /**
     * Gets the value of the historalActDepIngresadasEstado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the historalActDepIngresadasEstado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHistoralActDepIngresadasEstado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TEstadoWS }
     * 
     * 
     */
    public List<TEstadoWS> getHistoralActDepIngresadasEstado() {
        if (historalActDepIngresadasEstado == null) {
            historalActDepIngresadasEstado = new ArrayList<TEstadoWS>();
        }
        return this.historalActDepIngresadasEstado;
    }

    /**
     * Gets the value of the actDepAsociadasHead property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actDepAsociadasHead property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActDepAsociadasHead().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getActDepAsociadasHead() {
        if (actDepAsociadasHead == null) {
            actDepAsociadasHead = new ArrayList<String>();
        }
        return this.actDepAsociadasHead;
    }

    /**
     * Gets the value of the actDepAsociadasData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actDepAsociadasData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActDepAsociadasData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StringArray }
     * 
     * 
     */
    public List<StringArray> getActDepAsociadasData() {
        if (actDepAsociadasData == null) {
            actDepAsociadasData = new ArrayList<StringArray>();
        }
        return this.actDepAsociadasData;
    }

    /**
     * Gets the value of the nombreInstitucion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    /**
     * Sets the value of the nombreInstitucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreInstitucion(String value) {
        this.nombreInstitucion = value;
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
     * Gets the value of the biografia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * Sets the value of the biografia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBiografia(String value) {
        this.biografia = value;
    }

    /**
     * Gets the value of the link property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLink(String value) {
        this.link = value;
    }

    /**
     * Gets the value of the valoracion property.
     * 
     */
    public float getValoracion() {
        return valoracion;
    }

    /**
     * Sets the value of the valoracion property.
     * 
     */
    public void setValoracion(float value) {
        this.valoracion = value;
    }

}
