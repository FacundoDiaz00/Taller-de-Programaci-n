
package publicar.usuarioturisticasservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtProveedorDetallePrivado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtProveedorDetallePrivado">
 *   <complexContent>
 *     <extension base="{http://usuarioTuristicasService.publicar/}dtProveedorDetalle">
 *       <sequence>
 *         <element name="actividadesNoConfirmadas" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="key" type="{http://usuarioTuristicasService.publicar/}estadoActividadTuristica" minOccurs="0"/>
 *                             <element name="value" type="{http://usuarioTuristicasService.publicar/}dtActividadTuristica" maxOccurs="unbounded" minOccurs="0"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtProveedorDetallePrivado", propOrder = {
    "actividadesNoConfirmadas"
})
public class DtProveedorDetallePrivado
    extends DtProveedorDetalle
{

    protected DtProveedorDetallePrivado.ActividadesNoConfirmadas actividadesNoConfirmadas;

    /**
     * Obtiene el valor de la propiedad actividadesNoConfirmadas.
     * 
     * @return
     *     possible object is
     *     {@link DtProveedorDetallePrivado.ActividadesNoConfirmadas }
     *     
     */
    public DtProveedorDetallePrivado.ActividadesNoConfirmadas getActividadesNoConfirmadas() {
        return actividadesNoConfirmadas;
    }

    /**
     * Define el valor de la propiedad actividadesNoConfirmadas.
     * 
     * @param value
     *     allowed object is
     *     {@link DtProveedorDetallePrivado.ActividadesNoConfirmadas }
     *     
     */
    public void setActividadesNoConfirmadas(DtProveedorDetallePrivado.ActividadesNoConfirmadas value) {
        this.actividadesNoConfirmadas = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="key" type="{http://usuarioTuristicasService.publicar/}estadoActividadTuristica" minOccurs="0"/>
     *                   <element name="value" type="{http://usuarioTuristicasService.publicar/}dtActividadTuristica" maxOccurs="unbounded" minOccurs="0"/>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class ActividadesNoConfirmadas {

        protected List<DtProveedorDetallePrivado.ActividadesNoConfirmadas.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a {@code set} method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DtProveedorDetallePrivado.ActividadesNoConfirmadas.Entry }
         * 
         * 
         * @return
         *     The value of the entry property.
         */
        public List<DtProveedorDetallePrivado.ActividadesNoConfirmadas.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<>();
            }
            return this.entry;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence>
         *         <element name="key" type="{http://usuarioTuristicasService.publicar/}estadoActividadTuristica" minOccurs="0"/>
         *         <element name="value" type="{http://usuarioTuristicasService.publicar/}dtActividadTuristica" maxOccurs="unbounded" minOccurs="0"/>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            @XmlSchemaType(name = "string")
            protected EstadoActividadTuristica key;
            protected List<DtActividadTuristica> value;

            /**
             * Obtiene el valor de la propiedad key.
             * 
             * @return
             *     possible object is
             *     {@link EstadoActividadTuristica }
             *     
             */
            public EstadoActividadTuristica getKey() {
                return key;
            }

            /**
             * Define el valor de la propiedad key.
             * 
             * @param value
             *     allowed object is
             *     {@link EstadoActividadTuristica }
             *     
             */
            public void setKey(EstadoActividadTuristica value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the Jakarta XML Binding object.
             * This is why there is not a {@code set} method for the value property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getValue().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link DtActividadTuristica }
             * 
             * 
             * @return
             *     The value of the value property.
             */
            public List<DtActividadTuristica> getValue() {
                if (value == null) {
                    value = new ArrayList<>();
                }
                return this.value;
            }

        }

    }

}
