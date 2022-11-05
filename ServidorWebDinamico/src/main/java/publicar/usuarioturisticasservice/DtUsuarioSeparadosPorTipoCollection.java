
package publicar.usuarioturisticasservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtUsuarioSeparadosPorTipoCollection complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtUsuarioSeparadosPorTipoCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proveedores" type="{http://usuarioTuristicasService.publicar/}dtProveedor" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="turistas" type="{http://usuarioTuristicasService.publicar/}dtTurista" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtUsuarioSeparadosPorTipoCollection", propOrder = {
    "proveedores",
    "turistas"
})
public class DtUsuarioSeparadosPorTipoCollection {

    @XmlElement(nillable = true)
    protected List<DtProveedor> proveedores;
    @XmlElement(nillable = true)
    protected List<DtTurista> turistas;

    /**
     * Gets the value of the proveedores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proveedores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProveedores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtProveedor }
     * 
     * 
     */
    public List<DtProveedor> getProveedores() {
        if (proveedores == null) {
            proveedores = new ArrayList<DtProveedor>();
        }
        return this.proveedores;
    }

    /**
     * Gets the value of the turistas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the turistas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTuristas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtTurista }
     * 
     * 
     */
    public List<DtTurista> getTuristas() {
        if (turistas == null) {
            turistas = new ArrayList<DtTurista>();
        }
        return this.turistas;
    }

}
