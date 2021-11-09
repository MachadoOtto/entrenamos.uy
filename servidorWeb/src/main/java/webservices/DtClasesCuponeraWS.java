
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtClasesCuponeraWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtClasesCuponeraWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidadClases" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtClasesCuponeraWS", propOrder = {
    "cantidadClases",
    "nombreActividad"
})
public class DtClasesCuponeraWS {

    protected int cantidadClases;
    protected String nombreActividad;

    /**
     * Gets the value of the cantidadClases property.
     * 
     */
    public int getCantidadClases() {
        return cantidadClases;
    }

    /**
     * Sets the value of the cantidadClases property.
     * 
     */
    public void setCantidadClases(int value) {
        this.cantidadClases = value;
    }

    /**
     * Gets the value of the nombreActividad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreActividad() {
        return nombreActividad;
    }

    /**
     * Sets the value of the nombreActividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreActividad(String value) {
        this.nombreActividad = value;
    }

}
