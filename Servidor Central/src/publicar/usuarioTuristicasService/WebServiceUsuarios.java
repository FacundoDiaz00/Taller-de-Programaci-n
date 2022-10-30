package publicar.usuarioTuristicasService;

import configuraciones.Cargador;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import logica.controladores.Fabrica;
import logica.datatypes.DTUsuario;
import logica.datatypes.colleciones.DTPaqueteCollection;
import logica.datatypes.colleciones.DTUsuarioCollection;

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
    public DTUsuarioCollection obtenerDTUsuarios(){
        log.info("Solicitud a 'obtenerDTUsuarios'");
        return new DTUsuarioCollection(Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuarios());
    }



}
