
package publicar.usuarioturisticasservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtUsuarioDetallePrivadoPorTipo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtUsuarioDetallePrivadoPorTipo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="esTurista" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="dtTuristaDetalle" type="{http://usuarioTuristicasService.publicar/}dtTuristaDetallePrivado" minOccurs="0"/>
 *         <element name="dtProveedorDetalle" type="{http://usuarioTuristicasService.publicar/}dtProveedorDetallePrivado" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtUsuarioDetallePrivadoPorTipo", propOrder = {
    "esTurista",
    "dtTuristaDetalle",
    "dtProveedorDetalle"
})
public class DtUsuarioDetallePrivadoPorTipo {

    protected boolean esTurista;
    protected DtTuristaDetallePrivado dtTuristaDetalle;
    protected DtProveedorDetallePrivado dtProveedorDetalle;

    /**
     * Obtiene el valor de la propiedad esTurista.
     * 
     */
    public boolean isEsTurista() {
        return esTurista;
    }

    /**
     * Define el valor de la propiedad esTurista.
     * 
     */
    public void setEsTurista(boolean value) {
        this.esTurista = value;
    }

    /**
     * Obtiene el valor de la propiedad dtTuristaDetalle.
     * 
     * @return
     *     possible object is
     *     {@link DtTuristaDetallePrivado }
     *     
     */
    public DtTuristaDetallePrivado getDtTuristaDetalle() {
        return dtTuristaDetalle;
    }

    /**
     * Define el valor de la propiedad dtTuristaDetalle.
     * 
     * @param value
     *     allowed object is
     *     {@link DtTuristaDetallePrivado }
     *     
     */
    public void setDtTuristaDetalle(DtTuristaDetallePrivado value) {
        this.dtTuristaDetalle = value;
    }

    /**
     * Obtiene el valor de la propiedad dtProveedorDetalle.
     * 
     * @return
     *     possible object is
     *     {@link DtProveedorDetallePrivado }
     *     
     */
    public DtProveedorDetallePrivado getDtProveedorDetalle() {
        return dtProveedorDetalle;
    }

    /**
     * Define el valor de la propiedad dtProveedorDetalle.
     * 
     * @param value
     *     allowed object is
     *     {@link DtProveedorDetallePrivado }
     *     
     */
    public void setDtProveedorDetalle(DtProveedorDetallePrivado value) {
        this.dtProveedorDetalle = value;
    }

}
