
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
 * <pre>
 * &lt;complexType name="dtProveedorDetallePrivado">
 *   &lt;complexContent>
 *     &lt;extension base="{http://usuarioTuristicasService.publicar/}dtProveedorDetalle">
 *       &lt;sequence>
 *         &lt;element name="actividadesAgregadas" type="{http://usuarioTuristicasService.publicar/}dtActividadTuristica" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actividadesFinalizadas" type="{http://usuarioTuristicasService.publicar/}dtActividadTuristica" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actividadesRechazadas" type="{http://usuarioTuristicasService.publicar/}dtActividadTuristica" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actividadesAgregadas property.
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
     */
    public List<DtActividadTuristica> getActividadesAgregadas() {
        if (actividadesAgregadas == null) {
            actividadesAgregadas = new ArrayList<DtActividadTuristica>();
        }
        return this.actividadesAgregadas;
    }

    /**
     * Gets the value of the actividadesFinalizadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actividadesFinalizadas property.
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
     */
    public List<DtActividadTuristica> getActividadesFinalizadas() {
        if (actividadesFinalizadas == null) {
            actividadesFinalizadas = new ArrayList<DtActividadTuristica>();
        }
        return this.actividadesFinalizadas;
    }

    /**
     * Gets the value of the actividadesRechazadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actividadesRechazadas property.
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
     */
    public List<DtActividadTuristica> getActividadesRechazadas() {
        if (actividadesRechazadas == null) {
            actividadesRechazadas = new ArrayList<DtActividadTuristica>();
        }
        return this.actividadesRechazadas;
    }

}
