
package publicar.paqueteturisticasservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtCompra complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtCompra">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaCompraStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="vencimientoStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paquete" type="{http://paqueteTuristicasService.publicar/}dtPaquete" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCompra", propOrder = {
    "fechaCompraStr",
    "cantTuristas",
    "costo",
    "vencimientoStr",
    "paquete"
})
public class DtCompra {

    protected String fechaCompraStr;
    protected int cantTuristas;
    protected float costo;
    protected String vencimientoStr;
    protected DtPaquete paquete;

    /**
     * Obtiene el valor de la propiedad fechaCompraStr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCompraStr() {
        return fechaCompraStr;
    }

    /**
     * Define el valor de la propiedad fechaCompraStr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCompraStr(String value) {
        this.fechaCompraStr = value;
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
     * Obtiene el valor de la propiedad vencimientoStr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVencimientoStr() {
        return vencimientoStr;
    }

    /**
     * Define el valor de la propiedad vencimientoStr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVencimientoStr(String value) {
        this.vencimientoStr = value;
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
