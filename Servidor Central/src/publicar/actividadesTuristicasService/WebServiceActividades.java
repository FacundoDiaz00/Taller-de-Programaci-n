package publicar.actividadesTuristicasService;

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
public class WebServiceActividades {

    private Endpoint endpoint = null;
    private Logger log;

    public WebServiceActividades(){
        this.log = Logger.getLogger("logger");
    }

    @WebMethod(exclude = true)
    public void publicar(){
        endpoint = Endpoint.publish(Cargador.getDirrecionAHacerDeploy() + "/actividades", this);
        log.info("Servicios de actividades publicado");
    }
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
        return endpoint;
    }

    @WebMethod
    public List<String> obtenerIdCategorias(){
        log.info("Solicitud a 'obtenerIdCategorias'");
        return Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdCategorias();
    }

    @WebMethod
    public List<String> obtenerIdDepartamentos(){
        log.info("Solicitud a 'obtenerIdDepartamentos'");
        return Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdDepartamentos();
    }

    @WebMethod
    public DtActividadTuristicaCollection obtenerDTActividadesTuristicasConfirmadasPorDepartamento(String departamento) throws ObjetoNoExisteEnTurismoUy {
        log.info("Solicitud a 'obtenerDTActividadesTuristicasConfirmadasPorDepartamento'");
        return new DtActividadTuristicaCollection(Fabrica.getInstancia().getIControladorActividadTuristica().obtenerDTActividadesTuristicasConfirmadasPorDepartamento(departamento));
    }
    @WebMethod
    public DtActividadTuristicaCollection obtenerDTActividadesTuristicasConfirmadasPorCategoria(String categoria) throws ObjetoNoExisteEnTurismoUy {
        log.info("Solicitud a 'obtenerDTActividadesTuristicasConfirmadasPorCategoria'");
        return new DtActividadTuristicaCollection(Fabrica.getInstancia().getIControladorActividadTuristica().obtenerDTActividadesTuristicasConfirmadasPorCategoria(categoria));
    }





}
