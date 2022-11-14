
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

    private final static QName _ActividadTuristicaNoAceptada_QNAME = new QName("http://actividadesturisticasservice.publicar/", "ActividadTuristicaNoAceptada");
    private final static QName _ActividadTuristicaYaRegistradaException_QNAME = new QName("http://actividadesturisticasservice.publicar/", "ActividadTuristicaYaRegistradaException");
    private final static QName _AltaInscripcionPosteriorAFechaSalidaException_QNAME = new QName("http://actividadesturisticasservice.publicar/", "AltaInscripcionPosteriorAFechaSalidaException");
    private final static QName _CompraConConsumosInsuficientesExcepcion_QNAME = new QName("http://actividadesturisticasservice.publicar/", "CompraConConsumosInsuficientesExcepcion");
    private final static QName _CompraPaqueteVencidoExcepcion_QNAME = new QName("http://actividadesturisticasservice.publicar/", "CompraPaqueteVencidoExcepcion");
    private final static QName _ErrorAlProcesar_QNAME = new QName("http://actividadesturisticasservice.publicar/", "ErrorAlProcesar");
    private final static QName _FechaAltaActividadPosteriorAFechaAltaSalidaException_QNAME = new QName("http://actividadesturisticasservice.publicar/", "FechaAltaActividadPosteriorAFechaAltaSalidaException");
    private final static QName _FechaAltaSalidaPosteriorAFechaSalidaException_QNAME = new QName("http://actividadesturisticasservice.publicar/", "FechaAltaSalidaPosteriorAFechaSalidaException");
    private final static QName _FechaAltaSalidaTuristicaPosteriorAFechaInscripcion_QNAME = new QName("http://actividadesturisticasservice.publicar/", "FechaAltaSalidaTuristicaPosteriorAFechaInscripcion");
    private final static QName _InscripcionYaRegistradaException_QNAME = new QName("http://actividadesturisticasservice.publicar/", "InscripcionYaRegistradaException");
    private final static QName _NoExisteConsumoParaLaActividadExcepcion_QNAME = new QName("http://actividadesturisticasservice.publicar/", "NoExisteConsumoParaLaActividadExcepcion");
    private final static QName _ObjetoNoExisteEnTurismoUy_QNAME = new QName("http://actividadesturisticasservice.publicar/", "ObjetoNoExisteEnTurismoUy");
    private final static QName _PaqueteNoCompradoExcepcion_QNAME = new QName("http://actividadesturisticasservice.publicar/", "PaqueteNoCompradoExcepcion");
    private final static QName _SalidaYaRegistradaException_QNAME = new QName("http://actividadesturisticasservice.publicar/", "SalidaYaRegistradaException");
    private final static QName _SuperaElMaximoDeTuristasException_QNAME = new QName("http://actividadesturisticasservice.publicar/", "SuperaElMaximoDeTuristasException");
    private final static QName _TurismoUyException_QNAME = new QName("http://actividadesturisticasservice.publicar/", "TurismoUyException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicar.actividadesturisticasservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtMapActividadSalidaTuristicaCollection }
     * 
     * @return
     *     the new instance of {@link DtMapActividadSalidaTuristicaCollection }
     */
    public DtMapActividadSalidaTuristicaCollection createDtMapActividadSalidaTuristicaCollection() {
        return new DtMapActividadSalidaTuristicaCollection();
    }

    /**
     * Create an instance of {@link DtMapActividadSalidaTuristicaCollection.MapSalidas }
     * 
     * @return
     *     the new instance of {@link DtMapActividadSalidaTuristicaCollection.MapSalidas }
     */
    public DtMapActividadSalidaTuristicaCollection.MapSalidas createDtMapActividadSalidaTuristicaCollectionMapSalidas() {
        return new DtMapActividadSalidaTuristicaCollection.MapSalidas();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaDetalle }
     * 
     * @return
     *     the new instance of {@link DtActividadTuristicaDetalle }
     */
    public DtActividadTuristicaDetalle createDtActividadTuristicaDetalle() {
        return new DtActividadTuristicaDetalle();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaDetalle.Paquetes }
     * 
     * @return
     *     the new instance of {@link DtActividadTuristicaDetalle.Paquetes }
     */
    public DtActividadTuristicaDetalle.Paquetes createDtActividadTuristicaDetallePaquetes() {
        return new DtActividadTuristicaDetalle.Paquetes();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaDetalle.Salidas }
     * 
     * @return
     *     the new instance of {@link DtActividadTuristicaDetalle.Salidas }
     */
    public DtActividadTuristicaDetalle.Salidas createDtActividadTuristicaDetalleSalidas() {
        return new DtActividadTuristicaDetalle.Salidas();
    }

    /**
     * Create an instance of {@link DtPaqueteDetalles }
     * 
     * @return
     *     the new instance of {@link DtPaqueteDetalles }
     */
    public DtPaqueteDetalles createDtPaqueteDetalles() {
        return new DtPaqueteDetalles();
    }

    /**
     * Create an instance of {@link DtPaqueteDetalles.Actividades }
     * 
     * @return
     *     the new instance of {@link DtPaqueteDetalles.Actividades }
     */
    public DtPaqueteDetalles.Actividades createDtPaqueteDetallesActividades() {
        return new DtPaqueteDetalles.Actividades();
    }

    /**
     * Create an instance of {@link ActividadTuristicaNoAceptada }
     * 
     * @return
     *     the new instance of {@link ActividadTuristicaNoAceptada }
     */
    public ActividadTuristicaNoAceptada createActividadTuristicaNoAceptada() {
        return new ActividadTuristicaNoAceptada();
    }

    /**
     * Create an instance of {@link ActividadTuristicaYaRegistradaException }
     * 
     * @return
     *     the new instance of {@link ActividadTuristicaYaRegistradaException }
     */
    public ActividadTuristicaYaRegistradaException createActividadTuristicaYaRegistradaException() {
        return new ActividadTuristicaYaRegistradaException();
    }

    /**
     * Create an instance of {@link AltaInscripcionPosteriorAFechaSalidaException }
     * 
     * @return
     *     the new instance of {@link AltaInscripcionPosteriorAFechaSalidaException }
     */
    public AltaInscripcionPosteriorAFechaSalidaException createAltaInscripcionPosteriorAFechaSalidaException() {
        return new AltaInscripcionPosteriorAFechaSalidaException();
    }

    /**
     * Create an instance of {@link CompraConConsumosInsuficientesExcepcion }
     * 
     * @return
     *     the new instance of {@link CompraConConsumosInsuficientesExcepcion }
     */
    public CompraConConsumosInsuficientesExcepcion createCompraConConsumosInsuficientesExcepcion() {
        return new CompraConConsumosInsuficientesExcepcion();
    }

    /**
     * Create an instance of {@link CompraPaqueteVencidoExcepcion }
     * 
     * @return
     *     the new instance of {@link CompraPaqueteVencidoExcepcion }
     */
    public CompraPaqueteVencidoExcepcion createCompraPaqueteVencidoExcepcion() {
        return new CompraPaqueteVencidoExcepcion();
    }

    /**
     * Create an instance of {@link ErrorAlProcesar }
     * 
     * @return
     *     the new instance of {@link ErrorAlProcesar }
     */
    public ErrorAlProcesar createErrorAlProcesar() {
        return new ErrorAlProcesar();
    }

    /**
     * Create an instance of {@link FechaAltaActividadPosteriorAFechaAltaSalidaException }
     * 
     * @return
     *     the new instance of {@link FechaAltaActividadPosteriorAFechaAltaSalidaException }
     */
    public FechaAltaActividadPosteriorAFechaAltaSalidaException createFechaAltaActividadPosteriorAFechaAltaSalidaException() {
        return new FechaAltaActividadPosteriorAFechaAltaSalidaException();
    }

    /**
     * Create an instance of {@link FechaAltaSalidaPosteriorAFechaSalidaException }
     * 
     * @return
     *     the new instance of {@link FechaAltaSalidaPosteriorAFechaSalidaException }
     */
    public FechaAltaSalidaPosteriorAFechaSalidaException createFechaAltaSalidaPosteriorAFechaSalidaException() {
        return new FechaAltaSalidaPosteriorAFechaSalidaException();
    }

    /**
     * Create an instance of {@link FechaAltaSalidaTuristicaPosteriorAFechaInscripcion }
     * 
     * @return
     *     the new instance of {@link FechaAltaSalidaTuristicaPosteriorAFechaInscripcion }
     */
    public FechaAltaSalidaTuristicaPosteriorAFechaInscripcion createFechaAltaSalidaTuristicaPosteriorAFechaInscripcion() {
        return new FechaAltaSalidaTuristicaPosteriorAFechaInscripcion();
    }

    /**
     * Create an instance of {@link InscripcionYaRegistradaException }
     * 
     * @return
     *     the new instance of {@link InscripcionYaRegistradaException }
     */
    public InscripcionYaRegistradaException createInscripcionYaRegistradaException() {
        return new InscripcionYaRegistradaException();
    }

    /**
     * Create an instance of {@link NoExisteConsumoParaLaActividadExcepcion }
     * 
     * @return
     *     the new instance of {@link NoExisteConsumoParaLaActividadExcepcion }
     */
    public NoExisteConsumoParaLaActividadExcepcion createNoExisteConsumoParaLaActividadExcepcion() {
        return new NoExisteConsumoParaLaActividadExcepcion();
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
     * Create an instance of {@link PaqueteNoCompradoExcepcion }
     * 
     * @return
     *     the new instance of {@link PaqueteNoCompradoExcepcion }
     */
    public PaqueteNoCompradoExcepcion createPaqueteNoCompradoExcepcion() {
        return new PaqueteNoCompradoExcepcion();
    }

    /**
     * Create an instance of {@link SalidaYaRegistradaException }
     * 
     * @return
     *     the new instance of {@link SalidaYaRegistradaException }
     */
    public SalidaYaRegistradaException createSalidaYaRegistradaException() {
        return new SalidaYaRegistradaException();
    }

    /**
     * Create an instance of {@link SuperaElMaximoDeTuristasException }
     * 
     * @return
     *     the new instance of {@link SuperaElMaximoDeTuristasException }
     */
    public SuperaElMaximoDeTuristasException createSuperaElMaximoDeTuristasException() {
        return new SuperaElMaximoDeTuristasException();
    }

    /**
     * Create an instance of {@link TurismoUyException }
     * 
     * @return
     *     the new instance of {@link TurismoUyException }
     */
    public TurismoUyException createTurismoUyException() {
        return new TurismoUyException();
    }

    /**
     * Create an instance of {@link DtSalidaTuristicaDetalle }
     * 
     * @return
     *     the new instance of {@link DtSalidaTuristicaDetalle }
     */
    public DtSalidaTuristicaDetalle createDtSalidaTuristicaDetalle() {
        return new DtSalidaTuristicaDetalle();
    }

    /**
     * Create an instance of {@link DtSalidaTuristica }
     * 
     * @return
     *     the new instance of {@link DtSalidaTuristica }
     */
    public DtSalidaTuristica createDtSalidaTuristica() {
        return new DtSalidaTuristica();
    }

    /**
     * Create an instance of {@link DtInscripcion }
     * 
     * @return
     *     the new instance of {@link DtInscripcion }
     */
    public DtInscripcion createDtInscripcion() {
        return new DtInscripcion();
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
     * Create an instance of {@link DtCompra }
     * 
     * @return
     *     the new instance of {@link DtCompra }
     */
    public DtCompra createDtCompra() {
        return new DtCompra();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     * @return
     *     the new instance of {@link DtPaquete }
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
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
     * Create an instance of {@link DtActividadTuristicaCollection }
     * 
     * @return
     *     the new instance of {@link DtActividadTuristicaCollection }
     */
    public DtActividadTuristicaCollection createDtActividadTuristicaCollection() {
        return new DtActividadTuristicaCollection();
    }

    /**
     * Create an instance of {@link DtSalidaTuristicaCollection }
     * 
     * @return
     *     the new instance of {@link DtSalidaTuristicaCollection }
     */
    public DtSalidaTuristicaCollection createDtSalidaTuristicaCollection() {
        return new DtSalidaTuristicaCollection();
    }

    /**
     * Create an instance of {@link DtMapActividadSalidaTuristicaCollection.MapSalidas.Entry }
     * 
     * @return
     *     the new instance of {@link DtMapActividadSalidaTuristicaCollection.MapSalidas.Entry }
     */
    public DtMapActividadSalidaTuristicaCollection.MapSalidas.Entry createDtMapActividadSalidaTuristicaCollectionMapSalidasEntry() {
        return new DtMapActividadSalidaTuristicaCollection.MapSalidas.Entry();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaDetalle.Paquetes.Entry }
     * 
     * @return
     *     the new instance of {@link DtActividadTuristicaDetalle.Paquetes.Entry }
     */
    public DtActividadTuristicaDetalle.Paquetes.Entry createDtActividadTuristicaDetallePaquetesEntry() {
        return new DtActividadTuristicaDetalle.Paquetes.Entry();
    }

    /**
     * Create an instance of {@link DtActividadTuristicaDetalle.Salidas.Entry }
     * 
     * @return
     *     the new instance of {@link DtActividadTuristicaDetalle.Salidas.Entry }
     */
    public DtActividadTuristicaDetalle.Salidas.Entry createDtActividadTuristicaDetalleSalidasEntry() {
        return new DtActividadTuristicaDetalle.Salidas.Entry();
    }

    /**
     * Create an instance of {@link DtPaqueteDetalles.Actividades.Entry }
     * 
     * @return
     *     the new instance of {@link DtPaqueteDetalles.Actividades.Entry }
     */
    public DtPaqueteDetalles.Actividades.Entry createDtPaqueteDetallesActividadesEntry() {
        return new DtPaqueteDetalles.Actividades.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadTuristicaNoAceptada }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ActividadTuristicaNoAceptada }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "ActividadTuristicaNoAceptada")
    public JAXBElement<ActividadTuristicaNoAceptada> createActividadTuristicaNoAceptada(ActividadTuristicaNoAceptada value) {
        return new JAXBElement<>(_ActividadTuristicaNoAceptada_QNAME, ActividadTuristicaNoAceptada.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadTuristicaYaRegistradaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ActividadTuristicaYaRegistradaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "ActividadTuristicaYaRegistradaException")
    public JAXBElement<ActividadTuristicaYaRegistradaException> createActividadTuristicaYaRegistradaException(ActividadTuristicaYaRegistradaException value) {
        return new JAXBElement<>(_ActividadTuristicaYaRegistradaException_QNAME, ActividadTuristicaYaRegistradaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaInscripcionPosteriorAFechaSalidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaInscripcionPosteriorAFechaSalidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "AltaInscripcionPosteriorAFechaSalidaException")
    public JAXBElement<AltaInscripcionPosteriorAFechaSalidaException> createAltaInscripcionPosteriorAFechaSalidaException(AltaInscripcionPosteriorAFechaSalidaException value) {
        return new JAXBElement<>(_AltaInscripcionPosteriorAFechaSalidaException_QNAME, AltaInscripcionPosteriorAFechaSalidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompraConConsumosInsuficientesExcepcion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CompraConConsumosInsuficientesExcepcion }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "CompraConConsumosInsuficientesExcepcion")
    public JAXBElement<CompraConConsumosInsuficientesExcepcion> createCompraConConsumosInsuficientesExcepcion(CompraConConsumosInsuficientesExcepcion value) {
        return new JAXBElement<>(_CompraConConsumosInsuficientesExcepcion_QNAME, CompraConConsumosInsuficientesExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompraPaqueteVencidoExcepcion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CompraPaqueteVencidoExcepcion }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "CompraPaqueteVencidoExcepcion")
    public JAXBElement<CompraPaqueteVencidoExcepcion> createCompraPaqueteVencidoExcepcion(CompraPaqueteVencidoExcepcion value) {
        return new JAXBElement<>(_CompraPaqueteVencidoExcepcion_QNAME, CompraPaqueteVencidoExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorAlProcesar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ErrorAlProcesar }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "ErrorAlProcesar")
    public JAXBElement<ErrorAlProcesar> createErrorAlProcesar(ErrorAlProcesar value) {
        return new JAXBElement<>(_ErrorAlProcesar_QNAME, ErrorAlProcesar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FechaAltaActividadPosteriorAFechaAltaSalidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FechaAltaActividadPosteriorAFechaAltaSalidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "FechaAltaActividadPosteriorAFechaAltaSalidaException")
    public JAXBElement<FechaAltaActividadPosteriorAFechaAltaSalidaException> createFechaAltaActividadPosteriorAFechaAltaSalidaException(FechaAltaActividadPosteriorAFechaAltaSalidaException value) {
        return new JAXBElement<>(_FechaAltaActividadPosteriorAFechaAltaSalidaException_QNAME, FechaAltaActividadPosteriorAFechaAltaSalidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FechaAltaSalidaPosteriorAFechaSalidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FechaAltaSalidaPosteriorAFechaSalidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "FechaAltaSalidaPosteriorAFechaSalidaException")
    public JAXBElement<FechaAltaSalidaPosteriorAFechaSalidaException> createFechaAltaSalidaPosteriorAFechaSalidaException(FechaAltaSalidaPosteriorAFechaSalidaException value) {
        return new JAXBElement<>(_FechaAltaSalidaPosteriorAFechaSalidaException_QNAME, FechaAltaSalidaPosteriorAFechaSalidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FechaAltaSalidaTuristicaPosteriorAFechaInscripcion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FechaAltaSalidaTuristicaPosteriorAFechaInscripcion }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "FechaAltaSalidaTuristicaPosteriorAFechaInscripcion")
    public JAXBElement<FechaAltaSalidaTuristicaPosteriorAFechaInscripcion> createFechaAltaSalidaTuristicaPosteriorAFechaInscripcion(FechaAltaSalidaTuristicaPosteriorAFechaInscripcion value) {
        return new JAXBElement<>(_FechaAltaSalidaTuristicaPosteriorAFechaInscripcion_QNAME, FechaAltaSalidaTuristicaPosteriorAFechaInscripcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InscripcionYaRegistradaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InscripcionYaRegistradaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "InscripcionYaRegistradaException")
    public JAXBElement<InscripcionYaRegistradaException> createInscripcionYaRegistradaException(InscripcionYaRegistradaException value) {
        return new JAXBElement<>(_InscripcionYaRegistradaException_QNAME, InscripcionYaRegistradaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExisteConsumoParaLaActividadExcepcion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExisteConsumoParaLaActividadExcepcion }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "NoExisteConsumoParaLaActividadExcepcion")
    public JAXBElement<NoExisteConsumoParaLaActividadExcepcion> createNoExisteConsumoParaLaActividadExcepcion(NoExisteConsumoParaLaActividadExcepcion value) {
        return new JAXBElement<>(_NoExisteConsumoParaLaActividadExcepcion_QNAME, NoExisteConsumoParaLaActividadExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjetoNoExisteEnTurismoUy }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObjetoNoExisteEnTurismoUy }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "ObjetoNoExisteEnTurismoUy")
    public JAXBElement<ObjetoNoExisteEnTurismoUy> createObjetoNoExisteEnTurismoUy(ObjetoNoExisteEnTurismoUy value) {
        return new JAXBElement<>(_ObjetoNoExisteEnTurismoUy_QNAME, ObjetoNoExisteEnTurismoUy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaqueteNoCompradoExcepcion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PaqueteNoCompradoExcepcion }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "PaqueteNoCompradoExcepcion")
    public JAXBElement<PaqueteNoCompradoExcepcion> createPaqueteNoCompradoExcepcion(PaqueteNoCompradoExcepcion value) {
        return new JAXBElement<>(_PaqueteNoCompradoExcepcion_QNAME, PaqueteNoCompradoExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SalidaYaRegistradaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SalidaYaRegistradaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "SalidaYaRegistradaException")
    public JAXBElement<SalidaYaRegistradaException> createSalidaYaRegistradaException(SalidaYaRegistradaException value) {
        return new JAXBElement<>(_SalidaYaRegistradaException_QNAME, SalidaYaRegistradaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuperaElMaximoDeTuristasException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SuperaElMaximoDeTuristasException }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "SuperaElMaximoDeTuristasException")
    public JAXBElement<SuperaElMaximoDeTuristasException> createSuperaElMaximoDeTuristasException(SuperaElMaximoDeTuristasException value) {
        return new JAXBElement<>(_SuperaElMaximoDeTuristasException_QNAME, SuperaElMaximoDeTuristasException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TurismoUyException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TurismoUyException }{@code >}
     */
    @XmlElementDecl(namespace = "http://actividadesturisticasservice.publicar/", name = "TurismoUyException")
    public JAXBElement<TurismoUyException> createTurismoUyException(TurismoUyException value) {
        return new JAXBElement<>(_TurismoUyException_QNAME, TurismoUyException.class, null, value);
    }

}
