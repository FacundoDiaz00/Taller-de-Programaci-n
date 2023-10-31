
package publicar.usuarioturisticasservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtProveedorDetallePrivado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtProveedorDetallePrivado">
 *   <complexContent>
 *     <extension base="{http://usuarioturisticasservice.publicar/}dtProveedorDetalle">
 *       <sequence>
 *         <element name="actividadesAgregadas" type="{http://usuarioturisticasservice.publicar/}dtActividadTuristica" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="actividadesFinalizadas" type="{http://usuarioturisticasservice.publicar/}dtActividadTuristica" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="actividadesRechazadas" type="{http://usuarioturisticasservice.publicar/}dtActividadTuristica" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtProveedorDetallePrivado", propOrder = {
    "actividadesAgregadas",
    "actividadesFinalizadas",
    "actividadesRechazadas"
})
public class DtProveedorDetallePrivado
    extends DtProveedorDetalle
{

    @XmlElement(nillable = true)
    protected List<DtActividadTuristica> actividadesAgregadas;
    @XmlElement(nillable = true)
    protected List<DtActividadTuristica> actividadesFinalizadas;
    @XmlElement(nillable = true)
    protected List<DtActividadTuristica> actividadesRechazadas;

    /**
     * Gets the value of the actividadesAgregadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the actividadesAgregadas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActividadesAgregadas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtActividadTuristica }
     * 
     * 
     * @return
     *     The value of the actividadesAgregadas property.
     */
    public List<DtActividadTuristica> getActividadesAgregadas() {
        if (actividadesAgregadas == null) {
            actividadesAgregadas = new ArrayList<>();
        }
        return this.actividadesAgregadas;
    }

    /**
     * Gets the value of the actividadesFinalizadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the actividadesFinalizadas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActividadesFinalizadas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtActividadTuristica }
     * 
     * 
     * @return
     *     The value of the actividadesFinalizadas property.
     */
    public List<DtActividadTuristica> getActividadesFinalizadas() {
        if (actividadesFinalizadas == null) {
            actividadesFinalizadas = new ArrayList<>();
        }
        return this.actividadesFinalizadas;
    }

    /**
     * Gets the value of the actividadesRechazadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the actividadesRechazadas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActividadesRechazadas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtActividadTuristica }
     * 
     * 
     * @return
     *     The value of the actividadesRechazadas property.
     */
    public List<DtActividadTuristica> getActividadesRechazadas() {
        if (actividadesRechazadas == null) {
            actividadesRechazadas = new ArrayList<>();
        }
        return this.actividadesRechazadas;
    }

}
