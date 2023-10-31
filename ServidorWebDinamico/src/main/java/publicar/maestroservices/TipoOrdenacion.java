
package publicar.maestroservices;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoOrdenacion.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="tipoOrdenacion">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="ALFABETICAMENTE"/>
 *     <enumeration value="FECHA_PUBLICACION"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "tipoOrdenacion")
@XmlEnum
public enum TipoOrdenacion {

    ALFABETICAMENTE,
    FECHA_PUBLICACION;

    public String value() {
        return name();
    }

    public static TipoOrdenacion fromValue(String v) {
        return valueOf(v);
    }

}
