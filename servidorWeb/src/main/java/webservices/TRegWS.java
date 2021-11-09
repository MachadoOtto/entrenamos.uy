
package webservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tRegWS.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tRegWS">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="cuponera"/>
 *     &lt;enumeration value="general"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tRegWS")
@XmlEnum
public enum TRegWS {

    @XmlEnumValue("cuponera")
    CUPONERA("cuponera"),
    @XmlEnumValue("general")
    GENERAL("general");
    private final String value;

    TRegWS(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TRegWS fromValue(String v) {
        for (TRegWS c: TRegWS.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
