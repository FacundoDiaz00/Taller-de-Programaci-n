
package publicar.usuarioturisticasservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtTuristaDetallePrivado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtTuristaDetallePrivado">
 *   <complexContent>
 *     <extension base="{http://usuarioTuristicasService.publicar/}dtTuristaDetalle">
 *       <sequence>
 *         <element name="compras" type="{http://usuarioTuristicasService.publicar/}dtCompra" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="dtInscripciones" type="{http://usuarioTuristicasService.publicar/}dtInscripcion" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtTuristaDetallePrivado", propOrder = {
    "compras",
    "dtInscripciones"
})
public class DtTuristaDetallePrivado
    extends DtTuristaDetalle
{

    @XmlElement(nillable = true)
    protected List<DtCompra> compras;
    @XmlElement(nillable = true)
    protected List<DtInscripcion> dtInscripciones;

    /**
     * Gets the value of the compras property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the compras property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompras().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtCompra }
     * 
     * 
     * @return
     *     The value of the compras property.
     */
    public List<DtCompra> getCompras() {
        if (compras == null) {
            compras = new ArrayList<>();
        }
        return this.compras;
    }

    /**
     * Gets the value of the dtInscripciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the dtInscripciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtInscripciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtInscripcion }
     * 
     * 
     * @return
     *     The value of the dtInscripciones property.
     */
    public List<DtInscripcion> getDtInscripciones() {
        if (dtInscripciones == null) {
            dtInscripciones = new ArrayList<>();
        }
        return this.dtInscripciones;
    }

}
