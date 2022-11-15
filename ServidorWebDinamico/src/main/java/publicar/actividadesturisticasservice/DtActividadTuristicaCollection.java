
package publicar.actividadesturisticasservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtActividadTuristicaCollection complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtActividadTuristicaCollection">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="actividadTuristicas" type="{http://actividadesturisticasservice.publicar/}dtActividadTuristica" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtActividadTuristicaCollection", propOrder = {
    "actividadTuristicas"
})
public class DtActividadTuristicaCollection {

    @XmlElement(nillable = true)
    protected List<DtActividadTuristica> actividadTuristicas;

    /**
     * Gets the value of the actividadTuristicas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the actividadTuristicas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActividadTuristicas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtActividadTuristica }
     * 
     * 
     * @return
     *     The value of the actividadTuristicas property.
     */
    public List<DtActividadTuristica> getActividadTuristicas() {
        if (actividadTuristicas == null) {
            actividadTuristicas = new ArrayList<>();
        }
        return this.actividadTuristicas;
    }

}
