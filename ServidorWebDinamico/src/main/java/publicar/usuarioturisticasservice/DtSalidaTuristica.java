
package publicar.usuarioturisticasservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtSalidaTuristica complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtSalidaTuristica">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaHoraSalida" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *         <element name="lugarSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *         <element name="cantMaxTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="img" type="{http://usuarioTuristicasService.publicar/}imagen" minOccurs="0"/>
 *         <element name="actividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtSalidaTuristica", propOrder = {
    "nombre",
    "fechaHoraSalida",
    "lugarSalida",
    "fechaAlta",
    "cantMaxTuristas",
    "img",
    "actividad"
})
public class DtSalidaTuristica {

    protected String nombre;
    protected Object fechaHoraSalida;
    protected String lugarSalida;
    protected Object fechaAlta;
    protected int cantMaxTuristas;
    protected Imagen img;
    protected String actividad;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaHoraSalida.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    /**
     * Define el valor de la propiedad fechaHoraSalida.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFechaHoraSalida(Object value) {
        this.fechaHoraSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad lugarSalida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarSalida() {
        return lugarSalida;
    }

    /**
     * Define el valor de la propiedad lugarSalida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarSalida(String value) {
        this.lugarSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAlta.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFechaAlta(Object value) {
        this.fechaAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad cantMaxTuristas.
     * 
     */
    public int getCantMaxTuristas() {
        return cantMaxTuristas;
    }

    /**
     * Define el valor de la propiedad cantMaxTuristas.
     * 
     */
    public void setCantMaxTuristas(int value) {
        this.cantMaxTuristas = value;
    }

    /**
     * Obtiene el valor de la propiedad img.
     * 
     * @return
     *     possible object is
     *     {@link Imagen }
     *     
     */
    public Imagen getImg() {
        return img;
    }

    /**
     * Define el valor de la propiedad img.
     * 
     * @param value
     *     allowed object is
     *     {@link Imagen }
     *     
     */
    public void setImg(Imagen value) {
        this.img = value;
    }

    /**
     * Obtiene el valor de la propiedad actividad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividad() {
        return actividad;
    }

    /**
     * Define el valor de la propiedad actividad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividad(String value) {
        this.actividad = value;
    }

}
