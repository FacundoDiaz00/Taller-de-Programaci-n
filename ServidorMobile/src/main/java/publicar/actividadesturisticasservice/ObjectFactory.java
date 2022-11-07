
package publicar.actividadesturisticasservice;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


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

    private final static QName _CompraPaqueteVencidoExcepcion_QNAME = new QName("http://actividadesTuristicasService.publicar/", "CompraPaqueteVencidoExcepcion");
    private final static QName _CompraConConsumosInsuficientesExcepcion_QNAME = new QName("http://actividadesTuristicasService.publicar/", "CompraConConsumosInsuficientesExcepcion");
    private final static QName _ErrorAlProcesar_QNAME = new QName("http://actividadesTuristicasService.publicar/", "ErrorAlProcesar");
    private final static QName _ObjetoNoExisteEnTurismoUy_QNAME = new QName("http://actividadesTuristicasService.publicar/", "ObjetoNoExisteEnTurismoUy");
    private final static QName _FechaAltaSalidaTuristicaPosteriorAFechaInscripcion_QNAME = new QName("http://actividadesTuristicasService.publicar/", "FechaAltaSalidaTuristicaPosteriorAFechaInscripcion");
    private final static QName _SalidaYaRegistradaException_QNAME = new QName("http://actividadesTuristicasService.publicar/", "SalidaYaRegistradaException");
    private final static QName _ActividadTuristicaYaRegistradaException_QNAME = new QName("http://actividadesTuristicasService.publicar/", "ActividadTuristicaYaRegistradaException");
    private final static QName _InscripcionYaRegistradaException_QNAME = new QName("http://actividadesTuristicasService.publicar/", "InscripcionYaRegistradaException");
    private final static QName _SuperaElMaximoDeTuristasException_QNAME = new QName("http://actividadesTuristicasService.publicar/", "SuperaElMaximoDeTuristasException");
    private final static QName _FechaAltaSalidaPosteriorAFechaSalidaException_QNAME = new QName("http://actividadesTuristicasService.publicar/", "FechaAltaSalidaPosteriorAFechaSalidaException");
    private final static QName _PaqueteNoCompradoExcepcion_QNAME = new QName("http://actividadesTuristicasService.publicar/", "PaqueteNoCompradoExcepcion");
    private final static QName _ActividadTuristicaNoAceptada_QNAME = new QName("http://actividadesTuristicasService.publicar/", "ActividadTuristicaNoAceptada");
    private final static QName _FechaAltaActividadPosteriorAFechaAltaSalidaException_QNAME = new QName("http://actividadesTuristicasService.publicar/", "FechaAltaActividadPosteriorAFechaAltaSalidaException");
    private final static QName _NoExisteConsumoParaLaActividadExcepcion_QNAME = new QName("http://actividadesTuristicasService.publicar/", "NoExisteConsumoParaLaActividadExcepcion");
    private final static QName _AltaInscripcionPosteriorAFechaSalidaException_QNAME = new QName("http://actividadesTuristicasService.publicar/", "AltaInscripcionPosteriorAFechaSalidaException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicar.actividadesturisticasservice
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
     * Create an instance of {@link AltaInscripcionPosteriorAFechaSalidaException }
     * 
     */
    public AltaInscripcionPosteriorAFechaSalidaException createAltaInscripcionPosteriorAFechaSalidaException() {
        return new AltaInscripcionPosteriorAFechaSalidaException();
    }

    /**
     * Create an instance of {@link ActividadTuristicaNoAceptada }
     * 
     */
    public ActividadTuristicaNoAceptada createActividadTuristicaNoAceptada() {
        return new ActividadTuristicaNoAceptada();
    }

    /**
     * Create an instance of {@link FechaAltaActividadPosteriorAFechaAltaSalidaException }
     * 
     */
    public FechaAltaActividadPosteriorAFechaAltaSalidaException createFechaAltaActividadPosteriorAFechaAltaSalidaException() {
        return new FechaAltaActividadPosteriorAFechaAltaSalidaException();
    }

    /**
     * Create an instance of {@link NoExisteConsumoParaLaActividadExcepcion }
     * 
     */
    public NoExisteConsumoParaLaActividadExcepcion createNoExisteConsumoParaLaActividadExcepcion() {
        return new NoExisteConsumoParaLaActividadExcepcion();
    }

    /**
     * Create an instance of {@link FechaAltaSalidaPosteriorAFechaSalidaException }
     * 
     */
    public FechaAltaSalidaPosteriorAFechaSalidaException createFechaAltaSalidaPosteriorAFechaSalidaException() {
        return new FechaAltaSalidaPosteriorAFechaSalidaException();
    }

    /**
     * Create an instance of {@link PaqueteNoCompradoExcepcion }
     * 
     */
    public PaqueteNoCompradoExcepcion createPaqueteNoCompradoExcepcion() {
        return new PaqueteNoCompradoExcepcion();
    }

    /**
     * Create an instance of {@link CompraConConsumosInsuficientesExcepcion }
     * 
     */
    public CompraConConsumosInsuficientesExcepcion createCompraConConsumosInsuficientesExcepcion() {
        return new CompraConConsumosInsuficientesExcepcion();
    }

    /**
     * Create an instance of {@link CompraPaqueteVencidoExcepcion }
     * 
     */
    public CompraPaqueteVencidoExcepcion createCompraPaqueteVencidoExcepcion() {
        return new CompraPaqueteVencidoExcepcion();
    }

    /**
     * Create an instance of {@link ErrorAlProcesar }
     * 
     */
    public ErrorAlProcesar createErrorAlProcesar() {
        return new ErrorAlProcesar();
    }

    /**
     * Create an instance of {@link ObjetoNoExisteEnTurismoUy }
     * 
     */
    public ObjetoNoExisteEnTurismoUy createObjetoNoExisteEnTurismoUy() {
        return new ObjetoNoExisteEnTurismoUy();
    }

    /**
     * Create an instance of {@link ActividadTuristicaYaRegistradaException }
     * 
     */
    public ActividadTuristicaYaRegistradaException createActividadTuristicaYaRegistradaException() {
        return new ActividadTuristicaYaRegistradaException();
    }

    /**
     * Create an instance of {@link InscripcionYaRegistradaException }
     * 
     */
    public InscripcionYaRegistradaException createInscripcionYaRegistradaException() {
        return new InscripcionYaRegistradaException();
    }

    /**
     * Create an instance of {@link SuperaElMaximoDeTuristasException }
     * 
     */
    public SuperaElMaximoDeTuristasException createSuperaElMaximoDeTuristasException() {
        return new SuperaElMaximoDeTuristasException();
    }

    /**
     * Create an instance of {@link FechaAltaSalidaTuristicaPosteriorAFechaInscripcion }
     * 
     */
    public FechaAltaSalidaTuristicaPosteriorAFechaInscripcion createFechaAltaSalidaTuristicaPosteriorAFechaInscripcion() {
        return new FechaAltaSalidaTuristicaPosteriorAFechaInscripcion();
    }

    /**
     * Create an instance of {@link SalidaYaRegistradaException }
     * 
     */
    public SalidaYaRegistradaException createSalidaYaRegistradaException() {
        return new SalidaYaRegistradaException();
    }

    /**
     * Create an instance of {@link DtCompra }
     * 
     */
    public DtCompra createDtCompra() {
        return new DtCompra();
    }

    /**
     * Create an instance of {@link DtInscripcion }
     * 
     */
    public DtInscripcion createDtInscripcion() {
        return new DtInscripcion();
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
     * Create an instance of {@link ListOfDTSalidaTuristica }
     * 
     */
    public ListOfDTSalidaTuristica createListOfDTSalidaTuristica() {
        return new ListOfDTSalidaTuristica();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link ListOfObject }
     * 
     */
    public ListOfObject createListOfObject() {
        return new ListOfObject();
    }

    /**
     * Create an instance of {@link Imagen }
     * 
     */
    public Imagen createImagen() {
        return new Imagen();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaCollection }
     * 
     */
    public DtActividadTuristicaCollection createDtActividadTuristicaCollection() {
        return new DtActividadTuristicaCollection();
    }

    /**
     * Create an instance of {@link ListOfString }
     * 
     */
    public ListOfString createListOfString() {
        return new ListOfString();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CompraPaqueteVencidoExcepcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "CompraPaqueteVencidoExcepcion")
    public JAXBElement<CompraPaqueteVencidoExcepcion> createCompraPaqueteVencidoExcepcion(CompraPaqueteVencidoExcepcion value) {
        return new JAXBElement<CompraPaqueteVencidoExcepcion>(_CompraPaqueteVencidoExcepcion_QNAME, CompraPaqueteVencidoExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompraConConsumosInsuficientesExcepcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "CompraConConsumosInsuficientesExcepcion")
    public JAXBElement<CompraConConsumosInsuficientesExcepcion> createCompraConConsumosInsuficientesExcepcion(CompraConConsumosInsuficientesExcepcion value) {
        return new JAXBElement<CompraConConsumosInsuficientesExcepcion>(_CompraConConsumosInsuficientesExcepcion_QNAME, CompraConConsumosInsuficientesExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorAlProcesar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "ErrorAlProcesar")
    public JAXBElement<ErrorAlProcesar> createErrorAlProcesar(ErrorAlProcesar value) {
        return new JAXBElement<ErrorAlProcesar>(_ErrorAlProcesar_QNAME, ErrorAlProcesar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjetoNoExisteEnTurismoUy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "ObjetoNoExisteEnTurismoUy")
    public JAXBElement<ObjetoNoExisteEnTurismoUy> createObjetoNoExisteEnTurismoUy(ObjetoNoExisteEnTurismoUy value) {
        return new JAXBElement<ObjetoNoExisteEnTurismoUy>(_ObjetoNoExisteEnTurismoUy_QNAME, ObjetoNoExisteEnTurismoUy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FechaAltaSalidaTuristicaPosteriorAFechaInscripcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "FechaAltaSalidaTuristicaPosteriorAFechaInscripcion")
    public JAXBElement<FechaAltaSalidaTuristicaPosteriorAFechaInscripcion> createFechaAltaSalidaTuristicaPosteriorAFechaInscripcion(FechaAltaSalidaTuristicaPosteriorAFechaInscripcion value) {
        return new JAXBElement<FechaAltaSalidaTuristicaPosteriorAFechaInscripcion>(_FechaAltaSalidaTuristicaPosteriorAFechaInscripcion_QNAME, FechaAltaSalidaTuristicaPosteriorAFechaInscripcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SalidaYaRegistradaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "SalidaYaRegistradaException")
    public JAXBElement<SalidaYaRegistradaException> createSalidaYaRegistradaException(SalidaYaRegistradaException value) {
        return new JAXBElement<SalidaYaRegistradaException>(_SalidaYaRegistradaException_QNAME, SalidaYaRegistradaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadTuristicaYaRegistradaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "ActividadTuristicaYaRegistradaException")
    public JAXBElement<ActividadTuristicaYaRegistradaException> createActividadTuristicaYaRegistradaException(ActividadTuristicaYaRegistradaException value) {
        return new JAXBElement<ActividadTuristicaYaRegistradaException>(_ActividadTuristicaYaRegistradaException_QNAME, ActividadTuristicaYaRegistradaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InscripcionYaRegistradaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "InscripcionYaRegistradaException")
    public JAXBElement<InscripcionYaRegistradaException> createInscripcionYaRegistradaException(InscripcionYaRegistradaException value) {
        return new JAXBElement<InscripcionYaRegistradaException>(_InscripcionYaRegistradaException_QNAME, InscripcionYaRegistradaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuperaElMaximoDeTuristasException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "SuperaElMaximoDeTuristasException")
    public JAXBElement<SuperaElMaximoDeTuristasException> createSuperaElMaximoDeTuristasException(SuperaElMaximoDeTuristasException value) {
        return new JAXBElement<SuperaElMaximoDeTuristasException>(_SuperaElMaximoDeTuristasException_QNAME, SuperaElMaximoDeTuristasException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FechaAltaSalidaPosteriorAFechaSalidaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "FechaAltaSalidaPosteriorAFechaSalidaException")
    public JAXBElement<FechaAltaSalidaPosteriorAFechaSalidaException> createFechaAltaSalidaPosteriorAFechaSalidaException(FechaAltaSalidaPosteriorAFechaSalidaException value) {
        return new JAXBElement<FechaAltaSalidaPosteriorAFechaSalidaException>(_FechaAltaSalidaPosteriorAFechaSalidaException_QNAME, FechaAltaSalidaPosteriorAFechaSalidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaqueteNoCompradoExcepcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "PaqueteNoCompradoExcepcion")
    public JAXBElement<PaqueteNoCompradoExcepcion> createPaqueteNoCompradoExcepcion(PaqueteNoCompradoExcepcion value) {
        return new JAXBElement<PaqueteNoCompradoExcepcion>(_PaqueteNoCompradoExcepcion_QNAME, PaqueteNoCompradoExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadTuristicaNoAceptada }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "ActividadTuristicaNoAceptada")
    public JAXBElement<ActividadTuristicaNoAceptada> createActividadTuristicaNoAceptada(ActividadTuristicaNoAceptada value) {
        return new JAXBElement<ActividadTuristicaNoAceptada>(_ActividadTuristicaNoAceptada_QNAME, ActividadTuristicaNoAceptada.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FechaAltaActividadPosteriorAFechaAltaSalidaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "FechaAltaActividadPosteriorAFechaAltaSalidaException")
    public JAXBElement<FechaAltaActividadPosteriorAFechaAltaSalidaException> createFechaAltaActividadPosteriorAFechaAltaSalidaException(FechaAltaActividadPosteriorAFechaAltaSalidaException value) {
        return new JAXBElement<FechaAltaActividadPosteriorAFechaAltaSalidaException>(_FechaAltaActividadPosteriorAFechaAltaSalidaException_QNAME, FechaAltaActividadPosteriorAFechaAltaSalidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExisteConsumoParaLaActividadExcepcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "NoExisteConsumoParaLaActividadExcepcion")
    public JAXBElement<NoExisteConsumoParaLaActividadExcepcion> createNoExisteConsumoParaLaActividadExcepcion(NoExisteConsumoParaLaActividadExcepcion value) {
        return new JAXBElement<NoExisteConsumoParaLaActividadExcepcion>(_NoExisteConsumoParaLaActividadExcepcion_QNAME, NoExisteConsumoParaLaActividadExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaInscripcionPosteriorAFechaSalidaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://actividadesTuristicasService.publicar/", name = "AltaInscripcionPosteriorAFechaSalidaException")
    public JAXBElement<AltaInscripcionPosteriorAFechaSalidaException> createAltaInscripcionPosteriorAFechaSalidaException(AltaInscripcionPosteriorAFechaSalidaException value) {
        return new JAXBElement<AltaInscripcionPosteriorAFechaSalidaException>(_AltaInscripcionPosteriorAFechaSalidaException_QNAME, AltaInscripcionPosteriorAFechaSalidaException.class, null, value);
    }

}
