
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for logEntryWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="logEntryWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="browser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="date" type="{http://webServices/}dtFechaWS" minOccurs="0"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="so" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "logEntryWS", propOrder = {
    "browser",
    "date",
    "ip",
    "so",
    "url"
})
public class LogEntryWS {

    protected String browser;
    protected DtFechaWS date;
    protected String ip;
    protected String so;
    protected String url;

    /**
     * Gets the value of the browser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * Sets the value of the browser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrowser(String value) {
        this.browser = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link DtFechaWS }
     *     
     */
    public DtFechaWS getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtFechaWS }
     *     
     */
    public void setDate(DtFechaWS value) {
        this.date = value;
    }

    /**
     * Gets the value of the ip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Gets the value of the so property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSo() {
        return so;
    }

    /**
     * Sets the value of the so property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSo(String value) {
        this.so = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

}
