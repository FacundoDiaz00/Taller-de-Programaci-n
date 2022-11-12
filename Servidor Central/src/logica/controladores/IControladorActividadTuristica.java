package logica.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import excepciones.ActividadTuristicaNoAceptada;
import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.AltaInscripcionPosteriorAFechaSalidaException;
import excepciones.CategoriaYaRegistradaException;
import excepciones.CompraConConsumosInsuficientesExcepcion;
import excepciones.CompraPaqueteVencidoExcepcion;
import excepciones.DeparamentoYaRegistradoException;
import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import excepciones.InscripcionYaRegistradaException;
import excepciones.NoExisteConsumoParaLaActividadExcepcion;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.PaqueteNoCompradoExcepcion;
import excepciones.SalidaYaRegistradaException;
import excepciones.SuperaElMaximoDeTuristasException;
import excepciones.TurismoUyException;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTInscripcion;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;
import logica.datatypes.EstadoActividadTuristica;
import logica.datatypes.Imagen;

/**
 * @author Equipo taller prog 16
 */

public interface IControladorActividadTuristica {

    /**
     * Crea un nuevo departamento en base a los parametros pasados
     * 
     * @param nom
     * @param descr
     * @param URL
     * @throws DeparamentoYaRegistradoException
     */
    void altaDepartamento(String nom, String descr, String URL) throws DeparamentoYaRegistradoException;

    /**
     * Devuelve el nombre de todos los departamentos cargados en el sistema
     * 
     * @return
     */
    List<String> obtenerIdDepartamentos();

    /**
     * Devuelve el nombre de todos las categorias cargadas en el sistema
     * 
     * @return
     */
    List<String> obtenerIdCategorias();

