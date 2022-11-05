
package publicar.usuarioturisticasservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtProveedor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtProveedor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://usuarioTuristicasService.publicar/}dtUsuario">
 *       &lt;sequence>
 *         &lt;element name="descrpicionGeneral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtProveedor", propOrder = {
    "descrpicionGeneral",
    "link"
})
@XmlSeeAlso({
    DtProveedorDetalle.class
})
public class DtProveedor
    extends DtUsuario
{

    protected String descrpicionGeneral;
    protected String link;

    /**
     * Obtiene el valor de la propiedad descrpicionGeneral.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrpicionGeneral() {
        return descrpicionGeneral;
    }

    /**
     * Define el valor de la propiedad descrpicionGeneral.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrpicionGeneral(String value) {
        this.descrpicionGeneral = value;
    }

    /**
     * Obtiene el valor de la propiedad link.
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
     * Define el valor de la propiedad link.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLink(String value) {
        this.link = value;
    }

}
