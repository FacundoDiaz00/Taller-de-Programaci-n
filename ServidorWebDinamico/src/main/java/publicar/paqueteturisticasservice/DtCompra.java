
package publicar.paqueteturisticasservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtCompra complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtCompra">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fechaCompra" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *         <element name="cantTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="vencimiento" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *         <element name="paquete" type="{http://paqueteTuristicasService.publicar/}dtPaquete" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCompra", propOrder = {
    "fechaCompra",
    "cantTuristas",
    "costo",
    "vencimiento",
    "paquete"
})
public class DtCompra {

    protected Object fechaCompra;
    protected int cantTuristas;
    protected float costo;
    protected Object vencimiento;
    protected DtPaquete paquete;

    /**
     * Obtiene el valor de la propiedad fechaCompra.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFechaCompra() {
        return fechaCompra;
    }

    /**
     * Define el valor de la propiedad fechaCompra.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFechaCompra(Object value) {
        this.fechaCompra = value;
    }

    /**
     * Obtiene el valor de la propiedad cantTuristas.
     * 
     */
    public int getCantTuristas() {
        return cantTuristas;
    }

    /**
     * Define el valor de la propiedad cantTuristas.
     * 
     */
    public void setCantTuristas(int value) {
        this.cantTuristas = value;
    }

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad vencimiento.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getVencimiento() {
        return vencimiento;
    }

    /**
     * Define el valor de la propiedad vencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setVencimiento(Object value) {
        this.vencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad paquete.
     * 
     * @return
     *     possible object is
     *     {@link DtPaquete }
     *     
     */
    public DtPaquete getPaquete() {
        return paquete;
    }

    /**
     * Define el valor de la propiedad paquete.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPaquete }
     *     
     */
    public void setPaquete(DtPaquete value) {
        this.paquete = value;
    }

}
