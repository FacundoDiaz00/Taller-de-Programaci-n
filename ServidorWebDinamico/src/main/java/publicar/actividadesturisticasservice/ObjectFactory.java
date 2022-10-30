
package publicar.actividadesturisticasservice;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publicar.actividadesturisticasservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ObjetoNoExisteEnTurismoUy_QNAME = new QName("http://actividadesTuristicasService.publicar/", "ObjetoNoExisteEnTurismoUy");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicar.actividadesturisticasservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObjetoNoExisteEnTurismoUy }
     * 
     * @return
     *     the new instance of {@link ObjetoNoExisteEnTurismoUy }
     */
    public ObjetoNoExisteEnTurismoUy createObjetoNoExisteEnTurismoUy() {
        return new ObjetoNoExisteEnTurismoUy();
    }

    /**
     * Create an instance of {@link ListOfObject }
     * 
     * @return
     *     the new instance of {@link ListOfObject }
     */
    public ListOfObject createListOfObject() {
        return new ListOfObject();
    }

    /**
     * Create an instance of {@link ListOfString }
     * 
     * @return
     *     the new instance of {@link ListOfString }
     */
    public ListOfString createListOfString() {
        return new ListOfString();
    }

    /**
     * Create an instance of {@link DtActividadTuristica }
     * 
     * @return
     *     the new instance of {@link DtActividadTuristica }
     */
    public DtActividadTuristica createDtActividadTuristica() {
        return new DtActividadTuristica();
    }

    /**
     * Create an instance of {@link Imagen }
     * 
     * @return
     *     the new instance of {@link Imagen }
     */
    public Imagen createImagen() {
        return new Imagen();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaCollection }
     * 
     * @return
     *     the new instance of {@link DtActividadTuristicaCollection }
     */
    public DtActividadTuristicaCollection createDtActividadTuristicaCollection() {
        return new DtActividadTuristicaCollection();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjetoNoExisteEnTurismoUy }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObjetoNoExisteEnTurismoUy }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "ObjetoNoExisteEnTurismoUy")
    public JAXBElement<ObjetoNoExisteEnTurismoUy> createObjetoNoExisteEnTurismoUy(ObjetoNoExisteEnTurismoUy value) {
        return new JAXBElement<>(_ObjetoNoExisteEnTurismoUy_QNAME, ObjetoNoExisteEnTurismoUy.class, null, value);
    }

}
