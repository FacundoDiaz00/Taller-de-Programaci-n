
package publicar.usuarioturisticasservice;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


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

    private final static QName _ContraseniaInvalidaException_QNAME = new QName("http://usuarioTuristicasService.publicar/", "ContraseniaInvalidaException");
    private final static QName _ModificacionUsuarioNoPermitida_QNAME = new QName("http://usuarioTuristicasService.publicar/", "ModificacionUsuarioNoPermitida");
    private final static QName _ObjetoNoExisteEnTurismoUy_QNAME = new QName("http://usuarioTuristicasService.publicar/", "ObjetoNoExisteEnTurismoUy");
    private final static QName _UsuarioYaRegistradoException_QNAME = new QName("http://usuarioTuristicasService.publicar/", "UsuarioYaRegistradoException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicar.usuarioturisticasservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtProveedorDetallePrivado }
     * 
     * @return
     *     the new instance of {@link DtProveedorDetallePrivado }
     */
    public DtProveedorDetallePrivado createDtProveedorDetallePrivado() {
        return new DtProveedorDetallePrivado();
    }

    /**
     * Create an instance of {@link DtProveedorDetallePrivado.ActividadesNoConfirmadas }
     * 
     * @return
     *     the new instance of {@link DtProveedorDetallePrivado.ActividadesNoConfirmadas }
     */
    public DtProveedorDetallePrivado.ActividadesNoConfirmadas createDtProveedorDetallePrivadoActividadesNoConfirmadas() {
        return new DtProveedorDetallePrivado.ActividadesNoConfirmadas();
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
     * Create an instance of {@link ContraseniaInvalidaException }
     * 
     * @return
     *     the new instance of {@link ContraseniaInvalidaException }
     */
    public ContraseniaInvalidaException createContraseniaInvalidaException() {
        return new ContraseniaInvalidaException();
    }

    /**
     * Create an instance of {@link ModificacionUsuarioNoPermitida }
     * 
     * @return
     *     the new instance of {@link ModificacionUsuarioNoPermitida }
     */
    public ModificacionUsuarioNoPermitida createModificacionUsuarioNoPermitida() {
        return new ModificacionUsuarioNoPermitida();
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
     * Create an instance of {@link UsuarioYaRegistradoException }
     * 
     * @return
     *     the new instance of {@link UsuarioYaRegistradoException }
     */
    public UsuarioYaRegistradoException createUsuarioYaRegistradoException() {
        return new UsuarioYaRegistradoException();
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
     * Create an instance of {@link DtCompra }
     * 
     * @return
     *     the new instance of {@link DtCompra }
     */
    public DtCompra createDtCompra() {
        return new DtCompra();
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
     * Create an instance of {@link DtPaquete }
     * 
     * @return
     *     the new instance of {@link DtPaquete }
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link DtProveedor }
     * 
     * @return
     *     the new instance of {@link DtProveedor }
     */
    public DtProveedor createDtProveedor() {
        return new DtProveedor();
    }

    /**
     * Create an instance of {@link DtProveedorDetalle }
     * 
     * @return
     *     the new instance of {@link DtProveedorDetalle }
     */
    public DtProveedorDetalle createDtProveedorDetalle() {
        return new DtProveedorDetalle();
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
     * Create an instance of {@link DtTurista }
     * 
     * @return
     *     the new instance of {@link DtTurista }
     */
    public DtTurista createDtTurista() {
        return new DtTurista();
    }

    /**
     * Create an instance of {@link DtTuristaDetalle }
     * 
     * @return
     *     the new instance of {@link DtTuristaDetalle }
     */
    public DtTuristaDetalle createDtTuristaDetalle() {
        return new DtTuristaDetalle();
    }

    /**
     * Create an instance of {@link DtTuristaDetallePrivado }
     * 
     * @return
     *     the new instance of {@link DtTuristaDetallePrivado }
     */
    public DtTuristaDetallePrivado createDtTuristaDetallePrivado() {
        return new DtTuristaDetallePrivado();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtUsuarioDetallePorTipo }
     * 
     * @return
     *     the new instance of {@link DtUsuarioDetallePorTipo }
     */
    public DtUsuarioDetallePorTipo createDtUsuarioDetallePorTipo() {
        return new DtUsuarioDetallePorTipo();
    }

    /**
     * Create an instance of {@link DtUsuarioDetallePrivadoPorTipo }
     * 
     * @return
     *     the new instance of {@link DtUsuarioDetallePrivadoPorTipo }
     */
    public DtUsuarioDetallePrivadoPorTipo createDtUsuarioDetallePrivadoPorTipo() {
        return new DtUsuarioDetallePrivadoPorTipo();
    }

    /**
     * Create an instance of {@link DtUsuarioPorTipo }
     * 
     * @return
     *     the new instance of {@link DtUsuarioPorTipo }
     */
    public DtUsuarioPorTipo createDtUsuarioPorTipo() {
        return new DtUsuarioPorTipo();
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
     * Create an instance of {@link DtUsuarioSeparadosPorTipoCollection }
     * 
     * @return
     *     the new instance of {@link DtUsuarioSeparadosPorTipoCollection }
     */
    public DtUsuarioSeparadosPorTipoCollection createDtUsuarioSeparadosPorTipoCollection() {
        return new DtUsuarioSeparadosPorTipoCollection();
    }

    /**
     * Create an instance of {@link DtProveedorDetallePrivado.ActividadesNoConfirmadas.Entry }
     * 
     * @return
     *     the new instance of {@link DtProveedorDetallePrivado.ActividadesNoConfirmadas.Entry }
     */
    public DtProveedorDetallePrivado.ActividadesNoConfirmadas.Entry createDtProveedorDetallePrivadoActividadesNoConfirmadasEntry() {
        return new DtProveedorDetallePrivado.ActividadesNoConfirmadas.Entry();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ContraseniaInvalidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ContraseniaInvalidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://usuarioTuristicasService.publicar/", name = "ContraseniaInvalidaException")
    public JAXBElement<ContraseniaInvalidaException> createContraseniaInvalidaException(ContraseniaInvalidaException value) {
        return new JAXBElement<>(_ContraseniaInvalidaException_QNAME, ContraseniaInvalidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModificacionUsuarioNoPermitida }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ModificacionUsuarioNoPermitida }{@code >}
     */
    @XmlElementDecl(namespace = "http://usuarioTuristicasService.publicar/", name = "ModificacionUsuarioNoPermitida")
    public JAXBElement<ModificacionUsuarioNoPermitida> createModificacionUsuarioNoPermitida(ModificacionUsuarioNoPermitida value) {
        return new JAXBElement<>(_ModificacionUsuarioNoPermitida_QNAME, ModificacionUsuarioNoPermitida.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjetoNoExisteEnTurismoUy }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObjetoNoExisteEnTurismoUy }{@code >}
     */
    @XmlElementDecl(namespace = "http://usuarioTuristicasService.publicar/", name = "ObjetoNoExisteEnTurismoUy")
    public JAXBElement<ObjetoNoExisteEnTurismoUy> createObjetoNoExisteEnTurismoUy(ObjetoNoExisteEnTurismoUy value) {
        return new JAXBElement<>(_ObjetoNoExisteEnTurismoUy_QNAME, ObjetoNoExisteEnTurismoUy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioYaRegistradoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioYaRegistradoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://usuarioTuristicasService.publicar/", name = "UsuarioYaRegistradoException")
    public JAXBElement<UsuarioYaRegistradoException> createUsuarioYaRegistradoException(UsuarioYaRegistradoException value) {
        return new JAXBElement<>(_UsuarioYaRegistradoException_QNAME, UsuarioYaRegistradoException.class, null, value);
    }

}
