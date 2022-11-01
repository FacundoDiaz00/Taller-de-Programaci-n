package publicar.usuarioTuristicasService;

import configuraciones.Cargador;
import excepciones.ModificacionUsuarioNoPermitida;
import excepciones.ObjetoNoExisteEnTurismoUy;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import logica.controladores.Fabrica;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;
import logica.datatypes.DTUsuarioDetallePorTipo;
import logica.datatypes.DTUsuarioDetallePrivadoPorTipo;
import logica.datatypes.DTUsuarioPorTipo;
import logica.datatypes.colleciones.DTUsuarioSeparadosPorTipoCollection;

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
    public DTUsuarioDetallePorTipo obtenerDTUsuarioDetalle(String nickname) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTUsuarioDetalle'");
        return new DTUsuarioDetallePorTipo(Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuarioDetalle(nickname));
    }
    
    @WebMethod
    public DTUsuarioDetallePrivadoPorTipo obtenerDTUsuarioDetallePrivado(String nickname) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTUsuarioDetallePrivado'");
        return new DTUsuarioDetallePrivadoPorTipo(Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuarioDetallePrivado(nickname));
    }
    
    @WebMethod
    public DTUsuarioPorTipo obtenerDTUsuario(String nickname) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTUsuario'");
        return new DTUsuarioPorTipo(Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuario(nickname));
    }
    
    @WebMethod
    public void modificarUsuario(DTUsuarioPorTipo datosNuevos, String contrasenia, boolean borrarFoto) throws ModificacionUsuarioNoPermitida, ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTUsuarios'");
        DTUsuario datosss = datosNuevos.esTurista() ? datosNuevos.getDtTurista() : datosNuevos.getDtProveedor();
        
        Fabrica.getInstancia().getIControladorUsuario().modificarUsuario(datosss, contrasenia, borrarFoto);
    }
}
