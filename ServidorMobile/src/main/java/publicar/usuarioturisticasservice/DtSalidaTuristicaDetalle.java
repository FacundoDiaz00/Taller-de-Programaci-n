
package publicar.usuarioturisticasservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtSalidaTuristicaDetalle complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtSalidaTuristicaDetalle">
 *   <complexContent>
 *     <extension base="{http://usuarioturisticasservice.publicar/}dtSalidaTuristica">
 *       <sequence>
 *         <element name="inscripciones" type="{http://usuarioturisticasservice.publicar/}dtInscripcion" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="dtActividad" type="{http://usuarioturisticasservice.publicar/}dtActividadTuristicaDetalle" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtSalidaTuristicaDetalle", propOrder = {
    "inscripciones",
    "dtActividad"
})
public class DtSalidaTuristicaDetalle
    extends DtSalidaTuristica
{

    @XmlElement(nillable = true)
    protected List<DtInscripcion> inscripciones;
    protected DtActividadTuristicaDetalle dtActividad;

    /**
     * Gets the value of the inscripciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the inscripciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInscripciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtInscripcion }
     * 
     * 
     * @return
     *     The value of the inscripciones property.
     */
    public List<DtInscripcion> getInscripciones() {
        if (inscripciones == null) {
            inscripciones = new ArrayList<>();
        }
        return this.inscripciones;
    }

    /**
     * Obtiene el valor de la propiedad dtActividad.
     * 
     * @return
     *     possible object is
     *     {@link DtActividadTuristicaDetalle }
     *     
     */
    public DtActividadTuristicaDetalle getDtActividad() {
        return dtActividad;
    }

    /**
     * Define el valor de la propiedad dtActividad.
     * 
     * @param value
     *     allowed object is
     *     {@link DtActividadTuristicaDetalle }
     *     
     */
    public void setDtActividad(DtActividadTuristicaDetalle value) {
        this.dtActividad = value;
    }

}
