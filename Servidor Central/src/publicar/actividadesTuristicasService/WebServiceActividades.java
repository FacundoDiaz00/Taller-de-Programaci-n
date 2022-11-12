package publicar.actividadesTuristicasService;

import configuraciones.Cargador;
import excepciones.*;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import logica.controladores.Fabrica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;
import logica.datatypes.EstadoActividadTuristica;
import logica.datatypes.Imagen;
import logica.datatypes.colleciones.DtActividadTuristicaCollection;
import logica.datatypes.colleciones.DtMapActividadSalidaTuristicaCollection;
import logica.datatypes.colleciones.DtSalidaTuristicaCollection;
import logica.utils.UtilsDT;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        endpoint = Endpoint.publish(Cargador.getDireccionAHacerDeploy() + "/actividades", this);
        log.info("Servicios de actividades publicado");
    }
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
        return endpoint;
    }

    @WebMethod
    public String[] obtenerIdCategorias(){
        log.info("Solicitud a 'obtenerIdCategorias'");
        List<String> salida = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdCategorias();
        String[] salidaArry = new String[salida.size()];
        salida.toArray(salidaArry);
        return salidaArry;
       
    }

    
    @WebMethod
    public String[] obtenerIdDepartamentos(){
        log.info("Solicitud a 'obtenerIdDepartamentos'");
        List<String> salida = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdDepartamentos();
        
        String[] salidaArry = new String[salida.size()];
        salida.toArray(salidaArry);
        return salidaArry;
        
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
                                int duracion, float costo, String ciudad, byte[] imgContent, String extImg, String[] categorias, String urlVideo) throws ObjetoNoExisteEnTurismoUy, ActividadTuristicaYaRegistradaException, ErrorAlProcesar {
        log.info("Solicitud a 'altaActividadTuristica'");

        Imagen imgMetaData = null;
        if (imgContent.length > 0){
            imgMetaData = new Imagen("/actividades/" + nombreActividad + extImg);
        }

        
        
        Fabrica.getInstancia().getIControladorActividadTuristica().altaActividadTuristica(nombreProveedor, departamento, nombreActividad,
                descripcion, duracion, costo, ciudad, null, imgMetaData, Arrays.asList(categorias) , urlVideo);

        if (imgContent.length > 0) {
            UtilsDT.guardarImagen(imgMetaData.getPath(), imgContent);
        }
    }


    @WebMethod
    public DTSalidaTuristica obtenerDTSalidaTuristica(String nomSalida) throws ObjetoNoExisteEnTurismoUy {
        log.info("Solicitud a 'altaActividadTuristica'");
        return Fabrica.getInstancia().getIControladorActividadTuristica().obtenerDTSalidaTuristica(nomSalida);
    }

    @WebMethod
    public void altaSalidaTuristica(String actividad, String nombreSalida, String fechaYHoraSalidaStr,
                             String lugar, int cantMaxTur, byte[] imgContent, String extImg) throws FechaAltaSalidaPosteriorAFechaSalidaException, ActividadTuristicaNoAceptada, FechaAltaActividadPosteriorAFechaAltaSalidaException, ObjetoNoExisteEnTurismoUy, SalidaYaRegistradaException, ErrorAlProcesar {
        log.info("Solicitud a 'altaSalidaTuristica'");
        LocalDateTime fechaHoraSalida = LocalDateTime.parse(fechaYHoraSalidaStr, UtilsDT.formatterLocalDateTime);

        Imagen imgMetaData = null;
        if (imgContent.length > 0){
            imgMetaData = new Imagen("/salidas/" + nombreSalida + extImg);
        }

        Fabrica.getInstancia().getIControladorActividadTuristica().altaSalidaTuristica(actividad, nombreSalida, fechaHoraSalida, null, lugar, cantMaxTur,  imgMetaData);

        if (imgContent.length > 0) {
            UtilsDT.guardarImagen(imgMetaData.getPath(), imgContent);
        }
    }

    @WebMethod
    public String[] obtenerIdComprasDisponiblesParaInscripcion(String nombreActividad, String nickTurista) throws ObjetoNoExisteEnTurismoUy {
        log.info("Solicitud a 'obtenerIdComprasDisponiblesParaInscripcion'");
        List<String> salida = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdComprasDisponiblesParaInscripcion(nombreActividad, nickTurista);
        String[] salidaArry = new String[salida.size()];
        salida.toArray(salidaArry);
        return salidaArry;
        
    }

    @WebMethod
    public  void altaInscripcionSalidaTuristica(String nomSalTurim, String nicknameTuris, int canTuris, String nombrePaquete)
            throws InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException,
            FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException,
            CompraPaqueteVencidoExcepcion, CompraConConsumosInsuficientesExcepcion, PaqueteNoCompradoExcepcion,
            NoExisteConsumoParaLaActividadExcepcion, ObjetoNoExisteEnTurismoUy{

        log.info("Solicitud a 'altaInscripcionSalidaTuristica'");
        if (nombrePaquete  != null  && nombrePaquete.trim().length() == 0){
           nombrePaquete  =  null;
        }

        Fabrica.getInstancia().getIControladorActividadTuristica().altaInscripcionSalidaTuristica(nomSalTurim, nicknameTuris, canTuris, null, nombrePaquete);

    }

    @WebMethod
    public void cambiarEstadoDeActividadTuristica(String idActividad, EstadoActividadTuristica nuevoEstado)
            throws TurismoUyException{
    	log.info("Solicitud a 'cambiarEstadoDeActividadTuristica'");
    	Fabrica.getInstancia().getIControladorActividadTuristica().cambiarEstadoDeActividadTuristica(idActividad, nuevoEstado);
    }

    @WebMethod
    public void incrementarContadorActividad(String nombreAct) {
    	log.info("Solicitud a 'incrementarContadorActividad'");
    	try {
    		Fabrica.getInstancia().getIControladorActividadTuristica().incrementarContadorActividad(nombreAct);			
		} catch (TurismoUyException e) {
			// No contabilizamos la visita
			log.severe("No se pudo contabilizar la visita a la actividad " + nombreAct);
		}
    }

    @WebMethod
    public void incrementarContadorSalida(String nombreSal) {
    	log.info("Solicitud a 'incrementarContadorSalida'");
    	try {
    		Fabrica.getInstancia().getIControladorActividadTuristica().incrementarContadorSalida(nombreSal);			
		} catch (TurismoUyException e) {
			// No contabilizamos la visita
			log.severe("No se pudo contabilizar la visita a la salida " + nombreSal);
		}
    }
    
    
    @WebMethod
    public DtMapActividadSalidaTuristicaCollection obtenerDTSalidasTuristicas() throws ObjetoNoExisteEnTurismoUy{
        log.info("Solicitud a 'incrementarContadorSalida'");

        Map<String, List<DTSalidaTuristica>> salidas =  Fabrica.getInstancia().getIControladorActividadTuristica().obtenerDTSalidasTuristicas();

        Map<String, DtSalidaTuristicaCollection> respuestaInfo = new HashMap<>();

        for (String key : salidas.keySet()){
            respuestaInfo.put(key, new DtSalidaTuristicaCollection(salidas.get(key)));
        }

    	return new DtMapActividadSalidaTuristicaCollection(respuestaInfo);
    }

}