    /**
     * Crea una actividad turística con los parámetros
     * 
     * @param nombreProveedor
     * @param departamento
     * @param nombreActividad
     * @param descripcion
     * @param duracion
     * @param costo
     * @param ciudad
     * @param fechaAlta
     * @throws ActividadTuristicaYaRegistradaException
     * 
     */
    void altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad, String descripcion,
            int duracion, float costo, String ciudad, LocalDate fechaAlta, Imagen img, List<String> categorias, String urlVideo)
            throws ActividadTuristicaYaRegistradaException, ObjetoNoExisteEnTurismoUy;

    /**
     * Obtiene los detalles de la actividad turística identificada por
     * 'nombreAct'
     * 
     * @param nombreAct
     * @return
     * @throws ObjetoNoExisteEnTurismoUy
     */
    DTActividadTuristicaDetalle obtenerDTActividadTuristicaDetalle(String nombreAct) throws ObjetoNoExisteEnTurismoUy;

    /**
     * Obteiene el DTActividad turistica de todas las actividades turisticas
     * confirmadas que estan asociadas con la categoria identificado por nomCat
     * 
     * @param nomCat
     * @return
     */
    List<DTActividadTuristica> obtenerDTActividadesTuristicasConfirmadasPorCategoria(String nomCat)
            throws ObjetoNoExisteEnTurismoUy;

    /**
     * Obteiene el DTActividad turistica de todas las actividades turisticas
     * confirmadas que estan asociadas con el departamento identificado por
     * nomDep
     * 
     * @param nomDep
     * @return
     */
    List<DTActividadTuristica> obtenerDTActividadesTuristicasConfirmadasPorDepartamento(String nomDep)
            throws ObjetoNoExisteEnTurismoUy;

    List<DTActividadTuristica> obtenerDTActividadesTuristicas();

    /**
     * Devuelve los nombres de todas las actividades turísticas.
     * 
     * @param departamento
     * @return
     * 
     */
    List<String> obtenerIdActividadesTuristicas(String departamento) throws ObjetoNoExisteEnTurismoUy;

    List<String> obtenerIdActividadesTuristicasConfirmadasPorCategoria(String categoria)
            throws ObjetoNoExisteEnTurismoUy;

    /**
     * Devueve los DtSalidasTuristas de todas las salidas asociadas con la
     * actividad identificada con nombreActTuri
     * 
     * @param nombreActTuri identificador de la actividad turistica
     * @return
     * @throws ObjetoNoExisteEnTurismoUy
     */
    List<DTSalidaTuristica> obtenerDTSalidasTuristicas(String nombreActTuri) throws ObjetoNoExisteEnTurismoUy;

    /**
     * Crea la inscrpicon de un turistia a una salida turistica
     * Notar que en la estacion de trabajo este ultimo parametro siempre tiene que
     * ir en null
     *
     * @param nomSalTurim
     * @param nicknameTuris
     * @param canTuris
     * @param fechaInscripcion
     * @param nombrePaquete
     */
    void altaInscripcionSalidaTuristica(String nomSalTurim, String nicknameTuris, int canTuris,
            LocalDate fechaInscripcion, String nombrePaquete)
            throws InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException,
            FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException,
            CompraPaqueteVencidoExcepcion, CompraConConsumosInsuficientesExcepcion, PaqueteNoCompradoExcepcion,
            NoExisteConsumoParaLaActividadExcepcion, ObjetoNoExisteEnTurismoUy;

    /**
     *
     * @param actividad
     * @param nombre
     * @param fechaYHoraSalida
     * @param fechaAlta
     * @param lugar
     * @param cantMaxTur
     * @throws SalidaYaRegistradaException
     * @throws FechaAltaActividadPosteriorAFechaAltaSalidaException
     * @throws FechaAltaSalidaPosteriorAFechaSalidaException
     * @throws ObjetoNoExisteEnTurismoUy
     * @throws ActividadTuristicaNoAceptada
     */
    void altaSalidaTuristica(String actividad, String nombre, LocalDateTime fechaYHoraSalida, LocalDate fechaAlta,
            String lugar, int cantMaxTur, Imagen img)
            throws SalidaYaRegistradaException, FechaAltaActividadPosteriorAFechaAltaSalidaException,
            FechaAltaSalidaPosteriorAFechaSalidaException, ObjetoNoExisteEnTurismoUy, ActividadTuristicaNoAceptada;

    /**
     * Devuelve los nombres de todas las salidas registradas en el sistema.
     * 
     * @param act
     * @return
     * @throws ObjetoNoExisteEnTurismoUy
     */
    List<String> obtenerIdSalidasTuristicas(String act) throws ObjetoNoExisteEnTurismoUy;

    /**
     * Devuelve los id paquete disponibles para esa actividad y ese turista
     * 
     * @param nombreActividad
     * @param nickTurista
     * @return
     * @throws ObjetoNoExisteEnTurismoUy
     */
    List<String> obtenerIdComprasDisponiblesParaInscripcion(String nombreActividad, String nickTurista)
            throws ObjetoNoExisteEnTurismoUy;

    /**
     * Devuelve los datos de la salida identificada por el nombre pasada por
     * parámetro.
     * 
     * @param nomSal
     * @return
     * @throws ObjetoNoExisteEnTurismoUy
     */
    DTSalidaTuristica obtenerDTSalidaTuristica(String nomSal) throws ObjetoNoExisteEnTurismoUy;

    /**
     * Devuelve los detalles de los datos de la salida identificada por el
     * nombre pasada por parámetro.
     * 
     * @param nomSal
     * @return
     * @throws ObjetoNoExisteEnTurismoUy
     */
    DTSalidaTuristicaDetalle obtenerDTSalidaTuristicaDetalle(String nomSal) throws ObjetoNoExisteEnTurismoUy;

    /**
     * Devuelve los datos de la inscripción que tiene un link entre la salida
     * identificada por 'nomSal' y por el turista identificado por 'nick'
     * 
     * @param nick
     * @param nomSal
     * @return
     * @throws ObjetoNoExisteEnTurismoUy
     */
    DTInscripcion obtenerDTInscripcion(String nick, String nomSal) throws ObjetoNoExisteEnTurismoUy;

    public void altaCategoria(String nombre) throws CategoriaYaRegistradaException;

    List<String> obtenerIdActividadesTuristicasAgregadas();

    // Esto sumariza los casos de uso aceptar/rechazar y finalizar actividad turística
    void cambiarEstadoDeActividadTuristica(String idActividad, EstadoActividadTuristica nuevoEstado) throws TurismoUyException;

    Map<String, List<DTSalidaTuristica>> obtenerDTSalidasTuristicas() throws ObjetoNoExisteEnTurismoUy;

	void incrementarContadorActividad(String nombreAct) throws ObjetoNoExisteEnTurismoUy;

	void incrementarContadorSalida(String nombreSal) throws ObjetoNoExisteEnTurismoUy;
	
	// Devuelve una lista ordenada (descendente) de 4-tuplas con los datos: Actividad o Salida | Proveedor | Tipo | Cantidad de visitas
	List<String[]> obtenerDatosVisitas();
}
