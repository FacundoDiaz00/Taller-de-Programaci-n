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
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;
import logica.datatypes.colleciones.DTUsuarioSeparadosPorTipoCollection;
import logica.utils.UtilsDT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class WebServiceUsuarios {

    private Endpoint endpoint = null;
    private Logger log;

    public WebServiceUsuarios(){
        this.log = Logger.getLogger("logger");
    }

    @WebMethod(exclude = true)
    public void publicar(){
        endpoint = Endpoint.publish(Cargador.getDirrecionAHacerDeploy() + "/usuarios", this);
        log.info("Servicio de usuarios publicado");
    }
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
        return endpoint;
    }

    @WebMethod
    public DTUsuarioSeparadosPorTipoCollection obtenerDTUsuarios(){
        log.info("Solicitud a 'obtenerDTUsuarios'");

        List<DTProveedor> proveedores = new ArrayList<>();
        List<DTTurista> turistas = new ArrayList<>();

        for (DTUsuario dtu : Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuarios()){
            if (dtu instanceof DTProveedor){
               proveedores.add((DTProveedor) dtu);
            } else {
                turistas.add((DTTurista) dtu);
            }
        }

        return new DTUsuarioSeparadosPorTipoCollection(proveedores, turistas);
    }
    
    @WebMethod
    public DTUsuario obtenerDTUsuarioDetalle(String nickname) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTUsuarioDetalle'");
        return Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuarioDetalle(nickname);
    }
    
    @WebMethod
    public DTUsuario obtenerDTUsuarioDetallePrivado(String nickname) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTUsuarioDetallePrivado'");
        return Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuarioDetallePrivado(nickname);
    }
    
    @WebMethod
    public DTUsuario obtenerDTUsuario(String nickname) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTUsuario'");
        return Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuario(nickname);
    }
    
    @WebMethod
    public void modificarUsuario(DTUsuario datosNuevos, String contrasenia, boolean borrarFoto) throws ModificacionUsuarioNoPermitida, ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'modificarUsuario'");
        
        Fabrica.getInstancia().getIControladorUsuario().modificarUsuario(datosNuevos, contrasenia, borrarFoto);
    }
    
    @WebMethod
    public DTUsuario obtenerDtUsuarioPorNickname(String nickname, String contrasenia) throws ObjetoNoExisteEnTurismoUy, ContraseniaInvalidaException  {
        log.info("Solicitud a 'obtenerDtUsuarioPorNickname'");
        return Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuarioPorNickname(nickname, contrasenia);
    }
    
    @WebMethod
    public DTUsuario obtenerDtUsuarioPorEmail(String email, String contrasenia) throws ObjetoNoExisteEnTurismoUy, ContraseniaInvalidaException  {
        log.info("Solicitud a 'obtenerDtUsuarioPorEmail'");
        return Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuarioPorEmail(email, contrasenia);
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

        Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nickname, nombre, apellido, correo, contra, fNacLocalDate, imgMetaData, descripcion, link);

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

        Fabrica.getInstancia().getIControladorUsuario().altaTurista(nickname, nombre, apellido, correo, contra, fNacLocalDate, imgMetaData, nacionalidad);

        if (imgContent.length > 0) {
            UtilsDT.guardarImagen(imgMetaData.getPath(), imgContent);
        }

    }

    
}
