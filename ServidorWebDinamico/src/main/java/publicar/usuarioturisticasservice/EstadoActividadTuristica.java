
package publicar.usuarioturisticasservice;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estadoActividadTuristica.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="estadoActividadTuristica">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AGREGADA"/>
 *     &lt;enumeration value="ACEPTADA"/>
 *     &lt;enumeration value="RECHAZADA"/>
 *     &lt;enumeration value="FINALIZADA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "estadoActividadTuristica")
@XmlEnum
public enum EstadoActividadTuristica {

    AGREGADA,
    ACEPTADA,
    RECHAZADA,
    FINALIZADA;

    public String value() {
        return name();
    }

    public static EstadoActividadTuristica fromValue(String v) {
        return valueOf(v);
    }

}
