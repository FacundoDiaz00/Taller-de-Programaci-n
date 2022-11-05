
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
 * <pre>
 * &lt;complexType name="dtTuristaDetallePrivado">
 *   &lt;complexContent>
 *     &lt;extension base="{http://usuarioTuristicasService.publicar/}dtTuristaDetalle">
 *       &lt;sequence>
 *         &lt;element name="compras" type="{http://usuarioTuristicasService.publicar/}dtCompra" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dtInscripciones" type="{http://usuarioTuristicasService.publicar/}dtInscripcion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compras property.
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
     */
    public List<DtCompra> getCompras() {
        if (compras == null) {
            compras = new ArrayList<DtCompra>();
        }
        return this.compras;
    }

    /**
     * Gets the value of the dtInscripciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtInscripciones property.
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
     */
    public List<DtInscripcion> getDtInscripciones() {
        if (dtInscripciones == null) {
            dtInscripciones = new ArrayList<DtInscripcion>();
        }
        return this.dtInscripciones;
    }

}
