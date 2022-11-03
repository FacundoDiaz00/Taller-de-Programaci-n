
package publicar.usuarioturisticasservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtUsuarioPorTipo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtUsuarioPorTipo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="esTurista" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="dtTurista" type="{http://usuarioTuristicasService.publicar/}dtTurista" minOccurs="0"/>
 *         <element name="dtProveedor" type="{http://usuarioTuristicasService.publicar/}dtProveedor" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtUsuarioPorTipo", propOrder = {
    "esTurista",
    "dtTurista",
    "dtProveedor"
})
public class DtUsuarioPorTipo {

    protected boolean esTurista;
    protected DtTurista dtTurista;
    protected DtProveedor dtProveedor;

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
     * Obtiene el valor de la propiedad dtTurista.
     * 
     * @return
     *     possible object is
     *     {@link DtTurista }
     *     
     */
    public DtTurista getDtTurista() {
        return dtTurista;
    }

    /**
     * Define el valor de la propiedad dtTurista.
     * 
     * @param value
     *     allowed object is
     *     {@link DtTurista }
     *     
     */
    public void setDtTurista(DtTurista value) {
        this.dtTurista = value;
    }

    /**
     * Obtiene el valor de la propiedad dtProveedor.
     * 
     * @return
     *     possible object is
     *     {@link DtProveedor }
     *     
     */
    public DtProveedor getDtProveedor() {
        return dtProveedor;
    }

    /**
     * Define el valor de la propiedad dtProveedor.
     * 
     * @param value
     *     allowed object is
     *     {@link DtProveedor }
     *     
     */
    public void setDtProveedor(DtProveedor value) {
        this.dtProveedor = value;
    }

}
