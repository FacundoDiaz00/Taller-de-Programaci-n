
package publicar.maestroservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ObjetoNoExisteEnTurismoUy complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ObjetoNoExisteEnTurismoUy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="claseObjetoFaltante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjetoNoExisteEnTurismoUy", propOrder = {
    "claseObjetoFaltante",
    "message"
})
public class ObjetoNoExisteEnTurismoUy {

    protected String claseObjetoFaltante;
    protected String message;

    /**
     * Obtiene el valor de la propiedad claseObjetoFaltante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaseObjetoFaltante() {
        return claseObjetoFaltante;
    }

    /**
     * Define el valor de la propiedad claseObjetoFaltante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaseObjetoFaltante(String value) {
        this.claseObjetoFaltante = value;
    }

    /**
     * Obtiene el valor de la propiedad message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Define el valor de la propiedad message.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
