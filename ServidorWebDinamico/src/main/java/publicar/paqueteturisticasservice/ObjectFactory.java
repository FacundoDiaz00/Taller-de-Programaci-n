
package publicar.paqueteturisticasservice;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publicar.paqueteturisticasservice package. 
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

    private final static QName _CompraYaRegistradaException_QNAME = new QName("http://paqueteTuristicasService.publicar/", "CompraYaRegistradaException");
    private final static QName _PaquetesSinActividadesExcepcion_QNAME = new QName("http://paqueteTuristicasService.publicar/", "PaquetesSinActividadesExcepcion");
    private final static QName _ObjetoNoExisteEnTurismoUy_QNAME = new QName("http://paqueteTuristicasService.publicar/", "ObjetoNoExisteEnTurismoUy");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicar.paqueteturisticasservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtPaqueteDetalles }
     * 
     */
    public DtPaqueteDetalles createDtPaqueteDetalles() {
        return new DtPaqueteDetalles();
    }

    /**
     * Create an instance of {@link DtPaqueteDetalles.Actividades }
     * 
     */
    public DtPaqueteDetalles.Actividades createDtPaqueteDetallesActividades() {
        return new DtPaqueteDetalles.Actividades();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaDetalle }
     * 
     */
    public DtActividadTuristicaDetalle createDtActividadTuristicaDetalle() {
        return new DtActividadTuristicaDetalle();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaDetalle.Paquetes }
     * 
     */
    public DtActividadTuristicaDetalle.Paquetes createDtActividadTuristicaDetallePaquetes() {
        return new DtActividadTuristicaDetalle.Paquetes();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaDetalle.Salidas }
     * 
     */
    public DtActividadTuristicaDetalle.Salidas createDtActividadTuristicaDetalleSalidas() {
        return new DtActividadTuristicaDetalle.Salidas();
    }

    /**
     * Create an instance of {@link CompraYaRegistradaException }
     * 
     */
    public CompraYaRegistradaException createCompraYaRegistradaException() {
        return new CompraYaRegistradaException();
    }

    /**
     * Create an instance of {@link PaquetesSinActividadesExcepcion }
     * 
     */
    public PaquetesSinActividadesExcepcion createPaquetesSinActividadesExcepcion() {
        return new PaquetesSinActividadesExcepcion();
    }

    /**
     * Create an instance of {@link ObjetoNoExisteEnTurismoUy }
     * 
     */
    public ObjetoNoExisteEnTurismoUy createObjetoNoExisteEnTurismoUy() {
        return new ObjetoNoExisteEnTurismoUy();
    }

    /**
     * Create an instance of {@link DtCompra }
     * 
     */
    public DtCompra createDtCompra() {
        return new DtCompra();
    }

    /**
     * Create an instance of {@link DtPaqueteCollection }
     * 
     */
    public DtPaqueteCollection createDtPaqueteCollection() {
        return new DtPaqueteCollection();
    }

    /**
     * Create an instance of {@link DtInscripcion }
     * 
     */
    public DtInscripcion createDtInscripcion() {
        return new DtInscripcion();
    }

    /**
     * Create an instance of {@link Imagen }
     * 
     */
    public Imagen createImagen() {
        return new Imagen();
    }

    /**
     * Create an instance of {@link DtActividadTuristica }
     * 
     */
    public DtActividadTuristica createDtActividadTuristica() {
        return new DtActividadTuristica();
    }

    /**
     * Create an instance of {@link DtSalidaTuristicaDetalle }
     * 
     */
    public DtSalidaTuristicaDetalle createDtSalidaTuristicaDetalle() {
        return new DtSalidaTuristicaDetalle();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link DtSalidaTuristica }
     * 
     */
    public DtSalidaTuristica createDtSalidaTuristica() {
        return new DtSalidaTuristica();
    }

    /**
     * Create an instance of {@link DtPaqueteDetalles.Actividades.Entry }
     * 
     */
    public DtPaqueteDetalles.Actividades.Entry createDtPaqueteDetallesActividadesEntry() {
        return new DtPaqueteDetalles.Actividades.Entry();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaDetalle.Paquetes.Entry }
     * 
     */
    public DtActividadTuristicaDetalle.Paquetes.Entry createDtActividadTuristicaDetallePaquetesEntry() {
        return new DtActividadTuristicaDetalle.Paquetes.Entry();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaDetalle.Salidas.Entry }
     * 
     */
    public DtActividadTuristicaDetalle.Salidas.Entry createDtActividadTuristicaDetalleSalidasEntry() {
        return new DtActividadTuristicaDetalle.Salidas.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompraYaRegistradaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://paqueteTuristicasService.publicar/", name = "CompraYaRegistradaException")
    public JAXBElement<CompraYaRegistradaException> createCompraYaRegistradaException(CompraYaRegistradaException value) {
        return new JAXBElement<CompraYaRegistradaException>(_CompraYaRegistradaException_QNAME, CompraYaRegistradaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaquetesSinActividadesExcepcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://paqueteTuristicasService.publicar/", name = "PaquetesSinActividadesExcepcion")
    public JAXBElement<PaquetesSinActividadesExcepcion> createPaquetesSinActividadesExcepcion(PaquetesSinActividadesExcepcion value) {
        return new JAXBElement<PaquetesSinActividadesExcepcion>(_PaquetesSinActividadesExcepcion_QNAME, PaquetesSinActividadesExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjetoNoExisteEnTurismoUy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://paqueteTuristicasService.publicar/", name = "ObjetoNoExisteEnTurismoUy")
    public JAXBElement<ObjetoNoExisteEnTurismoUy> createObjetoNoExisteEnTurismoUy(ObjetoNoExisteEnTurismoUy value) {
        return new JAXBElement<ObjetoNoExisteEnTurismoUy>(_ObjetoNoExisteEnTurismoUy_QNAME, ObjetoNoExisteEnTurismoUy.class, null, value);
    }

}
