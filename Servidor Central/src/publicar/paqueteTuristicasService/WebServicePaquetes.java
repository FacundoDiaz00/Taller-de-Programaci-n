package publicar.paqueteTuristicasService;

import configuraciones.Cargador;
import excepciones.ObjetoNoExisteEnTurismoUy;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import logica.controladores.Fabrica;
import logica.datatypes.colleciones.DTPaqueteCollection;
import logica.datatypes.colleciones.DtActividadTuristicaCollection;

import java.util.List;
import java.util.logging.Logger;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class WebServicePaquetes {

    private Endpoint endpoint = null;
    private Logger log;

    public WebServicePaquetes(){
        this.log = Logger.getLogger("logger");
    }

    @WebMethod(exclude = true)
    public void publicar(){
        endpoint = Endpoint.publish(Cargador.getDirrecionAHacerDeploy() + "/paquetes", this);
        log.info("Servicio de paquetes publicado");
    }
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
        return endpoint;
    }

    @WebMethod
    public DTPaqueteCollection obtenerDtPaquetes(){
        log.info("Solicitud a 'obtenerDtPaquetes'");
        return new DTPaqueteCollection(Fabrica.getInstancia().getIControladorPaquete().obtenerDTPaquetes());
    }

    @WebMethod
    public DTPaqueteCollection obtenerDTPaquetesPorCategoria(String cat) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTPaquetesPorCategoria'");
        return new DTPaqueteCollection(Fabrica.getInstancia().getIControladorPaquete().obtenerDTPaquetesPorCategoria(cat));
    }





}
