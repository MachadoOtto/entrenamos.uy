
package webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import net.java.dev.jaxb.array.StringArray;


/**
 * <p>Java class for dtSocioWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtSocioWS">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webServices/}dtUsuarioWS">
 *       &lt;sequence>
 *         &lt;element name="clasesDeActividadesHead" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clasesDeActividadesData" type="{http://jaxb.dev.java.net/array}stringArray" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clasesDeActividadesFinalizadasHead" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clasesDeActividadesFinalizadasData" type="{http://jaxb.dev.java.net/array}stringArray" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cuponerasCompradas" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actividadesFavoritas" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="premiosHead" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="premiosData" type="{http://webServices/}dtPremioWS" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtSocioWS", propOrder = {
    "clasesDeActividadesHead",
    "clasesDeActividadesData",
    "clasesDeActividadesFinalizadasHead",
    "clasesDeActividadesFinalizadasData",
    "cuponerasCompradas",
    "actividadesFavoritas",
    "premiosHead",
    "premiosData"
})
public class DtSocioWS
    extends DtUsuarioWS
{

    @XmlElement(nillable = true)
    protected List<String> clasesDeActividadesHead;
    @XmlElement(nillable = true)
    protected List<StringArray> clasesDeActividadesData;
    @XmlElement(nillable = true)
    protected List<String> clasesDeActividadesFinalizadasHead;
    @XmlElement(nillable = true)
    protected List<StringArray> clasesDeActividadesFinalizadasData;
    @XmlElement(nillable = true)
    protected List<String> cuponerasCompradas;
    @XmlElement(nillable = true)
    protected List<String> actividadesFavoritas;
    @XmlElement(nillable = true)
    protected List<String> premiosHead;
    @XmlElement(nillable = true)
    protected List<DtPremioWS> premiosData;

    /**
     * Gets the value of the clasesDeActividadesHead property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clasesDeActividadesHead property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClasesDeActividadesHead().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getClasesDeActividadesHead() {
        if (clasesDeActividadesHead == null) {
            clasesDeActividadesHead = new ArrayList<String>();
        }
        return this.clasesDeActividadesHead;
    }

    /**
     * Gets the value of the clasesDeActividadesData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clasesDeActividadesData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClasesDeActividadesData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StringArray }
     * 
     * 
     */
    public List<StringArray> getClasesDeActividadesData() {
        if (clasesDeActividadesData == null) {
            clasesDeActividadesData = new ArrayList<StringArray>();
        }
        return this.clasesDeActividadesData;
    }

    /**
     * Gets the value of the clasesDeActividadesFinalizadasHead property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clasesDeActividadesFinalizadasHead property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClasesDeActividadesFinalizadasHead().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getClasesDeActividadesFinalizadasHead() {
        if (clasesDeActividadesFinalizadasHead == null) {
            clasesDeActividadesFinalizadasHead = new ArrayList<String>();
        }
        return this.clasesDeActividadesFinalizadasHead;
    }

    /**
     * Gets the value of the clasesDeActividadesFinalizadasData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clasesDeActividadesFinalizadasData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClasesDeActividadesFinalizadasData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StringArray }
     * 
     * 
     */
    public List<StringArray> getClasesDeActividadesFinalizadasData() {
        if (clasesDeActividadesFinalizadasData == null) {
            clasesDeActividadesFinalizadasData = new ArrayList<StringArray>();
        }
        return this.clasesDeActividadesFinalizadasData;
    }

    /**
     * Gets the value of the cuponerasCompradas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cuponerasCompradas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCuponerasCompradas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCuponerasCompradas() {
        if (cuponerasCompradas == null) {
            cuponerasCompradas = new ArrayList<String>();
        }
        return this.cuponerasCompradas;
    }

    /**
     * Gets the value of the actividadesFavoritas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actividadesFavoritas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActividadesFavoritas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getActividadesFavoritas() {
        if (actividadesFavoritas == null) {
            actividadesFavoritas = new ArrayList<String>();
        }
        return this.actividadesFavoritas;
    }

    /**
     * Gets the value of the premiosHead property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the premiosHead property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPremiosHead().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPremiosHead() {
        if (premiosHead == null) {
            premiosHead = new ArrayList<String>();
        }
        return this.premiosHead;
    }

    /**
     * Gets the value of the premiosData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the premiosData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPremiosData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPremioWS }
     * 
     * 
     */
    public List<DtPremioWS> getPremiosData() {
        if (premiosData == null) {
            premiosData = new ArrayList<DtPremioWS>();
        }
        return this.premiosData;
    }

}
