
package publicar.usuarioturisticasservice;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publicar.usuarioturisticasservice package. 
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

    private final static QName _ErrorAlProcesar_QNAME = new QName("http://usuarioTuristicasService.publicar/", "ErrorAlProcesar");
    private final static QName _ModificacionUsuarioNoPermitida_QNAME = new QName("http://usuarioTuristicasService.publicar/", "ModificacionUsuarioNoPermitida");
    private final static QName _ObjetoNoExisteEnTurismoUy_QNAME = new QName("http://usuarioTuristicasService.publicar/", "ObjetoNoExisteEnTurismoUy");
    private final static QName _UsuarioYaRegistradoException_QNAME = new QName("http://usuarioTuristicasService.publicar/", "UsuarioYaRegistradoException");
    private final static QName _ContraseniaInvalidaException_QNAME = new QName("http://usuarioTuristicasService.publicar/", "ContraseniaInvalidaException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicar.usuarioturisticasservice
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
     * Create an instance of {@link UsuarioYaRegistradoException }
     * 
     */
    public UsuarioYaRegistradoException createUsuarioYaRegistradoException() {
        return new UsuarioYaRegistradoException();
    }

    /**
     * Create an instance of {@link ContraseniaInvalidaException }
     * 
     */
    public ContraseniaInvalidaException createContraseniaInvalidaException() {
        return new ContraseniaInvalidaException();
    }

    /**
     * Create an instance of {@link ErrorAlProcesar }
     * 
     */
    public ErrorAlProcesar createErrorAlProcesar() {
        return new ErrorAlProcesar();
    }

    /**
     * Create an instance of {@link ModificacionUsuarioNoPermitida }
     * 
     */
    public ModificacionUsuarioNoPermitida createModificacionUsuarioNoPermitida() {
        return new ModificacionUsuarioNoPermitida();
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
     * Create an instance of {@link DtTuristaDetalle }
     * 
     */
    public DtTuristaDetalle createDtTuristaDetalle() {
        return new DtTuristaDetalle();
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
     * Create an instance of {@link DtUsuario }
     * 
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtUsuarioSeparadosPorTipoCollection }
     * 
     */
    public DtUsuarioSeparadosPorTipoCollection createDtUsuarioSeparadosPorTipoCollection() {
        return new DtUsuarioSeparadosPorTipoCollection();
    }

    /**
     * Create an instance of {@link DtTuristaDetallePrivado }
     * 
     */
    public DtTuristaDetallePrivado createDtTuristaDetallePrivado() {
        return new DtTuristaDetallePrivado();
    }

    /**
     * Create an instance of {@link DtSalidaTuristicaDetalle }
     * 
     */
    public DtSalidaTuristicaDetalle createDtSalidaTuristicaDetalle() {
        return new DtSalidaTuristicaDetalle();
    }

    /**
     * Create an instance of {@link DtTurista }
     * 
     */
    public DtTurista createDtTurista() {
        return new DtTurista();
    }

    /**
     * Create an instance of {@link DtProveedorDetallePrivado }
     * 
     */
    public DtProveedorDetallePrivado createDtProveedorDetallePrivado() {
        return new DtProveedorDetallePrivado();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link DtProveedor }
     * 
     */
    public DtProveedor createDtProveedor() {
        return new DtProveedor();
    }

    /**
     * Create an instance of {@link DtProveedorDetalle }
     * 
     */
    public DtProveedorDetalle createDtProveedorDetalle() {
        return new DtProveedorDetalle();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorAlProcesar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://usuarioTuristicasService.publicar/", name = "ErrorAlProcesar")
    public JAXBElement<ErrorAlProcesar> createErrorAlProcesar(ErrorAlProcesar value) {
        return new JAXBElement<ErrorAlProcesar>(_ErrorAlProcesar_QNAME, ErrorAlProcesar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModificacionUsuarioNoPermitida }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://usuarioTuristicasService.publicar/", name = "ModificacionUsuarioNoPermitida")
    public JAXBElement<ModificacionUsuarioNoPermitida> createModificacionUsuarioNoPermitida(ModificacionUsuarioNoPermitida value) {
        return new JAXBElement<ModificacionUsuarioNoPermitida>(_ModificacionUsuarioNoPermitida_QNAME, ModificacionUsuarioNoPermitida.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjetoNoExisteEnTurismoUy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://usuarioTuristicasService.publicar/", name = "ObjetoNoExisteEnTurismoUy")
    public JAXBElement<ObjetoNoExisteEnTurismoUy> createObjetoNoExisteEnTurismoUy(ObjetoNoExisteEnTurismoUy value) {
        return new JAXBElement<ObjetoNoExisteEnTurismoUy>(_ObjetoNoExisteEnTurismoUy_QNAME, ObjetoNoExisteEnTurismoUy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioYaRegistradoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://usuarioTuristicasService.publicar/", name = "UsuarioYaRegistradoException")
    public JAXBElement<UsuarioYaRegistradoException> createUsuarioYaRegistradoException(UsuarioYaRegistradoException value) {
        return new JAXBElement<UsuarioYaRegistradoException>(_UsuarioYaRegistradoException_QNAME, UsuarioYaRegistradoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContraseniaInvalidaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://usuarioTuristicasService.publicar/", name = "ContraseniaInvalidaException")
    public JAXBElement<ContraseniaInvalidaException> createContraseniaInvalidaException(ContraseniaInvalidaException value) {
        return new JAXBElement<ContraseniaInvalidaException>(_ContraseniaInvalidaException_QNAME, ContraseniaInvalidaException.class, null, value);
    }

}
