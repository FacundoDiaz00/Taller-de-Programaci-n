
package publicar.actividadesturisticasservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtActividadTuristica complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtActividadTuristica">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaAltaStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cuidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="duracion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costoPorTurista" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="nicknameProveedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="categorias" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="img" type="{http://actividadesturisticasservice.publicar/}imagen" minOccurs="0"/>
 *         <element name="estado" type="{http://actividadesturisticasservice.publicar/}estadoActividadTuristica" minOccurs="0"/>
 *         <element name="cantFavoritos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="urlVideo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtActividadTuristica", propOrder = {
    "nombre",
    "descripcion",
    "fechaAltaStr",
    "cuidad",
    "duracion",
    "costoPorTurista",
    "nicknameProveedor",
    "departamento",
    "categorias",
    "img",
    "estado",
    "cantFavoritos",
    "urlVideo"
})
@XmlSeeAlso({
    DtActividadTuristicaDetalle.class
})
public class DtActividadTuristica {

    protected String nombre;
    protected String descripcion;
    protected String fechaAltaStr;
    protected String cuidad;
    protected int duracion;
    protected float costoPorTurista;
    protected String nicknameProveedor;
    protected String departamento;
    @XmlElement(nillable = true)
    protected List<String> categorias;
    protected Imagen img;
    @XmlSchemaType(name = "string")
    protected EstadoActividadTuristica estado;
    protected int cantFavoritos;
    protected String urlVideo;

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
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAltaStr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAltaStr() {
        return fechaAltaStr;
    }

    /**
     * Define el valor de la propiedad fechaAltaStr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAltaStr(String value) {
        this.fechaAltaStr = value;
    }

    /**
     * Obtiene el valor de la propiedad cuidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuidad() {
        return cuidad;
    }

    /**
     * Define el valor de la propiedad cuidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuidad(String value) {
        this.cuidad = value;
    }

    /**
     * Obtiene el valor de la propiedad duracion.
     * 
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Define el valor de la propiedad duracion.
     * 
     */
    public void setDuracion(int value) {
        this.duracion = value;
    }

    /**
     * Obtiene el valor de la propiedad costoPorTurista.
     * 
     */
    public float getCostoPorTurista() {
        return costoPorTurista;
    }

    /**
     * Define el valor de la propiedad costoPorTurista.
     * 
     */
    public void setCostoPorTurista(float value) {
        this.costoPorTurista = value;
    }

    /**
     * Obtiene el valor de la propiedad nicknameProveedor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknameProveedor() {
        return nicknameProveedor;
    }

    /**
     * Define el valor de la propiedad nicknameProveedor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknameProveedor(String value) {
        this.nicknameProveedor = value;
    }

    /**
     * Obtiene el valor de la propiedad departamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Define el valor de la propiedad departamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Gets the value of the categorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the categorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the categorias property.
     */
    public List<String> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<>();
        }
        return this.categorias;
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
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link EstadoActividadTuristica }
     *     
     */
    public EstadoActividadTuristica getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoActividadTuristica }
     *     
     */
    public void setEstado(EstadoActividadTuristica value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad cantFavoritos.
     * 
     */
    public int getCantFavoritos() {
        return cantFavoritos;
    }

    /**
     * Define el valor de la propiedad cantFavoritos.
     * 
     */
    public void setCantFavoritos(int value) {
        this.cantFavoritos = value;
    }

    /**
     * Obtiene el valor de la propiedad urlVideo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlVideo() {
        return urlVideo;
    }

    /**
     * Define el valor de la propiedad urlVideo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlVideo(String value) {
        this.urlVideo = value;
    }

}
