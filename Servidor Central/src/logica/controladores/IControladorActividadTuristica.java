package logica.controladores;

import excepciones.*;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTInscripcion;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public interface IControladorActividadTuristica {


    /**
     * Crea un nuevo departamento en base a los parametros pasados
     * @param nom
     * @param descr
     * @param URL
     * @throws DeparamentoYaRegistradoException
     */
    void altaDepartamento(String nom, String descr, String URL) throws DeparamentoYaRegistradoException;

	/**
	 * Devuelve el nombre de todos los departamentos cargados en el sistema
	 * @return
	 */
	List<String> obtenerIdDepartamentos();

	/**
	 * Crea una actividad turística con los parámetros
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
	void altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad, String descripcion, int duracion, float costo, String ciudad, LocalDate fechaAlta) throws ActividadTuristicaYaRegistradaException;

	/**
	 * Obtiene los detalles de la actividad turística identificada por 'nombreAct'
	 * @param nombreAct
	 * @return
	 */
	DTActividadTuristicaDetalle obtenerDetallesActividadTuristica(String nombreAct);

	/**
	 * Devuelve los nombres de todas las actividades turísticas.
	 * @param departamento
	 * @return
	 */
	List<String> obtenerIdActividadesTuristicas(String departamento);




	/**
	 * Devueve los DtSalidasTuristas de todas las salidas asociadas con la actividad identificada con nombreActTuri
	 * @param nombreActTuri identificador de la actividad turistica
	 * @return
	 */
	List<DTSalidaTuristica> obtenerDTSalidasTuristicas(String nombreActTuri);

	/**
	 *
	 * @param nomSalTurim
	 * @param nicknameTuris
	 * @param canTuris
	 * @param fechaInscrp
	 * @throws InscripcionYaRegistradaException
	 * @throws SuperaElMaximoDeTuristasException
	 */
	void altaInscripcionSalidaTuristica(String nomSalTurim, String nicknameTuris, int canTuris, LocalDate fechaInscrp) throws InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException, FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;

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
	void altaSalidaTuristica(String actividad, String nombre, LocalDateTime fechaYHoraSalida,LocalDate fechaAlta, String lugar, int cantMaxTur) throws SalidaYaRegistradaException, FechaAltaActividadPosteriorAFechaAltaSalidaException, FechaAltaSalidaPosteriorAFechaSalidaException;

	/**
	 * Devuelve los nombres de todas las salidas registradas en el sistema.
	 * @param act
	 * @return
	 */
	List<String> obtenerIdSalidasTuristicas(String act);

	/**
	 * Devuelve los datos de la salida identificada por el nombre pasada por parámetro.
	 * @param nomSal
	 * @return
	 */
	DTSalidaTuristica obtenerDTSalidaTuristica(String nomSal);

	/**
	 * Devuelve los detalles de los datos de la salida identificada por el nombre pasada por parámetro.
	 * @param nomSal
	 * @return
	 */
	DTSalidaTuristicaDetalle obtenerDTSalidaTuristicaDetalle(String nomSal);

	/**
	 * Devuelve los datos de la inscripción que tiene un link entre la salida identificada por 'nomSal' y por el turista identificado por 'nick'
	 * @param nick
	 * @param nomSal
	 * @return
	 */
	DTInscripcion obtenerDTInscripcion(String nick, String nomSal);
	
}
