
package publicar.usuarioturisticasservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtProveedorDetalle complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtProveedorDetalle">
 *   <complexContent>
 *     <extension base="{http://usuarioTuristicasService.publicar/}dtProveedor">
 *       <sequence>
 *         <element name="actividades" type="{http://usuarioTuristicasService.publicar/}dtActividadTuristicaDetalle" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="seguidos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="seguidores" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtProveedorDetalle", propOrder = {
    "actividades",
    "seguidos",
    "seguidores"
})
@XmlSeeAlso({
    DtProveedorDetallePrivado.class
})
public class DtProveedorDetalle
    extends DtProveedor
{

    @XmlElement(nillable = true)
    protected List<DtActividadTuristicaDetalle> actividades;
    @XmlElement(nillable = true)
    protected List<String> seguidos;
    @XmlElement(nillable = true)
    protected List<String> seguidores;

    /**
     * Gets the value of the actividades property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the actividades property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActividades().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtActividadTuristicaDetalle }
     * 
     * 
     * @return
     *     The value of the actividades property.
     */
    public List<DtActividadTuristicaDetalle> getActividades() {
        if (actividades == null) {
            actividades = new ArrayList<>();
        }
        return this.actividades;
    }

    /**
     * Gets the value of the seguidos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the seguidos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeguidos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the seguidos property.
     */
    public List<String> getSeguidos() {
        if (seguidos == null) {
            seguidos = new ArrayList<>();
        }
        return this.seguidos;
    }

    /**
     * Gets the value of the seguidores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the seguidores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeguidores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the seguidores property.
     */
    public List<String> getSeguidores() {
        if (seguidores == null) {
            seguidores = new ArrayList<>();
        }
        return this.seguidores;
    }

}
