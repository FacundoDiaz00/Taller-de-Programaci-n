package publicar.actividadesTuristicasService;

import configuraciones.Cargador;
import excepciones.*;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import logica.controladores.Fabrica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTSalidaTuristicaDetalle;
import logica.datatypes.Imagen;
import logica.datatypes.colleciones.DTPaqueteCollection;
import logica.datatypes.colleciones.DtActividadTuristicaCollection;
import logica.utils.UtilsDT;

import java.io.IOException;
import java.time.LocalDateTime;
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

    @WebMethod
    public DTActividadTuristicaDetalle obtenerDTActividadTuristicaDetalle(String id) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTActividadesTuristicasConfirmadasPorCategoria'");
        return Fabrica.getInstancia().getIControladorActividadTuristica().obtenerDTActividadTuristicaDetalle(id);
    }

    @WebMethod
    public DTSalidaTuristicaDetalle obtenerDTSalidaTuristicaDetalle(String id) throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'obtenerDTActividadesTuristicasConfirmadasPorCategoria'");
        return Fabrica.getInstancia().getIControladorActividadTuristica().obtenerDTSalidaTuristicaDetalle(id);
    }

    @WebMethod
    public void altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad, String descripcion,
                                int duracion, float costo, String ciudad, byte[] imgContent, String extImg, List<String> categorias, String urlVideo) throws ObjetoNoExisteEnTurismoUy, ActividadTuristicaYaRegistradaException, ErrorAlProcesar {
        log.info("Solicitud a 'altaActividadTuristica'");

        Imagen imgMetaData = null;
        if (imgContent.length > 0){
            imgMetaData = new Imagen("/actividades/" + nombreActividad + extImg);
        }

        Fabrica.getInstancia().getIControladorActividadTuristica().altaActividadTuristica(nombreProveedor, departamento, nombreActividad,
                descripcion, duracion, costo, ciudad, null, imgMetaData, categorias, urlVideo);

        if (imgContent.length > 0) {
            UtilsDT.guardarImagen(imgMetaData.getPath(), imgContent);
        }
    }


    @WebMethod
    public void altaSalidaTuristica(String actividad, String nombreSalida, String fechaYHoraSalidaStr,
                             String lugar, int cantMaxTur, byte[] imgContent, String extImg) throws FechaAltaSalidaPosteriorAFechaSalidaException, ActividadTuristicaNoAceptada, FechaAltaActividadPosteriorAFechaAltaSalidaException, ObjetoNoExisteEnTurismoUy, SalidaYaRegistradaException, ErrorAlProcesar {
        LocalDateTime fechaHoraSalida = LocalDateTime.parse(fechaYHoraSalidaStr, UtilsDT.formatterLocalDateTime);

        Imagen imgMetaData = null;
        if (imgContent.length > 0){
            imgMetaData = new Imagen("/salidas/" + nombreSalida + extImg);
        }

        Fabrica.getInstancia().getIControladorActividadTuristica().altaSalidaTuristica(actividad,nombreSalida, fechaHoraSalida, null, lugar, cantMaxTur,  imgMetaData);

        if (imgContent.length > 0) {
            UtilsDT.guardarImagen(imgMetaData.getPath(), imgContent);
        }
    }

    @WebMethod
    public List<String> obtenerIdComprasDisponiblesParaInscripcion(String nombreActividad, String nickTurista) throws ObjetoNoExisteEnTurismoUy {
        return Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdComprasDisponiblesParaInscripcion(nombreActividad, nickTurista);
    }

    @WebMethod
    public  void altaInscripcionSalidaTuristica(String nomSalTurim, String nicknameTuris, int canTuris, String nombrePaquete)
            throws InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException,
            FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException,
            CompraPaqueteVencidoExcepcion, CompraConConsumosInsuficientesExcepcion, PaqueteNoCompradoExcepcion,
            NoExisteConsumoParaLaActividadExcepcion, ObjetoNoExisteEnTurismoUy{

        if (nombrePaquete  != null  && nombrePaquete.trim().length() == 0){
           nombrePaquete  =  null;
        }

        Fabrica.getInstancia().getIControladorActividadTuristica().altaInscripcionSalidaTuristica(nomSalTurim, nicknameTuris, canTuris, null, nombrePaquete);

    }




}
