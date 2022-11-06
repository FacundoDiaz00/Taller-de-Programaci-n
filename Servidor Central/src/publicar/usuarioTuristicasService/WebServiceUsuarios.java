package publicar.usuarioTuristicasService;

import configuraciones.Cargador;
import excepciones.ContraseniaInvalidaException;
import excepciones.ErrorAlProcesar;
import excepciones.ModificacionUsuarioNoPermitida;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.UsuarioYaRegistradoException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;
import logica.datatypes.colleciones.DTUsuarioSeparadosPorTipoCollection;
import logica.utils.UtilsDT;

import java.time.LocalDate;
import java.util.logging.Logger;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class WebServiceUsuarios {
	private IControladorUsuario iControladorUsuario;

    private Endpoint endpoint = null;
    private Logger log;

    public WebServiceUsuarios(){
    	this.iControladorUsuario = Fabrica.getInstancia().getIControladorUsuario();
        this.log = Logger.getLogger("logger");
    }

    @WebMethod(exclude = true)
    public void publicar(){
        endpoint = Endpoint.publish(Cargador.getDireccionAHacerDeploy() + "/usuarios", this);
        log.info("Servicio de usuarios publicado");
    }
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
        return endpoint;
    }

    @WebMethod
    public DTUsuarioSeparadosPorTipoCollection obtenerDTUsuarios(){
        log.info("Solicitud a 'obtenerDTUsuarios'");

        return new DTUsuarioSeparadosPorTipoCollection(iControladorUsuario.obtenerDTUsuarios());
    }
    
    @WebMethod
    public DTUsuario obtenerDTUsuarioDetalle(String nickname) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTUsuarioDetalle'");
        return iControladorUsuario.obtenerDTUsuarioDetalle(nickname);
    }
    
    @WebMethod
    public DTUsuario obtenerDTUsuarioDetallePrivado(String nickname) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTUsuarioDetallePrivado'");
        return iControladorUsuario.obtenerDTUsuarioDetallePrivado(nickname);
    }
    
    @WebMethod
    public DTUsuario obtenerDTUsuario(String nickname) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTUsuario'");
        return iControladorUsuario.obtenerDTUsuario(nickname);
    }
    
    
    @WebMethod
    public void modificarUsuario(DTUsuario datosNuevos, String contrasenia, byte[] imgContent, String extImg) throws ModificacionUsuarioNoPermitida, ObjetoNoExisteEnTurismoUy, ErrorAlProcesar{
        log.info("Solicitud a 'modificarUsuario'");
        
        Imagen imgMetaData = null;
        if (imgContent.length > 0){
            imgMetaData = new Imagen("/usuarios/" + datosNuevos.getNickname() + extImg);
        } else if (!extImg.equals("BORRAR")) { // MUY chancho pero estoy 100% seguro que no hay extensiones de imágenes .BORRAR
            imgMetaData = iControladorUsuario.obtenerDTUsuario(datosNuevos.getNickname()).getImg();
        }
        
        
        if (contrasenia.equals(""))
        	contrasenia = null;

        iControladorUsuario.modificarUsuario(datosNuevos, contrasenia, imgMetaData);

        if (imgContent.length > 0) {
            UtilsDT.guardarImagen(imgMetaData.getPath(), imgContent);
        }
        
    }
    
    @WebMethod
    public DTUsuario obtenerDtUsuarioPorNickname(String nickname, String contrasenia) throws ObjetoNoExisteEnTurismoUy, ContraseniaInvalidaException  {
        log.info("Solicitud a 'obtenerDtUsuarioPorNickname'");
        return iControladorUsuario.obtenerDTUsuarioPorNickname(nickname, contrasenia);
    }
    
    @WebMethod
    public DTUsuario obtenerDtUsuarioPorEmail(String email, String contrasenia) throws ObjetoNoExisteEnTurismoUy, ContraseniaInvalidaException  {
        log.info("Solicitud a 'obtenerDtUsuarioPorEmail'");
        return iControladorUsuario.obtenerDTUsuarioPorEmail(email, contrasenia);
    }

    @WebMethod
    public void altaProveedor(String nickname, String nombre, String apellido, String correo, String contra,
                              String FNacimiento,
                              byte[] imgContent, String extImg , String descripcion, String link) throws UsuarioYaRegistradoException, ErrorAlProcesar {
        LocalDate fNacLocalDate = LocalDate.parse(FNacimiento, UtilsDT.formatterLocalDate);

        if (link != null && link.trim().length() == 0){
            link = null;
        }

        Imagen imgMetaData = null;
        if (imgContent.length > 0){
            imgMetaData = new Imagen("/usuarios/" + nickname + extImg);
        }

        iControladorUsuario.altaProveedor(nickname, nombre, apellido, correo, contra, fNacLocalDate, imgMetaData, descripcion, link);

        if (imgContent.length > 0) {
            UtilsDT.guardarImagen(imgMetaData.getPath(), imgContent);
        }

    }

    @WebMethod
    public void altaTurista(String nickname, String nombre, String apellido, String correo, String contra,
                 String FNacimiento, byte[] imgContent, String extImg, String nacionalidad) throws UsuarioYaRegistradoException, ErrorAlProcesar {

        LocalDate fNacLocalDate = LocalDate.parse(FNacimiento, UtilsDT.formatterLocalDate);

        Imagen imgMetaData = null;
        if (imgContent.length > 0){
            imgMetaData = new Imagen("/usuarios/" + nickname + extImg);
        }

        iControladorUsuario.altaTurista(nickname, nombre, apellido, correo, contra, fNacLocalDate, imgMetaData, nacionalidad);

        if (imgContent.length > 0) {
            UtilsDT.guardarImagen(imgMetaData.getPath(), imgContent);
        }

    }


    @WebMethod
    public void seguirODejarDeSeguirUsuario(String nickSeguidor, String nickSeguido) throws ObjetoNoExisteEnTurismoUy {
        iControladorUsuario.seguirODejarDeSeguirUsuario(nickSeguidor, nickSeguido);
    }

    @WebMethod
    public void agregarOEliminarActividadDeFavoritos(String nickTurista, String nombreAct)
            throws ObjetoNoExisteEnTurismoUy {
        iControladorUsuario.agregarOEliminarActividadDeFavoritos(nickTurista, nombreAct);
    }

    @WebMethod
    public boolean perteneceAFavoritosDeTurista(String nickTurista, String nombreAct) throws ObjetoNoExisteEnTurismoUy {
        return iControladorUsuario.perteneceAFavoritosDeTurista(nickTurista, nombreAct);
    }

    @WebMethod
    public boolean nicknameDisponibleParaNuevoUsuario(String nick) {
        return iControladorUsuario.nicknameDisponibleParaNuevoUsuario(nick);
    }

    @WebMethod
    public boolean emailDisponibleParaNuevoUsuario(String email) {
    	return iControladorUsuario.emailDisponibleParaNuevoUsuario(email);
    }

    @WebMethod
    public boolean usuariosSeSiguen(String nickSeguidor, String nickSeguido) throws ObjetoNoExisteEnTurismoUy {
        return Fabrica.getInstancia().getIControladorUsuario().usuariosSeSiguen(nickSeguidor, nickSeguido);
    }

    @WebMethod
    public DTUsuarioSeparadosPorTipoCollection obtenerSeguidores(String nickUsuario) throws ObjetoNoExisteEnTurismoUy{
        var usuarios = Fabrica.getInstancia().getIControladorUsuario().obtenerSeguidores(nickUsuario);
        return new DTUsuarioSeparadosPorTipoCollection(usuarios);
    }

    @WebMethod
    public DTUsuarioSeparadosPorTipoCollection obtenerSeguidos(String nickUsuario) throws ObjetoNoExisteEnTurismoUy{
        var usuarios = Fabrica.getInstancia().getIControladorUsuario().obtenerSeguidos(nickUsuario);
        return new DTUsuarioSeparadosPorTipoCollection(usuarios);
    }

    
}
