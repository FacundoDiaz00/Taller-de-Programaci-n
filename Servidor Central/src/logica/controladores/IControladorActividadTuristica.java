package logica.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.AltaInscripcionPosteriorAFechaSalidaException;
import excepciones.DeparamentoYaRegistradoException;
import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import excepciones.InscripcionYaRegistradaException;
import excepciones.SalidaYaRegistradaException;
import excepciones.SuperaElMaximoDeTuristasException;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTInscripcion;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;
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
	 */
	void altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad, String descripcion,
			int duracion, float costo, String ciudad, LocalDate fechaAlta, Imagen img, List<String> categorias)
			throws ActividadTuristicaYaRegistradaException;

	/**
	 * Obtiene los detalles de la actividad turística identificada por
	 * 'nombreAct'
	 * 
	 * @param nombreAct
	 * @return
	 */
	DTActividadTuristicaDetalle obtenerDTActividadTuristicaDetalle(String nombreAct);

	List<DTActividadTuristica> obtenerDTActividadesTuristicas();

	/**
	 * Devuelve los nombres de todas las actividades turísticas.
	 * 
	 * @param departamento
	 * @return
	 */
	List<String> obtenerIdActividadesTuristicas(String departamento);

	List<String> obtenerIdActividadesTuristicasConfirmadasPorCategoria(String categoria);

	/**
	 * Devueve los DtSalidasTuristas de todas las salidas asociadas con la
	 * actividad identificada con nombreActTuri
	 * 
	 * @param nombreActTuri identificador de la actividad turistica
	 * @return
	 */
	List<DTSalidaTuristica> obtenerDTSalidasTuristicas(String nombreActTuri);

	// Versión web
	void altaInscripcionSalidaTuristica(String nomSalTurim, String nicknameTuris, int canTuris, String nombrePaquete)
			throws InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException,
			FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException;

	// Versión estacion de trabajo
	void altaInscripcionSalidaTuristica(String nomSalTurim, String nicknameTuris, int canTuris,
			LocalDate fechaInscripcion) throws InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException,
			FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException;

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
	 */
	void altaSalidaTuristica(String actividad, String nombre, LocalDateTime fechaYHoraSalida, LocalDate fechaAlta,
			String lugar, int cantMaxTur, Imagen img) throws SalidaYaRegistradaException,
			FechaAltaActividadPosteriorAFechaAltaSalidaException, FechaAltaSalidaPosteriorAFechaSalidaException;

	/**
	 * Devuelve los nombres de todas las salidas registradas en el sistema.
	 * 
	 * @param act
	 * @return
	 */
	List<String> obtenerIdSalidasTuristicas(String act);

	List<String> obtenerIdComprasDisponiblesParaInscripcion(String nombreActividad, String nickTurista);

	/**
	 * Devuelve los datos de la salida identificada por el nombre pasada por
	 * parámetro.
	 * 
	 * @param nomSal
	 * @return
	 */
	DTSalidaTuristica obtenerDTSalidaTuristica(String nomSal);

	/**
	 * Devuelve los detalles de los datos de la salida identificada por el
	 * nombre pasada por parámetro.
	 * 
	 * @param nomSal
	 * @return
	 */
	DTSalidaTuristicaDetalle obtenerDTSalidaTuristicaDetalle(String nomSal);

	/**
	 * Devuelve los datos de la inscripción que tiene un link entre la salida
	 * identificada por 'nomSal' y por el turista identificado por 'nick'
	 * 
	 * @param nick
	 * @param nomSal
	 * @return
	 */
	DTInscripcion obtenerDTInscripcion(String nick, String nomSal);

	List<String> obtenerIdActividadesTuristicasAgregadas();

	void aceptarORechazarActividadTuristica(String idActividad, boolean b);

}
