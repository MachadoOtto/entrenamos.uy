
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtFechaWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtFechaWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="anio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="horas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minutos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="segundos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtFechaWS", propOrder = {
    "anio",
    "mes",
    "dia",
    "horas",
    "minutos",
    "segundos"
})
public class DtFechaWS {

    protected int anio;
    protected int mes;
    protected int dia;
    protected int horas;
    protected int minutos;
    protected int segundos;

    /**
     * Gets the value of the anio property.
     * 
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Sets the value of the anio property.
     * 
     */
    public void setAnio(int value) {
        this.anio = value;
    }

    /**
     * Gets the value of the mes property.
     * 
     */
    public int getMes() {
        return mes;
    }

    /**
     * Sets the value of the mes property.
     * 
     */
    public void setMes(int value) {
        this.mes = value;
    }

    /**
     * Gets the value of the dia property.
     * 
     */
    public int getDia() {
        return dia;
    }

    /**
     * Sets the value of the dia property.
     * 
     */
    public void setDia(int value) {
        this.dia = value;
    }

    /**
     * Gets the value of the horas property.
     * 
     */
    public int getHoras() {
        return horas;
    }

    /**
     * Sets the value of the horas property.
     * 
     */
    public void setHoras(int value) {
        this.horas = value;
    }

    /**
     * Gets the value of the minutos property.
     * 
     */
    public int getMinutos() {
        return minutos;
    }

    /**
     * Sets the value of the minutos property.
     * 
     */
    public void setMinutos(int value) {
        this.minutos = value;
    }

    /**
     * Gets the value of the segundos property.
     * 
     */
    public int getSegundos() {
        return segundos;
    }

    /**
     * Sets the value of the segundos property.
     * 
     */
    public void setSegundos(int value) {
        this.segundos = value;
    }

}
